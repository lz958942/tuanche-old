package com.tuanche.console.read;

import com.tuanche.console.bean.SysAuthEmp;


public interface SysAuthEmpReadMapper {
	SysAuthEmp selectSysAuthEmpByEmpId(Integer empId);
}
