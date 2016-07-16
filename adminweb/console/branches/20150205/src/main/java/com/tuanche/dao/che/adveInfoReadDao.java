package com.tuanche.dao.che;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.che.Brand;
import com.tuanche.bean.che.City;
import com.tuanche.mapper.che.read.AdCityReadMapper;
import com.tuanche.mapper.che.read.adveInfoReadMapper;
import com.tuanche.smc.domain.base.Style;
@Repository
public class adveInfoReadDao {
	@Autowired
	private AdCityReadMapper  cityReadMapper;
	@Autowired
	adveInfoReadMapper infoReadMapper;
	public List<City> getCity(){
		return cityReadMapper.getCity();
	}
	public List<Brand> getBrand(){
		return infoReadMapper.getBrand();
	}
	public List<Style> getStyle(){
		return infoReadMapper.getStyle();
	}
	public List<City> getCityByName(String cityName){
		return cityReadMapper.getCityByName(cityName);
	}
}
