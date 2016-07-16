package com.tuanche.smc.domain.zixun;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class SimpleZixun implements Serializable {

	private static final long serialVersionUID = 3389155416719007651L;
	private int id;
	private int cityId;
	private String title;
	private Timestamp createTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		try{
			return sdf.format(createTime);
		} catch(Exception e){}
		return null;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
