package com.tuanche.cms.adminread;

import java.util.List;

import com.tuanche.cms.bean.Tlcity;


public interface TlcityReadMapper {
    Tlcity selectByPrimaryKey(Integer id);
    
    List<Tlcity> getTlcityByPage(Tlcity tlcity);
    
    List<Tlcity> getAllTlcity();
}