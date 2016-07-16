package com.tuanche.cms.adminread;

import java.util.List;

import com.tuanche.cms.bean.ZiXunClass;


public interface ZiXunClassReadMapper {
    List<ZiXunClass> selectAll();
    
    ZiXunClass getZixunClassById(Integer classId); 

}