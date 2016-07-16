package com.tuanche.console.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tuanche.bean.che.SysConfig;
import com.tuanche.bean.che.UsedCarstyle;
import com.tuanche.console.bean.City;
import com.tuanche.smc.common.Common;
import com.tuanche.smc.domain.base.Brand;
import com.tuanche.smc.domain.base.NewsClassify;
import com.tuanche.smc.domain.base.Style;
import com.tuanche.util.SitesUtils;

public class NameUtil {
	public static String getLevel(Integer level){
		return GlobalConstants.levelMap.get(level);
	}
	public static String getCity(Integer cityId){
	    if(cityId==-1){
	        return "全国";
	    }
		return GlobalConstants.cityMap.get(cityId);
	}
	public static String getCityPY(Integer cityId){
		if(cityId==-1){
			return "www";
		}
		City city = GlobalConstants.cityMaps.get(cityId);
		if(city == null){
			return "";
		}
		return city.getPy();
	}
	public static String getType(Integer type){
		return GlobalConstants.typeMap.get(type);
	}
	
	public static String getOnline(Integer online){
		return GlobalConstants.onlineStatusMap.get(online);
	}
	public static String getIncluded(Integer included){
		return GlobalConstants.includedStatusMap.get(included);
	}
	public static String getWord(Integer id){
		return GlobalConstants.wordMap.get(id);
	}
	
	
	public static String getZXClass(Integer id){
	    NewsClassify newsClassify = Common.newsClassifyMap.get(id+"");
	    if(id==null||id<=0||newsClassify==null){
	        return "资讯分类";
	    }
    return newsClassify.getName();
	}
	
	public static String getStyleInfo(Integer id){
	    Style style = Common.styleMap.get(id);
	    Brand brand = Common.brandMap.get(id+"");
	    if(id==null){
            return null;
        }
	    if(id>Common.styleIdInc){
	        if(style==null){
	            return null;
	        }
            return style.getName();
	    }else {
	        if(brand==null){
                return null;
            }
            return brand.getName();
        }
	}
	
	
	/*public static String getStyleInfoBrand(String id){
		String[] idd=id.split("-");
		if(idd.length>0&&"".equals(idd)){
	    Style style = Common.styleMap.get(idd[1]);
	    Brand brand = Common.brandMap.get(idd[0]);
	   
	    if(idd[0]!=null&&"".equals(idd[0])){
	        if(style==null){
	            return null;
	        }
            return style.getName();
	    }else {
	        if(brand==null){
                return null;
            }
            return brand.getName();
        }
		}
		 
	    if(id==null){
            return null;
        }
		return null;
	}*/
	public static String getquestionKind(Integer id){
		if(id>0){
			return GlobalConstants.questionKindmap.get(id);
		}else{
			return "暂无分类";
		}
	}
	public static String getCarstyleName(Integer id){
		Style style = Common.styleMap.get(id+"");
		if(style==null){
			return "未指定";
		}
		return style.getName();
	}
	
	public static String getStyleInfo(String id){
		//System.out.println("id="+id);
	    if(StringUtils.isEmpty(id)){
	        return null;
	    }
	    if(id.contains("-")){
	    	System.out.println("id="+id.split("-")[1].replace(",", ""));
	    	 Style style = Common.styleMap.get(id.split("-")[1].replace(",", ""));
	 	    if(style!=null){
	 	    	return style.getName();
	 	    }
	    }else{
	    	//System.out.println("id="+id);
	    	Brand brand = Common.brandMap.get(id);
	           if(brand!=null){
	               return  brand.getName();
	           }
	    }
	   
	    return "";
	  /* String[] split2 = id.split("-");
	   String qwer="";
	 for (String string : split2) {
		qwer+=string+",";
	};
	System.out.println(qwer);
	   String[] split=qwer.split(",");
	   String info = "";
	   for(String s : split){
		   if(s!=null&&!"".equals(s)){
	       int parseInt = Integer.parseInt(s);
		  
	       if(parseInt!=0 ){
	           Style style = Common.styleMap.get(parseInt);
	           if(style!=null){
	               info += style.getName()+",";
	           }else{
		           Brand brand = Common.brandMap.get(s);
		           if(brand!=null){
		               info += brand.getName()+",";
		           }
		       }
	       }
		   }
	   }
	   if(!"".equals(info)){
	       return info.substring(0,info.length()-1);
	   }*/
	   //return null;
	}
	
	
	
	/*public static String getStyleInfo(String id){
	    if(StringUtils.isEmpty(id)){
	        return null;
	    }
	   String[] split = id.split(",");
	   String info = "";
	   for(String s : split){
	       int parseInt = Integer.parseInt(s);
	       if(parseInt>Common.styleIdInc ){
	           Style style = Common.styleMap.get(parseInt-Common.styleIdInc+"");
	           if(style!=null){
	               info += style.getName()+",";
	           }
	       }else{
	           Brand brand = Common.brandMap.get(s);
	           if(brand!=null){
	               info += brand.getName()+",";
	           }
	       }
	   }
	   if(!"".equals(info)){
	       return info.substring(0,info.length()-1);
	   }
	   return null;
	}*/
	
	public static String getallCity(Integer cityId){
        if(cityId<=0){
            return "全国";
        }
        return Common.cityDistMap.get(cityId+"").getLocalname();
    }
	//品牌
	public static String getallBrand(Integer brandId){
        if(brandId<=0){
            return "全部品牌";
        }
        Brand  b=Common.brandMap.get(brandId+"");
        if(b==null){
        	return "全部品牌";
        }
        return b.getName();
    }
	//车型
	public static String getallStyle(Integer styleId){
        if(styleId<=0){
            return "全部车型";
        }
        Style style=Common.styleMap.get(styleId+"");
        if(style==null){
        	return "全部车型";
        }
        return style.getName();
    }
	//页面类型
	public static String getPageType(Integer pagetype){
        if(pagetype==200){
            return "品牌底层页";
        }
        if(pagetype==300){
        	return "车型底层页";
        }else {
        	return "全部页面";
        }
    }
	public static String getEditName(Integer id){
	   return GlobalConstants.employeeMap.get(id);
	}
	
	public static String getBusinessName(String bizCode,Map<String,String> businessMap){
		if(StringUtils.isEmpty(bizCode) || businessMap==null || businessMap.size()==0) return "";
		StringBuffer sb=new StringBuffer();
		for(String s:bizCode.split("")){
			if(businessMap.containsKey(s)){
				sb.append(businessMap.get(s));
				sb.append(",");
			}
		}
		return sb.substring(0,sb.length()-1).toString();
	}
	
	public static boolean inString(String s,String des){
		return des.indexOf(s)>=0;
	}
	
	public static String getDistrictName(Integer districtId){
		if(districtId==0 || !GlobalConstants.districtIdCitycodeMap.containsKey(districtId)) return "";
		return GlobalConstants.districtMap.get(GlobalConstants.districtIdCitycodeMap.get(districtId)).getName();
		
	}
	public static String getBrandName(Integer brandId){
		//if(brandId==0 || !GlobalConstants.brandMap.containsKey(brandId)) return "";
		if(brandId==0) return "";
		if(!GlobalConstants.brandMap.containsKey(brandId)){
			if(SitesUtils.brandmap.containsKey(brandId)){
				return	SitesUtils.brandmap.get(brandId).getName();
			}
		    return "";
		}
		return GlobalConstants.brandMap.get(brandId).getName();
	}
	
	public static String getUsedCarstyle(Integer id){
		UsedCarstyle usedCarstyle = Common.usedCstyleMap.get(id);
		if(usedCarstyle == null){
			return null;
		}
		return usedCarstyle.getCname();
	}
	
	//品牌管理查询品牌名称返回
	public static String getSeriesName(int ppid) {
		if(ppid==0||!SitesUtils.brandmap.containsKey(ppid)){
			return "";
		}
		return SitesUtils.brandmap.get(ppid).getName();
	}
	
	public static String getUrl(Map<String,String> infoMap,Long id){
		if(StringUtils.isEmpty(infoMap.get("next"))){
			return "javascript:void(0);";
		}
		StringBuffer sb=new StringBuffer();
		sb.append("/company/campaign?type=").append(infoMap.get("next")).append("&");
		for(String str:infoMap.get("params").split(",")){
			sb.append(str).append("=").append(StringUtils.isEmpty(infoMap.get(str))?"":infoMap.get(str)).append("&");
		}
		return sb.substring(0,sb.length()-1).toString()+id;
	}
	
	/**
	 * @return
	 * @author liuhg
	 * @Description 根据装饰code得到name
	 */
	public static String getKindName(String code){
		for(SysConfig s:GlobalConstants.kindList){
			GlobalConstants.KindMap.put(s.getCode(), s.getName());
		}
		return GlobalConstants.KindMap.get(code);
	}
	public static String getPlateName(String code){
		for(SysConfig s:GlobalConstants.plateList){
			GlobalConstants.plateMap.put(s.getCode(), s.getName());
		}
		return GlobalConstants.plateMap.get(code);
	}
	public static String getcarStyleNameById(Integer id) {
		if(id==null || id==0 ||!GlobalConstants.getCarStyleNameByCarId.containsKey(id)){
			return "无";
		}
		return GlobalConstants.getCarStyleNameByCarId.get(id).getStyle();
	}
	public static String getPropertyOrClassByCode(Integer code,Integer status) {
		if(code==null || status==null){
			return "无";
		}
		if(status==1){
			//属性  propertyid
			if(GlobalConstants.getPicProperty.containsKey(code)){
				return GlobalConstants.getPicProperty.get(code).getName();
			}
		}else{
			//分类  classid
			if(GlobalConstants.getPiCclass.containsKey(code)){
				return GlobalConstants.getPiCclass.get(code).getName();
			}
		}
		return "无";
	}
	public static String getChannelName(Integer id) {
		if(GlobalConstants.getChannelName.containsKey(id)){
			return GlobalConstants.getChannelName.get(id).getName();
		}
		return "无";
	}
	/**
	 * 礼品后台
	 * */
	public static String getGiftById(Integer id,Integer type) {
		//type==1 查兑换方式  type==2礼品来源 ==3 分类
		if(id==null && type==null){
			return "无";
		}
		if(type==1){
			if(GlobalConstants.sysGiftExchangeType.containsKey(id)){
				return	GlobalConstants.sysGiftExchangeType.get(id).getName();
			}else{
				return "无";
			}
		}else if(type==2){
			if(GlobalConstants.gift_source.containsKey(id)){
				return GlobalConstants.gift_source.get(id).getName();
			}else{
				return "无";
			}
		}else if(type==3){
			if(GlobalConstants.gifitClass.containsKey(id)){
				return	GlobalConstants.gifitClass.get(id).getName();
			}else{
				return "无";
			}
		}
		return "无";
	}
	public static String getTopicId(Integer tid,Integer sta) {
		if(tid==null || sta==null){
			return "无";
		}
		if(sta==1){
			if(GlobalConstants.gambit.containsKey(tid)){
				return GlobalConstants.gambit.get(tid).getTitle();
			}else{
				return "无";
			}
		}
		return "无";
	}
	public static String getUser(Integer id) {
		if(id==null){
			return "无";
		}
		if(GlobalConstants.users.containsKey(id)){
			return GlobalConstants.users.get(id).getName();
		}
		return "无";
	}
}
