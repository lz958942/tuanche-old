package com.tuanche.mapper.che.write;

import com.tuanche.bean.che.UsedCarstyle;


public interface UsedCarstyleWriteMapper {

    int insert(UsedCarstyle record);

    int updateByPrimaryKeySelective(UsedCarstyle record);

}