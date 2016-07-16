package com.tuanche.bean.sem;

import com.tuanche.commons.util.StringUtils;
import com.tuanche.console.util.GlobalConstants;

public class ApplyNum {
	private String addtime,bmtime,newsid,accountCode,carstyleName;
	private int sid,num,districtId,brandId,carstyleId,bmtype,position,sum;
	
	public String getCarstyleName(){
		if(GlobalConstants.carstyleMap.containsKey(brandId)&&GlobalConstants.carstyleMap.get(brandId).containsKey(carstyleId)){
			return GlobalConstants.carstyleMap.get(getBrandId()).get(carstyleId);
		}
		return "未知";
	}
	
	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getNewsid() {
		return newsid;
	}
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}
	public int getDistrictId() {
		return districtId;
	}
	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}
	public int getCarstyleId() {
		return carstyleId;
	}
	public void setCarstyleId(int carstyleId) {
		this.carstyleId = carstyleId;
	}
	public String getBmtime() {
		return bmtime;
	}
	public String getMMddtime(){
		if(!StringUtils.isEmpty(bmtime)){
			String[] split = bmtime.split("-");
			if(split.length >= 3){
				return split[1]+split[2];
			}
		}
		return null;
	}
	public void setBmtime(String bmtime) {
		this.bmtime = bmtime;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public int getBmtype() {
		return bmtype;
	}
	public void setBmtype(int bmtype) {
		this.bmtype = bmtype;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
}
