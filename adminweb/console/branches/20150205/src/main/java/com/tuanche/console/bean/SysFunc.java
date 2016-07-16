package com.tuanche.console.bean;

import com.tuanche.console.util.CommonData;

public class SysFunc {
	private Integer id;

	private Integer parentId;

	private String funcName;

	private String funcDesc;

	private String funcUrl;

	private Integer isMenu;

	private Integer funcSort;

	private String bizCode;

	private Integer funcLevel;

	private Integer funcStatus;
	private String funcStatusName;
	private String isMenuStr;

	public String getFuncStatusName() {
		return this.funcStatus == null ? "" : CommonData.dataStatusMap.get(this.funcStatus);
	}

	public String getIsMenuStr() {
		return this.isMenu == null ? "" : CommonData.booleanMap.get(this.isMenu);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName == null ? null : funcName.trim();
	}

	public String getFuncDesc() {
		return funcDesc;
	}

	public void setFuncDesc(String funcDesc) {
		this.funcDesc = funcDesc == null ? null : funcDesc.trim();
	}

	public String getFuncUrl() {
		return funcUrl;
	}

	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl == null ? null : funcUrl.trim();
	}

	public Integer getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}

	public Integer getFuncSort() {
		return funcSort;
	}

	public void setFuncSort(Integer funcSort) {
		this.funcSort = funcSort;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode == null ? null : bizCode.trim();
	}

	public Integer getFuncLevel() {
		return funcLevel;
	}

	public void setFuncLevel(Integer funcLevel) {
		this.funcLevel = funcLevel;
	}

	public Integer getFuncStatus() {
		return funcStatus;
	}

	public void setFuncStatus(Integer funcStatus) {
		this.funcStatus = funcStatus;
	}
}
