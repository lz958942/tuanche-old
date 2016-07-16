package com.tuanche.mapper.che.read;
import java.util.List;

import com.tuanche.bean.che.Brand;
import com.tuanche.smc.domain.base.Style;

public interface adveInfoReadMapper {
	public List<Brand> getBrand();
	public List<Style> getStyle();
}
