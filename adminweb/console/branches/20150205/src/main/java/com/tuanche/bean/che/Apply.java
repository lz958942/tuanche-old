package com.tuanche.bean.che;

import com.tuanche.console.util.GlobalConstants;

public class Apply {
	private Integer id,memberId,brandId,carstyleId,districtId,uid,state,cmid,buyway,getcartime,newsid,sid,bmtype,position;
	private String name,phone,knowprice,addTime,upTime,remarks,phonePlace,ipPlace,
					place,bmtime,accountCode,bizCode,ip,sidName,applyType;
	private Long kid;
	
	
	
	public String getApplyType() {
		if(GlobalConstants.applyTypeMap.containsKey(bmtype))
			return GlobalConstants.applyTypeMap.get(bmtype);
		return "未知";
	}
	
	public String getPositionType() {
		if(GlobalConstants.positionTypeMap.containsKey(position))
			return GlobalConstants.positionTypeMap.get(position);
		return "未知";
	}
	public String getSidName() {
		if(GlobalConstants.sidSiteMap.containsKey(sid))
			return GlobalConstants.sidSiteMap.get(sid);
		return "未知";
	}
	public String getBuywayName() {
		return GlobalConstants.buywayMap.get(this.buyway);
	}
	public String getGetcartimeName() {
		return GlobalConstants.getcartimeMap.get(this.getcartime);
	}
	public String getStateName() {
		return GlobalConstants.oldApplyStateMap.get(state);
	}
	public String getDistrictName() {
		return GlobalConstants.districtMap.get(GlobalConstants.districtIdCitycodeMap.get(districtId)).getName();
	}
	
	
	
	public Long getKid() {
		return kid;
	}

	public void setKid(Long kid) {
		this.kid = kid;
	}

	public Integer getBmtype() {
		return bmtype;
	}
	public void setBmtype(Integer bmtype) {
		this.bmtype = bmtype;
	}
	
	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getBrandName() {
		if(GlobalConstants.brandMap.containsKey(brandId)){
			return GlobalConstants.brandMap.get(brandId).getName();
		}
		return "未知";
	}
	public String getCarstyleName() {
		if(GlobalConstants.carstyleMap.containsKey(brandId)
				&&GlobalConstants.carstyleMap.get(brandId).containsKey(carstyleId)){
			return GlobalConstants.carstyleMap.get(brandId).get(carstyleId);
		}
		return "未知";
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getCarstyleId() {
		return carstyleId;
	}
	public void setCarstyleId(Integer carstyleId) {
		this.carstyleId = carstyleId;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getKnowprice() {
		return knowprice;
	}
	public void setKnowprice(String knowprice) {
		this.knowprice = knowprice;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getUpTime() {
		return upTime;
	}
	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getCmid() {
		return cmid;
	}
	public void setCmid(Integer cmid) {
		this.cmid = cmid;
	}
	public Integer getBuyway() {
		return buyway;
	}
	public void setBuyway(Integer buyway) {
		this.buyway = buyway;
	}
	public Integer getGetcartime() {
		return getcartime;
	}
	public void setGetcartime(Integer getcartime) {
		this.getcartime = getcartime;
	}
	public String getPhonePlace() {
		return phonePlace;
	}
	public void setPhonePlace(String phonePlace) {
		this.phonePlace = phonePlace;
	}
	public String getIpPlace() {
		return ipPlace;
	}
	public void setIpPlace(String ipPlace) {
		this.ipPlace = ipPlace;
	}
	
	public String getBmtime() {
		return bmtime;
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
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public Integer getNewsid() {
		return newsid;
	}
	public void setNewsid(Integer newsid) {
		this.newsid = newsid;
	}
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
