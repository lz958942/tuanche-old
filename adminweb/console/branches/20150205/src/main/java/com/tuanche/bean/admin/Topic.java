package com.tuanche.bean.admin;

import com.tuanche.smc.common.page.impl.Pagination;


/**
 * 话题
 * @author wtk
 *
 */
public class Topic {
	private Integer id;  //主键
	private String title;		//话题title
	private String content;		//话题内容
	private String picture;		//图片url
	private Integer brandId;	//品牌id
	private Integer carstyleId;	//车型id
	private Integer review;	//评论数
	private Byte isRecom;	//是否推荐（0为否，1为是）
	private Integer buildEmp;	//创建人
	private String buildTime;	//创建时间
	private Integer updateEmp;	//修改人
	private String updateTime;	//修改时间
	private Byte topicStatus;	//状态（-1为删除，0为未审核，1为上线）
	private Pagination page;	//分页
	private Byte current;		//是否本期话题
	public Byte getCurrent() {
		return current;
	}
	public void setCurrent(Byte current) {
		this.current = current;
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public Integer getCarstyleId() {
		return carstyleId;
	}
	public void setCarstyleId(Integer carstyleId) {
		this.carstyleId = carstyleId;
	}
	public Integer getReview() {
		return review;
	}
	public void setReview(Integer review) {
		this.review = review;
	}
	public Byte getIsRecom() {
		return isRecom;
	}
	public void setIsRecom(Byte isRecom) {
		this.isRecom = isRecom;
	}
	public Integer getBuildEmp() {
		return buildEmp;
	}
	public void setBuildEmp(Integer buildEmp) {
		this.buildEmp = buildEmp;
	}
	public String getBuildTime() {
		return buildTime;
	}
	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}
	public Integer getUpdateEmp() {
		return updateEmp;
	}
	public void setUpdateEmp(Integer updateEmp) {
		this.updateEmp = updateEmp;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Byte getTopicStatus() {
		return topicStatus;
	}
	public void setTopicStatus(Byte topicStatus) {
		this.topicStatus = topicStatus;
	}
}
