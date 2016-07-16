package com.tuanche.web.zhoukan;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuanche.bean.admin.Article;
import com.tuanche.bean.admin.Employe;
import com.tuanche.bean.admin.Magazine;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.service.admin.MagazineService;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.upload.UpLoadUtil;
import com.tuanche.util.KevinUtil;


@Controller
@RequestMapping(value="/magazine")
public class MagazineController{
	@Autowired
	private MagazineService service;
	
	private static final Byte DELETED=-1;
	private static final Byte ONLINE=1;
	private static final Byte LINE=0;
	/**
	 * 修改人员状态
	 * @param model
	 * @param request
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/updatestatus")
	@ResponseBody
	public String deleteOne(Model model,HttpServletRequest request,HttpSession session,@Param("id")Integer id,@Param("status")Byte status){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		service.updateEmpStatus(status,employee.getId(),id);
		if(status==-1){
			String oldurl=service.selectEmployeById(id).getEmployePic();
			oldurl=oldurl.substring(oldurl.indexOf("zhoukan"),oldurl.length());
			KevinUtil.ftpDelImg(oldurl.trim(), "picFtpHost", "wendaftp.username", "wendaftp.password");
		}
		return "";		
	}
	/**
	 * 修改周刊状态
	 * @param model
	 * @param request
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/updateMagStatus")
	@ResponseBody
	public String updateMagStatus(Model model,HttpServletRequest request,HttpSession session,@Param("id")Integer id,@Param("status")Byte status){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		service.updateMagStatus(status,employee.getId(),id);
		return "";		
	}
	/**
	 * 修改文章状态
	 * @param model
	 * @param request
	 * @param session
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/updateartstatus")
	@ResponseBody
	public String updateartstatus(Model model,HttpServletRequest request,HttpSession session,@Param("id")Integer id,@Param("status")Byte status){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		service.updateArtStatus(status,employee.getId(),id);
		if(status==-1){
			String oldurl=service.selectArticleById(id).getPicture();
			oldurl=oldurl.substring(oldurl.indexOf("zhoukan"),oldurl.length());
			KevinUtil.ftpDelImg(oldurl.trim(), "picFtpHost", "wendaftp.username", "wendaftp.password");
		}
		return "";		
	}
	/**
	 * 进入修改周刊页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/toUpdateMagazine")
	public String toUpdateMagazine(Model model,@Param("id")Integer id){		
		model.addAttribute("magazine", service.selectMagazineById(id));
		return "zhoukan/toAddMagazine";
	}
	/**
	 * 进入修改人员页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/toUpdateEmploye")
	public String toUpdatePic(Model model,@Param("id")Integer id){		
		model.addAttribute("emp", service.selectEmployeById(id));
		return "zhoukan/toAddEmploye";
	}
	/**
	 * 进入修改文章页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/toUpdateArticle")
	public String toUpdateArticle(Model model,@Param("id")Integer id){		
		model.addAttribute("article", service.selectArticleById(id));
		model.addAttribute("magazines",service.selectMagazines());
		return "zhoukan/toAddArticle";
	}
	/**
	 * 周刊页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/magazineList")
	public String magazineList(Model model,Magazine magazine){
		Pagination pagination=magazine.getPage();
		if(pagination==null){
		pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		model.addAttribute("magazines",service.magazinesByPage(magazine));
		model.addAttribute("page", Pagination.threadLocal.get());
		return "zhoukan/magazineList";
	}
	/**
	 * 人员页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/employeList")
	public String employeList(Model model,Employe employe){
		Pagination pagination=employe.getPage();
		if(pagination==null){
		pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		model.addAttribute("emps",service.selectEmpByPage());
		model.addAttribute("page", Pagination.threadLocal.get());
		return "zhoukan/employeList";
	}
	/**
	 * 文章页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/articleList")
	public String articleList(Model model,Article article){
		Pagination pagination=article.getPage();
		if(pagination==null){
		pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		
		model.addAttribute("articles",service.articleByPage(article));
		model.addAttribute("magazines", service.selectMagazines());
		model.addAttribute("magazineId",article.getMagazineId());		
		model.addAttribute("page", Pagination.threadLocal.get());
		return "zhoukan/articleList";
	}
	/**
	 * 修改人员
	 * @param employe
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateEmploye")
	public String updatePic(Employe employe,Model model,HttpSession session,HttpServletRequest request){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		Integer updateId=employee.getId();
		if(employe.getId()!=null&&employe.getId()>0){
			//新增
			employe.setUpdateEmp(updateId);
			service.updatePicAll(employe,request);
		}else{
			//修改
			employe.setBuildEmp(updateId);
			employe.setUpdateEmp(updateId);
			service.addQuestionPic(employe,request);
		}
		return "redirect:/magazine/employeList";
	}
	/**
	 * 新增修改周刊
	 * @param magazine
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateMagazine")
	public String updateMagazine(Magazine magazine,Model model,HttpSession session,HttpServletRequest request){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		Integer updateId=employee.getId();
		if(magazine.getId()!=null&&magazine.getId()>0){
			//修改
			magazine.setUpdateEmp(updateId);
			service.updateMagazine(magazine);
		}else{
			//新增
			magazine.setMagazineStatus(LINE);
			magazine.setBuildEmp(updateId);
			magazine.setUpdateEmp(updateId);
			service.addMagazine(magazine);
		}
		return "redirect:/magazine/magazineList";
	}
	/**
	 * 修改文章
	 * @param employe
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateArticle")
	public String updateArtPic(Article article,Model model,HttpSession session,HttpServletRequest request){
		Employee employee=(Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		Integer updateId=employee.getId();
		if(article.getId()!=null&&article.getId()>0){
			//修改
			article.setUpdateEmp(updateId);
			service.updateArtPicAll(article,request);
		}else{
			//新增
			article.setBuildEmp(updateId);
			article.setUpdateEmp(updateId);
			service.addArticlePic(article,request);
		}
		return "redirect:/magazine/articleList";
	}
	/**
	 * @param request
	 * @param response
	 * @author liuhg
	 * @Description 图片上传
	 */
	@RequestMapping("/uploadPic")
	public void uploadPic(HttpServletRequest request,HttpServletResponse response){
		UpLoadUtil upLoadUtil=new UpLoadUtil();
		upLoadUtil.uploadPicNoFtp(request, response);
	}
	/**
	 * ajax校验期数是否存在
	 * @param model
	 * @param request
	 * @param session
	 * @param edit
	 * @return
	 */
	@RequestMapping(value="/check")
	@ResponseBody
	public String check(Model model,HttpServletRequest request,HttpSession session,@Param("edit")Integer edit,@Param("id")Integer id){
		return service.selectMagCountById(edit,id)+"";		
	}
	/**
	 * ajax校验排序是否存在
	 * @param model
	 * @param request
	 * @param session
	 * @param edit
	 * @return
	 */
	@RequestMapping(value="/check1")
	@ResponseBody
	public String check1(Model model,HttpServletRequest request,HttpSession session,@Param("sort")Integer sort,@Param("id")Integer id,@Param("magazineId")Integer magazineId){
		return service.selectArtCountById(sort,id,magazineId)+"";		
	}
	/**
	 * 压缩上传图片到本地
	 * @param request
	 * @param delSrc
	 * @return
	 */
	@RequestMapping(value="/json/uploadImg")
	@ResponseBody
	public String uploadImg(HttpServletRequest request,@Param("delSrc")String delSrc) {
		return KevinUtil.picUploadByWH(request, "topPicFile", delSrc, "zhoukan", 700, 0);
	}
	@RequestMapping(value="/json/uploadImg1")
	@ResponseBody
	public String uploadImg1(HttpServletRequest request,@Param("delSrc")String delSrc) {
		return KevinUtil.picUploadByWH(request, "topPicFile", delSrc, "zhoukan", 60, 60);
	}

}
