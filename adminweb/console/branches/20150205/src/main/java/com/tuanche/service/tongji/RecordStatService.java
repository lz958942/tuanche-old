package com.tuanche.service.tongji;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.fcharts.Category;
import com.tuanche.bean.fcharts.Chart;
import com.tuanche.bean.fcharts.DataSet;
import com.tuanche.bean.fcharts.FusionCharts;
import com.tuanche.bean.fcharts.Label;
import com.tuanche.bean.fcharts.Value;
import com.tuanche.bean.tongji.FlowStat;
import com.tuanche.bean.tongji.RecordStat;
import com.tuanche.dao.tongji.RecordStatDao;

@Service
public class RecordStatService {

	private Logger logger = Logger.getLogger(RecordStatService.class);
	
	@Autowired
	private RecordStatDao recordStatDao;

	private Map<String, String> types;
	@PostConstruct
	public void init() {
		types = new HashMap<String, String>();
		types.put("indexNumber", "索引量");
		types.put("siteRecord", "网站site收录");
	}
	
	/**
	 * 添加收录统计
	 * @param recordStat
	 */
	public void addRecordStat(RecordStat recordStat) {
		recordStatDao.addRecordStat(recordStat);
	}

	/**
	 * 编辑收录统计
	 * @param recordStat
	 */
	public void updateRecordStat(RecordStat recordStat) {
		recordStatDao.updateRecordStat(recordStat);
	}
	
	public List<RecordStat> queryRecordStatListByPage(RecordStat recordStat) {
		return recordStatDao.queryRecordStatListByPage(recordStat);
	}
	
	/**
	 * 根据条件查询收录统计列表
	 * @param recordStat 查询条件
	 * @return
	 */
	public List<RecordStat> queryRecordStatList(RecordStat recordStat) {
		return recordStatDao.queryRecordStatList(recordStat);
	}
	
	/**
	 * 根据id查询收录统计
	 * @param id 主键id
	 * @return
	 */
	public RecordStat queryRecordStatById(int id) {
		return recordStatDao.queryRecordStatById(id);
	}
	
	public void deleteRecordStat(int id) {
		recordStatDao.deleteRecordStat(id);
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
	public FusionCharts getChartDataByTypes(String startDate, String endDate) {
		logger.debug("startDate: " + startDate + ", endDate: " + endDate);
		try {
			RecordStat flowStat = new RecordStat();
			flowStat.setStartDate(startDate);
			flowStat.setEndDate(endDate);
			List<RecordStat> flowList = this.queryRecordStatList(flowStat);
			FusionCharts charts = new FusionCharts();
			Chart chart = new Chart();
			Category category = new Category();
			List<Category> categories = new ArrayList<Category>();
			List<DataSet> dataset = new ArrayList<DataSet>();
			List<Label> labels = new ArrayList<Label>();
			// 趋势图标题
//			chart.setCaption(startDate + "至" + endDate);
			Set<String> keys = types.keySet();
			for(String key : keys) {
				DataSet set = new DataSet();
				List<Value> valueList = new ArrayList<Value>();
				set.setSeriesname(types.get(key));
				for(RecordStat flow : flowList) {
					Value value = new Value();
					value.setValue(String.valueOf(BeanUtils.getProperty(flow, key)));
					valueList.add(value);
				}
				set.setData(valueList);
				dataset.add(set);
			}
			for(RecordStat stat : flowList) {
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
	 * @param recordStat
	 * @return
	 */
	public int isExist(RecordStat recordStat) {
//		return recordStatDao.isExist(recordStat) == 0 ? false : true;
		return recordStatDao.isExist(recordStat);
	}

}
