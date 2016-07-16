package com.tuanche.cms.adminwrite;

import com.tuanche.cms.bean.Plate;


public interface PlateWriteMapper {
    int insert(Plate record);

    int updateByPrimaryKeySelective(Plate record);
}