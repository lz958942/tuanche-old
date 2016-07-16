package com.tuanche.dao.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.TopicReview;
import com.tuanche.mapper.admin.read.TopicReviewReadMapper;
import com.tuanche.mapper.admin.write.TopicReviewWritetMapper;

@Repository
public class TopicReviewDao {
	@Resource
	private TopicReviewReadMapper readMapper;
	@Resource
	private TopicReviewWritetMapper writetMapper;
	public List<TopicReview> reviewList(TopicReview bean) {
		return readMapper.reviewListByPage(bean);
	}
	public TopicReview findByid(int id) {
		return readMapper.findByid(id);
	}
	public void operation(String[] ids, Integer auditStatus) {
		writetMapper.operationBatch(ids,auditStatus);
	}
	public void operation(Integer id, Integer auditStatus) {
		writetMapper.operation(id,auditStatus);
	}
	
	public void updateSort(int id,int topicId,int sort_old,int sort_new)
	{
		writetMapper.updateTopicCommentSort(id, topicId, sort_new, sort_old);
	}
	public void batchInsert(List<TopicReview> list) {
		writetMapper.batchInsert(list);
	}
	
	
	
}
