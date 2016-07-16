package com.tuanche.dao.che;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.che.PersonCase;
import com.tuanche.mapper.che.read.PersonCaseReadMapper;
import com.tuanche.mapper.che.write.PersonCaseWriteMapper;
@Repository
public class PersonCaseDao {
	@Resource
	private PersonCaseReadMapper  readMapper;
	@Resource
	private PersonCaseWriteMapper writeMapper;
	public List<PersonCase> PEListByCondition(List<String> condition) {
		return readMapper.PEListByConditionByPage(condition);
	}
	public void sava(PersonCase bean) {
		// TODO Auto-generated method stub
		writeMapper.savePE(bean);
	}
	public PersonCase operation(Integer id) {
		return readMapper.updateTo(id);
	}
	public void operation(Integer id, Integer type) {
		writeMapper.updateStatus(id,type);
	}
	public void operation(PersonCase bean) {
		writeMapper.update(bean);
	}
	public void operation(List<String> list, Integer type) {
		writeMapper.beanchStatus(list,type);
	}
} 
