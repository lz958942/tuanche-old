package com.tuanche.bean.admin;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tuanche.smc.common.page.impl.Pagination;

public class Promotion {
	
	  	private Integer id;//主键
	  	private Integer cityId;//城市Id
	  	private String cityCode;//城市编码
	  	private Integer brandId;//品牌Id
	  	private String money;//推广费用
	  	private String spendTime;//页面选择的导入时间
	  	private String addTime;//Excel导入的实际时间
	  	private Integer adminId;//操作人的Id
	  	private String startTime;//开始时间
	  	private String endTime;//结束时间
	  	private Integer minMoney;//最小费用
	  	private Integer maxMoney;//最大费用
	  	private Pagination page;//分页封装类
	  	private int recordCount;//搜索记录条数
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getCityId() {
			return cityId;
		}
		public void setCityId(Integer cityId) {
			this.cityId = cityId;
		}
		public String getCityCode() {
			return cityCode;
		}
		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}
		public Integer getBrandId() {
			return brandId;
		}
		public void setBrandId(Integer brandId) {
			this.brandId = brandId;
		}
		public String getMoney() {
			return money;
		}
		public void setMoney(String money) {
			this.money = money;
		}
		public String getSpendTime() {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
			Date date=null;
			if(this.spendTime!=null&&!"".equals(this.spendTime)){
				try {
					date=df.parse(this.spendTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return df.format(date);
			}else{
				return null;
			}
			
		}
		public void setSpendTime(String spendTime) {
			this.spendTime = spendTime;
		}
		public String getAddTime() {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			Date date=null;
			if(this.addTime!=null&&!"".equals(this.addTime)){
				try {
					date=df.parse(this.addTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return df.format(date);
			}else{
				return null;
			}
			
		}
		public void setAddTime(String addTime) {
			this.addTime = addTime;
		}
		public Integer getAdminId() {
			return adminId;
		}
		public void setAdminId(Integer adminId) {
			this.adminId = adminId;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public String getEndTime() {
			return endTime;
		}
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
		public Integer getMinMoney() {
			return minMoney;
		}
		public void setMinMoney(Integer minMoney) {
			this.minMoney = minMoney;
		}
		public Integer getMaxMoney() {
			return maxMoney;
		}
		public void setMaxMoney(Integer maxMoney) {
			this.maxMoney = maxMoney;
		}
		public Pagination getPage() {
			return page;
		}
		public void setPage(Pagination page) {
			this.page = page;
		}
		public int getRecordCount() {
			return recordCount;
		}
		public void setRecordCount(int recordCount) {
			this.recordCount = recordCount;
		}
	  
}
