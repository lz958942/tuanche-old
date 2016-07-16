package com.tuanche.cms.bean;

import java.util.List;

import com.tuanche.commons.util.StringUtils;


public class User {
	public int id;
	public String name;
	public int getGroup;//带团期数
	public int sellNum;//购车总数
	public String pic;
	public String manifesto;
	public String uinfo;
	public int activityNum;//累计人数

	public List<Brand>  brands;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		if(StringUtils.isNotEmpty(this.name)&&this.name.length()>0){
			return this.name.substring(0,1)+"团";
		}
		return "";
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public int getGetGroup() {
		return getGroup+10;
	}
	public void setGetGroup(int getGroup) {
		this.getGroup = getGroup;
	}
	public int getSellNum() {
		return sellNum+22;
	}
	public void setSellNum(int sellNum) {
		this.sellNum = sellNum;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getManifesto() {
		return manifesto;
	}
	public void setManifesto(String manifesto) {
		this.manifesto = manifesto;
	}
	public String getUinfo() {
		return uinfo;
	}
	public void setUinfo(String uinfo) {
		this.uinfo = uinfo;
	}
	public int getActivityNum() {
		return activityNum+200;
	}
	public void setActivityNum(int activityNum) {
		this.activityNum = activityNum;
	}
	public List<Brand> getBrands() {
		return brands;
	}
	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}
	
}
