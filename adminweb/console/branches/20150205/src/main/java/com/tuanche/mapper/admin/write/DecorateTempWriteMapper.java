package com.tuanche.mapper.admin.write;

import java.util.List;

import com.tuanche.bean.admin.DecorateTemp;

public interface DecorateTempWriteMapper {

	/**
	 * @param list
	 * @author liuhg
	 * @Description 添加板块标题
	 */
	void addDecPlate(List<DecorateTemp> list);

	/**
	 * @param id
	 * @author liuhg
	 * @Description 删除小标题
	 */
	void deletePlate(Integer id);

	/**
	 * @param decorateTemp
	 * @author liuhg
	 * @Description 更新小标题
	 */
	void updatePlate(DecorateTemp decorateTemp);

}
