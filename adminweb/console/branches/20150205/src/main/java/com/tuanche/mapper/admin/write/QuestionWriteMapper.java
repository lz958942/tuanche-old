package com.tuanche.mapper.admin.write;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.Question;
/**
 * 
 * @author wtk
 *
 */

@Repository
public interface QuestionWriteMapper {
	/**
	 * 批量添加问题
	 * @param list
	 * @return
	 */
	Integer addQuestions(List<Question> list);
	
	/**
	 * 批量修改（上下线，审核）
	 * @param list
	 */
	void updateQuestions(@Param("questionStatus")Byte questionStatus,@Param("buildEmp")Integer buildEmp,@Param("list")List<String> list);
	/**
	 * 批量删除数据
	 * @param list
	 */
	void deleteQuestions(List<Question> list);
	/**
	 * 删除单条问题
	 * @param id
	 */
		void deleteOneQuestion(@Param("questionStatus")Byte questionStatus,@Param("buildEmp")Integer buildEmp,@Param("id")Integer id);
	/**
	 * 推荐问题
	 * @param map
	 */
		void isRecom(Map<String,Object> map);
	/**
	 * 插入问题返回id
	 * @param question
	 * @return
	 */
	void insertQuestion(Question question);
	/**
	 * 问题得到解决
	 * @param map
	 */
	void resolve(@Param("Resolve")Byte Resolve,@Param("buildEmp")Integer buildEmp,@Param("id")Integer id);
	/**
	 * 修改问题回复数	
	 * @param map
	 */
	void updateReply(@Param("reply")Integer reply,@Param("id")Integer id);
	
	void updateReplys(List<Integer> list);
	/**
	 * 
	 * @param userId
	 * @param quetionId
	 */
	void deleteContent(@Param("userId")Integer userId,@Param("id")Integer id);
	
	void updateContent(@Param("userId")Integer userId, @Param("id")Integer id, @Param("content")String content);

	void modify(@Param("userId")Integer userId, @Param("id")Integer id,@Param("firstkindId")Integer firstkindId, @Param("secondkindId")Integer secondkindId,@Param(value="brandId")Integer brandId,@Param(value="carstyleId")Integer carstyleId);

	/**
	 * 重复标记
	 * @param list ID集合
	 */
	void updateDeduplication(List<Integer> list);

	void delete(List<String> list);

	void batchUpdate(@Param("list")List<String> list, @Param("userId")Integer userId, @Param("firstkindId")Integer firstkindId, @Param("secondkindId")Integer secondkindId,@Param(value="brandId")Integer brandId,@Param(value="carstyleId")Integer carstyleId);

}
