package com.tuanche.console.bean;

import com.tuanche.console.util.CommonData;

public class SysRoleFunc {
	private Integer id;
	private Integer roleId;
	private Integer funcId;
	private Integer isDel;
	private String delStatus;
	public SysRoleFunc() {
	}

	public SysRoleFunc(Integer roleId, Integer funcId, Integer isDel) {
		super();
		this.roleId = roleId;
		this.funcId = funcId;
		this.isDel = isDel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getFuncId() {
		return funcId;
	}

	public void setFuncId(Integer funcId) {
		this.funcId = funcId;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getDelStatus() {
		return this.isDel == null ? null : CommonData.booleanMap.get(this.isDel);
	}
	
}
