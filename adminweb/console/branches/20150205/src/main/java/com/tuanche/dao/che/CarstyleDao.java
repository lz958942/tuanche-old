package com.tuanche.dao.che;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.che.CarstyleDomain;
import com.tuanche.mapper.che.read.CarstylekReadMapper;
import com.tuanche.mapper.che.write.CarstyleWriteMapper;

@Repository
public class CarstyleDao {
	@Resource
	private CarstylekReadMapper carstyleReadMapper;
	@Resource
	private CarstyleWriteMapper carstyleWriteMapper;
	
	public void carStyleDel(int id,int updateid) {
		carstyleWriteMapper.carStyleDel(id,updateid);
		
	}

	public CarstyleDomain carStyleUpdateBefore(int id) {
		// TODO Auto-generated method stub
		return carstyleReadMapper.carStyleUpdateBefore(id);
	}

	public void carStyleSave(CarstyleDomain carstyleDomain) {
		// TODO Auto-generated method stub
		carstyleWriteMapper.carStyleSave(carstyleDomain);
	}

	public void carStyleUpdate(CarstyleDomain carstyleDomain) {
		carstyleWriteMapper.carStyleUpdate(carstyleDomain);
	}

	public List<CarstyleDomain> carStylesearch(List<String> conditions) {
		// TODO Auto-generated method stub
		return carstyleReadMapper.carStylesearchByPage(conditions);
	}

	public List<CarstyleDomain> selectStyleById(int ppid) {
		// TODO Auto-generated method stub
		return carstyleReadMapper.selectStyleByIdByPage(ppid);
	}

	public List<CarstyleDomain> selectStyleName() {
		// TODO Auto-generated method stub
		return carstyleReadMapper.selectStyleName();
	}

	//下架
	public void slodOut(int id) {
		// TODO Auto-generated method stub
		carstyleWriteMapper.slodOut(id);
	}

	public void putaway(int id) {
		// TODO Auto-generated method stub
		carstyleWriteMapper.putaway(id);
	}

	//查询所有车款
	public List<CarstyleDomain> selectStyleByPage() {
		// TODO Auto-generated method stub
		return carstyleReadMapper.selectStyleByPage();
	}

	public CarstyleDomain selectCarStyle(int id) {
		// TODO Auto-generated method stub
		return carstyleReadMapper.selectCarStyleByid(id);
	}

	//删除关联车款
	public void styleDel(int id,int updateid) {
		// TODO Auto-generated method stub
		carstyleWriteMapper.styleDel(id,updateid);
	}

	public List<CarstyleDomain> styleFindname(String string, String name) {
		int ppid=Integer.valueOf(string);
		return carstyleReadMapper.styleFindname(ppid,name);
	}

	public List<CarstyleDomain> selectStyleByIdName(Integer valueOf) {
		// TODO Auto-generated method stub
		return carstyleReadMapper.selectStyleByIdName(valueOf);
	}

	public List<CarstyleDomain> selectOkStyleByIdName(Integer valueOf) {
		// TODO Auto-generated method stub
		return carstyleReadMapper.selectOkStyleByIdName(valueOf);
	}
	
	public CarstyleDomain getCarName(Integer valueOf) {
		// TODO Auto-generated method stub
		return carstyleReadMapper.getCarName(valueOf);
	}

	public void carSaleStop(int id) {
		// TODO Auto-generated method stub
		carstyleWriteMapper.carSaleStop(id);
	}

	public void carSaleState(int id,int type) {
		// TODO Auto-generated method stub
		carstyleWriteMapper.carSaleState(id,type);
	}

	public CarstyleDomain getBrandBystyleId(Integer id) {
		// TODO Auto-generated method stub
		return carstyleReadMapper.getBrandBystyleId(id);
	}

	/**
	 * 根据 车型id 获取所有车款
	 * Administrator：zhaojl
	 * @param cid
	 * @return
	 */
	public List<CarstyleDomain> getCarstylePyCid(int cid){
		return carstyleReadMapper.selectCarSeriesStyle(cid);
	}

	public List<CarstyleDomain> getCarNamesByPpid(Integer ppid) {
		// TODO Auto-generated method stub
		return carstyleReadMapper.getCarNamesByPpid(ppid);
	}

	public CarstyleDomain getCarById(int id) {
		return carstyleReadMapper.selectCarStyleByid(id);
	}




}
