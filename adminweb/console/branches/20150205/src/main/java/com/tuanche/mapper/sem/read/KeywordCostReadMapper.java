package com.tuanche.mapper.sem.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.Search;
import com.tuanche.bean.sem.Keyword;

public interface KeywordCostReadMapper {
	public List<Keyword> selectKeyword(@Param(value="brandId")int brandId,@Param(value="carstyleId")int carstyleId,
			@Param(value="districtId")int cityId,@Param(value="accountId")int accountId,@Param(value="time")String time);
	public List<Keyword> select(Search search);
	public int count(Search search);
}
