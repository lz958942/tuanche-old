package com.tuanche.dao.erp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.erp.TCrmCustomer;
import com.tuanche.mapper.erp.read.TCrmCustomerMapper;

@Repository
public class TCrmCustomerDao {
	@Autowired
	private TCrmCustomerMapper crmCustomerMapper;
	
	public TCrmCustomer getById(int id){
		return crmCustomerMapper.selectByPrimaryKey(id);
	}
	
	public List<TCrmCustomer> getListByName(String name){
		return crmCustomerMapper.selectByLikeName(name);
	}
}
