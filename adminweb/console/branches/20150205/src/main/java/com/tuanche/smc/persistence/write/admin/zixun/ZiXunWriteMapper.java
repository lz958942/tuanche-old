package com.tuanche.smc.persistence.write.admin.zixun;

import org.apache.ibatis.annotations.Param
;

import com.tuanche.smc.domain.zixun.ZiXun;

public interface ZiXunWriteMapper {
	
	/**
	 * 添加新资讯
	 * @param ziXun
	 * @return
	 */
	public int addZixun(ZiXun ziXun);
	
	/** 
	* @author yangzs
	* @Title: addZixunContent 
	* @Description: 添加新的资讯内容
	* @param @param ziXun
	* @param @return     
	* @return int    
	* @throws 
	*/
	public int addZixunContent(ZiXun ziXun);
	/**
	 * 删除指定ID的资讯
	 * @param id
	 * @return
	 */
	public int delZixun(@Param("id")int id);
	
	/**
	 * 发布资讯
	 * @param id
	 * @param status
	 * @return
	 */
	public int deployZixun(@Param("id")int id, @Param("status")int status);
	
	/**
	 * 更新资讯
	 * @param ziXun
	 * @return
	 */
	public int updateZixunProperties(ZiXun ziXun);
	
	/**
	 * 更新资讯的推荐房源
	 * @param searchUrl
	 * @param id
	 * @return
	 */
	public int updateZixunHouseInfo(@Param("searchUrl")String searchUrl, @Param("id")int id);
	/**
	 * 更新资讯的推荐房源
	 * @param searchUrl
	 * @param id
	 * @return
	 */
	public void updateZixunListPic(@Param("listPic")String listPic, @Param("id")int id);
	
	/**
	 * 增加点击量
	 * @param id
	 * @return
	 */
	public int incrementClickCount(@Param("id")int id);
	
	/** 
	* @author yangzs
	* @Title: 
	* @Description: 更新资讯内容
	* @param @param ziXun     
	* @return void    
	* @throws 
	*/
	public void updateZixunContent(ZiXun ziXun);
	
	
	/** 
	* @author yangzs
	* @Title: batchUpdateZixunstatus 
	* @Description: 批量更新资讯状态
	* @param @param ids
	* @param @param status     
	* @return void    
	* @throws 
	*/
	void batchUpdateZixunstatus(@Param(value="ids") int [] ids,@Param(value="status")int status);
	
	/**********修改资讯发布编辑********/
	public void updateZixunPublishEditorId(@Param("editorId")int editorId,@Param("id")int id);
	/**********修改资讯修改编辑********/
	public void updateZixunUpEditorId(@Param("editorId")int editorId,@Param("id")int id);
	
	/**********修改资讯修改编辑********/
	public void updateZixunUpEditorIds(@Param(value="ids") int [] ids,@Param("editorId")int editorId);

}
