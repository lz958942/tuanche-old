package com.tuanche.bean.che;

import java.io.Serializable;

import com.tuanche.smc.common.page.impl.Pagination;

public class EvaluatePic implements Serializable{
	 private String pic;
	 private Integer state;
	 private Integer id;
	 private String aprrovalTime;
	 private Integer aprrovalUser;
	 private Pagination page;
	public String getAprrovalTime() {
		return aprrovalTime;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	public void setAprrovalTime(String aprrovalTime) {
		this.aprrovalTime = aprrovalTime;
	}
	public Integer getAprrovalUser() {
		return aprrovalUser;
	}
	public void setAprrovalUser(Integer aprrovalUser) {
		this.aprrovalUser = aprrovalUser;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "EvaluatePic [pic=" + pic + ", state=" + state + ", id=" + id
				+ ", aprrovalTime=" + aprrovalTime + ", aprrovalUser="
				+ aprrovalUser + "]";
	}
	
	
	 
}
