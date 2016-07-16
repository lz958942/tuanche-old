package com.tuanche.bean.che;
/**团购管理搜索条件实体**/
public class SearchBean {
	private Integer tmcCityId, tmcBrandId, tmcCarstyleId, tmcStateName,tmpCpage;

	public Integer getTmcCityId() {
		return tmcCityId;
	}


	public Integer getTmpCpage() {
		return tmpCpage;
	}


	public void setTmpCpage(Integer tmpCpage) {
		this.tmpCpage = tmpCpage;
	}


	public void setTmcCityId(Integer tmcCityId) {
		this.tmcCityId = tmcCityId;
	}

	public Integer getTmcBrandId() {
		return tmcBrandId;
	}

	public void setTmcBrandId(Integer tmcBrandId) {
		this.tmcBrandId = tmcBrandId;
	}

	public Integer getTmcCarstyleId() {
		return tmcCarstyleId;
	}

	public void setTmcCarstyleId(Integer tmcCarstyleId) {
		this.tmcCarstyleId = tmcCarstyleId;
	}

	public Integer getTmcStateName() {
		return tmcStateName;
	}

	public void setTmcStateName(Integer tmcStateName) {
		this.tmcStateName = tmcStateName;
	}

	@Override
	public String toString() {
		return "SearchBean [tmcCityId=" + tmcCityId + ", tmcBrandId="
				+ tmcBrandId + ", tmcCarstyleId=" + tmcCarstyleId
				+ ", tmcStateName=" + tmcStateName + "]";
	}

	
}
