 package com.tuanche.console.util.http;
 
 import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
 public class LightHttpProcessor extends AbstractHttpProcessor
 {
   public String post(String reqURL, String params, ProcessorConfig config)
     throws NetworkException
   {
     return getHttpResponse(reqURL, params, "POST", config.charset, config.timeout, config.contentType, config.userAgent);
   }
 
   public String get(String reqURL, ProcessorConfig config) throws NetworkException
   {
     return getHttpResponse(reqURL, null, "GET", config.charset, config.timeout, config.contentType, config.userAgent);
   }
 
   public void shutdown()
   {
   }
 
   public void closeIdleConnection()
   {
   }
 
   private static String getHttpResponse(String reqURL, String params, String method, String charset, int timeout, String contentType, String userAgent)
     throws NetworkException
   {
     logger.debug(String.format("NETCLIENT %s METHOD TO REQUEST:%s\nPOST PARAMETERS:%s", new Object[] { method, reqURL, params }));
 
     String response = null;
     HttpURLConnection connection = null;
     try {
       URL url = new URL(reqURL);
       connection = (HttpURLConnection)url.openConnection();
       connection.setDoOutput(true);
       connection.setUseCaches(false);
       connection.setRequestProperty("Content-type", contentType);
       connection.setRequestProperty("User-Agent", userAgent);
       connection.setRequestMethod(method);
       connection.setConnectTimeout(timeout);
       connection.connect();
       if (params != null) {
         BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), charset));
 
         writer.write(params);
         writer.flush();
         writer.close();
       }
       InputStream is = connection.getInputStream();
 
       ByteArrayOutputStream os = new ByteArrayOutputStream();
       int i = -1;
       while ((i = is.read()) != -1) {
         os.write(i);
       }
       response = new String(os.toByteArray(), charset);
       is.close();
       os.close();
     } catch (Exception e) {
       throw new NetworkException(e);
     } finally {
       if (connection != null) {
         connection.disconnect();
       }
     }
     return response;
   }
 }