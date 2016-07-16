package com.tuanche.cms.bean;

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
	//是否为测试
	private int isTest;
	
	private String pyPre;
	
	
	public int getIsTest() {
		return isTest;
	}

	public void setIsTest(int isTest) {
		this.isTest = isTest;
	}

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

	
}
