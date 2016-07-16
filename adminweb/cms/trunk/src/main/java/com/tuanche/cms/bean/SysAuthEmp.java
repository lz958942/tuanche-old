package com.tuanche.cms.bean;

import java.util.Date;

import com.tuanche.cms.util.GlobalConstants;

public class SysAuthEmp {
	 private Integer id;

	    private Integer empId;

	    private String bizCode;

	    private String districtIds;//城市id

	    private String brandIds;//品牌id
	    private String downloadBrandIds;

	    private Integer authStatus;

	    private Date addDate;

	    private Integer addUser;

	    private Integer lastUser;

	    private Date lastDate;
		private String authStatusName;
		private String districtNames;
		private String brandNames;
		private String empName;

	    private String brandIdDesc;
	   
		public String getEmpName() {
			return this.empId==null?"":GlobalConstants.employeeMap.get(this.empId);
		}


		  public String getBrandIdDesc() {
		        return brandIdDesc;
		    }

		    public void setBrandIdDesc(String brandIdDesc) {
		        this.brandIdDesc = brandIdDesc == null ? null : brandIdDesc.trim();
		    }
		public void setBrandNames(String brandNames) {
			this.brandNames = brandNames;
		}

		public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public Integer getEmpId() {
	        return empId;
	    }

	    public void setEmpId(Integer empId) {
	        this.empId = empId;
	    }

	    public String getBizCode() {
	        return bizCode;
	    }

	    public void setBizCode(String bizCode) {
	        this.bizCode = bizCode == null ? null : bizCode.trim();
	    }

	    public String getDistrictIds() {
	        return districtIds;
	    }

	    public void setDistrictIds(String districtIds) {
	        this.districtIds = districtIds == null ? null : districtIds.trim();
	    }

	    public String getBrandIds() {
	        return brandIds;
	    }

	    public void setBrandIds(String brandIds) {
	        this.brandIds = brandIds == null ? null : brandIds.trim();
	    }

	    public Integer getAuthStatus() {
	        return authStatus;
	    }

	    public void setAuthStatus(Integer authStatus) {
	        this.authStatus = authStatus ;
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

	    public Integer getLastUser() {
	        return lastUser;
	    }

	    public void setLastUser(Integer lastUser) {
	        this.lastUser = lastUser;
	    }

	    public Date getLastDate() {
	        return lastDate;
	    }

	    public void setLastDate(Date lastDate) {
	        this.lastDate = lastDate;
	    }
	    
	    
		public String getDownloadBrandIds() {
			return downloadBrandIds;
		}


		public void setDownloadBrandIds(String downloadBrandIds) {
			this.downloadBrandIds = downloadBrandIds;
		}


		public String getAuthStatusName() {
			return this.authStatus == null ? "" : GlobalConstants.dataStatusMap.get(this.authStatus);
		}
	    
}
