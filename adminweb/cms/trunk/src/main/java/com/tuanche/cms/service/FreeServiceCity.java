package com.tuanche.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.tuanche.cms.bean.Employee;
import com.tuanche.cms.bean.Plate;
import com.tuanche.cms.bean.Template;
import com.tuanche.cms.bean.Tlcity;
import com.tuanche.cms.cheread.CityReadMapper;
import com.tuanche.cms.dao.GroupBuyDao;
import com.tuanche.cms.util.FreemarkerUtil;
import com.tuanche.cms.util.GlobalConstants;
import com.tuanche.cms.util.Resources;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.info.bean.Brand;
import com.tuanche.info.bean.CarStyle;
import com.tuanche.info.bean.GroupBuy;
import com.tuanche.info.service.SearchGroupBuyService;

@Service
public class FreeServiceCity{
	
	@Autowired
	private FreeService freeService;
	@Autowired
	private GroupBuyDao groupBuyDao;
	@Autowired
	private EmployeeService employeeService;
	
	//广告
	@Autowired
	private AdGetService adGetService;
	
	@Autowired
	private TemplateReadMapper templateReadMapper;
	@Autowired
	private TlcityReadMapper tlcityReadMapper;
	   
	@Autowired
	private PlateReadMapper plateReadMapper;
	
	@Autowired
	private ContentReadMapper contentReadMapper;
	
	@Autowired
	private ZiXunClassReadMapper classReadMapper;
	
	@Autowired
	private CityReadMapper cityReadMapper;
	
	@Autowired
	private OnLineBrandStyleService onlineservice;
	
	private static SearchGroupBuyService searchGroupbuy  = null;
	
	static{
		if(searchGroupbuy == null){
			searchGroupbuy = new SearchGroupBuyService();
		}
	}
	/**
	 * 生成页面
	 * Administrator：zhaojl
	 * @param tlcityId
	 * @param request
	 */
	public String makePaeeByTlcityId(Tlcity tlcityId,HttpServletRequest request,boolean uploadFTP){
		String htmlName="";
		Map<String ,Object> map = new HashMap<String, Object>();
		//获取城市模板
		Template cityTemplate = templateReadMapper.selectByPrimaryKey(tlcityId.getTid());
		//所有板块
		List<Plate> plateByCityId = freeService.getPlateByCityId(tlcityId,2);
		for(Plate p :plateByCityId){
			getContentPyPlate(map, p,tlcityId.getCityId());
		}
		Integer cityId = tlcityId.getCityId();
		com.tuanche.cms.bean.City cityVl = cityReadMapper.getCityById(cityId==-1?10:cityId);
		map.put("city", cityVl);
		init(map, cityId);
		//根据城市名生成静态文件
		htmlName =  cityVl.getPy()+"_city.html";
		if(cityId==-1){//全国的
			htmlName = "www_city.html";
		}
		try {
			FreemarkerUtil.makeHtml(map, cityTemplate.getTemplatePath(), htmlName,request,tlcityId.getTlcitytype());
			if(uploadFTP){
				freeService.uploadFtp(request, htmlName ,tlcityId.getTlcitytype());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htmlName;
	}
	
	/**
	 * 根据板块找内容
	 * Administrator：zhaojl
	 * @param map
	 * @param cityId
	 */
	public void getContentPyPlate(Map<String ,Object> map,Plate plate,Integer cityId){
		map.put(plate.getLabel(), plate);
		Integer dataFillMode = plate.getDataFillMode();
		Integer dataType = plate.getDataType();
		//自动  -- 取资讯
		if(dataFillMode == Plate.data_fill_mode_Y){
			if(plate.getDataType()==4){//团长
				List<Employee> userByCityId = employeeService.getUserByCityId(cityId);
				map.put(plate.getLabel()+"_contentList",  userByCityId);
			}else if(plate.getDataType()==5){//车型
				List<com.tuanche.cms.bean.GroupBuy> groups = onlineservice.getGroupBuyByBos(cityId, plate.getDataTypeClass(), null, plate.getConCount());
				map.put(plate.getLabel()+"_contentList", groups);
			}else if(plate.getDataType()==1){//资讯
				List<Content> contentAoto = freeService.getContentAoto(plate,cityId);
				map.put(plate.getLabel()+"_contentList", contentAoto);
			}else{//其他
				
			}
			
		}else{//手动
			Map<String , Object> map1 = new HashMap<String , Object>();
			map1.put("plateId", plate.getId());
			map1.put("count", plate.getConCount());
			List<Content> contentList = contentReadMapper.getContentLimitByPlateId(map1);
			if(plate.getDataType()==4){//团长
				StringBuffer sb = new StringBuffer("");
				List<Employee> userById = new ArrayList<Employee>();
				for(Content con :contentList){
					Employee userById2 = employeeService.getUserById(con.getGroupLeaderId());
					userById.add(userById2);
				}
				map.put(plate.getLabel()+"_contentList", userById);
			}else if(plate.getDataType()==5){//车型
				StringBuffer sb = new StringBuffer("");
				List<com.tuanche.cms.bean.GroupBuy> groups = new ArrayList<com.tuanche.cms.bean.GroupBuy>();
				//过滤已经添加的车型
				StringBuffer carStyleIds = new StringBuffer("");
				for(Content con :contentList){
					carStyleIds.append(con.getCarTypeId()+",");
					com.tuanche.cms.bean.GroupBuy groupbuy = groupBuyDao.getGroupBuyByStyleId(cityId==-1?10:cityId, con.getCarTypeId());
					groups.add(groupbuy);
				}
				//不够
				if(groups.size() <plate.getConCount()){
					List<com.tuanche.cms.bean.GroupBuy> groupBuyByBos = onlineservice.getGroupBuyByBos(cityId, plate.getDataTypeClass(), carStyleIds.toString(), plate.getConCount()-groups.size());
					if(groupBuyByBos!=null){
						groups.addAll(groupBuyByBos);
					}
				}
				List<com.tuanche.cms.bean.GroupBuy> xc = OnLineBrandStyleService.getXC(groups);
				map.put(plate.getLabel()+"_contentList", xc);
			}else if(plate.getDataType()==1){//资讯
				List<Content> contentAoto = freeService.getContentByPlateId(plate,cityId);
				if(contentAoto!= null && contentAoto.size() < plate.getConCount()){
					plate.setDataFillMode(Plate.data_fill_mode_Y);
					plate.setConCount(plate.getConCount()-contentAoto.size());
					List<Content> contentAoto1 = freeService.getContentAoto(plate,cityId);
					if(contentAoto1!=null){
						contentAoto.addAll(contentAoto1);
					}
				}
				map.put(plate.getLabel()+"_contentList", contentAoto);
				
			}else{
				map.put(plate.getLabel()+"_contentList", contentList);
			}
		}
	}
	

	/**
	 * 初始化数据
	 * Administrator：zhaojl
	 * @param map
	 */
	private void init(Map<String ,Object> map,Integer cityId){
		Map<String, String> codeMap = new HashMap<String, String>();
		//城市首页车型是否评星
		if(Resources.getString("car_comment_city").indexOf(cityId+",") != -1){
			map.put("car_comment", true);
		}
		map.put("static_version","?v="+Resources.getString("static_version"));
		//广告
		List<AdGet> adGet = null;
		try {
			 adGet = adGetService.adGet(1001, cityId==-1?0:cityId, -1, -1);
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
		//头尾公用数据
		map = freeService.initData(map, cityId,2);
		
		//热门团购
		List<GroupBuy> hotRecommend = searchGroupbuy.recommend(cityId, -1, 8, "passNum desc");
		map.put("hotGroupBuy", groupBuyDao.getGroupBuyByIds(cityId, FreeService.getBuyIds(hotRecommend)));
		
		//车型排行榜
		List<GroupBuy> recommend = searchGroupbuy.recommend(cityId==-1?10:cityId, -1, 5, "");
		map.put("car_style_top", recommend);
		//系列品牌
		LinkedHashMap<Integer, List<Brand>> citySeriesToBrand = onlineservice.getCitySeriesToBrand(cityId==-1?10:cityId, 1);
		Map<String, List<Brand>> sb_map = handlecarSeries(citySeriesToBrand);
		map.put("series_brand", sb_map);
		//品牌车型
		LinkedHashMap<Integer, List<CarStyle>> cityBrandToStyle = onlineservice.getCityBrandToStyle(cityId==-1?10:cityId, 1);
		 Iterator<Integer> iterator2 = cityBrandToStyle.keySet().iterator();
		////品牌车型  key ->String
		LinkedHashMap<String, List<CarStyle>> cityBrandToStyleString = new LinkedHashMap<String, List<CarStyle>>();
		while (iterator2.hasNext()) {
			 Integer next = iterator2.next();
			cityBrandToStyleString.put(next+"_", cityBrandToStyle.get(next));
		}
		map.put("brand_style", cityBrandToStyleString);
		//是否有资讯，展示  团购资讯导航
		if(Resources.getString("zixun_city_byOnline").indexOf(","+cityId+",")>=0){
			map.put("have_zixun", true);
		}
		
	}
	//处理系列
	private LinkedHashMap<String, List<Brand>> handlecarSeries(LinkedHashMap<Integer, List<Brand>> citySeriesToBrand){
		LinkedHashMap<String, List<Brand>>  citySeriesToBrand1 = new LinkedHashMap<String, List<Brand>>();
		Set<Integer> keySet = GlobalConstants.carSeries.keySet();
		Integer[] intArr = new  Integer[keySet.size()]; 
		keySet.toArray(intArr);
		for (int i = 0; i < intArr.length; i++) {
			if(intArr[i]!=null && citySeriesToBrand.get(intArr[i])!=null){
				citySeriesToBrand1.put(GlobalConstants.carSeries.get(intArr[i]), citySeriesToBrand.get(intArr[i]));
			}else{
				citySeriesToBrand1.put(GlobalConstants.carSeries.get(intArr[i]), null);
			}
		}
		return citySeriesToBrand1;
	}
		
}
