package com.tuanche.bean.fcharts;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable {
	private List<Label> category;

	public List<Label> getCategory() {
		return category;
	}

	public void setCategory(List<Label> category) {
		this.category = category;
	}

}
