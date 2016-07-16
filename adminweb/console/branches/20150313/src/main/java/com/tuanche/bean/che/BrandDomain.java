package com.tuanche.bean.che;



import java.io.Serializable;

import com.tuanche.commons.util.StringUtils;
import com.tuanche.smc.common.page.impl.Pagination;

public class BrandDomain implements Serializable {
		private Integer id; 
	    private String tid;
	    private String name;
	    private String enname;
	    private String logo;
	    private String logosrc;
	    private String oldImage;
	    private String cname;
	    private String series;
	    private Byte sort;
	    private Integer addtime;
	    private String initial;
	    private String pinyin;
	    private Byte newSeries;
	    private Byte contry;
	    private Byte vender;
	    private int status;
	    private String level;
		private Pagination page;
		 private Integer createUserId; //创建人
		 private String updateTime;     //修改时间
		 private Integer updateUserId; //修改人
		 private String createTime;  //创建时间
		 private int pid;
		private String reviewInitial; //页面展示首字母  一汽大众 Y
		private String  typepinyI;
		private String brndPic;
		private String brndPicSrc;
		private  Integer publicMark; //标识 0公用，1新车，2二手车
		
		
	    public Integer getPublicMark() {
			return publicMark;
		}
		public void setPublicMark(Integer publicMark) {
			this.publicMark = publicMark;
		}
		public String getBrndPic() {
			return brndPic;
		}
		public void setBrndPic(String brndPic) {
			this.brndPic = brndPic;
		}
		public String getReviewInitial() {
			return reviewInitial;
		}
		public void setReviewInitial(String reviewInitial) {
			this.reviewInitial = reviewInitial;
		}
		public String getOldImage() {
			return oldImage;
		}
		public void setOldImage(String oldImage) {
			this.oldImage = oldImage;
		}
		public int getPid() {
			return pid;
		}
		public void setPid(int pid) {
			this.pid = pid;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public Integer getCreateUserId() {
			return createUserId;
		}
		public void setCreateUserId(Integer createUserId) {
			this.createUserId = createUserId;
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
		public Integer getUpdateUserId() {
			return updateUserId;
		}
		public void setUpdateUserId(Integer updateUserId) {
			this.updateUserId = updateUserId;
		}
		public String getCreateTime() {
			if(this.createTime!=null&&this.createTime.length()>0){
				return this.createTime.substring(0, this.createTime.length()-2);
			}
			return createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		public Pagination getPage() {
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
	    public String getTid() {
	        return tid;
	    }

	    public void setTid(String tid) {
	        this.tid = tid;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	 
	    public String getEnname() {
	        return enname;
	    }

	 
	    public void setEnname(String enname) {
	        this.enname = enname;
	    }

	   
	    public String getLogo() {
	    	return logo;
	    	//return "/pic_tmp/sites/"+logo+"_s.jpg";
	    	
	    }

	 
	    public void setLogo(String logo) {
	        this.logo = logo;
	    }
	    


	    public String getLogosrc() {
	    	if(StringUtils.isNotEmpty(this.logo)&&this.logo.startsWith("http")){
	    		return logo;
	    	}
	    	if(StringUtils.isNotEmpty(this.logo)&&this.logo.startsWith("/pic_tmp")){
	    		return logo;
	    		}
	    	if(!StringUtils.isNotEmpty(this.logo)){
	    		return logo;
	    		}
			return "http://pic.tuanche.com/car"+logo+"_s.jpg";
			 
		}
	    public String getBrndPicSrc() {
			if(StringUtils.isNotEmpty(this.brndPic)&&this.brndPic.startsWith("http")){
	    		return brndPic;
	    	}
			if(StringUtils.isNotEmpty(this.brndPic)&&this.brndPic.startsWith("/pic_tmp")){
	    		return brndPic;
	    		}
			if(!StringUtils.isNotEmpty(this.brndPic)){
	    		return brndPic;
	    		}
			return "http://pic.tuanche.com/car"+brndPic+".jpg";
		}
		public void setLogosrc(String logosrc) {
			this.logosrc = logosrc;
		}
		public String getCname() {
	        return cname;
	    }

	    public void setCname(String cname) {
	        this.cname = cname;
	    }

	  
	    public String getSeries() {
	    	if(this.series!=null&&this.series.length()>0){
	    	if(this.series.equals("1"))
	        return "美系";
	    	if(this.series.equals("2"))
		        return "德系";
	    	if(this.series.equals("3"))
		        return "法系";
	    	if(this.series.equals("4"))
		        return "日系";
	    	if(this.series.equals("5"))
		        return "合资";
	    	if(this.series.equals("6"))
		        return "自主";
	    	if(this.series.equals("8"))
		        return "韩系";
	    	return "其他";
	    	}
	    	return series;
	    }

	  
	    public void setSeries(String series) {
	        this.series = series;
	    }

	    public Byte getSort() {
	        return sort;
	    }

	    public void setSort(Byte sort) {
	        this.sort = sort;
	    }

	   
	    public Integer getAddtime() {
	        return addtime;
	    }

	    public void setAddtime(Integer addtime) {
	        this.addtime = addtime;
	    }

	   
	    public String getInitial() {
	        return initial;
	    }

	    public void setInitial(String initial) {
	        this.initial = initial;
	    }

	  
	    public String getPinyin() {
	        return pinyin;
	    }

	   
	    public void setPinyin(String pinyin) {
	        this.pinyin = pinyin;
	    }

	  
	    public Byte getNewSeries() {
	        return newSeries;
	    }

	   
	    public void setNewSeries(Byte newSeries) {
	        this.newSeries = newSeries;
	    }

	   
	    public Byte getContry() {
	        return contry;
	    }

	  
	    public void setContry(Byte contry) {
	        this.contry = contry;
	    }

	    public Byte getVender() {
	        return vender;
	    }

	    public void setVender(Byte vender) {
	        this.vender = vender;
	    }

	    
	    public String getLevel() {
	        return level;
	    }

		public String getTypepinyI() {
			if(this.pinyin==null ||this.pinyin.length()==0 ||"NULL".equals(this.pinyin)){
				return "";
			}
			return this.pinyin.substring(0,1).toUpperCase();
		}
		public void setTypepinyI(String typepinyI) {
			this.typepinyI = typepinyI;
		}
		
	
		
	    
}
