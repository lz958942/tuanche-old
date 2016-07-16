package com.tuanche.mapper.sem.read;

import java.util.List;
import java.util.Map;

import com.tuanche.bean.sem.Company;

public interface CompanyReadMapper {
	public List<Company> select(Map<String,Object> map);
	public Company find(int id);
}
