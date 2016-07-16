package com.tuanche.cms.bean;

import com.tuanche.cms.util.GlobalConstants;
import com.tuanche.commons.util.StringUtils;

public class Brand {
    private int id;
    private String name;
    private String logo;
    private int series;
    private String pinyin;
    private String level;
    private int pId;
    private int newSeriesId;

    
	public int getNewSeriesId() {
		return newSeriesId;
	}
	public void setNewSeriesId(int newSeriesId) {
		this.newSeriesId = newSeriesId;
	}
	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLogo() {
    	if(StringUtils.isNotEmpty(logo)&&!logo.startsWith("http")){
    		logo=GlobalConstants.HOST_PIC+"/car"+logo+"_s.jpg";
		}
		return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    public int getSeries() {
        if(this.series==5){//日系  韩系  合成 日韩系
            return 2;
        }
        if(this.series==6){//法系  合成  欧系
            return 2;
        }
        return series;
    }
    public void setSeries(int series) {
        this.series = series;
    }
    public String getPinyin() {
        return pinyin;
    }
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }
    public int getpId() {
        return pId;
    }
    public void setpId(int pId) {
        this.pId = pId;
    }
    
}
