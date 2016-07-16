package com.tuanche.bean.admin;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.tuanche.commons.util.StringUtils;
import com.tuanche.smc.common.page.impl.Pagination;

public class GmsCity implements Serializable{
	private static final long serialVersionUID = -2895854941510269549L;
	//显示   4s店及以下
	public static final Integer IS_SHOW = 1;
	//删除
	public static final Integer IS_DELETE = 2;
	//非上线状态
	public static final Integer NO_ONLINE= 1;
	//上线状态
	public static final Integer ONLINE= 2;
	
	//车展相关
	private GmsAddress gmsAddress;
	//热门车型
    private List<GmsBrand> hotStyle;
    //高端车型
    private List<GmsBrand> highStyle;
    //国产车型
    private List<GmsBrand> homemadeStyle;
    //合资车型
    private List<GmsBrand> jointStyle;
    //车展新闻
    private List<GmsContent> carNews;
    //车展回顾
    private List<GmsContent> carReview;
    //参展4s店
    private List<GmsContent> showShops;
    //往期车展
    private List<GmsContent> oldCarShow;
    //县市巡展
    private List<GmsContent> countyGoShow;
    
    
    /**
     * 团车会期数
     */
    private Integer periodsNum;
    
	

	private Integer id;

    private Integer cityId;

    private String cityName;

    private String title;

    private Date beginTime;
    
    private String beginTimeStr;

    private Date endTime;
    
    private String endTimeStr;

    private String address;

    private String image;

    private String url;

    private String addressNumber;

    private String addressDesc;
    
    private Integer isShow;
    
    private Integer online;

    private Integer isDelete;

    private Date createTime;

    private Integer createUserId;

    private String createUserName;

    private Date updateTime;

    private Integer updateUserId;

    private String updateUserName;
    
    private Pagination page;
    
    
    

    
	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getAddressDesc() {
		return addressDesc;
	}

	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public Integer getPeriodsNum() {
		return periodsNum;
	}

	public void setPeriodsNum(Integer periodsNum) {
		this.periodsNum = periodsNum;
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
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getImage() {
    	if(StringUtils.isEmpty(image)){
    		return null;
    	}
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
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

	public GmsAddress getGmsAddress() {
		return gmsAddress;
	}

	public void setGmsAddress(GmsAddress gmsAddress) {
		this.gmsAddress = gmsAddress;
	}

	public List<GmsBrand> getHotStyle() {
		return hotStyle;
	}

	public void setHotStyle(List<GmsBrand> hotStyle) {
		this.hotStyle = hotStyle;
	}

	public List<GmsBrand> getHighStyle() {
		return highStyle;
	}

	public void setHighStyle(List<GmsBrand> highStyle) {
		this.highStyle = highStyle;
	}

	public List<GmsBrand> getHomemadeStyle() {
		return homemadeStyle;
	}

	public void setHomemadeStyle(List<GmsBrand> homemadeStyle) {
		this.homemadeStyle = homemadeStyle;
	}

	public List<GmsBrand> getJointStyle() {
		return jointStyle;
	}

	public void setJointStyle(List<GmsBrand> jointStyle) {
		this.jointStyle = jointStyle;
	}

	public List<GmsContent> getCarNews() {
		return carNews;
	}

	public void setCarNews(List<GmsContent> carNews) {
		this.carNews = carNews;
	}

	public List<GmsContent> getCarReview() {
		return carReview;
	}

	public void setCarReview(List<GmsContent> carReview) {
		this.carReview = carReview;
	}

	public List<GmsContent> getShowShops() {
		return showShops;
	}

	public void setShowShops(List<GmsContent> showShops) {
		this.showShops = showShops;
	}

	public List<GmsContent> getOldCarShow() {
		return oldCarShow;
	}

	public void setOldCarShow(List<GmsContent> oldCarShow) {
		this.oldCarShow = oldCarShow;
	}

	public List<GmsContent> getCountyGoShow() {
		return countyGoShow;
	}

	public void setCountyGoShow(List<GmsContent> countyGoShow) {
		this.countyGoShow = countyGoShow;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public String getBeginTimeStr() {
		 if(this.beginTime!=null){
            String pattern = "yyyy-MM-dd H:mm:ss";
            Date date = beginTime;
            String format = formatDate(pattern, date);
            return format;
        }
        return beginTimeStr;
	}

	public void setBeginTimeStr(String beginTimeStr) {
		if(StringUtils.isNotEmpty(beginTimeStr)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
            Date parse = null;
            try {
                parse = simpleDateFormat.parse(beginTimeStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.beginTime = parse;
        }
        this.beginTimeStr = beginTimeStr;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getEndTimeStr() {
		if(this.endTime!=null){
            String pattern = "yyyy-MM-dd H:mm:ss";
            Date date = endTime;
            String format = formatDate(pattern, date);
            return format;
        }
        return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		if(StringUtils.isNotEmpty(endTimeStr)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
            Date parse = null;
            try {
                parse = simpleDateFormat.parse(endTimeStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.endTime = parse;
        }
        this.endTimeStr = endTimeStr;
	}
	
	private String formatDate(String pattern, Date date) {
	    if(date == null){
	        return null;
	    }
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	    String format = simpleDateFormat.format(date);
	    return format;
	}
}