/**
 * @FileName:
 * @Package: com.asura.weixin.service.impl
 *
 * @author afi on 2015/4/30
 * @created 10:49
 *
 * Copyright 2011-2015 Asura
 */
package com.tuanche.baseapi.service.impl;

import com.tuanche.framework.base.util.JsonUtil;
import com.tuanche.baseapi.service.HttpsService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * <p>远程调用</p>
 * <PRE>
 *     <BR>	修改记录
 *     <BR>-----------------------------------------------
 *     <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi  on 2015/6/3.
 * @version 1.0
 * @since 1.0
 */
@Component(value = "httpsService")
public class HttpsRequestImpl implements HttpsService, InitializingBean {

    private final static Logger logger = LoggerFactory.getLogger(HttpsRequestImpl.class);

    private final static Charset UTF8 = Charset.forName("UTF-8");

    private CloseableHttpClient httpClient;

    private RequestConfig requestConfig;


    /**
     *
     * 调用远程地址.
     *
     * @param api_url
     * @param object
     * @return
     */
    public String sendPost(String api_url, Object object){
        return sendPost(api_url, JsonUtil.Object2Json(object));
    }


    public String sendPostDes(final String api_url, final String jsonPostArgs) {
        final HttpPost httpPost = new HttpPost(api_url);
        if (logger.isDebugEnabled()) {
            logger.debug("API: " + api_url + "\nArgs: " + jsonPostArgs);
        }
        //直接post参数
        final StringEntity postEntity = new StringEntity(jsonPostArgs, UTF8);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("des", "true");
        httpPost.setEntity(postEntity);
        httpPost.setConfig(requestConfig);
        String result = null;
        try {
            final HttpResponse response = httpClient.execute(httpPost);
            final HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, UTF8);
        } catch (final IOException e) {
            logger.error("执行远程调用失败.", e);
        } finally {
            httpPost.abort();
        }
        if (logger.isDebugEnabled()) {
            logger.debug("API: " + api_url + "\nResp: " + result);
        }
        return result;
    }


    public String sendPost(final String api_url, final String jsonPostArgs) {
        final HttpPost httpPost = new HttpPost(api_url);

        if (logger.isDebugEnabled()) {
            logger.debug("API: " + api_url + "\nArgs: " + jsonPostArgs);
        }
        //直接post参数
        final StringEntity postEntity = new StringEntity(jsonPostArgs, UTF8);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(postEntity);
        httpPost.setConfig(requestConfig);
        String result = null;
        try {
            final HttpResponse response = httpClient.execute(httpPost);
            final HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, UTF8);
        } catch (final IOException e) {
            logger.error("执行远程调用失败.", e);
        } finally {
            httpPost.abort();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("API: " + api_url + "\nResp: " + result);
        }
        return result;
    }

    public String sendPost(String api_url) {
        final HttpPost httpPost = new HttpPost(api_url);

        //直接post参数
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setConfig(requestConfig);
        String result = null;
        try {
            final HttpResponse response = httpClient.execute(httpPost);
            final HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, UTF8);
        } catch (final IOException e) {
            logger.error("执行远程调用失败.", e);
        } finally {
            httpPost.abort();
        }

        if (logger.isDebugEnabled()) {
            logger.debug("API: " + api_url + "\nResp: " + result);
        }
        return result;
    }

    /**
     * 初始化 httpClient配置信息
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(3000).build();
        httpClient = HttpClients.custom().build();
    }
}