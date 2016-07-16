package com.tuanche.mapper.sem.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.Search;
import com.tuanche.bean.sem.DayStatistics;

public interface DayStatisticsWriteMapper {
	public void insertList(@Param(value="dsList")List<DayStatistics> list); 
	
	public int delete(Search search);
	
}
