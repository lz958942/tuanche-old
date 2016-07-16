package com.tuanche.bean.che;

import java.util.Map;

public class Brand {
	private int id;
	private int vender;
	private String name,orderName;
	private Map<Integer,String> carstyleMap;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVender() {
		return vender;
	}
	public void setVender(int vender) {
		this.vender = vender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Map<Integer, String> getCarstyleMap() {
		return carstyleMap;
	}
	public void setCarstyleMap(Map<Integer, String> carstyleMap) {
		this.carstyleMap = carstyleMap;
	}
	
	
}
