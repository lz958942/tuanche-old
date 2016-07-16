package com.tuanche.smc.domain.zixun;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tuanche.commons.util.Resources;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.smc.common.Common;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.domain.base.CityDist;
import com.tuanche.smc.domain.base.NewsClassify;

public class ZiXun implements Serializable {
	
	private static final long serialVersionUID = 4241566136208509045L;
	public static final int CHANNEL_RESALE = 1;
	public static final int CHANNEL_RENT = 2;
	
	public static final int CHECK_STATUS_OK = 1;
	public static final int CHECK_STAUTS_UNCHECKED = 0;
	public static final int CHECK_STATUS_REJECT = 2;
	
	public static final int DEPLOY_STATUS_OK = 1;
	public static final int DEPLOY_STATUS_UNDEPLOY = 0;

	/**
	 * 数据库自增主键
	 */
	private Integer id;
	
	/**
	 * 新闻标题
	 */
	private String title;
	
	/**
	 * 关键词
	 */
	private String keyword;
	
	/**
	 * 标签词
	 */
	private String labelWord;
	

	
	/**
	 * 责任编辑
	 */
	private int editorId;
	
	/**
	 * 频道code
	 */
	private String channel;
	
	/**
	 * 城市ID
	 */
	private int cityId;
	
	/**
	 * 城市
	 */
	private String cityName;
	
	
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	
	/**
	 * 分类ID
	 */
	private int classId;
	
	/**
	 * 分类
	 */
	private String className;
	
	/**
	 * 房源搜索条件
	 */
	private String searchUrl;
	
	/**
	 * 资讯正文内容
	 */
	private String content;
	
	/**
	 * 发布状态 0：未发布；1：发布
	 */
	private int status;
	
	/**
	 * 热词
	 */
	private List<HotWord> hotWords;
	
	/**
	 * 点击次数
	 */
	private int clickCount;
	
	private String startDate;
	
	private String endDate;
	
	/**
	 * DistId
	 */
	private int distId;
	
	/**
	 * distName
	 */
	private String distName;
	
	/**
	 * 商圈ID
	 */
	private int blockId;
	
	/**
	 * 商圈名称
	 */
	private String blockName;
	
	/**
	 * 价格范围低值
	 */
	private int priceLow;
	
	/**
	 * 价格范围高值
	 */
	private int priceHigh;
	
	/**
	 * 面积范围低值
	 */
	private int areaLow;
	
	/**
	 * 面积范围高值
	 */
	private int areaHigh;
	
	/**
	 * 户型
	 */
	private String flatType;
	
	/**
	 * 房屋类型
	 */
	private String houseType;
	
	/**
	 * 搜索关键词
	 */
	private String searchKeyWord;
	
	/**
	 * 装修
	 */
	private String decoration;
	
	/**
	 * 出租方式
	 */
	private String rentType;
	
	/**
	 * 列表页：0-默认列表；1-最新房源；2-急售房源
	 */
	private int listPage;
	
	/**
	 * 展示方式：0-图文列表；1-文字列表
	 */
	private int viewType;
	
	/**
	 * 搜索记录条数
	 */
	private int recordCount;
	
	/**
	 * 辅助字段
	 */
	private int priceLowTxt;
	
	private int priceHighTxt;
	
	private int recordCountAll;
	
	private String hotWordsTxt;
	
	private int hyperlink;
	
	
	private List<SimpleZixun> relatedZixuns;
	
	private String url;
	
	private String linkContent;
	
	
	
	/****************新添加字段********************/
	/**
	 *关联品牌id 
	 */
	private String brandId;
	/**
	 * 关联车型id
	 */
	private String styleId;
	
	/**
	 * 列表图
	 */
	private String listPic;
	
	/**
	 * 分类的code
	 */
	private String classCode;
	
	/**
	 * 发布日期
	 */
	private String publishDateStr;
	
	/**
	 * 
	 */
	private Date publishDate;
	
	private Date updateDate;
	
	private Pagination page;
	private String description;
	/**
	 * 发布编辑
	 */
	private int publishEditorId;
	/**
	 * 修改编辑
	 */
	private int updateEditorId;

	public int getPublishEditorId() {
		return publishEditorId;
	}

	public void setPublishEditorId(int publishEditorId) {
		this.publishEditorId = publishEditorId;
	}

	public int getUpdateEditorId() {
		return updateEditorId;
	}

	public void setUpdateEditorId(int updateEditorId) {
		this.updateEditorId = updateEditorId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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


	

	public int getEditorId() {
		return editorId;
	}

	public void setEditorId(int editorId) {
		this.editorId = editorId;
	}


	public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	
	public String getTrueCityName(){
		if(-1 == this.cityId) {
			return "全国";
		} else {
			CityDist cityDist = Common.cityDistMap.get(this.cityId+"");
            return cityDist==null?"全国":cityDist.getLocalname();
		}
	}
	
	public void setCityName() {
		if(-1 == this.cityId) {
			this.cityName = "";
		} else {
			this.cityName = Common.cityDistMap.get(this.cityId+"").getLocalname();
		}
	}

	public String getCreateTime() {
		try{
			return formatDate("yyyy-MM-dd", createTime);
		} catch(Exception e){}
		return null;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSearchUrl() {
		return searchUrl;
	}

	public void setSearchUrl(String searchUrl) {
		this.searchUrl = searchUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<HotWord> getHotWords() {
		if(hotWords == null) {
			hotWords = new ArrayList<HotWord>();
		}
		int count = 10 - hotWords.size();
		for(int i = 0; i < count; i++) {
			hotWords.add(new HotWord());
		}
		return hotWords;
	}
	
	public List<HotWord> getPageHotWords(){
		return hotWords;
	}

	public void setHotWords(List<HotWord> hotWords) {
		this.hotWords = hotWords;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	


	public int getDistId() {
		return distId;
	}

	public void setDistId(int distId) {
		this.distId = distId;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public int getBlockId() {
		return blockId;
	}

	public void setBlockId(int blockId) {
		this.blockId = blockId;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public int getPriceLow() {
		return priceLow;
	}

	public void setPriceLow(int priceLow) {
		this.priceLow = priceLow;
	}

	public int getPriceHigh() {
		return priceHigh;
	}

	public void setPriceHigh(int priceHigh) {
		this.priceHigh = priceHigh;
	}

	public int getAreaLow() {
		return areaLow;
	}

	public void setAreaLow(int areaLow) {
		this.areaLow = areaLow;
	}

	public int getAreaHigh() {
		return areaHigh;
	}

	public void setAreaHigh(int areaHigh) {
		this.areaHigh = areaHigh;
	}

	public String getFlatType() {
		return flatType;
	}

	public void setFlatType(String flatType) {
		this.flatType = flatType;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getSearchKeyWord() {
		return searchKeyWord;
	}

	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}

	public String getDecoration() {
		return decoration;
	}

	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}

	public String getRentType() {
		return rentType;
	}

	public void setRentType(String rentType) {
		this.rentType = rentType;
	}

	public int getListPage() {
		return listPage;
	}

	public void setListPage(int listPage) {
		this.listPage = listPage;
	}

	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getPriceLowTxt() {
		return priceLowTxt;
	}

	public void setPriceLowTxt(int priceLowTxt) {
		this.priceLowTxt = priceLowTxt;
	}

	public int getPriceHighTxt() {
		return priceHighTxt;
	}

	public void setPriceHighTxt(int priceHighTxt) {
		this.priceHighTxt = priceHighTxt;
	}

	public int getRecordCountAll() {
		return recordCountAll;
	}

	public void setRecordCountAll(int recordCountAll) {
		this.recordCountAll = recordCountAll;
	}

	public String getHotWordsTxt() {
		return hotWordsTxt;
	}

	public void setHotWordsTxt(String hotWordsTxt) {
		this.hotWordsTxt = hotWordsTxt;
	}

	public int getHyperlink() {
		return hyperlink;
	}

	public void setHyperlink(int hyperlink) {
		this.hyperlink = hyperlink;
	}


	public String getLabelWord() {
		return labelWord;
	}

	public void setLabelWord(String labelWord) {
		this.labelWord = labelWord;
	}

	public List<SimpleZixun> getRelatedZixuns() {
		return relatedZixuns;
	}

	public void setRelatedZixuns(List<SimpleZixun> relatedZixuns) {
		this.relatedZixuns = relatedZixuns;
	}
	


	public String getLinkContent() {
		return linkContent;
	}
	
    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
    	if(brandId!=null){
    		  this.brandId = brandId.replace(",", "");
    	}
      
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
    	if(styleId==null){
    		return;
    	}
    	if(styleId.contains("-")){
    		String[] strs=styleId.split("-");
    		  this.styleId = strs[1].replace(",", "");
    		  this.brandId=strs[0];
    	}else{
    		this.styleId=styleId;
    	}
     
    }

    public String getListPic() {
        return listPic;
    }

    public void setListPic(String listPic) {
        this.listPic = listPic;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getPublishDateStr() {
        if(this.publishDate!=null){
            String pattern = "yyyy-MM-dd H:mm:ss";
            Date date = publishDate;
            String format = formatDate(pattern, date);
            return format;
        }
        return publishDateStr;
    }

    private String formatDate(String pattern, Date date) {
        if(date == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(date);
        return format;
    }

    public void setPublishDateStr(String publishDateStr) {
        if(StringUtils.isNotEmpty(publishDateStr)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
            Date parse = null;
            try {
                parse = simpleDateFormat.parse(publishDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.publishDate = parse;
        }
        
        this.publishDateStr = publishDateStr;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Pagination getPage() {
        if(page==null){
        	page=new Pagination();
        }
        return page;
    }

    public void setPage(Pagination page) {
        this.page = page;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        if(this.url!=null){
            return this.url;
        }
        String prefix = "http://";
        String city = cityId<=0?"":Common.cityDistMap.get(cityId+"").getDirName()+".";
        String zixunClass = "/zx-";
        NewsClassify newsClassify = Common.newsClassifyMap.get(this.classId+"");
        if(newsClassify==null){
            return null;
        }
        zixunClass+=concatUrl("", newsClassify);
        String fullUrl = prefix+city+Resources.getString("tuanche.domain")+zixunClass+id+".html";
        setUrl(fullUrl);
        return fullUrl;
    }

    private String  concatUrl(String url,NewsClassify newsClassify) {
        //获取当前层级的url
    	if(newsClassify!=null){
    		 String levelUrl = newsClassify.getUri();
    		 String currentUrl = levelUrl+"/"+url;
    		 if(newsClassify.getLevel()==1){
    	            return currentUrl;
	        }else {
	            NewsClassify parentNewsClassify = Common.newsClassifyMap.get(newsClassify.getpId()+"");
	            return concatUrl(currentUrl, parentNewsClassify);
	        }
    	}
        return ""; 
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
    
    public String  getUpdateDateStr() {
        return formatDate("yyyy-MM-dd HH:mm:ss", updateDate);
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    public String getListPicFull(){
        if(StringUtils.isEmpty(this.listPic)){
            return "/images/selectPiuc.jpg";
        }
        if(this.listPic.startsWith("http")){
        	return this.listPic;
        }
        
        String s= this.listPic.replace(Resources.getString("temfilePath"), "qwer");
        if(!s.equals(this.listPic)){
        	 return this.listPic;	
        }
     
        return Resources.getString("fileUrl")+this.listPic+"_s.jpg"; 
    }

	@Override
	public String toString() {
		return "ZiXun [description=" + description + ", id=" + id + ", title="
				+ title + ", keyword=" + keyword + ", labelWord=" + labelWord
				+ ", editorId=" + editorId + ", channel=" + channel
				+ ", cityId=" + cityId + ", cityName=" + cityName
				+ ", createTime=" + createTime + ", classId=" + classId
				+ ", className=" + className + ", searchUrl=" + searchUrl
				+ ", content=" + content + ", status=" + status + ", hotWords="
				+ hotWords + ", clickCount=" + clickCount + ", startDate="
				+ startDate + ", endDate=" + endDate + ", distId=" + distId
				+ ", distName=" + distName + ", blockId=" + blockId
				+ ", blockName=" + blockName + ", priceLow=" + priceLow
				+ ", priceHigh=" + priceHigh + ", areaLow=" + areaLow
				+ ", areaHigh=" + areaHigh + ", flatType=" + flatType
				+ ", houseType=" + houseType + ", searchKeyWord="
				+ searchKeyWord + ", decoration=" + decoration + ", rentType="
				+ rentType + ", listPage=" + listPage + ", viewType="
				+ viewType + ", recordCount=" + recordCount + ", priceLowTxt="
				+ priceLowTxt + ", priceHighTxt=" + priceHighTxt
				+ ", recordCountAll=" + recordCountAll + ", hotWordsTxt="
				+ hotWordsTxt + ", hyperlink=" + hyperlink + ", relatedZixuns="
				+ relatedZixuns + ", url=" + url + ", linkContent="
				+ linkContent + ", brandId=" + brandId + ", styleId=" + styleId
				+ ", listPic=" + listPic + ", classCode=" + classCode
				+ ", publishDateStr=" + publishDateStr + ", publishDate="
				+ publishDate + ", updateDate=" + updateDate + ", page=" + page
				+ "]";
	}
	
}
