package com.tuanche.mapper.admin.read;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.QuestionKind;

/**
 * 
 * @author wtk
 *
 */
@Repository
public interface QuestionKindReadMapper {
	/**
	 * 根据父id查询下面的二级分类
	 * @param pid
	 * @return
	 */
	List<QuestionKind> selectTwoKind(Integer pid);
	/**
	 * 查询所有一级分类
	 * @return
	 */
	List<QuestionKind> selectOneKind();
	/**
	 * 查询所有分类
	 * @return
	 */
	List<QuestionKind> selectAlls();
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	QuestionKind selectOne(Integer id);
}
