package com.tuanche.dao.che;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.che.UsedCarstyle;
import com.tuanche.mapper.che.read.UsedCarstyleReadMapper;
import com.tuanche.mapper.che.write.UsedCarstyleWriteMapper;
import com.tuanche.smc.common.Common;

@Repository
public class UsedCarstyleDao {
	@Autowired
	private UsedCarstyleReadMapper readMapper;
	
	@Autowired
	private UsedCarstyleWriteMapper writeMapper;
	
	public List<UsedCarstyle> getList(UsedCarstyle carstyle,String modelstr){
		if(carstyle == null){
			carstyle = new UsedCarstyle();
		}
		if( "type".equals(modelstr)){
			carstyle.setModel(1);
		}else{
			carstyle.setModel(2);
		}
		return readMapper.getByPage(carstyle);
	}
	
	public int add(UsedCarstyle carstyle){
		return writeMapper.insert(carstyle);
	}
	
	public UsedCarstyle getById(Integer id){
		return readMapper.selectByPrimaryKey(id);
	}
	
	public int update(UsedCarstyle carstyle){
		return writeMapper.updateByPrimaryKeySelective(carstyle);
	}
	
	public List<UsedCarstyle> getListWhere(UsedCarstyle carstyle){
		return readMapper.getByWhere(carstyle);
	}
	
	public void init(){
		List<UsedCarstyle> byWhere = readMapper.getByWhere(null);
		HashMap<Integer, UsedCarstyle> map = new HashMap<Integer, UsedCarstyle>();
		for (UsedCarstyle ucarstyle :byWhere) {
			map.put(ucarstyle.getId(), ucarstyle);
		}
		Common.usedCstyleMap = map;
		Common.usedCstyleList = byWhere;
	}
}
