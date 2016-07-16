package com.tuanche.dao.che;


import java.util.List;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.che.AdContent;
import com.tuanche.bean.che.AdPosition;
import com.tuanche.mapper.che.read.AdContentReadMapper;
import com.tuanche.mapper.che.write.AdContentWriteMapper;

@Repository
public class AdContentDao {
	@Autowired
	AdContentReadMapper adContentReadMapper;
	@Autowired
	AdContentWriteMapper adContentWriteMapper;
	public List<AdContent> queryAdContent(AdContent adContent) {
		return adContentReadMapper.queryAdContentByPage(adContent);
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
	public boolean findByLocationCode(String name) {
		return adContentReadMapper.findByLocationCode(name)==null?true:false;
	}
}
