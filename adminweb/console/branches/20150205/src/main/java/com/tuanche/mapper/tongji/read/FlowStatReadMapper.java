package com.tuanche.mapper.tongji.read;

import java.util.List;

import com.tuanche.bean.tongji.FlowStat;
import com.tuanche.bean.tongji.RecordStat;

public interface FlowStatReadMapper {
	List<FlowStat> queryFlowStatList(FlowStat flowStat);
	List<FlowStat> queryFlowStatListByPage(FlowStat flowStat);
	FlowStat queryFlowStatById(int id);
	int isExist(FlowStat flowStat);
}
