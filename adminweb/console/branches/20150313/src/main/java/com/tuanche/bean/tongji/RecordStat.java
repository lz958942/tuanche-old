package com.tuanche.bean.tongji;

import java.io.Serializable;
import java.util.Date;

import com.tuanche.smc.common.page.impl.Pagination;

/**
 * 收录统计
 * @author Administrator
 *
 */
public class RecordStat implements Serializable {
	
	private int id;
	
	/**
	 * 索引量
	 */
	private int indexNumber;
	
	/**
	 * 日期
	 */
	private String date;
	
	/**
	 * 网站site收录
	 */
	private int siteRecord;
	
	/**
	 * 搜索引擎
	 */
	private String queryEngine;
	
	/**
	 * 查询条件: 开始时间
	 */
	private String startDate;
	
	/**
	 * 查询条件: 结束时间
	 */
	private String endDate;
	
    private Pagination page;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIndexNumber() {
		return indexNumber;
	}
	public void setIndexNumber(int indexNumber) {
		this.indexNumber = indexNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getSiteRecord() {
		return siteRecord;
	}
	public void setSiteRecord(int siteRecord) {
		this.siteRecord = siteRecord;
	}
	public String getQueryEngine() {
		return queryEngine;
	}
	public void setQueryEngine(String queryEngine) {
		this.queryEngine = queryEngine;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	
}
