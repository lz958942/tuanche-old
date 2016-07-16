package com.tuanche.bean.tongji;

import com.tuanche.smc.common.Common;
import com.tuanche.smc.common.page.impl.Pagination;

public class RateDomain {
	private Integer id;
	private Integer cityId;
	private Integer brandId;
	private Integer styleId;
	private Integer pageType;
	private Integer pv;
	private Integer uv;
	private Float rate;
	private String date;
	private String startTime;
	private String endTime;
	private Integer applyNumber;
	private Pagination page;
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	//CityName，BrandName，StyleName，PageTypeName
	public String getCityName(){
		if(Common.cityDistMap.get(cityId+"")==null){
			return "全国";
		}
		return Common.cityDistMap.get(cityId+"").getLocalname();
	}
	public String getBrandName(){
		if(Common.brandMap.get(brandId+"")==null){
			return "全部品牌";
		}
		return Common.brandMap.get(brandId+"").getName();
	}
	public String getStyleName(){
		if(Common.styleMap.get(styleId+"")==null){
			return "全部车型";
		}
		return Common.styleMap.get(styleId+"").getName();
	}
	public String getPageTypeName(){
		return pageType==200?"品牌底层页":"车型底层页";
	}
	
	public Integer getApplyNumber() {
		return applyNumber;
	}
	public void setApplyNumber(Integer applyNumber) {
		this.applyNumber = applyNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getPageType() {
		return pageType;
	}
	public void setPageType(Integer pageType) {
		this.pageType = pageType;
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
	public Integer getPv() {
		return pv;
	}
	public void setPv(Integer pv) {
		this.pv = pv;
	}
	public Integer getUv() {
		return uv;
	}
	public void setUv(Integer uv) {
		this.uv = uv;
	}

	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "RateDomain [id=" + id + ", cityId=" + cityId + ", brandId="
				+ brandId + ", styleId=" + styleId + ", pageType=" + pageType
				+ ", pv=" + pv + ", uv=" + uv+ ", rate=" + rate + ", startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}
}
