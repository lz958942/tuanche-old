 package com.tuanche.console.util.http;

import java.util.Map;

import com.tuanche.console.util.http.NetworkException;

public abstract interface HttpProcessor
{
  public abstract String post(String paramString1, String paramString2)
    throws NetworkException;

  public abstract String post(String paramString1, String paramString2, ProcessorConfig paramProcessorConfig)
    throws NetworkException;

  public abstract String get(String paramString)
    throws NetworkException;

  public abstract String get(String paramString, ProcessorConfig paramProcessorConfig)
    throws NetworkException;

  public abstract String get(String paramString, Map<String, String> paramMap)
    throws NetworkException;

  public abstract ProcessorConfig getDefaultProcessorConfig();

  public abstract void shutdown();

  public abstract void closeIdleConnection();
}