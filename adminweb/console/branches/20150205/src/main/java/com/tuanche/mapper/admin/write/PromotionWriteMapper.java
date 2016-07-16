package com.tuanche.mapper.admin.write;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.Promotion;

@Repository
public interface PromotionWriteMapper {
/**
 * 插入推广费用
 * @param list
 * @return
 */
	Integer addPromotion(List<Promotion> list);

	/**
	 * @param promotion
	 * @author liuhg
	 * @Description 修改
	 */
	void toUpdate(Promotion promotion);

	/**
	 * @param id
	 * @author liuhg
	 * @Description 删除
	 */
	void delete(Integer id);
	/**
	 * 插入前删除与导入时间相同的数据
	 * @param spendTime
	 */
	void deleteByTime(Map<String,Object> map);
	/**
	 * 插入后删除城市，品牌，推广费用为空的数据
	 */
	void deleteNulls();

}
