package com.tuanche.mapper.admin.write;

import com.tuanche.bean.admin.GmsAddress;



public interface GmsAddressWriteMapper {
    int insert(GmsAddress record);

    int updateByPrimaryKeySelective(GmsAddress record);

}