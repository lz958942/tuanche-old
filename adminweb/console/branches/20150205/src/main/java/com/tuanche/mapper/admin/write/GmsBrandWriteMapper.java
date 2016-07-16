package com.tuanche.mapper.admin.write;

import com.tuanche.bean.admin.GmsBrand;


public interface GmsBrandWriteMapper {

    int insert(GmsBrand record);

    int updateByPrimaryKeySelective(GmsBrand record);

}