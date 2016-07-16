package com.tuanche.cms.adminwrite;

import org.apache.ibatis.annotations.Param;

import com.tuanche.cms.bean.AdPositionTime;

public interface AdPositionTimeWriteMapper {
	
	public void adAdPositionTime(AdPositionTime adPositionTime);
	public void updateAdPositionTime(AdPositionTime adPositionTime);
	public void openAdPositionTime(@Param(value="id")int id,@Param(value="status")int status);
	public void deleteById(@Param(value="id")int id);
}
