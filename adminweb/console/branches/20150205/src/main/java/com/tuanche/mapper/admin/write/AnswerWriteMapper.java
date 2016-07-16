package com.tuanche.mapper.admin.write;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.Answer;

/**
 * 
 * @author wtk
 *
 */

@Repository
public interface AnswerWriteMapper {
	/**
	 * 批量添加
	 * @param list
	 * @return
	 */
	void addAnswers(List<Answer> list);
	/**
	 * 批量修改（审核，上下线）
	 * @param list
	 */
	void updateAnswer(Map<String,Object> map);
	/**
	 * 批量删除
	 * @param list
	 */
	void deleteAnswer(List<Answer> list);
	/**
	 * 删除创建人及内容为空的数据
	 */
	void deleteNull();
	/**
	 * 删除问题之下的答案
	 * @param questionId
	 */
	void deleteAnswersWithQ(@Param("answerStatus")Byte answerStatus,@Param("buildEmp")Integer buildEmp,@Param("id")Integer id);
	
	void updateState(@Param("id")Integer id,@Param("type") Integer type,@Param("eid")Integer eid);
	void saveAnswer(Answer answer);
	/**
	 * 修改答案状态   审核通过或者删除
	 * @param answerStatus
	 * @param buildEmp
	 * @param list
	 */
	void updateAnswers(@Param("answerStatus") Byte answerStatus,@Param("buildEmp") Integer buildEmp,@Param("list") List<String> list);
	
}
