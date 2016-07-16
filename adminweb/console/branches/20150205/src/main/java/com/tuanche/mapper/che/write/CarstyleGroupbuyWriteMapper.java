package com.tuanche.mapper.che.write;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.BrandGroupbuy;
import com.tuanche.bean.che.CarstyleGroupbuy;

public interface CarstyleGroupbuyWriteMapper {
	public int insertList(List<CarstyleGroupbuy> list);
	public int insert(CarstyleGroupbuy carstyleGroupbuy);
	public int update(CarstyleGroupbuy carstyleGroupbuy);
	public int updateTime(BrandGroupbuy brandGroupbuy);
	public int updateState(@Param(value="id")int id,@Param(value="cityId")int cityId,@Param(value="brandId")int brandId,@Param(value="state")int state);
		//团购删除
	public void del(@Param("id")int id,@Param("eid") int eid,@Param("type")Integer type);
	public void batchUpdate(@Param("stateName")Integer stateName,@Param("content") String content, @Param("uid")Integer uid,
							@Param("cityId")Integer cityId);
	
	public void updateCarStyleSort(@Param("id")int id,@Param("cityId")Integer cityId,@Param(value="brandId")int brandId,@Param(value="baseSeq")int baseSeq,@Param(value="sort_old")int sort_old);

}
