package com.tuanche.cms.adminwrite;
import com.tuanche.cms.bean.AdContentPosition;

public interface AdContentPositionWriteMapper {
	public void openContentLocation(AdContentPosition adContentPosition); 
	public void deleteContentPositionById(int id);
}
