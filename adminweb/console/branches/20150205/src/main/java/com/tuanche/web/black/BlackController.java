package com.tuanche.web.black;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.admin.Black;
import com.tuanche.bean.che.SysConfig;
import com.tuanche.mapper.che.read.SysConfigReadMapper;
import com.tuanche.service.admin.BlackService;
import com.tuanche.smc.common.Common;
import com.tuanche.smc.common.page.impl.Pagination;

@Controller
@RequestMapping("/black")
public class BlackController {
	@Autowired
private BlackService blackService;
	@Autowired
private SysConfigReadMapper configReadMapper;
	/**
	 * 搜索方法与首页相同
	 * */
	@RequestMapping("/search")
	public ModelAndView search(@Param("black")Black black,Model model) {
		Pagination pagination=black.getPage();
		if(pagination==null){
		pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		model.addAttribute("page", Pagination.threadLocal.get());
		List<Black> blist=blackService.search(black);
		List<String>keys=new ArrayList<String>();
		keys.add("blackType");
		
		List<SysConfig> configs=configReadMapper.getCodeByKey(keys);
		if(configs!=null &&configs.size()>0){
		model.addAttribute("configs",configs);
		}
		model.addAttribute("editers", Common.getEditers());
		model.addAttribute("blackBean", black);
		return new ModelAndView("/black/list","list",blist);
	}
	@RequestMapping("/ToAddBlack")
	public ModelAndView ToAddBlack(Model model) {
		List<String>keys=new ArrayList<String>();
		keys.add("blackType");
		
		List<SysConfig> configs=configReadMapper.getCodeByKey(keys);
		if(configs!=null &&configs.size()>0){
		model.addAttribute("configs",configs);
		}
		return new ModelAndView("/black/addBlack","configs",configs);
	}
	@RequestMapping("/ToUpdateBlack")
	public ModelAndView ToUpdateBlack(@Param("id")Integer id,Model model) {
		List<String>keys=new ArrayList<String>();
		keys.add("blackType");
		List<SysConfig> configs=configReadMapper.getCodeByKey(keys);
		if(id!=null){
			Black black=blackService.selectById(id);
			model.addAttribute("blackBean",black);
			if(configs!=null &&configs.size()>0){
				model.addAttribute("configs",configs);
				}
		}
		return new ModelAndView("/black/addBlack","configs",configs);
	}
	@RequestMapping("/saveBlack")
	public ModelAndView saveBlack(@Param("black")Black black,Model model,HttpSession session) {
		blackService.saveBlack(black,session);
		return new ModelAndView("redirect:/black/search");
	}
	@RequestMapping("/verifyWord")
	private void verifyWord(@Param("word")String word,@Param("type")Integer type,HttpServletResponse response) {
		if(blackService.verifyWord(word,type)){
			//有数据
			sentResponseData(response,"no");
		}else{
			sentResponseData(response,"yes");
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
	
}
