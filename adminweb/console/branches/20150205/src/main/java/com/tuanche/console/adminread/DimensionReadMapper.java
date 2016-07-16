package com.tuanche.console.adminread;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.console.bean.Dimension;

public interface DimensionReadMapper {
	
	/**
	 * @param conditions
	 * @return
	 * @author liuhg
	 * @Description 分页查询维度
	 */
	public List<Dimension> queryByPage(@Param("conditions")List<String> conditions);

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 更新前查询
	 */
	public Dimension selectDimensionById(Integer id);


	/**
	 * @return
	 * @author liuhg
	 * @Description 查询操作人员
	 */
	public List<Dimension> selectOperate();

	/**
	 * @return
	 * @author liuhg
	 * @Description 查询维度名称
	 */
	public List<Dimension> selectDieName();

}
