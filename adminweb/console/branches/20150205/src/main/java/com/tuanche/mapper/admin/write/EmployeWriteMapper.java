package com.tuanche.mapper.admin.write;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.Employe;


/**
 * 
 * @author wtk
 *
 */

@Repository
public interface EmployeWriteMapper {
	
	void addEmploye(Employe employe);
	
	void updateEmploye(Employe employe);

	void updateEmpStatus(@Param("employeStatus")Byte employeStatus,@Param("updateEmp")Integer updateEmp,@Param("id")Integer id);
}
