package com.tuanche.mapper.admin.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.admin.GmsBrand;



public interface GmsBrandReadMapper {
    GmsBrand selectByPrimaryKey(Integer id);
    
    List<GmsBrand>selectGmsBrandsByGmsId(@Param("gmsId")Integer gmsId,@Param("gbType")Integer gbType);
}