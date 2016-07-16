package com.tuanche.dao.che;

import java.util.List;











import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.che.EvaluatePic;
import com.tuanche.bean.che.Review;
import com.tuanche.mapper.che.read.EvaluatePicMapper;
import com.tuanche.mapper.che.read.ReviewMapper;
import com.tuanche.mapper.che.write.EvaluatePicWriteMapper;
import com.tuanche.mapper.che.write.ReviewWriteMapper;
@Repository
public class ReviewDao {
	@Resource
private ReviewMapper reviewMapper;
	@Resource
private EvaluatePicMapper evaluatePicMapper;
	@Resource
private EvaluatePicWriteMapper evaluatePicWriteMapper; 
	@Resource
private ReviewWriteMapper  reviewWriteMapper;


	public List<Review> getReviewAll() {
		// TODO Auto-generated method stub
		return reviewMapper.getReviewAllByPage();
	}

	public Review getParticular(Integer id) {
		// TODO Auto-generated method stub
		return reviewMapper.getParticular(id);
	}
	public List<EvaluatePic> getEvaluatePic(Integer id){
		return evaluatePicMapper.getEvaluatePic(id);
	}

	public List<Review> search(List<String> search,int type) {
		// TODO Auto-generated method stub
		return reviewMapper.searchByPage(search,type);
	}

	public void batchPass(Integer id,Integer editId) {
		// TODO Auto-generated method stub
		evaluatePicWriteMapper.batchPass(id,editId);
	}

	public void cream(Integer idd,Integer editId) {
		// TODO Auto-generated method stub
		reviewWriteMapper.cream(idd,editId);
	}

	public void creamRemove(Integer id, Integer editId) {
		// TODO Auto-generated method stub
		reviewWriteMapper.creamRemove(id,editId);
	}

	public void passNo(Integer idd, Integer editId) {
		// TODO Auto-generated method stub
		reviewWriteMapper.passNo(idd,editId);
	}

	public List<Review> reviewHome(List<String> search) {
		// TODO Auto-generated method stub
		return reviewMapper.reviewHomeByPage(search);
	}
	
	public Review getReviewByid(Integer id) {
		// TODO Auto-generated method stub
		return reviewMapper.getReviewByid(id);
	}

	public void deleteReview(Integer id) {
		// TODO Auto-generated method stub
		reviewWriteMapper.deleteReview(id);
	}

	public void updateReview(Review review) {
		System.out.println(review);
		reviewWriteMapper.updateReview(review);
	}

	public void insertReview(Review review) {
		reviewWriteMapper.insertReview(review);
	}

	public void insertPic(Integer id,String src) {
		evaluatePicWriteMapper.insertPic(id, src);
	}

	public void UpdatePic(Integer id, String src) {
		System.out.println(id+src);
		evaluatePicWriteMapper.UpdatePic(id,src);
	}

	public void deleteReviewPic(Integer eid) {
		evaluatePicWriteMapper.deleteReviewPic(eid);
	}
}
