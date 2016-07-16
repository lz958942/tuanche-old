package com.tuanche.mapper.che.read;

import java.util.List;

import com.tuanche.bean.che.UsedCarstyle;



public interface UsedCarstyleReadMapper {
    UsedCarstyle selectByPrimaryKey(Integer id);
    
    List<UsedCarstyle> getByPage(UsedCarstyle carstyle);
    
    List<UsedCarstyle> getByWhere(UsedCarstyle carstyle);
    
    
}