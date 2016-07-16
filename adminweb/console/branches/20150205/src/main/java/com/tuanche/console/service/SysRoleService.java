package com.tuanche.console.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.console.read.SysRoleReadMapper;

@Service
public class SysRoleService {
	@Autowired
	private SysRoleReadMapper sysRoleReadMapper;
	
	
	public List selectSysRoleList(Map map){
		return sysRoleReadMapper.selectSysRoleList(map);
	}
}
