package com.tuanche.mapper.admin.read;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.admin.GmsAddress;



public interface GmsAddressReadMapper {
    GmsAddress selectByPrimaryKey(Integer id);
    GmsAddress selectByGcmId(@Param("gcmId")Integer gcmId);
}