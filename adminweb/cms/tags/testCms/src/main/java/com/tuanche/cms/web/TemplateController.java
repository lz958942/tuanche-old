package com.tuanche.cms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuanche.cms.bean.Template;
import com.tuanche.cms.common.page.impl.Pagination;
import com.tuanche.cms.service.TemplateService;
import com.tuanche.cms.util.FreemarkerUtil;

@Controller
@RequestMapping(value = "/template")
public class TemplateController {
	
	@Autowired
	private TemplateService service ;

	@RequestMapping(value = "/toList",method = RequestMethod.GET)
	public String toList(Model model,@ModelAttribute("template")Template template){
		Pagination page = template.getPage();
		Pagination.threadLocal.set(page);
		List<Template> teplateList = service.getTeplateList();
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("templateList", teplateList);
		return "/template/template_list";
	}
	
	@RequestMapping(value = "/toAdd",method = RequestMethod.GET)
	public String toAdd(HttpSession session, Model model){
		return "/template/template_new";
	}
	
	@RequestMapping(value = "/add" ,method = RequestMethod.POST)
	public String add(HttpSession session, Model model,@ModelAttribute("template")Template template,
			HttpServletRequest request,HttpServletResponse response){
		try {
			String makeFtl = FreemarkerUtil.makeFtl(request, response, template);
			template.setTemplatePath(makeFtl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.addTemplate(template);
		return "redirect:/template/toList";
	}
	
	@RequestMapping(value = "/toUpdate")
	public String toUpdate(HttpSession session, Model model,int id){
		Template templateById = service.getTemplateById(id);
		model.addAttribute("template", templateById);
		return "/template/template_new";
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String update(HttpSession session, Model model,@ModelAttribute("template")Template template,
			HttpServletRequest request,HttpServletResponse response){
		try {
			String makeFtl = FreemarkerUtil.makeFtl(request, response, template);
			template.setTemplatePath(makeFtl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		service.updateTemplate(template);
		return "redirect:/template/toList";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpSession session, Model model,int id){
		Template template = new Template();
		template.setId(id);
		template.setDeleteFlay(2);
		service.updateTemplate(template);
		return "redirect:/template/toList";
	}
	
	
}
