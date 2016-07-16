package com.tuanche.cms.cheread;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.cms.bean.Brand;
import com.tuanche.cms.bean.Style;


public interface CarStyleMapper {
     List<Brand> getBrand();
     List<Style>  getCarStyle();
     List<Brand> getBrandsById(@Param(value="ids")String ids);
}
