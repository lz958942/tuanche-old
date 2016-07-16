package com.tuanche.bean.admin;

import com.tuanche.smc.common.page.impl.Pagination;
/**
 * 问题
 * @author wtk
 *
 */
public class Question {
	private Integer id; //主键
	private String content; //内容
	private Integer firstkindId;	//一级分类
	private Integer secondkindId;	//二级分类
	private Byte questionStatus;//状态（0代表删除，1代表已下线，2代表上传成功未核审，3代表核审通过未上线，4代表已经上线）
	private Integer buildEmp; //创建人id
	private String buildTime;		//创建时间
	private Integer updateEmp;	//修改人id
	private String updateTime;	//修改时间
	private String lable;	//所属的相关标签（逗号分隔）
	private Byte isResolve; //问题是否已经解决（0代表未解决，1代表已解决）
	private String showEmp; //页面显示用户
	private String keyword; //相关关键词
	private String kdId;  //相关关键词维度
	private Byte isRecom; //是否为推荐问题
	private String title;	//标题
	private String dimension; //维度
	private Integer reply;	//回复数
	private Integer brandId;
	private Integer carstyleId;
	private Integer deduplication; //重复标记
	
	private Pagination page;
	
	

	public Integer getDeduplication() {
		return deduplication;
	}
	public void setDeduplication(Integer deduplication) {
		this.deduplication = deduplication;
	}
	public Integer getCarstyleId() {
		return carstyleId;
	}
	public void setCarstyleId(Integer carstyleId) {
		this.carstyleId = carstyleId;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	public Integer getReply() {
		return reply;
	}
	public void setReply(Integer reply) {
		this.reply = reply;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Byte getIsRecom() {
		return isRecom;
	}
	public void setIsRecom(Byte isRecom) {
		this.isRecom = isRecom;
	}
	public Integer getFirstkindId() {
		return firstkindId;
	}
	public void setFirstkindId(Integer firstkindId) {
		this.firstkindId = firstkindId;
	}
	public Integer getSecondkindId() {
		return secondkindId;
	}
	public void setSecondkindId(Integer secondkindId) {
		this.secondkindId = secondkindId;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getKdId() {
		return kdId;
	}
	public void setKdId(String kdId) {
		this.kdId = kdId;
	}
	public String getShowEmp() {
		return showEmp;
	}
	public void setShowEmp(String showEmp) {
		this.showEmp = showEmp;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Byte getQuestionStatus() {
		return questionStatus;
	}
	public void setQuestionStatus(Byte questionStatus) {
		this.questionStatus = questionStatus;
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
	public Byte getIsResolve() {
		return isResolve;
	}
	public void setIsResolve(Byte isResolve) {
		this.isResolve = isResolve;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", content=" + content + ", firstkindId="
				+ firstkindId + ", secondkindId=" + secondkindId
				+ ", questionStatus=" + questionStatus + ", buildEmp="
				+ buildEmp + ", buildTime=" + buildTime + ", updateEmp="
				+ updateEmp + ", updateTime=" + updateTime + ", lable=" + lable
				+ ", isResolve=" + isResolve + ", showEmp=" + showEmp
				+ ", keyword=" + keyword + ", kdId=" + kdId + ", isRecom="
				+ isRecom + ", title=" + title + ", dimension=" + dimension
				+ ", reply=" + reply + ", brandId=" + brandId + ", carstyleId="
				+ carstyleId + ", page=" + page + "]";
	}

}
