package com.tuanche.mapper.admin.read;

import java.util.List;

import com.tuanche.bean.admin.DecorateBase;

public interface DecorateBaseReadMapper {

	/**
	 * @param condition
	 * @return
	 * @author liuhg
	 * @Description 装饰条件查询
	 */
	List<DecorateBase> selectByPage(List<String> condition);

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询
	 */
	DecorateBase selectById(Integer id);

	
}
