package com.tuanche.mapper.che.read;


import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.tuanche.bean.che.AdPosition;
public interface AdPositionReadMapper {
	public List<AdPosition> queryAdPositionAll();
	public List<AdPosition> findCityById(@Param(value="adContentId")Integer adContentId);
}
