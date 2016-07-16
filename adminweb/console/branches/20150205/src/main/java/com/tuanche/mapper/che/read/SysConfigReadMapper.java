package com.tuanche.mapper.che.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.SysConfig;

public interface SysConfigReadMapper {

	List<SysConfig> selectAllByPage(@Param("condition")List<String> condition);

	SysConfig updateBefore(@Param("id")Integer id);

	List<SysConfig> verification(@Param("key")String key, @Param("code")String code);
	/**
	 * 根据key集合倒到对象
	 * */
	List<SysConfig> getCodeByKey(@Param("key")List<String> key);
	/**
	 * 根据单个key得到对象
	 * */
	List<SysConfig> getCodeByKeyFromString(@Param("key")String key);
	/**
	 * 根据键值，code得到一个对象
	 * */
	SysConfig getCodeByKeyAndcode(@Param("key")Integer key,@Param("code")Integer code);

	/**
	 * 根据键值，得到一个int
	 * */
	Integer getCodeByKeyResuitInt(@Param("key")String key);
	
}
