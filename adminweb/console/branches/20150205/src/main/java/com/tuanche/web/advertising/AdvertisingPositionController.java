package com.tuanche.web.advertising;
import java.util.HashMap;
import java.util.List;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.tuanche.bean.che.AdContentPosition;
import com.tuanche.bean.che.City;
import com.tuanche.bean.che.PicAtlasBean;
import com.tuanche.bean.che.SysConfig;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.dao.che.AdContentPositionDao;
import com.tuanche.dao.che.adveInfoReadDao;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.util.ZiXunDateUtil;
import com.tuanche.util.KevinUtil;
import com.tuanche.util.RequestUtils;


@Controller
public class AdvertisingPositionController {
	@Autowired 
	AdContentPositionDao adContentPositionDao;
	@Autowired
	adveInfoReadDao infoDao;
	public static final Map<Integer,String> adTypeMap=new HashMap<Integer,String>();
    static{
    	adTypeMap.put(1, "文字链接");
    	adTypeMap.put(2, "图片文字");
    }
	
	@RequestMapping(value="/advertisingPosition/adContentPosition")
	public ModelAndView  adContentPosition(AdContentPosition bean,Model model,HttpServletRequest request,@Param("type")String type,HttpSession session){
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		  if(sessionUser!=null&&sessionUser.getId()!=null && type!=null && type.length()>0){
			  model.addAttribute("channelMap",recChannel(type,GlobalConstants.getChannelName));
			  sessionUser.setRecType(type);
		model.addAttribute("cityMap", GlobalConstants.cityMap);
		model.addAttribute("adTypeMap",adTypeMap );
		AdContentPosition adContentPosition=setAdContentPosition(request);
		adContentPosition.setTypes(type.split("-"));
		Pagination pagination=bean.getPage();
		if(pagination==null){pagination = new Pagination();}
		Pagination.threadLocal.set(pagination);
		adContentPosition.setCityId(getcityId(RequestUtils.getString(request, "cityName")));
		List<AdContentPosition> adContentPositionList=adContentPositionDao.
				queryAdContentPosition(adContentPosition);
		model.addAttribute("adContentPosition",adContentPosition);
		model.addAttribute("types",type);
		
		model.addAttribute("list",adContentPositionList);
		model.addAttribute("page", Pagination.threadLocal.get());
		  }
		return new ModelAndView("/advertising/ad_position_manage");
	}
	@RequestMapping(value="/advertisingPosition/getpositionbycityid")
	@ResponseBody
	public List<AdContentPosition>  adContentPositionByCityId(Model model,HttpServletRequest request,HttpServletResponse response){
        int cityId=StringUtils.strToInt(request.getParameter("cityId"));
		List<AdContentPosition> adContentPositionList=adContentPositionDao.getPositionByCityId(cityId);
		return adContentPositionList;
	}
	
	
	//位置页开启停止
	@RequestMapping(value="/advertisingPosition/openContentPosition/{contentPositionId}/{status}")
	public String  openContentPosition(@PathVariable("contentPositionId")int contentPositionId,
			@PathVariable("status")int status,Model model,HttpServletRequest request){
		adContentPositionDao.openContentLocation(contentPositionId,status);

		return "redirect:/advertisingPosition/adContentPosition";
	}
	
	//条目删除
	@RequestMapping(value="/advertisingPosition/deleteContentPositionById")
	public String  deleteContentPositionById(@Param("contentPositionId")int contentPositionId,@Param("types")String types,@Param("sta")Integer sta){
		adContentPositionDao.deleteContentPositionById(contentPositionId,sta);
		return "redirect:/advertisingPosition/adContentPosition?type="+types;
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
	@RequestMapping("/json/ap/push")
	@ResponseBody
	public String pushUpload (HttpServletRequest request,@Param("h")Integer h,@Param("w")Integer w,@Param("delSrc")String delSrc){
		return KevinUtil.picUploadByWH(request, "boxFile", delSrc, "push",w,h);
	}
	@RequestMapping("/advertisingPosition/updateImg")
	@ResponseBody
	public String updateImg (HttpSession session,@Param("id")Integer id,@Param("path")String path,@Param("picTitle")String picTitle,@Param("picDesc")String picDesc,@Param("targetUrl")String targetUrl){
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		  if(sessionUser!=null&&sessionUser.getId()!=null){
			  String newPath=null;
		if(path!=null && path.length()>0 && id!=null){
			KevinUtil.ftpUpload(session, path, "push/"+ZiXunDateUtil.getEndDir(), "wendaftp.username", "wendaftp.password");
			newPath=new String(path.replace("/pic_tmp/push", "http://pic.tuanche.com/recpic/push"));
		}
		adContentPositionDao.updateImg(id,newPath,picTitle,picDesc,sessionUser.getId(),targetUrl);
	}
		  return null;
	}
	private Map<Integer, SysConfig> recChannel(String type,Map<Integer, SysConfig> map) {
		if(type!=null && map!=null){
			Map<Integer, SysConfig> recMap=new HashMap<Integer, SysConfig>();
		if(KevinUtil.getFrequency(type,"-")==0){
			 Integer key=Integer.valueOf(type);
			 if(map.containsKey(key)){
					recMap.put(key, map.get(key));
				}
		  }else{
			  String[] types=type.split("-");
			  for (String string : types) {
				  Integer key=Integer.valueOf(string);
				if(map.containsKey(key)){
					recMap.put(key, map.get(key));
				}
			}
		  }
		return recMap;
		}
		  return null;
	}
	@RequestMapping("/json/ap/findByid")
	@ResponseBody
	public AdContentPosition findByid(@Param("id")Integer id) {
		if(id!=null){
			return adContentPositionDao.queryAdContentPositionById(id);
		}
		return null;
	}
}
