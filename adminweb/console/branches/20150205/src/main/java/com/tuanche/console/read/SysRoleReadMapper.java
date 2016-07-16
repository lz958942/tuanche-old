package com.tuanche.console.read;

import java.util.List;
import java.util.Map;

import com.tuanche.console.bean.SysRole;


public interface SysRoleReadMapper {
	List<SysRole> selectSysRoleList(Map map);
}
