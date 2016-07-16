package com.tuanche.mapper.admin.write;

import java.util.Map;

import com.tuanche.bean.admin.DecorateBase;

public interface DecorateBaseWriteMapper {

	/**
	 * @param decorateBase
	 * @author liuhg
	 * @Description 基本信息添加
	 */
	void addDecorateBase(DecorateBase decorateBase);

	/**
	 * @param decorateBase
	 * @author liuhg
	 * @Description 基本信息修改
	 */
	void updateBase(DecorateBase decorateBase);

	/**
	 * @param map
	 * @author liuhg
	 * @Description 上下线
	 */
	void changeOnline(Map<String, Object> map);

	/**
	 * @param id
	 * @author liuhg
	 * @Description 删除装饰文章
	 */
	void deleteDecorate(Integer id);

}
