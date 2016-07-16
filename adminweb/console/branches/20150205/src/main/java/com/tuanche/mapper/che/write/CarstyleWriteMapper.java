package com.tuanche.mapper.che.write;

import org.apache.ibatis.annotations.Param;


import com.tuanche.bean.che.CarstyleDomain;

public interface CarstyleWriteMapper {

	void carStyleDel(@Param("id")int id,@Param("updateid")int updateid);
	void carSaleStop(@Param("id")int id);

	void carStyleUpdate(@Param("carstyleDomain")CarstyleDomain carstyleDomain);

	void carStyleSave(CarstyleDomain carstyleDomain);

	void slodOut(@Param("id")int id);

	void putaway(@Param("id")int id);

	void delStyle(@Param("id")Integer id,@Param("updateid")int updateid);

	void delCarStyle(@Param("id")Integer id,@Param("updateid")int updateid);

	void styleDel(@Param("id")int id,@Param("updateid")int updateid);
	void carSaleState(@Param("id")Integer id ,@Param("type")int type);

}
