package com.tuanche.cms.bean;


public class City {
	private String dname;
	private String orderName;
	private int id;
	private String staticUrl;
	private String cityCode;
	private String py;
	private String code ;
	private int isOpenZixun;
	private int isTest;
	
	
	
	
	public int getIsTest() {
		return isTest;
	}
	public void setIsTest(int isTest) {
		this.isTest = isTest;
	}
	public int getIsOpenZixun() {
		return isOpenZixun;
	}
	public void setIsOpenZixun(int isOpenZixun) {
		this.isOpenZixun = isOpenZixun;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPy() {
		return py;
	}
	public void setPy(String py) {
		this.py = py;
	}

	public String getStaticUrl() {
		return staticUrl;
	}
	public void setStaticUrl(String staticUrl) {
		this.staticUrl = staticUrl;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
}

