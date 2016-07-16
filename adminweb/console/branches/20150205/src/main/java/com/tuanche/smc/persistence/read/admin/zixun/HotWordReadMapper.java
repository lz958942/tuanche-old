package com.tuanche.smc.persistence.read.admin.zixun;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.smc.domain.zixun.HotWord;

public interface HotWordReadMapper {
	public List<HotWord> getHotWordsByZixunId(@Param("zixunId")int zixunId);
}
