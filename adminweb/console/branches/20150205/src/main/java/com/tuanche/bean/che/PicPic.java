package com.tuanche.bean.che;

import com.tuanche.console.util.StringUtils;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.util.KevinUtil;

public class PicPic implements Cloneable{

	private Integer id,did,bid,cid,uid,classid,score,addtime,status,createUid,operationUid,colourId,carId,isNew,collectionId,picCover;
	private String visitUrl,collectionName,propertyid,name,url,burl,murl,surl,turl,createTime,operationTime,desc,colourName,picTitle,delImg;
	public PicPic(){
	
	}
	
	public PicPic(String picTitle2, String desc2, Integer bid2, Integer cid2,
			Integer carId2, String propertyid2, Integer classid2, String name2) {
		this.picTitle=picTitle2;
		this.desc=desc2;
		this.bid=bid2;
		this.cid=cid2;
		this.carId=carId2;
		this.propertyid=propertyid2;
		this.classid=classid2;
		this.name=name2;
		
	}
	public PicPic(String picTitle2, String desc2,Integer uid,String operationTime,Integer collectionId){
		this.picTitle=picTitle2;
		this.desc=desc2;
		this.operationUid=uid;
		this.uid=uid;
		this.operationTime=operationTime;
		this.collectionId=collectionId;
	}
	

	public String getDelImg() {
		return delImg;
	}

	public void setDelImg(String delImg) {
		this.delImg = delImg;
	}

	public Integer getPicCover() {
		return picCover;
	}

	public void setPicCover(Integer picCover) {
		this.picCover = picCover;
	}

	public Integer getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getPicTitle() {
		return picTitle;
	}
	public void setPicTitle(String picTitle) {
		this.picTitle = picTitle;
	}
	public Integer getCarId() {
		if(carId==null){
			return 0;
		}
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public String getColourName() {
		return colourName;
	}
	public void setColourName(String colourName) {
		this.colourName = colourName;
	}
	public Integer getColourId() {
		return colourId;
	}
	public void setColourId(Integer colourId) {
		this.colourId = colourId;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDid() {
		if(did==null){
			return 0;
		}
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public Integer getBid() {
		if(bid==null){
			return 0;
		}
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getCid() {
		if(cid==null){
			return 0;
		}
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getClassid() {
		if(classid==null){return 0;}
		return classid;
	}
	public void setClassid(Integer classid) {
		this.classid = classid;
	}
	public Integer getScore() {
		if(score==null){return 0;}
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getAddTime() {
		if(addtime==null){return 0;}
		return addtime;
	}
	public void setAddTime(Integer addTime) {
		this.addtime = addTime;
	}
	public String getPropertyid() {
		if(StringUtils.isNotEmptyString(propertyid)){
			return propertyid;
		}
		return "0";
	}
	public void setPropertyid(String propertyid) {
		this.propertyid = propertyid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBurl() {
		return burl;
	}
	public void setBurl(String burl) {
		this.burl = burl;
	}
	public String getMurl() {
		return murl;
	}
	public void setMurl(String murl) {
		this.murl = murl;
	}
	public String getSurl() {
		return surl;
	}
	public void setSurl(String surl) {
		this.surl = surl;
	}
	public String getTurl() {
		return turl;
	}
	public void setTurl(String turl) {
		this.turl = turl;
	}
	
	public Integer getStatus() {
		if(status==null){return 0;}
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	

	public Integer getCreateUid() {
		return createUid;
	}
	public void setCreateUid(Integer createUid) {
		this.createUid = createUid;
	}
	public Integer getOperationUid() {
		return operationUid;
	}
	public void setOperationUid(Integer operationUid) {
		this.operationUid = operationUid;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}
	public Integer getIsNew() {
		return isNew;
	}
	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}


	/*********非持久对象 and **************/
	private String createTimeX,updateTimeX,names;
	private Integer brind;
	private Pagination page;


	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	public String getCreateTimeX() {
		if(addtime!=null && addtime>0 ){
			return KevinUtil.getStringTimeByLong(addtime*1000);
		}else{
			if(StringUtils.isNotEmptyString(createTime)){
				return createTime.substring(0,createTime.length()-10);
			}
		}
		return "无";
	}
	
	public String getUpdateTimeX() {
		if(StringUtils.isNotEmptyString(operationTime)){
			return operationTime.substring(0,operationTime.length()-10);
		}
		return "无";
	}
	public Integer getBrind() {
		return brind;
	}
	public void setBrind(Integer brind) {
		this.brind = brind;
	}
	public String getVisitUrl() {
		if(propertyid!=null && id!=null){
		if(carId!=null  && carId >0){
			return "http://photo.tuanche.com/s"+carId+"/"+propertyid+"/"+id+"/";
		}else if(collectionId!=null){
			return "http://photo.tuanche.com/yt/"+collectionId+"/"+id+"/";
		}
		}
		return "无";
	}

	@Override
	public String toString() {
		return "PicPic [id=" + id + ", did=" + did + ", bid=" + bid + ", cid="
				+ cid + ", uid=" + uid + ", classid=" + classid + ", score="
				+ score + ", addtime=" + addtime + ", status=" + status
				+ ", createUid=" + createUid + ", operationUid=" + operationUid
				+ ", colourId=" + colourId + ", carId=" + carId + ", isNew="
				+ isNew + ", propertyid=" + propertyid + ", name=" + name
				+ ", url=" + url + ", burl=" + burl + ", murl=" + murl
				+ ", surl=" + surl + ", turl=" + turl + ", createTime="
				+ createTime + ", operationTime=" + operationTime + ", desc="
				+ desc + ", colourName=" + colourName + ", picTitle="
				+ picTitle + ", createTimeX=" + createTimeX + ", updateTimeX="
				+ updateTimeX + ", names=" + names + ", brind=" + brind
				+ ", page=" + page + "]";
	}
	@Override
	public Object clone() {
		Object o=null;
		try {
		o=super.clone();
		}
		catch(CloneNotSupportedException e) {
		System.out.println(e.toString());
		}
		return o;
		}

}
