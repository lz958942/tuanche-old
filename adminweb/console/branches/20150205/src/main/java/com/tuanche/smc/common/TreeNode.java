package com.tuanche.smc.common;

public class TreeNode {
    
    private String id;
    private Integer pId;
    private String name;
    private boolean open;
    private String url;
    private Integer level;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setId(Integer id) {
        this.id = id+"";
    }
    public Integer getpId() {
        return pId;
    }
    public void setpId(Integer pId) {
        this.pId = pId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isOpen() {
        return open;
    }
    public void setOpen(boolean open) {
        this.open = open;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return super.toString();
    }
}
