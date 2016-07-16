package com.tuanche.bean.che;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SubjectKind implements Serializable {
	
	private Integer id; //分类id
	private String name;//分类名称
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
	
	

}
