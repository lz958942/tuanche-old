package com.tuanche.console.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.console.bean.SysFunc;
import com.tuanche.console.read.SysFuncReadMapper;

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
