package com.tuanche.dao.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.GmsCity;
import com.tuanche.mapper.admin.read.GmsCityReadMapper;
import com.tuanche.mapper.admin.write.GmsCityWriteMapper;

@Repository
public class GcmCityDao {
	@Autowired 
	private GmsCityReadMapper cityRead;
	
	@Autowired
	private GmsCityWriteMapper cityWrite;
	
	public List<GmsCity> getByPage(GmsCity gmsCity){
		return cityRead.getByPage(gmsCity);
	}
	
	public int  insert(GmsCity gmsCity){
		handleGcm(gmsCity);
		return cityWrite.addGcm(gmsCity);
	} 
	
	public GmsCity getGcmById(Integer id){
		return cityRead.selectByPrimaryKey(id);
	}
	
	public int updateGcm(GmsCity gmsCity){
		handleGcm(gmsCity);
		return  cityWrite.updateByPrimaryKeySelective(gmsCity);
	}
	
	public Integer getCountById(Integer cityId){
		return cityRead.selectCountById(cityId);
	}
	
	private void  handleGcm(GmsCity city){
		if(city.getIsShow() == null){//非隐藏
			city.setIsShow(1);
		}
	}
}
