 package com.tuanche.console.util.http;
 
 import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParamBean;
import org.apache.http.util.EntityUtils;

import com.tuanche.console.util.http.NetworkException;
 
 public class HttpClientProcessor extends AbstractHttpProcessor
 {
   private ThreadSafeClientConnManager mgr;
   private HttpClient httpClient;
   private IdleConnectionMonitor monitor;
   private static final AtomicInteger threadId = new AtomicInteger(0);
 
   public HttpClientProcessor(int maxConnection, int closeIdleTimeSec, int maxRoute, ProcessorConfig config)
   {
     this.mgr = new ThreadSafeClientConnManager();
     this.mgr.setMaxTotal(maxConnection);
     this.mgr.setDefaultMaxPerRoute(maxRoute);
     this.mgr.closeIdleConnections(closeIdleTimeSec, TimeUnit.SECONDS);
 
     HttpParams defaultParams = parse(getDefaultProcessorConfig());
     this.httpClient = new DefaultHttpClient(this.mgr, defaultParams);
     this.monitor = new IdleConnectionMonitor(this.mgr, closeIdleTimeSec);
     this.monitor.start();
   }
 
   public String post(String url, String content, ProcessorConfig config) throws NetworkException
   {
     try {
       HttpPost httppost = new HttpPost(url);
       httppost.setParams(parse(config));
       StringEntity entity = new StringEntity(content);
       httppost.setEntity(entity);
       return getResponse(httppost);
     } catch (Exception e) {
       throw new NetworkException(e);
     }
   }
 
   public String get(String url, ProcessorConfig config) throws NetworkException
   {
     try {
       HttpGet httpget = new HttpGet(url);
       httpget.setParams(parse(config));
       return getResponse(httpget);
     } catch (Exception e) {
       throw new NetworkException(e);
     }
   }
 
   private String getResponse(HttpUriRequest request) throws ParseException, IOException {
     String responseString = null;
     HttpResponse response = this.httpClient.execute(request);
     HttpEntity entity = response.getEntity();
     if (entity != null) {
       responseString = EntityUtils.toString(entity);
     }
     return responseString;
   }
 
   private HttpParams parse(ProcessorConfig config) {
     HttpParams params = new BasicHttpParams();
     HttpProtocolParamBean paramsBean = new HttpProtocolParamBean(params);
     paramsBean.setVersion(HttpVersion.HTTP_1_1);
     paramsBean.setContentCharset(config.charset);
     paramsBean.setUseExpectContinue(Boolean.FALSE.booleanValue());
     paramsBean.setUserAgent(config.userAgent);
     return params;
   }
 
   public void shutdown()
   {
     this.httpClient.getConnectionManager().shutdown();
     if (this.monitor != null)
       this.monitor.shutdown();
   }
 
   public void closeIdleConnection()
   {
     this.httpClient.getConnectionManager().closeIdleConnections(0L, TimeUnit.SECONDS);
   }
 
   private static class IdleConnectionMonitor extends Thread
   {
     private ThreadSafeClientConnManager mgr;
     private int intervalTime;
     private volatile boolean shutdown;
 
     public IdleConnectionMonitor(ThreadSafeClientConnManager mgr, int intervalTime)
     {
       this.mgr = mgr;
       this.intervalTime = intervalTime;
       setDaemon(true);
       setName("HttpClient-Idle-Connection-Monitor-" + HttpClientProcessor.threadId.incrementAndGet());
     }
 
     public void run()
     {
       try {
         while (!this.shutdown)
           synchronized (this) {
             wait((this.intervalTime + 60) * 1000);
             AbstractHttpProcessor.logger.info(getName() + "execute close idle connection:" + this.mgr.getConnectionsInPool());
             this.mgr.closeIdleConnections(this.intervalTime, TimeUnit.SECONDS);
             AbstractHttpProcessor.logger.info(getName() + "finished  close idle connection:" + this.mgr.getConnectionsInPool());
           }
       }
       catch (InterruptedException e)
       {
       }
     }
 
     public void shutdown()
     {
       this.shutdown = true;
       synchronized (this) {
         notifyAll();
       }
     }
   }
 }