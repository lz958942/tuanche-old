package com.tuanche.mapper.admin.write;

import java.util.List;

import com.tuanche.bean.admin.DecorateContent;

public interface DecorateContentWriteMapper {

	/**
	 * @param decorateContent
	 * @author liuhg
	 * @Description 添加内容
	 */
	void addContent(DecorateContent decorateContent);

	/**
	 * @param decorateContent
	 * @author liuhg
	 * @Description 更新内容
	 */
	void updateContent(DecorateContent decorateContent);

	/**
	 * @param id
	 * @author liuhg
	 * @Description 删除内容
	 */
	void deleteContent(Integer id);

}
