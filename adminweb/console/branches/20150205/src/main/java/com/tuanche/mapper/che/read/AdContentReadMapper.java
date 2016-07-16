package com.tuanche.mapper.che.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.AdContent;
public interface AdContentReadMapper {
	List<AdContent>  queryAdContentByPage(AdContent adContent);
	List<String> getGroupName();
	AdContent queryAdContentById(Integer id);
	int count(AdContent adContent);
	List<AdContent>  queryAdContentAll();
	AdContent findByLocationCode(@Param("name")String name);
}
