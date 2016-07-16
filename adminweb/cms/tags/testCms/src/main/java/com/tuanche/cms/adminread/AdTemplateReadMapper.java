package com.tuanche.cms.adminread;

import java.util.List;

import com.tuanche.cms.bean.AdTemplate;

public interface AdTemplateReadMapper {
	public List<AdTemplate> queryAdTemplate(AdTemplate AdTemplate);
	public List<AdTemplate> queryAdTemplateAll();
	public AdTemplate queryAdTemplateById(Integer id);
	public int count(AdTemplate AdTemplate);
}
