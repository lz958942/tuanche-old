package com.tuanche.mapper.che.read;
import java.util.List;

import com.tuanche.bean.che.AdContentPosition;

public interface AdContentPositionReadMapper {
	public List<AdContentPosition> queryAdContentPositionByPage(AdContentPosition adContentPosition);
	public int count(AdContentPosition adContentPosition);
	public List<AdContentPosition> queryAdContentPositionAll();
	public AdContentPosition queryAdContentPositionById(Integer id);
	
	public List<AdContentPosition> getPositionByCityId(Integer cityId);
	
}
