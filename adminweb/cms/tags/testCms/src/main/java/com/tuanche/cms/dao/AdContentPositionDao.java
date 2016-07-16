package com.tuanche.cms.dao;
import com.tuanche.cms.bean.AdContentPosition;
import com.tuanche.cms.adminread.AdContentPositionReadMapper;
import com.tuanche.cms.adminwrite.AdContentPositionWriteMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdContentPositionDao {
	@Autowired
	AdContentPositionReadMapper adContentPositionReadMapper;
	@Autowired
	AdContentPositionWriteMapper adContentPositionWriteMapper;
	public List<AdContentPosition> queryAdContentPosition(AdContentPosition adContentPosition){
		return adContentPositionReadMapper.queryAdContentPosition(adContentPosition);
	}
	public void openContentLocation(int contentPositionId,int status){
		adContentPositionWriteMapper.openContentLocation(new AdContentPosition(contentPositionId,status));
	}
	public int count(AdContentPosition adContentPosition){
		return adContentPositionReadMapper.count(adContentPosition);
	}
	public List<AdContentPosition> queryAdContentPositionAll(){
		return adContentPositionReadMapper.queryAdContentPositionAll();
	}
	public AdContentPosition  queryAdContentPositionById(int id){
		return adContentPositionReadMapper. queryAdContentPositionById(id);
	}
	public void deleteContentPositionById(int contentPositionId){
		adContentPositionWriteMapper.deleteContentPositionById(contentPositionId);
	}
	public List<AdContentPosition> getPositionByCityId(Integer cityId){
		return adContentPositionReadMapper.getPositionByCityId(cityId);
	}
	
}
