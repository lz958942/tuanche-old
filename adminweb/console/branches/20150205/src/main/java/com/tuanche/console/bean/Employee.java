package com.tuanche.console.bean;

import java.util.Date;

import com.tuanche.console.util.CommonData;
public class Employee {
    private Integer id,addUser,lastUser,isdel;
    private String empLogin;
    private String empNo;

    private String empName;

    private String empEmail;

    private String empPwd;

    private String empGender;

    private String empPhone;

    private String empQq;

    private String extNum;

    private String empWx;

    private String roleIds;
    private String roleId;

    private String cityCode;

    private String deptCode;

    private String positionCode;

    private String positionLevel;

    private String empStatus;

    private Date addDate;

    private Date lastDate;
    
    private Date findPwdTime;
    private String empStatusStr;
    private String recType ;  //推送type 根据type查频道
    
    public String getRecType() {
		return recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}

	public String getEmpStatusStr() {
			return this.empStatus== null ? "" : CommonData.empStatusMap.get(this.empStatus);
	}

	public Integer getId() {
        return id;
    }
	public String getEmpLogin() {
	        return empLogin;
	}

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo == null ? null : empNo.trim();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpLogin(String empLogin) {
        this.empLogin = empLogin == null ? null : empLogin.trim();
    }
    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail == null ? null : empEmail.trim();
    }

    public String getEmpPwd() {
        return empPwd;
    }

    public void setEmpPwd(String empPwd) {
        this.empPwd = empPwd == null ? null : empPwd.trim();
    }

    public String getEmpGender() {
        return empGender;
    }

    public void setEmpGender(String empGender) {
        this.empGender = empGender == null ? null : empGender.trim();
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone == null ? null : empPhone.trim();
    }

    public String getEmpQq() {
        return empQq;
    }

    public void setEmpQq(String empQq) {
        this.empQq = empQq == null ? null : empQq.trim();
    }

    public String getExtNum() {
        return extNum;
    }

    public void setExtNum(String extNum) {
        this.extNum = extNum == null ? null : extNum.trim();
    }

    public String getEmpWx() {
        return empWx;
    }

    public void setEmpWx(String empWx) {
        this.empWx = empWx == null ? null : empWx.trim();
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds == null ? null : roleIds.trim();
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode == null ? null : positionCode.trim();
    }

    public String getPositionLevel() {
        return positionLevel;
    }

    public void setPositionLevel(String positionLevel) {
        this.positionLevel = positionLevel == null ? null : positionLevel.trim();
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus == null ? null : empStatus.trim();
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Integer getAddUser() {
        return addUser;
    }

    public void setAddUser(Integer addUser) {
        this.addUser = addUser;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Integer getLastUser() {
        return lastUser;
    }

    public void setLastUser(Integer lastUser) {
        this.lastUser = lastUser;
    }

	public Date getFindPwdTime() {
		return findPwdTime;
	}

	public void setFindPwdTime(Date findPwdTime) {
		this.findPwdTime = findPwdTime;
	}

	public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
    
}