package com.tuanche.cms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.tuanche.cms.bean.AdPositionTime;
import com.tuanche.cms.adminwrite.AdPositionTimeWriteMapper;
import com.tuanche.cms.adminread.AdPositionTimeReadMapper;
import com.tuanche.cms.bean.AdGet;


@Repository
public class AdPositionTimeDao {
	@Autowired
	AdPositionTimeWriteMapper adPositionTimeWriteMapper;
	@Autowired
	AdPositionTimeReadMapper adPositionTimeReadMapper;
	public List<AdPositionTime> queryAdPositionTime(AdPositionTime adPositionTime){
		return  adPositionTimeReadMapper.queryAdPositionTime(adPositionTime);
	}
	public  AdPositionTime queryAdPositionTimeById(int id){
		 return adPositionTimeReadMapper.queryAdPositionTimeById(id);
	}
	public void adAdPositionTime(AdPositionTime adPositionTime){
		adPositionTimeWriteMapper.adAdPositionTime( adPositionTime);
	}
	public void updateAdPositionTime(AdPositionTime adPositionTime){
		adPositionTimeWriteMapper.updateAdPositionTime(adPositionTime);
	}
	public int count(AdPositionTime adPositionTime){
		return adPositionTimeReadMapper.count(adPositionTime);
	}
	public List<AdGet> adGet(AdGet adGet){
		 List<AdGet> adGet2 = adPositionTimeReadMapper.adGet(adGet);
		if(adGet2!= null && adGet2.size() >0){
			return adGet2;
		}
		return null;
	}
	public void openAdPositionTime(int id,int status){
		adPositionTimeWriteMapper.openAdPositionTime(id,status);
	}
	public Integer searchAdPositionTime(AdPositionTime adPositionTime){
		return adPositionTimeReadMapper.searchAdPositionTime(adPositionTime);
	}
	public void deleteById(Integer id){
		adPositionTimeWriteMapper.deleteById(id);
	}
}
