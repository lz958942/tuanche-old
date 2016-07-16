package com.tuanche.console.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SysRoleFuncReadMapper {
	List<String> selectSysRoleFuncURL(@Param(value="roleId")Integer roleId);
}
