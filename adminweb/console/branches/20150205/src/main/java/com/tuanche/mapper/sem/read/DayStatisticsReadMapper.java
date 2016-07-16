package com.tuanche.mapper.sem.read;

import java.util.List;


import com.tuanche.bean.che.Search;
import com.tuanche.bean.sem.DayStatistics;

public interface DayStatisticsReadMapper {
	public List<DayStatistics> select(Search search);
	public DayStatistics selectwhere(Search search);
	public int count(Search search);
	public List<DayStatistics> selectListGroupCity(Search search);
	public List<DayStatistics> selectListGroupCityAndBrand(Search search);
	
}
