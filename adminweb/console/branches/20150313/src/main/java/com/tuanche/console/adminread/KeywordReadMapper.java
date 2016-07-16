package com.tuanche.console.adminread;

import com.tuanche.console.bean.Dimension;
import com.tuanche.console.bean.Keyword;

import java.util.List;

import org.apache.ibatis.annotations.Param;
public interface KeywordReadMapper {  
	public List<Keyword> findByWord(@Param(value="words")String[] words);
	public List<Keyword> findParentByLevel(@Param(value="plevel")int plevel);
	public int count(Keyword keyword);
	public List<Keyword> find(Keyword keyword);
	public List<Keyword> getKeyWord(); 
	public Keyword findByid(int id);
	public List<String> findAllWord();
	public List<Integer> findIdsByKdId(List<Integer> list);
	public String findKeyword(Integer id);
	
}
