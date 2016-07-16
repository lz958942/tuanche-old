package com.tuanche.cms.bean;

import java.io.Serializable;
import java.util.Date;

import com.tuanche.cms.common.page.impl.Pagination;
import com.tuanche.commons.util.Resources;
import com.tuanche.commons.util.StringUtils;

public class Content implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer cityId;
    
    private String cityName;
    
    private Integer sort;

    private Integer plateId;

    private String plateName;

    private Integer dataType;

    private String title;

    private String descriction;

    private String imagUrl;

    private String hyperlink;

    private Integer conId;

    private Integer createUserId;

    private String createUserName;

    private Date createTime;

    private Integer updateUserId;

    private String updateUserName;

    private Date updateTime;

    private Integer deleteFlay;
    
    
    private Integer upSort;
    
    private Integer doSort;

    /**
     * 内容说明
     */
    private String expand;
    
    /**
     * 团长id
     */
    private Integer groupLeaderId;
    
    /**
     * 车型id
     */
    private Integer carTypeId;
    
    
    
    private Pagination page;
    //团友说名字
    private String name,title2;

    
    public String getTitle2() {
    	if(StringUtils.isNotEmpty(this.title)){
    		if(this.title.indexOf(",|，")>0){
    			return this.title.split(",")[1];
    		}else if(this.title.indexOf("，")>0){
    			return this.title.split("，")[1];
    		}
    	}
		return this.title;
	}

	public String getName() {
    	if(StringUtils.isNotEmpty(this.title)){
    		if(this.title.indexOf(",")>0){
    			return this.title.split(",")[0];
    		}else if(this.title.indexOf("，")>0){
    			return this.title.split("，")[0];
    		}
    	}
		return "赵先生";
	}

	public Integer getGroupLeaderId() {
		return groupLeaderId;
	}

	public void setGroupLeaderId(Integer groupLeaderId) {
		this.groupLeaderId = groupLeaderId;
	}

	public Integer getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Integer carTypeId) {
		this.carTypeId = carTypeId;
	}

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public Integer getUpSort() {
		return upSort;
	}

	public void setUpSort(Integer upSort) {
		this.upSort = upSort;
	}

	public Integer getDoSort() {
		return doSort;
	}

	public void setDoSort(Integer doSort) {
		this.doSort = doSort;
	}

	public String getExpand() {
		return expand;
	}

	public void setExpand(String expand) {
		this.expand = expand;
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

    public Integer getPlateId() {
        return plateId;
    }
    
    public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		 this.cityName = cityName == null ? null : cityName.trim();
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public void setPlateId(Integer plateId) {
        this.plateId = plateId;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName == null ? null : plateName.trim();
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
        this.title2 = title == null ? null : title.trim();
    }


    public String getDescriction() {
		return descriction;
	}

	public void setDescriction(String descriction) {
		this.descriction = descriction;
	}

	public String getImagUrl() {
		if(imagUrl!=null && imagUrl.contains("http://")){
			return imagUrl;
		}
	    if(StringUtils.isEmpty(imagUrl)){
			return null;
		}else{
			return Resources.getString("pic.url")+imagUrl;
		}
    }

    public void setImagUrl(String imagUrl) {
        this.imagUrl = imagUrl == null ? null : imagUrl.trim();
    }

    public String getHyperlink() {
        return hyperlink;
    }

    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink == null ? null : hyperlink.trim();
    }

    public Integer getConId() {
        return conId;
    }

    public void setConId(Integer conId) {
        this.conId = conId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleteFlay() {
        return deleteFlay;
    }

    public void setDeleteFlay(Integer deleteFlay) {
        this.deleteFlay = deleteFlay;
    }
}