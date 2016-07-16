package com.tuanche.smc.persistence.read.admin.zixun;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.smc.domain.base.NewsClassify;

public interface NewsClassfityMapper {
   List<NewsClassify> getNewsClassfityByPage();
   List<NewsClassify> getNewsClassfitySelectiveByPage(@Param(value="cons")List<String> cons);
   List<NewsClassify> getNewsClassfity();
   List<NewsClassify> getNewsClassfityByIdByPage(Integer id);
   Integer getNewsClassfityChild(Integer id);
   Integer getNewsClassfityOnlineChild(Integer id);
   String getClassUrl(NewsClassify classify);
}
