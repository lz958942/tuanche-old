package com.tuanche.mapper.admin.read;

import java.util.List;

import com.tuanche.bean.admin.DecorateContent;

public interface DecorateContentReadMapper {

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description　内容修改前查询
	 */
	Integer selectContentByIdResult(Integer id);

	List<DecorateContent> selectContentByBaseId(Integer id);

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description
	 */
	DecorateContent selectContentById(Integer id);


}
