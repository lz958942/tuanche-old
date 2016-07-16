package com.tuanche.mapper.che.write;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.Recommendation;


public interface RecommendationWriteMapper {

	void saveR12n(ArrayList<Recommendation> list);

	void updateR12n(Recommendation r12n);

	void updateStatusBatch(@Param("ids")String[] ids, @Param("type")Integer type, @Param("uid")Integer uid);

	void updateStatus(@Param("id")Integer id, @Param("type")Integer type, @Param("uid")Integer uid);
	
}