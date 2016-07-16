package com.tuanche.mapper.admin.write;



import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.Magazine;


/**
 * 
 * @author wtk
 *
 */

@Repository
public interface MagazineWriteMapper {

	void updateMagazine(Magazine magazine);
	void addMagazine(Magazine magazine);
	void updateMagStatus(@Param("magazineStatus") Byte magazineStatus,@Param("updateEmp")Integer updateEmp,@Param("id")Integer id);
	
}
