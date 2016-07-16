package com.tuanche.mapper.sem.write;

import com.tuanche.bean.sem.Company;

public interface CompanyWriteMapper {
	public int insert(Company company);
	public int update(Company company);
}
