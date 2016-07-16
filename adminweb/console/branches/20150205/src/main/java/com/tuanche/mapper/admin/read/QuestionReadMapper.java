package com.tuanche.mapper.admin.read;



import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.Question;


@Repository
public interface QuestionReadMapper {
/**
 * 查询单个问题
 * @param question
 * @return
 */
	Question selectQuestion(Integer id);
	/**
	 * 查询特定条件的问题
	 * @param question
	 * @return
	 */
	List<Question> queryByPage(Question question);
	/**
	 * 下载
	 * @param question
	 * @return
	 */
	List<Question> selectAlls(Question question);
	/**
	 * 根据二级分类id 查询问题个数
	 * @param id
	 * @return
	 */
	Integer selectAnsCount(Integer id);
	/**
	 * 获取所有提问标题
	 * @return
	 */
	List<Question> selectQuestions();
}
