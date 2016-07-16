package com.tuanche.smc.domain.zixun;

import java.io.Serializable;

public class DictObject implements Serializable {
	
	private static final long serialVersionUID = 664831700773222646L;

	private int id;
	
	private String listIndex;
	
	private String listKey;
	
	private int listValue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getListIndex() {
		return listIndex;
	}

	public void setListIndex(String listIndex) {
		this.listIndex = listIndex;
	}

	public String getListKey() {
		return listKey;
	}

	public void setListKey(String listKey) {
		this.listKey = listKey;
	}

	public int getListValue() {
		return listValue;
	}

	public void setListValue(int listValue) {
		this.listValue = listValue;
	}
}
