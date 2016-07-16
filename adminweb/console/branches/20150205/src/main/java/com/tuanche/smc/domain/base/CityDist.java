package com.tuanche.smc.domain.base;

import java.io.Serializable;

/**
 * @author wanghongmeng
 * 
 */
public class CityDist implements Serializable {
    
	private static final long serialVersionUID = 6291975272185441173L;
	//id
	private int localid;
	//name
	private String localname;
	//父id
	private int pid;
	//拼音
	private String dirName;
	
	private String pyPre;
	private String cityCode;
	/**
	 * @return 地标id
	 */
	public int getLocalid() {
		return localid;
	}

	/**
	 * @param localid
	 *            地标id
	 */
	public void setLocalid(int localid) {
		this.localid = localid;
	}

	/**
	 * @return 地标中文名称
	 */
	public String getLocalname() {
		return localname;
	}

	/**
	 * @param localname
	 *            地标中文名称
	 */
	public void setLocalname(String localname) {
		this.localname = localname;
	}

	/**
	 * @return 地标的父亲id
	 */
	public int getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            地标的父亲id
	 */
	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

    public String getPyPre() {
        return pyPre;
    }

    public void setPyPre(String pyPre) {
        this.pyPre = pyPre;
    }

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	
}
