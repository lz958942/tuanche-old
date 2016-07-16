package com.tuanche.mapper.admin.write;

import com.tuanche.bean.admin.GmsCity;



public interface GmsCityWriteMapper {

    int addGcm(GmsCity record);

    int updateByPrimaryKeySelective(GmsCity record);

}