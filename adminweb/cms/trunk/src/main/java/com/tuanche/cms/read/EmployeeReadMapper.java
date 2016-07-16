package com.tuanche.cms.read;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tuanche.cms.bean.Employee;
import com.tuanche.cms.bean.MapBean;


public interface EmployeeReadMapper {
	Employee selectEmployeeById(Integer id);
	List<MapBean> selectEmployeeInit(Map map);
	Employee  selectEmployeeByEmpLogin(String empLogin);
	
	List<Employee>  selectEmployeeByIds(@Param(value="ids")String ids);
	
	List<Employee> selectEmployeeByCityId(@Param(value="city_code")String city_code,@Param("limit")int limit);
	
}
