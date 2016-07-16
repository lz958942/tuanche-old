package com.tuanche.mapper.admin.write;



import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.Topic;


/**
 * 
 * @author wtk
 *
 */

@Repository
public interface TopicWriteMapper {

	void updateTopic(Topic topic);
	
	void addTopic(Topic topic);

	void updateTopStatus(@Param("topicStatus")Byte status,@Param("updateEmp")Integer updateEmp,@Param("id")Integer id);

	void recom(@Param("isRecom")Byte recom, @Param("updateEmp")Integer updateEmp, @Param("id")Integer id);

	void current(@Param("current")Byte current, @Param("updateEmp")Integer updateEmp, @Param("id")Integer id);
}
