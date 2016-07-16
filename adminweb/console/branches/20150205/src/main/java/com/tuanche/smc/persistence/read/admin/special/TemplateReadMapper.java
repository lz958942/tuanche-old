package com.tuanche.smc.persistence.read.admin.special;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.smc.domain.specialSubject.Template;

public interface TemplateReadMapper {


	/**
	 * @param conditions
	 * @return
	 * @author liuhg
	 * @Description 分页查询模板
	 */
	List<Template> queryTempByPage(@Param("conditions")List<String> conditions);

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询模板
	 */
	Template preUpdateTemp(Integer id);

	/**
	 * @return
	 * @author liuhg
	 * @Description 模板下拉列表显示
	 */
	List<Template> selectTemp();

	/**
	 * 
	 * @author liuhg
	 * @return 
	 * @Description 查询去除重复用户
	 */
	List<Template> selectUser();

	
	

}
