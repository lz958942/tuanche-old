package com.tuanche.cms.read;

import com.tuanche.cms.bean.SysAuthEmp;


public interface SysAuthEmpReadMapper {
	SysAuthEmp selectSysAuthEmpByEmpId(Integer empId);
}
