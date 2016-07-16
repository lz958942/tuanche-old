package com.tuanche.bean.sem;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tuanche.commons.util.StringUtils;
import com.tuanche.smc.common.page.impl.Pagination;

public class KeywordClassifyCost {
    private int id;

    private int cityId;

    private String cityName;

    private int brandId;

    private Long campaignid;

    private String campaignname;

    private Long groupid;

    private String groupname;

    private Long uesKeywordid;

    private Long keywordid;

    private String title;

    private BigDecimal dayCost;

    private int applyNum;

    private int display;

    private int click;

    private int accountid;

    private int datetime;

    private int device;
    
    private Pagination page;
    
    private String startTime;
    
    private String endTime;
    
    private String strdate;
    
    
    

    public String getStrdate() {
    	if(datetime != 0){
    		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            return sd.format(new Date(datetime * 1000L));
    	}
		return strdate;
	}

	public void setStrdate(String strdate) {
		this.strdate = strdate;
	}

	public String getStartTime() {
    	if(StringUtils.isEmpty(startTime)){
    		Date d=new Date(System.currentTimeMillis()-1000*60*60*24);
        	SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
        	return sp.format(d);//获取昨天日期
    	}
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		if(StringUtils.isEmpty(startTime)){
    		Date d=new Date();
        	SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
        	return sp.format(d);//获取昨天日期
    	}
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public Long getCampaignid() {
        return campaignid;
    }

    public void setCampaignid(Long campaignid) {
        this.campaignid = campaignid;
    }

    public String getCampaignname() {
        return campaignname;
    }

    public void setCampaignname(String campaignname) {
        this.campaignname = campaignname == null ? null : campaignname.trim();
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    public Long getUesKeywordid() {
        return uesKeywordid;
    }

    public void setUesKeywordid(Long uesKeywordid) {
        this.uesKeywordid = uesKeywordid;
    }

    public Long getKeywordid() {
        return keywordid;
    }

    public void setKeywordid(Long keywordid) {
        this.keywordid = keywordid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getDayCost() {
        return dayCost;
    }

    public void setDayCost(BigDecimal dayCost) {
        this.dayCost = dayCost;
    }

    public int getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(int applyNum) {
        this.applyNum = applyNum;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public int getDatetime() {
        return datetime;
    }

    public void setDatetime(int datetime) {
        this.datetime = datetime;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }
}