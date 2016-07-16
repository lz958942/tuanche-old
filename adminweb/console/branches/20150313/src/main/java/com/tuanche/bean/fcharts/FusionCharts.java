package com.tuanche.bean.fcharts;

import java.io.Serializable;
import java.util.List;


public class FusionCharts implements Serializable {
	
	private Chart chart;
	private List<Category> categories;
	private List<DataSet> dataset;
	
	public FusionCharts() {
	}
	
	public FusionCharts(Chart chart) {
		this.chart = chart;
	}
	
	public Chart getChart() {
		return chart;
	}
	public void setChart(Chart chart) {
		this.chart = chart;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public List<DataSet> getDataset() {
		return dataset;
	}
	public void setDataset(List<DataSet> dataset) {
		this.dataset = dataset;
	}
}
