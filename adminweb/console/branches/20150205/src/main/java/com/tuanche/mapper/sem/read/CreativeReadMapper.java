package com.tuanche.mapper.sem.read;

import java.util.List;

import com.tuanche.bean.sem.Creative;

public interface CreativeReadMapper {
	
	public List<Creative> getCreativeList(int accountId);
}
