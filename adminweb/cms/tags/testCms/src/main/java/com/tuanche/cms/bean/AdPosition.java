package com.tuanche.cms.bean;

public class AdPosition {
	private int id;
	private int adContentId;
	private int cityId;
	private int status;
	private int flag;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAdContentId() {
		return adContentId;
	}
	public void setAdContentId(int adContentId) {
		this.adContentId = adContentId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public AdPosition(int adContentId,int cityId){
		this.adContentId=adContentId;
		this.cityId=cityId;
	}
	public AdPosition(){}
}
