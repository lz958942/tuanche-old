package com.tuanche.smc.common;

import com.tuanche.commons.util.Resources;


public class Globals {
    
	public static final long oneDayInMilliSeconds = 86400000;
	
	public static final int TOPIC_ALL_TODAY = 1;
	public static final int TOPIC_ALL_YESTERDAY = 2;
	public static final int TOPIC_ALL_MY_TODAY = 3;
	public static final int TOPIC_ALL_MY_YESTERDAY = 4;
	public static final int TOPIC_ALL_MY = 5;
	
	public static boolean isSecond(String cityId){
    	 return Resources.getString("second.city")
    			 .indexOf(","+cityId+",")>=0;
     }
	public static boolean cityCodeTj(String cityId){
   	 return Resources.getString("zt.cityCode")
   			 .indexOf(","+cityId+",")>=0;
    }
}
