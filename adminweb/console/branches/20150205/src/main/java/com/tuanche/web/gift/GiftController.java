package com.tuanche.web.gift;

import java.io.File;

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

import com.tuanche.bean.che.GiftBean;
import com.tuanche.bean.che.PersonCase;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.service.che.GiftService;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.util.ZiXunDateUtil;
import com.tuanche.util.KevinUtil;

@Controller
public class GiftController {
	@Autowired
private GiftService service;
	
	/**
	 * 搜索，首页一体
	 * */
	@RequestMapping(value="/gift/home")
	public ModelAndView home(GiftBean bean,Model model) {
		Pagination pagination=bean.getPage();
		if(pagination==null){pagination = new Pagination();}
		Pagination.threadLocal.set(pagination);
		List<GiftBean> beans=service.home(bean);
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("bean", bean);
		model.addAttribute("giftClass",GlobalConstants.gifitClass);
		model.addAttribute("exchangeType",GlobalConstants.sysGiftExchangeType);
		model.addAttribute("giftSource",GlobalConstants.gift_source);
		return new ModelAndView("gift/giftList","beans",beans);
	}
	@RequestMapping(value="/gift/saveGift.do")
	public ModelAndView addGift(Model model,@Param("id") Integer id) {
		model.addAttribute("giftClass",GlobalConstants.gifitClass);
		model.addAttribute("exchangeType",GlobalConstants.sysGiftExchangeType);
		model.addAttribute("giftSource",GlobalConstants.gift_source);
		if(id==null){
			return new ModelAndView("gift/addGift");
		}
		GiftBean bean=service.findByid(id);
		return new ModelAndView("gift/addGift","bean",bean); 
	}
	@RequestMapping(value="/gift/saveGift")
	public String savegift(GiftBean bean,HttpSession session) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		  if(sessionUser!=null&&sessionUser.getId()!=null){
			 String imgPath=new String(bean.getImgUrl());
			  if(imgPath!=null&&imgPath.length()>0){
				  String path = session.getServletContext().getContextPath()+bean.getImgUrl();
					String fPath= session.getServletContext().getRealPath(path);
					if(new File(fPath).exists()){
				  //上传
				  KevinUtil.ftpUpload(session, imgPath, "gift/"+ZiXunDateUtil.getEndDir(), "wendaftp.username","wendaftp.password");
				  bean.setImgUrl(imgPath.replaceAll("/pic_tmp/", ""));
					}
				}
			  service.savegift(bean,sessionUser.getId());
		  }
		  return "redirect:/gift/home";
	}
	
	@RequestMapping("/json/gift/uploadImg")
	@ResponseBody
	public String uploadImg(HttpServletRequest request,@Param("delSrc")String delSrc) {
		return KevinUtil.picUploadByWH(request, "listPicFile", delSrc, "gift", 100, 100);
	}
	/****修改单个状态，或是批量状态*****/
	@RequestMapping("/gift/updateStatus")
	public String updateStatus(@Param("id")Integer id,@Param("type")Integer type,HttpSession session) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		  if(sessionUser!=null&&sessionUser.getId()!=null){
			  if(id!=null && type!=null ){
				  service.updateStatus(id,type,sessionUser.getId());
			  }
		  }
		  return "redirect:/gift/home";
	}
	@RequestMapping("/gift/updateStatus.do")
	@ResponseBody
	public String updateStatusByIds(@Param("type")Integer type,HttpSession session,@Param("ids")String ids) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		  if(sessionUser!=null&&sessionUser.getId()!=null){
			  if(ids!=null && type!=null ){
				  service.updateStatusFromIds(type,sessionUser.getId(),ids);
			  }
			  return "200";
			  }
		  return "";
	}
}
