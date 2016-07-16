package com.tuanche.bean.admin;
import com.tuanche.console.util.StringUtils;
import com.tuanche.smc.common.page.impl.Pagination;


public class MediaReport {
    private Integer id;
    private String title;
    private String url;
    private String reportSource;
    private String reportTime;
    private Integer status;
    private Integer createUid;
    private String createTime;
    private Integer updateUid;
    private String updateTime;
    
    
    //非持久化属性
    private String reportTimeNnd; //报道结束时间，查询使用
    private Integer publishUid;
    private String createTimeCope;
    private String updateTimeCope;
    private String reportTimeCope;
    
    
    public String getCreateTimeCope() {
    	if(StringUtils.isNotEmptyString(createTime)){
    		return createTime.substring(0,createTime.length()-10);
    	}
		return "无";
	}
	public String getUpdateTimeCope() {
		if(StringUtils.isNotEmptyString(updateTime)){
    		return updateTime.substring(0,updateTime.length()-10);
    	}
		return "无";
	}
	public String getReportTimeCope() {
		if(StringUtils.isNotEmptyString(reportTime)){
    		return reportTime.substring(0,reportTime.length()-10);
    	}
		return "无";
	}
	
	
	private Pagination page;
    
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	
	public Integer getPublishUid() {
		return publishUid;
	}
	public void setPublishUid(Integer publishUid) {
		this.publishUid = publishUid;
	}
	public String getReportTimeNnd() {
		return reportTimeNnd;
	}
	public void setReportTimeNnd(String reportTimeNnd) {
		this.reportTimeNnd = reportTimeNnd;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getReportSource() {
		return reportSource;
	}
	public void setReportSource(String reportSource) {
		this.reportSource = reportSource;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCreateUid() {
		return createUid;
	}
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getUpdateUid() {
		return updateUid;
	}
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "MediaReport [id=" + id + ", title=" + title + ", url=" + url
				+ ", reportSource=" + reportSource + ", reportTime="
				+ reportTime + ", status=" + status + ", createUid="
				+ createUid + ", createTime=" + createTime + ", updateUid="
				+ updateUid + ", updateTime=" + updateTime + "]";
	}
    
}