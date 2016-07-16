package com.tuanche.web.personCase;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.che.PersonCase;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.info.util.CommonUtil;
import com.tuanche.service.che.PersonCaseService;
import com.tuanche.service.che.ReviewService;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.util.KevinUtil;

@Controller
public class personCaseController {
	@Autowired
	private PersonCaseService service;
	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping("/PE/PEList")
	public ModelAndView PEList(PersonCase bean,Model model) {
		Pagination pagination=bean.getPage();
		if(pagination==null){pagination = new Pagination();}
		Pagination.threadLocal.set(pagination);
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("citys", GlobalConstants.districtMap);
		model.addAttribute("brand",reviewService.getBrandNameId());
		List<PersonCase> beanList=service.PEListByCondition(bean);
		model.addAttribute("bean", bean);
		return new ModelAndView("/PE/home","beanList",beanList);
	}
	@RequestMapping("/PE/savePE.do")
	public String addPETo(PersonCase bean,Model model) {
		model.addAttribute("citys", GlobalConstants.districtMap);
		model.addAttribute("brand",reviewService.getBrandNameId());
		return "/PE/newPE";
	}
	@RequestMapping("/json/PE/picUpload")
	@ResponseBody
	public String picUpload(HttpServletRequest reques,@Param("delSrc")String delSrc,@Param("picId")String picId) {
		String path=KevinUtil.picUpload(reques,picId,delSrc,"PE");
		return path;
	}
	@RequestMapping("/PE/savePE")
	public String savePE(PersonCase bean,Model model,HttpSession session) {
		service.sava(bean,session);
		return "redirect:/PE/PEList";
	}
	@RequestMapping("/PE/operation")
	public ModelAndView operation(@Param("id")Integer id,@Param("type")Integer type,@Param("ids")String ids,HttpSession session,Model model ) {
		if(id !=null ){
			if(type !=null){
				PersonCase case1=service.operation(id,type);
				if(case1==null){
					//不是修改查询
					return new ModelAndView("redirect:/PE/PEList");
			}else{
				//是修查询
				model.addAttribute("citys", GlobalConstants.districtMap);
				model.addAttribute("brand",reviewService.getBrandNameId());
				return new ModelAndView("/PE/newPE","bean",case1);
			}
		}}else{
			service.operation(session,type, ids);
	}
		return new ModelAndView("redirect:/PE/PEList");
}
	@RequestMapping("/json/preview")
	public ModelAndView preview(@Param("id")Integer id) {
		if(id!=null){
			PersonCase case1=service.operation(id,1);
			return new ModelAndView("","bean",case1);
		}
		return new ModelAndView("");
	}
}
