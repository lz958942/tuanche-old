package com.tuanche.bean.che;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.tuanche.smc.common.page.impl.Pagination;

@SuppressWarnings("serial")
public class SubjectInfo implements Serializable{
	
	private Integer id;//报名id
	private Integer subjectId;//专题id
	private Integer newSubjectId;//新专题id
	private Integer newKindId;//新分类id
	private String userName;//报名姓名
	private String phone;//报名手机号码
	private String carLicense;//车牌号
	private String address;//车主地址
	private Integer status;//是否购买
	private Long addTime;//报名时间
	private String ztName;//专题名称
	private String styleName;//分类名称
	private Pagination page;//分页
	private Integer styleId;//装饰分类
	private Integer ztId;//专题id
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String title;//新装饰文章标题
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public Integer getNewSubjectId() {
		return newSubjectId;
	}
	public void setNewSubjectId(Integer newSubjectId) {
		this.newSubjectId = newSubjectId;
	}
	public Integer getNewKindId() {
		return newKindId;
	}
	public void setNewKindId(Integer newKindId) {
		this.newKindId = newKindId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCarLicense() {
		return carLicense;
	}
	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getAddTime() {
		
		return addTime;
	}
	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	public String getZtName() {
		return ztName;
	}
	public void setZtName(String ztName) {
		this.ztName = ztName;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getStartTime() {
		if(startTime!=null&&!"".equals(startTime)){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			long timeStart=0;
			//得到毫秒数
			try {
				timeStart=(sdf.parse(startTime).getTime())/1000;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(timeStart>0){
				return timeStart+"";
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		if(endTime!=null&&!"".equals(endTime)){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			long timeEnd=0;
			//得到毫秒数
			try {
				timeEnd=(sdf.parse(endTime).getTime())/1000;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(timeEnd>0){
				return timeEnd+"";
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getStyleId() {
		return styleId;
	}
	public void setStyleId(Integer styleId) {
		this.styleId = styleId;
	}
	public Integer getZtId() {
		return ztId;
	}
	public void setZtId(Integer ztId) {
		this.ztId = ztId;
	}
	public String getRegistTime(){
		if(this.addTime>0){
			Date data=new Date(this.addTime*1000);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String time=sdf.format(data);
			if(time!=null&&!"".equals(time)){
				return time;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	public String getBeginTime(){
		return startTime;
	}
	public String getStopTime(){
		return endTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
