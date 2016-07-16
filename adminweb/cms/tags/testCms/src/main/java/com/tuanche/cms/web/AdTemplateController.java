package com.tuanche.cms.web;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuanche.cms.bean.AdTemplate;
import com.tuanche.cms.dao.AdTemplateDao;
import com.tuanche.cms.util.Pager;
import com.tuanche.cms.util.RequestUtils;

@Controller 
@RequestMapping(value="/adTemplate")
public class AdTemplateController {
	@Autowired
	AdTemplateDao adTemplateDao;
	
	@RequestMapping(value="/templateList")
	 public String templateList(HttpServletRequest request,Model model){
		AdTemplate adTemplate=setAdTemplate(request);
		int cpage = RequestUtils.getInt(request, "cpage",0);
		int totalRows = RequestUtils.getInt(request, "totalRows",0);
		int start=(cpage>0?cpage-1:cpage);
		adTemplate.setStart(start*10);
		adTemplate.setLimit(10);
		totalRows=adTemplateDao.count(adTemplate);
		List<AdTemplate>  adTemplateList=adTemplateDao.queryAdTemplate(adTemplate);
		System.out.println(adTemplateList.size());
		model.addAttribute("pb", new Pager(cpage, totalRows,10));
		model.addAttribute("list", adTemplateList);
		model.addAttribute("adTemplate",adTemplate);
		return "/advertising/ad_template";
	}
	@RequestMapping(value="/addTemplate")
	 public String addTemplate(HttpServletRequest request,Model model){
		return "/advertising/ad_template_add";
	}
	@RequestMapping(value="/insertTemplate")
	 public String insertTemplate(HttpServletRequest request,Model model){
		List<AdTemplate> adTemplateList=adTemplateDao.queryAdTemplateAll();
		List<String> strList=new ArrayList<String>();
		for(AdTemplate adTemplate:adTemplateList){
			strList.add(adTemplate.getCode());
		}
		AdTemplate adTemplate=setAdTemplate(request);
		if(!strList.contains(adTemplate.getCode())){
			adTemplate.setStatus(0);
			adTemplate.setFlag(1);
			adTemplateDao.addAdTemplate(adTemplate);
		}
		return "redirect:/adTemplate/templateList";
	}
	@RequestMapping(value="/deleteAdTemplateById/{id}")
	 public String deleteAdTemplateById(@PathVariable("id")int id){
		adTemplateDao.deleteAdTemplateById(id);
		return "redirect:/adTemplate/templateList";
	}
	@RequestMapping(value="/editAdTemplate/{id}")
	 public String editAdTemplate(@PathVariable("id")int id,Model model){
		AdTemplate adTemplate=adTemplateDao.queryAdTemplateById(id);
		model.addAttribute("adTemplate", adTemplate);
		return "/advertising/ad_template_edit";
	}
	
	@RequestMapping(value="/updateTemplate")
	 public String updateTemplate(HttpServletRequest request){
		AdTemplate adTemplate=setAdTemplate(request);
		adTemplate.setId(RequestUtils.getInt(request,"templateId", -1));
		adTemplateDao.updateAdTemplate(adTemplate);
		return "redirect:/adTemplate/templateList";
	}
	public AdTemplate setAdTemplate(HttpServletRequest request){
		AdTemplate adTemplate=new AdTemplate();
		adTemplate.setName(RequestUtils.getString(request, "templateName"));
		adTemplate.setStatus(RequestUtils.getInt(request,"templateStatus", -1));
		adTemplate.setCode(RequestUtils.getString(request, "templateCode"));
		adTemplate.setContent(RequestUtils.getString(request, "templateContent"));
		return adTemplate;
	}

}
