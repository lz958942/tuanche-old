package com.tuanche.mapper.admin.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.admin.GmsContent;



public interface GmsContentReadMapper {
    GmsContent selectByPrimaryKey(Integer id);
    
    List<GmsContent> selectByGcmId(@Param("gcmId")Integer gcmId,@Param("type")Integer type);
}