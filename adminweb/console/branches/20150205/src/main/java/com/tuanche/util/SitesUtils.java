package com.tuanche.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.tuanche.bean.che.BrandDomain;
import com.tuanche.console.util.GlobalConstants;

public class SitesUtils {
public static Map<Integer, BrandDomain> brandmap=new ConcurrentHashMap<Integer, BrandDomain>();
	public  static  String getDateToString(){
		String returnDate="";
		Date date = new Date(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		returnDate=df.format(date);
		return returnDate;
	}
	public static String getSeriesName(int ppid) {
		if(ppid==0||!brandmap.containsKey(ppid)){
			return "";
		}
		if(brandmap.get(ppid)==null){
		return GlobalConstants.brandMap.get(ppid).getName();
		}
		return brandmap.get(ppid).getName();
	}
}
