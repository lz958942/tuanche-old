package com.tuanche.console.adminwrite;

import org.apache.ibatis.annotations.Param;

import com.tuanche.console.bean.Keyword;

import java.util.List;

public interface KeywordWriteMapper { 
	public int addKeyword(@Param(value="keywordList")List<Keyword> keywordList);
	public int addkw(Keyword kw);
	public void delByIds(@Param(value="Ids") String Ids);
	public void includedItems(@Param(value="Ids") String Ids);
	public void onlineItems(@Param(value="Ids") String Ids);
	public void updateSave(Keyword keyword);
	public void saveItems(Keyword keyword);
	
}
