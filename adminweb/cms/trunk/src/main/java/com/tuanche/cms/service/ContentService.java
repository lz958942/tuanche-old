package com.tuanche.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.cms.adminread.ContentReadMapper;
import com.tuanche.cms.adminwrite.ContentWriteMapper;
import com.tuanche.cms.bean.Content;
import com.tuanche.cms.bean.Employee;
import com.tuanche.cms.util.ApplicationUtil;

@Service
public class ContentService {
	
	@Autowired
	private ContentReadMapper readMapper;
	
	@Autowired
	private ContentWriteMapper writeMapper;
	
	public Content getConetntById(Integer contentId){
		return readMapper.selectByPrimaryKey(contentId);
	}
	
	public List<Content> getContentByPlateId(Integer plateId){
		return readMapper.getContentByPage(plateId);
	}
	
	public int addContent(Content content){
		Integer maxSort = readMapper.getMaxSortByPlateId(content.getPlateId());
		if(maxSort == null){
			maxSort = 1;
		}
		content.setSort(maxSort+1);
		handleAdd(content);
		return writeMapper.insert(content);
	}
	
	public int update(Content content){
		handleUpdate(content);
		return writeMapper.updateByPrimaryKeySelective(content);
	}
	
	public int updateSort(Integer upContentId, Integer DownContentId){
		Content content = new Content();
		content.setId(upContentId);
		content.setUpSort(1);
		Content content2 = new  Content();
		content2.setId(DownContentId);
		content2.setDoSort(1);
		writeMapper.updateByPrimaryKeySelective(content2);
		return writeMapper.updateByPrimaryKeySelective(content);
	}

	private static void  handleAdd(Content content){
		Employee employee = ApplicationUtil.getEmployee();
		content.setCreateUserId(employee.getId());
		content.setCreateUserName(employee.getEmpName());
	}
	
	private static void handleUpdate(Content content){
		Employee employee = ApplicationUtil.getEmployee();
		content.setUpdateUserId(employee.getId());
		content.setUpdateUserName(employee.getEmpName());
	}
}
