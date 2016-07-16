package com.tuanche.bean.che;

import java.io.Serializable;

import com.tuanche.console.util.StringUtils;
import com.tuanche.smc.common.page.impl.Pagination;

public class SysConfig implements Serializable{
    private Integer id;
    private String key;
    private String desc;
    private String name;
    private String code;
    private Integer status;
    private Integer createUid;
    private String createTime;
    private Pagination page;
    
    public String getCreateTime() {
    	if(StringUtils.isNotEmptyString(createTime)){
    		return createTime.substring(0,createTime.length()-2);
    	}
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateUid() {
		return createUid;
	}

	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
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

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
    	if(StringUtils.isNotEmptyString(key)){
    		 this.key = key.trim();
    	}else{
    		 this.key = key;
    	}
       
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
	    if(StringUtils.isNotEmptyString(desc)){
	   		 this.desc = desc.trim();
	   	}else{
	   	 this.desc = desc;
	   	}
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
    	 if(StringUtils.isNotEmptyString(code)){
	   		 this.code = code.trim();
	   	}else{
	   		this.code = code;
	   	}
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "SysConfig [id=" + id + ", key=" + key + ", desc=" + desc
				+ ", name=" + name + ", code=" + code + ", status=" + status
				+ "]";
	}
    
}