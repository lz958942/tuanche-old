package com.tuanche.mapper.admin.read;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.admin.TopicReview;



public interface TopicReviewReadMapper {
	TopicReview selectByPrimaryKey(Long id);
	List<TopicReview> reviewListByPage(TopicReview bean);
	TopicReview findByid(@Param("id")int id);
}