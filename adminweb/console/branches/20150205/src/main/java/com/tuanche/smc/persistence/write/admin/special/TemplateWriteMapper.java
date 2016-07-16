package com.tuanche.smc.persistence.write.admin.special;

import com.tuanche.smc.domain.specialSubject.Template;

public interface TemplateWriteMapper {

	/**
	 * @param id
	 * @author liuhg
	 * @Description 删除模板
	 */
	void deleteTp(Integer id);

	/**
	 * @param template
	 * @author liuhg
	 * @Description 修改模板
	 */
	void updateTemp(Template template);

	/**
	 * @param template
	 * @author liuhg
	 * @Description 添加模板
	 */
	void addTemplate(Template template);
	
	

}
