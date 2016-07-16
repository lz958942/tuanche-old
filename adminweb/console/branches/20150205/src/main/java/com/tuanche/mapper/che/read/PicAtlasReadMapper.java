package com.tuanche.mapper.che.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.PicAtlasBean;

public interface PicAtlasReadMapper {

	List<PicAtlasBean> getAtlas();

	PicAtlasBean findByName(@Param("name")String name);

	PicAtlasBean findByid(@Param("id")Integer id);

	List<PicAtlasBean> getAtlasByName(@Param("name")String name);

}
