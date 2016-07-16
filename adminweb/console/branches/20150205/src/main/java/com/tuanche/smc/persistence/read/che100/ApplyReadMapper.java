package com.tuanche.smc.persistence.read.che100;

import java.util.List;
import java.util.Map;

import com.tuanche.bean.che.Apply;
import com.tuanche.bean.sem.ApplyNum;

public interface ApplyReadMapper {
	public List<Apply> selectApplyList(Map<String,Object> map);
	public List<ApplyNum> selectTimeApplyNum(Map<String,Object> map);
	public List<ApplyNum> selectDayApply(Map<String,Object> map);
	public List<ApplyNum> selectApplyDisSum(Map<String,Object> map);
	public List<ApplyNum> selectTimeApplyDisSum(Map<String,Object> map);
	public List<ApplyNum> selectDayBrandApply(Map<String,Object> map);
	public List<ApplyNum> selectTimeApply(Map<String,Object> map);
	public List<ApplyNum> selectAccountApply(Map<String,Object> map);
	
	public List<ApplyNum> selectCityDayPosition(Map<String,Object> map);
	public List<ApplyNum> selectPosition(Map<String,Object> map);
	public List<ApplyNum> selectAllCity(Map<String,Object> map);
	public List<ApplyNum> selectwhere(Map<String,Object> map);
}
