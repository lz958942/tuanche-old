package com.tuanche.mapper.che.read;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.PicColour;

public interface PicColourReadMapper {

	PicColour findByName(@Param("colourName")String colourName);

}
