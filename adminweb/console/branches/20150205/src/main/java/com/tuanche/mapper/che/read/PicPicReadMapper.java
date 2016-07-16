package com.tuanche.mapper.che.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.PicPic;

public interface PicPicReadMapper {

	List<PicPic> getPicAllByPage(@Param("condition")List<String> condition);

	PicPic findByID(@Param("id")Integer id);

}
