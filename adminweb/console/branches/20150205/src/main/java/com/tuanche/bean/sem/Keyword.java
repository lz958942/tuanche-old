package com.tuanche.bean.sem;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tuanche.console.util.GlobalConstants;

import freemarker.template.SimpleDate;

public class Keyword {
	private int applyNum,accountId,brandId,click,districtId;
	private String title,cityCode,accountName;
	private BigDecimal costMoney;
	private Date datetime;
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public int getDistrictId() {
		return districtId;
	}
	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}
	public int getApplyNum() {
		return applyNum;
	}
	public void setApplyNum(int applyNum) {
		this.applyNum = applyNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public BigDecimal getCostMoney() {
		return costMoney;
	}
	public void setCostMoney(BigDecimal costMoney) {
		this.costMoney = costMoney;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public Date getDatetime() {
		return datetime;
	}
	public String getCityName(){
		if(GlobalConstants.cityMap.containsKey(districtId))
			return GlobalConstants.cityMap.get(districtId);
		return "";
	}
	public String getBrandName(){
		if(GlobalConstants.brandMap.containsKey(brandId))
			return GlobalConstants.brandMap.get(brandId).getName();
		return "";
	}
	public String getTime() {
		return new SimpleDateFormat("yyyy-MM-dd").format(datetime);
	}
	public BigDecimal getAverage(){
		return applyNum>0?costMoney.divide(new BigDecimal(applyNum),2,BigDecimal.ROUND_HALF_UP):costMoney;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	
	
}
