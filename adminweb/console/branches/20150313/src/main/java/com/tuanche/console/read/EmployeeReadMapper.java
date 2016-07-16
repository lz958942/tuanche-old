package com.tuanche.console.read;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tuanche.console.bean.Employee;
import com.tuanche.console.bean.MapBean;


public interface EmployeeReadMapper {
	Employee selectEmployeeById(Integer id);
	List<MapBean> selectEmployeeInit(Map map);
	Employee  selectEmployeeByEmpLogin(String empLogin);
	List<Employee> selectEmployeeByRoleIds(@Param(value="roleIds")List<String> roleId);
}
