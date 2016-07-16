package com.tuanche.console.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.console.bean.SysAuthEmp;
import com.tuanche.console.read.SysAuthEmpReadMapper;

@Service
public class SysAuthEmpService {
	@Autowired
	private SysAuthEmpReadMapper sysAuthEmpReadMapper;
	
	
	public SysAuthEmp selectSysAuthEmpByEmpId(Integer empId){
		return sysAuthEmpReadMapper.selectSysAuthEmpByEmpId(empId);
	}
}
