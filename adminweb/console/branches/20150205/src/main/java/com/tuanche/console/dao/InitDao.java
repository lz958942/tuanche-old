package com.tuanche.console.dao;


import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.util.HSSFColor.GOLD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.QuestionKind;
import com.tuanche.bean.admin.Topic;
import com.tuanche.bean.che.Brand;
import com.tuanche.bean.che.CarStyle;
import com.tuanche.bean.che.CarstyleDomain;
import com.tuanche.bean.che.SysConfig;
import com.tuanche.bean.che.Users;
import com.tuanche.bean.sem.Account;
import com.tuanche.console.bean.City;
import com.tuanche.console.bean.Keyword;
import com.tuanche.console.service.EmployeeService;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.console.util.StringUtils;
import com.tuanche.util.KevinUtil;

@Repository
public class InitDao {
	@Autowired
	private InfoDao infoDao;
	@Autowired
	private EmployeeService employService;
	public void init(){
		initCity();
		initAllCity();
		initWordMap();
		initBrandMap();
		initCarStyleMap();
		initAccountMap();
		initQuestionKind();
		
		getCarStyleNameByCarId();
		getPicProperty();
		getPiCclass();
		getChannelName();
		getstyleNameByCid();
		giftInit();
		//话题
		topicAll();
		getUsers();
	}
	private void getUsers() {
		 List<Users> users=infoDao.getUsers();	
		 if(users!=null &&users.size()>0 ){
			 for (Users users2 : users) {
				GlobalConstants.users.put(users2.getId(), users2);
			}
		 }
	}
	public void topicAll() {
		List<Topic> list=infoDao.topicAll();
		if(list!=null && list.size()>0){
			for (Topic topic : list) {
				GlobalConstants.gambit.put(topic.getId(),topic);
			}
		}
	}
	private void giftInit() {
		Map<String,List<SysConfig>> sysGift=infoDao.getGift(new String[]{"gift_exchange_type","gifit_class","gift_source"});	
		if(sysGift!=null &&sysGift.size()>0){
		       Set<String> key = sysGift.keySet();
		        for (Iterator it = key.iterator(); it.hasNext();) {
		            String s = (String) it.next();
		            giftInitMap(s,sysGift.get(s));
		        }
		}
		
	}
	private void giftInitMap(String code,List<SysConfig> list) {
		if(code!=null && code.length()>0 && list!=null && list.size()>0){
			int sysCode=0;
			for (SysConfig sysConfig : list) {
				sysCode=Integer.valueOf(sysConfig.getCode());
				if("gift_exchange_type".equals(code)){
					GlobalConstants.sysGiftExchangeType.put(sysCode, sysConfig);	
				}else if("gifit_class".equals(code)){
					GlobalConstants.gifitClass.put(sysCode, sysConfig);
				}else if("gift_source".equals(code)){
					GlobalConstants.gift_source.put(sysCode, sysConfig);
				}
			}
		}
	}
	
	private void getstyleNameByCid() {
		List<CarstyleDomain> cars=infoDao.getstyleNameByCid();
		if(cars!=null && cars.size()>0){
			int id=0;
			for (CarstyleDomain carstyleDomain : cars) {
				id=carstyleDomain.getId()==null?0:carstyleDomain.getId();
				GlobalConstants.getstyleNameByCid.put(id, carstyleDomain);
			}
		}
	}
	private void initCity(){
		GlobalConstants.citys=infoDao.getCity();
		if(GlobalConstants.citys!=null){
			for(City tmp:GlobalConstants.citys){
				GlobalConstants.cityMap.put(tmp.getId(), tmp.getDname());
				GlobalConstants.cityMaps.put(tmp.getId(), tmp);
			}
		}
	}
	private void initAllCity(){
		GlobalConstants.all_citys=infoDao.getAllCity();
		if(GlobalConstants.all_citys!=null){
			for(City tmp:GlobalConstants.all_citys){
				GlobalConstants.all_cityNameMaps.put(tmp.getDname(), tmp);
			}
		}
	}
	private void initWordMap(){
		GlobalConstants.keywords=infoDao.getKeyWord();
		if(GlobalConstants.keywords!=null){
			for(Keyword tmp:GlobalConstants.keywords){
				if(tmp.getLevel()==1||tmp.getLevel()==2){
					GlobalConstants.wordMap.put(tmp.getId(), tmp.getWord());
			    }	
		    }
	    }
	}
	/**
	 * 
	 * @author liuhg
	 * @Description 关键词关联品牌
	 */
	private void initBrandMap(){
		GlobalConstants.brandList=infoDao.getBrand();
		if(GlobalConstants.brandList!=null&&GlobalConstants.brandList.size()>0){
			for(Brand brand:GlobalConstants.brandList){
				if(!StringUtils.isEmpty(brand.getOrderName())){
					String name=brand.getOrderName().substring(0, 1);
					name=name.toUpperCase();
					brand.setOrderName(name);
				}
				GlobalConstants.brands.put(brand,brand);
			}
		}
	}
	/**
	 * 
	 * @author liuhg
	 * @Description 关键词关联车型
	 */
	private void initCarStyleMap(){
		GlobalConstants.carStyleList=infoDao.getCarStyle();
		if(GlobalConstants.carStyleList!=null&&GlobalConstants.carStyleList.size()>0){
			for(CarStyle carstyle:GlobalConstants.carStyleList)
			{
				GlobalConstants.carStyles.put(carstyle,carstyle);
				
			}
		}
	}

	
	/**
	 * sem账户
	 * Administrator：zhaojl
	 */
	private void initAccountMap(){
		List<Account> accountList = infoDao.getAccountList();
		for (Account account: accountList) {
			GlobalConstants.accountCodeMaps.put(account.getAccountCode(), account);
		}
	}
	/**
	 * 
	 * @author wtk
	 * @Description 所有分类
	 */
	private void initQuestionKind(){
		GlobalConstants.questionKindList=infoDao.getQuestionKind();
		if(GlobalConstants.questionKindList!=null&&GlobalConstants.questionKindList.size()>0){
			for(QuestionKind kind:GlobalConstants.questionKindList){
				GlobalConstants.questionKindmap.put(kind.getId(),kind.getName());
				GlobalConstants.kindsmap.put(kind.getId(),kind);
			}
		}
	}

	/**
	 * 
	 * @author lgh
	 * @Description 车款
	 */
	private void getCarStyleNameByCarId(){
		List<CarstyleDomain> cars=infoDao.getCarStyles();
		if(cars!=null && cars.size()>0){
			int id=0;
			for (CarstyleDomain carstyleDomain : cars) {
				id=carstyleDomain.getId()==null?0:carstyleDomain.getId();
				GlobalConstants.getCarStyleNameByCarId.put(id, carstyleDomain);
			}
		}
	}
	private void getPicProperty(){
		List<SysConfig> ps=infoDao.getPicProperty();
		if(ps!=null && ps.size()>0){
			int code=0;
			for (SysConfig sysConfig : ps) {
				if(KevinUtil.isNumeric(sysConfig.getCode())){
					code=Integer.valueOf(sysConfig.getCode());
					GlobalConstants.getPicProperty.put(code, sysConfig);
				}
				
			}
		}
	}
	private void getPiCclass(){
		List<SysConfig> cs=infoDao.getPiCclass();
		if(cs!=null && cs.size()>0){
			int code=0;
			for (SysConfig sysConfig : cs) {
				if(KevinUtil.isNumeric(sysConfig.getCode())){
					code=Integer.valueOf(sysConfig.getCode());
					GlobalConstants.getPiCclass.put(code, sysConfig);
				}
				
			}
		}
	}
	private void getChannelName() {
		List<SysConfig> cs=infoDao.getChannelName();
		if(cs!=null && cs.size()>0){
			int code=0;
			for (SysConfig sysConfig : cs) {
				if(KevinUtil.isNumeric(sysConfig.getCode())){
					code=Integer.valueOf(sysConfig.getCode());
					GlobalConstants.getChannelName.put(code, sysConfig);
				}
				
			}
		}
	}

}
