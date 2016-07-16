package com.tuanche.cms.adminread;
import java.util.List;
import com.tuanche.cms.bean.AdContentPosition;
//111
public interface AdContentPositionReadMapper {
	public List<AdContentPosition> queryAdContentPosition(AdContentPosition adContentPosition);
	public int count(AdContentPosition adContentPosition);
	public List<AdContentPosition> queryAdContentPositionAll();
	public AdContentPosition queryAdContentPositionById(Integer id);
	
	public List<AdContentPosition> getPositionByCityId(Integer cityId);
	
}
