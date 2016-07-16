package com.tuanche.smc.persistence.read.che100;

import java.util.List;

import com.tuanche.bean.che.City;

public interface CityMapper {
	 List<com.tuanche.smc.domain.base.CityDist> getCityDist(Integer pid);
	 
	 List<City> getCitys();
}
