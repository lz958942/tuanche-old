package com.tuanche.smc.domain.specialSubject;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tuanche.commons.util.Resources;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.smc.common.Common;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.domain.base.CityDist;

public class SpecialSubject implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	//删除
	public static final int DELETE_Y = -1;
	//非删除
	public static final int DELETE_N = 1;
	//上线
	public static final int UPLINE = 1;
	//下线
	public static final int DOWNLINE = 2;
	private Integer id;//专题id
	private String spName;//专题名字
	private String content;//60秒专题内容
	private String spAbstract;//专题导语
	private String zixunIds;//关联资讯的id
	private Integer templateId;//关联模板的Id
	private String spStatus;//专题状态  1：未删除，-1：删除
	private Integer spOnline;//专题是否上线   1：是，2：否
	private String keywords;//专题绑定的关键词
	private String spDesc;//专题描述
	private Integer cityId;//城市id
	private String cityTrueName;//获得城市真实名字
	private String brandId;//关联品牌的id
	private String carStyleId;//关联车型的id
	private Integer operateUserId;//操作用户的id
	private String operateUserName;//操作用户的名字
    private String pubDate;//创建时间
	private String onlineDate;//上线时间
	private String beginTime;//开始时间
	private String endTime;//结束时间
	private String ztType;//专题分类
	private String spUrl;//专题访问的url
	private String tpName;//模板名称
	
	private String abstractTitles;//老模板小标题，用“|”分隔
	
    private Pagination page;//分页信息
	private int recordCount;//搜索记录条数
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getContent() {
		return content;
	}
	public void setContext(String content) {
		this.content = content;
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
	public String getSpStatus() {
		return spStatus;
	}
	public void setSpStatus(String spStatus) {
		this.spStatus = spStatus;
	}

	public Integer getSpOnline() {
		return spOnline;
	}
	public void setSpOnline(Integer spOnline) {
		this.spOnline = spOnline;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getSpDesc() {
		return spDesc;
	}
	public void setSpDesc(String spDesc) {
		this.spDesc = spDesc;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId.replace(",", "");
	}
	public String getCarStyleId() {
		return carStyleId;
	}
	public void setCarStyleId(String carStyleId) {
		if(carStyleId==null){
    		return;
    	}
    	if(carStyleId.contains("-")){
    		String[] strs=carStyleId.split("-");
    		  this.carStyleId = strs[1].replace(",", "");
    		//  this.brandId=strs[0];
    	}else{
    		this.carStyleId=carStyleId;
    	}
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
	public String getPubDate() {
		return pubDate;
	}
	
	public String getPubTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date=null;
		try {
			date=df.parse(this.pubDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return df.format(date);
	}
	public void setPubDate(String pubDate) {
		
		this.pubDate = pubDate;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public String getOnlineDate() {
		return onlineDate;
	}
	public String getOnlineTime(){
		if(this.onlineDate==null){
			return null;
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date=null;
		try {
			date=df.parse(this.onlineDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return df.format(date);
	}
	public void setOnlineDate(String onlineDate) {
		this.onlineDate = onlineDate;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public String getCityTrueName() {
			if(-1==this.cityId){
				return "全国";
			}else{
				CityDist cityDist=Common.cityDistMap.get(this.cityId+"");
				return cityDist==null?"":cityDist.getLocalname();
			}
	}
	public void setCityTrueName() {
		if(-1 == this.cityId) {
			this.cityTrueName = "";
		} else {
			this.cityTrueName = Common.cityDistMap.get(this.cityId+"").getLocalname();
		}
	}
	/*public String getCityTrueName() {
		return cityTrueName;
	}
	public void setCityTrueName(String cityTrueName) {
		this.cityTrueName = cityTrueName;
	}*/
	public String getZtType() {
		return ztType;
	}
	public void setZtType(String ztType) {
		this.ztType = ztType;
	}
	public String getSpUrl() {
		if("1".equals(this.ztType)){
			return "http://"+Resources.getString("tuanche.domain")+"/zt-mc/"+this.id+"/";
		}else if("2".equals(this.ztType)){
			return "http://"+Resources.getString("tuanche.domain")+"/zt-htxc/"+this.id+"/";
		}else{
			return null;
		}
	}
	
	
	public String getEncodeSpUrl(){
		if(this.getSpUrl()!=null){
			return StringUtils.encodeUTF8(this.getSpUrl());
		}
		return null;
	}
	public void setSpUrl(String spUrl) {
		
	    this.spUrl=spUrl;
		
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTpName() {
		return tpName;
	}
	public void setTpName(String tpName) {
		this.tpName = tpName;
	}
	public String getAbstractTitles() {
		return abstractTitles;
	}
	public void setAbstractTitles(String abstractTitles) {
		this.abstractTitles = abstractTitles;
	}	
}
