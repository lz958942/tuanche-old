package com.tuanche.mapper.sem.write;

import com.tuanche.bean.sem.Account;

public interface AccountWriteMapper {
	public int insert(Account account);
	public int update(Account acount);
}
