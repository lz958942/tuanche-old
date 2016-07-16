package com.tuanche.mapper.admin.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.Answer;
/**
 * 
 * @author wtk
 *
 */
@Repository
public interface AnswerReadMapper {
	/**
	 * 查询最佳答案
	 * @param pid
	 * @return
	 */
	Answer selectAnswer(Integer qid);
	/**
	 * 查询所有回答
	 * @param pid
	 * @return
	 */
	List<Answer> queryByPage(Integer qid);
	/**
	 * 查询问题的回复数
	 * @param qid
	 * @return
	 * @author kevin
	 */
	Integer selectCount(Integer qid);
	List<Answer> getAnswersByQuestionByPage(@Param("qid")Integer qid);
	List<Answer> answerListByPage(@Param("search")List<String> search);
	Integer getPidByid(@Param("id")Integer id);
	
}
