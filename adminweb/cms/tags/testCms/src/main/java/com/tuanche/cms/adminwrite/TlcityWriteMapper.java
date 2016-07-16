package com.tuanche.cms.adminwrite;

import com.tuanche.cms.bean.Tlcity;


public interface TlcityWriteMapper {
    int insert(Tlcity record);

    int updateByPrimaryKeySelective(Tlcity record);

}