package com.tuanche.web.pic;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.che.PicAtlasBean;
import com.tuanche.bean.che.PicPic;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.console.util.StringUtils;
import com.tuanche.mapper.che.read.BrandkReadMapper;
import com.tuanche.service.che.PicPicService;
import com.tuanche.service.che.SitesService;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.util.ZiXunDateUtil;
import com.tuanche.util.KevinUtil;
@Controller
public class PicPicController {
	@Autowired
private PicPicService service;
	@Autowired
private BrandkReadMapper brandReadMapper;
	
	@RequestMapping(value="/pic/pichome")
	public ModelAndView picpichome(PicPic bean,Model model) {
		Pagination pagination=bean.getPage();
		if(pagination==null){pagination = new Pagination();}
		Pagination.threadLocal.set(pagination);
		List<PicPic> pics=service.getPicAll(bean);
		//属性
		model.addAttribute("classIds", service.getClassId());
		model.addAttribute("propertyIds",service.getPropertyId());
		//model.addAttribute("citys", GlobalConstants.districtMap);
		model.addAttribute("brand",brandReadMapper.selectBrandAll());
		model.addAttribute("atlas",service.getAtlas());
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("bean", bean);
		return new  ModelAndView("/pic/picHome","beans",pics);
	}
	@RequestMapping(value="/pic/operation")
	@ResponseBody
	public int operation(PicPic bean,@Param("type")Integer type,HttpSession session,HttpServletResponse response) throws IOException {
		//type==1 修改bean ,type==-1删除
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		  if(sessionUser!=null&&sessionUser.getId()!=null){
			  service.updateBeanOrStatusOrInsert(bean,type,sessionUser.getId(),session);
				return new Random().nextInt(10);
					}else{
						return 11;
					}
	}
	@RequestMapping(value="/pic/operation.action")
	public ModelAndView findByidUpdateOrInsert(@Param("id")Integer id,Model model,Integer type) {
		PicPic bean=service.findByidUpdateOrInsert(id);
		model.addAttribute("classIds", service.getClassId());
		model.addAttribute("propertyIds",service.getPropertyId());
		model.addAttribute("citys", GlobalConstants.districtMap);
		model.addAttribute("brand",brandReadMapper.selectBrandAll());
			if(type==null){
				return new ModelAndView("/pic/addpic"); 	
			}else if(type==3){
				return new ModelAndView("/pic/update","bean",bean);	
			}else if(type==1 || type==4){
				return new ModelAndView("/pic/addpicYT","bean",bean);
		}
		return new ModelAndView("/pic/update","bean",bean);
	}
	@RequestMapping(value="/pic/operation.do")
	public String updateOrInset(PicPic bean,Model model,Integer type,HttpSession session) {
		//type==-1 删除  ==1 找回 ==3 大修改 ==4 添加==5修改
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		  if(sessionUser!=null&&sessionUser.getId()!=null){
			  service.updateBeanOrStatusOrInsert(bean,type,sessionUser.getId(),session);
		 }
		  return "redirect:/pic/pichome";
	}
	@RequestMapping(value="/json/zipUpload")
	public void zip(HttpServletRequest request,String picId,HttpSession session,HttpServletResponse response,@Param("type")String type) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgFile = multipartRequest.getFile(type);
		String path="/pic_tmp/piclibrary/"+ZiXunDateUtil.getEndDir();
		Map<String,String> map=KevinUtil.zipDecompression(imgFile.getInputStream(), path,session);
		String fPath= session.getServletContext().getRealPath(path);
		service.cutImage(map.get("fileName").trim(),new File(fPath),fPath);
		KevinUtil.sentResponseData(response, new String(map.get("fileName").trim()+"-lgh-"+map.get("error").trim()));
	}
	//保存到数据库
	@RequestMapping(value="/pic/savaPicNames")
	public String savaPicNames(PicPic bean,Model model,HttpSession session){
		return "";
	}
	@RequestMapping(value="/json/uploadFile")
	@ResponseBody
	public String uploadFile(HttpServletRequest request,HttpSession session,String delSrc) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			return KevinUtil.picUploadCompress(multipartRequest, "boxFile", delSrc, "/piclibrary",2);
		}
		
	
	/**
	 * 简便修改图片，描述，以及标题
	 * */
	@RequestMapping(value="/pic/simple/update")
	@ResponseBody
	public int simpleUpdate(HttpSession session,PicPic bean) throws Exception {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		 if(sessionUser!=null&&sessionUser.getId()!=null){
		if(bean!=null && bean.getTurl()!=null && bean.getTurl().length()>0){
			KevinUtil.ftpUploads(session, bean.getTurl(), ZiXunDateUtil.getEndDir(), "Pics.user", "Pics.pass",new String[]{"s.jpg","m.jpg","b.jpg"},bean.getCollectionId()==null ?0:bean.getCollectionId());
		}
		service.simpleUpdate(bean,sessionUser.getId());
		  }
		return 0;
	}
	
	@RequestMapping(value="/pic/savePic")
	public String savePic(HttpServletRequest request,PicPic bean,HttpSession session) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		  if(sessionUser!=null&&sessionUser.getId()!=null){
		String[]langtype =request.getParameterValues("boxPicTitle");
		service.savePic(session,langtype,bean,sessionUser.getId());
	}
		  return "redirect:/pic/pichome";
	}
	
	@RequestMapping("/json/getColl")
	@ResponseBody
	public List<PicAtlasBean> getcoll() {
		return service.getcoll();
	}
	@RequestMapping("/json/getCollById")
	@ResponseBody
	public PicAtlasBean getCollById(@Param("id")Integer id) {
		if(id!=null){
			return service.getcollByid(id);
		}
		return null;
	}
	
	@RequestMapping("/json/getCollByName")
	@ResponseBody
	public List<PicAtlasBean> getCollByName(@Param("collName") String collName) {
		if(collName!=null){
			 List<PicAtlasBean> list= service.getcollByName(collName);
			 if(list!=null &&list.size()>0){
				 return list;
			 }
			return null;
			}
		return null;
	}
	@RequestMapping("/pic/updateCollection")
	@ResponseBody
	private int updateCollection(HttpSession session,PicAtlasBean bean) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		  if(sessionUser!=null&&sessionUser.getId()!=null){
		service.updateCollection(bean,sessionUser.getId());
		  }
		return 0;
	}
	@RequestMapping("/json/ImgDel")
	@ResponseBody
	public int ImgDel(@Param("name") String name,HttpSession session) {
		if(StringUtils.isNotEmptyString(name)){
			String path="/pic_tmp/piclibrary/"+ZiXunDateUtil.getEndDir();
			String fPath= session.getServletContext().getRealPath(path);
			if(KevinUtil.getFrequency(name,",") > 1){
				String names[] =name.split(",");
				for (String string : names) {
				if(new File(fPath,string).exists()){
					new File(fPath,string).delete();
					new File(fPath,string.replaceAll("_s.jpg", "_b.jpg")).delete();
					new File(fPath,string.replaceAll("_s.jpg", "_m.jpg")).delete();
				}
				}
			}else{
			File file=new File(fPath,name);
			if(file.exists()){
				file.delete();
				new File(fPath,name.replaceAll("_s.jpg", "_b.jpg")).delete();
				new File(fPath,name.replaceAll("_s.jpg", "_m.jpg")).delete();
			}
		}
		}
		return 0;
	}
	@RequestMapping(value="/pic/savePic.do")
	public String coverUIniqueness(@Param("collid")Integer collid, @Param("id")Integer id) {
		service.coverUIniqueness(collid,0);
		service.updaeCover(id);
		 return "redirect:/pic/pichome";
	}
}
