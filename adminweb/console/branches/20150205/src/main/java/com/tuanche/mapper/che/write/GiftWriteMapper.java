package com.tuanche.mapper.che.write;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.GiftBean;

public interface GiftWriteMapper {
    GiftBean selectByPrimaryKey(Integer id);
    int insert(GiftBean record);
    int updateByPrimaryKeySelective(GiftBean record);
	void savegift(GiftBean bean);
	void editGift(GiftBean bean);
	void updateStatus(@Param("id")Integer id,@Param("type") Integer type,@Param("uid") int uid);
	void updateStatusFromIds(@Param("type") Integer type,@Param("uid") int uid,@Param("ids") String[] ids);
}