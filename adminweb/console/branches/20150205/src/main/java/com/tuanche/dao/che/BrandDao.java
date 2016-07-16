package com.tuanche.dao.che;

import java.util.List
;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.che.BrandDomain;
import com.tuanche.bean.che.CarstyleDomain;
import com.tuanche.mapper.che.read.BrandkReadMapper;
import com.tuanche.mapper.che.read.CarstylekReadMapper;
import com.tuanche.mapper.che.write.BrandWriteMapper;
import com.tuanche.mapper.che.write.CarstyleWriteMapper;
import com.tuanche.util.SitesUtils;

@Repository
public class BrandDao {
	@Resource
	private BrandkReadMapper brandReadMapper;
	@Resource
	private BrandWriteMapper brandWriteMapper;
	@Resource
	private CarstylekReadMapper carstyleReadMapper;
	@Resource
	private CarstyleWriteMapper carstyleWriteMapper;
	public List<BrandDomain> selectBrandAllByPage(List<String> conditions ) {
		return brandReadMapper.selectAllByPage(conditions);
	}
	public BrandDomain getBrandBuId(Integer id) {
		// TODO Auto-generated method stub
		return brandReadMapper.getBrandBuId(id);
	}
	public void brandSave(BrandDomain domain) {
		brandWriteMapper.brandSave(domain);
		
	}
	public void brandOneUpdate(BrandDomain domain) {
		brandWriteMapper.brandOneUpdate(domain);
	}

	public List<BrandDomain> selectToBrandList(Integer id) {
		// TODO Auto-generated method stub
		return brandReadMapper.electToBrandList(id);
	}
	//查询所有一级品牌
	public List<BrandDomain> selectBrandAll() {
		// TODO Auto-generated method stub
		return brandReadMapper.selectBrandAll();
	}
	//条件查询二级品牌（一级品牌 二级品牌名称）
	public List<BrandDomain> selectTwoBrandSeel(List<String> conditions) {
		// TODO Auto-generated method stub
		return brandReadMapper.selectTwoBrandSeel(conditions);
	}
	//保存二级品牌
	public void saveTowBrand(BrandDomain domain) {
		// TODO Auto-generated method stub
		brandWriteMapper.brandSave(domain);
	}
	public void twoBrandUpdate(BrandDomain domain) {
		// TODO Auto-generated method stub
		System.out.println(domain.getLogo());
		brandWriteMapper.brandOneUpdate(domain);
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////
	public void newBrandUpdate(BrandDomain domainid) {
		brandWriteMapper.brandOneUpdate(domainid);
	}
	public void newDelBrand(Integer id,int updateid) {
		brandWriteMapper.newDelBrand(id,updateid);
		
	}
	////////////////////////車型車款///////////////////////////////////////////////////////
	//查询品牌下的车型
	public List<CarstyleDomain> carStyleHome(int pid) {
		return carstyleReadMapper.carStyleHomepidByPage(pid);
	}
	public List<CarstyleDomain> queryCarStyleByPage() {
		// TODO Auto-generated method stub
		return carstyleReadMapper.queryCarStyleByPage();
	}
	public List<BrandDomain> getBrandByName(String name) {
		// TODO Auto-generated method stub
		System.out.println(name);
		return brandReadMapper.getBrandByName(name);
	}
	public List<CarstyleDomain> getCarstyleByName(String name) {
		// TODO Auto-generated method stub
		return carstyleReadMapper.getCarstyleByName(name);
	}
	
	//删除品牌对应下车型//查询
	public List<CarstyleDomain> selectCarSeries(Integer id) {
		// TODO Auto-generated method stub
		return carstyleReadMapper.selectCarSeries(id);
	}
	//删除品牌对应下车型//下的车款sadsddssa
	public void selectCarSeriesStyle(Integer id) {
		// TODO Auto-generated method stub
		carstyleReadMapper.selectCarSeriesStyle(id);
	}
	
	//删除车款
	public void delStyle(Integer id,int updateid) {
		// TODO Auto-generated method stub
		carstyleWriteMapper.delStyle(id,updateid);
	}
	//删除车系
	public void delCarStyle(Integer id,int updateid ) {
		// TODO Auto-generated method stub
		carstyleWriteMapper.delCarStyle(id,updateid);
	}
	
	public List<BrandDomain> getBrandNameId() {
		// TODO Auto-generated method stub
		return brandReadMapper.getBrandNameId();
	}
	public void updateBrandPic(Integer id, String path) {
		// TODO Auto-generated method stub
		brandWriteMapper.updateBrandPic(id,path);
	}
	public BrandDomain updateBrandPicBeff(Integer id) {
		// TODO Auto-generated method stub
		return brandReadMapper.updateBrandPicBeff(id);
	}
	
}
