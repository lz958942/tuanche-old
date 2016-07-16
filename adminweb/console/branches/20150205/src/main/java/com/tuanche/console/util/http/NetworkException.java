 package com.tuanche.console.util.http;
 
 public class NetworkException extends Exception
 {
   private static final long serialVersionUID = 1L;
   private String message;
 
   public NetworkException(String message)
   {
     this.message = message;
   }
 
   public NetworkException(Exception e) {
     super(e);
   }
 
   public NetworkException(String message, Exception e) {
     super(e);
     this.message = message;
   }
 
   public String getMessage()
   {
     if (this.message != null) {
       return this.message;
     }
     return super.getMessage();
   }
 }