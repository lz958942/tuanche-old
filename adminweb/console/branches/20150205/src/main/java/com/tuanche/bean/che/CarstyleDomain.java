package com.tuanche.bean.che;

import java.io.Serializable;

import com.tuanche.commons.util.StringUtils;
import com.tuanche.smc.common.page.impl.Pagination;

public class CarstyleDomain implements Serializable{
    private Integer id;
    private Pagination page;
    //品牌字段，不在数据库中 
    private int bbip;
    private String carname;
    private String oldImage;
    private String style;
    private String enname;
    private Integer pid;
   /* private BrandDomain ppid;*/
    private Integer ppid;
    private String factoryPrice;
    private String marketPrice;
    private String level;
    private String speedBox;
    private String detailUrl;
    private String texts;
    private int sort;
    private int bos;
    private Integer addtime;
    private String initial;
    private Integer type;
    private int show;
    private String colors;
    private String colorNo;
    private  String bpic; //全图
    private  String spic; //略缩图
    private String spicsrc;
    private  Integer publicMark; //标识 0公用，1新车，2二手车
    private Integer saveMoney;
    public String getOldImage() {
		return oldImage;
	}

	public void setOldImage(String oldImage) {
		this.oldImage = oldImage;
	}

	public String getCarname() {
		return carname;
	}

	public void setCarname(String carname) {
		this.carname = carname;
	}

	public int getBbip() {
		return bbip;
	}

	public void setBbip(int bbip) {
		this.bbip = bbip;
	}

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}
    public Integer getPublicMark() {
		return publicMark;
	}
	public void setPublicMark(Integer publicMark) {
		this.publicMark = publicMark;
	}
    
    public String getSpicsrc() {
		if(this.spic!=null&&this.spic.length()>0&&this.spic.startsWith("http")){
			return this.spic;
		}
		if(this.spic!=null&&this.spic.length()>0&&this.spic.startsWith("/pic")){
			return this.spic;
		}
		if(!StringUtils.isNotEmpty(this.spic)){
			return "";
		}
    	return "http://pic.tuanche.com/car/"+this.spic+"_s.jpg";
	}

	public void setSpicsrc(String spicsrc) {
		this.spicsrc = spicsrc;
	}

	private String createTime;
    private int createTimeUserID;
    private String updateTime;
    private int updateTimeUserID;
    
    
    public String getCreateTime() {
    	if(this.createTime!=null&&this.createTime.length()>0){
    		return this.createTime.substring(0, this.createTime.length()-2);
    	}
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getCreateTimeUserID() {
		return createTimeUserID;
	}

	public void setCreateTimeUserID(int createTimeUserID) {
		this.createTimeUserID = createTimeUserID;
	}

	public String getUpdateTime() {
		if(this.updateTime!=null&&this.updateTime.length()>0){
    		return this.updateTime.substring(0, this.updateTime.length()-2);
    	}
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getUpdateTimeUserID() {
		return updateTimeUserID;
	}

	public void setUpdateTimeUserID(int updateTimeUserID) {
		this.updateTimeUserID = updateTimeUserID;
	}

	public String getBpic() {
		return bpic;
	}

	public void setBpic(String bpic) {
		this.bpic = bpic;
	}

	public String getSpic() {
		return spic;
	}

	public void setSpic(String spic) {
		this.spic = spic;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.id
     *
     * @return the value of tc_carstyle.id
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.id
     *
     * @param id the value for tc_carstyle.id
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.style
     *
     * @return the value of tc_carstyle.style
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public String getStyle() {
        return style;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.style
     *
     * @param style the value for tc_carstyle.style
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.enname
     *
     * @return the value of tc_carstyle.enname
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public String getEnname() {
        return enname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.enname
     *
     * @param enname the value for tc_carstyle.enname
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setEnname(String enname) {
        this.enname = enname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.pid
     *
     * @return the value of tc_carstyle.pid
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.pid
     *
     * @param pid the value for tc_carstyle.pid
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }


/*    public BrandDomain getPpid() {
		return ppid;
	}

	public void setPpid(BrandDomain ppid) {
		this.ppid = ppid;
	}*/
    

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.factory_price
     *
     * @return the value of tc_carstyle.factory_price
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public String getFactoryPrice() {
        return factoryPrice;
    }

    public Integer getPpid() {
		return ppid;
	}

	public void setPpid(Integer ppid) {
		this.ppid = ppid;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.factory_price
     *
     * @param factoryPrice the value for tc_carstyle.factory_price
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setFactoryPrice(String factoryPrice) {
        this.factoryPrice = factoryPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.market_price
     *
     * @return the value of tc_carstyle.market_price
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public String getMarketPrice() {
        return marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.market_price
     *
     * @param marketPrice the value for tc_carstyle.market_price
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.level
     *
     * @return the value of tc_carstyle.level
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public String getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.level
     *
     * @param level the value for tc_carstyle.level
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.speed_box
     *
     * @return the value of tc_carstyle.speed_box
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public String getSpeedBox() {
    	
        return speedBox;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.speed_box
     *
     * @param speedBox the value for tc_carstyle.speed_box
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setSpeedBox(String speedBox) {
        this.speedBox = speedBox;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.detail_url
     *
     * @return the value of tc_carstyle.detail_url
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public String getDetailUrl() {
        return detailUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.detail_url
     *
     * @param detailUrl the value for tc_carstyle.detail_url
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.texts
     *
     * @return the value of tc_carstyle.texts
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public String getTexts() {
        return texts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.texts
     *
     * @param texts the value for tc_carstyle.texts
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setTexts(String texts) {
        this.texts = texts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.sort
     *
     * @return the value of tc_carstyle.sort
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public int getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.sort
     *
     * @param sort the value for tc_carstyle.sort
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setSort(int sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.bos
     *
     * @return the value of tc_carstyle.bos
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public int getBos() {
        return bos;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.bos
     *
     * @param bos the value for tc_carstyle.bos
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setBos(int bos) {
        this.bos = bos;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.addtime
     *
     * @return the value of tc_carstyle.addtime
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public Integer getAddtime() {
        return addtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.addtime
     *
     * @param addtime the value for tc_carstyle.addtime
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.initial
     *
     * @return the value of tc_carstyle.initial
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public String getInitial() {
        return initial;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.initial
     *
     * @param initial the value for tc_carstyle.initial
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setInitial(String initial) {
        this.initial = initial;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.type
     *
     * @return the value of tc_carstyle.type
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.type
     *
     * @param type the value for tc_carstyle.type
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.show
     *
     * @return the value of tc_carstyle.show
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public int getShow() {
        return show;
    }
    public void setShow(int show) {
        this.show = show;
    }

    public String getColors() {
        return colors;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.colors
     *
     * @param colors the value for tc_carstyle.colors
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setColors(String colors) {
        this.colors = colors;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.color_no
     *
     * @return the value of tc_carstyle.color_no
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public String getColorNo() {
        return colorNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.color_no
     *
     * @param colorNo the value for tc_carstyle.color_no
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setColorNo(String colorNo) {
        this.colorNo = colorNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tc_carstyle.save_money
     *
     * @return the value of tc_carstyle.save_money
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public Integer getSaveMoney() {
        return saveMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tc_carstyle.save_money
     *
     * @param saveMoney the value for tc_carstyle.save_money
     *
     * @mbggenerated Fri Jun 20 18:42:35 CST 2014
     */
    public void setSaveMoney(Integer saveMoney) {
        this.saveMoney = saveMoney;
    }

	@Override
	public String toString() {
		return "CarstyleDomain [id=" + id + ", page=" + page + ", bbip=" + bbip
				+ ", carname=" + carname + ", oldImage=" + oldImage
				+ ", style=" + style + ", enname=" + enname + ", pid=" + pid
				+ ", ppid=" + ppid + ", factoryPrice=" + factoryPrice
				+ ", marketPrice=" + marketPrice + ", level=" + level
				+ ", speedBox=" + speedBox + ", detailUrl=" + detailUrl
				+ ", texts=" + texts + ", sort=" + sort + ", bos=" + bos
				+ ", addtime=" + addtime + ", initial=" + initial + ", type="
				+ type + ", show=" + show + ", colors=" + colors + ", colorNo="
				+ colorNo + ", bpic=" + bpic + ", spic=" + spic + ", spicsrc="
				+ spicsrc + ", publicMark=" + publicMark + ", saveMoney="
				+ saveMoney + ", createTime=" + createTime
				+ ", createTimeUserID=" + createTimeUserID + ", updateTime="
				+ updateTime + ", updateTimeUserID=" + updateTimeUserID + "]";
	}

    
}