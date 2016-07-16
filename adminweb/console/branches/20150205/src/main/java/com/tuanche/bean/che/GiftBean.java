package com.tuanche.bean.che;
import java.io.Serializable;

import com.tuanche.console.util.StringUtils;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.util.KevinUtil;
public class GiftBean implements Serializable {
    private Integer id;
    private String imgUrl;
    private Integer number;
    private String giftDesc;
    private String giftTitle;
    private Integer remainNumber;
    private Integer giftClass;
    private Integer giftSource;
    private Integer exchangeType;
    private Integer exchangeNumber;
    private String exchangeTime;
    private String showEndTime;
    private String showStartTime;
    private String showDate;
    private String createTime;
    private String showTime;
    private Integer createUid;
    private String updateTime;
    private Integer updateUid;
    private Integer giftStatus;
    private Pagination page;
    private String imgTitle,imgTargetUrl;
    
    
    public String getShowTime() {
    	if(StringUtils.isNotEmptyString(createTime)){
    		return createTime.substring(0,createTime.length()-11);
    	}
    	
		return showTime;
	}

	public String getShowDate() {
    	if(StringUtils.isNotEmptyString(showStartTime) &&StringUtils.isNotEmptyString(showEndTime)){
    		return KevinUtil.relativelyDate(showEndTime.substring(0, showEndTime.lastIndexOf("-")+3),showStartTime.substring(0, showStartTime.lastIndexOf("-")+3));
    	}
		return "0";
	}

	public String getImgTitle() {
		return imgTitle;
	}

	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}

	public String getImgTargetUrl() {
		return imgTargetUrl;
	}

	public void setImgTargetUrl(String imgTargetUrl) {
		this.imgTargetUrl = imgTargetUrl;
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

    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getGiftDesc() {
        return giftDesc;
    }

    public void setGiftDesc(String giftDesc) {
        this.giftDesc = giftDesc == null ? null : giftDesc.trim();
    }

    public String getGiftTitle() {
        return giftTitle;
    }

    public void setGiftTitle(String giftTitle) {
        this.giftTitle = giftTitle == null ? null : giftTitle.trim();
    }

    public Integer getRemainNumber() {
    	if(remainNumber==null){
    		return number;
    	}else{
    		return remainNumber;
    	}
    }

    public void setRemainNumber(Integer remainNumber) {
        this.remainNumber = remainNumber;
    }

    public Integer getGiftClass() {
        return giftClass;
    }

    public void setGiftClass(Integer giftClass) {
        this.giftClass = giftClass;
    }

    public Integer getGiftSource() {
        return giftSource;
    }

    public void setGiftSource(Integer giftSource) {
        this.giftSource = giftSource;
    }

    public Integer getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(Integer exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Integer getExchangeNumber() {
        return exchangeNumber;
    }

    public void setExchangeNumber(Integer exchangeNumber) {
        this.exchangeNumber = exchangeNumber;
    }

    public String getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(String exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    public String getShowEndTime() {
        return showEndTime;
    }

    public void setShowEndTime(String showEndTime) {
        this.showEndTime = showEndTime;
    }

    public String getShowStartTime() {
        return showStartTime;
    }

    public void setShowStartTime(String showStartTime) {
        this.showStartTime = showStartTime;
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

	public Integer getGiftStatus() {
        return giftStatus;
    }

    public void setGiftStatus(Integer giftStatus) {
        this.giftStatus = giftStatus;
    }

	@Override
	public String toString() {
		return "GiftBean [id=" + id + ", imgUrl=" + imgUrl + ", number="
				+ number + ", giftDesc=" + giftDesc + ", giftTitle="
				+ giftTitle + ", remainNumber=" + remainNumber + ", giftClass="
				+ giftClass + ", giftSource=" + giftSource + ", exchangeType="
				+ exchangeType + ", exchangeNumber=" + exchangeNumber
				+ ", exchangeTime=" + exchangeTime + ", showEndTime="
				+ showEndTime + ", showStartTime=" + showStartTime
				+ ", createTime=" + createTime + ", createUid=" + createUid
				+ ", updateTime=" + updateTime + ", updateUid=" + updateUid
				+ ", giftStatus=" + giftStatus + ", page=" + page
				+ ", imgTitle=" + imgTitle + ", imgTargetUrl=" + imgTargetUrl
				+ "]";
	}
    
}