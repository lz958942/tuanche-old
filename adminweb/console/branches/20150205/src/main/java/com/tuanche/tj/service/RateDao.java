package com.tuanche.tj.service;

import java.util.List


;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.tongji.RateDomain;
import com.tuanche.mapper.tongji.read.RateMapper;


@Repository
public class RateDao {
	@Resource
	private RateMapper rateMapper;
	public List<RateDomain> queryByPage(RateDomain rateDomain){
		return rateMapper.queryByPage(rateDomain);
	}
	public List<RateDomain> selectRate(RateDomain rateDomain){
		return rateMapper.selectRate(rateDomain);
	}
	public List<RateDomain> selectRatesByPage(RateDomain rateDomain){
		return rateMapper.selectRatesByPage(rateDomain);
	}
	public List<RateDomain> selectRates(RateDomain rateDomain){
		return rateMapper.selectRates(rateDomain);
	}
}
