package com.tuanche.service.che;

import java.io.File;
import java.util.ArrayList;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.tuanche.bean.che.BrandDomain;
import com.tuanche.bean.che.CarstyleDomain;
import com.tuanche.bean.che.EvaluateReplyBean;
import com.tuanche.bean.che.Review;
import com.tuanche.commons.util.Resources;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.dao.che.BrandDao;
import com.tuanche.dao.che.ReviewDao;
import com.tuanche.mapper.che.read.CarstylekReadMapper;
import com.tuanche.mapper.che.read.ReviewMapper;
import com.tuanche.smc.util.ZiXunDateUtil;
import com.tuanche.upload.FtpUtil;
@Service
public class ReviewService {
	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private CarstylekReadMapper carstyleReadMapper;
	@Autowired
	private BrandDao brandDao;
	@Autowired
	private ReviewMapper reviewMapper;
	@Autowired
	private EvaluateReplyService evaluateReplyService;
	
	private static final int IMAGE=1;
	private static final int NOIMAGE=0;
	public List<Review> search(Review review,int type) {
		List<String> search=new ArrayList<String>();
		int types=0;
		if(review!=null){
			if(review.getId()!=null&&review.getId()!=0){
				search.add("t.id="+review.getId());
			}
			if(review.getComment()!=null&&review.getComment().length()>0){
				search.add("t.comment LIKE '%"+review.getComment()+"%'");
			}
			if(review.getCarstyleId()!=null&&review.getCarstyleId()!=0){
				search.add("t.carstyle_id="+review.getCarstyleId());
			}
			if(review.getIshavepic()!=null&&review.getIshavepic()!=3){
				search.add("t.ishavepic="+review.getIshavepic());
			}
			if(review.getSource()!=null&&review.getSource()!=0){
				search.add("t.source="+review.getSource());
			}
			if(review.getBrandId()!=null&&review.getBrandId()!=0){
				search.add("t.brand_id="+review.getBrandId());
			}
			if(type==1){
				//未通过
			search.add("t.state=0");
			types=1;
			}else if(type==2){
			//通过
			search.add("t.state=1");
			types=2;
			}else if(type==4){
				search.add("t.state=2");
				types=2;
			}
			
			if(type==1){
				//查询wei通过
				if(review!=null && review.getModifyTime()!=null &&review.getModifyTime().length()>0&&!review.getModifyTime().equals("NULL") && !review.getModifyTime().equals("起始日期")){
					search.add("t.create_time > '"+review.getModifyTime()+"'");
				}
				if(review!=null && review.getEndtDate()!=null &&review.getEndtDate().length()>0&&!review.getEndtDate().equals("NULL") && !review.getEndtDate().equals("终止日期")){
					search.add("t.create_time < '"+review.getEndtDate()+"'");
				}
			List<Review> list=reviewDao.search(search,types);
			for (Review review2 : list) {
				 review2.setCarName(carName(review2.getCarstyleId()));
			}
			 return list;
			}else if(type==2){
				//查询通过精华帖
				if(review!=null && review.getIsCream()!=null &&review.getIsCream()!=3){
					search.add("t.is_cream="+review.getIsCream());
				}
				if(review!=null && review.getCommentTotal()!=null &&review.getCommentTotal()==5){
					search.add("(comment_total = 5 OR comment_total=4)");
				}else if(review!=null && review.getCommentTotal()!=null &&review.getCommentTotal()==3){
					search.add("(comment_total = 2 OR comment_total=3)");
				}else if(review!=null && review.getCommentTotal()!=null &&review.getCommentTotal()==1){
					search.add("comment_total =1");
				}
				if(review!=null && review.getIsReply()!=null){
					search.add("t.is_reply="+review.getIsReply());
				}
				if(review!=null && review.getModifyTime()!=null && review.getModifyTime().length()>0&&!review.getModifyTime().equals("NULL")&& !review.getModifyTime().equals("起始日期")){
					search.add("t.modify_time > '"+review.getModifyTime()+"'");
				}
				if(review!=null && review.getEndtDate()!=null &&review.getEndtDate().length()>0&&!review.getEndtDate().equals("NULL") && !review.getEndtDate().equals("终止日期")){
					search.add("t.modify_time < '"+review.getEndtDate()+"'");
				}
				if(review!=null && review.getMemId()!=null ){
					if(review.getMemId()==0){
						search.add("(t.mem_id = 0 OR t.mem_id IS NULL)");
					}else{
						search.add("(t.mem_id !=0 AND t.mem_id IS NOT NULL)");
					}
				}
				List<Review> list=reviewDao.search(search,types);
				for (Review review2 : list) {
					 review2.setCarName(carName(review2.getCarstyleId()));
					 reply(review2);
				}
				 return list;
			}else if(type==4){
				//未通过
				if(review!=null && review.getModifyTime()!=null &&review.getModifyTime().length()>0&&!review.getModifyTime().equals("NULL")&&!review.getModifyTime().equals("起始日期")){
					search.add("t.modify_time > '"+review.getModifyTime()+"'");
				}
				if(review!=null && review.getEndtDate()!=null &&review.getEndtDate().length()>0&&!review.getEndtDate().equals("NULL") && !review.getEndtDate().equals("终止日期")){
					search.add("t.create_time < '"+review.getEndtDate()+"'");
				}
				List<Review> list=reviewDao.search(search,types);
				for (Review review2 : list) {
					 review2.setCarName(carName(review2.getCarstyleId()));
				}
				 return list;
			}
		}if(review==null || type==3){
			//通过首页
			search.add("t.state=1");
			 List<Review> list=reviewDao.search(search,2);
			 for (Review review2 : list) {
				 review2.setCarName(carName(review2.getCarstyleId()));
				 if(review2.getIsReply()!=null &&review2.getIsReply()==1){
					 reply(review2);
				 }
			}
			 return list;
		}else if(review==null || type==5){
			//为通过首页
			search.add("t.state=2");
			 List<Review> list=reviewDao.search(search,2);
			 for (Review review2 : list) {
				 review2.setCarName(carName(review2.getCarstyleId()));
			}
			 return list;
		}
		return null;
		
		//return null;
		
	}
	private void reply(Review review2) {
		if(review2!=null){
		List<EvaluateReplyBean> replyList= evaluateReplyService.getReply(review2.getId());
		 int replyListSize=replyList.size();
		 if(replyList!=null  && replyListSize==1 && replyList.get(0)!=null){
			 review2.setReply(replyList.get(0).getContent());
		 }else if(replyList!=null  && replyListSize>1 && replyList.get(0)!=null){
			 /*StringBuffer buffer=new StringBuffer();
			 for (EvaluateReplyBean evaluateReplyBean : replyList) {
				 //多条
				 buffer.append(evaluateReplyBean.getContent());
				 buffer.append("-");
				 if(buffer!=null){
					 review2.setReply(buffer.toString().trim());
				 }
				
			}*/
			 review2.setReply(replyList.get(0).getContent());
		 }
		}
	}
	private String carName(Integer carstyleId) {
		Map<Integer,CarstyleDomain>  map=new HashMap<Integer, CarstyleDomain>();
		List<CarstyleDomain> clist=	carstyleReadMapper.carStyleHome();
		if(map.size()!=clist.size()){
		for (CarstyleDomain carstyleDomain : clist) {
			map.put(carstyleDomain.getId(), carstyleDomain);
		}
		}
		if(map.containsKey(carstyleId)){
			return map.get(carstyleId).getStyle();
		}
		return "";
	}
	public List<Review> getReviewAll() {
		 List<Review> list =reviewDao.getReviewAll();
		 for (Review review2 : list) {
			 review2.setCarName(carName(review2.getCarstyleId()));
		}
		return list;
	}
	public List<CarstyleDomain>  getCarAll() {
		return carstyleReadMapper.carStyleHome();
	}
	public List<BrandDomain> getBrandNameId() {
		List<BrandDomain> blist=brandDao.getBrandNameId();
		if(blist!=null&&blist.size()>0){
			for (BrandDomain brandDomain : blist) {
				if(brandDomain.getPinyin()!=null && brandDomain.getPinyin().length()>0 && !brandDomain.getPinyin().equals("NULL")){
					brandDomain.setReviewInitial(brandDomain.getPinyin().substring(0,1).toUpperCase());
				}
			}
			}
		
		return blist;
	}
	public  void cream(HttpSession session,Integer idd) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(sessionUser!=null &&sessionUser.getId()!=null ){
			reviewDao.cream(idd,sessionUser.getId());
		}
		
	}
	public void creamRemove(HttpSession session, Integer id) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(sessionUser!=null &&sessionUser.getId()!=null ){
			reviewDao.creamRemove(id,sessionUser.getId());
		}
	}
	public void passNo(HttpSession session,Integer idd) {
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(sessionUser!=null &&sessionUser.getId()!=null ){
			reviewDao.passNo(idd,sessionUser.getId());
		}
	}
	
	
	//自定义添加评论
	public List<Review> reviewHome(Review reviewBean) {
		List<String> search=new ArrayList<String>();
		if(reviewBean!=null){
			encapsulation(search,reviewBean);
		}
		search.add("( state= 0 OR state= 2 )");
		search.add("(mem_id = 0 OR mem_id IS NULL OR mem_id='')");
		return reviewDao.reviewHome(search);
	}
	private void encapsulation(List<String> search, Review reviewBean) {
			if(reviewBean!=null){
				if(reviewBean.getId()!=null && reviewBean.getId()>0){
					search.add("id="+reviewBean.getId());
				}
				if(reviewBean.getIshavepic()!=null && reviewBean.getIshavepic()!=3 ){
					search.add("ishavepic="+reviewBean.getIshavepic());
				}
				if(reviewBean.getCityId()!=null){
					search.add("city_id="+reviewBean.getCityId());
				}
				if(reviewBean.getBrandId()!=null){
					search.add("brand_id="+reviewBean.getBrandId());
				}
				if(reviewBean.getCarstyleId()!=null){
					search.add("carstyle_id="+reviewBean.getCarstyleId());
				}
				/*if(reviewBean.getCreatetime()!=null && reviewBean.getCreatetime().length()>0 ){
					search.add("create_time < "+reviewBean.getCreatetime());
				}*/
			}
	}
	public void deleteReview(Integer id) {
		if(id!=null && id!=0){
			Review review=reviewDao.getReviewByid(id);
			if(review!=null && review.getIshavepic()!=null && review.getIshavepic()==1){
				reviewDao.deleteReviewPic(id);
			}
			reviewDao.deleteReview(id);
		}
		
	}
	
	public void updateReview(Review review, HttpSession session) {
		if(review!=null){
			if(review.getImageSrc()!=null &&review.getImageSrc().length()>0){
				//有图片  上传
				review.setIshavepic(IMAGE);
				String path = session.getServletContext().getContextPath()+"/pic_tmp/review/"+review.getImageSrc();
				String fPath= session.getServletContext().getRealPath(path);
				if(new File(fPath).exists()){
					imageUpload(fPath, review.getId(), 1);
				}
			}else{
				review.setIshavepic(NOIMAGE);
			}
			reviewDao.updateReview(review);
		}
	}
	public void insertReview(Review review,HttpSession session) {
		if(review!=null){
			if(review.getImageSrc()!=null &&review.getImageSrc().length()>0){
				//有图片  上传
				review.setIshavepic(IMAGE);
			}else{
				review.setIshavepic(NOIMAGE);
			}
		reviewDao.insertReview(review);
		if(review.getIshavepic()==1){
			//上传
			String path = session.getServletContext().getContextPath()+"/pic_tmp/review/"+review.getImageSrc();
			String fPath= session.getServletContext().getRealPath(path);
			imageUpload(fPath,review.getId(),0);
		}
		}
	}
	private void imageUpload(String ingSrc ,Integer id,Integer type) {
		if(ingSrc!=null && ingSrc.length()>0){
			if(new File(ingSrc).exists()){
				//上传 成功 插入   tc_evaluate_pic
				int subleng=ingSrc.lastIndexOf("/");
				if(subleng==-1){
					subleng=ingSrc.lastIndexOf("\\");
				}
				String  fileName=ingSrc.substring(subleng+1,ingSrc.length());
				String path=ingSrc.substring(0,subleng);
				FtpUtil.ftpUpload(Resources.getString("ftp.host"),Resources.getString("ftp.uevaluate.username"),Resources.getString("ftp.uevaluate.password"), path+"/",ZiXunDateUtil.getEndDir(), fileName);
				if(type!=null && type==0){
					reviewDao.insertPic(id,Resources.getString("pic.host")+"/uevaluate"+"/"+ZiXunDateUtil.getEndDir()+"/"+fileName);
				}else{
					reviewDao.UpdatePic(id,Resources.getString("pic.host")+"/uevaluate"+"/"+ZiXunDateUtil.getEndDir()+"/"+fileName);
				}
			}
		}
	}
}
