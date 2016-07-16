package com.tuanche.mapper.tongji.write;

import com.tuanche.bean.tongji.FlowStat;

public interface FlowStatWriteMapper {
	void addFlowStat(FlowStat flowStat);
	void importFlowStat(FlowStat flowStat);
	void updateFlowStat(FlowStat flowStat);
	void deleteFlowStat(int id);
	void deleteFlowStatByDate(FlowStat flowStat);
}
