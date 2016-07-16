package com.tuanche.mapper.che.read;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.BrandGroupbuy;
import com.tuanche.bean.che.CarstyleGroupbuy;

public interface BrandGroupbuyReadMapper {
	public int check(@Param(value="cityId")int cityId,@Param(value="brandId")int brandId);
	public List<BrandGroupbuy> select(Map<String,Object> map);
	public BrandGroupbuy find(int id);
	public int count(Map<String,Object> map);
	public List<BrandGroupbuy> findByCityId(@Param(value="cityId")Integer cityId,@Param(value="stateName")Integer stateName);
}
