package com.tuanche.cms.adminwrite;

import com.tuanche.cms.bean.AdTemplate;
public interface AdTemplateWriteMapper {
	public void addAdTemplate(AdTemplate adTemplate);
	public void updateAdTemplate(AdTemplate adTemplate);
	public void deleteAdTemplateById(Integer id);
}
