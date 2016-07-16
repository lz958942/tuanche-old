package com.tuanche.smc.persistence.read.che100;

import java.util.List;

import com.tuanche.smc.domain.base.Brand;
import com.tuanche.smc.domain.base.Style;
import com.tuanche.smc.domain.specialSubject.SpecialContent;

public interface CarStyleMapper {
     List<Brand> getBrand();
     List<Style>  getCarStyle();
     List<Style>  getCarStyleOk();
     List<com.tuanche.bean.che.Brand> getBrands();
	List<SpecialContent> selectxgpp(int brandId);
	String selectCarName(int carStyleId);
}
