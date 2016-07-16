package com.tuanche.dao.che;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.che.BrandGroupbuy;
import com.tuanche.bean.che.CarstyleDomain;
import com.tuanche.bean.che.CarstyleGroupbuy;
import com.tuanche.bean.che.Recommendation;
import com.tuanche.console.util.RequestUtils;
import com.tuanche.mapper.che.read.BrandGroupbuyReadMapper;
import com.tuanche.mapper.che.read.CarstyleGroupbuyReadMapper;
import com.tuanche.mapper.che.read.CarstylekReadMapper;
import com.tuanche.mapper.che.read.RecommendationReadMapper;
import com.tuanche.mapper.che.write.RecommendationWriteMapper;
@Repository
public class RecommendationDao {
	@Resource
private RecommendationReadMapper readMapper;
	@Resource
private RecommendationWriteMapper writeMapper;
	
	@Autowired
private BrandGroupbuyReadMapper brandGroupbuyReadMapper;
	@Autowired
private CarstyleGroupbuyReadMapper carstyleGroupbuyReadMapper;
	
	@Resource
private CarstylekReadMapper carstyleReadMapper;
	
public List<Recommendation> getR12NHome(Recommendation r12n) {
		return readMapper.getR12NHomeByPage(r12n);
	}

public Recommendation getR12NByid(Integer id) {
	return readMapper.getR12NByid(id);
}

public List<BrandGroupbuy> getGroupBrandAllBycity(Integer city) {
	Map<String,Object> map=new HashMap<String, Object>();
	map.put("cityId",city);
	map.put("start", -1);
	return brandGroupbuyReadMapper.select(map);
}

public List<CarstyleGroupbuy> getGroupCarStyleAllByCityAndBrand(Integer city,Integer brand) {
	Map<String,Object> map=new HashMap<String, Object>();
	map.put("cityId",city);
	map.put("brandId",brand);
	map.put("start", -1);
	return carstyleGroupbuyReadMapper.select(map);
}

public void saveR12n(ArrayList<Recommendation> r12n) {
	writeMapper.saveR12n(r12n);
}

public void updateR12n(Recommendation r12n) {
	writeMapper.updateR12n(r12n);	
}

public void updateStatusBatch(String ids, Integer type, Integer uid) {
	writeMapper.updateStatusBatch(ids.split(","),type,uid);
}

public void updateStatus(Integer id, Integer type, Integer uid) {
	writeMapper.updateStatus(id,type,uid);
}

public List<CarstyleDomain> getCarStyle(Integer brandId) {
	// TODO Auto-generated method stub
	return carstyleReadMapper.selectStyleByIdName(brandId);
}

/*public BrandGroupbuy getGroupBrandAll() {
	return brandGroupbuyReadMapper;
}

public CarstyleGroupbuy getGroupCarStyleAll() {
	return null;
}*/
}
