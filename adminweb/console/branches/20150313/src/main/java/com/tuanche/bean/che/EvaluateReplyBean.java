package com.tuanche.bean.che;

import java.io.Serializable;


import com.tuanche.smc.common.page.impl.Pagination;

public class EvaluateReplyBean implements Serializable{
    private Integer id;

    private Integer evaluateId;
    private Integer replyUid;
    private String replyTime;
    private Integer updateUid;
    private String updateTime;
    private Boolean status;
    private String content;
    private Pagination page;
    
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
    public Integer getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Integer evaluateId) {
        this.evaluateId = evaluateId;
    }

    public Integer getReplyUid() {
        return replyUid;
    }

    public void setReplyUid(Integer replyUid) {
        this.replyUid = replyUid;
    }

    public Integer getUpdateUid() {
        return updateUid;
    }
    public void setUpdateUid(Integer updateUid) {
        this.updateUid = updateUid;
    }


    public String getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}