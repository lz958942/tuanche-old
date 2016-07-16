package com.tuanche.cms.adminread;

import java.util.List;

import com.tuanche.cms.bean.AdContent;

public interface AdContentReadMapper {
	List<AdContent>  queryAdContent(AdContent adContent);
	List<String> getGroupName();
	AdContent queryAdContentById(Integer id);
	int count(AdContent adContent);
	List<AdContent>  queryAdContentAll();
}
