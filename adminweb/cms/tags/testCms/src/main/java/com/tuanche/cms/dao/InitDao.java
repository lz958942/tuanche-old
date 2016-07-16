package com.tuanche.cms.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.cms.bean.Brand;
import com.tuanche.cms.bean.City;
import com.tuanche.cms.bean.Style;
import com.tuanche.cms.service.EmployeeService;
import com.tuanche.cms.util.GlobalConstants;
import com.tuanche.commons.util.StringUtils;

@Repository
public class InitDao {
	@Autowired
	private InfoDao infoDao;
	@Autowired
	private EmployeeService employService;
	public void init(){
		initCity();
		initBrand();
		initStyle();
	}
	private void initCity(){
		List<City> cityList=infoDao.getCity();
		if(cityList!=null){
			for(City tmp:cityList){
				GlobalConstants.cityMap.put(tmp.getId(), tmp.getDname());
				GlobalConstants.cityAllMap.put(tmp.getId(), tmp);
				GlobalConstants.cityMapPY.put(tmp.getId(),  tmp.getOrderName());
				if(StringUtils.isNotEmpty(tmp.getCityCode())){
                   GlobalConstants.cityCodeToIdMap.put(tmp.getCityCode(), tmp.getId());
				}
			}
		}
	}
	private  void initStyle() {
		List<Style> styles=infoDao.getStyle();
		if(styles!=null){
			for(Style tmp:styles){
				if(tmp.getBrandId()>0||tmp.getPid()>0){
					GlobalConstants.styleMap.put(tmp.getId(), tmp.getName());
					if(tmp.getBrandId()>0){
						if(!GlobalConstants.styles.containsKey(tmp.getBrandId())){
							GlobalConstants.styles.put(tmp.getBrandId(), new ArrayList<Style>());
						}
						GlobalConstants.styles.get(tmp.getBrandId()).add(tmp);
					}
					if(tmp.getPid()>0){
						if(!GlobalConstants.cars.containsKey(tmp.getPid())){
							GlobalConstants.cars.put(tmp.getPid(), new ArrayList<Style>());
						}
						GlobalConstants.cars.get(tmp.getPid()).add(tmp);
					}
				}
				
			}
		}
		
	}

	private  void initBrand() {
		// TODO Auto-generated method stub
		GlobalConstants.brands=infoDao.getBrand();
		if(GlobalConstants.brands!=null){
			for(Brand tmp:GlobalConstants.brands){
				GlobalConstants.brandMap.put(tmp.getId(), tmp.getName());
				GlobalConstants.brandSeriesById.put(tmp.getId(),tmp.getNewSeriesId());
			}
		}
		
	}
}
