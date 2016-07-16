package com.tuanche.cms.cheread;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.cms.bean.GroupBuy;

public interface GroupBuyReadMapper {
   public List<GroupBuy> getOnLineBuyStyle(@Param(value="cityId")int cityId);
   
   public List<GroupBuy> getHotStyle(@Param(value="cityId")int cityId,@Param(value="limit")int limit);

   public List<GroupBuy> getOnLineBuyBrand(@Param(value="cityId")int cityId,@Param(value="limit")int limit);

   public List<GroupBuy> getOnLineBuyByCarLevel(@Param(value="cityId")int cityId,@Param(value="carLevel")int carLevle,@Param(value="limit")int limit);
   /**
    * 获得城市中所有品牌的报名人数
    * @param cityId
    * @return
    */
   public List<GroupBuy> getBrandManNum(@Param(value="cityId")int cityId,@Param(value="limit")int limit);
   /**
    * 搜索团购页获得   团购亮点 及  过去报名人数  及  报名人数
    * @param cityId 城市id
    * @param cids   车型id
    * @return
    */
   public List<GroupBuy> getGroupBuyByStyleIds(@Param(value="cityId")int cityId,@Param(value="styleIds")String styleIds);
   
   /**
    * 获得品牌下的团购车型
    * @param cityId
    * @param ids
    * @return
    */
   public List<GroupBuy> getGroupBuyByIds(@Param(value="cityId")int cityId,@Param(value="ids")String ids);
   
}
