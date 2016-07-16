package com.tuanche.bean.admin;

import com.tuanche.console.util.StringUtils;
import com.tuanche.smc.common.page.impl.Pagination;

public class Black {
    private Integer id;
    private String word;
    private Integer type;
    private Integer status;
    private Integer createUid;
    private Integer updateUid;
    private String createTime;
    private String updateTime;
    public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	private Pagination page;
    
    public Integer getCreateUid() {
		return createUid;
	}
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	public Integer getUpdateUid() {
		return updateUid;
	}
	public void setUpdateUid(Integer updateUid) {
		this.updateUid = updateUid;
	}
	public String getCreateTime() {
		if(StringUtils.isNotEmptyString(createTime)){
			return createTime.substring(0,createTime.length()-2);
		}
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		if(StringUtils.isNotEmptyString(updateTime)){
			return updateTime.substring(0,updateTime.length()-2);
		}
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
	@Override
	public String toString() {
		return "Black [id=" + id + ", word=" + word + ", type=" + type
				+ ", status=" + status + ", createUid=" + createUid
				+ ", updateUid=" + updateUid + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
    
}