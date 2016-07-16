package com.tuanche.bean.sem;

import java.util.Date;

public class Company {
	private int id,state,addUserId;
	private String parentCode,companyName,companyDomain,code,remark;
	private Date addDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode==null?"":parentCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName==null?"":companyName;
	}
	public String getCompanyDomain() {
		return companyDomain;
	}
	public void setCompanyDomain(String companyDomain) {
		this.companyDomain = companyDomain==null?"":companyDomain;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code==null?"":code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark==null?"":remark;
	}
	public int getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(int addUserId) {
		this.addUserId = addUserId;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	
}
