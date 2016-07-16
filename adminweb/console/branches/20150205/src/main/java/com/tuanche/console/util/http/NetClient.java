 package com.tuanche.console.util.http;

 
 
 public class NetClient
 {
   private static HttpProcessor httpProcessor;
   private static int maxConnecton = 1000;
   private static int maxRoute = 100;
   private static int closeIdleTimeSec = 120;
 
   protected static void init(int maxConnection, int maxRoute, int closeIdleTimeSec, ProcessorConfig config)
   {
     HttpProcessor processor = new HttpClientProcessor(maxConnection, maxRoute, closeIdleTimeSec, config);
     httpProcessor = processor;
   }
 
   public static String getHttpResponse(String url)
     throws Exception
   {
     return httpProcessor.get(url);
   }
 
   public static void shutdown()
   {
     httpProcessor.shutdown();
   }
 
   static
   {
     ProcessorConfig config = new ProcessorConfig();
     config.timeout = 3000;
     init(maxConnecton, maxRoute, closeIdleTimeSec, config);
   }
 }