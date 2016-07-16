package com.tuanche.mapper.che.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.Recommendation;


public interface RecommendationReadMapper {
	
	List<Recommendation> getR12NHomeByPage(Recommendation r12n);

	Recommendation getR12NByid(@Param("id")Integer id);
	
}