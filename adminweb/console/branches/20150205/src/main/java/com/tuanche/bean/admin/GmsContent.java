package com.tuanche.bean.admin;

import java.io.Serializable;

import com.tuanche.commons.util.StringUtils;

public class GmsContent  implements Serializable{
	
	//删除
	public static final Integer IS_DELETE = 2;
   //内容类型，1：车展新闻（资讯）,2：车展回顾,3:4s店,4：往期车展5：县市巡展
	public static final Integer TYPE_NEWS = 1;
	public static final Integer TYPE_HG = 2;
	public static final Integer TYPE_DIAN = 3;
	public static final Integer TYPE_OLDNEWS = 4;
	public static final Integer TYPE_XW_XSXZ= 5;
	private static final long serialVersionUID = 1467829598607469479L;

	private Integer id;

    private Integer gcmId;

    private Integer type;

    private String title;

    private String image;

    private String url;

    private Integer sort;

    private Integer dianId;
    
    private String dianName;

    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGcmId() {
        return gcmId;
    }

    public void setGcmId(Integer gcmId) {
        this.gcmId = gcmId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImage() {
    	if(StringUtils.isEmpty(image)){
    		return null;
    	}
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getDianId() {
        return dianId;
    }

    public void setDianId(Integer dianId) {
        this.dianId = dianId;
    }
    

    public String getDianName() {
		return dianName;
	}

	public void setDianName(String dianName) {
		this.dianName = dianName;
	}

	public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}