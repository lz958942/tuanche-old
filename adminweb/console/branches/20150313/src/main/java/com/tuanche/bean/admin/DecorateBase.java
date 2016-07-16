package com.tuanche.bean.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tuanche.smc.common.page.impl.Pagination;

/**
 * @author liuhg
 * 装饰基础类
 */
public class DecorateBase {
	
	private String status; //1.上线 2.未上线
	private String isDel;//0.未删除 -1.已删除
	private Integer id; //主键
	private Integer kindId;//分类Id
	private String title;//装饰标题
	private String keywords;//关键词
	private String topPicUrl;//头图url
	private Integer cityId;//城市Id
	private String decDesc;//装饰描述
	private String prePrice;//团车价
	private String marPrice;//市场价
	private String picUrl;//标题图片
	private Integer addUserId;//操作人Id;
	private String addUserName;//操作人
	private String addTime;//添加时间
	private String startTime;//开始时间
	private String endTime;//结束时间
	private Pagination page;//分页
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getKindId() {
		return kindId;
	}
	public void setKindId(Integer kindId) {
		this.kindId = kindId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getTopPicUrl() {
		return topPicUrl;
	}
	public void setTopPicUrl(String topPicUrl) {
		this.topPicUrl = topPicUrl;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getDecDesc() {
		return decDesc;
	}
	public void setDecDesc(String decDesc) {
		this.decDesc = decDesc;
	}
	public String getPrePrice() {
		return prePrice;
	}
	public void setPrePrice(String prePrice) {
		this.prePrice = prePrice;
	}
	public String getMarPrice() {
		return marPrice;
	}
	public void setMarPrice(String marPrice) {
		this.marPrice = marPrice;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Integer getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}
	public String getAddUserName() {
		return addUserName;
	}
	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}
	public String getAddTime() {
		Date date=null;
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		if(this.addTime!=null&&!"".equals(this.addTime)){
			try {
				date=df.parse(this.addTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return df.format(date);
		}else{
			return null;
		}
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
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
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
}
