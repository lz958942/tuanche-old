package com.tuanche.web.tj;

import java.text.SimpleDateFormat
;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuanche.bean.admin.Promotion;
import com.tuanche.bean.che.Apply;
import com.tuanche.bean.che.BrandDomain;
import com.tuanche.bean.che.City;
import com.tuanche.bean.tongji.RateDomain;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.console.util.ExportExcel;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.console.util.NameUtil;
import com.tuanche.console.util.RequestUtils;
import com.tuanche.console.web.AuthUtil;
import com.tuanche.dao.che.BrandDao;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.tj.service.CommonService;
import com.tuanche.tj.service.RateDao;

@Controller
public class ApplyController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private BrandDao brandDao;
	@Autowired
	private RateDao rateDao;
	@RequestMapping(value="/apply/plist")
	public String plist(HttpServletRequest request,ModelMap model){
		model.addAttribute("citys",AuthUtil.checkedCityMap(request));
		model.addAttribute("bmtype",GlobalConstants.applyTypeMap);
		model.addAttribute("position",GlobalConstants.positionTypeMap);
		if(request.getMethod().equalsIgnoreCase("post")){
			Map<String,Object> map=new HashMap<String, Object>();
			addNewSearchMap(request,map,model);
			Date starttime=RequestUtils.getUtilDateTime(request,"starttime");
			Date endtime=RequestUtils.getUtilDateTime(request,"endtime");
			map.put("starttime",starttime);
			map.put("endtime",endtime);
			map.put("timeType",endtime.getTime()-starttime.getTime()<=24*3600*1000?"%Y-%m-%d %H":"%Y-%m-%d");
			Map<String,Map<Integer,Integer>> resultMap=commonService.selectCityDayPosition(map);
			model.put("resultMap",resultMap);
			model.put("btypeMap",resultMap.get("0"));
			model.addAttribute("search",map);
			model.addAttribute("starttime",starttime);
			model.addAttribute("endtime",endtime);
			return "tongji/plist";
		}
		
		Date time=new Date();
		Date end=new Date();
		model.addAttribute("starttime",time);
		end.setTime(time.getTime()+3600000);
		model.addAttribute("endtime",end);
		return "tongji/plist";
	}
	
	@RequestMapping(value="/common/position")
	@ResponseBody
	public Map<String,Map<Integer,Integer>> getPosition(HttpServletRequest request,ModelMap model){
		Map<String,Object> map=new HashMap<String, Object>();
		addNewSearchMap(request, map, model);
		Date time=RequestUtils.getUtilDateTime(request,"time");
		Date end=RequestUtils.getUtilDateTime(request,"time");
		int isDay=RequestUtils.getInt(request,"isDay",0);
		map.put("starttime",time);
		end.setTime(time.getTime()+(isDay==0?3600000*24:3600000));
		map.put("endtime",end);
		return commonService.selectPosition(map);
	}
	
	@RequestMapping(value="/common/selectAll")
	@ResponseBody
	public Map<String,Map<Integer,Integer>> selectAll(HttpServletRequest request,ModelMap model){
		Map<String,Object> map=new HashMap<String, Object>();
		addNewSearchMap(request, map, model);
		Date time=RequestUtils.getUtilDateTime(request,"time");
		Date end=RequestUtils.getUtilDateTime(request,"time");
		int isDay=RequestUtils.getInt(request,"isDay",0);
		map.put("starttime",time);
		end.setTime(time.getTime()+(isDay==0?3600000*24:3600000));
		map.put("endtime",end);
		return commonService.selectAllCity(map);
	}
	
	
	
	@RequestMapping(value="/apply/list")
	public String search(HttpServletRequest request,ModelMap model){
		if(request.getMethod().equalsIgnoreCase("post")){
			Map<String,Object> map=new HashMap<String, Object>();
			addSearchMap(request,map,model);
			Map<Integer,Map<String,Integer>> resultMap=null;
			if(map.get("districtId")!=null){
				map.put(map.get("brandId")!=null?"brandId":"brandIds",
						map.get("brandId")!=null?map.get("brandId"):AuthUtil.checkedBrandMap(request).keySet());
				map.put("group","brandId");
				resultMap=commonService.selectDayBrandApply(map);
				map.put("line", "brand_id");
				Map<Integer, Integer> selectApplySum = commonService.selectApplySum(map);
				Map<String, Integer> selectTimeApplySum = commonService.selectTimeApplySum(map);
				model.addAttribute("timeSum", selectTimeApplySum);
				model.addAttribute("disSum", selectApplySum);
				model.addAttribute("name","brand");
				model.addAttribute("ajaxName","seecarstyle");
			}else{
				map.put("group","districtId");
				map.put("line", "did");
				map.put("districtIds",getAllDistrictIds(request));
				resultMap=commonService.selectDayApply(map);
				model.addAttribute("name","district");
				model.addAttribute("ajaxName","seebrand");
				Map<Integer, Integer> selectApplySum = commonService.selectApplySum(map);
				Map<String, Integer> selectTimeApplySum = commonService.selectTimeApplySum(map);
				model.addAttribute("timeSum", selectTimeApplySum);
				model.addAttribute("disSum", selectApplySum);
			}
			model.addAttribute("timeMap",resultMap.get(0));
			model.addAttribute("data",resultMap);
			model.addAttribute("search",map);
		}
		setGetCommon(request, model);
		return "tongji/applysearch";
	}
	
	@RequestMapping(value="/apply/downloadnum")
	public void applynumdownload(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Map<String,Object> map=new HashMap<String, Object>();
		addSearchMap(request,map,model);
		Map<Integer,Map<String,Integer>> resultMap=null;
		Map<Integer, Integer> zongSum = null;
		String name=null;
		String fileName=null;
		if(map.get("districtId")!=null){
			map.put(map.get("brandId")!=null?"brandId":"brandIds",
					map.get("brandId")!=null?map.get("brandId"):AuthUtil.checkedBrandMap(request).keySet());
			map.put("group","brandId");
			resultMap=commonService.selectDayBrandApplyDownload(map);
			name="brand";
			fileName=GlobalConstants.cityMap.get(StringUtils.strToInt(request.getParameter("districtId")));
			map.put("line", "brand_id");
			zongSum = commonService.selectApplySum(map);
		}else{
			map.put("districtIds",getAllDistrictIds(request));
			resultMap=commonService.selectDayApply(map);
			map.put("group","districtId");
			map.put("line", "did");
			model.addAttribute("name","district");
			model.addAttribute("ajaxName","seebrand");
			zongSum = commonService.selectApplySum(map);
		}
		ExportExcel.exportApplyNumExcel(request,(fileName!=null?fileName:new SimpleDateFormat("yyyy-MM-dd").format(new Date()))+".xls", resultMap, response,name,zongSum);
	}
	
	@RequestMapping(value="/apply/download")
	public void applydownload(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Map<String,Object> map=new HashMap<String, Object>();
		addSearchMap(request,map,model);
		map.put(map.get("brandId")!=null?"brandId":"brandIds",
				map.get("brandId")!=null?map.get("brandId"):AuthUtil.checkedBrandMap(request).keySet());
		map.put(map.get("districtId")!=null?"districtId":"districtIds",
				map.get("districtId")!=null?map.get("districtId"):getAllDistrictIds(request));
		List<Apply> applyList=commonService.getApplyList(map);
		Map<String,String> titleValueMap=new LinkedHashMap<String,String>();
		titleValueMap.put("DistrictName","地区");
		titleValueMap.put("Bmtime","报名时间");
		titleValueMap.put("Name","姓名");
		titleValueMap.put("Phone","号码");
		titleValueMap.put("BrandName","品牌");
		titleValueMap.put("CarstyleName","车型");
		titleValueMap.put("StateName","是否参团");
		titleValueMap.put("GetcartimeName","提车时间");
		titleValueMap.put("BuywayName","购买方式");
		titleValueMap.put("Sid","站点id");
		titleValueMap.put("SidName","站点名称");
		titleValueMap.put("ApplyType","报名来源");
		titleValueMap.put("PositionType","报名位置");
		titleValueMap.put("Newsid","资源id");
		titleValueMap.put("Ip","IP");
		titleValueMap.put("AccountCode","账户代码");
		titleValueMap.put("BizCode","业务类型");
		titleValueMap.put("Kid","关键词ID");
		titleValueMap.put("Remarks","备注");
		titleValueMap.put("Knowprice","已知优惠");
		ExportExcel.exportExcel(new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xls",titleValueMap,applyList,request,response);
	}
	@RequestMapping(value="/apply/downloadRate")
	public void ratedownload(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Map<String,String> titleValueMap=new LinkedHashMap<String,String>();
		//啊啊啊
		RateDomain rateDomain=new RateDomain();
		Date starttime1=RequestUtils.getUtilDate(request,"starttime");
		Date endtime1=RequestUtils.getUtilDate(request,"endtime");
		String cityId=request.getParameter("cityId");
		String brandId=request.getParameter("brandName");
		String styleId=request.getParameter("styleId");
		String pageType=request.getParameter("pageType");
		String starttime=request.getParameter("starttime");
		String endtime=request.getParameter("endtime");
		if(brandId!=null && brandId.length()>0){
			rateDomain.setBrandId(Integer.valueOf(brandId));
		}
		if(styleId!=null && styleId.length()>0){
			rateDomain.setStyleId((Integer.valueOf(styleId)));
		}
		if(cityId!=null&&cityId.length()>0){
			rateDomain.setCityId(Integer.valueOf(cityId));
		}
		if(pageType!=null&&pageType.length()>0){
			rateDomain.setPageType(Integer.valueOf(pageType));
		}
		if(starttime!=null && starttime.length()>0){
			rateDomain.setStartTime(starttime.trim().replaceAll("-",""));
		}
		if(endtime!=null&&endtime.length()>0){
			rateDomain.setEndTime(endtime.trim().replaceAll("-",""));
		}
		List<RateDomain> list=rateDao.selectRate(rateDomain);
		titleValueMap.put("Date","时间");
		titleValueMap.put("CityName","城市");
		titleValueMap.put("BrandName","品牌");
		titleValueMap.put("StyleName","车型");
		titleValueMap.put("PageTypeName","页面类型");
		titleValueMap.put("Pv","浏览量");
		titleValueMap.put("Uv","独立访客");
		titleValueMap.put("Rate","转化率");
		titleValueMap.put("ApplyNumber","报名人数");	
		ExportExcel.exportExcel(new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xls",titleValueMap,list,request,response);
		model.addAttribute("starttime", starttime1);
		model.addAttribute("endtime", endtime1);
	}
	/**
	 * 下载汇总
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value="/apply/downloadRateAll")
	public void ratedownloadAll(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		Map<String,String> titleValueMap=new LinkedHashMap<String,String>();
		RateDomain rateDomain=new RateDomain();
		Date starttime1=RequestUtils.getUtilDate(request,"starttime");
		Date endtime1=RequestUtils.getUtilDate(request,"endtime");
		String cityId=request.getParameter("cityId");
		String starttime=request.getParameter("starttime");
		String endtime=request.getParameter("endtime");
		if(cityId!=null&&cityId.length()>0){
			rateDomain.setCityId(Integer.valueOf(cityId));
		}
		if(starttime!=null && starttime.length()>0){
			rateDomain.setStartTime(starttime.trim().replaceAll("-",""));
		}
		if(endtime!=null&&endtime.length()>0){
			rateDomain.setEndTime(endtime.trim().replaceAll("-",""));
		}
		List<RateDomain> list=rateDao.selectRates(rateDomain);
		titleValueMap.put("Date","时间");
		titleValueMap.put("CityName","城市");
		titleValueMap.put("Pv","浏览量");
		titleValueMap.put("Uv","独立访客");
		titleValueMap.put("Rate","转化率");
		titleValueMap.put("ApplyNumber","报名人数");	
		ExportExcel.exportExcel(new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"汇总.xls",titleValueMap,list,request,response);
		model.addAttribute("starttime", starttime1);
		model.addAttribute("endtime", endtime1);
	}
	
	@RequestMapping(value="/apply/sitelist")
	public String sitelist(HttpServletRequest request,ModelMap model){
		if(request.getMethod().equalsIgnoreCase("post")){
			Map<String,Object> map=new HashMap<String, Object>();
			addSearchMap(request,map,model);
			if(map.get("districtId")!=null){
				map.put(map.get("brandId")!=null?"brandId":"brandIds",
						map.get("brandId")!=null?map.get("brandId"):AuthUtil.checkedBrandMap(request).keySet());
			}else if(map.get("brandId")!=null){
				map.put(map.get("districtId")!=null?"districtId":"districtIds",
						map.get("districtId")!=null?map.get("districtId"):getAllDistrictIds(request));
			}
			Map<String,Map<String,Integer>> resultMap=commonService.selectTimeApply(map);
			model.addAttribute("sidMap",resultMap.get("0"));
			model.addAttribute("data",resultMap);
			model.addAttribute("search",map);
		}
		setGetCommon(request, model);
		return "tongji/sitelist";
	}
	
	
	@RequestMapping("/apply/zlist")
	public String zlist(Promotion promotion,HttpServletRequest request,ModelMap model){
			Pagination page=promotion.getPage();
			if(page==null){
				page=new Pagination();
			}
			Pagination.threadLocal.set(page);
			// 查询品牌
			List<BrandDomain> brands = brandDao.selectBrandAll();
			String cityId=request.getParameter("cityId");
			String brandId=request.getParameter("brandName");
			String styleId=request.getParameter("styleId");
			String pageType=request.getParameter("pageType");
			String starttime=request.getParameter("starttime");
			String endtime=request.getParameter("endtime");
			RateDomain rateDomain=new RateDomain();
			Boolean flag=false;
			if(brandId!=null && brandId.length()>0){
				flag=true;
				rateDomain.setBrandId(Integer.valueOf(brandId));
			}
			if(styleId!=null && styleId.length()>0){
				flag=true;
				rateDomain.setStyleId((Integer.valueOf(styleId)));
			}
			if(cityId!=null&&cityId.length()>0){
				flag=true;
				rateDomain.setCityId(Integer.valueOf(cityId));
			}
			if(pageType!=null&&pageType.length()>0){
				flag=true;
				rateDomain.setPageType(Integer.valueOf(pageType));
			}
			if(starttime!=null && starttime.length()>0){
				flag=true;
				rateDomain.setStartTime(starttime.trim().replaceAll("-",""));
			}
			if(endtime!=null&&endtime.length()>0){
				flag=true;
				rateDomain.setEndTime(endtime.trim().replaceAll("-",""));
			}
			Date starttime1=RequestUtils.getUtilDate(request,"starttime");
			Date endtime1=RequestUtils.getUtilDate(request,"endtime");
			if(flag)
			{
			model.addAttribute("rate",rateDao.queryByPage(rateDomain) );
			}
			//分页条件
			model.addAttribute("page", Pagination.threadLocal.get());
			model.addAttribute("citys",GlobalConstants.districtMap);
			model.addAttribute("flag",1);
			model.addAttribute("brands", brands);
			model.addAttribute("styleId", styleId);
			model.addAttribute("starttime", starttime1);
			model.addAttribute("endtime", endtime1);
			model.addAttribute("pagetype",pageType);
			model.addAttribute("brandName", brandId);
			model.addAttribute("cityId",cityId);
		return "tongji/zlist";
	}
	/**
	 * 汇总
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/apply/zzlist")
	public String zzlist(Promotion promotion,HttpServletRequest request,ModelMap model){
			Pagination page=promotion.getPage();
			if(page==null){
				page=new Pagination();
			}
			Pagination.threadLocal.set(page);
			// 查询品牌
			List<BrandDomain> brands = brandDao.selectBrandAll();
			String cityId=request.getParameter("cityId");
			String starttime=request.getParameter("starttime");
			String endtime=request.getParameter("endtime");
			RateDomain rateDomain=new RateDomain();
			Boolean flag=false;
			if(cityId!=null&&cityId.length()>0){
				flag=true;
				rateDomain.setCityId(Integer.valueOf(cityId));
			}
			if(starttime!=null && starttime.length()>0){
				flag=true;
				rateDomain.setStartTime(starttime.trim().replaceAll("-",""));
			}
			if(endtime!=null&&endtime.length()>0){
				flag=true;
				rateDomain.setEndTime(endtime.trim().replaceAll("-",""));
			}
			Date starttime1=RequestUtils.getUtilDate(request,"starttime");
			Date endtime1=RequestUtils.getUtilDate(request,"endtime");
			if(flag)
			{
			model.addAttribute("rate",rateDao.selectRatesByPage(rateDomain));
			Pagination.threadLocal.get().setTotalCount(rateDao.selectRates(rateDomain).size());
			}
/*			for(RateDomain r:rateDao.selectRates(rateDomain)){
				System.out.println(r.getDate()+"............."+r.getCityName()+".............."+r.getPv()+"........."+r.getUv()+"..............."+r.getRate()+"............."+".............."+r.getApplyNumber());
			}*/
			//分页条件
			model.addAttribute("page", Pagination.threadLocal.get());
			model.addAttribute("citys",GlobalConstants.districtMap);
			model.addAttribute("brands", brands);
			model.addAttribute("starttime", starttime1);
			model.addAttribute("endtime", endtime1);
			model.addAttribute("cityId",cityId);
		return "tongji/zlist";
	}
	@RequestMapping(value="/apply/accountlist")
	public String accountlist(HttpServletRequest request,ModelMap model){
		if(request.getMethod().equalsIgnoreCase("post")){
			Map<String,Object> map=new HashMap<String, Object>();
			addSearchMap(request, map,model);
			if(map.get("districtId")!=null){
				map.put(map.get("brandId")!=null?"brandId":"brandIds",
						map.get("brandId")!=null?map.get("brandId"):AuthUtil.checkedBrandMap(request).keySet());
			}else if(map.get("brandId")!=null){
				map.put(map.get("districtId")!=null?"districtId":"districtIds",
						map.get("districtId")!=null?map.get("districtId"):getAllDistrictIds(request));
			}
			Map<String,Map<String,Integer>> resultMap=commonService.selectAccoutApply(map);
			model.addAttribute("accountMap",resultMap.get("0"));
			model.addAttribute("data",resultMap);
			model.addAttribute("search",map);
		}
		setGetCommon(request, model);
		return "tongji/accountlist";
	}
	
	@SuppressWarnings("unchecked")
	private void addSearchMap(HttpServletRequest request,Map<String,Object> map,ModelMap model){
		Enumeration<String> paraNames=request.getParameterNames();
		String name=null;
		while(paraNames.hasMoreElements()){
			name=paraNames.nextElement();
			if(name.indexOf("time")>=0){
				Date starttime=RequestUtils.getUtilDate(request,"starttime");
				Date endtime=RequestUtils.getUtilDate(request,"endtime");
				map.put("timeType",starttime.getTime()-endtime.getTime()==0?"%Y-%m-%d %H":"%Y-%m-%d");
				model.addAttribute("endtime",endtime);
				model.addAttribute("starttime",starttime);
				map.put("starttime",starttime);
				map.put("endtime",new Date(endtime.getTime()+24*3600*1000));
				
				continue;
			}
			if(StringUtils.isNotEmpty(request.getParameter(name)))
				map.put(name,request.getParameter(name));
		}
	}
	
	@SuppressWarnings("unchecked")
	private void addNewSearchMap(HttpServletRequest request,Map<String,Object> map,ModelMap model){
		Enumeration<String> paraNames=request.getParameterNames();
		String name=null;
		while(paraNames.hasMoreElements()){
			name=paraNames.nextElement();
			if(name.indexOf("time")>=0) continue;
			if(StringUtils.isNotEmpty(request.getParameter(name)))
				map.put(name,request.getParameter(name));
		}
	}
	
	
	
	public void setGetCommon(HttpServletRequest request,ModelMap model){
		Date starttime=new Date();
		if(request.getMethod().equalsIgnoreCase("get")){
			model.addAttribute("starttime",starttime);
			model.addAttribute("endtime",starttime);
		}
		model.addAttribute("citys",AuthUtil.checkedCityMap(request));
		model.addAttribute("brands",AuthUtil.checkedBrandMap(request));
	}
	
	private Integer[] getAllDistrictIds(HttpServletRequest request){
		Map<String, City> checkedCityMap = AuthUtil.checkedCityMap(request);
		if(checkedCityMap == null){
			return null;
		}
		Integer[] districtIds=new Integer[checkedCityMap.size()];
		int i=0;
		for(City c:checkedCityMap.values()){
			if(c!=null){
				districtIds[i++]=c.getId();
			}
		}
		return districtIds;
	}
	
	@RequestMapping(value="/common/seecarstyle")
	@ResponseBody
	public Map<String,Map<String,Integer>> seeCarstyle(HttpServletRequest request,ModelMap model){
		Map<String,Object> map=new HashMap<String, Object>();
		addSearchMap(request, map, model);
		map.put("group","carstyleId");
		return commonService.selectDayCarstyleApply(map);
	}
	@RequestMapping(value="/common/seebrand")
	@ResponseBody
	public Map<String,Map<String,Integer>> seeBrand(HttpServletRequest request,ModelMap model){
		Map<String,Object> map=new HashMap<String, Object>();
		addSearchMap(request, map, model);
		map.put("group","brandId");
		Map<Integer,Map<String,Integer>> reMap=commonService.selectDayBrandApply(map);
		map.put("line", "brand_id");
		Map<Integer, Integer> selectApplySum = commonService.selectApplySum(map);
		Map<String,Map<String,Integer>> returnMap=new LinkedHashMap<String, Map<String,Integer>>();
		reMap.remove(0);
		for(Integer id:reMap.keySet()){
			Map<String, Integer> map2 = reMap.get(id);
			map2.put("00", selectApplySum.get(id));
			returnMap.put(id+"-"+NameUtil.getBrandName(id),map2);
		}
		return returnMap;
	}

}
