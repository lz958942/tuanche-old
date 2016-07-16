package com.tuanche.smc.domain.specialSubject;

import java.util.Map;

import com.tuanche.commons.util.Resources;

public class SpecialContent {
	
	private Integer id;//id
	private Integer spId;//关联专题的id
	private String stType;//1 ：头图；2：套图；3：事件回顾
	private String[] stTitle;//专题下的小标题(页面传递)
	private String[] stContent;//标题下的内容(页面传递)
	private String[] listPic; //图片链接
	private String stTitles;//专题下的小标题
	private String stContents;//标题下的内容
	private String listPics;//图片链接
	private String listPicsInsert;//插入图片链接
	
	private String titleUrl;//标题链接
	private String[] titleUrls;//标题链接
	private String picTitle;//套图标题
	private String onePic;//头图
	private String onePicInsert;//插入头图
	private int[] titlesId;
	private int[] picListId;
	private Integer onePicId;
	private String status;//删除状态
	
	private String[] sorts;//排序
	private String sort;
	private String banner;//头图标语
	
	private Map<String,Object> oldMap;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSpId() {
		return spId;
	}
	public void setSpId(Integer spId) {
		this.spId = spId;
	}
	public String[] getStTitle() {
		return stTitle;
	}
	public String getStTitles() {
		return stTitles;
	}
	public void setStTitles(String stTitles) {
		this.stTitles = stTitles;
	}
	public String getStContents() {
		return stContents;
	}
	public void setStContents(String stContents) {
		this.stContents = stContents;
	}
	public void setStTitle(String[] stTitle) {
		this.stTitle = stTitle;
	}
	public String[] getStContent() {
		return stContent;
	}
	public void setStContent(String[] stContent) {
		this.stContent = stContent;
	}
	public String getStType() {
		return stType;
	}
	public void setStType(String stType) {
		this.stType = stType;
	}
	public void setTitleUrl(String titleUrl) {
		this.titleUrl = titleUrl;
	}
	public String[] getTitleUrls() {
		return titleUrls;
	}
	public void setTitleUrls(String[] titleUrls) {
		this.titleUrls = titleUrls;
	}
	public String getTitleUrl() {
		return titleUrl;
	}
	public String[] getListPic() {
		return listPic;
	}
	public void setListPic(String[] listPic) {
		this.listPic = listPic;
	}
	public String getListPics() {
		if(listPics!=null&&!"".equals(listPics)&&!"null".equals(listPics)){
			if(listPics.contains("zhuanti")){
				return Resources.getString("zt.picUrl")+listPics;
			}else{
				return listPics;
			}
		}else{
			return "null";
		}
		
	}
	public void setListPics(String listPics) {
		if(!listPics.contains("zhuanti")){
			if(listPics.contains("http://")){
				this.listPics = listPics;
			}else{
				this.listPics="null";
			}
		}else{
			this.listPics = listPics;
		}
		
	}
	public String getPicTitle() {
		return picTitle;
	}
	public void setPicTitle(String picTitle) {
		this.picTitle = picTitle;
	}
	public String getOnePic() {
		if(onePic!=null&&!"".equals(onePic)&&!"null".equals(onePic)){
			if(onePic.contains("http")){
				return onePic;
			}else{
				return Resources.getString("zt.picUrl")+onePic;
			}
		}else{
			return "null";
		}
	}
	public void setOnePic(String onePic) {
		if(!onePic.contains("zhuanti")){
			this.onePic="null";
		}else{
			this.onePic = onePic;
		}
		
	}
	public int[] getTitlesId() {
		return titlesId;
	}
	public void setTitlesId(int[] titlesId) {
		this.titlesId = titlesId;
	}
	public int[] getPicListId() {
		return picListId;
	}
	public void setPicListId(int[] picListId) {
		this.picListId = picListId;
	}
	public Integer getOnePicId() {
		return onePicId;
	}
	public void setOnePicId(Integer onePicId) {
		this.onePicId = onePicId;
	}
	public String getListPicsInsert() {
		return listPicsInsert;
	}
	public void setListPicsInsert(String listPicsInsert) {
		this.listPicsInsert = listPicsInsert;
	}
	public String getOnePicInsert() {
		return onePicInsert;
	}
	public void setOnePicInsert(String onePicInsert) {
		this.onePicInsert = onePicInsert;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map<String, Object> getOldMap() {
		return oldMap;
	}
	public void setOldMap(Map<String, Object> oldMap) {
		this.oldMap = oldMap;
	}
	public String[] getSorts() {
		return sorts;
	}
	public void setSorts(String[] sorts) {
		this.sorts = sorts;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	
}
