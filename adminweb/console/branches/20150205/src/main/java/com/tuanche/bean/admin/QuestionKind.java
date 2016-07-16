package com.tuanche.bean.admin;


/**
 * 问题类别
 * @author wtk
 *
 */
public class QuestionKind {
	private Integer id;	//主键
	private String name; //分类名
	private Integer parentId; //父Id
	private Integer buildEmp;	//创建人
	private String buildTime;	//创建时间
	private Integer updateEmp;	//修改人id
	private String updateTime;	//修改时间
	private String lable;	//分类所属的相关标签（逗号分隔）
	private Byte kindStatus;	//状态（0代表删除，1代表已下线，2代表上传成功未核审，3代表核审通过未上线，4代表已经上线） 
	private String title;	//标题
	private String keyword;	//关键字 
	private String kdId;	//关键字id
	private String dimension;	//关键字维度
	private String pageKeyword; //页面关键字
	private String pageDesc;	//页面描述
	private String pageTitle;	//页面标题
	private Integer questCount; //问题总数
	private Integer sort;	//类别排序
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getPageKeyword() {
		return pageKeyword;
	}
	public void setPageKeyword(String pageKeyword) {
		this.pageKeyword = pageKeyword;
	}
	public String getPageDesc() {
		return pageDesc;
	}
	public void setPageDesc(String pageDesc) {
		this.pageDesc = pageDesc;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	//private Integer questCount;	//问题数量
	public Integer getQuestCount() {
		return questCount;
	}
	public void setQuestCount(Integer questCount) {
		this.questCount = questCount;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getKdId() {
		return kdId;
	}
	public void setKdId(String kdId) {
		this.kdId = kdId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public Byte getKindStatus() {
		return kindStatus;
	}
	public void setKindStatus(Byte kindStatus) {
		this.kindStatus = kindStatus;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
