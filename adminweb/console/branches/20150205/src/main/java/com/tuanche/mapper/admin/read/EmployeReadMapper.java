package com.tuanche.mapper.admin.read;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.Employe;

/**
 * 
 * @author wtk
 *
 */
@Repository
public interface EmployeReadMapper {

	Employe selectEmployeById(Integer id);

	List<Employe> selectEmpByPage();
}
