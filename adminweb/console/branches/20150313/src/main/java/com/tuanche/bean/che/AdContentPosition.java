package com.tuanche.bean.che;

import java.util.List;

import com.tuanche.smc.common.page.impl.Pagination;

public class AdContentPosition {
	private int id;
	private int adContentId;
	private int channel;//频道
	private int adType;//广告类型
	private String locationName;//名称
	private String describe;//描述
	private String locationCode;//位置代码
	private int width;//宽度
	private int height;//高度
	private int isEdit;//是否可以编辑, 0：可以编辑， 1：不可以编辑
	private String groupName;//分组
	private int contentPositionId;
	private int cityId;
	private String cityName;
	private int contentPositiontatus;
	private int start;
	private int limit;
	private int flag;
	private Integer status;
	private Pagination page;
	private String url;
	private String targetUrl,picDesc,picTitle;
	private String[] types;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String[] getTypes() {
		return types;
	}
	public void setTypes(String[] types) {
		this.types = types;
	}
	public String getPicDesc() {
		return picDesc;
	}
	public void setPicDesc(String picDesc) {
		this.picDesc = picDesc;
	}
	public String getPicTitle() {
		return picTitle;
	}
	public void setPicTitle(String picTitle) {
		this.picTitle = picTitle;
	}
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
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
	public int getAdContentId() {
		return adContentId;
	}
	public void setAdContentId(int adContentId) {
		this.adContentId = adContentId;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public int getAdType() {
		return adType;
	}
	public void setAdType(int adType) {
		this.adType = adType;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(int isEdit) {
		this.isEdit = isEdit;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getContentPositionId() {
		return contentPositionId;
	}
	public void setContentPositionId(int contentPositionId) {
		this.contentPositionId = contentPositionId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getContentPositiontatus() {
		return contentPositiontatus;
	}
	public void setContentPositiontatus(int contentPositiontatus) {
		this.contentPositiontatus = contentPositiontatus;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public AdContentPosition(int contentPositionId,int contentPositiontatus){
		this.contentPositionId=contentPositionId;
		this.contentPositiontatus=contentPositiontatus;
	}
	public AdContentPosition(){}
}
