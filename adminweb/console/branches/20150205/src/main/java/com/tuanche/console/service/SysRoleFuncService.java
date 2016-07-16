package com.tuanche.console.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.console.bean.SysRole;
import com.tuanche.console.read.SysRoleFuncReadMapper;
import com.tuanche.console.read.SysRoleReadMapper;
import com.tuanche.console.util.GlobalConstants;

@Service
public class SysRoleFuncService {
	@Autowired
	private SysRoleReadMapper sysRoleReadMapper;
	@Autowired
	private SysRoleFuncReadMapper sysRoleFuncReadMapper;
	
	public ConcurrentMap<Integer,List<String>> selectRoleAuth(){
		Map parameMap = new HashMap();
		parameMap.put("cpage",0);
		parameMap.put("pageSize", Integer.MAX_VALUE);
		parameMap.put("orderStr","id");
		parameMap.put("isDel",GlobalConstants.BOOLEAN_N);
		List<SysRole> list=sysRoleReadMapper.selectSysRoleList(parameMap);
		ConcurrentMap<Integer,List<String>>  authMap=new  ConcurrentHashMap();
		for(SysRole role:list){
			authMap.put(role.getId(),sysRoleFuncReadMapper.selectSysRoleFuncURL(role.getId()));
		}
		return authMap;
		
	}
	
	public List<String> selectSysRoleFuncURL(int roleId){
		return sysRoleFuncReadMapper.selectSysRoleFuncURL(roleId);
	}
}
