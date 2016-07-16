package com.tuanche.console.util;

import java.util.Map;
import java.util.TreeMap;

public class CommonData {
	public static Map<String, String> empStatusMap = new TreeMap<String, String>();
	// Data状态
	public static Map<Integer, String> dataStatusMap = new TreeMap<Integer, String>();
	public static Map<String, String> bizCodeMap = new TreeMap<String, String>();
	public static Map<Integer, String> deptStatusMap = new TreeMap<Integer, String>();
	public static Map<Integer, String> booleanMap = new TreeMap<Integer, String>();
	static {
		// 员工状态
		empStatusMap.put("1", "岗前培训");
		empStatusMap.put("2", "试用");
		empStatusMap.put("3", "在职");
		empStatusMap.put("4", "在岗");
		empStatusMap.put("5", "离职");
		empStatusMap.put("6", "退休");
		empStatusMap.put("7", "离岗");
		empStatusMap.put("8", "其它");
	
	
		bizCodeMap.put("SYS", "系统管理");
		bizCodeMap.put("HR", "人事系统");
		bizCodeMap.put("CRM", "销售系统");
		bizCodeMap.put("FM", "财务系统");
		
		dataStatusMap.put(1, "正常");
		dataStatusMap.put(-1, "删除");
		dataStatusMap.put(-2, "锁定");
		
		deptStatusMap.put(1, "正常");
		deptStatusMap.put(-1, "删除");
		
		booleanMap.put(1, "是");
		booleanMap.put(-1, "否");

		}
}
