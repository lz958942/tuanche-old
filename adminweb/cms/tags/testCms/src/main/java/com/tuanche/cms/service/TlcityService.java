package com.tuanche.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.cms.adminread.TlcityReadMapper;
import com.tuanche.cms.adminwrite.TlcityWriteMapper;
import com.tuanche.cms.bean.Employee;
import com.tuanche.cms.bean.Tlcity;
import com.tuanche.cms.util.ApplicationUtil;

@Service
public class TlcityService {
	
	@Autowired
	private TlcityReadMapper readMapper;
	
	@Autowired
	private TlcityWriteMapper writeMapper;
	
	public List<Tlcity> getTlcityByPage(Tlcity tlcity){
		return readMapper.getTlcityByPage(tlcity);
	}
	
	public int addTlcity(Tlcity tlcity){
		handleAdd(tlcity);
		return writeMapper.insert(tlcity);
	}
	
	public Tlcity getTlcityById(int id){
		return readMapper.selectByPrimaryKey(id);
	}
	
	public int updateTlcity(Tlcity tlcity){
		handleUpdate(tlcity);
		return writeMapper.updateByPrimaryKeySelective(tlcity);
	}
	
	private void  handleAdd(Tlcity tlcity){
		Employee employee = ApplicationUtil.getEmployee();
		tlcity.setCreateUserId(employee.getId());
		tlcity.setCreateUserName(employee.getEmpName());
	}
	
	private void handleUpdate(Tlcity tlcity){
		Employee employee = ApplicationUtil.getEmployee();
		tlcity.setUpdateUserId(employee.getId());
		tlcity.setUpdateUserName(employee.getEmpName());
	}
	
}
