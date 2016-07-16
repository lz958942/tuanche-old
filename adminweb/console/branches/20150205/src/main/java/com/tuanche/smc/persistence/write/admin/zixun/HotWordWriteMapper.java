package com.tuanche.smc.persistence.write.admin.zixun;

import org.apache.ibatis.annotations.Param;

import com.tuanche.smc.domain.zixun.HotWord;

public interface HotWordWriteMapper {
	
	/**
	 * 添加热词
	 * @param hotWord
	 * @return
	 */
	public int addHotWord(HotWord hotWord);
	
	public int delHotWordByZixunId(@Param("zixunId")int zixunId);
}
