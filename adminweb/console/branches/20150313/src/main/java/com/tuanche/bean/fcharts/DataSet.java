package com.tuanche.bean.fcharts;

import java.io.Serializable;
import java.util.List;

public class DataSet implements Serializable {
	private String seriesname;
	private List<Value> data;
	
	public String getSeriesname() {
		return seriesname;
	}
	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}
	public List<Value> getData() {
		return data;
	}
	public void setData(List<Value> data) {
		this.data = data;
	}
	
}
