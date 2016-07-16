package com.tuanche.cms.bean;

public class AdGet {
	  private int channel;
	  private int cityId;
	  private String locationCode;
	  private int contentPositionId;
	  private int adType;
	  private int height; 
	  private int width;
	  private String picName;//照片路径和名称
	  private String describe;
	  private String adLink;
	  private String adTitle;
	  private int templateId;
	  private int brandId;
	  private int styleId;
	  private int isDefault;
	  private String extendCode;
	  
	  public String getExtendCode() {
		return extendCode;
	}
	public void setExtendCode(String extendCode) {
		this.extendCode = extendCode;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	//广告模板内容
	  private String content;
	  
	  public int getChannel() {
			return channel;
		}
		public void setChannel(int channel) {
			this.channel = channel;
		}
		public int getCityId() {
			return cityId;
		}
		public void setCityId(int cityId) {
			this.cityId = cityId;
		}
		public String getLocationCode() {
			return locationCode;
		}
		public void setLocationCode(String locationCode) {
			this.locationCode = locationCode;
		}
		public int getContentPositionId() {
			return contentPositionId;
		}
		public void setContentPositionId(int contentPositionId) {
			this.contentPositionId = contentPositionId;
		}
		public int getAdType() {
			return adType;
		}
		public void setAdType(int adType) {
			this.adType = adType;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}
		public String getPicName() {
			return picName;
		}
		public void setPicName(String picName) {
			this.picName = picName;
		}
		public String getDescribe() {
			return describe;
		}
		public void setDescribe(String describe) {
			this.describe = describe;
		}
		public String getAdLink() {
			return adLink;
		}
		public void setAdLink(String adLink) {
			this.adLink = adLink;
		}
		public String getAdTitle() {
			return adTitle;
		}
		public void setAdTitle(String adTitle) {
			this.adTitle = adTitle;
		}
		public int getTemplateId() {
			return templateId;
		}
		public void setTemplateId(int templateId) {
			this.templateId = templateId;
		}
		public int getBrandId() {
			return brandId;
		}
		public void setBrandId(int brandId) {
			this.brandId = brandId;
		}
		public int getStyleId() {
			return styleId;
		}
		public void setStyleId(int styleId) {
			this.styleId = styleId;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
	  

}
