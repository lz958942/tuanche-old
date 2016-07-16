package com.tuanche.smc.persistence.read.admin.zixun;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.smc.domain.zixun.SimpleZixun;
import com.tuanche.smc.domain.zixun.ZiXun;

public interface ZiXunReadMapper {
	
	public List<ZiXun> getAllDefaultZixunByPage();
	
	public List<ZiXun> getAllZixunByPage(@Param("conditions")List<String> conditions);
	
	public List<ZiXun> getAllTodayZixunByPage(@Param("time")String time);
	
	public List<ZiXun> getAllMyTodayZixunByPage(@Param("userId")int userId, @Param("time")String time);
	
	public List<ZiXun> getAllYesterdayZixunByPage(@Param("start")String start, @Param("end")String end);
	
	public List<ZiXun> getAllMyYesterdayZixunByPage(@Param("userId")int userId, @Param("start")String start, @Param("end")String end);
	
	public List<ZiXun> getAllMyZixunByPage(@Param("userId")int userId);
	
	public ZiXun getZixunById(@Param("id")int id);
	
	public List<SimpleZixun> getRelatedZixun(@Param("id")int id, @Param("classId")int classId);
	
	public List<SimpleZixun> getNewRelatedZixun(@Param("classId")int classId);
	
	public List<SimpleZixun> getLatestZixun(@Param("cityId")int cityId, @Param("count")int count);

	public Integer getClickSum(@Param("conditions")List<String> conditions);
}
