package com.tuanche.mapper.admin.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.admin.GmsCity;



public interface GmsCityReadMapper {
	List<GmsCity> getByPage(GmsCity gmsCity);
	
    GmsCity selectByPrimaryKey(Integer id);
    
    /**
     * 团车会期数
     * Administrator：zhaojl
     * @param cityId
     * @return
     */
    Integer selectCountById(@Param("cityId")Integer cityId);
}