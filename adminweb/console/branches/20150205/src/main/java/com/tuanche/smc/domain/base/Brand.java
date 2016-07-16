package com.tuanche.smc.domain.base;

public class Brand {
    private int id;
    private String name;
    private String logo;
    private int series;
    private String pinyin;
    private String level;
    private int pId;
    private int vender;
    /**
     * 后台图片推送
     * */
    private int newSeriesId;
    
    public int getNewSeriesId() {
		return newSeriesId;
	}
	public void setNewSeriesId(int newSeriesId) {
		this.newSeriesId = newSeriesId;
	}
	/**
     * 后台图片推送
     * */
    
   
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public int getVender() {
		return vender;
	}
	public void setVender(int vender) {
		this.vender = vender;
	}
	public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLogo() {
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
