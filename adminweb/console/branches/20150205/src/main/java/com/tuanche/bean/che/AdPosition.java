package com.tuanche.bean.che;

public class AdPosition {
	private int id;
	private int adContentId;
	private int cityId;
	private int status;
	private int flag;
	
	private  Integer createUid,updateUid;
	private String url,picTitle,picDesc,createTime,updateTime;
	
	
	public Integer getCreateUid() {
		return createUid;
	}
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	public Integer getUpdateUid() {
		return updateUid;
	}
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPicTitle() {
		return picTitle;
	}
	public void setPicTitle(String picTitle) {
		this.picTitle = picTitle;
	}
	public String getPicDesc() {
		return picDesc;
	}
	public void setPicDesc(String picDesc) {
		this.picDesc = picDesc;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
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
	@Override
	public String toString() {
		return "AdPosition [id=" + id + ", adContentId=" + adContentId
				+ ", cityId=" + cityId + ", status=" + status + ", flag="
				+ flag + "]";
	}
	
}
