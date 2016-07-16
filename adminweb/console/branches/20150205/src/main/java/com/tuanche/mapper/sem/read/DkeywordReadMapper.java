package com.tuanche.mapper.sem.read;

import java.util.List;

import com.tuanche.bean.sem.DownKeyword;

public interface DkeywordReadMapper {
	public List<DownKeyword> getDownKeywordList(int accountId);
}
