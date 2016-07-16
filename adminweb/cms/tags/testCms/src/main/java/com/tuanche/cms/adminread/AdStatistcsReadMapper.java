package com.tuanche.cms.adminread;
import com.tuanche.cms.bean.AdStatistic;
import java.util.List;

public interface AdStatistcsReadMapper {
	public List<AdStatistic> queryAdStatistic(AdStatistic adStatistic);
	public int count(AdStatistic adStatistic);
}
