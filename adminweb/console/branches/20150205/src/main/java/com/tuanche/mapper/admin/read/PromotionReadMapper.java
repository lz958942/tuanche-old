package com.tuanche.mapper.admin.read;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.Promotion;

@Repository
public interface PromotionReadMapper {

	/**
	 * @param condition
	 * @return
	 * @author liuhg
	 * @Description 分页查询
	 */
	List<Promotion> queryByPage(List<String> condition);
	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询
	 */
	Promotion selectOne(Integer id);
	/**
	 * @param map
	 * @return
	 * @author liuhg
	 * @Description 查询是否有重复记录
	 */
	int selectSameCount(Map<String, Object> map);
	
	

}
