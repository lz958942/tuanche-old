package com.tuanche.bean.admin;

/**
 * @author Administrator
 * 装饰内容
 */
public class DecorateContent {
	
	private Integer id;//主键
	private Integer baseId;//基础id
	private Integer tempId;//板块Id
	private String picUrl;//图片路径
	private String dctitle;//标题
	private String dcSort;//排序
	private String content;//内容
	private String isdel;//状态
	private String style;//形式
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBaseId() {
		return baseId;
	}
	public void setBaseId(Integer baseId) {
		this.baseId = baseId;
	}
	public Integer getTempId() {
		return tempId;
	}
	public void setTempId(Integer tempId) {
		this.tempId = tempId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getDctitle() {
		return dctitle;
	}
	public void setDctitle(String dctitle) {
		this.dctitle = dctitle;
	}
	public String getDcSort() {
		return dcSort;
	}
	public void setDcSort(String dcSort) {
		this.dcSort = dcSort;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	
}
