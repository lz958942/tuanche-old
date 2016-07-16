package com.tuanche.bean.admin;

import org.apache.poi.hssf.util.HSSFColor.GOLD;

import com.tuanche.console.util.GlobalConstants;
import com.tuanche.smc.common.page.impl.Pagination;

public class TopicReview implements Cloneable {
		
	 public TopicReview(){}
	 
	 public TopicReview(Integer auditStatus,Integer idDelete,Integer gid,Integer uid){
		 this.auditStatus=auditStatus;
		 this.idDelete=idDelete;
		 this.topicId=gid;
		 this.userId=uid;
	 }
	 
		private Integer id;
	    private Integer topicId,sort;

	    private String comment;

	    private Integer backerNum;

	    private Integer floorNum;

	    private Integer auditStatus;

	    private Integer idDelete;

	    private Integer userId;

	    private String userName;

	    private String addTime;

	    private Integer upStringUserId;

	    private String upStringTime;
	    private Pagination page;
	    
	    
	    public Pagination getPage() {
			return page;
		}

		public void setPage(Pagination page) {
			this.page = page;
		}

		public Integer getSort() {
			return sort;
		}

		public void setSort(Integer sort) {
			this.sort = sort;
		}

		public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public Integer getTopicId() {
	        return topicId;
	    }

	    public void setTopicId(Integer topicId) {
	        this.topicId = topicId;
	    }

	    public String getComment() {
	        return comment;
	    }

	    public void setComment(String comment) {
	        this.comment = comment == null ? null : comment.trim();
	    }

	    public Integer getBackerNum() {
	        return backerNum;
	    }

	    public void setBackerNum(Integer backerNum) {
	        this.backerNum = backerNum;
	    }

	    public Integer getFloorNum() {
	        return floorNum;
	    }

	    public void setFloorNum(Integer floorNum) {
	        this.floorNum = floorNum;
	    }

	    public Integer getAuditStatus() {
	        return auditStatus;
	    }

	    public void setAuditStatus(Integer auditStatus) {
	        this.auditStatus = auditStatus;
	    }

	    public Integer getIdDelete() {
	        return idDelete;
	    }

	    public void setIdDelete(Integer idDelete) {
	        this.idDelete = idDelete;
	    }

	    public Integer getUserId() {
	        return userId;
	    }

	    public void setUserId(Integer userId) {
	        this.userId = userId;
	    }

	    public String getUserName() {
	    	if(this.userId==null ||this.userId==0){
	    		return userName;
	    	}else{
	    		if(GlobalConstants.users.containsKey(this.userId)){
	    			return GlobalConstants.users.get(this.userId).getName();
	    		}
	    	}
	    	
	        return "无";
	    }

	    public void setUserName(String userName) {
	        this.userName = userName == null ? null : userName.trim();
	    }

	    public String getAddTime() {
	        return addTime;
	    }

	    public void setAddTime(String addTime) {
	        this.addTime = addTime;
	    }

	    public Integer getUpStringUserId() {
	        return upStringUserId;
	    }

	    public void setUpStringUserId(Integer upStringUserId) {
	        this.upStringUserId = upStringUserId;
	    }

	    public String getUpStringTime() {
	        return upStringTime;
	    }

	    public void setUpStringTime(String upStringTime) {
	        this.upStringTime = upStringTime;
	    }
	    @Override
	    public Object clone(){
	    	Object  o=null;
	    	try {
				o= super.clone();
			} catch (CloneNotSupportedException e) {
				System.out.println(e.toString());
			}
	    	return o;
	    }

		@Override
		public String toString() {
			return "TopicReview [id=" + id + ", topicId=" + topicId + ", sort="
					+ sort + ", comment=" + comment + ", backerNum="
					+ backerNum + ", floorNum=" + floorNum + ", auditStatus="
					+ auditStatus + ", idDelete=" + idDelete + ", userId="
					+ userId + ", userName=" + userName + ", addTime="
					+ addTime + ", upStringUserId=" + upStringUserId
					+ ", upStringTime=" + upStringTime + ", page=" + page + "]";
		}
	    
}
