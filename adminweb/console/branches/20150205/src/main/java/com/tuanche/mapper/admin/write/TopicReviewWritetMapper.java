package com.tuanche.mapper.admin.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.admin.TopicReview;

public interface TopicReviewWritetMapper {
	void operationBatch(@Param("ids")String[] ids,@Param("auditStatus")Integer auditStatus);
	void operation(@Param("id")Integer id, @Param("auditStatus")Integer auditStatus);
	void updateTopicCommentSort(@Param("id")int id,@Param("topicid")Integer topicid,@Param(value="sort_new")int sort_new,@Param(value="sort_old")int sort_old);
	void batchInsert(@Param("list")List<TopicReview> list);
}
