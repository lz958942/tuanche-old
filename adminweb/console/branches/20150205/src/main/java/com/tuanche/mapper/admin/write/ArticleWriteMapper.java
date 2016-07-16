package com.tuanche.mapper.admin.write;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.Article;


/**
 * 
 * @author wtk
 *
 */

@Repository
public interface ArticleWriteMapper {
	/**
	 * 修改答案状态   审核通过或者删除
	 * @param answerStatus
	 * @param buildEmp
	 * @param list
	 */
	void updateAnswers(@Param("answerStatus") Byte answerStatus,@Param("buildEmp") Integer buildEmp,@Param("list") List<String> list);

	void updateArticle(Article article);
	
	void addArticle(Article article);

	void updateArtStatus(@Param("articleStatus")Byte status,@Param("updateEmp")Integer updateEmp,@Param("id")Integer id);
}
