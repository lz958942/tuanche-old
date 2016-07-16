package com.tuanche.smc.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuanche.smc.common.Common;
import com.tuanche.smc.domain.base.NewsClassify;

@Controller
@RequestMapping(value = "/local")
public class LocalController extends BaseController{
	/**
	 * @param model
	 * @param pid地标的父亲id
	 * @return 地标的信息集合
	 */
	@RequestMapping(value = "/ajax/zone")
	public String getCityDist(Model model,HttpServletResponse response) {
		sentResponseData(response, gson.toJson(Common.dists));
		return null;
	}
	
	/** 
	* @author yangzs
	* @Title: getClass 
	* @Description: 获取所有资讯类别
	* @param @param response     
	* @return void    
	* @throws 
	*/
	@RequestMapping(value = "/ajax/class")
	public void getClass(HttpServletResponse response) {
	    List<NewsClassify> newsClassifies = Common.newsClassifies;
        sentResponseData(response, gson.toJson(newsClassifies));
	}
	/** 
	 * @author yangzs
	 * @Title: getClass 
	 * @Description: 获取所有车型类别
	 * @param @param response     
	 * @return void    
	 * @throws 
	 */
	@RequestMapping(value = "/ajax/carstyles")
	public void getCarstyles(HttpServletResponse response) {
	    sentResponseData(response, gson.toJson(Common.carStyles));
	}
	
}
