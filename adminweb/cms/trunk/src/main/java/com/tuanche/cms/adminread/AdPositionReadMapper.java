package com.tuanche.cms.adminread;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.cms.bean.AdPosition;

public interface AdPositionReadMapper {
	public List<AdPosition> queryAdPositionAll();
	public List<AdPosition> findCityById(@Param(value="adContentId")Integer adContentId);
}
