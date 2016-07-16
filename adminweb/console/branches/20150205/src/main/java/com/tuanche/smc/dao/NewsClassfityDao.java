package com.tuanche.smc.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.smc.common.Common;
import com.tuanche.smc.domain.base.NewsClassify;
import com.tuanche.smc.persistence.read.admin.zixun.NewsClassfityMapper;
import com.tuanche.smc.persistence.write.admin.zixun.ZixunClassMapper;

@Repository
public class NewsClassfityDao {
    
    @Autowired    
    private NewsClassfityMapper newsClassfityMapper;
    
    @Autowired
    private ZixunClassMapper zixunClassMapper;
    
    
    public void updateZixunClassStatus(NewsClassify newsClassify){
        zixunClassMapper.updateZixunClassStatus(newsClassify);
    }
    public void updateZixunClass(NewsClassify classify){
        zixunClassMapper.updateZixunClass(classify);
    }
    public List<NewsClassify> getNewsClassfity(){
        return newsClassfityMapper.getNewsClassfity();
    }
    public List<NewsClassify> getNewsClassfityByPage(){
        return newsClassfityMapper.getNewsClassfityByPage();
    }
    public List<NewsClassify> selectClassfityByPage(List<String> cons){
        return newsClassfityMapper.getNewsClassfitySelectiveByPage(cons);
    }
    
    public Integer getNewsClassfityChild(Integer id){
        return newsClassfityMapper.getNewsClassfityChild(id);
    }
    public Integer getNewsClassfityOnlineChild(Integer id){
        return newsClassfityMapper.getNewsClassfityOnlineChild(id);
    }
    
    public void save(NewsClassify newsClassify){
        zixunClassMapper.save(newsClassify);
    }
   public  List<NewsClassify> getNewsClassfityByIdByPage(Integer id){
        return newsClassfityMapper.getNewsClassfityByIdByPage(id);
    }
    
    public String getClassUrl(NewsClassify classify){
        return newsClassfityMapper.getClassUrl(classify);
    }
    public void init(){
        List<NewsClassify> newsClassfity = newsClassfityMapper.getNewsClassfity();
        HashMap<String, NewsClassify> newsClassifys = new HashMap<String, NewsClassify>();
        for(NewsClassify newsClassify :newsClassfity){
            newsClassifys.put(newsClassify.getId().toString(), newsClassify);
            newsClassify.setUri(newsClassify.getUrl());
            newsClassify.setUrl(null);
        }
        
        NewsClassify newsClassify = new NewsClassify();
        newsClassify.setId(0);
        newsClassify.setpId(1000000000);
        newsClassify.setLevel(0);
        newsClassify.setName("资讯分类");
        newsClassify.setOpen(true);
        newsClassfity.add(newsClassify);
        newsClassifys.put(newsClassify.getId().toString(), newsClassify);
        Common.newsClassifyMap = newsClassifys;
        Common.newsClassifies = newsClassfity;
    }
    
}
