package com.tuanche.console.bean;

import java.sql.Date;

public class Keyword {
	private int id;
	private String word;
	private int level;
	private int pid;
	private int baiduIndex;
	private int online;
	private int included;
	private int rank;
	private String describe;
	private int uv;
	private Integer kdId;//维度id
	private String dimName;//维度名称


	private Date  addDate;//添加时间
	private Date updateDate;//修改时间
	private int  addUserId;
	private String brandId;//品牌id
	private String carStyleId;//车型Id


	private int cityId;
	private int type;//1买车系列  2 车展系列
	
	private String startDate;
	private String endDate;
	
	
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

	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getBaiduIndex() {
		return baiduIndex;
	}
	public void setBaiduIndex(int baiduIndex) {
		this.baiduIndex = baiduIndex;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public int getIncluded() {
		return included;
	}
	public void setIncluded(int included) {
		this.included = included;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public int getUv() {
		return uv;
	}
	public void setUv(int uv) {
		this.uv = uv;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	private int start;
	private int limit;
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(int addUserId) {
		this.addUserId = addUserId;
	}
	public Integer getKdId() {
		return kdId;
	}
	public void setKdId(Integer kdId) {
		this.kdId = kdId;
	}
	public String getDimName() {
		return dimName;
	}
	public void setDimName(String dimName) {
		this.dimName = dimName;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		if(brandId==null||"".equals(brandId)){
			this.brandId=null;
		}else{
			this.brandId = brandId.replace(",", "");
		}
	}
	public String getCarStyleId() {
		return carStyleId;
	}
	public void setCarStyleId(String carStyleId) {
		if(carStyleId==null||"".equals(carStyleId)){
    		this.carStyleId=null;
    	}else{
    		if(carStyleId.contains("-")){
        		String[] strs=carStyleId.split("-");
        		  this.carStyleId = strs[1].replace(",", "");
        	}else{
        		this.carStyleId=carStyleId;
        	}
    	}
	}
}
