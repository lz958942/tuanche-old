package com.tuanche.cms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuanche.cms.bean.AdContentPosition;
import com.tuanche.cms.bean.AdPositionTime;
import com.tuanche.cms.bean.AdTemplate;
import com.tuanche.cms.bean.City;
import com.tuanche.cms.dao.AdContentPositionDao;
import com.tuanche.cms.dao.AdTemplateDao;
import com.tuanche.cms.dao.InfoDao;
import com.tuanche.cms.service.AdPositionTimeService;
import com.tuanche.cms.service.ImageService;
import com.tuanche.cms.service.MemcacheService;
import com.tuanche.cms.util.GlobalConstants;
//import com.tuanche.cms.util.ImageUtil;
import com.tuanche.cms.util.Pager;
import com.tuanche.cms.util.RequestUtils;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.upload.UpLoadUtil;


@Controller
@RequestMapping(value="/adPositionTime")
public class AdPositionTimeController {
	@Autowired
	AdPositionTimeService adPositionTimeService;
	@Autowired
	ImageService imageService;
	@Autowired
	AdContentPositionDao adContentPositionDao;
	@Autowired
	AdTemplateDao adTemplateDao;
	@Autowired
	InfoDao infoDao;
	
	@Autowired
	private MemcacheService memcacheService;
	
	@RequestMapping(value="/deleteWapMemcache")
	public void deleteWapMemcache(HttpServletRequest request,HttpServletResponse response){
		String cityIdStr = request.getParameter("cityId");
		memcacheService.delete(cityIdStr+"_wap_plan_ad");
	}
	
	@RequestMapping(value="/positionTime")
	public String positionTime(HttpServletRequest request,Model model){
		AdPositionTime adPositionTime=createAdPositionTime(request);
		String cityIdStr = request.getParameter("cityId");
		String cityName = RequestUtils.getString(request, "cityName");
		String isDefaultStr = RequestUtils.getString(request, "isDefault");
		 int cityId = getcityId(cityName);
		adPositionTime.setCityId(cityId);
		if(!StringUtils.isEmpty(cityIdStr)){
			adPositionTime.setCityId(Integer.parseInt(cityIdStr));
		}
		if(!StringUtils.isEmpty(isDefaultStr)){
			adPositionTime.setIsDefault(Integer.parseInt(isDefaultStr));
		}
		adPositionTime.setCityName(cityName);
		int cpage = RequestUtils.getInt(request, "cpage",0);
		int totalRows = RequestUtils.getInt(request, "totalRows",0);
		totalRows=adPositionTimeService.count(adPositionTime);
		int start=(cpage>0?cpage-1:cpage);
		adPositionTime.setStart(start*10);
		adPositionTime.setLimit(10);
		
		model.addAttribute("pb",new Pager(cpage, totalRows,10));
		List<AdPositionTime> adPositionTimeList=adPositionTimeService.queryAdPositionTime(adPositionTime);
		model.addAttribute("channelMap", GlobalConstants.channelMap);
		model.addAttribute("adTypeMap", GlobalConstants.adTypeMap);
		model.addAttribute("cityMap", GlobalConstants.cityMapPY);
		model.addAttribute("adPositionTime",adPositionTime);
		model.addAttribute("list",adPositionTimeList);
		return "/advertising/ad_position_time";
		
	}
	@RequestMapping(value="/addPositionTime")
	public String addPositionTime(Model model){
		List<AdContentPosition> adContentPositionList=adContentPositionDao.queryAdContentPositionAll();
		model.addAttribute("adContentPositionList", adContentPositionList);
		List<AdTemplate> templateList=adTemplateDao.queryAdTemplateAll();
		model.addAttribute("channelMap", GlobalConstants.channelMap);
		model.addAttribute("templateList", templateList);
		model.addAttribute("brand",GlobalConstants.brands);
		model.addAttribute("cityMap", GlobalConstants.cityMapPY);
		return "/advertising/ad_position_time_add";
	}
	@RequestMapping(value="/saveAddPositionTime")
	public String saveAddPositionTime(Model model,HttpServletRequest request){
		
		AdContentPosition adContentPosition =adContentPositionDao.
				queryAdContentPositionById(StringUtils.
				strToInt(request.getParameter("location"), -1));
		if(adContentPosition==null){
			return "redirect:/adPositionTime/positionTime";
		}

	    int width = adContentPosition.getWidth();
	    int height = adContentPosition.getHeight();
	    AdPositionTime adPositionTime=createAdPositionTime(request);
	    adPositionTime.setTemplateId(StringUtils.strToInt(request.getParameter("template"), -1));
	    adPositionTime.setAdContentId(adContentPosition.getAdContentId());
	    adPositionTime.setChannel(adContentPosition.getChannel());
	    adPositionTime.setHeight(height);
	    adPositionTime.setWidth(width);
	    adPositionTime.setLocationCode(adContentPosition.getLocationCode());
	    adPositionTime.setLocationName(adContentPosition.getLocationName());
	    adPositionTime.setContentPositionId(adContentPosition.getContentPositionId());
//	    adPositionTime.setCityId(adContentPosition.getCityId()); 不要放入广告位置城市id， 若广告位置为全国，添加的广告城市为错误信息
	    adPositionTime.setAdType(adContentPosition.getAdType());
	    String picName = request.getParameter("listPic").toString();
	    adPositionTime.setPicName(picName);
	    //压缩图片
	    cutImage(request,picName,width,height,adPositionTime);
	    if(request.getParameter("isDefault")==null){
	    	adPositionTime.setIsDefault(0);
	    }else{
	    	adPositionTime.setIsDefault(1);
	    }
	    adPositionTime.setId(-1);
	    //根据城市位置ID查找排期
	    int i=adPositionTimeService.searchAdPositionTime(adPositionTime);
	    if(i==0){
	    	adPositionTimeService.adAdPositionTime(adPositionTime);
	    }
	    
		return "redirect:/adPositionTime/positionTime?cityId="+adPositionTime.getCityId();
	}
	@RequestMapping(value="/editPositionTime/{id}")
	public String editPositionTime(@PathVariable("id")int id,Model model){
		AdPositionTime adPositionTime=adPositionTimeService.queryAdPositionTimeById(id);
		model.addAttribute("adPositionTime", adPositionTime);
		List<AdTemplate> templateList=adTemplateDao.queryAdTemplateAll();
		model.addAttribute("templateList", templateList);
		List<AdContentPosition> adContentPositionList=adContentPositionDao.getPositionByCityId(adPositionTime.getCityId());
		model.addAttribute("adContentPositionList", adContentPositionList);
		model.addAttribute("brand",GlobalConstants.brands);
		model.addAttribute("cityMap", GlobalConstants.cityMapPY);
		return "/advertising/ad_position_time_edit";
	}
	@RequestMapping(value="/saveEditPositionTime")
	public String saveEditPositionTime(HttpServletRequest request,Model model){
		AdPositionTime adPositionTime=createAdPositionTime(request);
		adPositionTime.setTemplateId(StringUtils.strToInt(request.getParameter("template"), -1));
		adPositionTime.setId(RequestUtils.getInt(request, "adPositionTimeId"));
		AdContentPosition adContentPosition =adContentPositionDao.
				queryAdContentPositionById(StringUtils.
				strToInt(request.getParameter("location"), -1));
			int width = adContentPosition.getWidth();
			int height = adContentPosition.getHeight();
		 	adPositionTime.setAdContentId(adContentPosition.getAdContentId());
		    adPositionTime.setChannel(adContentPosition.getChannel());
		    adPositionTime.setHeight(height);
		    adPositionTime.setWidth(width);
		    adPositionTime.setLocationCode(adContentPosition.getLocationCode());
		    adPositionTime.setLocationName(adContentPosition.getLocationName());
		    adPositionTime.setContentPositionId(adContentPosition.getContentPositionId());
		    adPositionTime.setAdType(adContentPosition.getAdType());
		    adPositionTime.setPicName(request.getParameter("listPic").toString());
		    //压缩图片
		   cutImage(request,adPositionTime.getPicName(),width,height,adPositionTime);
		    if(request.getParameter("isDefault")==null){
		    	adPositionTime.setIsDefault(0);
		    }else{
		    	adPositionTime.setIsDefault(1);
		    }
		    
		    int i=adPositionTimeService.searchAdPositionTime(adPositionTime);
		    if(i==0){
		    	adPositionTimeService.updateAdPositionTime(adPositionTime);
		    }
		return "redirect:/adPositionTime/positionTime?cityId="+adPositionTime.getCityId();
	}
	@RequestMapping(value="/imageUpload")
	public void imageUpload(HttpServletResponse response,HttpServletRequest request){
		UpLoadUtil  util=new UpLoadUtil();
	    util.uploadPic(request, response);
	}
	 
	@RequestMapping(value="/openContent/{id}/{status}")
		public String openContent(@PathVariable("id")int id,@PathVariable("status")int status,
				HttpServletRequest request,Model model){
		adPositionTimeService.openAdPositionTime(id,status);
		AdPositionTime adPositionTime=createAdPositionTime(request);
		int cpage = RequestUtils.getInt(request, "cpage",0);
		int totalRows = RequestUtils.getInt(request, "totalRows",0);
		totalRows=adPositionTimeService.count(adPositionTime);
		int start=(cpage>0?cpage-1:cpage);
		adPositionTime.setStart(start*10);
		adPositionTime.setLimit(10);
		model.addAttribute("pb",new Pager(cpage, totalRows,10));
		List<AdPositionTime> adPositionTimeList=adPositionTimeService.queryAdPositionTime(adPositionTime);
		model.addAttribute("channelMap", GlobalConstants.channelMap);
		model.addAttribute("adTypeMap", GlobalConstants.adTypeMap);
		model.addAttribute("cityMap", GlobalConstants.cityMapPY);
		model.addAttribute("adPositionTime",adPositionTime);
		model.addAttribute("list",adPositionTimeList);
		return "/advertising/ad_position_time";
	}
	
	@RequestMapping(value="/deleteById/{id}")
	public String deleteById(@PathVariable("id")int id,
			HttpServletRequest request,Model model){
		adPositionTimeService.deleteById(id);
		AdPositionTime adPositionTime=createAdPositionTime(request);
		int cpage = RequestUtils.getInt(request, "cpage",0);
		int totalRows = RequestUtils.getInt(request, "totalRows",0);
		totalRows=adPositionTimeService.count(adPositionTime);
		int start=(cpage>0?cpage-1:cpage);
		adPositionTime.setStart(start*10);
		adPositionTime.setLimit(10);
		model.addAttribute("pb",new Pager(cpage, totalRows,10));
		List<AdPositionTime> adPositionTimeList=adPositionTimeService.queryAdPositionTime(adPositionTime);
		model.addAttribute("channelMap", GlobalConstants.channelMap);
		model.addAttribute("adTypeMap", GlobalConstants.adTypeMap);
		model.addAttribute("cityMap", GlobalConstants.cityMapPY);
		model.addAttribute("adPositionTime",adPositionTime);
		model.addAttribute("list",adPositionTimeList);
		return "/advertising/ad_position_time";
	}
	
	@RequestMapping(value = "/checkDouble")
	public @ResponseBody Integer checkDouble(HttpServletRequest request,Model model){
		AdPositionTime adPositionTime = new AdPositionTime();
		adPositionTime.setContentPositionId(Integer.parseInt(request.getParameter("location")));
		adPositionTime.setCityId(Integer.parseInt(request.getParameter("cityId")));
		adPositionTime.setStartDate(request.getParameter("startDate"));
		adPositionTime.setEndDate(request.getParameter("endDate"));
		if(request.getParameter("isDefault")==null){
		    	adPositionTime.setIsDefault(0);
	    }else{
	    	adPositionTime.setIsDefault(1);
	    }
		int i=adPositionTimeService.searchAdPositionTime(adPositionTime);
	    if(i==0){
	    	return 1;
	    }
		return null;
	}
	
	public AdPositionTime createAdPositionTime(HttpServletRequest request){
		AdPositionTime adPositionTime=new AdPositionTime();
		adPositionTime.setCityId(StringUtils.strToInt(request.getParameter("cityId"), -1));
		adPositionTime.setChannel(StringUtils.strToInt(request.getParameter("channel"), -1));
		adPositionTime.setAdType(StringUtils.strToInt(request.getParameter("adType"), -1));
		adPositionTime.setStartDate(RequestUtils.getString(request, "startDate"));
		adPositionTime.setEndDate(RequestUtils.getString(request, "endDate"));
		adPositionTime.setAdTitle(RequestUtils.getString(request, "title"));
		adPositionTime.setDescribe(RequestUtils.getString(request, "describe"));
		adPositionTime.setAdLink(RequestUtils.getString(request, "adLink"));
		adPositionTime.setExtendCode(RequestUtils.getString(request, "extendCode"));
		adPositionTime.setBrandId(StringUtils.strToInt(request.getParameter("brandId"), -1));
		adPositionTime.setStyleId(StringUtils.strToInt(request.getParameter("styleId"), -1));
		return adPositionTime;
	}
	
	private int getcityId(String cityName){
		if(StringUtils.isEmpty(cityName)){
			return -1;
		}
		 List<City> cityByName = infoDao.getCityByName(cityName);
		 if(cityByName!=null&& cityByName.size()>0){
			 return cityByName.get(0).getId();
		 }
		return -1;
	}
	
	public void cutImage(HttpServletRequest request,String picName,int width,int height,AdPositionTime adPositionTime){
//		if(!StringUtils.isEmpty(picName)){
//			String picType = "adv";
//			String realPath=request.getSession().getServletContext().getRealPath(request.getContextPath());
//			String allPath = realPath + "pic_tmp";  
//			String allPicName = Resources.getString("picPath")+picName;
//			String smallIcon = "";
//			if(!StringUtils.isEmpty(picName)){
//				smallIcon = picName.substring(picName.indexOf(".")+1);
//			}
//			if(picName.substring(0,picName.indexOf(".")).indexOf(smallIcon) == -1){//没有压缩
//				try {
//					//com.tuanche.upload.ImageUtil.cutImage(allPicName, allPath + picName, width, height,false);
//					ImageUtil.saveToFile(allPicName,allPath + picName);
//					com.tuanche.upload.ImageUtil.cutImage(allPath + picName, allPath + picName, width, height);
//					UpLoadUtil  util=new UpLoadUtil();
//					String[] split = picName.split("/");
//					util.uploadPic(picType, realPath, split[2], split[3]);
//				} catch (Exception e) {
//					System.out.println("ImageMagick fail---------");
//					String zipImageFile = ImageUtil.zipImageFile(allPath + picName, width, height, 0.8f, smallIcon);
//					UpLoadUtil  util=new UpLoadUtil();
//					String[] split = zipImageFile.split("/");
//					util.uploadPic(picType, realPath, split[split.length-2], split[split.length-1]);
//					adPositionTime.setPicName("/"+split[split.length-3] + "/"+ split[split.length-2] +"/"+ split[split.length-1]);
//					e.printStackTrace();
//				}
//			}
//		}
	}
}
