package com.tuanche.bean.che;

import com.tuanche.console.util.StringUtils;
import com.tuanche.smc.common.page.impl.Pagination;

public class Review {
	private Integer id;
    private Integer applyId;
    private Integer carstyleId;
    private Integer commentTotal;
    private Integer commentPrice;
    private Integer commentService;
    private Integer commentShop;
    private String comment;
    private Integer cityId;
    private Integer ishavepic; //是否有图片
    private Integer state;
	private Pagination page;
	private String carName;
	private Integer isCream; //0不是精华 1 是
	private Integer editId; //后添加列表编辑人
	private String modifyTime; //审核时间
	private String createtime;
	private String endtDate; //非持久化属性
	private Integer isReply; //是否有回复
	private Integer brandId;
	private String evaName;
	private String imageSrc;
	private String reply;
	private Integer memId;
	
	
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	private Integer carstyleModelId;
	public String getImageSrc() {
		return imageSrc;
	}
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	
	public Integer getCarstyleModelId() {
		return carstyleModelId;
	}
	public void setCarstyleModelId(Integer carstyleModelId) {
		this.carstyleModelId = carstyleModelId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getEvaName() {
		return evaName;
	}
	public void setEvaName(String evaName) {
		this.evaName = evaName;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public Integer getIsReply() {
		return isReply;
	}
	public void setIsReply(Integer isReply) {
		this.isReply = isReply;
	}
	public String getEndtDate() {
		return endtDate;
	}
	public void setEndtDate(String endtDate) {
		this.endtDate = endtDate;
	}
	public String getModifyTime() {
		if(StringUtils.isNotEmptyString(modifyTime)){
			return modifyTime.substring(0,modifyTime.length()-2);
		}
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Integer getEditId() {
		return editId;
	}
	public void setEditId(Integer editId) {
		this.editId = editId;
	}
	public Integer getIsCream() {
		return isCream;
	}
	public void setIsCream(Integer isCream) {
		this.isCream = isCream;
	}
	//品论来源
	private Integer source;
	
	
    public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
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
    public Integer getApplyId() {
        return applyId;
    }
    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getCarstyleId() {
        return carstyleId;
    }
    public void setCarstyleId(Integer carstyleId) {
        this.carstyleId = carstyleId;
    }
	public Integer getCommentTotal() {
		return commentTotal;
	}
	public void setCommentTotal(Integer commentTotal) {
		this.commentTotal = commentTotal;
	}
	public Integer getCommentPrice() {
		return commentPrice;
	}
	public void setCommentPrice(Integer commentPrice) {
		this.commentPrice = commentPrice;
	}
	public Integer getCommentService() {
		return commentService;
	}
	public void setCommentService(Integer commentService) {
		this.commentService = commentService;
	}
	public Integer getCommentShop() {
		return commentShop;
	}
	public void setCommentShop(Integer commentShop) {
		this.commentShop = commentShop;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getIshavepic() {
		return ishavepic;
	}
	public void setIshavepic(Integer ishavepic) {
		this.ishavepic = ishavepic;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Review [id=" + id + ", applyId=" + applyId + ", carstyleId="
				+ carstyleId + ", commentTotal=" + commentTotal
				+ ", commentPrice=" + commentPrice + ", commentService="
				+ commentService + ", commentShop=" + commentShop
				+ ", comment=" + comment + ", cityId=" + cityId
				+ ", ishavepic=" + ishavepic + ", state=" + state + ", page="
				+ page + ", carName=" + carName + ", isCream=" + isCream
				+ ", editId=" + editId + ", modifyTime=" + modifyTime
				+ ", createtime=" + createtime + ", endtDate=" + endtDate
				+ ", isReply=" + isReply + ", brandId=" + brandId
				+ ", evaName=" + evaName + ", imageSrc=" + imageSrc
				+ ", reply=" + reply + ", carstyleModelId=" + carstyleModelId
				+ ", source=" + source + "]";
	}
	
   
}
