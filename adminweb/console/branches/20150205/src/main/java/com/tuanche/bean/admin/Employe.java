package com.tuanche.bean.admin;

import com.tuanche.smc.common.page.impl.Pagination;


/**
 * 人员
 * @author wtk
 *
 */
public class Employe {
	private Integer id;	//主键
	private Byte  position;		//1 统筹 2 摄影 3设计/制作
	private String name;		//员工昵称
	private String employePic;		//员工头像图片
	private Integer buildEmp;		//创建人
	private String buildTime;		//创建时间
	private Integer updateEmp;		//修改人
	private String updateTime;		//修改时间
	private Byte employeStatus;	//人员状态
	private Pagination page;	//分页
	private String oldUrl;		//要删除的Url
	public String getOldUrl() {
		return oldUrl;
	}
	public void setOldUrl(String oldUrl) {
		this.oldUrl = oldUrl;
	}
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
	public Byte getPosition() {
		return position;
	}
	public void setPosition(Byte position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmployePic() {
		return employePic;
	}
	public void setEmployePic(String employePic) {
		this.employePic = employePic;
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
	public Byte getEmployeStatus() {
		return employeStatus;
	}
	public void setEmployeStatus(Byte employeStatus) {
		this.employeStatus = employeStatus;
	}

}
