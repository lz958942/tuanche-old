package com.tuanche.dao.che;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.che.SysConfig;
import com.tuanche.mapper.che.read.SysConfigReadMapper;
import com.tuanche.mapper.che.write.SysConfigWriteMapper;

@Repository
public class SysConfigDao {
	@Resource
	private SysConfigReadMapper configReadMapper;
	@Resource
	private SysConfigWriteMapper configWriteMapper;
	public List<SysConfig> selectAllByPage(List<String> condition) {
		return configReadMapper.selectAllByPage(condition);
	}
	public synchronized void saveConfig(SysConfig sysConfig) {
		configWriteMapper.saveConfig(sysConfig);
	}
	public void updateConfig(SysConfig sysConfig) {
		// TODO Auto-generated method stub
		configWriteMapper.updateConfig(sysConfig);
	}
	public SysConfig updateBefore(Integer id) {
		// TODO Auto-generated method stub
		return configReadMapper.updateBefore(id);
	}
	public List<SysConfig> verification(String key, String code) {
		return configReadMapper.verification(key,code);
	}
	public Integer getCodeByKey(String key) {
		return configReadMapper.getCodeByKeyResuitInt(key);
	}
}
