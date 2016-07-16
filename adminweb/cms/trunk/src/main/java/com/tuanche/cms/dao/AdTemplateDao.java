package com.tuanche.cms.dao;

import java.util.List;

import com.tuanche.cms.adminwrite.AdTemplateWriteMapper;
import com.tuanche.cms.bean.AdTemplate;
import com.tuanche.cms.adminread.AdTemplateReadMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdTemplateDao {
	@Autowired 
	AdTemplateWriteMapper adTemplateWriteMapper;
	@Autowired
	AdTemplateReadMapper adTemplateReadMapper;
	public void addAdTemplate(AdTemplate adTemplate){
		adTemplateWriteMapper.addAdTemplate(adTemplate);
	}
	public void updateAdTemplate(AdTemplate adTemplate){
		adTemplateWriteMapper.updateAdTemplate(adTemplate);
	}
	public List<AdTemplate> queryAdTemplate(AdTemplate adTemplate){
		return adTemplateReadMapper.queryAdTemplate(adTemplate);
	}
	public AdTemplate queryAdTemplateById(Integer id){
		return adTemplateReadMapper.queryAdTemplateById(id);
	}
	public List<AdTemplate> queryAdTemplateAll(){
		return adTemplateReadMapper.queryAdTemplateAll();
	}
	public void deleteAdTemplateById(Integer id){
		adTemplateWriteMapper.deleteAdTemplateById(id);
	}
	public int count(AdTemplate adTemplate){
		return adTemplateReadMapper.count(adTemplate);
	}
}
