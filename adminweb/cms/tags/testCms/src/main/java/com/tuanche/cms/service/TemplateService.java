package com.tuanche.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.cms.adminread.TemplateReadMapper;
import com.tuanche.cms.adminwrite.TemplateWriteMapper;
import com.tuanche.cms.bean.Employee;
import com.tuanche.cms.bean.Template;
import com.tuanche.cms.util.ApplicationUtil;

@Service
public class TemplateService {
	
	@Autowired 
	private TemplateReadMapper readMapper;
	
	@Autowired
	private TemplateWriteMapper writeMapper;
	
	public List<Template> getTeplateList(){
		return readMapper.getTemplateByPage();
	}
	
	/**
	 * 新建模块
	 *Administrator：zhaojl
	 * @param template
	 * @return
	 */
	public int addTemplate(Template template){
		handleAddUserData(template);
		return writeMapper.insert(template);
	}
	
	/**
	 * 根据id获取模板
	 *Administrator：zhaojl
	 * @param id
	 * @return
	 */
	public Template getTemplateById(int id){
		return readMapper.selectByPrimaryKey(id);
	}

	/**
	 * 修改模板
	 *Administrator：zhaojl
	 * @param template
	 * @return 
	 */
	public int updateTemplate(Template template){
		handleaUpdateUserData(template);
		return writeMapper.update(template);
	}
	
	/**
	 * 获取所有模板
	 *Administrator：zhaojl
	 * @return
	 */
	public List<Template> getAllTemplate(){
		return readMapper.getAllTemplate();
	}
	
	/**
	 * 处理新增用户数据
	 *Administrator：zhaojl
	 * @param template
	 */
	private static void  handleAddUserData(Template template){
		Employee employee = ApplicationUtil.getEmployee();
		template.setCreateUserId(employee.getId());
		template.setCreateUserName(employee.getEmpName());
	}
	/**
	 * 处理修改用户数据
	 *Administrator：zhaojl
	 * @param template
	 */
	private static void  handleaUpdateUserData(Template template){
		Employee employee = ApplicationUtil.getEmployee();
		template.setUpdateUserId(employee.getId());
		template.setUpdateUserName(employee.getEmpName());
	}
	
	
	
}
