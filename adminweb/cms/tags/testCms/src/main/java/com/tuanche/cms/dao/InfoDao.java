package com.tuanche.cms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;



import com.tuanche.cms.bean.City;
import com.tuanche.cms.cheread.CityReadMapper;
import com.tuanche.cms.bean.Brand;
import com.tuanche.cms.bean.Style;
import com.tuanche.cms.cheread.InfoReadMapper;

@Repository
public class InfoDao {
	@Autowired
	private CityReadMapper  cityReadMapper;
	@Autowired
	InfoReadMapper infoReadMapper;
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
