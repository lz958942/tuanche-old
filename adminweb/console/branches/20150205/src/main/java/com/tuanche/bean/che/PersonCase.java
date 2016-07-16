package com.tuanche.bean.che;


import com.tuanche.console.util.StringUtils;
import com.tuanche.smc.common.page.impl.Pagination;

public class PersonCase {
	private Integer id;
    private Integer cityId;
    private Integer brandId;
    private Integer styleId;
    private Integer priceEvaluate;
    private String userName;
    private String picture;
    private Integer createUid;
    private String crateTime;
    private Integer updateUid;
    private String updateTime;
    private Integer status;
    private String content;
    
    //非持久属性
    private String brabdName,styleName,cityName,cityCode;
    
    public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getBrabdName() {
    	if(StringUtils.isNotEmptyString(brabdName)){
    		return brabdName;
    	}
		return "";
	}
	public void setBrabdName(String brabdName) {
		this.brabdName = brabdName;
	}
	public String getStyleName() {
		if(StringUtils.isNotEmptyString(styleName)){
    		return styleName;
    	}
		return "";
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	private Pagination page;
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
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public Integer getStyleId() {
		return styleId;
	}
	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}
	public Integer getPriceEvaluate() {
		return priceEvaluate;
	}
	public void setPriceEvaluate(Integer priceEvaluate) {
		this.priceEvaluate = priceEvaluate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getCreateUid() {
		return createUid;
	}
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	public String getCrateTime() {
		if(StringUtils.isNotEmptyString(crateTime)){
			return crateTime.substring(0,crateTime.length()-2);
		}
		return crateTime;
	}
	public void setCrateTime(String crateTime) {
		this.crateTime = crateTime;
	}
	public Integer getUpdateUid() {
		return updateUid;
	}
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	public String getUpdateTime() {
		if(StringUtils.isNotEmptyString(updateTime)){
			return updateTime.substring(0,updateTime.length()-2);
		}
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "PersonCase [id=" + id + ", cityId=" + cityId + ", brandId="
				+ brandId + ", styleId=" + styleId + ", priceEvaluate="
				+ priceEvaluate + ", userName=" + userName + ", picture="
				+ picture + ", createUid=" + createUid + ", crateTime="
				+ crateTime + ", updateUid=" + updateUid + ", updateTime="
				+ updateTime + ", status=" + status + ", content=" + content
				+ "]";
	}

}