package com.tuanche.cms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.cms.bean.AdPosition;
import com.tuanche.cms.adminread.AdPositionReadMapper;

@Repository
public class AdPositionDao {
	@Autowired
	AdPositionReadMapper adPositionReadMapper;
	public List<AdPosition> queryAdPositionAll(){
		return adPositionReadMapper.queryAdPositionAll();
	}
	public List<AdPosition> findCityById(int adContentId){
		return adPositionReadMapper.findCityById(adContentId);
	}
}
