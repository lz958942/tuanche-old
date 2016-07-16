package com.tuanche.mapper.erp.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.erp.TCrmCustomer;


public interface TCrmCustomerMapper {
    TCrmCustomer selectByPrimaryKey(Integer id);
    
    List<TCrmCustomer> selectByLikeName(@Param("customer")String customer);
    
}