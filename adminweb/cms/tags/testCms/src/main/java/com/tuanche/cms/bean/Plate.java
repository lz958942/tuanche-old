package com.tuanche.cms.bean;

import java.io.Serializable;
import java.util.Date;

import com.tuanche.cms.common.page.impl.Pagination;
import com.tuanche.commons.util.StringUtils;

public class Plate implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//数据填充方式（1：自动
	public static final Integer data_fill_mode_Y = 1;
	//2：手动）
	public static final Integer data_fill_mode_N = 2;
	//数据类型
	//资讯
	public static final Integer data_type_zixun = 1;
	//团购
	public static final Integer data_type_tuangou = 2;
	//专题
	public static final Integer data_type_zhuanti = 3;

	//特殊展示类型
	public static final Integer type_teshu = 4;
	//车型
	public static final Integer data_type_car = 5;
	


	private Integer id;

    private Integer cityId;

    private String cityName;

    private Integer tid;

    private String tname;
    
    private String tpath;

    private Integer type;
    
    private Integer ptype;

    private Integer dataType;
    
    private Integer dataTypeClass;

    private String plateName;

    private String label;

    private String title;

    private String descriction;

    private String memo;

    private Integer imageWidth;

    private Integer imageHight;

    private Integer createUserId;

    private String createUserName;

    private Date createTime;

    private Integer updateUserId;

    private String updateUserName;

    private Date updateTime;

    private Integer deleteFlay;

    private String expand;
    
    private String hyperlink;
    
    private String imageUrl;
    
    private Integer dataFillMode;

    private Integer conCount;
    
    public Integer getPtype() {
		return ptype;
	}

	public void setPtype(Integer ptype) {
		this.ptype = ptype;
	}

	public Integer getDataTypeClass() {
		return dataTypeClass;
	}

	public void setDataTypeClass(Integer dataTypeClass) {
		this.dataTypeClass = dataTypeClass;
	}

	public String getTpath() {
		return tpath;
	}

	public void setTpath(String tpath) {
		this.tpath = tpath;
	}

	public Integer getDataFillMode() {
		return dataFillMode;
	}

	public void setDataFillMode(Integer dataFillMode) {
		this.dataFillMode = dataFillMode;
	}

	public Integer getConCount() {
		return conCount;
	}

	public void setConCount(Integer conCount) {
		this.conCount = conCount;
	}

	private Pagination page;
    
    

    public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public String getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(String hyperlink) {
		this.hyperlink = hyperlink;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getPlateName() {
    	if(StringUtils.isEmpty(plateName)){
    		return null;
    	}
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName == null ? null : plateName.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

   

    public String getDescriction() {
		return descriction;
	}

	public void setDescriction(String descriction) {
		this.descriction = descriction;
	}

	public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    public Integer getImageHight() {
        return imageHight;
    }

    public void setImageHight(Integer imageHight) {
        this.imageHight = imageHight;
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

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand == null ? null : expand.trim();
    }
}