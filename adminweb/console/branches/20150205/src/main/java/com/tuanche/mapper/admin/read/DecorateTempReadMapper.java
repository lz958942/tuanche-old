package com.tuanche.mapper.admin.read;

import java.util.List;

import com.tuanche.bean.admin.DecorateTemp;

public interface DecorateTempReadMapper {

	/**
	 * @param condition
	 * @return
	 * @author liuhg
	 * @Description 板块条件查询
	 */
	List<DecorateTemp> selectTempByPage(List<String> condition);

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询标题信息
	 */
	DecorateTemp selectPlateById(Integer id);

}
