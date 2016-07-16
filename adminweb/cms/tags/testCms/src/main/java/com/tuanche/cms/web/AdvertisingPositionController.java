package com.tuanche.cms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.tuanche.cms.bean.AdContentPosition;
import com.tuanche.cms.bean.City;
import com.tuanche.cms.dao.AdContentPositionDao;
import com.tuanche.cms.dao.InfoDao;
import com.tuanche.cms.util.GlobalConstants;
import com.tuanche.cms.util.Pager;
import com.tuanche.cms.util.RequestUtils;
import com.tuanche.commons.util.StringUtils;


@Controller
@RequestMapping(value="/advertisingPosition")
public class AdvertisingPositionController extends BaseController{
	@Autowired 
	AdContentPositionDao adContentPositionDao;
	@Autowired
	InfoDao infoDao;
	@RequestMapping(value="/adContentPosition")
	public ModelAndView  adContentPosition(Model model,HttpServletRequest request){
		model.addAttribute("channelMap", GlobalConstants.channelMap);
		model.addAttribute("adTypeMap", GlobalConstants.adTypeMap);
		model.addAttribute("cityMap", GlobalConstants.cityMap);
		AdContentPosition adContentPosition=setAdContentPosition(request);
		int cpage = RequestUtils.getInt(request, "cpage",0);
		int totalRows = RequestUtils.getInt(request, "totalRows",0);
		int start=(cpage>0?cpage-1:cpage);
		adContentPosition.setStart(start*10);
		adContentPosition.setLimit(10);
		adContentPosition.setCityId(getcityId(RequestUtils.getString(request, "cityName")));
		List<AdContentPosition> adContentPositionList=adContentPositionDao.
				queryAdContentPosition(adContentPosition);
		totalRows=adContentPositionDao.count(adContentPosition);
		model.addAttribute("adContentPosition",adContentPosition);
		model.addAttribute("list",adContentPositionList);
		model.addAttribute("pb", new Pager(cpage,totalRows,10));
		return new ModelAndView("/advertising/ad_position_manage");
	}
	@RequestMapping(value="/getpositionbycityid")
	public void  adContentPositionByCityId(Model model,HttpServletRequest request,HttpServletResponse response){
        int cityId=StringUtils.strToInt(request.getParameter("cityId"));
		List<AdContentPosition> adContentPositionList=adContentPositionDao.getPositionByCityId(cityId);
		this.sentResponseData(response, new Gson().toJson(adContentPositionList));
	}
	
	
	//位置页开启停止
	@RequestMapping(value="/openContentPosition/{contentPositionId}/{status}")
	public String  openContentPosition(@PathVariable("contentPositionId")int contentPositionId,
			@PathVariable("status")int status,Model model,HttpServletRequest request){
		adContentPositionDao.openContentLocation(contentPositionId,status);

		return "redirect:/advertisingPosition/adContentPosition";
	}
	
	//条目删除
	@RequestMapping(value="/deleteContentPositionById/{contentPositionId}")
	public String  deleteContentPositionById(@PathVariable("contentPositionId")int contentPositionId){
		adContentPositionDao.deleteContentPositionById(contentPositionId);
		return "redirect:/advertisingPosition/adContentPosition";
	}
	
	
	
	public AdContentPosition setAdContentPosition(HttpServletRequest request){
		AdContentPosition adContentPosition=new AdContentPosition();
		adContentPosition.setCityId(RequestUtils.getInt(request, "cityId",-1));
		adContentPosition.setChannel(RequestUtils.getInt(request, "channel",-1));
		adContentPosition.setAdType(RequestUtils.getInt(request, "adType",-1));
		adContentPosition.setLocationName(RequestUtils.getString(request, "locationName"));
		adContentPosition.setCityName(RequestUtils.getString(request, "cityName"));
		return adContentPosition;
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
}
