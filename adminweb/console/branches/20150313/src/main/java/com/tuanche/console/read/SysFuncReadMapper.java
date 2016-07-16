package com.tuanche.console.read;

import java.util.List;
import java.util.Map;

import com.tuanche.console.bean.SysFunc;

public interface SysFuncReadMapper {
	List<SysFunc> selectSysFuncList(Map map);
	List<SysFunc> selectAuthByRoleId(Integer id);
}
