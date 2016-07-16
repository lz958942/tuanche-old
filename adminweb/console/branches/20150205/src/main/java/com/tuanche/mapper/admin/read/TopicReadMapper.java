package com.tuanche.mapper.admin.read;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.Topic;

/**
 * 
 * @author wtk
 *
 */
@Repository
public interface TopicReadMapper {

	List<Topic> topicByPage(Topic topic);
	List<Topic> topicList();
	Topic selectTopicById(Integer id);
	
}
