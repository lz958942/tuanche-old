package com.tuanche.bean.admin;

import java.util.Date;

import com.tuanche.console.util.StringUtils;
import com.tuanche.smc.common.page.impl.Pagination;
/**
 * 答案
 * @author wtk
 *
 */
public class Answer {
	private Integer id;	//主键
	private String content;	//答案
	private Integer questionId;	//问题表外键
	private Byte answerStatus;	//状态（0代表删除，1代表已下线，2代表上传成功未核审，3代表核审通过未上线，4代表已经上线）
	private Integer buildEmp;	//创建人
	private String buildTime;	//创建时间
	private Integer updateEmp;	//修改人
	private String updateTime;	//修改时间
	private Byte answerAdopt;	//答案是否被采纳（0代表未采纳，1代表采纳）
	private String showEmp;
	private Pagination page;
	private String questionAnswer; //问题对应答案的标题
	
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
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
	public Byte getAnswerStatus() {
		return answerStatus;
	}
	public void setAnswerStatus(Byte answerStatus) {
		this.answerStatus = answerStatus;
	}
	public Integer getBuildEmp() {
		return buildEmp;
	}
	public void setBuildEmp(Integer buildEmp) {
		this.buildEmp = buildEmp;
	}
	public String getBuildTime() {
		if(StringUtils.isNotEmptyString(buildTime)){
			return buildTime.substring(0, buildTime.length()-2);
		}
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
		if(StringUtils.isNotEmptyString(updateTime)){
			return updateTime.substring(0, updateTime.length()-2);
		}
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Byte getAnswerAdopt() {
		return answerAdopt;
	}
	public void setAnswerAdopt(Byte answerAdopt) {
		this.answerAdopt = answerAdopt;
	}
	public String getShowEmp() {
		return showEmp;
	}
	public void setShowEmp(String showEmp) {
		this.showEmp = showEmp;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	public String getQuestionAnswer() {
		return questionAnswer;
	}
	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}


}
