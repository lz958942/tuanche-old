package com.tuanche.smc.domain.zixun;

import java.io.Serializable;

public class HotWord implements Serializable {
	private static final long serialVersionUID = -4421387082627666787L;

	/**
	 * 数据库自增主键
	 */
	private int id;
	
	/**
	 * 所属资讯ID
	 */
	private int ziXunId;
	
	/**
	 * 热词
	 */
	private String keyword;
	
	/**
	 * 对应的URL
	 */
	private String url;
	
	/**
	 * 排序
	 */
	private int orderIndex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getZiXunId() {
		return ziXunId;
	}

	public void setZiXunId(int ziXunId) {
		this.ziXunId = ziXunId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
}
