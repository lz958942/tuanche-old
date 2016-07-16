package com.tuanche.mapper.admin.write;

import com.tuanche.bean.admin.GmsContent;



public interface GmsContentWriteMapper {

    int insert(GmsContent record);

    int updateByPrimaryKeySelective(GmsContent record);

}