package com.tuanche.cms.cheread;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.cms.bean.City;


public interface CityReadMapper {
	List<City> getCity();
	
	City getCityById(@Param("id")Integer cityId);
	
	List<City> getCityByName(@Param("dname")String dname);
}
