 package com.tuanche.console.util.http;
 
 public class ProcessorConfig
 {
   public static final String CHARSET_UTF8 = "utf-8";
   public static final String CHARSET_GBK = "gbk";
   public static final String CONTENTTYPE_JAVA = "application/x-java-serialized-object";
   public static final String CONTENTTYPE_HTML = "text/html";
   public static final String CONTENTTYPE_PLAIN = "text/plain";
   public static final String METHOD_POST = "POST";
   public static final String METHOD_GET = "GET";
   public static final int DEFAULT_TIMEOUT = 3000;
   public static final String DEFAULT_USERAGENT = "com-tuanche";
   public String charset = "utf-8";
   public String method = "GET";
   public String userAgent = "com-tuanche";
   public int timeout = 3000;
   public String contentType = "text/plain";
 }