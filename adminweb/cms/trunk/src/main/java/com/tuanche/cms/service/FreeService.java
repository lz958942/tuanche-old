package com.tuanche.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.cms.adminread.ContentReadMapper;
import com.tuanche.cms.adminread.PlateReadMapper;
import com.tuanche.cms.adminread.TemplateReadMapper;
import com.tuanche.cms.adminread.TlcityReadMapper;
import com.tuanche.cms.adminread.ZiXunClassReadMapper;
import com.tuanche.cms.bean.AdGet;
import com.tuanche.cms.bean.Content;
import com.tuanche.cms.bean.Plate;
import com.tuanche.cms.bean.Template;
import com.tuanche.cms.bean.Tlcity;
import com.tuanche.cms.bean.ZiXunClass;
import com.tuanche.cms.cheread.CityReadMapper;
import com.tuanche.cms.dao.GroupBuyDao;
import com.tuanche.cms.util.FreemarkerUtil;
import com.tuanche.cms.util.GlobalConstants;
import com.tuanche.cms.util.Resources;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.info.bean.CarStyle;
import com.tuanche.info.bean.City;
import com.tuanche.info.bean.FriendLink;
import com.tuanche.info.bean.GroupBuy;
import com.tuanche.info.bean.News;
import com.tuanche.info.service.FriendLinkService;
import com.tuanche.info.service.NewsService;
import com.tuanche.info.service.OnLineService;
import com.tuanche.info.service.SearchGroupBuyService;
import com.tuanche.info.util.Channel;
import com.tuanche.upload.FtpUtil;

@Service
public class FreeService{
	//全国    
	private static final int COUNTRY = -1;
	private static SearchGroupBuyService buyService = new SearchGroupBuyService();
	
	//广告
	@Autowired
	private AdGetService adGetService;
	
	@Autowired
	private TemplateReadMapper templateReadMapper;
	@Autowired
	private TlcityReadMapper tlcityReadMapper;
	
	@Autowired
	private PlateService plateService;
	   
	@Autowired
	private PlateReadMapper plateReadMapper;
	
	@Autowired
	private ContentReadMapper contentReadMapper;
	
	@Autowired
	private ZiXunClassReadMapper classReadMapper;
	
	@Autowired
	private  GroupBuyDao groupBuyDao;
	
	@Autowired
	private CityReadMapper cityReadMapper;

	@Autowired
	private OnLineBrandStyleService onlineservice;
	
	
	private static NewsService newsService = null;
	private static FriendLinkService linkService = null;
	private static SearchGroupBuyService searchGroupbuy  = null;
	static{
		if(newsService  == null){
			newsService = new NewsService();
		}
		if(linkService == null){
			linkService = new FriendLinkService();
		}
		if(searchGroupbuy == null){
			searchGroupbuy = new SearchGroupBuyService();
		}
		
	}
	
	/**
	 * 定时器生成 页面
	 * Administrator：zhaojl
	 */
	public List<Tlcity> getAllTlcity(HttpServletRequest request){
		return tlcityReadMapper.getAllTlcity();
	}
	
	/**
	 * 根据城市id生成页面
	 * Administrator：zhaojl
	 * @param tlcityId
	 */
	public String makePaeeByTlcityId(Tlcity city,HttpServletRequest request,boolean uploadFTP){
		String htmlName ="";
		//获取城市模板
		Template cityTemplate = templateReadMapper.selectByPrimaryKey(city.getTid());
		
		Integer cityId = city.getCityId();
		//获取城市下的所有板块  
		List<Plate> plateByCityId = this.getPlateByCityId(city,1);
		//数据
		Map<String,Object> map = new HashMap<String, Object>();
		//标示全国资讯
	    if(cityId == -1){
	    	map.put("isCountry", true);
	    }
	    //导航栏资讯   选中
	    map.put("isZixun", true);
		com.tuanche.cms.bean.City cityVl = cityReadMapper.getCityById(cityId==-1?10:cityId);
		map.put("city", cityVl);
		//初始化页面基本数据
		map = initData(map,cityId,1);
		List<GroupBuy> hotRecommend = searchGroupbuy.recommend(cityId, -1, 8, "passNum desc");
		List<com.tuanche.cms.bean.GroupBuy> groupBuyByIds = groupBuyDao.getGroupBuyByIds(cityId, FreeService.getBuyIds(hotRecommend));
		List<com.tuanche.cms.bean.GroupBuy> xc = onlineservice.getXC(groupBuyByIds);
		map.put("hotGroupBuy",xc);
		//页面广告
		this.getAd(map, cityId);
		for(Plate p :plateByCityId){
			map.put(p.getLabel(), p);//通过板块，得到板块标题  和板块的模板地址 等
			
			List<Content> contentList = this.getContentByPlateId(p,cityId);//可能p 是全国的板块，所以要传 城市id
			if(contentList!=null && contentList.size() >0){
				map.put(p.getLabel()+"_contentList", contentList);
			}
			
		}
		try {
			//根据城市名生成静态文件
			htmlName = cityVl.getPy()+"_zx.html";
			if(city.getCityId()==-1){//全国的
				htmlName = "www_zx.html";
			}
			FreemarkerUtil.makeHtml(map, cityTemplate.getTemplatePath(), htmlName,request,city.getTlcitytype());
			if(uploadFTP){
				uploadFtp(request, htmlName ,city.getTlcitytype());
			}
			System.out.println("生成页面成功");
		} catch (Exception e) {
			System.out.println("生成页面失败");
			e.printStackTrace();
		}
		return htmlName;
	}

	/**
	 * 根据板块获取内容
	 * Administrator：zhaojl
	 * @return
	 */
	public List<Content> getContentByPlateId(Plate plate,int cityId){
		List<Content> conList = null;
		//板块 的类型
		String classurl = "";
		 Integer dataTypeClass = plate.getDataTypeClass();
		 if(dataTypeClass!=null&&dataTypeClass>0){
			 classurl = classReadMapper.getZixunClassById( dataTypeClass).getUrl();
		 }
		Integer dataFillMode = plate.getDataFillMode();
		Integer dataType = plate.getDataType();
		//自动
		if(dataFillMode == Plate.data_fill_mode_Y){
			conList = getContentAoto(plate,cityId);
		}else{
			//手动
			Map<String , Object> map = new HashMap<String , Object>();
			map.put("plateId", plate.getId());
			map.put("count", plate.getConCount());
			conList = contentReadMapper.getContentLimitByPlateId(map);
			if(GlobalConstants.no_supply_zixun.get(plate.getLabel()) == null){
				Integer conCount = plate.getConCount();//需要的内容条数
				int lackCount = 0;//缺少的内容条数
				if(conList == null  || conList.size() <=0){
					lackCount = conCount;
					List<News> list = newsService.getList("", classurl, cityId, 0, lackCount);
					conList = this.listToList(list);
				}else{
					lackCount = conCount - conList.size();
					List<News> list = newsService.getList("", classurl, cityId, 0, lackCount);
					List<Content> listToList = this.listToList(list);
					conList.addAll(listToList);
				}
			}
		}
		if(conList == null){
			conList = new ArrayList<Content>();
		}
		return conList;
	}
	
	/**
	 * 自动获取资讯数据
	 * Administrator：zhaojl
	 * @param plate
	 * @return
	 */
	public  List<Content> getContentAoto(Plate plate,Integer cityId){
		List<Content> conList = null;
		//板块 的类型
		 String classurl = "";
		 Integer dataTypeClass = plate.getDataTypeClass();
		 ZiXunClass zixunClassById = null;
		 if(dataTypeClass!=null&&dataTypeClass>0){
			 zixunClassById = classReadMapper.getZixunClassById( dataTypeClass);
			 if(zixunClassById!=null){
				classurl = zixunClassById.getUrl();
				
				 Byte cityAttr = zixunClassById.getCityAttr();
				 if(cityAttr==0){
					 cityId = -1;
				 }
			 }
		 }
		Integer dataFillMode = plate.getDataFillMode();
		Integer dataType = plate.getDataType();
		//自动
		if(dataFillMode == Plate.data_fill_mode_Y){
			if(dataType == Plate.data_type_zixun){//资讯
				if(!"".equals(classurl) && classurl.indexOf("-")==classurl.length()-1){//资讯分类 url  == solr  classStr
					classurl = classurl.substring(0, classurl.length()-1);
				}
				Integer type = plate.getType();
				List<News> newslist  =null;
				if(type ==1 || type == 2){//图片类型
					newslist = newsService.getList("", classurl, cityId, 0, plate.getConCount(),true);
				}
				if(newslist == null || newslist.size() <=0){//没有 有图片的
					newslist = newsService.getList("", classurl, cityId, 0, plate.getConCount());
				}else{//有有图片的
					if(newslist.size() < plate.getConCount()){//不够板块的内容数量，就找没有图片的补齐
						 List<News> list = newsService.getList("", classurl, cityId, 0, plate.getConCount()-newslist.size());
						 newslist.addAll(list);
					}
				}
				conList = this.listToList(newslist);
			}
		}
		return conList;
	}
	
	/**
	 * 获取城市下的所有板块  （并与全国的所有板块相冗余 ,根据板块 标签冗余）
	 * Administrator：zhaojl
	 * @param cityId
	 * @param type 1:资讯   2：城市  3,摇号
	 * @return
	 */
	public List<Plate> getPlateByCityId(Tlcity city,Integer type){
		Integer cityId = city.getCityId();
		Plate pl = new Plate();
		pl.setCityId(cityId);
		pl.setPtype(type);
		List<Plate> plateByTlCityId = plateReadMapper.getPlateBywhere(pl);
		Plate pl1 = new Plate();
		pl1.setCityId(this.COUNTRY);//全国
		pl1.setPtype(type);
		List<Plate> countryAllPlate = plateReadMapper.getPlateBywhere(pl1);
		for (int i = 0; i <countryAllPlate.size(); i++) {
			//全国 板块的标签
			Plate plate = countryAllPlate.get(i);
			String label = plate.getLabel();
			int flay = 0;
			for (int j = 0; j < plateByTlCityId.size(); j++) {
				Plate plate2 = plateByTlCityId.get(j);
				//全国所有板块中的 与  本城市下的板块  重复  
				if(label != null && label.equals(plate2.getLabel())){
					flay = 1;
					break;
				}
			}
			//本城市下的板块中没有全国板块中的此板块 ，加入城市板块
			if(flay == 0){
				plateByTlCityId.add(plate);
			}
		}
		plateByTlCityId = plateService.getAllPlatehyperlinkByCityId(plateByTlCityId, cityId);
		return plateByTlCityId;
	}
	
	/**
	 * list转换
	 * Administrator：zhaojl
	 * @param news
	 * @return
	 */
	public static List<Content> listToList(List<News> news){
		List<Content> list = new ArrayList<Content>();
		if(news != null && news.size() > 0){
			for (int i = 0; i < news.size(); i++) {
				News news2 = news.get(i);
				Content content = new Content();
				content.setId(news2.getId());
				content.setTitle(news2.getTitle());
				content.setHyperlink(news2.getUrl());
				content.setExpand(news2.getSimpleInfo());
				if(news2.getPic()!= null &&  !"".equals(news2.getPic())){
					content.setImagUrl(news2.getPic());
				}
				list.add(content);
			}
		}
		return list;
	}
	
	/**
	 * 获取广告
	 * Administrator：zhaojl
	 * @param map
	 * @param cityId
	 */
	public void getAd(Map<String,Object> map,Integer cityId){
		Map<String, String> codeMap = new HashMap<String, String>();
		List<AdGet> adGet = null;
		try {
			adGet = adGetService.adGet(1006, cityId==-1?0:cityId, -1, -1);// 资讯     频道： 1006
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(adGet != null){
			for (AdGet ad:adGet) {
				if(map.get(ad.getLocationCode()) !=null ){
					if(ad.getIsDefault()==0){//如果出现非默认和默认链各个广告，非默认替换默认
						map.put(ad.getLocationCode(), ad);
					}
				}else{
					map.put(ad.getLocationCode(), ad);
					if(!StringUtils.isEmpty(ad.getExtendCode())){
						codeMap.put(ad.getLocationCode(), ad.getExtendCode());
					}
				}
			}	
		}
		map.put("adCodeMap", codeMap);
	}
	
	/**
	 * 初始化 页面上需要的数据
	 * Administrator：zhaojl
	 * @param map
	 */
	public static Map initData(Map<String,Object> map,Integer cityId,Integer tlcitytype){
		map.put("static_version","?v="+Resources.getString("static_version"));
		com.tuanche.cms.bean.City city = GlobalConstants.cityAllMap.get(cityId);
//		String pageUrl = "tuanche.com/";
		String pageUrl = Resources.getString("tuanches.url")+"/";
		if(city!=null){
			pageUrl= "http://"+city.getPy()+"."+pageUrl;
		}
		try {
			if(cityId == -1 ){
				//城市信息】
				cityId = 10;
			}

			/**
			 * 死链更新修改
			 */
			//map.put("staticFile",Resources.getString("static.url"));//静态资源，替换模板文件中的staticFile/staticUrl   注意前面加.tuanche.com
			map.put("oldHost",Resources.getString("oldHost")); 
			map.put("wwwUrl",Resources.getString("wwwUrl"));
			map.put("zhidaoUrl",Resources.getString("zhidao.url"));//团车知道url
			map.put("ucUrl",Resources.getString("uc.url"));//UCurl
			map.put("zcUrl",Resources.getString("zc.url"));//ZCurl
			
			
			
			
			
			
			map.put("staticFile", Resources.getString("static.url"));//静态资源
			
			if(tlcitytype == 1){//资讯页
				 pageUrl = pageUrl+"zx/";
				//友情链接
				List<FriendLink> flLink = linkService.getByChannel(FriendLinkService.FL_TYPE_LINK,pageUrl, Channel.CITYZIXUNINDEX, cityId);
				//商务合作
				List<FriendLink> flBusiness = linkService.getByUrl(FriendLinkService.FL_TYPE_BUSINESS, pageUrl);
				if(flLink!=null && flLink.size()>0){
					map.put("flLink", flLink);
				}
				if(flBusiness!=null && flBusiness.size()>0){
					map.put("flBusiness", flBusiness);
				}
			}else if(tlcitytype == 2){//城市首页
				List<FriendLink> byChannel = linkService.getByChannel(FriendLinkService.FL_TYPE_LINK,pageUrl, Channel.CITYINDEX, cityId);
				map.put("flLink", byChannel);
			}else{//摇号
				pageUrl =pageUrl+"yh/";
				//友情链接	
				List<FriendLink> flLink = linkService.getByUrl(FriendLinkService.FL_TYPE_LINK, pageUrl);
				//List<FriendLink> flLink = linkService.getByChannel(FriendLinkService.FL_TYPE_LINK,pageUrl, Channel.CITYINDEX, cityId);
				map.put("flLink", flLink);
			}
			//热门新车
			List<GroupBuy> recommend = buyService.recommend(cityId, -1, 40, "passNum desc");
			map.put("hotNewCars", recommend);
			//热门搜索
			if(cityId == -1){//全国默认北京
				cityId = 10;
			}
			List<CarStyle> hotSearch = OnLineService.getHotSearch(cityId, 7);
			map.put("hotSearch", hotSearch);
			//切换的城市
			LinkedHashMap<String, List<City>> changeCity = OnLineService.getChangeCity();
			map.put("changeCity", changeCity);
			
			//是否有资讯，展示  团购资讯导航
			if(GlobalConstants.cityAllMap.get(cityId).getIsOpenZixun()==1){
				map.put("have_zixun", true);
			}
			//导航   汽车装饰
			if(Resources.getString("carDecorate_city_byOnline").indexOf(","+cityId+",")>=0){
				map.put("have_zhs", true);
			}
			//导航
			map.put("isSecond",Resources.getString("second.city").indexOf(","+cityId+",")>=0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	public static String getBuyIds(List<GroupBuy>  list){
		if(list!=null){
			StringBuffer ids=new StringBuffer("");;
			for(GroupBuy tmp:list){
				ids.append(tmp.getId()+",");
			}
			String idStr=ids.toString();
			if(idStr.endsWith(",")){
				idStr=idStr.substring(0,idStr.length()-1);
			}
			return idStr;
		}
		return null;
	}
	
	/**
	 * 静态上传ftl
	 * Administrator：zhaojl
	 * @param request
	 * @param htmlName
	 * @param htmltype  1 ：资讯   2：城市页面
	 */
	public void uploadFtp(HttpServletRequest request, String htmlName,int htmltype){
		String servletPath = request.getContextPath();
        String realPath = request.getSession().getServletContext().getRealPath(servletPath);
        String ftpHost =Resources.getString("ftpHost");
        String ftpUserName=Resources.getString("ftpUserName");
        String ftpPassword=Resources.getString("ftpPassword");
        String srcDir ="";
        String destDir = "";
        if(htmltype==1){
        	destDir= Resources.getString("zixun_destDir");
            srcDir = realPath + FreemarkerUtil.zixun_staticfile;
        }else if(htmltype ==2){
        	destDir=  Resources.getString("city_destDir");
            srcDir = realPath + FreemarkerUtil.city_staticfile;
        }else if(htmltype == 3){
        	destDir= Resources.getString("yaohao_destDir");
            srcDir = realPath + FreemarkerUtil.yaohao_staticfile;
        }
    
        FtpUtil.ftpUpload(ftpHost, ftpUserName, ftpPassword, srcDir, destDir, htmlName);
	}
	
}
