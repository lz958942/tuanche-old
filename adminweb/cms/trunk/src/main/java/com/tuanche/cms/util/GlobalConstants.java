package com.tuanche.cms.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import com.tuanche.cms.bean.Brand;
import com.tuanche.cms.bean.City;
import com.tuanche.cms.bean.Style;

public class GlobalConstants {
	public static final String CHARSET = "UTF-8";
	public static final String USER_HOME = "/";
	
	
	public static final String SESSION_EMP = "emp_session";
	public static final String SESSION_AUTH_DATA = "auth_data"; //鐢ㄦ埛鎷ユ湁鐨勬搷浣滃煄甯傦紝鍝佺墝鐨勬潈闄�	public static final  String MESS_KEY= "mess_key";
	public static final  String ERROR_KEY= "error_key";
	
	public static final String EXCLUDE_URL_INIT="exclude";
	public static final String EXCLUDE_DIRECTORY_REGEX="^/css/|^/images/|^/js/|^/findpwd/|^/login|^/dwz|^/ftl/static|^/free/timmingHandle";
	public static final String EXCLUDE_ONLY_LOGIN="^/inc/|^/common/|^/json/|^/upload/";
	public static final String LOGIN_PAGE = "/index.jsp";
	public static final String NO_AUTH_PAGE = "/noAuth.html";
	public static final String MAIN_PAGE = "/main";
	public static final String ERROR_PAGE = "/inc/error.jsp";
	
	public static final  String ROLE_SUPPER_ID=",1,";
	
	public static String HOST_PIC =Resources.getString("pic.url");
	public static final  String ROLE_URL_MAP="roleUrlMap";
	public static final  String ROLE_FUN_ID="roleFuncId";
	public static final  String ROLE_MAP="roleMap";
	public static final  String FUNC_MAP="funcMap";
	public static final  String EMPLOYEE_MAP="employeeMap";
	public static final  String MAKE_AUTH="makeAuth";
	
	public static final int BOOLEAN_Y=1;  //姝ｅ父
	public static final int BOOLEAN_N=-1;//閿佸畾
	public static final String BOOLEAN_STRY="Y";  //姝ｅ父
	public static final String BOOLEAN_STRN="N";//閿佸畾
	
	public static Map<Integer,String>  employeeMap=new ConcurrentHashMap<Integer,String>();
	public static Map<Integer,String>  delEmployeeMap=new ConcurrentHashMap<Integer,String>();
	public static Map<Integer,String> channelMap=new  HashMap<Integer,String>();
	public static Map<Integer,String> adTypeMap=new HashMap<Integer,String>();
	public static Map<Integer,String>  cityMap=new HashMap<Integer,String>();
	public static Map<Integer,City>  cityAllMap=new HashMap<Integer,City>();
	public static Map<Integer,String>  cityMapPY=new LinkedHashMap<Integer,String>();//城市名带  首字母，排序
	
	public static Map<String,Integer>  cityCodeToIdMap=new HashMap<String,Integer>();
	
	public static Map<Integer,String> brandMap=new HashMap<Integer, String>();
	public static Map<Integer,Integer> brandSeriesById=new HashMap<Integer, Integer>();
	public static Map<Integer,String>   styleMap=new HashMap<Integer,String>();
	public static Map<Integer,List<Style>> styles=new HashMap<Integer,List<Style>>();
	public static Map<Integer,List<Style>> cars=new HashMap<Integer,List<Style>>();
	public static List<Brand> brands=new ArrayList<Brand>();
	
	public static LinkedHashMap<Integer,String>  carSeries=new LinkedHashMap<Integer,String>();	
	//资讯  板块内容不够  不抓取
	public static Map<String,Integer> no_supply_zixun = new HashMap<String,Integer>();
	//城市   板块内容不够  不抓取
	public static Map<String,Integer> no_supply_city = new HashMap<String, Integer>();
	public static Map<String, String> empStatusMap = new TreeMap<String, String>();
	// Data状态
	public static Map<Integer, String> dataStatusMap = new TreeMap<Integer, String>();
	public static Map<String, String> bizCodeMap = new TreeMap<String, String>();
	public static Map<Integer, String> deptStatusMap = new TreeMap<Integer, String>();
	public static Map<Integer, String> booleanMap = new TreeMap<Integer, String>();
	static{
		no_supply_zixun.put("big_image_switch", 1);//焦点图
		no_supply_zixun.put("te_shu_tem1", 0);//特殊1
		no_supply_zixun.put("te_shu_tem2", 0);//特殊2
		no_supply_zixun.put("te_shu_tem3", 0);//特殊3
		no_supply_zixun.put("te_shu_tem4", 0);//特殊4
		no_supply_zixun.put("te_shu_tem5", 0);//特殊5
		no_supply_zixun.put("car_show", 0);//车展欣赏
		
		
		channelMap.put(1001, "城市首页");
		channelMap.put(1002, "车型团购");
		channelMap.put(1003, "品牌团购");
		channelMap.put(1004, "论坛");
		channelMap.put(1005, "团购底层页");
		channelMap.put(1006, "城市资讯首页");
		channelMap.put(1007, "tuanche.com资讯首页");
		channelMap.put(1008, "团车会");
		channelMap.put(1009, "资讯文章页");
		channelMap.put(1010, "wap端");
		
		
		adTypeMap.put(1, "文字链接");
		adTypeMap.put(2, "图片文字");
		
		//标识系别  德系1             日系2           美 系3             欧系4            韩系5                    自主7                法系6
		//put顺序  在cms城市页面中  ‘按品牌车型团购’  有用到，不可改
		carSeries.put(1, "德系");
		carSeries.put(2, "日韩系");//日系2   韩系5
		carSeries.put(7,"国产");
		carSeries.put(3, "美系");
		carSeries.put(4, "欧系");//欧系4   
		
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
	public static int getCityId(String cityCode){
		if(GlobalConstants.cityCodeToIdMap.containsKey(cityCode)){
			return GlobalConstants.cityCodeToIdMap.get(cityCode);
		}
		return 0;
	}
	
}