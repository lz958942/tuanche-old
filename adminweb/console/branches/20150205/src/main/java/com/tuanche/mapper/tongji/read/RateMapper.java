package com.tuanche.mapper.tongji.read;


import java.util.List;

import com.tuanche.bean.tongji.RateDomain;


public interface RateMapper {
	List<RateDomain> queryByPage(RateDomain rateDomain);
	List<RateDomain> selectRate(RateDomain rateDomain);

	List<RateDomain> selectRatesByPage(RateDomain rateDomain);
	List<RateDomain> selectRates(RateDomain rateDomain);
	

}
