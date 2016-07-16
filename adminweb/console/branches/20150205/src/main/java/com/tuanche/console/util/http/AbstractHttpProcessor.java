 package com.tuanche.console.util.http;

import java.util.Map;

import org.apache.log4j.Logger;

import com.tuanche.console.util.StringUtils;
import com.tuanche.console.util.http.NetworkException;
 
 
 public abstract class AbstractHttpProcessor
   implements HttpProcessor
 {
   protected static Logger logger = Logger.getLogger(AbstractHttpProcessor.class);
   private static ProcessorConfig DEFAULT_CONFIG = new ProcessorConfig();
 
   public String get(String url) throws NetworkException
   {
     return get(url, getDefaultProcessorConfig());
   }
 
   public String get(String reqURL, Map<String, String> args) throws NetworkException
   {
     StringBuilder url = new StringBuilder(reqURL);
     if (args != null) {
       url.append("?");
       for (Map.Entry<String, String> entry : args.entrySet()) {
         url.append(new StringBuilder().append(StringUtils.encodeUTF8((String)entry.getKey())).append("=").append(StringUtils.encodeUTF8((String)entry.getValue())).toString());
         url.append("&");
       }
       url = url.delete(url.length() - 1, url.length());
     }
     return get(url.toString());
   }
 
   public String post(String url, String content) throws NetworkException
   {
     return post(url, content, getDefaultProcessorConfig());
   }
 
   public ProcessorConfig getDefaultProcessorConfig()
   {
     return DEFAULT_CONFIG;
   }
 }