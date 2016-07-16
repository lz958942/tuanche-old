package com.tuanche.cms.web;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuanche.cms.bean.AdGet;
import com.tuanche.cms.service.AdGetService;
import com.tuanche.cms.util.RequestUtils;

@Controller

public class AdGetController {
	@Autowired
	AdGetService adGetService;
	@RequestMapping(value="/adGet")
	@ResponseBody  
	public List<AdGet> adGet(HttpServletRequest request,Model model) throws Exception{
		int isUseTemplate=RequestUtils.getInt(request,"isUseTemplate" ,-1);
		List<AdGet> adGetList=adGetService.adGet(
						   RequestUtils.getInt(request,"channel" ,-1),
						   RequestUtils.getInt(request,"cityId" ,-1),
						   RequestUtils.getInt(request,"brandId" ,-1), 
						   RequestUtils.getInt(request,"styleId" ,-1));
		if(adGetList==null){
			 adGetList=adGetService.adGet(
					   RequestUtils.getInt(request,"channel" ,-1),
					   0,
					   RequestUtils.getInt(request,"brandId" ,-1), 
					   RequestUtils.getInt(request,"styleId" ,-1));
		}
		if(adGetList==null){
			 adGetList=adGetService.adGet(
					   RequestUtils.getInt(request,"channel" ,-1),
					   RequestUtils.getInt(request,"cityId" ,-1),
					   RequestUtils.getInt(request,"brandId" ,-1), 
					   RequestUtils.getInt(request,"styleId" ,-1));
		}
		return adGetList;
	}
}
