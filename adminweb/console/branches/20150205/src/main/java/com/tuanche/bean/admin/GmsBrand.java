package com.tuanche.bean.admin;

import java.io.Serializable;
import java.util.Date;

public class GmsBrand implements Serializable{
	//删除
	public static final Integer IS_DELETE = 2;
    //分类，1：热门，2：高端，3：国产，4：合资
	public static final Integer TYPE_RM = 1;
	public static final Integer TYPE_GD = 2;
	public static final Integer TYPE_GC = 3;
	public static final Integer TYPE_HZ = 4;
	
	private static final long serialVersionUID = -3487980028143912212L;

	private Integer id;

    private Integer gcmId;

    private Integer type;

    private Integer brandId;
    
    private Integer sort;

    private Integer isDelete;

    private Date createTime;

    private Integer createUserId;

    private String createUserName;

    private Date updateTime;

    private Integer updateUserId;

    private String updateUserName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGcmId() {
        return gcmId;
    }

    public void setGcmId(Integer gcmId) {
        this.gcmId = gcmId;
    }
    

    public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName == null ? null : updateUserName.trim();
    }
}