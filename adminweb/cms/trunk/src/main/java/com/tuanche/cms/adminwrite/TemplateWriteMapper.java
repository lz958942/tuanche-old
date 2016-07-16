package com.tuanche.cms.adminwrite;

import com.tuanche.cms.bean.Template;



public interface TemplateWriteMapper {
   
	public int insert(Template template);
	
	public int update(Template template);
	
}