package com.tuanche.bean.admin;

import java.util.Date;

import com.tuanche.smc.common.page.impl.Pagination;
/**
 * 图片
 * @author wtk
 *
 */
public class QuestionPic {
	private Integer id;	//主键
	private Integer sort;	//排序序号		
	private String title;	//图片标题	
	private String picUrl;	//图片保存url
	
	private Integer buildEmp;	//创建人
	private String buildTime;	//创建时间
	private Integer updateEmp;	//修改人
	private String updateTime;	//修改时间
	
	private String hrefUrl;	//图片指向Url
	
	private Byte picStatus;		//状态   0 为删除    1为未审核   2为上线

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Integer getBuildEmp() {
		return buildEmp;
	}

	public void setBuildEmp(Integer buildEmp) {
		this.buildEmp = buildEmp;
	}

	public String getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}

	public Integer getUpdateEmp() {
		return updateEmp;
	}

	public void setUpdateEmp(Integer updateEmp) {
		this.updateEmp = updateEmp;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getHrefUrl() {
		return hrefUrl;
	}

	public void setHrefUrl(String hrefUrl) {
		this.hrefUrl = hrefUrl;
	}

	public Byte getPicStatus() {
		return picStatus;
	}

	public void setPicStatus(Byte picStatus) {
		this.picStatus = picStatus;
	}
	
	
}
