package com.tuanche.smc.domain.base;

import com.tuanche.smc.common.page.impl.Pagination;

public class NewsClassify {
    private Integer id;
    private Integer pId;
    private Integer level;
    private String name;
    private String code;
    private String url;
    private String uri;
    private int status;
    private int cityAttr;
    private Pagination page;
    private boolean open;
    
    private String title;
    private String keyword;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getpId() {
        return pId;
    }
    public void setpId(Integer pId) {
        this.pId = pId;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getCityAttr() {
        return cityAttr;
    }
    public void setCityAttr(int cityAttr) {
        this.cityAttr = cityAttr;
    }
    public Pagination getPage() {
        return page;
    }
    public void setPage(Pagination page) {
        this.page = page;
    }
    public boolean isOpen() {
        return open;
    }
    public void setOpen(boolean open) {
        this.open = open;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    
    
}
