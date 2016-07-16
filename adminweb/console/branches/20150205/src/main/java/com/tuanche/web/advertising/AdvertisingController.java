package com.tuanche.web.advertising;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.che.AdContent;
import com.tuanche.bean.che.AdPosition;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.console.util.StringUtils;
import com.tuanche.dao.che.AdContentDao;
import com.tuanche.dao.che.AdPositionDao;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.util.ZiXunDateUtil;
import com.tuanche.util.RequestUtils;

@Controller
public class AdvertisingController {
	@Autowired
	AdContentDao adContentDao;
	@Autowired
	AdPositionDao adPositionDao;
	
	@RequestMapping(value="/advertising/contentList")
	public ModelAndView searchContentList(AdContent bean,Model model,HttpServletRequest request){
		AdContent adContent=setAdContent(request);
		Pagination pagination=bean.getPage();
		if(pagination==null){pagination = new Pagination();}
		Pagination.threadLocal.set(pagination);
		model.addAttribute("list", adContentDao.queryAdContent(adContent));
		model.addAttribute("adContent",adContent);
		model.addAttribute("channelMap", GlobalConstants.getChannelName);
		model.addAttribute("cityMap", GlobalConstants.cityMap);
		model.addAttribute("page", Pagination.threadLocal.get());
		return new ModelAndView("advertising/ad_position_content");
		
	}
	/**
	 * 添加广告位
	 */
	@RequestMapping(value="/advertising/addContentPosition")
	public ModelAndView add(Model model){
		model.addAttribute("channelMap", GlobalConstants.getChannelName);
		model.addAttribute("GroupNameList",adContentDao.getGroupName());
		return new ModelAndView("advertising/ad_position_content_add");
	}
	/**
	 * 编辑广告位内容
	 */
	@RequestMapping(value="/advertising/editContent")
	public ModelAndView edit(@Param("id")int id, Model model){
		model.addAttribute("adContent", adContentDao.queryAdContentById(id));
		model.addAttribute("channelMap", GlobalConstants.getChannelName);
		model.addAttribute("groupList", adContentDao.getGroupName());
		return new ModelAndView("advertising/ad_position_content_edit");
	}
	/**
	 * 添加或编辑后保存广告位内容 
	 */
	@RequestMapping(value="/advertising/saveEdit")
	public String edit(@Param("type")String type,HttpServletRequest request,Model model){
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

	
	@RequestMapping(value="/advertising/openContent/{id}/{status}")
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
	@RequestMapping(value="/advertising/assignCity")
	public ModelAndView assignCity(@Param("id")int id, Model model){
		List<AdPosition> AdPositionList=adPositionDao.findCityById(id);
		List<Integer> cityList=new LinkedList<Integer>();
		for(AdPosition adp:AdPositionList){
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
	@RequestMapping(value="/advertising/assignCitySave")
	public String assignCitySave( Model model,HttpServletRequest request,HttpSession session){
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		  if(sessionUser!=null&&sessionUser.getId()!=null){
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
					adPosition.setCreateTime(ZiXunDateUtil.getDateToString());
					adPosition.setCreateUid(sessionUser.getId());
					adContentDao.assignCiy(adPosition);
				}/*else if(){
					//删除的城市,
					
				}*/
				
			}
		}
		  }
		return "redirect:/advertising/contentList";
	}
	
	@RequestMapping(value="/advertising/deleteContentbyId")
	public String deleteContent(@Param("id")int id,
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
	@RequestMapping(value="/json/validateLC")
	@ResponseBody
	public boolean validateLC(@Param("name") String name) {
		if(StringUtils.isNotEmptyString(name)){
			return adContentDao.findByLocationCode(name);
		}
		return false;
	}
}
