package com.tuanche.mapper.che.write;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.BrandGroupbuy;

public interface BrandGroupbuyWriteMapper {
	public int insert(BrandGroupbuy brandGroupbuy);
	public int update(BrandGroupbuy brandGroupbuy);
	public int updateState(@Param(value="id")int id,@Param(value="cityId")int cityId,
			@Param(value="brandId")int brandId,@Param(value="state")int state);
	public void batchUpdate(@Param(value="cityId")Integer cityId, @Param(value="stateName")Integer stateName, @Param(value="content")String content,@Param("uid")int uid);
	public void updateStateByBrandCityId(@Param("pid")Integer pid,@Param("cityId") Integer cityId,@Param("type") int type);
	public void updateStateByBrand(@Param("pid")Integer pid,@Param("type") int type);
	public void brandCascadeDelBycar(@Param("bid")Integer bid);
	public void brandCascadeRestoreBycar(@Param("bid")Integer bid);
	
}
