package com.tuanche.web.mediaReport;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.admin.MediaReport;
import com.tuanche.bean.che.PersonCase;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.service.admin.MediaReportService;
import com.tuanche.smc.common.page.impl.Pagination;

@Controller
public class MediaReportController {
	@Autowired
private MediaReportService service;
	@RequestMapping(value="/xw/list")
	public ModelAndView list(MediaReport bean,Model model) {
		Pagination pagination=bean.getPage();
		if(pagination==null){pagination = new Pagination();}
		Pagination.threadLocal.set(pagination);
		List<MediaReport> beans=service.list(bean);
		model.addAttribute("bean", bean);
		model.addAttribute("page", Pagination.threadLocal.get());
		return new ModelAndView("/mediaReport/home","beans",beans);
	}
	@RequestMapping("/xw/saveXW.do")
	public ModelAndView addXWTo(@Param("id")Integer id,Model model) {
		if(id!=null){
			MediaReport bean=service.getMediaReportById(id);
			if(bean!=null){
				model.addAttribute("bean", bean);	
			}
		}
		return new ModelAndView("/mediaReport/newXW");
	}
	@RequestMapping("/xw/saveXW")
	public ModelAndView saveXW(MediaReport bean,Model model,HttpSession session) {
		service.saveXW(bean,session);
		return new ModelAndView("redirect:/xw/list");
	}
	@RequestMapping("/xw/operation")
	@ResponseBody
	public String operation(@Param("id")Integer id,@Param("type")Integer type,@Param("ids")String ids,HttpSession session,Model model ) {
		service.operation(id,type,ids,session);
		return "";
	}
	@RequestMapping("/json/titleRepetition")
	@ResponseBody
	public String titleRepetition(@Param("title") String title) {
		if(service.titleRepetition(title)){
			return "yes";
		}
		return "no";
	}
}
