package com.tuanche.cms.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuanche.cms.bean.Plate;
import com.tuanche.cms.bean.Template;
import com.tuanche.cms.bean.ZiXunClass;
import com.tuanche.cms.common.page.impl.Pagination;
import com.tuanche.cms.service.PlateService;
import com.tuanche.cms.service.TemplateService;

@Controller
@RequestMapping(value = "/plate")
public class PlateController {

	@Autowired
	private PlateService plateService;
	
	@Autowired
	private TemplateService templateService;
	
	@RequestMapping(value = "/toList")
	public String toList(Model model,@ModelAttribute("plate")Plate plate){
		Pagination page = plate.getPage();
		Pagination.threadLocal.set(page);
		List<Plate> plateByPage = plateService.getPlateByPage(plate);
		Pagination pagination = Pagination.threadLocal.get();
		model.addAttribute("page", pagination);
		model.addAttribute("plateList", plateByPage);
		model.addAttribute("plate", plate);
		return "/template/plate_list";
	}
	
	@RequestMapping(value = "/toAdd")
	public String toAdd(Model model ){
		List<Template> allTemplate = templateService.getAllTemplate();
		model.addAttribute("templateList", allTemplate);
		List<ZiXunClass> allZiXunClass = plateService.getAllZiXunClass();
		model.addAttribute("zixunClass", allZiXunClass);
		return "/template/plate_new";
	}
	
	@RequestMapping(value = "/add")
	public String add(Model model, @ModelAttribute("plate")Plate plate){
		plateService.addPlate(plate);
		return "redirect:/plate/toList?cityId="+plate.getCityId();
	}
	
	@RequestMapping(value = "/toUpdate")
	public String toUpdate(Model model ,Integer id){
		Plate plateById = plateService.getPlateById(id);
		model.addAttribute("plate", plateById);
		List<Template> allTemplate = templateService.getAllTemplate();
		model.addAttribute("templateList", allTemplate);
		List<ZiXunClass> allZiXunClass = plateService.getAllZiXunClass();
		model.addAttribute("zixunClass", allZiXunClass);
		return "/template/plate_new";
	}
	
	@RequestMapping(value = "/update")
	public String update(Model model, @ModelAttribute("plate")Plate plate){
		Integer dataType = plate.getDataType();
		if(dataType!=Plate.data_type_zixun && dataType!=Plate.data_type_car){//资讯  车型  没有数据类型   设 -1
			plate.setDataTypeClass(-1);
		}
		plateService.updatePlate(plate);
		return "redirect:/plate/toList?cityId="+plate.getCityId();
	}
	
	@RequestMapping(value = "/delete")
	public String delete(Model model, Integer id){
		Plate plate = new Plate();
		plate.setId(id);
		plate.setDeleteFlay(2);
		plateService.updatePlate(plate);
		return "redirect:/plate/toList";
	}
	
}
