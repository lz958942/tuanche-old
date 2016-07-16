package com.tuanche.mapper.che.read;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.GiftBean;

public interface GiftReadMapper {
	GiftBean selectByPrimaryKey(Integer id);

	List<GiftBean> homeByPage(GiftBean bean);

	GiftBean findByid(@Param("id")Integer id);
}