package com.tuanche.bean.admin;

import com.tuanche.smc.common.page.impl.Pagination;


/**
 * 文章
 * @author wtk
 *
 */
public class Article {
	private Integer id;	//主键
	private Integer magazineId;	//关联周刊表主键
	private  String title;	//文章标题
	private String content;	//文章内容
	private String picture;	//图片地址url
	private String url;		//图片指向url
	private Integer buildEmp; //创建人
	private String buildTime;	//创建时间
	private Integer updateEmp;		//修改人
	private String updateTime;		//修改时间
	private Byte articleStatus;		//文章状态
	private Integer sort;		//文章排序
	private String hiddenUrl;	//隐藏url
	public String getHiddenUrl() {
		return hiddenUrl;
	}
	public void setHiddenUrl(String hiddenUrl) {
		this.hiddenUrl = hiddenUrl;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	private Pagination page;		//分页
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
	public Integer getMagazineId() {
		return magazineId;
	}
	public void setMagazineId(Integer magazineId) {
		this.magazineId = magazineId;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public Byte getArticleStatus() {
		return articleStatus;
	}
	public void setArticleStatus(Byte articleStatus) {
		this.articleStatus = articleStatus;
	}

	
}
