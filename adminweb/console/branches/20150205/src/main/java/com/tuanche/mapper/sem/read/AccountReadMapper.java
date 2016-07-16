package com.tuanche.mapper.sem.read;

import java.util.List;
import java.util.Map;

import com.tuanche.bean.sem.Account;

public interface AccountReadMapper {
	public List<Account> select(Map<String,Object> map);
	public int getAccountNum(int companyId);
	public List<Account> getGroupAccount(String code);
	public Account find(int id);
	public List<Account> getAllAccount();
}
