package com.tuanche.cms.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import com.tuanche.cms.common.page.impl.Pagination;
import com.tuanche.commons.util.StringUtils;

public class Tlcity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 类型 为资讯
	 */
	public static final Integer type_zixun = 1;
	
	/**
	 * 类型 为城市首页
	 */
	public static final Integer type_city = 2;
	
	/**
	 * 类型 为摇号
	 */
	public static final Integer type_yaohao = 3;

	private Integer id;

    private Integer cityId;

    private String cityName;

    private Integer tid;

    private String tname;

    private String pic;

    private Byte locked;

    private Integer createUserId;

    private String createUserName;

    private Timestamp createTime;

    private Integer updateUserId;

    private String updateUserName;

    private Timestamp updateTime;

    private Integer deleteFlay;

    private String content;
    
    /**
     * 城市模板分类  1：资讯   2：城市首页
     */
    private Integer tlcitytype;
    
    /**
     *分页信息
     */
    private Pagination page;


    
    
    public Integer getTlcitytype() {
		return tlcitytype;
	}

	public void setTlcitytype(Integer tlcitytype) {
		this.tlcitytype = tlcitytype;
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
		if(StringUtils.isEmpty(cityName)){
	  	   return null;
	    }
        return cityName;
    }

    public void setCityName(String cityName) {
    	
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Byte getLocked() {
        return locked;
    }

    public void setLocked(Byte locked) {
        this.locked = locked;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleteFlay() {
        return deleteFlay;
    }

    public void setDeleteFlay(Integer deleteFlay) {
        this.deleteFlay = deleteFlay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}