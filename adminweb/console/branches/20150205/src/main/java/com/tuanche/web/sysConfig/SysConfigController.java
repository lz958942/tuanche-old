package com.tuanche.web.sysConfig;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.che.SysConfig;
import com.tuanche.service.che.SysConfigService;
import com.tuanche.smc.common.page.impl.Pagination;

@Controller
@RequestMapping("/sysConfig")
public class SysConfigController {

	@Autowired
	private SysConfigService sysConfigService;
	
	/**
	 * 搜索条件首页一体
	 * **/
	@RequestMapping("/home")
	public ModelAndView sysConfigHome(@Param("sysConfig")SysConfig sysConfig,@Param("type") Integer type,Model model) {
		Pagination pagination=sysConfig.getPage();
		if(pagination==null){
		pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		model.addAttribute("page", Pagination.threadLocal.get());
		if(sysConfig!=null){
		model.addAttribute("sysConfig", sysConfig);
		}
		List<SysConfig> list=sysConfigService.home(sysConfig,type);
		return new ModelAndView("/sysConfig/list","sysConfiglist",list);
	}
	@RequestMapping("/addConfig")
		public ModelAndView addConfig() {
			return new ModelAndView("/sysConfig/addConfig");
		}
	@RequestMapping("/saveConfig")
	public ModelAndView saveConfig(@Param("sysConfig")SysConfig sysConfig,Model model,HttpSession session) {
		if(sysConfig!=null){
		sysConfigService.saveConfig(sysConfig,session);
		}
		return new ModelAndView("redirect:/sysConfig/home");
	}
		//修改前
		@RequestMapping("/updateBefore")
		public ModelAndView updateBefore(@Param("id")Integer id) {
			if(id!=null && id>0){
			SysConfig config= sysConfigService.updateBefore(id);
			return new ModelAndView("/sysConfig/addConfig","config",config);
			}
			return new ModelAndView("/sysConfig/addConfig");
		}
		@RequestMapping("/verification")
		public void verification(@Param("key")String key,@Param("code") String code,HttpServletResponse response) {
			if(key!=null && key.length()>0 &&!"NULL".equals(key) &&code!=null && code.length()>0 && !"NULL".equals(code)){
					Boolean type=sysConfigService.verification(key,code);
					if(type){
						sentResponseData(response,"yes");
					}else{
						sentResponseData(response,"no");
					}
				}else{
					sentResponseData(response,"1");	
				}
		}
		private void sentResponseData(HttpServletResponse response,String data){
			response.setContentType("text/html; charset=UTF-8");
	        response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
	        PrintWriter out = null;
			try {
				out = response.getWriter();
				out.println(data);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				out.flush();
				out.close();
			}
		}
		
		/****************************编辑快捷回复管理模块*********************************************/
		@RequestMapping("/edit/Home")	
		public ModelAndView editHome(@Param("sysConfig")SysConfig sysConfig,Model model) {
			Pagination pagination=sysConfig.getPage();
			if(pagination==null){
			pagination = new Pagination();
			}
			Pagination.threadLocal.set(pagination);
			model.addAttribute("page", Pagination.threadLocal.get());
			if(sysConfig!=null){
			model.addAttribute("sysConfig", sysConfig);
			}
			List<SysConfig> list=sysConfigService.home(sysConfig,3);
			return new ModelAndView("/shortcut/list","sysConfiglist",list);
			}
		@RequestMapping("/edit/addShortcut")	
		public ModelAndView addShortcut(@Param("id")Integer id) {
			if(id!=null && id>0){
				SysConfig config= sysConfigService.updateBefore(id);
				return new ModelAndView("/shortcut/addConfig","config",config);
				}
				return new ModelAndView("/shortcut/addConfig");
		}
		@RequestMapping("/saveConfig.do")
		public ModelAndView saveEditConfig(@Param("sysConfig")SysConfig sysConfig,Model model,HttpSession session) {
			if(sysConfig!=null){
			sysConfigService.saveEditConfig(sysConfig,session);
			}
			return new ModelAndView("redirect:/sysConfig/edit/Home");
		}
}
