package com.tuanche.bean.che;

import java.util.Date;

import com.tuanche.console.util.Encriptor;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.console.util.Resources;

/**
 * @author tcw
 *
 */
public class BrandGroupbuy {
	private int id,cityId,brandId,salerId,baseNum,openGroupbuyNum,addUserId,lastUserId,groupbuyState,isdel,historyOpen;
	private String title,cityCode,token,webUrl,salerName,groupbuyLight,groupbuyPlace,prompt;
	private Date groupbuyDate,addDate,lastUpDate;
	

	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
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
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getBrandId() {
		return brandId;
	}
	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}
	public int getSalerId() {
		return salerId;
	}
	public void setSalerId(int salerId) {
		this.salerId = salerId;
	}
	public int getBaseNum() {
		return baseNum;
	}
	public void setBaseNum(int baseNum) {
		this.baseNum = baseNum;
		this.openGroupbuyNum=(int) (baseNum+Math.round(Math.random()*30));
	}
	public int getOpenGroupbuyNum() {
		return openGroupbuyNum;
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
	public int getGroupbuyState() {
		return groupbuyDate!=null?1:0;
	}
	public int getIsdel() {
		return isdel;
	}
	public void setIsdel(int isdel) {
		this.isdel = isdel;
	}
	public String getSalerName() {
		if(salerId==0 ||!GlobalConstants.employeeMap.containsKey(salerId)) return "";
		return GlobalConstants.employeeMap.get(salerId);
	}
	public String getTitle() {
		return title==null?(GlobalConstants.districtMap.get(getCityCode()).getName()+GlobalConstants.brandMap.get(brandId).getName()+"团购"):title;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public String getWebUrl() {
		return "http://"+GlobalConstants.districtMap.get(getCityCode()).getPy()+GlobalConstants.WEB_HOST+"/b"+brandId+"/tuan";
	}
	public int getHistoryOpen() {
		return historyOpen;
	}
	public void setHistoryOpen(int historyOpen) {
		this.historyOpen = historyOpen;
	}
	public String getGroupbuyLight() {
		return groupbuyLight==null?"":groupbuyLight;
	}
	public void setGroupbuyLight(String groupbuyLight) {
		this.groupbuyLight = groupbuyLight;
	}
	public String getGroupbuyPlace() {
		return groupbuyPlace;
	}
	public void setGroupbuyPlace(String groupbuyPlace) {
		this.groupbuyPlace = (groupbuyPlace==null?"":groupbuyPlace);
	}
	@Override
	public String toString() {
		return "BrandGroupbuy [id=" + id + ", cityId=" + cityId + ", brandId="
				+ brandId + ", salerId=" + salerId + ", baseNum=" + baseNum
				+ ", openGroupbuyNum=" + openGroupbuyNum + ", addUserId="
				+ addUserId + ", lastUserId=" + lastUserId + ", groupbuyState="
				+ groupbuyState + ", isdel=" + isdel + ", historyOpen="
				+ historyOpen + ", title=" + title + ", cityCode=" + cityCode
				+ ", token=" + token + ", webUrl=" + webUrl + ", salerName="
				+ salerName + ", groupbuyLight=" + groupbuyLight
				+ ", groupbuyPlace=" + groupbuyPlace + ", groupbuyDate="
				+ groupbuyDate + ", addDate=" + addDate + ", lastUpDate="
				+ lastUpDate + "]";
	}
	
}
