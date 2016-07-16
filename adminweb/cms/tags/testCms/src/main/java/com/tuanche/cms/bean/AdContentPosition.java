package com.tuanche.cms.bean;

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
