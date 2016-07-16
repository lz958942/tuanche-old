package com.tuanche.console.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.console.bean.Employee;
import com.tuanche.console.bean.MapBean;
import com.tuanche.console.read.EmployeeReadMapper;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeReadMapper employeeReadMapper;
	
	
	public Employee selectEmployeeById(Integer id){
    	return employeeReadMapper.selectEmployeeById(id);
    }
	
	public List<MapBean> selectEmployeeInit(Map map){
		return employeeReadMapper.selectEmployeeInit(map);
	}
	public Employee  selectEmployeeByEmpLogin(String empLogin){
    	return  employeeReadMapper.selectEmployeeByEmpLogin(empLogin);
    }
}
