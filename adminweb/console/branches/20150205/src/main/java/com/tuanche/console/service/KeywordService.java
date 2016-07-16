package com.tuanche.console.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tuanche.bean.che.CarStyle;
import com.tuanche.console.adminread.DimensionReadMapper;
import com.tuanche.console.adminread.KeywordReadMapper;
import com.tuanche.console.adminwrite.DimensionWriteMapper;
import com.tuanche.console.bean.Dimension;
import com.tuanche.mapper.che.read.CarStylesReadMapper;

@Service
public class KeywordService {
	
	@Autowired
	private DimensionReadMapper dimensionReadMapper;
	
	@Autowired
	private DimensionWriteMapper dimensionWriteMapper;
	
	@Autowired
	private CarStylesReadMapper carStylesReadMapper;
	
	@Autowired
	private KeywordReadMapper keywordReadMapper;
	
	public void setKeywordReadMapper(KeywordReadMapper keywordReadMapper) {
		this.keywordReadMapper = keywordReadMapper;
	}

	/**
	 * @param conditions
	 * @return
	 * @author liuhg
	 * @Description 分页查询
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Dimension> queryByPage(List<String> conditions){
		return dimensionReadMapper.queryByPage(conditions);
	}

	/**
	 * @param dimension
	 * @author liuhg
	 * @Description 添加维度
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void insert(Dimension dimension) {
		if(dimension.getDimNameArray().length>0){
			Object obj[]=new Object[dimension.getDimNameArray().length];
			for(int x=0;x<dimension.getDimNameArray().length;x++){
				Dimension dimension2=new Dimension();
				dimension2.setDieName("'"+dimension.getDimNameArray()[x]+"'");
				dimension2.setKeywords("'"+dimension.getKeywordsArray()[x]+"'");
				dimension2.setOperateUserId(dimension.getOperateUserId());
				dimension2.setOperateUserName("'"+dimension.getOperateUserName()+"'");
				obj[x]=dimension2;
			}
			dimensionWriteMapper.insertDimension(obj);
		}
		
	}

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 更新前查询
	 */
	public Dimension selectDimensionById(Integer id) {
		// TODO Auto-generated method stub
		return dimensionReadMapper.selectDimensionById(id);
	}

	/**
	 * @param dimension
	 * @author liuhg
	 * @Description 更新
	 */
	public void update(Dimension dimension) {
		dimensionWriteMapper.update(dimension);
	}

	/**
	 * @return
	 * @author liuhg
	 * @Description 查询操作人员
	 */
	public List<Dimension> selectOperate() {
		return dimensionReadMapper.selectOperate();
	}

	/**
	 * @param id
	 * @author liuhg
	 * @Description 单个删除
	 */
	public void deleteDie(Integer id) {
		dimensionWriteMapper.deleteDie(id);
	}

	/**
	 * @param id
	 * @author liuhg
	 * @Description 批量删除
	 */
	public void deleteAllDim(int[] id) {
		dimensionWriteMapper.deleteAllDim(id);
	}

	/**
	 * @return
	 * @author liuhg
	 * @Description 查询维度名称
	 */
	public List<Dimension> selectDieName() {
		// TODO Auto-generated method stub
		return dimensionReadMapper.selectDieName();
	}

	/**
	 * @param pid
	 * @return
	 * @author liuhg
	 * @Description 根据品牌id查询车型
	 */
	public List<CarStyle> getCarStyleById(HashMap<String, Object> map) {
		
		return carStylesReadMapper.getCarStyleById(map);
	}
	/**
	 * 根据维度查询相关关键词
	 * @param kdIds
	 * @return
	 */
	public List<Integer> findIdsByKdId(String[] kdIds){
		List<Integer> list= new ArrayList<Integer>();
		for(String id:kdIds){
			list.add(Integer.parseInt(id.trim()));
		}
		return keywordReadMapper.findIdsByKdId(list);
		
	}
	/**
	 * 根据id查询相关关键字
	 * @param id
	 * @return
	 */
	public String findKeyword(Integer id){
		return keywordReadMapper.findKeyword(id);
	}
}
