package com.tuanche.cms.web;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.cms.bean.AdContent;
import com.tuanche.cms.bean.AdPosition;
import com.tuanche.cms.dao.AdContentDao;
import com.tuanche.cms.dao.AdPositionDao;
import com.tuanche.cms.util.GlobalConstants;
import com.tuanche.cms.util.Pager;
import com.tuanche.cms.util.RequestUtils;
import com.tuanche.commons.util.StringUtils;


@Controller
@RequestMapping(value="/advertising")
public class AdvertisingController {
	@Autowired
	AdContentDao adContentDao;
	@Autowired
	AdPositionDao adPositionDao;
	
	@RequestMapping(value="/contentList")
	public ModelAndView searchContentList(Model model,HttpServletRequest request){
		AdContent adContent=setAdContent(request);
		int cpage = RequestUtils.getInt(request, "cpage",0);
		int totalRows = RequestUtils.getInt(request, "totalRows",0);
		totalRows=adContentDao.count(adContent);
		int start=(cpage>0?cpage-1:cpage);
		adContent.setStart(start*10);
		adContent.setLimit(10);
		model.addAttribute("list", adContentDao.queryAdContent(adContent));
		model.addAttribute("adContent",adContent);
		model.addAttribute("pb",new Pager(cpage, totalRows,10));
		model.addAttribute("channelMap", GlobalConstants.channelMap);
		model.addAttribute("adTypeMap", GlobalConstants.adTypeMap);
		model.addAttribute("cityMap", GlobalConstants.cityMap);
		return new ModelAndView("advertising/ad_position_content");
	}
	/**
	 * 添加广告位
	 */
	@RequestMapping(value="/addContentPosition")
	public ModelAndView add(Model model){
		model.addAttribute("channelMap", GlobalConstants.channelMap);
		model.addAttribute("adTypeMap", GlobalConstants.adTypeMap);
		model.addAttribute("GroupNameList",adContentDao.getGroupName());
		return new ModelAndView("advertising/ad_position_content_add");
	}
	/**
	 * 编辑广告位内容
	 */
	@RequestMapping(value="/editContent/{id}")
	public ModelAndView edit(@PathVariable("id")int id, Model model){
		model.addAttribute("adContent", adContentDao.queryAdContentById(id));
		model.addAttribute("groupList", adContentDao.getGroupName());
		model.addAttribute("channelMap", GlobalConstants.channelMap);
		model.addAttribute("adTypeMap", GlobalConstants.adTypeMap);
		return new ModelAndView("advertising/ad_position_content_edit");
	}
	/**
	 * 添加或编辑后保存广告位内容 
	 */
	@RequestMapping(value="/saveEdit/{type}")
	public String edit(@PathVariable("type") String type,HttpServletRequest request, 
			Model model){
		AdContent adContent=setAdContent(request);
		List<AdContent> existAdContentList=adContentDao.queryAdContentAll();
		List<String> locationCodeList=new ArrayList<String>();
		for(AdContent adCt:existAdContentList){
			if(adCt.getId()!=RequestUtils.getInt(request, "adContentId",-1)){
				locationCodeList.add(adCt.getLocationCode().trim());
			}
			
		}
		//type为1是添加，为2是修改
		if(type.equals("add")&&(!locationCodeList.contains(adContent.getLocationCode()))){
			adContent.setFlag(1);
			adContentDao.addAdContent(adContent);
		}else if(type.equals("update")&&(!locationCodeList.contains(adContent.getLocationCode().trim()))){
		
			adContent.setId(RequestUtils.getInt(request, "adContentId",-1));
			adContentDao.updateAdContent(adContent);
		}
		return "redirect:/advertising/contentList";
	}

	
	@RequestMapping(value="/openContent/{id}/{status}")
	public void openContent(@PathVariable("id")int id,@PathVariable("status")int status){
		//AdContent adContent=setAdContent(request);
		adContentDao.openContent(id,status);
//		model.addAttribute("channelMap", GlobalConstants.channelMap);
//		model.addAttribute("adTypeMap", GlobalConstants.adTypeMap);
//		model.addAttribute("cityMap", GlobalConstants.cityMap);
//		model.addAttribute("list", adContentDao.queryAdContent(adContent));
//		model.addAttribute("adContent",adContent);
	}
	/**
	 * 指派城市
	 */
	@RequestMapping(value="/assignCity/{id}")
	public ModelAndView assignCity(@PathVariable("id")int id, Model model){
		List<AdPosition> AdPositionList=adPositionDao.findCityById(id);
		System.out.println("AdPositionList.size()=="+AdPositionList.size());
		List<Integer> cityList=new LinkedList<Integer>();
		for(AdPosition adp:AdPositionList){
			System.out.println("==========="+adp.getCityId());
			cityList.add(adp.getCityId());
		}
		model.addAttribute("cityList", cityList);
		model.addAttribute("adContent", adContentDao.queryAdContentById(id));
		model.addAttribute("cityMap", GlobalConstants.cityMap);
		return new ModelAndView("advertising/ad_assign_city");
	}
	/**
	 * 指派城市保存
	 */
	@RequestMapping(value="/assignCitySave")
	public String assignCitySave( Model model,HttpServletRequest request){
		List<AdPosition> adPositionList=adPositionDao.queryAdPositionAll();
		List<String> strList=new ArrayList<String>();
		for(AdPosition adPosition:adPositionList){
			strList.add(adPosition.getAdContentId()+"-"+adPosition.getCityId());
		}
		String CityStr=request.getParameter("checkItemSave");
		String ContentId=request.getParameter("adContentId");
		if(!StringUtils.isEmpty(CityStr)){
			String[] CityArray=CityStr.split(",");
			for(String tmp:CityArray){
				if(!strList.contains(ContentId+"-"+tmp)){
					AdPosition adPosition=new AdPosition(Integer.parseInt(ContentId),Integer.parseInt(tmp));
					adPosition.setFlag(1);
					adContentDao.assignCiy(adPosition);
				}
				
			}
		}
		return "redirect:/advertising/contentList";
	}
	
	@RequestMapping(value="/deleteContentbyId/{id}")
	public String deleteContent(@PathVariable("id")int id,
			Model model,HttpServletRequest request){
		AdContent adContent=setAdContent(request);
		model.addAttribute("adContent", adContent);
		adContentDao.deleteContentbyId(id);
		return "redirect:/advertising/contentList";
	}
	public AdContent setAdContent(HttpServletRequest request){
		AdContent adContent=new AdContent();
		adContent.setChannel(RequestUtils.getInt(request, "channel",-1));
		adContent.setAdType(RequestUtils.getInt(request, "adType",-1));
		adContent.setLocationName(RequestUtils.getString(request, "locationName"));
		adContent.setDescribe(RequestUtils.getString(request, "describe"));
		adContent.setLocationCode(RequestUtils.getString(request, "locationCode"));
		adContent.setWidth(RequestUtils.getInt(request, "width",-1));
		adContent.setHeight(RequestUtils.getInt(request, "height",-1));
		if("-1".equals(RequestUtils.getString(request,"groupName1"))){
			adContent.setGroupName(RequestUtils.getString(request,"groupName2"));
		}else{
			adContent.setGroupName(RequestUtils.getString(request,"groupName1"));
		}
		return adContent;
	}
	
}
