package com.tuanche.bean.che;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tuanche.console.util.Encriptor;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.console.util.Resources;
import com.tuanche.smc.common.Common;

public class CarstyleGroupbuy {
	private int id,cityId,salerId,brandId,carstyleId,manNum,manBaseNum,passNum,passBaseNum,
		sellNum,sellBaseNum,groupbuyState,addUserId,lastUserId,isdel,saveMoney,baseSeq;
	private String title,cityCode,token,carstyleName,webUrl,cityName,brandName,groupbuyDateStr,groupbuyLight,salerName,carNice;
	private Date groupbuyDate,addDate,lastUpDate;
	public CarstyleGroupbuy(){
		
	}
	
	public int getBaseSeq() {
		return baseSeq;
	}

	public void setBaseSeq(int baseSeq) {
		this.baseSeq = baseSeq;
	}

	public String getCarNice() {
		return carNice;
	}

	public void setCarNice(String carNice) {
		this.carNice = carNice;
	}

	public CarstyleGroupbuy(int manBaseNum,int passBaseNum,int sellBaseNum){
		this.manBaseNum=manBaseNum;
		this.passBaseNum=passBaseNum;
		this.sellBaseNum=sellBaseNum;
		this.manNum=manBaseNum+(int)(Math.round(Math.random()*15+1));
		this.passNum=this.manNum+15;
	}
	
	public void setManNum(int manNum) {
		this.manNum = manNum;
	}
	public void setPassNum(int passNum) {
		this.passNum = passNum;
	}
	public void setSellNum(int sellNum) {
		this.sellNum = sellNum;
	}
	public String getSalerName() {
		if(salerId==0 ||!GlobalConstants.employeeMap.containsKey(salerId)) return "";
		return GlobalConstants.employeeMap.get(salerId);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCityId() {
		return cityId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getSalerId() {
		return salerId;
	}
	public void setSalerId(int salerId) {
		this.salerId = salerId;
	}
	public int getBrandId() {
		return brandId;
	}
	public int getCarstyleId() {
		return carstyleId;
	}
	public void setCarstyleId(int carstyleId) {
		this.carstyleId = carstyleId;
		/*for(Integer bid:GlobalConstants.carstyleMap.keySet()){
			if(GlobalConstants.carstyleMap.get(bid).containsKey(carstyleId)){
				this.brandId=bid;
				this.carstyleName=GlobalConstants.carstyleMap.get(bid).get(carstyleId);
				break;
			}
		}*/
		if(GlobalConstants.getstyleNameByCid.containsKey(carstyleId)){
			this.carstyleName=GlobalConstants.getstyleNameByCid.get(carstyleId).getStyle();
		}
	}
	public int getManNum() {
		return manNum;
	}
	public int getManBaseNum() {
		return manBaseNum;
	}
	public void setManBaseNum(int manBaseNum) {
		this.manBaseNum = manBaseNum;
		this.manNum=(int) (manBaseNum+Math.round(Math.random()*15+1));
	}
	public int getPassNum() {
		return passNum;
	}
	public int getPassBaseNum() {
		return passBaseNum;
	}
	public void setPassBaseNum(int passBaseNum) {
		this.passBaseNum = passBaseNum;
		this.passNum=passBaseNum+this.manNum+15;
	}
	public int getSellNum() {
		return sellNum;
	}
	public int getSellBaseNum() {
		return sellBaseNum;
	}
	public void setSellBaseNum(int sellBaseNum) {
		this.sellBaseNum = sellBaseNum;
		this.sellNum=(int) (sellBaseNum+Math.round(Math.random()*0));
	}
	public int getGroupbuyState() {
		return groupbuyDate!=null?1:0;
	}
	public void setGroupbuyState(int groupbuyState) {
		this.groupbuyState = groupbuyState;
	}
	public int getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(int addUserId) {
		this.addUserId = addUserId;
	}
	public int getLastUserId() {
		return lastUserId;
	}
	public void setLastUserId(int lastUserId) {
		this.lastUserId = lastUserId;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public String getTitle() {
		return title==null?(GlobalConstants.districtMap.get(getCityCode()).getName()+carstyleName+"团购"):title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCityCode() {
		return cityId<=0 || !GlobalConstants.districtIdCitycodeMap.containsKey(cityId)?"":GlobalConstants.districtIdCitycodeMap.get(cityId);
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public Date getGroupbuyDate() {
		return groupbuyDate;
	}
	public void setGroupbuyDate(Date groupbuyDate) {
		this.groupbuyDate = groupbuyDate;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Date getLastUpDate() {
		return lastUpDate;
	}
	public void setLastUpDate(Date lastUpDate) {
		this.lastUpDate = lastUpDate;
	}
	public String getUpToken() {
		return Encriptor.getMD5(Resources.getWebMessage("update.auth")+""+id);
	}
	public String getStateName() {
		return groupbuyState==0?"筹备中":"确认开团";
	}
	public String getDelName() {
		return isdel==0?"正常":"删除";
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCarstyleName() {
		return carstyleName;
	}
	public int getSaveMoney() {
		return saveMoney;
	}
	public void setSaveMoney(int saveMoney) {
		this.saveMoney = saveMoney;
	}
	public String getWebUrl() {
		return "http://"+GlobalConstants.districtMap.get(getCityCode()).getPy()+GlobalConstants.WEB_HOST+"/c"+carstyleId+"/tuan";
	}
	public String getCityName() {
		return GlobalConstants.districtMap.get(getCityCode()).getName();
	}
	public String getBrandName() {
		return GlobalConstants.brandMap.get(brandId).getName();
	}
	public String getGroupbuyDateStr() {
		return groupbuyDate!=null?new SimpleDateFormat("yyyy-MM-dd").format(groupbuyDate):"";
	}
	public String getGroupbuyLight() {
		return groupbuyLight==null?"":groupbuyLight;
	}
	public void setGroupbuyLight(String groupbuyLight) {
		this.groupbuyLight = groupbuyLight;
	}

	@Override
	public String toString() {
		return "CarstyleGroupbuy [id=" + id + ", cityId=" + cityId
				+ ", salerId=" + salerId + ", brandId=" + brandId
				+ ", carstyleId=" + carstyleId + ", manNum=" + manNum
				+ ", manBaseNum=" + manBaseNum + ", passNum=" + passNum
				+ ", passBaseNum=" + passBaseNum + ", sellNum=" + sellNum
				+ ", sellBaseNum=" + sellBaseNum + ", groupbuyState="
				+ groupbuyState + ", addUserId=" + addUserId + ", lastUserId="
				+ lastUserId + ", isdel=" + isdel + ", saveMoney=" + saveMoney
				+ ", title=" + title + ", cityCode=" + cityCode + ", token="
				+ token + ", carstyleName=" + carstyleName + ", webUrl="
				+ webUrl + ", cityName=" + cityName + ", brandName="
				+ brandName + ", groupbuyDateStr=" + groupbuyDateStr
				+ ", groupbuyLight=" + groupbuyLight + ", salerName="
				+ salerName + ", groupbuyDate=" + groupbuyDate + ", addDate="
				+ addDate + ", lastUpDate=" + lastUpDate + "]";
	}
	
}
