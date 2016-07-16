package com.tuanche.cms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuanche.cms.bean.Content;
import com.tuanche.cms.bean.Plate;
import com.tuanche.cms.common.page.impl.Pagination;
import com.tuanche.cms.service.ContentService;
import com.tuanche.cms.service.PlateService;

@Controller
@RequestMapping(value = "/content")
public class ContentController extends BaseController{
	@Autowired
	private ContentService contentService;
	@Autowired
	private PlateService plateService;
	
	@RequestMapping(value = "/toList")
	public String toList(Model module,Integer plateId,@ModelAttribute("content")Content content){
		Pagination page = content.getPage();
		Pagination.threadLocal.set(page);
		List<Content> contentByPlateId = contentService.getContentByPlateId(plateId);
		Plate plateById = plateService.getPlateById(plateId);
		module.addAttribute("plate", plateById);
		module.addAttribute("page", Pagination.threadLocal.get());
		module.addAttribute("contentList", contentByPlateId);
		return "/template/content_list";
	}
	
	@RequestMapping(value = "/toAdd")
	public String toAdd(Model module,Integer plateId){
		Plate plateById = plateService.getPlateById(plateId);
		module.addAttribute("plate", plateById);
		return "/template/content_new";
	}
	
	@RequestMapping(value = "/add")
	public String add(Model module,@ModelAttribute("content")Content content){
		Plate plateById = plateService.getPlateById(content.getPlateId());
		content.setCityId(plateById.getCityId());
		content.setCityName(plateById.getCityName());
		content.setPlateName(plateById.getPlateName());
		contentService.addContent(content);
		
		return "redirect:/content/toList?plateId="+content.getPlateId();
	}
	
	@RequestMapping(value = "/toUpdate")
	public void toUpdate(HttpServletRequest request,HttpServletResponse response
			,Model module,Integer contentId){
		Content conetntById = contentService.getConetntById(contentId);
		module.addAttribute("content", conetntById);
		JSONObject fromObject = JSONObject.fromObject(conetntById);
		sentResponseData(response, fromObject);
	}
	
	@RequestMapping(value = "/update")
	public String update(Model model,@ModelAttribute("content")Content content){
		contentService.update(content);
		return "redirect:/content/toList?plateId="+content.getPlateId();
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model ,Integer contentId,Integer plateId){
		Content content = new Content();
		content.setId(contentId);
		content.setDeleteFlay(2);
		contentService.update(content);
		return "redirect:/content/toList?plateId="+plateId;
	}
	
	@RequestMapping(value = "/sort")
	public String sort(Model model ,Integer upContentId, Integer DownContentId,Integer plateId){
		contentService.updateSort(upContentId, DownContentId);
		return "redirect:/content/toList?plateId="+plateId;
	}
	
}
