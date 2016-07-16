package com.tuanche.mapper.sem.read;

import java.util.List;

import com.tuanche.bean.sem.KeywordClassifyCost;


public interface KeywordClassifyCostMapper {
	public List<KeywordClassifyCost> selectKeyWordCostByPage(KeywordClassifyCost classifyCost);
	public List<KeywordClassifyCost> selectAll(KeywordClassifyCost classifyCost);
}