package com.tuanche.cms.cheread;

import java.util.List;

import com.tuanche.cms.bean.Brand;
import com.tuanche.cms.bean.Style;

public interface InfoReadMapper {
	public List<Brand> getBrand();
	public List<Style> getStyle();
}
