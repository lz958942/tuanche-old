package com.tuanche.dao.tongji;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.tongji.FlowStat;
import com.tuanche.mapper.tongji.read.FlowStatReadMapper;
import com.tuanche.mapper.tongji.read.PvtotalReadMapper;
import com.tuanche.mapper.tongji.write.FlowStatWriteMapper;
import com.tuanche.mapper.tongji.write.PvtotalWriteMapper;

@Repository
public class FlowStatDao {
	
	private Logger logger = Logger.getLogger(FlowStatDao.class);
	
	@Autowired
	private FlowStatWriteMapper flowStatWriteMapper;
	
	@Autowired
	private FlowStatReadMapper flowStatReadMapper;
	@Autowired
	private PvtotalReadMapper pvtotalReadMapper;
	@Autowired
	private PvtotalWriteMapper pvtotalWriteMapper;

/*	@Autowired
	private PvDayReadMapper pvDayReadMapper;
	*//**
	 * 条件查询流量统计pvDay
	 * @return
	 *//*
	public List<PvDay> queryPvDayByPage(PvDay pvDay){
		return pvDayReadMapper.queryPvDayByPage(pvDay);
	}*/

	public List<FlowStat> seleAllDate(FlowStat flowStat){
		return pvtotalReadMapper.seleAllDate(flowStat);
	}

	public List<FlowStat> selectDomain(){
		return pvtotalReadMapper.selectDomain();
	}
	/**
	 * all
	 * @return
	 */
	public List<FlowStat> selectAllList(FlowStat flowStat){
		return pvtotalReadMapper.selectAllList(flowStat);
	}
	/**
	 * all
	 * @return
	 */
	public List<FlowStat> seleAllList(FlowStat flowStat){
		return pvtotalReadMapper.seleAllList(flowStat);
	}
	/**
	 * ..
	 * 
	 * @return
	 */
	public List<FlowStat> selectDate(){
		return pvtotalReadMapper.selectDate();
	}
	/**
	 * ..
	 * @param flowStat
	 * @return
	 */
	public List<FlowStat> queryPvtotalListByPage(FlowStat flowStat){
		
		return pvtotalReadMapper.queryFlowStatListByPage(flowStat);
	}
	/**
	 * ..
	 * @param flowStat
	 * @return
	 */
	public List<FlowStat> queryPvtotalList(FlowStat flowStat){
		return pvtotalReadMapper.queryFlowStatList(flowStat);
	}
	//@Transactional(rollbackFor = Exception.class)
	public void addFlowStat(FlowStat flowStat) {
		logger.debug("---addFlowStat---");
		flowStatWriteMapper.addFlowStat(flowStat);
	}
	
	public void importFlowStat(FlowStat flowStat) {
		logger.debug("---importFlowStat---");
		flowStatWriteMapper.importFlowStat(flowStat);
	}
	
	public void updateFlowStat(FlowStat flowStat) {
		flowStatWriteMapper.updateFlowStat(flowStat);
	}
	
	public List<FlowStat> queryFlowStatList(FlowStat flowStat) {
		return flowStatReadMapper.queryFlowStatList(flowStat);
	}
	
	public FlowStat queryFlowStatById(int id) {
		return flowStatReadMapper.queryFlowStatById(id);
	}
	
	public void deleteFlowStat(int id) {
		flowStatWriteMapper.deleteFlowStat(id);
	}
	
	public List<FlowStat> queryFlowStatListByPage(FlowStat flowStat) {
		return flowStatReadMapper.queryFlowStatListByPage(flowStat);
	}
	
	public int isExist(FlowStat flowStat) {
		return flowStatReadMapper.isExist(flowStat);
	}
	
	public void deleteFlowStatByDate(FlowStat flowStat) {
		flowStatWriteMapper.deleteFlowStatByDate(flowStat);
	}
}
