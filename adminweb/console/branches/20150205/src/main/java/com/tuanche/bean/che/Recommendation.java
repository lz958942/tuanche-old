package com.tuanche.bean.che;



import com.tuanche.smc.common.page.impl.Pagination;

public class Recommendation  implements Cloneable {
    private Integer id;
    private Integer cityId;
    private Integer brandId;
    private Integer carstyleId;
    private String type;
    private Integer rBrandId;
    private Integer rStyleId;
    private String createTime;
    private Integer createUid;
    private String updateTime;
    private Integer updateUid;
    private Integer status;
    private Pagination page;
    
    /*************非持久层*************************/
    private String brandName,carStyleName,brandNameR,carStyleNameR;
    
    
    public String getBrandNameR() {
		return brandNameR;
	}

	public void setBrandNameR(String brandNameR) {
		this.brandNameR = brandNameR;
	}

	public String getCarStyleNameR() {
		return carStyleNameR;
	}

	public void setCarStyleNameR(String carStyleNameR) {
		this.carStyleNameR = carStyleNameR;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCarStyleName() {
		return carStyleName;
	}

	public void setCarStyleName(String carStyleName) {
		this.carStyleName = carStyleName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getrBrandId() {
        return rBrandId;
    }

    public void setrBrandId(Integer rBrandId) {
        this.rBrandId = rBrandId;
    }

    public Integer getrStyleId() {
        return rStyleId;
    }

    public void setrStyleId(Integer rStyleId) {
        this.rStyleId = rStyleId;
    }

    public String getCreateTime() {
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(Integer updateUid) {
        this.updateUid = updateUid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Object clone() {
		Object o=null;
		try {
		o=super.clone();
		}
		catch(CloneNotSupportedException e) {
		System.out.println(e.toString());
		}
		return o;
		}

	@Override
	public String toString() {
		return "Recommendation [id=" + id + ", cityId=" + cityId + ", brandId="
				+ brandId + ", carstyleId=" + carstyleId + ", type=" + type
				+ ", rBrandId=" + rBrandId + ", rStyleId=" + rStyleId
				+ ", createTime=" + createTime + ", createUid=" + createUid
				+ ", updateTime=" + updateTime + ", updateUid=" + updateUid
				+ ", status=" + status + ", page=" + page + ", brandName="
				+ brandName + ", carStyleName=" + carStyleName + "]";
	}
    
}