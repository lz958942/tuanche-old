package com.tuanche.cms.bean;

import com.tuanche.cms.util.GlobalConstants;

public class SysRole {
	private Integer id;

	private String roleName;

	private Integer roleStatus;
	private String roleStatusName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public Integer getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Integer roleStatus) {
		this.roleStatus = roleStatus;
	}

	public String getRoleStatusName() {
		return this.roleStatus == null ? null : GlobalConstants.dataStatusMap.get(this.roleStatus);
	}

	
}
