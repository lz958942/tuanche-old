package com.tuanche.cms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.cms.bean.GroupBuy;
import com.tuanche.cms.cheread.GroupBuyReadMapper;
import com.tuanche.commons.cache.Cache;
import com.tuanche.commons.cache.PerpetualCacheImpl;
import com.tuanche.commons.util.StringUtils;
@Repository
public class GroupBuyDao {
	@Autowired
	private GroupBuyReadMapper groupBuyReadMapper;
	public static Cache onLineBuyByCarLevelCache=new PerpetualCacheImpl(3600);//1天
	public List<GroupBuy> getOnLineBuyStyle(Integer cityId){
		return groupBuyReadMapper.getOnLineBuyStyle(cityId);
	}
	public List<GroupBuy> getHotStyle(Integer cityId,int limit){
		return groupBuyReadMapper.getHotStyle(cityId,limit);
	}
	public List<GroupBuy> getOnLineBuyBrand(Integer cityId,int limit){
		return groupBuyReadMapper.getOnLineBuyBrand(cityId,limit);
	}
   @SuppressWarnings("unchecked")
public List<GroupBuy> getOnLineBuyByCarLevel(int cityId,int carLevle,int limit){
	   List<GroupBuy>  list=(List<GroupBuy>) onLineBuyByCarLevelCache.get(cityId+"-"+carLevle+"-"+limit);
	   if(list!=null){
		   return list;
	   }
	   list=groupBuyReadMapper.getOnLineBuyByCarLevel(cityId, carLevle, limit);
	   if(list!=null){
		   onLineBuyByCarLevelCache.put(cityId+"-"+carLevle+"-"+limit, list);
	   }
	   return list;
   }
   public List<GroupBuy>  getBrandManNum(int cityId){
	   return groupBuyReadMapper.getBrandManNum(cityId,0);
   }
   public List<GroupBuy>  getBrandManNum(int cityId,int limit){
	   return groupBuyReadMapper.getBrandManNum(cityId,limit);
   }
   /**
    * 搜索团购页获得   团购亮点 及  过去报名人数  及  报名人数
    * @param cityId 城市id
    * @param cids   车型id
    * @return
    */
   public List<GroupBuy> getGroupBuyByStyleIds(int cityId,String styleIds){
	   if(StringUtils.isEmpty(styleIds)){
		   return null;
	   }
	   return groupBuyReadMapper.getGroupBuyByStyleIds(cityId, styleIds);
   }
   public GroupBuy getGroupBuyByStyleId(int cityId,Integer styleId){
	    List<GroupBuy> list=groupBuyReadMapper.getGroupBuyByStyleIds(cityId, styleId+"");
	    if(list!=null&&list.size()>0){
	    	return list.get(0);
	    }
	    return null;
   }


   public List<GroupBuy> getGroupBuyByIds(int cityId,String ids){
	   if(StringUtils.isEmpty(ids)){
		   return null;
	   }
	   return groupBuyReadMapper.getGroupBuyByIds(cityId, ids);
   }
}
