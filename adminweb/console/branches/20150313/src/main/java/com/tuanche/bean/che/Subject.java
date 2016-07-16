package com.tuanche.bean.che;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Subject implements Serializable{

	private Integer id;//汽车用品专题id
	private String name;//汽车用品专题名称
	private Integer kindId;//汽车用品分类id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getKindId() {
		return kindId;
	}
	public void setKindId(Integer kindId) {
		this.kindId = kindId;
	}
	
}
