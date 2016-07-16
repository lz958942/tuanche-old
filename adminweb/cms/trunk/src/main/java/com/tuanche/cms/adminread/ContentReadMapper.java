package com.tuanche.cms.adminread;

import java.util.List;
import java.util.Map;

import com.tuanche.cms.bean.Content;


public interface ContentReadMapper {
	Content	selectByPrimaryKey(Integer id);
	
	List<Content> getContentByPage(Integer plateId);
	
	Integer getMaxSortByPlateId(Integer plateId);
	
	List<Content> getContentLimitByPlateId(Map<String,Object> map);
}