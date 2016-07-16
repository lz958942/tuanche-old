package com.tuanche.cms.read;

import java.util.List;
import java.util.Map;

import com.tuanche.cms.bean.SysRole;


public interface SysRoleReadMapper {
	List<SysRole> selectSysRoleList(Map map);
}
