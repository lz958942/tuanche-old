package com.tuanche.cms.adminread;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.cms.bean.Template;


public interface TemplateReadMapper {
    public Template selectByPrimaryKey(@Param("id")Integer id);
    
    public  List<Template> getTemplateByPage();
    
    public List<Template> getAllTemplate();
}