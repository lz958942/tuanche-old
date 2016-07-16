package com.tuanche.mapper.che.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.EvaluatePic;

public interface EvaluatePicMapper {

	List<EvaluatePic> getEvaluatePic(@Param("id")Integer id);

	List<EvaluatePic> selectPassPic(@Param("id")Integer id);

	EvaluatePic getPicSrc(@Param("eid")Integer id);

}
