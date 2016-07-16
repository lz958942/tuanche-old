package com.tuanche.mapper.che.read;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.CarstyleGroupbuy;

public interface CarstyleGroupbuyReadMapper {
	public int check(@Param(value="cityId")int cityId,@Param(value="carstyleId")int carstyleId);
	public CarstyleGroupbuy find(int id);
	public List<CarstyleGroupbuy> select(Map<String,Object> map);
	public int count(Map<String,Object> map);
	public int maxSort(@Param(value="cityId")int cityId,@Param(value="brandId")int brandId);
	public  List<CarstyleGroupbuy> findByBrandId(@Param("bid")int bid,@Param("stateName")Integer stateName);
	public List<CarstyleGroupbuy> findByBrand(@Param("pid")Integer pid,@Param("cid")Integer cid);
	public List<CarstyleGroupbuy> findCountIsDelBrand(@Param("bid")int brandId,@Param("status")int status);
}
