package com.tuanche.mapper.che.read;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.City;



public interface AdCityReadMapper {
	List<City> getCity();
	
	City getCityById(@Param("id")Integer cityId);
	
	List<City> getCityByName(@Param("dname")String dname);
}
