package com.tuanche.cms.bean;

import java.sql.Date;
import java.text.NumberFormat;

import com.tuanche.commons.util.DateUtil;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.info.util.CommonUtil;

public class GroupBuy {
	private int styleId;
	private String styleName;
	private int cityId;
	private int brandId;
	private String brandName;
	private int series;// 1 美系，2 德系，3 法系，4 日系，5 合资，6 自主，7 其它，8 韩系
	private String brandPinYin;
	private String title;
	private int manNum;
	private int passNum;
	private int carLevel;
	private String spic;
	private String bpic;
	private Date startTime;
	private int userId;
	private String buyIds;
	private int groupbuyState;// 0筹备中 1：确认开团

	/**
	 * 活动总评价1-5颗星
	 */
	private Double commentTotal;
	/**
	 * 价格给力程度1-5颗星
	 */
	private Double commentPrice;
	/**
	 * 团长服务质量1-5颗星
	 */
	private Double commentService;
	/**
	 * 4S店服务质量1-5颗星
	 */
	private Double commentShop;
	/**
	 * 参与评价总人数
	 */
	private Integer evaluaterTotal;

	public int getGroupbuyState() {
		return groupbuyState;
	}

	public void setGroupbuyState(int groupbuyState) {
		this.groupbuyState = groupbuyState;
	}

	public Integer getEvaluaterTotal() {
		return evaluaterTotal;
	}

	public void setEvaluaterTotal(Integer evaluaterTotal) {
		this.evaluaterTotal = evaluaterTotal;
	}

	public Double getCommentPrice() {
		return commentPrice;
	}

	public void setCommentPrice(Double commentPrice) {
		this.commentPrice = commentPrice;
	}

	public Double getCommentService() {
		return commentService;
	}

	public void setCommentService(Double commentService) {
		this.commentService = commentService;
	}

	public Double getCommentShop() {
		return commentShop;
	}

	public void setCommentShop(Double commentShop) {
		this.commentShop = commentShop;
	}

	public void setCommentTotal(Double commentTotal) {
		this.commentTotal = commentTotal;
	}

	public void setSaveMoney(int saveMoney) {
		this.saveMoney = saveMoney;
	}

	public Double getCommentTotal() {
		return commentTotal;
	}

	public int getCommentTotalf() {
		return (int) Math.round(commentTotal);
	}

	public void setCommentTotal(double commentTotal) {
		this.commentTotal = commentTotal;
	}

	private String brandLevel;
	private int saveMoney;

	public int getStyleId() {
		return styleId;
	}

	public void setStyleId(int styleId) {
		this.styleId = styleId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public String getBrandPinYin() {
		return brandPinYin;
	}

	public void setBrandPinYin(String brandPinYin) {
		this.brandPinYin = brandPinYin;
	}

	public String getTitle() {
		return this.title;
	}

	public String getAllTitle() {
		return this.title + "-" + this.getDateStr();
	}

	public String getDateStr() {// 用于列表页的 title 后半段
		return this.getStartTimeStr();
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getManNum() {
		return manNum;
	}

	public void setManNum(int manNum) {
		this.manNum = manNum;
	}

	public int getCarLevel() {
		return carLevel;
	}

	public void setCarLevel(int carLevel) {
		this.carLevel = carLevel;
	}

	public int getPassNum() {
		return passNum;
	}

	public void setPassNum(int passNum) {
		this.passNum = passNum;
	}

	public void setSpic(String spic) {
		this.spic = spic;
	}

	public Date getStartTime() {

		return startTime;
	}

	public String getStartTimeStr() {
		if (groupbuyState==1 && this.getStartTime() != null) {
			return DateUtil.getDate(this.getStartTime().getTime() / 1000,
					"yyyy-MM-dd");
		}
		return "正在筹备中";
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getBuyIds() {
		return buyIds;
	}

	public void setBuyIds(String buyIds) {
		this.buyIds = buyIds;
	}

	/**
	 * 档次为A品牌优惠幅度：品牌累计报名人数*rand[0.10,0.15]*rand[5000,6000]
	 * 档次为B品牌优惠幅度：品牌累计报名人数*rand[0.10,0.15]*rand[2000,3000]
	 * 档次为C品牌优惠幅度：品牌累计报名人数*rand[0.10,0.15]*rand[1000,2000]
	 * 
	 * @return
	 */
	public String getSaveMoney() {
		return getBrandSaveMoneyNew(this.brandId, this.passNum - this.manNum
				+ this.saveMoney);
	}

	public String getBrandSaveMoneyNew(int brandId, int passNum) {
		float saveMoney = 0;
		brandLevel = (brandLevel == null ? "" : brandLevel);
		if ("1".equals(brandLevel)) {
			saveMoney = 5531;
		}else if("2".equals(brandLevel)){
			saveMoney = 2531;
		}else{
			saveMoney = 1531;
		}
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		return nf.format(passNum * 0.13f * saveMoney / 10000);
	}

	public String getBuyUrl() {
		return "http://" + CommonUtil.getCityCode(this.cityId)
				+ ".tuanche.com/c" + this.styleId + "/tuan/";
	}

	public String getSpic() {
		if (StringUtils.isNotEmpty(spic) && !spic.startsWith("http")) {
			return "http://pic.tuanche.com/car" + spic + "_s.jpg";
		}
		return spic;
	}

	public String getBpic() {
		if (StringUtils.isNotEmpty(bpic) && !bpic.startsWith("http")) {
			return "http://pic.tuanche.com/car" + bpic + "_pcb.jpg";
		}
		return bpic;
	}

	public int getRealManNum() {
		return this.manNum;
	}

	public String getBrandLevel() {
		return brandLevel;
	}

	public void setBrandLevel(String brandLevel) {
		this.brandLevel = brandLevel;
	}

	public int getStyleSaveMoney() {
		return saveMoney;
	}

	public void setStyleSaveMoney(int styleSaveMoney) {
		this.saveMoney = styleSaveMoney;
	}

}
