package com.tuanche.console.dao;

import com.tuanche.console.bean.Dimension;
import com.tuanche.console.bean.Keyword;
import com.tuanche.console.adminread.KeywordReadMapper;
import com.tuanche.console.adminwrite.KeywordWriteMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KeywordDao {
	
	@Autowired
	private KeywordReadMapper keywordReadmapper;
	@Autowired
	private KeywordWriteMapper keywordWriteMapper;
	public List<Keyword> findByWord(String[] words){
		return keywordReadmapper.findByWord(words);
	}
	public int addKeyWord(List<Keyword> keywordList){
		return keywordWriteMapper.addKeyword(keywordList);
	}
	public int addkw(Keyword kw){
		return keywordWriteMapper.addkw(kw);
	}
	public List<Keyword> findParentByLevel(int plevel){
		return keywordReadmapper.findParentByLevel(plevel);
	}
	public int count(Keyword keyword){
		return keywordReadmapper.count(keyword);
	}
	public List<Keyword> find(Keyword keyword){
		return keywordReadmapper.find(keyword);
	}
	public void delByIds(String Ids){
		keywordWriteMapper.delByIds(Ids);
	}
	public void includedItems(String Ids){
		keywordWriteMapper.includedItems(Ids);
	}
	public void onlineItems(String Ids){
		keywordWriteMapper.onlineItems(Ids);
	}
	public Keyword findByid(int id){
		return keywordReadmapper.findByid(id);
	}
	public void updateSave(Keyword keyword){
		keywordWriteMapper.updateSave(keyword);
	}
	public void saveItems(Keyword keyword){
		keywordWriteMapper.saveItems(keyword);
	}
	public List<String> findAllWord(){
		return keywordReadmapper.findAllWord();
	}

}