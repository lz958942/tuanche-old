package com.tuanche.mapper.che.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.BrandDomain;
public interface BrandkReadMapper {

	List<BrandDomain> selectAllByPage(@Param("conditions")List<String> conditions);

	BrandDomain getBrandBuId(@Param("stairBrand")int stairBrand);

	List<BrandDomain> electToBrandList(@Param("stairBrand")int stairBrand);

	List<BrandDomain> selectBrandAll();

	List<BrandDomain> selectTwoBrandSeel(@Param("conditions")List<String> conditions);

	List<BrandDomain> getBrandByName(@Param("name")String name);

	List<BrandDomain> getBrandNameId();

	BrandDomain updateBrandPicBeff(@Param("id")Integer id);

}
