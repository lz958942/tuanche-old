package com.tuanche.mapper.che.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.CarstyleDomain;

public interface CarstylekReadMapper {

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
	List<CarstyleDomain> selectCarSeriesStyle(@Param("id")Integer id);
	//删除前确认
	CarstyleDomain selectCarStyleByid(@Param("id")int id);
	List<CarstyleDomain> styleFindname(@Param("ppid")int ppid, @Param("name")String name);
	List<CarstyleDomain> selectStyleByIdName(@Param("pid")int pid);
	List<CarstyleDomain> selectOkStyleByIdName(@Param("pid")int pid);
	CarstyleDomain getCarName(@Param("ppid")Integer ppid);
	CarstyleDomain getBrandBystyleId(@Param("id")Integer id);
	CarstyleDomain getCarNameById(@Param("id")Integer id);
	List<CarstyleDomain> getCarNamesByPpid(@Param("ppid")Integer ppid);
	List<CarstyleDomain> getCarStyles();
	List<CarstyleDomain> getCarShapes();
	


}
