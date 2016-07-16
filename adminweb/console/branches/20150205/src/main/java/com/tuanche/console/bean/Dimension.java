package com.tuanche.console.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tuanche.smc.common.page.impl.Pagination;

public class Dimension {
	
	private Integer id;//主键
	private String dieName;//维度名称
	private String status;//维度状态 -1：删除 1：未删除
	private String keywords;// 相关关键词
	private Integer operateUserId;//操作人员id
	private String operateUserName;//操作人员
	private String createTime;//创建时间
	private String updateTime;//更新时间
	private String[] dimNameArray;//接收维度名称
	private String[] keywordsArray;// 接收相关关键词
	private String startTime;//开始时间
	private String endTime;//结束时间
	
	 private Pagination page;//分页信息
	 private int recordCount;//搜索记录条数
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDieName() {
		return dieName;
	}
	public void setDieName(String dieName) {
		this.dieName = dieName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Integer getOperateUserId() {
		return operateUserId;
	}
	public void setOperateUserId(Integer operateUserId) {
		this.operateUserId = operateUserId;
	}
	public String getOperateUserName() {
		return operateUserName;
	}
	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}
	public String getCreateTime() {
		if(this.createTime!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date=new Date();
			try {
				date=sdf.parse(this.createTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return sdf.format(date);
		}else{
			return createTime;
		}
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		
		if(this.updateTime!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date=new Date();
			try {
				date=sdf.parse(this.updateTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return sdf.format(date);
		}else{
			return updateTime;
		}
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String[] getDimNameArray() {
		return dimNameArray;
	}
	public void setDimNameArray(String[] dimNameArray) {
		this.dimNameArray = dimNameArray;
	}
	public String[] getKeywordsArray() {
		return keywordsArray;
	}
	public void setKeywordsArray(String[] keywordsArray) {
		this.keywordsArray = keywordsArray;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
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
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	
	
}
