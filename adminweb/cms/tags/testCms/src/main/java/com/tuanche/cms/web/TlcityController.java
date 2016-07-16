package com.tuanche.cms.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuanche.cms.bean.Template;
import com.tuanche.cms.bean.Tlcity;
import com.tuanche.cms.common.page.impl.Pagination;
import com.tuanche.cms.service.TemplateService;
import com.tuanche.cms.service.TlcityService;

@Controller
@RequestMapping(value = "/tlcity")
public class TlcityController {

	@Autowired
	private TlcityService tlcityService;
	
	@Autowired
	private TemplateService templateService ;
	
	@RequestMapping(value = "/toList")
	public String toList(Model model,@ModelAttribute("tlcity")Tlcity tlcity){
		Pagination page = tlcity.getPage();
		Pagination.threadLocal.set(page);
		List<Tlcity> tlcityByPage = tlcityService.getTlcityByPage(tlcity);
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("tlcityList", tlcityByPage);
		model.addAttribute("tlcity", tlcity);
		return "/template/tlcity_list";
	}
	
	@RequestMapping(value = "/toAdd")
	public String toAdd(Model model){
		List<Template> allTemplate = templateService.getAllTemplate();
		model.addAttribute("templateList", allTemplate);
		return "/template/tlcity_new";
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model,@ModelAttribute("tlcity")Tlcity tlcity){
		Template templateById = templateService.getTemplateById(tlcity.getTid());
		tlcity.setTname(templateById.getName());
		tlcity.setPic(templateById.getPic());
		
		tlcityService.addTlcity(tlcity);
		return "redirect:/tlcity/toList";
	}
	
	@RequestMapping(value = "/toUpdate")
	public String toUpdate(Model model,int id){
		Tlcity tlcityById = tlcityService.getTlcityById(id);
		model.addAttribute("tlcity", tlcityById);
		List<Template> allTemplate = templateService.getAllTemplate();
		model.addAttribute("templateList", allTemplate);
		return "/template/tlcity_new";
	}
	
	@RequestMapping(value = "/update")
	public String update(Model model,@ModelAttribute("tlcity")Tlcity tlcity){
		Template templateById = templateService.getTemplateById(tlcity.getTid());
		tlcity.setTname(templateById.getName());
		tlcity.setContent(templateById.getContent());
		tlcity.setPic(templateById.getPic());
		
		tlcityService.updateTlcity(tlcity);
		return "redirect:/tlcity/toList";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(int id){
		Tlcity tlcity = new Tlcity();
		tlcity.setId(id);
		tlcity.setDeleteFlay(2);
		tlcityService.updateTlcity(tlcity);
		return "redirect:/tlcity/toList";
	}
}
