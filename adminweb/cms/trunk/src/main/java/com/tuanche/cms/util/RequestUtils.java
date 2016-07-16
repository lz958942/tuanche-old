package com.tuanche.cms.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.tuanche.commons.util.StringUtils;

public class RequestUtils {
	public static SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

	public static int getInt(HttpServletRequest request, String str) {
		String value=RequestUtils.getString(request, str);
		if (StringUtils.isEmpty(str)) {
			return 0;
		}
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	

	public static int getInt(HttpServletRequest request, String str, int defaultInt) {
		String value=RequestUtils.getString(request, str);
		if (StringUtils.isEmpty(str)) {
			return defaultInt;
		}
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultInt;
		}
	}



	public static Integer getInteger(HttpServletRequest request, String str) {
		String tempStr = request.getParameter(str);
		if (tempStr == null || "".equals(tempStr)) {
			return null;
		} else {
			return Integer.parseInt(tempStr);
		}
	}

	public static BigDecimal getBigDecimal(HttpServletRequest request, String str) {
		String tempStr = request.getParameter(str);
		BigDecimal tempBigDecimal = null;
		if (tempStr == null || "".equals(tempStr)) {
			tempStr = "0.00";
		}
		try {
			tempBigDecimal = new BigDecimal(tempStr).setScale(2, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			tempBigDecimal = new BigDecimal(0.00);
		}
		return tempBigDecimal;
	}

	public static BigDecimal[] getBigDecimals(HttpServletRequest request, String str) {
		String[] strArr = request.getParameterValues(str);
		int len = strArr.length;
		BigDecimal[] tempBigDecimal = new BigDecimal[len];
		for (int j = 0; j < len; j++) {
			if (strArr[j] == null || "".equals(strArr[j])) {
				strArr[j] = "0.00";
			}
			try {
				tempBigDecimal[j] = new BigDecimal(strArr[j]).setScale(2, BigDecimal.ROUND_HALF_UP);
			} catch (Exception e) {
				tempBigDecimal[j] = new BigDecimal(0.00);
			}
		}
		return tempBigDecimal;

	}

	public static String getString(HttpServletRequest request, String str) {
		String tempStr  = request.getParameter(str);
		if(!StringUtils.isEmpty(tempStr)){
			tempStr=tempStr.trim();
		}
		if(StringUtils.isEmpty(tempStr)){
			return null;
		}
		return tempStr;
	}

	public static String getStrings(HttpServletRequest request, String str) {
		String tempStr = "";
		String[] temps = request.getParameterValues(str);
		if (temps == null) {
			return null;
		}
		for (String temp : temps) {
			tempStr += temp + ",";
		}
		return tempStr;
	}

	public static Date getSQLDate(HttpServletRequest request, String str) {
		String tempStr = request.getParameter(str);
		Date tempDate = null;
		if (tempStr != null) {
			try {
				tempDate = Date.valueOf(tempStr);
			} catch (Exception e) {
				tempDate = null;
			}
		}

		return tempDate;
	}

	public static java.util.Date getUtilDate(HttpServletRequest request, String str) {
		String tempStr = request.getParameter(str);
		java.util.Date tempDate = null;
		if (tempStr != null) {
			try {
				tempDate = sd.parse(tempStr);
			} catch (Exception e) {
				tempDate = null;
			}
		}

		return tempDate;
	}

	public static java.util.Date getUtilDateTime(HttpServletRequest request, String str) {
		String tempStr = request.getParameter(str);
		java.util.Date tempDate = null;
		if (tempStr != null) {
			try {
				tempDate = sdt.parse(tempStr);
			} catch (Exception e) {
				tempDate = null;
			}
		}

		return tempDate;
	}

	public static Date[] getSqlDate(HttpServletRequest request, String str) {
		String[] strArr = request.getParameterValues(str);
		int length = strArr.length;
		Date[] dateArr = new Date[length];
		for (int i = 0; i < length; i++) {
			if (strArr[i] != null) {
				try {
					dateArr[i] = Date.valueOf(strArr[i]);
				} catch (Exception e) {
					dateArr[i] = null;
				}
			}
		}
		return dateArr;
	}
	public static Object getBean(Class clz, HttpServletRequest request) {
		Object obj = null;
		try {
			java.lang.reflect.Field[] field = clz.getDeclaredFields();
			obj = clz.newInstance();
			PropertyDescriptor pd2 = null;
			String value = null;
			Method meod = null;
			Object arg = null;
			int length = field.length;
			for (int i = 0; i < length; i++) {
				try {
					field[i].setAccessible(true);
					String type = field[i].getType().getSimpleName();
					String str = field[i].getName();
					value = request.getParameter(str);
					if ("".equals(value) || value == null) {
						Object value2 = request.getAttribute(str);
						if (value2 != null) {
							value = value2.toString();
						}
					}

					if (value != null && value.length() > 0 && (!("null".equalsIgnoreCase(value)))) {
						value = value.trim();
						pd2 = new PropertyDescriptor(str, clz);
						meod = pd2.getWriteMethod();
						if (type.equalsIgnoreCase("String")) {
							arg = new String(value);// new
													// String(value.getBytes("ISO8859-1"),"utf-8");
						} else if (type.equalsIgnoreCase("Integer")) {
							arg = Integer.valueOf(value);
						} else if (type.equalsIgnoreCase("Double")) {
							arg = Double.valueOf(value);
						} else if (type.equalsIgnoreCase("Float")) {
							arg = Float.valueOf(value);
						} else if (type.equalsIgnoreCase("int")) {
							arg = Integer.valueOf(value);
						} else if (type.equalsIgnoreCase("Date")) {
							arg = Date.valueOf(value);
						} else if (type.equalsIgnoreCase("bigDecimal")) {
							arg = new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);
						} else if (type.equalsIgnoreCase("BigInteger")) {
							arg = new BigInteger(value);
						}
						meod.invoke(obj, arg);
					}
				} catch (Exception e) {
					System.out.println("e" + e.toString());
				}
			}
		} catch (Exception e) {
			System.out.println("e2" + e.toString());
		}
		return obj;
	}
}