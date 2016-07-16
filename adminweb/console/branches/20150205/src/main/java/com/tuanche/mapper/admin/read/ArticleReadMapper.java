package com.tuanche.mapper.admin.read;


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
public interface ArticleReadMapper {

	List<Article> articleByPage(Article article);

	Article selectArticleById(Integer id);

	int selectArtCountById(@Param("sort")Integer sort,@Param("id")Integer id,@Param("magazineId")Integer magazineId);
	
}
