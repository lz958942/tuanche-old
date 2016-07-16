package com.tuanche.cms.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.cms.bean.SysFunc;
import com.tuanche.cms.read.SysFuncReadMapper;

@Service
public class SysFuncService {
	@Autowired
	private SysFuncReadMapper sysFuncReadMapper;
	
	public List selectSysFuncList(Map map){
		return sysFuncReadMapper.selectSysFuncList(map);
	}
	
	public List<SysFunc> selectAuthByRoleId(Integer id){
		return sysFuncReadMapper.selectAuthByRoleId(id);
	}
}
