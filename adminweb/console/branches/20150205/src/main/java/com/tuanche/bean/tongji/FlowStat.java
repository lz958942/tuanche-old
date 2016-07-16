package com.tuanche.bean.tongji;

import java.io.Serializable;

import com.tuanche.smc.common.page.impl.Pagination;

public class FlowStat implements Serializable {
	
	private int id;
	
	/**
	 * 日期
	 */
	private String date;
	
	/**
	 * 域名
	 */
	private String domain;
	
	/**
	 * 数据类型
	 * SEO:页面添加（1）
	 * SEM:Excel导入（2）
	 */
	private String dataType;
	
	/**
	 * pv
	 */
	private int pv;
	
	/**
	 * uv
	 */
	private int uv;
	
	private double pvuv;
	/**
	 * seopv
	 */
	private int tpv;
	/**
	 * seouv
	 */
	private int tuv;
	/**
	 * sempv
	 */
	private int mpv;
	/**
	 * semuv
	 */
	private int muv;
	private Pagination page;
	public int getMpv() {
		return mpv;
	}

	public void setMpv(int mpv) {
		this.mpv = mpv;
	}

	public int getMuv() {
		return muv;
	}

	public void setMuv(int muv) {
		this.muv = muv;
	}

	/**
	 * 显示数据
	 * pv，uv
	 */
	private String type;
	private Integer star;
	private Integer en;
	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Integer getEn() {
		return en;
	}

	public void setEn(Integer en) {
		this.en = en;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * 数据来源
	 * 1.百度统计
	 * 2.网页日志
	 */
	private String from;
	
	public int getTpv() {
		return tpv;
	}

	public void setTpv(int tpv) {
		this.tpv = tpv;
	}

	public int getTuv() {
		return tuv;
	}

	public void setTuv(int tuv) {
		this.tuv = tuv;
	}

	/**
	 * 查询条件: 开始日期
	 */
	private String startDate;
	
	/**
	 * 查询条件: 结束日期
	 */
	private String endDate;
	/**
	 * 数据来源
	 * 1.全站
	 * 2.各域名
	 */
	private Integer source; 
	/**
	 * 点击量
	 */
	private Integer click;
	
    public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getUv() {
		return uv;
	}

	public void setUv(int uv) {
		this.uv = uv;
	}

	public double getPvuv() {
		return pvuv;
	}

	public void setPvuv(double pvuv) {
		this.pvuv = pvuv;
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
