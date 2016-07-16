package com.tuanche.web.gcm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.admin.GmsCity;
import com.tuanche.bean.erp.TCrmCustomer;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.service.admin.GcmCityService;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.web.controller.BaseController;

@Controller
public class GcmController extends BaseController{
	
	@Autowired
	private GcmCityService cityService;
	
	
	@RequestMapping(value="/gcm/toList")
	public ModelAndView toList(Model model,HttpServletRequest request ,@Param("gmsCity")GmsCity gmsCity){
		if(gmsCity!=null){
			Pagination.threadLocal.set(gmsCity.getPage());
		}
		List<GmsCity> byPage = cityService.getByPage(gmsCity);
		model.addAttribute("list", byPage);
		model.addAttribute("page",Pagination.threadLocal.get());
		model.addAttribute("citys",GlobalConstants.districtMap);
		model.addAttribute("isList", "0");//标示列表|新增 
		if(gmsCity!=null && gmsCity.getCityId()!=null && gmsCity.getCityId() >0){//城市id  搜索
			model.addAttribute("sCityId", gmsCity.getCityId());
		}
        return new ModelAndView("/gcm/home");
	}
	
	@RequestMapping(value="/gcm/toAdd")
	public ModelAndView toAdd(Model model,HttpServletRequest request){
		model.addAttribute("brands",GlobalConstants.brandMap);
		model.addAttribute("citys",GlobalConstants.districtMap);
		return new ModelAndView("/gcm/add");
	}
	
	@RequestMapping(value="/gcm/add")
	public ModelAndView add(Model model,HttpServletRequest request ,
			HttpSession session,@Param("gmsCity")GmsCity gmsCity){
		//用户信息
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(employee!=null){
			gmsCity.setCreateUserId(employee.getId());
			gmsCity.setCreateUserName(employee.getEmpName());
		}
		int add = cityService.add(gmsCity,request);//返回团车会id
		return new ModelAndView("redirect:/gcm/toList");
	}
	
	@RequestMapping(value="/gcm/toUpdate")
	public ModelAndView toUpdate(Model model,HttpServletRequest request,@Param("id")Integer id){
		model.addAttribute("brands",GlobalConstants.brandMap);
		GmsCity gcmById = cityService.getGcmById(id);
		model.addAttribute("gcm", gcmById);
		model.addAttribute("citys",GlobalConstants.districtMap);
		return new ModelAndView("/gcm/add");
	}
	@RequestMapping(value="/gcm/update")
	public ModelAndView update(Model model,HttpServletRequest request ,
			HttpSession session,@Param("gmsCity")GmsCity gmsCity){
		//用户信息
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(employee!=null){
			gmsCity.setUpdateUserId(employee.getId());
			gmsCity.setUpdateUserName(employee.getEmpName());
		}
		cityService.updateGcm(gmsCity,request);
		return new ModelAndView("redirect:/gcm/toList");
	}
	
	@RequestMapping(value = "/gcm/onLineGcmCity")
	public void deleteGcmCity(Model model,HttpServletRequest request
			,HttpServletResponse response ,@Param("gcmId")Integer gcmId,@Param("isOnline")Integer isOnline){
		int deleteGcm = cityService.deleteGcm(gcmId,isOnline);//返回执行函数
		outJson(response, deleteGcm);
	}
	
	@RequestMapping(value = "/gcm/deleteGcmBrand")
	public void deleteGcmBrand(Model model,HttpServletRequest request
			,HttpServletResponse response ,@Param("gcmBrandId")Integer gcmBrandId){
		int deleteGcm = cityService.deleteGcmBrand(gcmBrandId);//返回执行函数
		outJson(response, deleteGcm);
	}
	
	@RequestMapping(value = "/gcm/deleteGcmContent")
	public void deleteGcmContent(Model model,HttpServletRequest request
			,HttpServletResponse response ,@Param("gcmContentId")Integer gcmContentId){
		int deleteGcm = cityService.deleteGcmContent(gcmContentId);//返回执行函数
		outJson(response, deleteGcm);
	}
	
	@RequestMapping(value = "/gcm/getCustomer")
	public void getCustomer(Model model,HttpServletRequest request
			,HttpServletResponse response ,@Param("customerName")String customerName){
		List<TCrmCustomer> byName = cityService.getByName(customerName);
		outJsonArray(response, byName);
	}
}
