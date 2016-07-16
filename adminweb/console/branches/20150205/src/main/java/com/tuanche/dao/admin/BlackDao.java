package com.tuanche.dao.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.Black;
import com.tuanche.mapper.admin.read.BlackReadMapper;
import com.tuanche.mapper.admin.write.BlackWriteMapper;
@Repository
public class BlackDao {
	@Resource
	private BlackReadMapper blackReadMapper;
	@Resource
	private BlackWriteMapper blackWriteMapper;
	public List<Black> search(List<String> search) {
		// TODO Auto-generated method stub
		
		return blackReadMapper.searchByPage(search);
	}
	public void update(Black black) {
		// TODO Auto-generated method stub
		blackWriteMapper.update(black);
	}
	public void saveBlack(Black black) {
		// TODO Auto-generated method stub
		blackWriteMapper.saveBlack(black);
	}
	public Black selectById(Integer id) {
		// TODO Auto-generated method stub
		return blackReadMapper.selectById(id);
	}
	public List<Black> verifyWord(String word, Integer type) {
		// TODO Auto-generated method stub
		return blackReadMapper.verifyWord(word,type);
	}
}
