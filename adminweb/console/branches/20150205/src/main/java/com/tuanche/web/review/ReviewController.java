package com.tuanche.web.review;

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
import java.util.List;
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

import com.tuanche.bean.che.CarstyleDomain;
import com.tuanche.bean.che.EvaluatePic;
import com.tuanche.bean.che.EvaluateReplyBean;
import com.tuanche.bean.che.Review;
import com.tuanche.commons.util.Resources;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.dao.che.ReviewDao;
import com.tuanche.mapper.che.read.CarstylekReadMapper;
import com.tuanche.mapper.che.read.EvaluatePicMapper;
import com.tuanche.mapper.che.write.EvaluatePicWriteMapper;
import com.tuanche.mapper.che.write.ReviewWriteMapper;
import com.tuanche.service.che.EvaluateReplyService;
import com.tuanche.service.che.ReviewService;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.util.ZiXunDateUtil;
@Controller
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private ReviewWriteMapper reviewWriteMapper;
	@Autowired
	private EvaluatePicWriteMapper evaluatePicMapper;
	@Autowired
	private EvaluatePicMapper picMapper;
	@Resource
	private CarstylekReadMapper carstyleReadMapper;
	@Autowired
	private EvaluateReplyService evaluateReplyService;
	
	
	@RequestMapping("/home")
	public ModelAndView home(Model model) {
		Pagination pagination=null;
		if(pagination==null){
		pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);

		List<Review> list=reviewService.getReviewAll();
		model.addAttribute("brand",reviewService.getBrandNameId());
		model.addAttribute("page", Pagination.threadLocal.get());
		return new ModelAndView("/review/home","list",list);
	}
	@RequestMapping("/particular")
	public ModelAndView particular(Model model,Integer idd,Review review) {
		Pagination pagination=null;
		if(pagination==null){
		pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		model.addAttribute("page", Pagination.threadLocal.get());
		List<EvaluatePic> particular=reviewDao.getEvaluatePic(idd);
		if(review.getId()!=null&&review.getId()!=0){
			model.addAttribute("id", review.getId());
		}
		if(review.getComment()!=null&&review.getComment().length()>0){
			model.addAttribute("comment", review.getComment());
		}
		if(review.getCarstyleId()!=null&&review.getCarstyleId()!=0){
			model.addAttribute("carstyleId", review.getCarstyleId());
		}
		if(review.getIshavepic()!=null&&review.getIshavepic()!=3){
			model.addAttribute("ishavepic",review.getIshavepic());
		}
		if(review.getSource()!=null&&review.getSource()!=0){
			model.addAttribute("source",review.getSource());
		}
		return new ModelAndView("/review/particular","pic",particular);
	}
	/*@RequestMapping("/pass")
	public ModelAndView pass(Review review,Integer idd,Model model,@Param("brandName") String brandName,HttpSession session,@Param("sta")Integer sta) {
		 if(idd!=null&&idd!=0){
			 Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
			if(sessionUser!=null&&sessionUser.getId()!=null){
				reviewWriteMapper.pass(idd,sessionUser.getId());	
			}
			
		 }
		 if(sta!=null && sta==6){
			 return this.passSearch(review, model, brandName, 4);
		 }
		return this.search(review,model,brandName);
	}*/
	@RequestMapping("/pass")
	public void pass(HttpSession session,@Param("idd")Integer idd,HttpServletResponse response) {
		 if(idd!=null&&idd!=0){
			 Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
			if(sessionUser!=null&&sessionUser.getId()!=null){
				reviewWriteMapper.pass(idd,sessionUser.getId());
				sentResponseName(response, "1");
			}
			
		 }
	}
	@RequestMapping("/pass.do")
	private void passPic(Integer id,int type,HttpServletResponse response,HttpSession session) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(sessionUser!=null &&sessionUser.getId()!=null ){
		if(type==3){
			//不通过
			evaluatePicMapper.passPic(id,2,sessionUser.getId());
			sentResponseName(response,0+"");
		}else{
			//通过
			evaluatePicMapper.passPic(id,1,sessionUser.getId());
			sentResponseName(response,1+"");
		}
		}
	}
	@RequestMapping("/search")
	private ModelAndView search(Review review,Model model,@Param("brandName") String brandName) {
		Pagination pagination=review.getPage();
		if(pagination==null){
		pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
	List<Review> list=reviewService.search(review,1);
	if(list!=null&&list.size()>0){
		if(review!=null){
			model.addAttribute("review", review);
		}
		if(brandName!=null&&brandName.length()>0){
			model.addAttribute("bid",brandName);
		}
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("brand",reviewService.getBrandNameId());
		return new ModelAndView("/review/home","list",list);
	}
	if(brandName!=null&&brandName.length()>0){
		model.addAttribute("bid",brandName);
	}
	model.addAttribute("brand",reviewService.getBrandNameId());
	model.addAttribute("page", Pagination.threadLocal.get());
	return new ModelAndView("/review/home","list",list);
	}
	
	private void sentResponseName(HttpServletResponse response, String string) {
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(string);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/batchPass")
	public void batchPass(HttpServletResponse response,@Param("ids")String ids,HttpSession session) {
		StringBuffer buffer=new StringBuffer();
		if(ids!=null&&ids.length()>0){
			String[] idsArr=ids.split("-");
			if(idsArr!=null&&idsArr.length>0){
				Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
				if(sessionUser!=null&&sessionUser.getId()!=null){
			for (String string : idsArr) {
				if(isNumeric(string)){
					reviewDao.batchPass(Integer.valueOf(string),sessionUser.getId());
					buffer.append(string+"-");
				}
			}
			}
			}
		}
		if(buffer.length()>0)
		sentResponseName(response, buffer.toString());
	}
	public  boolean isNumeric(String str){
		  for (int i = str.length();--i>=0;){   
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
	//设置精华帖
	@RequestMapping("/cream")
	public  ModelAndView cream(@Param("review")Review review,Model model,HttpSession session,@Param("idd")Integer idd){
		reviewService.cream(session,idd);
		return this.search(review, model, null);
		 }
	
	
	//====================审核通过模块==========================================
	@RequestMapping("/passSearch")
	public ModelAndView passSearch(Review review,Model model,@Param("type") Integer type) {
		Pagination pagination=review.getPage();
		if(pagination==null){
		pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		
		List<Review> list=new ArrayList<Review>();
		if(type!=null && type !=0 &&review !=null){
		 list=reviewService.search(review,type);
		}
	if(review!=null){
	model.addAttribute("review", review);
	}
	model.addAttribute("brand",reviewService.getBrandNameId());
	model.addAttribute("page", Pagination.threadLocal.get());
	if(type==4 ||type==5){
		return new ModelAndView("/review/passNoHome","list",list);
	}
	return new ModelAndView("/review/passHome","list",list);
	}
	@RequestMapping("/creamDispose")
	public void creamDispose(HttpServletResponse response,Integer id,Integer type,HttpSession session,@Param("sta")Integer sta) {
		if(type!=null && type==0){
			//取消精华
			reviewService.creamRemove(session,id);
			sentResponseName(response, "0");
		}
		if(type!=null && type==1){
			//添加精华
			/*if(sta!=null && sta==6){
				//帖子精华，修改审核状态 审核过了	
				
			}*/

			reviewService.cream(session,id);
			sentResponseName(response, "1");
		}
	}
	//查看审核图片
	@RequestMapping("/particular.do")
	public ModelAndView selectPassPic(@Param("id")Integer id) {
		List<EvaluatePic> list =picMapper.selectPassPic(id);
		return  new ModelAndView("/review/passParticular","list",list);
	}
	@RequestMapping("/passHomeAll")
	public ModelAndView passHome(Model model) {
		return passSearch(new Review(),model,3);
	}
	//审核完成后不通过
	@RequestMapping("/passNo")
	public void passNo(@Param("idd") Integer idd,HttpSession session,HttpServletResponse response) {
		reviewService.passNo(session,idd);
		sentResponseName(response, "1");
	}
/*************************查看审核不通过**************************/
	//审核完成后不通过
		@RequestMapping("/passNoHome")
		public ModelAndView passNoHome(Model model) {
			//return passSearch(new Review(),model,null,5);
			return new ModelAndView("redirect:/review/passSearch?type=5");
		}
		/*************************添加自定义点评**************************/
		@RequestMapping("/homeReview")
		public ModelAndView reviewHome(Model model,Review reviewBean) {
			Pagination pagination=reviewBean.getPage();
			if(pagination==null){
			pagination = new Pagination();
			}
			Pagination.threadLocal.set(pagination);
			List<Review> list=reviewService.reviewHome(reviewBean);
			for (Review review : list) {
				if(review!=null && review.getCarstyleId()!=null){
					 CarstyleDomain car=carstyleReadMapper.getCarNameById(review.getCarstyleId());
					if(car!=null && car.getStyle()!=null){
						review.setCarName(car.getStyle());
					}
					car =null;
				}
			}
			if(reviewBean!=null){
				model.addAttribute("reviewBean", reviewBean);
			}
			model.addAttribute("citys", GlobalConstants.districtMap);
			model.addAttribute("brand",reviewService.getBrandNameId());
			model.addAttribute("page", Pagination.threadLocal.get());
			return new ModelAndView("/review/listReview","list",list);
		}
		@RequestMapping("/addReviewTo")
		public ModelAndView addReviewTo(Model model,@Param("idd")Integer idd) {
			if(idd==null){
				model.addAttribute("brand",reviewService.getBrandNameId());
			}else{
				//修改
				Review review=reviewDao.getReviewByid(idd);
				model.addAttribute("review",review);
				model.addAttribute("brand",reviewService.getBrandNameId());
				//车型
				model.addAttribute("carStyles",carstyleReadMapper.getCarStyles());
				//车款
				model.addAttribute("carShapes",carstyleReadMapper.getCarShapes());
				if(review!=null && review.getIshavepic()!=null && review.getIshavepic()==1){
					if(review.getId()!=null){
						EvaluatePic epic=picMapper.getPicSrc(review.getId());
						if(epic !=null && epic.getPic()!=null &&epic.getPic().length()>0 ){
							review.setImageSrc(epic.getPic());
						}
					}
				}
			}
			//查是否有图片
			model.addAttribute("citys", GlobalConstants.districtMap);
			return new ModelAndView("/review/addReview");
		}
		@RequestMapping("/saveReview")
		public ModelAndView saveReview(Model model,Review review,HttpSession session) {
			if(review!=null){
			if(review.getId()!=null ){
				//修改
				reviewService.updateReview(review,session);
			}else{
				reviewService.insertReview(review,session);
			}
			}
			return new ModelAndView("redirect:/review/homeReview");
		}
		@RequestMapping("/deleteReview")
		public ModelAndView deleteReview(@Param("id")Integer id){
			if(id!=null && id>0){
				reviewService.deleteReview(id);
			}
			return new ModelAndView("redirect:/review/homeReview");
		}
		
		//上传图片
		@RequestMapping("/saveReview.do")
		public void uploadImgForlistPic(HttpServletRequest request,HttpServletResponse response,@Param("delSrc")String delSrc) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile imgFile = multipartRequest.getFile("listPicFile");
			String parentPath = Resources.getString("filePath");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			String dailyPath = simpleDateFormat.format(new Date()) + "/";
			String uname = System.currentTimeMillis() + ""+ new Random().nextInt(10000);
			String ext = getFileExt(imgFile.getOriginalFilename());
			String imgName = uname + ext;
			String path = parentPath + dailyPath;
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			String fullPath = path + imgName;
			String servletPath = request.getContextPath();
			String realPath = request.getSession().getServletContext().getRealPath(servletPath);
			fullPath = new File(realPath) + "/"+ Resources.getString("temfilePath") + "review/" + dailyPath+imgName;
			if(delSrc!=null){
				String fpath=realPath+"/pic_tmp/review/"+ZiXunDateUtil.getEndDir()+delSrc;
				if(new File(fpath).exists()){
					new File(fpath).delete();
				}
			}
			File dir = new File(fullPath).getParentFile();
			if (!dir.exists()) {
				dir.mkdirs();
			}
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
			sentResponseName(response, ZiXunDateUtil.getEndDir()+"/"+imgName);
			 
		}
		private String getFileExt(String fileName) {
			return ".jpg";
		}
		
}
