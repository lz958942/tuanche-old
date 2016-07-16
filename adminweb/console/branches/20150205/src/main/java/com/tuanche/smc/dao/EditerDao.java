package com.tuanche.smc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tuanche.commons.util.Resources;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.read.EmployeeReadMapper;
import com.tuanche.smc.common.Common;

@Repository
public class EditerDao {
    @Resource
    private EmployeeReadMapper employeeReadMapper;
    
    public void init(){
        
       String[] split = Resources.getString("EDITER_ROLE_ID").split(",");
       ArrayList<String> list = new ArrayList<String>();
       for(String s:split){
           list.add("%,"+s+",%");
       }
       List<Employee> selectEmployeeByRoleIds = employeeReadMapper.selectEmployeeByRoleIds(list);
       Common.editersMap = new HashMap<String, Employee>();
       for(Employee employee : selectEmployeeByRoleIds){
           Common.editersMap.put(employee.getId()+"",employee);
       }
       Common.setEditers(selectEmployeeByRoleIds);
       Common.init = System.currentTimeMillis();
    }
}
