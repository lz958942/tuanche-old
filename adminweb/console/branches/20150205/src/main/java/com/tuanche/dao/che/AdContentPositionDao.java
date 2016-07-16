package com.tuanche.dao.che;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.che.AdContentPosition;
import com.tuanche.mapper.che.read.AdContentPositionReadMapper;
import com.tuanche.mapper.che.write.AdContentPositionWriteMapper;

import java.util.List;
@Repository
public class AdContentPositionDao {
	@Autowired
	AdContentPositionReadMapper adContentPositionReadMapper;
	@Autowired
	AdContentPositionWriteMapper adContentPositionWriteMapper;
	public List<AdContentPosition> queryAdContentPosition(AdContentPosition adContentPosition){
		return adContentPositionReadMapper.queryAdContentPositionByPage(adContentPosition);
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
	public void deleteContentPositionById(int contentPositionId,Integer sta){
			adContentPositionWriteMapper.deleteContentPositionById(contentPositionId,sta);
	}
	public List<AdContentPosition> getPositionByCityId(Integer cityId){
		return adContentPositionReadMapper.getPositionByCityId(cityId);
	}
	public void updateImg(Integer id, String newPath, String picTitle, String picDesc,int uid,String turl) {
		adContentPositionWriteMapper.updateImg(id,newPath,picTitle,picDesc,uid,turl);
	}
	
}
