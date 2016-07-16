package com.tuanche.console.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.tuanche.bean.admin.QuestionKind;
import com.tuanche.bean.admin.Topic;
import com.tuanche.bean.che.Brand;
import com.tuanche.bean.che.CarStyle;
import com.tuanche.bean.che.CarstyleDomain;
import com.tuanche.bean.che.CarstyleGroupbuy;
import com.tuanche.bean.che.SysConfig;
import com.tuanche.bean.che.Users;
import com.tuanche.bean.sem.Account;
import com.tuanche.console.bean.City;
import com.tuanche.console.bean.Keyword;

public class GlobalConstants {
	public static final String CHARSET = "UTF-8";
	public static final String USER_HOME = "/";
	
	
	public static final String SESSION_EMP = "emp_session";
	public static final String SESSION_AUTH_DATA = "auth_data"; //用户拥有的操作城市，品牌的权限
	public static final  String MESS_KEY= "mess_key";
	public static final  String ERROR_KEY= "error_key";
	public static final  int PAGE_SIZE=10;
	
	public static final String EXCLUDE_DIRECTORY_REGEX="^/css/|^/images/|^/js/|^/findpwd/|^/ue/|^/local/|^/login|^/specialSubject/createFMAll|^/zt-mc/|^/pic_tmp/";//不走权限和登录
	public static final String EXCLUDE_ONLY_LOGIN="^/inc/|^/common/|^/json/|^/upload/|^/main";//走登录不走权限
	public static final String LOGIN_PAGE = "/index.jsp";
	public static final String NO_AUTH_PAGE = "/noAuth.html";
	public static final String MAIN_PAGE = "/main";
	public static final String ERROR_PAGE = "/inc/error.jsp";
	public static final String WEB_HOST=".tuanche.com";
	
	public static final  String ROLE_SUPPER_ID=",1,";
	public static final  String ROLE_SALE_ID=",2,";
	
	
	public static final  String ROLE_URL_MAP="roleUrlMap";
	public static final  String ROLE_FUN_ID="roleFuncId";
	public static final  String ROLE_MAP="roleMap";
	public static final  String FUNC_MAP="funcMap";
	public static final  String EMPLOYEE_MAP="employeeMap";
	public static final  String MAKE_AUTH="makeAuth";
	public static final  String WORD_MAP="wordMap";
	public static final  String DISTRICT_MAP="districtMap";
	public static final  String BRAND_MAP="brandMap";
	public static final	 String CARSTYLE_MAP="carstyleMap";
	public static final  String DISTRICT_CITYCODE_MAP="districtCitycodeMap";
	
	public static final int BOOLEAN_Y=1;  //正常
	public static final int BOOLEAN_N=-1;//锁定
	public static final String BOOLEAN_STRY="Y";  //正常
	public static final String BOOLEAN_STRN="N";//锁定
	
	public static Map<Integer,String>  employeeMap=new ConcurrentHashMap<Integer,String>();
	public static Map<Integer,String>  delEmployeeMap=new ConcurrentHashMap<Integer,String>();
	public static Map<Integer,String> editEmployeeMap=new ConcurrentHashMap<Integer,String>();
	public static Map<String,String>  businessType=new ConcurrentHashMap<String, String>();
	public static Map<Integer,String> districtIdCitycodeMap=new ConcurrentHashMap<Integer, String>();
	public static Map<String,Map<String,String>> accountPageMap=new ConcurrentHashMap<String, Map<String,String>>();
	
	public static List<QuestionKind> questionKindList=new ArrayList<QuestionKind>();
	public static List<City> citys=new ArrayList<City>();
	public static List<City> all_citys=new ArrayList<City>();
	public static List<Keyword> keywords=new ArrayList<Keyword>();
	public static Map<Integer,String> questionKindmap=new HashMap<Integer, String>();
	public static Map<Integer, QuestionKind> kindsmap=new HashMap<Integer, QuestionKind>();//
	public static Map<Integer,List<String>> levelListMap=new HashMap<Integer,List<String>>();
	public static Map<Integer,String>  cityMap=new HashMap<Integer,String>();
	public static Map<Integer,City>  cityMaps=new HashMap<Integer,City>();
	public static Map<Integer,Brand>  brandMap=new LinkedHashMap<Integer, Brand>();
	public static Map<String,com.tuanche.bean.che.City>  districtMap=new LinkedHashMap<String, com.tuanche.bean.che.City>();
	public static Map<Integer,String>  levelMap=new HashMap<Integer,String>();
	public static Map<Integer,String>  onlineStatusMap=new HashMap<Integer,String>();
	public static Map<Integer,String>  includedStatusMap=new HashMap<Integer,String>();
	public static Map<Integer,String>  typeMap=new HashMap<Integer,String>();
	public static Map<Integer,String>  wordMap=new HashMap<Integer,String>();
	public static Map<Integer,Map<Integer,String>> carstyleMap=new ConcurrentHashMap<Integer, Map<Integer,String>>();
	public static Map<Integer,Map<Integer,String>> carstyleOKMap=new ConcurrentHashMap<Integer, Map<Integer,String>>();
	
	public static Map<String,String> campaignPageMap=new ConcurrentHashMap<String, String>();
	public static Map<String,String> groupPageMap=new ConcurrentHashMap<String, String>();
	public static Map<String,String> keywordPageMap=new ConcurrentHashMap<String, String>();
	
	public static final Map<Integer,String> buywayMap=new LinkedHashMap<Integer, String>();
	public static final Map<Integer,String> getcartimeMap=new LinkedHashMap<Integer, String>();
	public static final Map<Integer,String> oldApplyStateMap=new LinkedHashMap<Integer, String>();
	public static final Map<Integer,String> sidSiteMap=new ConcurrentHashMap<Integer, String>();
	public static final Map<Integer,String> applyTypeMap=new ConcurrentHashMap<Integer, String>();
	public static final Map<Integer,String> positionTypeMap=new ConcurrentHashMap<Integer, String>();
	
	//关键词excel模板导入
	public static List<Brand> brandList=new ArrayList<Brand>();
	public static List<CarStyle> carStyleList=new ArrayList<CarStyle>();
	public static  Map<Object,Object> brands=new HashMap<Object,Object>();
	public static  Map<Object,Object> carStyles=new HashMap<Object,Object>();
	
	public static Map<String,String> KindMap=new HashMap<String,String>();
	public static List<SysConfig> kindList=new ArrayList<SysConfig>();
	
	public static Map<String,String> plateMap=new HashMap<String,String>();
	public static List<SysConfig> plateList=new ArrayList<SysConfig>();
	public static Map<String,Account> accountCodeMaps=new ConcurrentHashMap<String,Account>();
	public static Map<String,City>  all_cityNameMaps=new HashMap<String,City>();
	public static Map<String,String> accountCodeMap_MENG = new HashMap<String, String>();//上传花费时 要算在一起的报名   账户
	public static Map<String,String> accountTmpMap = new HashMap<String, String>();//上传花费时   一起买车等 按 站点id （或和 资源id） 算报名数
	public static Map<Integer,CarstyleDomain> getCarStyleNameByCarId=new HashMap<Integer,CarstyleDomain>();
	public static Map<Integer,SysConfig> getPicProperty=new HashMap<Integer,SysConfig>();
	public static Map<Integer,SysConfig> getPiCclass=new HashMap<Integer,SysConfig>();
	public static Map<Integer,SysConfig> getChannelName=new HashMap<Integer,SysConfig>();
	public static Map<Integer, CarstyleDomain> getstyleNameByCid=new HashMap<Integer, CarstyleDomain>();
	public static Map<Integer,SysConfig>  sysGiftExchangeType=new ConcurrentHashMap<Integer,SysConfig>();
	public static Map<Integer,SysConfig> gifitClass=new ConcurrentHashMap<Integer,SysConfig>();
	public static Map<Integer,SysConfig> gift_source=new ConcurrentHashMap<Integer,SysConfig>();
	//public static Map<Integer,SysConfig>  sysGiftExchangeType=new ConcurrentHashMap<Integer,SysConfig>();
	//所有话题Map
	public static Map<Integer,Topic> gambit=new ConcurrentHashMap<Integer,Topic>();
	public static Map<Integer,Users> users=new ConcurrentHashMap<Integer,Users>();
	
	static{
		accountCodeMap_MENG.put("bd01", "bd02-bd04");
		accountCodeMap_MENG.put("bd05", "bd06");
		accountCodeMap_MENG.put("bd08", "bd12");
		accountCodeMap_MENG.put("bd10", "bd11");
		accountCodeMap_MENG.put("bd03", "bd14");
		accountCodeMap_MENG.put("sg01", "sg02-sg04");
		
		accountTmpMap.put("bd10", "-48");
		accountTmpMap.put("3s03", "-39");
		accountTmpMap.put("sg03", "-33&750");
		
		levelMap.put(1, "一级");
		levelMap.put(2, "二级");
		levelMap.put(3,"三级");
		
		onlineStatusMap.put(0, "否");
		onlineStatusMap.put(1, "是");
		
		includedStatusMap.put(0, "否");
		includedStatusMap.put(1,"是");
		
		typeMap.put(1, "买车");
		typeMap.put(2, "车展");
		typeMap.put(3,"二手车");
		typeMap.put(4,"用车");
		businessType.put("business","业务类型");
		businessType.put("ad","广告形式");
		businessType.put("pay","付费免费");
		businessType.put("source","来源");
		
		getcartimeMap.put(1,"本期团购");
		getcartimeMap.put(2,"一个月内");
		getcartimeMap.put(3,"三个月内");
		getcartimeMap.put(4,"一年内");
		//1、置换 2、个人摇号 3、外地购车 4、企业摇号 5.没有指标 6.摇号和置换都可以 7 本地购车 10 摇号中 
				buywayMap.put(1,"置换");
				buywayMap.put(2,"个人摇号");
				buywayMap.put(3,"外地购车");
				buywayMap.put(4,"企业摇号");
				buywayMap.put(5,"没有指标");
				buywayMap.put(6,"摇号和置换都可以");
				buywayMap.put(7,"本地购车");
				buywayMap.put(8,"非置换");
				buywayMap.put(9,"竞价");
				buywayMap.put(10,"摇号中");
				
				//10：未处理 20：已回访 21：车型待定 22：已知行情 23：已参团未购车 24：着急购车 25：客服关闭 30：已邀约 31：邀约成功  33：顺延下棋 34：待定 35：短信邀约 36：未知邀约 40：已签到 
				oldApplyStateMap.put(10,"未处理");
				oldApplyStateMap.put(20,"已回访");
				oldApplyStateMap.put(21,"车型待定");
				oldApplyStateMap.put(22,"已知行情");
				oldApplyStateMap.put(23,"已参团未购车");
				oldApplyStateMap.put(24,"着急购车");
				oldApplyStateMap.put(25,"客服关闭");
				oldApplyStateMap.put(30,"已邀约");
				oldApplyStateMap.put(31,"邀约成功");
				oldApplyStateMap.put(33,"顺延下棋");
				oldApplyStateMap.put(34,"待定");
				oldApplyStateMap.put(35,"短信邀约");
				oldApplyStateMap.put(36,"未知邀约");
				oldApplyStateMap.put(40,"已签到");
				
				//-50 wap.sgyg.net -48 ct.sgyg.net -44 老专题页面 －39 tghzh.yiqimaiche.com －38微信报名 －34 tghwap.100che.cn －28 www.shang66.com －6 cc后台 1 tuanche.com
				sidSiteMap.put(-53,"安卓市场");
				sidSiteMap.put(-54,"ios");
				sidSiteMap.put(-52,"tghwap.100che.cn");
				sidSiteMap.put(-51,"m.tuanche.com");
				sidSiteMap.put(-50,"wap.sgyg.net");
				sidSiteMap.put(-48,"ct.sgyg.net");
				sidSiteMap.put(-44,"老专题页面");
				sidSiteMap.put(-39,"tghzh.yiqimaiche.com");
				sidSiteMap.put(-38,"微信报名");
				sidSiteMap.put(-33,"www.100che.cn");
				sidSiteMap.put(-34,"tghwap.100che.cn");
				sidSiteMap.put(-26,"www.shang66.com");
				sidSiteMap.put(-6,"cc后台");
				sidSiteMap.put(1,"tuanche.com");
				sidSiteMap.put(0,"tuanche.com");
				
				applyTypeMap.put(100,"首页");
				applyTypeMap.put(200,"品牌团购底层页");
				applyTypeMap.put(300,"车型团购底层页");
				applyTypeMap.put(400,"二手车");
				applyTypeMap.put(500,"保障");
				applyTypeMap.put(600,"资讯底层");
				applyTypeMap.put(700,"摇号");
				applyTypeMap.put(800,"用品");
				applyTypeMap.put(900,"计算");
				applyTypeMap.put(1000,"团车会");
				applyTypeMap.put(1200,"专题底层");
				applyTypeMap.put(1300,"用户中心");
				applyTypeMap.put(1400,"专题列表");
				applyTypeMap.put(1500,"资讯列表");
				applyTypeMap.put(1600,"车型综述");
				applyTypeMap.put(1700,"车型报价");
				applyTypeMap.put(1800,"车型销量");
				applyTypeMap.put(1900,"车型口碑");
				applyTypeMap.put(2000,"车型图片");
				applyTypeMap.put(2100,"车型经销商");
				applyTypeMap.put(2200,"品牌综述");
				applyTypeMap.put(2300,"品牌报价");
				applyTypeMap.put(2400,"品牌销量");
				applyTypeMap.put(2500,"品牌口碑");
				applyTypeMap.put(2600,"品牌图片");
				applyTypeMap.put(2700,"品牌经销商");
				applyTypeMap.put(2800,"车型油耗");
				
				applyTypeMap.put(2900,"团车");
				applyTypeMap.put(3000,"选车");
				applyTypeMap.put(3100,"汽车品牌大全");
				applyTypeMap.put(3200,"汽车报价大全");
				applyTypeMap.put(3300,"汽车导购");
				applyTypeMap.put(3400,"汽车经销商大全");
				applyTypeMap.put(3500,"汽车试驾");
				applyTypeMap.put(3600,"汽车排行榜");
				applyTypeMap.put(3700,"汽车图片大全");
				applyTypeMap.put(3800,"汽车销量大全");
				applyTypeMap.put(4100,"知道首页");
				applyTypeMap.put(4200,"知道列表页");
				applyTypeMap.put(4400,"双11专题页");
				applyTypeMap.put(4500,"图库图集页");
				
				positionTypeMap.put(1,"上面");
				positionTypeMap.put(2,"下面");
				positionTypeMap.put(3,"中间");
				positionTypeMap.put(4,"右面");
				positionTypeMap.put(5,"快捷报名");
				
				campaignPageMap.put("before","");
				campaignPageMap.put("title","推广计划");
				campaignPageMap.put("last","");
				campaignPageMap.put("next","group");
				campaignPageMap.put("id","campaignId");
				campaignPageMap.put("name","campaignName");
				campaignPageMap.put("params","companyId,accountId,campaignId");
				
				groupPageMap.put("before","campaign");
				groupPageMap.put("last","");
				groupPageMap.put("next","keyword");
				groupPageMap.put("id","groupId");
				groupPageMap.put("name","groupName");
				groupPageMap.put("title","推广单元组");
				groupPageMap.put("params","companyId,accountId,campaignId,groupId");
				
				keywordPageMap.put("before","group");
				keywordPageMap.put("last","campaign");
				keywordPageMap.put("next","");
				keywordPageMap.put("id","keywordId");
				keywordPageMap.put("name","title");
				keywordPageMap.put("title","关键词");
				keywordPageMap.put("params","companyId,accountId,campaignId,groupId,keywordId");
				
				accountPageMap.put("campaign",campaignPageMap);
				accountPageMap.put("group",groupPageMap);
				accountPageMap.put("keyword",keywordPageMap);

	}
}
