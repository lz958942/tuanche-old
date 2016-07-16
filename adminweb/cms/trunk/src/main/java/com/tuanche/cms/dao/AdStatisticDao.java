package com.tuanche.cms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.tuanche.cms.adminread.AdStatistcsReadMapper;
import com.tuanche.cms.bean.AdStatistic;
@Repository
public class AdStatisticDao {
	@Autowired
	AdStatistcsReadMapper AdStatistcsReadMapper;
	public List<AdStatistic>  queryAdStatistic(AdStatistic adStatistic){
		return AdStatistcsReadMapper.queryAdStatistic(adStatistic);
	}
	public int count(AdStatistic adStatistic){
		return AdStatistcsReadMapper.count(adStatistic);
	}

}
