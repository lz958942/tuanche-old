package com.tuanche.bean.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tuanche.smc.common.page.impl.Pagination;

/**
 * @author Administrator
 *  装饰板块
 */
public class DecorateTemp {
	
	private Integer id;//主键
	private Integer baseId;//基本信息Id
	private String baseTitle;//标题
	private Integer plateId;//分类Id
	private String title;//小标题名
	private String titleShowStyle;//显示样式 1.黑色 2.蓝色
	private String titleContentStyle;//内容样式 1.文本 2.图片 3.图文
	private String isDel;//状态 -1：删除 0：未删除
	private String sort;//显示级别
	private Integer addUserId;//操作人Id
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
	public Integer getBaseId() {
		return baseId;
	}
	public void setBaseId(Integer baseId) {
		this.baseId = baseId;
	}
	public String getBaseTitle() {
		return baseTitle;
	}
	public void setBaseTitle(String baseTitle) {
		this.baseTitle = baseTitle;
	}
	public Integer getPlateId() {
		return plateId;
	}
	public void setPlateId(Integer plateId) {
		this.plateId = plateId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleShowStyle() {
		return titleShowStyle;
	}
	public void setTitleShowStyle(String titleShowStyle) {
		this.titleShowStyle = titleShowStyle;
	}
	public String getTitleContentStyle() {
		return titleContentStyle;
	}
	public void setTitleContentStyle(String titleContentStyle) {
		this.titleContentStyle = titleContentStyle;
	}
	public String getIsDel() {
		return isDel;
	}
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
		SimpleDateFormat dft=new SimpleDateFormat("yyyy-MM-dd");
		if(this.addTime!=null){
			try {
				date=dft.parse(this.addTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return dft.format(date);
		}else{
			return null;
		}
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
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
