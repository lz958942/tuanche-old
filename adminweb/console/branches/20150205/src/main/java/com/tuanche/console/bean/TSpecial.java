package com.tuanche.console.bean;

import java.util.Date;

public class TSpecial {
    private Integer id;

    private String sp_Name;

    private String spAbstract;

    private String zixunIds;

    private String picTitle;

    private String spStatus;

    private Integer online;

    private String keyword;

    private String sp_Desc;

    private Integer cityId;

    private Integer brandId;

    private Integer carstyleId;

    private Integer operateUserId;

    private String operateUserName;

    private Date pub_Date;

   

    private String content;
    private Date onlineDate;
    private int templateId;
    
    
    
    public Date getOnlineDate() {
		return onlineDate;
	}

	public void setOnlineDate(Date onlineDate) {
		this.onlineDate = onlineDate;
	}

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSp_Name() {
		return sp_Name;
	}

	public void setSp_Name(String sp_Name) {
		this.sp_Name = sp_Name;
	}

	public String getSpAbstract() {
		return spAbstract;
	}

	public void setSpAbstract(String spAbstract) {
		this.spAbstract = spAbstract;
	}

	public String getZixunIds() {
        return zixunIds;
    }

    public void setZixunIds(String zixunIds) {
        this.zixunIds = zixunIds;
    }

    
    public String getPicTitle() {
        return picTitle;
    }

    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle;
    }

    public String getSpStatus() {
        return spStatus;
    }

    public void setSpStatus(String spStatus) {
        this.spStatus = spStatus;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

   

    public String getSp_Desc() {
		return sp_Desc;
	}

	public void setSp_Desc(String sp_Desc) {
		this.sp_Desc = sp_Desc;
	}

	public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCarstyleId() {
        return carstyleId;
    }

    public void setCarstyleId(Integer carstyleId) {
        this.carstyleId = carstyleId;
    }

    public Integer getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Integer operateUserId) {
        this.operateUserId = operateUserId;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName;
    }

    
    public Date getPub_Date() {
		return pub_Date;
	}

	public void setPub_Date(Date pub_Date) {
		this.pub_Date = pub_Date;
	}

	
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	@Override
	public String toString() {
		return "TSpecial [id=" + id + ", sp_Name=" + sp_Name + ", spAbstract="
				+ spAbstract + ", zixunIds=" + zixunIds + ", picTitle="
				+ picTitle + ", spStatus=" + spStatus + ", online=" + online
				+ ", keyword=" + keyword + ", sp_Desc=" + sp_Desc + ", cityId="
				+ cityId + ", brandId=" + brandId + ", carstyleId="
				+ carstyleId + ", operateUserId=" + operateUserId
				+ ", operateUserName=" + operateUserName + ", pub_Date="
				+ pub_Date + ", content=" + content + ", onlineDate="
				+ onlineDate + ", templateId=" + templateId + "]";
	}

	

	

	
    
}