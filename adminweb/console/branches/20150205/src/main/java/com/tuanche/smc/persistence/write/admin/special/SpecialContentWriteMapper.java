package com.tuanche.smc.persistence.write.admin.special;

import org.apache.ibatis.annotations.Param;

import com.tuanche.smc.domain.specialSubject.SpecialContent;


public interface SpecialContentWriteMapper {

	public void addSpecialTitles(@Param("array")Object[] array);

	public void addpicUrl(@Param("array")Object[] array2);

	public void addOnePicUrl(SpecialContent specialContent4);

	public void updateSpecialTitles(SpecialContent specialContent2);

	public void updatePicUrl(SpecialContent specialContent3);

	public void updateOnePicUrl(SpecialContent specialContent4);

	public void deletePic(Integer id);

	/**
	 * @param id
	 * @author liuhg
	 * @Description 删除专题相关
	 */
	public void updateStatus(Integer id);

	/**
	 * @param id
	 * @author liuhg
	 * @Description 批量删除专题相关
	 */
	public void deletePics(int[] id);

	
}
