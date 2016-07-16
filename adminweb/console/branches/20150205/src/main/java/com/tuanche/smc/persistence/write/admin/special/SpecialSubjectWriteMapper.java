package com.tuanche.smc.persistence.write.admin.special;

import org.apache.ibatis.annotations.Param;

import com.tuanche.smc.domain.specialSubject.SpecialSubject;

public interface SpecialSubjectWriteMapper {

	/**
	 * @param specialSubject
	 * @author liuhg
	 * @Description 添加专题
	 */
	public void addSpecialSubject(SpecialSubject specialSubject);

	/**
	 * @param specialSubject
	 * @author liuhg
	 * @Description 专题状态修改
	 */
	public void updateSpecialStatus(SpecialSubject specialSubject);

	/**
	 * @param id
	 * @author liuhg
	 * @Description 批量删除
	 */
	public void deleteSelect(int[] id);

	/**
	 * @param id
	 * @param downline
	 * @author liuhg
	 * @Description 批量上下线
	 */
	public void upOrDownSelect(@Param(value="ids") int [] ids,@Param(value="spOnline")int spOnline);

	/**
	 * @param date
	 * @param type
	 * @param id
	 * @author liuhg
	 * @Description 存放图片路径
	 */
	public void addPicUrl(String date, String type, Integer id);

	/**
	 * @param specialSubject
	 * @author liuhg
	 * @Description 修改专题内容
	 */
	public void updateSpecialSubject(SpecialSubject specialSubject);
	
	

}
