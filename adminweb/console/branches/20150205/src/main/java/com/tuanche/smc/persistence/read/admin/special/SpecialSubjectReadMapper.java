package com.tuanche.smc.persistence.read.admin.special;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.smc.domain.specialSubject.SpecialSubject;

public interface SpecialSubjectReadMapper {

	/**
	 * 分页查询专题信息
	 * @param conditions
	 * @return
	 */
	List<SpecialSubject> queryByPage(@Param("conditions")List<String> conditions);

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询
	 */
	SpecialSubject selectById(Integer id);

	List<SpecialSubject> createFMAll(int i);

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据多个id查询
	 */
	List<SpecialSubject> selectByIds(String[] id);

	List<SpecialSubject> selectSpecialUser();

	SpecialSubject selectTcSpecial();
	
	

}
