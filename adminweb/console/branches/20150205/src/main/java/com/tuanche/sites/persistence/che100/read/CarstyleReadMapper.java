package com.tuanche.sites.persistence.che100.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.CarstyleDomain;

public interface CarstyleReadMapper {

	List<CarstyleDomain> carStyleHomepidByPage(@Param("pid")int pid);
	List<CarstyleDomain> carStyleHome();
	CarstyleDomain carStyleUpdateBefore(@Param("id")int id);
	List<CarstyleDomain> queryCarStyleByPage();
	List<CarstyleDomain> carStylesearchByPage(@Param("conditions")List<String> conditions);
	List<CarstyleDomain> selectStyleByIdByPage(@Param("ppid")int ppid);
	List<CarstyleDomain> selectStyleName();
	List<CarstyleDomain> selectStyleByPage();
	List<CarstyleDomain> getCarstyleByName(@Param("name")String name);
	
	List<CarstyleDomain> selectCarSeries(@Param("id")Integer id);
	void selectCarSeriesStyle(@Param("id")Integer id);
	//删除前确认
	CarstyleDomain selectCarStyleByid(@Param("id")int id);
	List<CarstyleDomain> styleFindname(@Param("ppid")int ppid, @Param("name")String name);
	List<CarstyleDomain> selectStyleByIdName(@Param("pid")int pid);
	CarstyleDomain getCarName(@Param("ppid")Integer ppid);


}
