package com.tuanche.smc.domain.specialSubject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tuanche.smc.common.page.impl.Pagination;

public class Template {
	
	private Integer id;//模板id
	
	private String tpName;//模板名称

	private String tpDesc;//模板描述
	
	private String tpContent;//模板内容
	
	private Integer createUserId;//创建创建用户id
	
	private String createUserName;//创建创建用户名
	
	private String createTime;//创建时间
	
	private String updateTime;//创建时间
	
	private Integer deleteFlay;//删除状态
	
	private static int DELETE_Y=2;//删除状态
	
	private static int DELETE_N=1;//未删除状态
	
	private Pagination page;//分页信息
	
	private int recordCount;//搜索记录条数

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTpName() {
		return tpName;
	}

	public void setTpName(String tpName) {
		this.tpName = tpName;
	}

	public String getTpDesc() {
		return tpDesc;
	}

	public void setTpDesc(String tpDesc) {
		this.tpDesc = tpDesc;
	}

	public String getTpContent() {
		return tpContent;
	}

	public void setTpContent(String tpContent) {
		this.tpContent = tpContent;
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
		this.createUserName = createUserName;
	}

	public String getCreateTime() {
		if(this.createTime!=null&&!"".equals(this.createTime)){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
			try {
				Date date=df.parse(this.createTime);
				this.createTime=df.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return createTime;
	}
	
	public String getCreateDate(){
		if(this.createTime!=null&&!"".equals(this.createTime)){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Date date=null;
			try {
				date=df.parse(this.createTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return df.format(date);
		}else{
			return null;
		}
		
	}
	
	
	public String getUpdateTime() {
		if(this.updateTime!=null&&!"".equals(this.updateTime)){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Date date=null;
			try {
				date=df.parse(this.updateTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return df.format(date);
		}else{
			return null;
		}
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getDeleteFlay() {
		return deleteFlay;
	}

	public void setDeleteFlay(Integer deleteFlay) {
		this.deleteFlay = deleteFlay;
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
	
}
