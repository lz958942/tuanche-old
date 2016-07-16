package com.tuanche.cms.util;

import com.tuanche.commons.util.StringUtils;

public class NameUtil {
	public static String getChannelName(Integer id){
		if(id<0){
			return "";
		}
		if(GlobalConstants.channelMap.containsKey(id)){
			return GlobalConstants.channelMap.get(id);
		}
		return "未知";
	}
	public static String getAdTypeName(Integer id){
		if(id<0){
			return "";
		}
		if(GlobalConstants.adTypeMap.containsKey(id)){
			return GlobalConstants.adTypeMap.get(id);
		}
		return "未知";
	}
	public static String getCityName(Integer id){
		if(id<0){
			return "";
		}
		if(id==0){
			return "全国";
		}
		if(GlobalConstants.cityMap.containsKey(id)){
			return GlobalConstants.cityMap.get(id);
		}
		return "未知";
	}
	
	public static boolean isNull(String str){
		 return StringUtils.isEmpty(str);
	}
	public static boolean isEqual(Integer v1,Integer v2){
		int vv1=v1;
		int vv2=v2;
		return vv1==vv2;
	}
}
