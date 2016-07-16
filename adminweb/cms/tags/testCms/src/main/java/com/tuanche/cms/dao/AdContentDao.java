package com.tuanche.cms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.cms.bean.AdContent;
import com.tuanche.cms.adminread.AdContentReadMapper;
import com.tuanche.cms.adminwrite.AdContentWriteMapper;
import com.tuanche.cms.bean.AdContent;
import com.tuanche.cms.bean.AdPosition;

@Repository
public class AdContentDao {
	@Autowired
	AdContentReadMapper adContentReadMapper;
	@Autowired
	AdContentWriteMapper adContentWriteMapper;
	public List<AdContent> queryAdContent(AdContent adContent) {
		return adContentReadMapper.queryAdContent(adContent);
	}
	public List<String> getGroupName(){
		return adContentReadMapper.getGroupName();
	}
	public AdContent queryAdContentById(Integer id){
		return adContentReadMapper.queryAdContentById(id);
	}
	public int addAdContent(AdContent adContent){
		return adContentWriteMapper.addAdContent(adContent);
	}
	public void updateAdContent(AdContent adContent){
		adContentWriteMapper.updateAdContent(adContent);
	}
	public void openContent(Integer id,Integer status){
		adContentWriteMapper.openContent(new AdContent(id,status));
	}
	public void assignCiy(AdPosition adPosition){
		adContentWriteMapper.assignCiy(adPosition);
	}
	public int count(AdContent adContent){
		return adContentReadMapper.count(adContent);
	}
	public List<AdContent> queryAdContentAll(){
		return adContentReadMapper.queryAdContentAll();
	}
	public void deleteContentbyId(int id){
		 adContentWriteMapper.deleteContentbyId(id);
	}
}
