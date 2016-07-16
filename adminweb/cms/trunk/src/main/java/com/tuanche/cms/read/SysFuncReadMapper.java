package com.tuanche.cms.read;

import java.util.List;
import java.util.Map;

import com.tuanche.cms.bean.SysFunc;

public interface SysFuncReadMapper {
	List<SysFunc> selectSysFuncList(Map map);
	List<SysFunc> selectAuthByRoleId(Integer id);
}
