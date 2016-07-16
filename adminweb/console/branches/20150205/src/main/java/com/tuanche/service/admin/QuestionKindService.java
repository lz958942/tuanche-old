package com.tuanche.service.admin;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.admin.Question;
import com.tuanche.bean.admin.QuestionKind;
import com.tuanche.mapper.admin.read.QuestionKindReadMapper;
import com.tuanche.mapper.admin.write.QuestionKindWriteMapper;



@Service
public class QuestionKindService {
	@Autowired
	private QuestionKindReadMapper kindReadMapper;
	
	@Autowired
	private QuestionKindWriteMapper kindWriteMapper;
	/**
	 * 查询所有一级分类
	 * @return
	 */
	public List<QuestionKind> selectOneKind(){
		return kindReadMapper.selectOneKind();
	}
	/**
	 * 根据id查询分类
	 * @param id
	 * @return
	 */
	public QuestionKind selectOne(Integer id){
		return kindReadMapper.selectOne(id);
	}
	/**
	 * 修改一级分类
	 * @param kind
	 */
	public void updateKind(QuestionKind kind){
		kindWriteMapper.updateKind(kind);
	}
	/**
	 * 添加分类
	 * @param list
	 * @return 
	 */
	public Integer addKind(QuestionKind kind){
		return kindWriteMapper.addKind(kind);
	}
	/**
	 * 批量添加
	 * @param list
	 */
	public void addKinds(List<QuestionKind> list){
		kindWriteMapper.addKins(list);
	}
	/**
	 * 根据父id 查询二级分类
	 * @param id
	 * @return
	 */
	public List<QuestionKind> selectTwoKind(Integer id){
		return kindReadMapper.selectTwoKind(id);
	}
	/**
	 * 修改二级分类
	 * @param list
	 */
	public void updateKinds(List<QuestionKind> list){
		for(QuestionKind kind:list){
			kindWriteMapper.updateKind(kind);
		}
	}
	/**
	 * 删除一级分类
	 * @param map
	 */
	public void deleteOneKind(Integer buildEmp,Integer id,Byte kindStatus){
		kindWriteMapper.deleteOneKind(buildEmp, id, kindStatus);
	}
	/**
	 * 查询所有分类
	 * @return
	 */
	public List<QuestionKind> selectAlls(){
		return kindReadMapper.selectAlls();
	}
	/**
	 * 修改类别问题个数
	 * @param map
	 */
	public void updateQuestCount(Integer questCount,Integer id){
		kindWriteMapper.updateQuestCount(questCount,id);
	}
	
	public void updateQuestCounts(List<Integer> list){
		kindWriteMapper.updateQuestCounts(list);
	}
}
