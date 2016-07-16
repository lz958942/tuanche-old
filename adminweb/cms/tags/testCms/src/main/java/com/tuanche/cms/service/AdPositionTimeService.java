package com.tuanche.cms.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.cms.dao.AdPositionTimeDao;
import com.tuanche.cms.bean.AdPositionTime;

@Service
public class AdPositionTimeService {
	@Autowired
	AdPositionTimeDao adPositionTimeDao;
	public List<AdPositionTime> queryAdPositionTime(AdPositionTime adPositionTime){
		return  adPositionTimeDao.queryAdPositionTime(adPositionTime);
	}
	public  AdPositionTime queryAdPositionTimeById(int id){
		 return adPositionTimeDao.queryAdPositionTimeById(id);
	}
	public void adAdPositionTime(AdPositionTime adPositionTime){
		adPositionTimeDao.adAdPositionTime( adPositionTime);
	}
	public void updateAdPositionTime(AdPositionTime adPositionTime){
		adPositionTimeDao.updateAdPositionTime(adPositionTime);
	}
	public int count(AdPositionTime adPositionTime){
		return adPositionTimeDao.count(adPositionTime);
	}
	public void openAdPositionTime(int id,int status){
		adPositionTimeDao.openAdPositionTime(id, status);
	}
	public Integer searchAdPositionTime(AdPositionTime adPositionTime){
		return adPositionTimeDao.searchAdPositionTime(adPositionTime);
	}
	public void deleteById(Integer id){
		 adPositionTimeDao.deleteById(id);
	}
	
}
