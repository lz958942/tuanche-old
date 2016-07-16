package com.tuanche.smc.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.read.biff.BiffException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tuanche.bean.che.CarStyle;
import com.tuanche.commons.util.Resources;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.service.KeywordService;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.smc.common.Common;
import com.tuanche.smc.common.Globals;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.domain.zixun.ZiXun;
import com.tuanche.smc.persistence.read.admin.zixun.ZiXunReadMapper;
import com.tuanche.smc.persistence.write.admin.zixun.ZiXunWriteMapper;
import com.tuanche.smc.service.ZiXunService;
import com.tuanche.smc.util.FromZixunSta;
import com.tuanche.smc.util.ImageUtil;
import com.tuanche.smc.util.ZiXunDateUtil;
import com.tuanche.upload.FtpUtil;

;
@Controller
@RequestMapping(value = "/zixun")
public class ZiXunController extends BaseController {
	@Resource
	private ZiXunService zixunService;
	@Resource
	private ZiXunWriteMapper ziXunWriteMapper;
	@Resource
	private ZiXunReadMapper ziXunReadMapper;
	
	@RequestMapping(value = "/home")
	public String zixunHome(HttpServletRequest request, HttpSession session, ZiXun empsearchZixun,Model model) {
		//清空标识符 0 清空
		String mkg=request.getParameter("mkg");
		//发布取消发布标识符 0，获得表单搜索项状态
		String marking=request.getParameter("marking");
		//发布待发布
		String  tabfu=request.getParameter("tabfu");
		if(marking != null && "0".equals(marking)){
		return this.searchZixun(session, model, empsearchZixun);
	}
		//清空
		if (mkg != null && "0".equals(mkg)) {
			ZiXun searchZixun=new ZiXun();
			searchZixun.setStatus(3);
			return this.searchZixun(session, model, searchZixun);
		}
		//修改
		ZiXun searchZixun = (ZiXun) session.getAttribute("zixunState");
		if (searchZixun != null) {
			// 保存时的状态
			session.removeAttribute("zixunState");
			searchZixun.setId(null);
		} else {
			searchZixun = new ZiXun();
			searchZixun.setStatus(3);
			searchZixun.setStartDate(ZiXunDateUtil.getDateToString());
			searchZixun.setEndDate(ZiXunDateUtil.getEndDateToString());
			Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
			if (sessionUser != null) {
				// 列表显示编辑人
				if(tabfu!=null&&tabfu.length()>0&&!"123".equals(tabfu)){
					searchZixun.setEditorId(sessionUser.getId());
				}
				if(tabfu==null){
					searchZixun.setEditorId(sessionUser.getId());
				}
			}else{
				searchZixun.setEditorId(0);
			}
		}
		model.addAttribute("brandList", GlobalConstants.brandList);
		model.addAttribute("carStyleList", GlobalConstants.carStyleList);
		return this.searchZixun(session, model, searchZixun);
	}

	@RequestMapping(value = "/search")
	public String searchZixun(HttpSession session, Model model,
			@ModelAttribute("searchZixun") ZiXun searchZixun) {

		Pagination page = searchZixun.getPage();
		Pagination.threadLocal.set(page);
		// 用来存放查询条件
		Employee sessionUser = (Employee) session
				.getAttribute(GlobalConstants.SESSION_EMP);
		List<String> conditions = new ArrayList<String>();

		if (searchZixun.getId() != null && searchZixun.getId() > 0) {
			conditions.add("t.id = '" + searchZixun.getId() + "'");
		}

		if (searchZixun.getKeyword() != null
				&& !"".equals(searchZixun.getKeyword())) {
			conditions.add("t.keyword like '%" + searchZixun.getKeyword()
					+ "%' ");
		}

		if (searchZixun.getTitle() != null
				&& !"".equals(searchZixun.getTitle())) {
			conditions.add("t.title like '%" + searchZixun.getTitle() + "%' ");
		}
		if (searchZixun.getStatus() != 3) {
			conditions.add("t.status = " + searchZixun.getStatus());
		}
		//修改人
		if (searchZixun.getUpdateEditorId()>0) {
			conditions.add("t.update_editor_id = " + searchZixun.getUpdateEditorId());
		}
		//发布人
		if (searchZixun.getPublishEditorId()>0) {
			conditions.add("t.publish_editor_id = "+searchZixun.getPublishEditorId());
		}
		//品牌
		if (StringUtils.isNotEmpty(searchZixun.getBrandId())) {
			conditions.add("t.brand_id = "+searchZixun.getBrandId());
		}
		//车型
		if (StringUtils.isNotEmpty(searchZixun.getStyleId())) {
			conditions.add("t.style_id = "+searchZixun.getStyleId());
		}
		// 有图片
		if (searchZixun.getListPic() != null
				&& "1".equals(searchZixun.getListPic())) {
			conditions.add("t.list_pic != '' AND t.`list_pic` IS NOT NULL ");
		}
		// 没有图片
		if (searchZixun.getListPic() != null
				&& "2".equals(searchZixun.getListPic())) {
			conditions.add("(t.list_pic IS NULL OR t.list_pic ='')");
		}
		if (!StringUtils.isBlank(searchZixun.getStartDate())
				&& !"起始日期".equals(searchZixun.getStartDate())) {
			if (!StringUtils.isBlank(searchZixun.getEndDate())
					&& !"终止日期".equals(searchZixun.getEndDate())) {
				conditions.add("t.create_time > '" + searchZixun.getStartDate()
						+ "' AND t.create_time < '" + searchZixun.getEndDate()
						+ "'");
			} else {
				conditions.add("t.create_time > '" + searchZixun.getStartDate()
						+ "'");
			}
		} else {
			searchZixun.setStartDate("起始日期");
			if (!StringUtils.isBlank(searchZixun.getEndDate())
					&& !"终止日期".equals(searchZixun.getEndDate())) {
				conditions.add("t.create_time < '" + searchZixun.getEndDate()
						+ "'");
			} else {
				searchZixun.setEndDate("终止日期");
			}
		}

		if (searchZixun.getCityId() > 0) {
			conditions.add("t.city_id = " + searchZixun.getCityId());
		}

		if (searchZixun.getEditorId() > 0) {
			conditions.add("t.editor_id = " + searchZixun.getEditorId());
		}

		if (searchZixun.getClassId() > 0) {
			conditions.add("t.class_id = " + searchZixun.getClassId());
		}
		List<ZiXun> zixuns = null;
		if (searchZixun.getHyperlink() == Globals.TOPIC_ALL_TODAY) {
			zixuns = zixunService.getAllTodayZixunByPage();
		} else if (searchZixun.getHyperlink() == Globals.TOPIC_ALL_YESTERDAY) {
			zixuns = zixunService.getAllYesterdayZixunByPage();
		} else if (searchZixun.getHyperlink() == Globals.TOPIC_ALL_MY_TODAY) {
			zixuns = zixunService.getAllMyTodayZixunByPage(sessionUser.getId());
		} else if (searchZixun.getHyperlink() == Globals.TOPIC_ALL_MY_YESTERDAY) {
			zixuns = zixunService.getAllMyYesterdayZixunByPage(sessionUser
					.getId());
		} else if (searchZixun.getHyperlink() == Globals.TOPIC_ALL_MY) {
			zixuns = zixunService.getAllMyZixunByPage(sessionUser.getId());
		} else {
			zixuns = zixunService.getAllZixunByPage(conditions);
		}
		//点击总数
		model.addAttribute("clickSum", zixunService.getClickSum(conditions));
		model.addAttribute("brandList", GlobalConstants.brandList);
		model.addAttribute("carStyleList", GlobalConstants.carStyleList);
		model.addAttribute("zixuns", zixuns);
		model.addAttribute("page", page);
		model.addAttribute("searchZixun", searchZixun);
		model.addAttribute("actionUrl", "/zixun/search");
		model.addAttribute("editers", Common.getEditers());
		
		return "zixun/_home";
	}

	/**
	 * 申请新建一个资讯
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/newZixunProperties", method = RequestMethod.GET)
	public String newTopicProperties(HttpServletRequest request, Model model) {
		model.addAttribute("newZixun", true);
		return "zixun/_newZixunProperties";
	}

	/**
	 * @author yangzs
	 * @Title: nextZixunProperties
	 * @Description: 资讯下一步
	 * @param @param model
	 * @param @param request
	 * @param @param zixun
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/nextZixunProperties", method = RequestMethod.POST)
	public String nextZixunProperties(Model model, HttpServletRequest request,
			@ModelAttribute("zixun") ZiXun zixun) {
		model.addAttribute("searchType", "loc");
		model.addAttribute("zixun", zixun);
		// zixunService.saveZixun(zixun);
		saveOrUpdateZixun(request.getSession(), zixun);
		model.addAttribute("newZixun", true);
		return "zixun/_houseHotWord";
	}

	/**
	 * @author yangzs
	 * @Title: saveZixun
	 * @Description: 保存
	 * @param @param request
	 * @param @param session
	 * @param @param zixun
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/saveZixun", method = RequestMethod.POST)
	public String saveZixun(HttpServletRequest request, HttpSession session,
			@ModelAttribute("zixun") ZiXun zixun) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		zixun.setEditorId(sessionUser.getId());
		zixunService.addZixunHotWord(zixun);
		return "redirect:/zixun/home";
	}

	/**
	 * @author yangzs
	 * @Title: getZixunProperties
	 * @Description: 获取资讯保存当前状态
	 * @param @param model
	 * @param @param id
	 * @param @param isPreStep
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping(value = "/getZixunProperties")
	public String getZixunPropertiesstu(Model model, String isPreStep,
			ZiXun status, HttpServletRequest request, HttpSession session) {
		// 保存修改前的状态
		ZiXun zixun;
		if (status != null && status.getId() != null) {
			zixun = zixunService.getZixunById(status.getId());
			// 保存状态
			if(zixun != null) {
				// int satid=status.getId();
				// status.setId(null);
				session.setAttribute("zixunState", status);

				model.addAttribute("zixun", zixun);
				model.addAttribute("newZixun", false);
				if (isPreStep != null) {
					model.addAttribute("newZixun", true);
				}
				return "zixun/_newZixunProperties";
			}
		}
		return "redirect:/zixun/home";
	}

	@RequestMapping(value = "/preStep")
	public String getZixunProperties(Model model,
			@ModelAttribute("zixun") ZiXun zixun) {

		model.addAttribute("zixun", zixun);
		model.addAttribute("newZixun", true);
		return "zixun/_newZixunProperties";
	}

	/**
	 * @author yangzs
	 * @Title: saveZixunProperties
	 * @Description: 保存资讯
	 * @param @param model
	 * @param @param session
	 * @param @param zixun
	 * @param @return
	 * @return String
	 * @throws
	 */
	// 保存
	@RequestMapping(value = "/saveZixunProperties", method = RequestMethod.POST)
	public String saveZixunProperties(Model model, HttpSession session,
			@ModelAttribute("zixun") ZiXun zixun) {
		saveOrUpdateZixun(session, zixun);
		return "redirect:/zixun/home";
	}

	/**
	 * @author yangzs
	 * @Title: saveOrUpdateZixun
	 * @Description: 根据是否具有id属性选择更新或者保存资讯
	 * @param @param session
	 * @param @param zixun
	 * @return void
	 * @throws
	 */
	private void saveOrUpdateZixun(HttpSession session, ZiXun zixun) {
		if (zixun.getId() != null) {
			zixunService.updateZixunProperties(zixun,session);
			//修改咨询图片
			
		} else {
			System.out.println("保存资讯");
			Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
			zixun.setEditorId(sessionUser.getId());
			zixunService.saveZixun(zixun,session);
		}
	}

	@RequestMapping(value = "/getZixunHouseInfo")
	public String getZixunHouseInfo(Model model, int id) {
		ZiXun zixun = zixunService.getZixunById(id);
		zixun.setHotWords(zixunService.getHotWordsByZixunId(zixun.getId()));
		model.addAttribute("searchType", "loc");
		model.addAttribute("zixun", zixun);
		model.addAttribute("newZixun", false);
		return "zixun/_houseHotWord";
	}

	@RequestMapping(value = "/saveZixunHouseInfo", method = RequestMethod.POST)
	public String saveZixunHouseInfo(HttpSession session,
			@ModelAttribute("zixun") ZiXun zixun) {
		zixunService.updateZixunHouseInfo(zixun);
		return "redirect:/zixun/home";
	}

	// 发布
	@RequestMapping(value = "/deployZixun")
	public String deployZixun(HttpServletResponse response, Model model, HttpSession session,
			int[] id, boolean type) {
		if (id.length == 1) {
			zixunService.deployZixun(id[0], type);
			Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
			if(sessionUser!=null){
			ziXunWriteMapper.updateZixunPublishEditorId(sessionUser.getId(),id[0]);
			}
		}
		if (id.length > 1) {
			zixunService.batchUpdateZixunstatus(id, type ? 1 : 0);
			Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
			if(sessionUser!=null){
				ziXunWriteMapper.updateZixunUpEditorIds(id, sessionUser.getId());
			}
		}
		sentResponseInfo(response, "{info:true}");
		return null;
	}

	@RequestMapping(value = "/updateZixuns")
	public String updateZixuns(HttpServletResponse response, Model model,
			int[] id, int status) {
		zixunService.batchUpdateZixunstatus(id, status);
		sentResponseInfo(response, "");
		return null;
	}

	@RequestMapping(value = "/delZixun")
	public String delTopic(HttpServletResponse response, Model model, int[] id) {
		if (id.length == 1) {
			zixunService.delZixun(id[0]);
		}
		if (id.length > 1) {
			zixunService.batchUpdateZixunstatus(id, -1);
		}
		sentResponseInfo(response, "{info:true}");
		return null;
	}

	/**
	 * @author yangzs
	 * @Title: uploadImgForlistPic
	 * @Description: 将用户上传图片一张原图保存为name_b.jpg 另一张存为name_s.jpg 而数据库则只存储name
	 *               旨在满足不同页面对图片的需求
	 * @param request
	 * @param response
	 * @return void
	 * @throws
	 */
	@RequestMapping(value = "/uploadImg")
	public void uploadImgForlistPic(HttpServletRequest request,HttpServletResponse response ,FromZixunSta fzs) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imgFile = multipartRequest.getFile("listPicFile");
		String parentPath = Resources.getString("filePath");
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		String dailyPath = simpleDateFormat.format(new Date()) + "/";
		String uname = System.currentTimeMillis() + ""+ new Random().nextInt(10000);
		String ext = getFileExt(imgFile.getOriginalFilename());
		String imgName = uname + "_b" + ext;
		String listImgName = uname + "_s" + ext;
		String path = parentPath + dailyPath;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		String fullPath = path + imgName;

		String servletPath = request.getContextPath();
		String realPath = request.getSession().getServletContext().getRealPath(servletPath);
		
		if(fzs.getTab()!=null&&fzs.getTab().length()>0&&"10".equals(fzs.getTab())){
			ZiXun ziXun=null;
			if(isNumeric(fzs.getIds())){
				ziXun=zixunService.getZixunById(Integer.valueOf(fzs.getIds()));
				
				if(ziXun!=null&&fzs.getIds().length()>0){
					ziXunWriteMapper.updateZixunListPic("/"+Resources.getString("temfilePath")+"zixun/"+dailyPath+listImgName, Integer.valueOf(fzs.getIds()));
					//创建图片
					// 放到项目根目录下
					fullPath = new File(realPath) + "/"+ Resources.getString("temfilePath")+"zixun/" + dailyPath + imgName;
					File dir = new File(fullPath).getParentFile();
					if (!dir.exists()) {
						dir.mkdirs();
					}

					String listFullPath = new File(realPath) + "/"+ Resources.getString("temfilePath") +"zixun/" + dailyPath + listImgName;
					try {
						InputStream inputStream = imgFile.getInputStream();
						bis = new BufferedInputStream(inputStream);
						OutputStream out = new FileOutputStream(fullPath);
						bos = new BufferedOutputStream(out);
						byte[] tem = new byte[1024];
						int len = 0;
						while ((len = bis.read(tem)) != -1) {
							bos.write(tem);
						}
					} catch (Exception e) {
						e.printStackTrace();
						sentResponseData(response, "1");
						return;
					} finally {
						if (bos != null) {
							try {
								bos.flush();
								bos.close();
							} catch (IOException e) {
							}
						}
						if (bis != null) {
							try {
								bis.close();
							} catch (IOException e) {
							}
						}
					}
					try {
						ImageUtil.cutImage(fullPath, listFullPath, 238, 158);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		}
			
			this.sentResponseDataTo(response,"/"+dailyPath+uname,fzs.getIds());
		}else{
		// 放到项目根目录下
		fullPath = new File(realPath) + "/"+ Resources.getString("temfilePath")+"zixun/" + dailyPath + imgName;

		File dir = new File(fullPath).getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}

		String listFullPath = new File(realPath) + "/"+ Resources.getString("temfilePath") +"zixun/" + dailyPath + listImgName;
		try {
			InputStream inputStream = imgFile.getInputStream();
			bis = new BufferedInputStream(inputStream);
			OutputStream out = new FileOutputStream(fullPath);
			bos = new BufferedOutputStream(out);
			byte[] tem = new byte[1024];
			int len = 0;
			while ((len = bis.read(tem)) != -1) {
				bos.write(tem);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sentResponseData(response, "1");
			return;
		} finally {
			if (bos != null) {
				try {
					bos.flush();
					bos.close();
				} catch (IOException e) {
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
				}
			}
		}
		try {
			ImageUtil.cutImage(fullPath, listFullPath, 238, 158);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sentResponseData(response,"/"+dailyPath+uname);
		}
	
	
		

	}
	/**
	 * 获取文件扩展名
	 * 
	 * @return string
	 */
	private String getFileExt(String fileName) {
		// return fileName.substring(fileName.lastIndexOf("."));
		return ".jpg";
	}
	@RequestMapping("/addListPics")
	public String addListPics(String ids,Model model,String tab,HttpSession session) {
		String[] sids=ids.split("_");
		if(sids!=null && sids.length>0){
			ZiXun xun;
			List<ZiXun> list=new ArrayList<ZiXun>();
			for (String id : sids) {
				if(isNumeric(id)){
					xun=ziXunReadMapper.getZixunById(Integer.valueOf(id));
					if(xun!=null){
						//修改图片路径上传图片服务器
						if(tab!=null&&tab.length()>0&&"0".equals(tab)){
							//要修改上传了
							addImagePics(xun,session);
						}
						list.add(xun);
					}
				}
			}
			
			if(list.size()>0&&list!=null){
				model.addAttribute("zixuns", list);
			}
			return "zixun/addListPics";
		}
		//空返回home
		return "redirect:/zixun/home";
	}
	public  boolean isNumeric(String str){
		  for (int i = str.length();--i>=0;){   
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
	public void saveListpics() {
	}
	private void addImagePics(ZiXun xun,HttpSession session) {
		String listpic="";
		String path="";
		String fileName="";
		String typename="";
		
		if(xun!=null){
			if(xun.getListPic()!=null&&xun.getListPic().length()>0){
				listpic=xun.getListPic();
				if(listpic.startsWith("/"+Resources.getString("temfilePath"))){
			//	/20140618/14030824714866776
		typename=listpic.replace("_s.jpg", "");
		fileName=typename.substring(typename.lastIndexOf("/")+1,typename.length() );
		path = session.getServletContext().getRealPath("/" + Resources.getString("temfilePath"));
		FtpUtil.ftpUpload(Resources.getString("ftp.host"),Resources.getString("ftp.username"),Resources.getString("ftp.password"), path+"zixun/"+ZiXunDateUtil.getEndDir()+"/",ZiXunDateUtil.getEndDir(), fileName + "_s.jpg");
		FtpUtil.ftpUpload(Resources.getString("ftp.host"),Resources.getString("ftp.username"),Resources.getString("ftp.password"), path+"zixun/"+ZiXunDateUtil.getEndDir()+"/",ZiXunDateUtil.getEndDir(), fileName + "_b.jpg");
		//保存路径
		ziXunWriteMapper.updateZixunListPic("/"+ZiXunDateUtil.getEndDir()+"/"+fileName,xun.getId());
			}
			}
		}
	}
	
	public void sentResponseDataTo(HttpServletResponse response,String data,String ids){
		response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(data+"_"+ids);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
	}
	
	/**
	 * @param pid
	 * @return
	 * @author liuhg
	 * @Description ajax加载车型
	 */
	@RequestMapping(value="/getCarStyleById")
	public @ResponseBody Map<String,Object> getCarStyleById(Integer pid){
		HashMap<String,Object> map=new HashMap<String,Object>();
		if(pid!=null&&!"".equals(pid)){
			map.put("pid", pid);
		}
		List<CarStyle> carStyleList=zixunService.getCarStyleById(map);
		if(carStyleList!=null&&carStyleList.size()>0){
			map.put("carStyleList", carStyleList);
		}
		return map;
	}
}
