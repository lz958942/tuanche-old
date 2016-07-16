package com.tuanche.mapper.tongji.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.tongji.FlowStat;

public interface PvtotalReadMapper {
	List<FlowStat> seleAllDate(FlowStat flowStat);
	List<FlowStat> selectAllList(FlowStat flowStat);
	List<FlowStat> seleAllList(FlowStat flowStat);
	List<FlowStat> queryFlowStatList(FlowStat flowStat);
	List<FlowStat> FlowStatListByPage();
	List<FlowStat> queryFlowStatListByPage(FlowStat flowStat);
	List<FlowStat> selectDate();
	List<FlowStat> selectDomain();
}
