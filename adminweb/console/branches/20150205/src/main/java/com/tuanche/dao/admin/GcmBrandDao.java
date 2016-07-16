package com.tuanche.dao.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.GmsBrand;
import com.tuanche.mapper.admin.read.GmsBrandReadMapper;
import com.tuanche.mapper.admin.write.GmsBrandWriteMapper;

@Repository
public class GcmBrandDao {
	@Autowired
	private GmsBrandReadMapper brandRead;
	
	@Autowired
	private GmsBrandWriteMapper brandWrite;
	/**
	 * Administrator：zhaojl
	 * @param gmsBrands 
	 * @param gmsId   团车会id
	 * @param gbType  品牌类型
	 */
	public void addGmsBrandList(List<GmsBrand> gmsBrands,Integer gmsId,Integer gbType){
		if(gmsBrands!=null){
			for (GmsBrand brand: gmsBrands) {
				if(brand.getBrandId()==null){
					continue;
				}
				brand.setGcmId(gmsId);
				brand.setType(gbType);
				brandWrite.insert(brand);
			}
		}
	}
	/**
	 * Administrator：zhaojl
	 * @param gmsId  团车会id
	 * @param gbType 品牌类型
	 * @return
	 */
	public List<GmsBrand> getGmsBrandsByGmsId(Integer gmsId,Integer gbType){
		List<GmsBrand> gmsBrands = brandRead.selectGmsBrandsByGmsId(gmsId, gbType);
		if(gmsBrands != null && gmsBrands.size() > 0){
			return gmsBrands;
		}
		return null;
	}
	
	public void updateGmsBrandList(List<GmsBrand> gmsBrands,Integer gmsId,Integer gbType){
		if(gmsBrands!=null){
			for (GmsBrand brand: gmsBrands) {
				if(brand.getBrandId()==null){
					continue;
				}
				if(brand.getId()==null){//新增
					brand.setGcmId(gmsId);
					brand.setType(gbType);
					brandWrite.insert(brand);
				}else{
					brandWrite.updateByPrimaryKeySelective(brand);
				}
			}
		}
	}
	
	public int updateGmsBrand(GmsBrand brand){
		if(brand == null){
			return 0;
		}
		return brandWrite.updateByPrimaryKeySelective(brand);
	}
}
