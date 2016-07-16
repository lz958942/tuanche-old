package com.tuanche.cms.web;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tuanche.cms.util.GlobalConstants;;

@Controller
@RequestMapping(value="/gson")
public class CommonController {
	@RequestMapping(value="/getStyle/{brandId}",method=RequestMethod.GET)
	public Model getStyle(Model model,@PathVariable(value="brandId") int brandId){
		model.addAttribute("infos", GlobalConstants.styles.get(brandId));
		return  model;
	}
	@RequestMapping(value="/getCar/{pId}",method=RequestMethod.GET)
	public Model getCar(Model model,@PathVariable(value="pId") int pId){
		model.addAttribute("infos", GlobalConstants.cars.get(pId));
		return  model;
	}
}
