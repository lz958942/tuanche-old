package com.tuanche.mapper.admin.read;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.Magazine;

/**
 * 
 * @author wtk
 *
 */
@Repository
public interface MagazineReadMapper {

	List<Magazine> magazinesByPage(Magazine magazine);
	
	List<Magazine> selectMagazines();

	Magazine selectMagazineById(@Param("id")Integer id);

	int selectMagCountById(@Param("edition")Integer edit,@Param("id")Integer id);
}
