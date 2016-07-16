package com.tuanche.cms.adminread;

import java.util.List;

import com.tuanche.cms.bean.Plate;


public interface PlateReadMapper {
    Plate selectByPrimaryKey(Integer id);
    
    List<Plate> getPlateByPage(Plate plate);
    
    List<Plate> getPlateByTlCityId(Integer  cityId);
    
    List<Plate> getPlateBywhere(Plate plate);
}