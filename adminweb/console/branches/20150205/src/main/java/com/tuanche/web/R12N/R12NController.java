package com.tuanche.web.R12N;


import java.util.List;












import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.che.BrandGroupbuy;
import com.tuanche.bean.che.CarstyleDomain;
import com.tuanche.bean.che.CarstyleGroupbuy;
import com.tuanche.bean.che.Recommendation;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.mapper.che.read.BrandkReadMapper;
import com.tuanche.service.che.RecommendationService;
import com.tuanche.smc.common.page.impl.Pagination;

@Controller
public class R12NController {
	@Autowired
	private RecommendationService service;
	@Autowired
	private BrandkReadMapper brandReadMapper;
	
	@RequestMapping(value="/r12n/home")
	public ModelAndView R12NHome(Recommendation r12n,Model model) {
		Pagination pagination=r12n.getPage();
		if(pagination==null){pagination = new Pagination();}
		Pagination.threadLocal.set(pagination);
		List<Recommendation> r12ns= service.getR12NHome(r12n);
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("bean", r12n);
		model.addAttribute("citys", GlobalConstants.districtMap);
		model.addAttribute("brand",brandReadMapper.selectBrandAll());
		return new ModelAndView("r12n/r12nHome","beans",r12ns);
	}
	@RequestMapping(value="/r12n/add/r12n")
	public ModelAndView addR12N(Integer id,Model model) {
		if(id==null){
			model.addAttribute("citys", GlobalConstants.districtMap);
			model.addAttribute("beands",GlobalConstants.brandMap);
		}
		return new ModelAndView("r12n/addR12n","bean",id==null?null:service.getR12NByid(id));
	}
	@RequestMapping(value="/json/r12n/getBrand")
	@ResponseBody
	public List<Recommendation> getBrandBycityAll(@Param("cityId")Integer cityId) {
		return service.getGroupBrandAllBycity(cityId);
	}
	@RequestMapping(value="/json/r12n/getCar")
	@ResponseBody
	public List<Recommendation> getCarStyleBycityAll(@Param("cityId")Integer cityId,@Param("brandId")Integer brandId) {
		return service.getGroupCarStyleAllByCityAndBrand(cityId, brandId);
	}
	
	@RequestMapping(value="/json/r12n/getCarStyle")
	@ResponseBody
	public List<CarstyleDomain> getCarStyle(@Param("brandId")Integer brandId) {
		return service.getCarStyle(brandId);
	}
	
	@RequestMapping(value="/r12n/add/r12n.do")
	public String saveR12N(Recommendation r12n,HttpSession session) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		  if(sessionUser!=null&&sessionUser.getId()!=null){
			  r12n.setCreateUid(sessionUser.getId());
		service.saveR12N(r12n);
		  }
		return "redirect:/r12n/home";
	}
	//修改状态以及批量
	//1 上线，0 下线，-1 删除 -11 批量删除  3 添加页面
	@RequestMapping(value="/r12n/UpdateStatusOrbatch")
	@ResponseBody
	public int UpdateStatusOrbatch(HttpSession session,@Param("id")Integer id,@Param("ids")String ids,@Param("type")Integer type) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		  if(sessionUser!=null&&sessionUser.getId()!=null && type!=null){
			  if(type==-11){
				id=null;
				type=-1;
			  }else if(type==8){
				  id=null;
				  type=1;  
			  }else if(type==-8){
				  id=null;
				  type=0; 
			  }
			  service.UpdateStatusOrbatch(id,ids,type,sessionUser.getId());
		  }
		  return 200;
	}
}
