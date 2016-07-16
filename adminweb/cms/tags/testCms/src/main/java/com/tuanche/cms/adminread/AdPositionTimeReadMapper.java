package com.tuanche.cms.adminread;

import com.tuanche.cms.bean.AdGet;
import com.tuanche.cms.bean.AdPositionTime;

import java.util.List;

public interface AdPositionTimeReadMapper {
	public List<AdPositionTime> queryAdPositionTime(AdPositionTime adPositionTime);
	public AdPositionTime queryAdPositionTimeById(int id);
	public int  count(AdPositionTime adPositionTime); 
	public List<AdGet> adGet(AdGet adGet);
	public Integer searchAdPositionTime(AdPositionTime adPositionTime);
}
