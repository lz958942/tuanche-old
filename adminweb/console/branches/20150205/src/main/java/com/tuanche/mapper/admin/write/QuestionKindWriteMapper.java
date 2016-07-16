package com.tuanche.mapper.admin.write;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.QuestionKind;


/**
 * 
 * @author wtk
 *
 */

@Repository
public interface QuestionKindWriteMapper {
	/**
	 * 添加分类
	 * @param kind
	 * @return 
	 */
	Integer addKind(QuestionKind kind);
	/**
	 * 批量添加
	 * @param list
	 */
	void addKins(List<QuestionKind> list);
	/**
	 * 修改分类
	 * @param kind
	 */
	void updateKind(QuestionKind kind);
	/**
	 * 删除分类
	 * @param kind
	 */
	void deleteKind(Integer id);
	/**
	 * 批量添加分类
	 * @param list
	 */
	void deleteOneKind(@Param("buildEmp")Integer buildEmp,@Param("id")Integer id,@Param("kindStatus")Byte kindStatus);
	/**
	 * 修改类别问题个数
	 * @param map
	 */
	void updateQuestCount(@Param("questCount")Integer questCount,@Param("id")Integer id);
	
	void updateQuestCounts(List<Integer> list);
}
