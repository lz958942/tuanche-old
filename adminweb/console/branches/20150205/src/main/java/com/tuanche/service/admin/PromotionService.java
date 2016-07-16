package com.tuanche.service.admin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.admin.Promotion;
import com.tuanche.bean.sem.DayStatistics;
import com.tuanche.mapper.admin.read.PromotionReadMapper;
import com.tuanche.mapper.admin.write.PromotionWriteMapper;

@Service
public class PromotionService {
	
	@Autowired
	private PromotionReadMapper promotionReadMapper;
	@Autowired
	private PromotionWriteMapper promotionWriteMapper;
	/**
	 * @param condition
	 * @return
	 * @author liuhg
	 * @Description 分页查询
	 */
	public List<Promotion> queryByPage(List<String> condition) {
		
		return promotionReadMapper.queryByPage(condition);
	}
	/**
	 * @param id
	 * @return
	 * @author liuhg
	 * @Description 根据id查询
	 */
	public Promotion selectOne(Integer id) {
		return promotionReadMapper.selectOne(id);
	}
	public Integer addPromotion(List<Promotion> list){
		return promotionWriteMapper.addPromotion(list);
	}
	public Integer addPromotionDay(List<DayStatistics> list){
		List<Promotion> listPro = new ArrayList<Promotion>();
		for (DayStatistics  dayStatistics :list) {
			Promotion promotion = new Promotion();
			promotion.setSpendTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dayStatistics.getDatetime()));
			promotion.setBrandId(dayStatistics.getBrandId());
			promotion.setCityId(dayStatistics.getDistrictId());
			promotion.setCityCode(dayStatistics.getCityCode());
			promotion.setMoney(dayStatistics.getCostMoney().setScale(2, BigDecimal.ROUND_DOWN).toString());
			listPro.add(promotion);
		}
		if(list != null && list.size() > 0){
			DayStatistics dayStatistics = list.get(0);
			Map<String,Object> map = new HashMap<String, Object>();
			if(dayStatistics != null && dayStatistics.getDatetime()!= null){
				map.put("spendTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dayStatistics.getDatetime()));
				promotionWriteMapper.deleteByTime(map);
			}
		}
		return promotionWriteMapper.addPromotion(listPro);
	}
	/**
	 * @param promotion
	 * @author liuhg
	 * @Description 修改
	 */
	public void toUpdate(Promotion promotion) {
		promotionWriteMapper.toUpdate(promotion);
	}
	/**
	 * @param map
	 * @return
	 * @author liuhg
	 * @Description 查询是否有重复记录
	 */
	public int selectSameCount(Map<String, Object> map) {
		return promotionReadMapper.selectSameCount(map);
	}
	/**
	 * @param id
	 * @author liuhg
	 * @Description 删除
	 */
	public void delete(Integer id) {
		promotionWriteMapper.delete(id);
		
	}
	/**
	 * @author wtk
	 * @param spendTime
	 * @Description 导入前删除相同导入时间的数据
	 */
	public void deleteByTime(Map<String,Object> map){
		promotionWriteMapper.deleteByTime(map);
	}
	/**
	 * @author wtk
	 * @Description 导入后删除城市，品牌，推广费用其中一项为空的数据
	 */
	public void deleteNulls(){
		promotionWriteMapper.deleteNulls();
	}

}
