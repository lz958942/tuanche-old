package com.tuanche.bean.admin;

import com.tuanche.smc.common.page.impl.Pagination;


/**
 * 周刊
 * @author wtk
 *
 */
public class Magazine {
	private Integer id; //主键
	private String title;	//周刊导语
	private Integer edition;	//周刊期数
	private Integer buildEmp;	//创建人
	private String buildTime;	//创建时间
	private Integer updateEmp;	//修改人
	private String updateTime;	//修改时间
	private Byte magazineStatus;	//周刊状态
	private Pagination page;		//分页
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getEdition() {
		return edition;
	}
	public void setEdition(Integer edition) {
		this.edition = edition;
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
	public Byte getMagazineStatus() {
		return magazineStatus;
	}
	public void setMagazineStatus(Byte magazineStatus) {
		this.magazineStatus = magazineStatus;
	}
	
}
