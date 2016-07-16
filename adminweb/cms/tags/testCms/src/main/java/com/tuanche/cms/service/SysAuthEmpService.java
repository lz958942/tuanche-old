package com.tuanche.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.cms.bean.SysAuthEmp;
import com.tuanche.cms.read.SysAuthEmpReadMapper;

@Service
public class SysAuthEmpService {
	@Autowired
	private SysAuthEmpReadMapper sysAuthEmpReadMapper;
	
	
	public SysAuthEmp selectSysAuthEmpByEmpId(Integer empId){
		return sysAuthEmpReadMapper.selectSysAuthEmpByEmpId(empId);
	}
}
