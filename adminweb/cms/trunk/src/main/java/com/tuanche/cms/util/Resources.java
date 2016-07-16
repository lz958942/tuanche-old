package com.tuanche.cms.util;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class Resources {
	private static Logger log = Logger.getLogger(Resources.class);
	/** 国际化资源 */
	public static ResourceBundle resourceBundle;
	public static ResourceBundle wf;
	public static ResourceBundle messageBundle;

	static {
		resourceBundle = ResourceBundle.getBundle("application");
		messageBundle = ResourceBundle.getBundle("message");
	}

	public static void close() {
		resourceBundle = null;
	}

	public static String myString() {
		return resourceBundle.toString();
	}

	/**
	 * 从资源文件中返回字符串 我们不希望程序崩溃，所以如果没有找到Key，就直接返回Key
	 */
	public static String getWebMessage(String key) {
		try {
			if (!messageBundle.containsKey(key)) {
				return "";
			}
			return messageBundle.getString(key);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return "";
		}
	}

	public static String getWorkflow(String bizType, String key) {
		wf = ResourceBundle.getBundle(bizType + "_wf");
		return wf.getString(key);
	}

	public static String getErrorMessage(String key) {
		try {
			if (!messageBundle.containsKey(key)) {
				return "";
			}
			return  messageBundle.getString(key);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return "";
		}
	}

	public static String getString(String key) {
		try {
			if (!resourceBundle.containsKey(key)) {
				return "";
			}
			return resourceBundle.getString(key);
		} catch (Exception e) {
			log.error(e);
			return "";
		}
	}

	public static int getConfigAsInt(String key) {
		return Integer.valueOf(getString(key));
	}

	/**
	 * 从资源文件中返回字符串 我们不希望程序崩溃，所以如果没有找到Key，就直接返回Key
	 */
	public static String getString(String key, Object[] args) {
		try {
			return MessageFormat.format(getString(key), args);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return "";
		}
	}
}

