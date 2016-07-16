package com.tuanche.sites.persistence.che100.write;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.BrandDomain;

public interface BrandWriteMapper {

	void brandSave(BrandDomain domain );

	void brandOneUpdate(BrandDomain domain);

	void newDelBrand(@Param("id")Integer id,@Param("updateUserId")int updateid);

	void updateBrandPic(@Param("id")Integer id, @Param("path")String path);

	

	//void newBrandUpdate(BrandDomain domainid);

	//void saveTowBrand(@Param("domain")BrandDomain domain);

	//void twoBrandUpdate(@Param("domain")BrandDomain domain);
	
}
