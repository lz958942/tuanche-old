package com.tuanche.smc.persistence.read.admin.special;

import java.util.List;
import java.util.Map;

import com.tuanche.smc.domain.specialSubject.SpecialContent;
import com.tuanche.smc.domain.specialSubject.SpecialSubject;

public interface SpecialContentReadMapper {

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询关联标题
	 */
	List<SpecialContent> selectContentById(Integer id);

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询关联套图
	 */
	List<SpecialContent> selectPicsById(Integer id);

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询关联头图
	 */
	SpecialContent selectOneById(Integer id);

	/**
	 * @param integer
	 * @return
	 * @author liuhg
	 * @Description 老模板查询
	 */
	List<SpecialContent> selectOldTemp(Integer id);



	/**
	 * @param map2
	 * @return
	 * @author liuhg
	 * @Description 车型相关资讯
	 */
	List<SpecialContent> selectXGEndList(Map<String, Object> map2);

	/**
	 * @return
	 * @author liuhg
	 * @Description 没有关联车型的相关资讯
	 */
	List<SpecialContent> selectxgppNoBrand();

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 查询头图和套图
	 */
	List<SpecialContent> selectPics(Integer id);

	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 多个id查询
	 */
	List<SpecialContent> selectAllPics(int[] id);

	/**
	 * @return
	 * @author liuhg
	 * @Description 往期
	 */
	List<SpecialContent> selectWQList();

	/**
	 * @return
	 * @author liuhg
	 * @Description 轮播图
	 */
	List<SpecialContent> selectlunboPic();



}
