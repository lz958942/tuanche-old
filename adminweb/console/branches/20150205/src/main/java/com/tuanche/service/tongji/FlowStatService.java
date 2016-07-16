package com.tuanche.service.tongji;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tuanche.bean.fcharts.Category;
import com.tuanche.bean.fcharts.Chart;
import com.tuanche.bean.fcharts.DataSet;
import com.tuanche.bean.fcharts.FusionCharts;
import com.tuanche.bean.fcharts.Label;
import com.tuanche.bean.fcharts.Value;
import com.tuanche.bean.tongji.FlowStat;
import com.tuanche.bean.tongji.RecordStat;
import com.tuanche.dao.tongji.FlowStatDao;

@Service
public class FlowStatService {

	private Logger logger = Logger.getLogger(FlowStatService.class);

	
	@Autowired
	private FlowStatDao flowStatDao;
	
	private SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
	private SimpleDateFormat sdf2 = new SimpleDateFormat("yy-MM-dd");

	private Map<String, String> typesMap;
	
	@PostConstruct
	public void init() {
		typesMap = new HashMap<String, String>();
		typesMap.put("sem", "uv");
		typesMap.put("seo", "pv");
	}
	
	private double getPvuv(int pv, int uv) {
		if(uv == 0) {
			return 0;
		}
		Integer tempPv = pv;
		Integer tempUv = uv;
        BigDecimal bd = new BigDecimal(tempPv.doubleValue() / tempUv.doubleValue());
		return bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public List<FlowStat> selectDomain(){
		return flowStatDao.selectDomain();
	}
	/**
	 * all
	 * @return
	 */
	public List<FlowStat> selectAllList(FlowStat flowStat){
		return flowStatDao.selectAllList(flowStat);
	}
	/**
	 * all
	 * @return
	 */
	public List<FlowStat> seleAllList(FlowStat flowStat){
		return flowStatDao.seleAllList(flowStat);
	}
	/**
	 * ..
	 * @return
	 */
	public List<FlowStat> selectDate(){
		return flowStatDao.selectDate();
	}
	/**
	 * ..
	 * @param flowStat
	 * @return
	 */
	public List<FlowStat> queryPvtotalListByPage(FlowStat flowStat){
		return flowStatDao.queryPvtotalListByPage(flowStat);
	}
	
	public List<FlowStat> seleAllDate(FlowStat flowStat){
		return flowStatDao.seleAllDate(flowStat);
	}
	public List<FlowStat> queryPvtotalList(FlowStat flowStat){
		return flowStatDao.queryPvtotalList(flowStat);
	}
	/**
	 * 添加流量统计
	 * @param flowStat
	 */
	public void addFlowStat(FlowStat flowStat) {
		flowStat.setPvuv(getPvuv(flowStat.getPv(), flowStat.getUv()));
		flowStat.setDataType("seo");
		flowStatDao.addFlowStat(flowStat);
	}

	/**
	 * 编辑流量统计
	 * @param flowStat
	 */
	public void updateFlowStat(FlowStat flowStat) {
		flowStat.setPvuv(getPvuv(flowStat.getPv(), flowStat.getUv()));
		flowStatDao.updateFlowStat(flowStat);
	}
	
	/**
	 * 根据条件查询流量统计列表
	 * @param flowStat 查询条件
	 * @return
	 */
	public List<FlowStat> queryFlowStatList(FlowStat flowStat) {
		return flowStatDao.queryFlowStatList(flowStat);
	}
	
	public List<FlowStat> queryFlowStatListByPage(FlowStat flowStat) {
		return flowStatDao.queryFlowStatListByPage(flowStat);
	}
	
	/**
	 * 根据id查询流量统计
	 * @param id 主键id
	 * @return
	 */
	public FlowStat queryFlowStatById(int id) {
		return flowStatDao.queryFlowStatById(id);
	}
	
	public void deleteFlowStat(int id) {
		flowStatDao.deleteFlowStat(id);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public boolean uploadExcel(InputStream stream) {
		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(stream);
		    Sheet sheet = workbook.getSheet(0);
		    for(int i=1; i<sheet.getRows(); i++) {
	    		logger.debug(sheet.getCell(0, i).getContents());
	    		logger.debug(sheet.getCell(1, i).getContents());
	    		logger.debug(sheet.getCell(2, i).getContents());
		    	FlowStat flowStat = new FlowStat();
		    	String date = sheet.getCell(0, i).getContents();
		    	flowStat.setDate(date);
		    	flowStat.setDomain(sheet.getCell(1, i).getContents());
		    	flowStat.setUv(Integer.valueOf(sheet.getCell(2, i).getContents()));
		    	flowStat.setDataType("sem");
				if(date.indexOf("/") != -1) {
					flowStatDao.importFlowStat(flowStat);
				} else {
		    		flowStatDao.addFlowStat(flowStat);
				}
		    }
		    return true;
		} catch (Exception e) {
			logger.warn(e.getMessage(), e.fillInStackTrace());
		}
		return false;
	}
	
	/**
	 * 生成数据
	 * @param startDate
	 * @param endDate
	 * @param domain
	 * @param dataType
	 * @param types
	 * @return
	 */
	public FusionCharts getChartDataByTypes(String startDate, 
											String endDate, 
											String domain, 
											String dataType,
											String from,
											String type,
											List<String> types) {
		logger.debug("startDate: " + startDate + ", endDate: " + endDate);
		try {
			FlowStat flowStat = new FlowStat(); //实体
			flowStat.setStartDate(startDate);	//开始时间
			flowStat.setEndDate(endDate);		//结束时间	
			flowStat.setDomain(domain);			//域名
			flowStat.setDataType(dataType);		//数据类型
			flowStat.setFrom(from);				//数据来源    百度统计/网页日志
			flowStat.setType(type);				//数据类别  PV/UV
			List<FlowStat> flowList=new ArrayList<FlowStat>();
			List<FlowStat> pvtotalList=new ArrayList<FlowStat>();
			List<FlowStat> dates=new ArrayList<FlowStat>();
			Map<String,FlowStat> flowMap=new HashMap<String, FlowStat>();
			Map<String,FlowStat> pvtotalMap=new HashMap<String, FlowStat>();
			//判断查那个表
			if(from.equals("百度统计")){
			 flowList = this.queryFlowStatList(flowStat); //
			 if(flowList.size()>0){
				 for(FlowStat f:flowList){
					 flowMap.put(f.getDate(),f);
				 }
			 }			 
			 dates=flowList;
			}else  if(from.equals("网页日志")){
				pvtotalList=this.queryPvtotalList(flowStat);
				if(pvtotalList.size()>0){
					for(FlowStat f:pvtotalList){
					pvtotalMap.put(f.getDate(),f);	
					}
				}
				dates=pvtotalList;
			}else{
				flowList=this.queryFlowStatList(flowStat);
				pvtotalList=this.queryPvtotalList(flowStat);
					if(flowList.size()>0){
						for(FlowStat f:flowList){
							flowMap.put(f.getDate(),f);
						}
					}
					if(pvtotalList.size()>0){
						for(FlowStat f:pvtotalList){
							pvtotalMap.put(f.getDate(),f);
						}
					}
					
				dates=this.seleAllDate(flowStat);
			}

			
			
			FusionCharts charts = new FusionCharts();
			Chart chart = new Chart();
			Category category = new Category();
			List<Category> categories = new ArrayList<Category>();
			List<DataSet> dataset = new ArrayList<DataSet>();
			List<Label> labels = new ArrayList<Label>();
			// 趋势图标题
//			chart.setCaption(startDate + "至" + endDate); 
//			if(dataType == null || dataType.equals("") || dataType.equals("null")) {根据页面查询条件展示对应的折线
			//判断pv /uv 数据  设置折现名字及数据，加dateType条件   SUM 合
			// 1.dataType   不选   4个条件          2.   pv/uv   不选  两个条件     sum的 合
			if(dataType.length()>0&&!(type.length()>0)){
				//1.选择  dateType  一个条件 2.  不选dateType     两个条件
				if(flowMap.size()>0){
					
					for(String key : types) {
						DataSet set = new DataSet();
						List<Value> valueList = new ArrayList<Value>();
						set.setSeriesname("百度"+dataType+typesMap.get(key)); //设置折线名字
					
						for(FlowStat flow : dates) {
							Value value = new Value();
							for(String dat:flowMap.keySet()){
								if(flowMap.containsKey(flow.getDate())){
									if(dat.equals(flow.getDate())){
									value.setValue(String.valueOf(BeanUtils.getProperty(flowMap.get(dat), typesMap.get(key))));
									}
								}else{
									value.setValue("0");
									break;
								}
							}
							valueList.add(value);
						}
						
						set.setData(valueList);
						dataset.add(set);
					}
					}
					if(pvtotalMap.size()>0){
					for(String key : types) {
						DataSet set = new DataSet();
						List<Value> valueList = new ArrayList<Value>();
						set.setSeriesname("网页"+dataType+typesMap.get(key)); //设置折线名字
					
						for(FlowStat flow : dates) {
							Value value = new Value();
							for(String dat:pvtotalMap.keySet()){
								if(pvtotalMap.containsKey(flow.getDate())){
									if(dat.equals(flow.getDate())){
										value.setValue(String.valueOf(BeanUtils.getProperty(pvtotalMap.get(dat),typesMap.get(key))));
									}
								}else{
									
									value.setValue("0");
								}
							}
							
							valueList.add(value);
						}
						
						set.setData(valueList);
						dataset.add(set);
					}
					}
				
			}else if(type.length()>0&&!(dataType.length()>0)){
				//1.选择  dateType  一个条件 2.  不选dateType     两个条件
					if(flowMap.size()>0){
					
					
						DataSet set = new DataSet();
						List<Value> valueList = new ArrayList<Value>();
						set.setSeriesname("百度"+type); //设置折线名字
					
						for(FlowStat flow : dates) {
							Value value = new Value();
							for(String dat:flowMap.keySet()){
								if(flowMap.containsKey(flow.getDate())){
									if(dat.equals(flow.getDate())){
										value.setValue(String.valueOf(BeanUtils.getProperty(flowMap.get(dat), type)));	
									}
								}else{
									value.setValue("0");
								}
							}
							valueList.add(value);
						}
						
						set.setData(valueList);
						dataset.add(set);
					
					}
					if(pvtotalMap.size()>0){
					
						DataSet set = new DataSet();
						List<Value> valueList = new ArrayList<Value>();
						set.setSeriesname("网页"+type); //设置折线名字
					
						for(FlowStat flow : dates) {
							Value value = new Value();
							for(String dat:pvtotalMap.keySet()){
								if(pvtotalMap.containsKey(flow.getDate())){
									if(dat.equals(flow.getDate())){
										value.setValue(String.valueOf(BeanUtils.getProperty(pvtotalMap.get(dat), type)));
									}
								}else{
									value.setValue("0");
								}
							}
						
							valueList.add(value);
						}
						
						set.setData(valueList);
						dataset.add(set);
					
					}
			}else if(type.length()>0&&dataType.length()>0){

				if(flowMap.size()>0){
					

						DataSet set = new DataSet();
						List<Value> valueList = new ArrayList<Value>();
						set.setSeriesname("百度"+dataType+type); //设置折线名字
					
						for(FlowStat flow : dates) {
							Value value = new Value();
							for(String dat:flowMap.keySet()){
								if(flowMap.containsKey(flow.getDate())){
									if(dat.equals(flow.getDate())){
										value.setValue(String.valueOf(BeanUtils.getProperty(flowMap.get(dat),type)));
									}
								}else{
									value.setValue("0");
								}
							}
							
							valueList.add(value);
						}
						
						set.setData(valueList);
						dataset.add(set);
					
					}
					if(pvtotalMap.size()>0){

						DataSet set = new DataSet();
						List<Value> valueList = new ArrayList<Value>();
						set.setSeriesname("网页"+dataType+type); //设置折线名字
					
						for(FlowStat flow : dates) {
							Value value = new Value();
							for(String dat:pvtotalMap.keySet()){
								if(pvtotalMap.containsKey(flow.getDate())){
									if(dat.equals(flow.getDate())){
										value.setValue(String.valueOf(BeanUtils.getProperty(pvtotalMap.get(dat), type)));	
									}
								}else{
									value.setValue("0");
								}
							}
						
							valueList.add(value);
						}
						
						set.setData(valueList);
						dataset.add(set);
					
					}
				
			
				
			}else if(!(type.length()>0)&&!(dataType.length()>0)){
				
				if(flowMap.size()>0){
				
				for(String key : types) {
					DataSet set = new DataSet();
					List<Value> valueList = new ArrayList<Value>();
					set.setSeriesname("百度"+typesMap.get(key)); //设置折线名字
				
					for(FlowStat flow : dates) {
						Value value = new Value();
						for(String dat:flowMap.keySet()){
							if(flowMap.containsKey(flow.getDate())){
								if(dat.equals(flow.getDate())){
									value.setValue(String.valueOf(BeanUtils.getProperty(flowMap.get(dat), typesMap.get(key))));	
								}
							}else{
								value.setValue("0");
							}
						}
						
						valueList.add(value);
					}
					
					set.setData(valueList);
					dataset.add(set);
				}
				}
				if(pvtotalMap.size()>0){
				for(String key : types) {
					DataSet set = new DataSet();
					List<Value> valueList = new ArrayList<Value>();
					set.setSeriesname("网页"+typesMap.get(key)); //设置折线名字
				
					for(FlowStat flow : dates) {
						Value value = new Value();
						for(String dat:pvtotalMap.keySet()){
							if(pvtotalMap.containsKey(flow.getDate())){
								if(dat.equals(flow.getDate())){
									value.setValue(String.valueOf(BeanUtils.getProperty(pvtotalMap.get(dat), typesMap.get(key))));	
								}
							}else{
								value.setValue("0");
							}
						}
						
						valueList.add(value);
					}
					
					set.setData(valueList);
					dataset.add(set);
				}
				}
			}
//			}
			/*
			else {
				DataSet set = new DataSet();
				List<Value> valueList = new ArrayList<Value>();
				set.setSeriesname(typesMap.get(dataType));
				for(FlowStat flow : flowList) {
					Value value = new Value();
					value.setValue(String.valueOf(BeanUtils.getProperty(flow, typesMap.get(dataType))));
					valueList.add(value);
				}
				set.setData(valueList);
				dataset.add(set);
			
			}
			*/
			for(FlowStat stat : dates) {//设置x轴时间条件
				Label label = new Label();
				label.setLabel(stat.getDate());
				labels.add(label);
			}
			category.setCategory(labels);
			categories.add(category);
			// 设置标题
			charts.setChart(chart);
			// 设置横坐标数据
			charts.setCategories(categories);
			// 设置数据集
			charts.setDataset(dataset);
			return charts;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询是否存在重复的数据
	 * @param flowStat
	 * @return
	 */
	public int isExist(FlowStat flowStat) {
//		return flowStatDao.isExist(flowStat) == 0 ? false : true;
		return flowStatDao.isExist(flowStat);
	}
	
	/**
	 * 根据页面指定日期范围删除数据
	 * @param startDate
	 * @param endDate
	 */
	public void deleteFlowStatByDate(String startDate, String endDate) {
		FlowStat flowStat = new FlowStat();
		flowStat.setStartDate(startDate);
		flowStat.setEndDate(endDate);
		flowStat.setDataType("sem");
		flowStatDao.deleteFlowStatByDate(flowStat);
	}

}
