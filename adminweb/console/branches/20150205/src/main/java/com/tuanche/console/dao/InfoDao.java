package com.tuanche.console.dao;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

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
import com.tuanche.console.cheread.CityReadMapper;
import com.tuanche.console.adminread.KeywordReadMapper;
import com.tuanche.mapper.admin.read.QuestionKindReadMapper;
import com.tuanche.mapper.admin.read.TopicReadMapper;
import com.tuanche.mapper.che.read.BrandsReadMapper;
import com.tuanche.mapper.che.read.CarStylesReadMapper;
import com.tuanche.mapper.che.read.CarstylekReadMapper;
import com.tuanche.mapper.che.read.SysConfigReadMapper;
import com.tuanche.mapper.che.read.UsersReadMapper;
import com.tuanche.mapper.sem.read.AccountReadMapper;
@Repository
public class InfoDao {
	@Autowired
	private CityReadMapper  cityReadMapper;
	@Autowired
	private KeywordReadMapper keywordReadMapper;
	@Autowired
	private BrandsReadMapper brandReadMapper;
	@Autowired
	private CarStylesReadMapper carStyleReadMapper;
	
	@Resource
	private CarstylekReadMapper carstyleReadMapper;
	@Autowired
	private QuestionKindReadMapper kindReadMapper;
	@Autowired
	private AccountReadMapper accountReadMapper;
	
	@Resource
	private CarstylekReadMapper carReadMapper;
	
	@Resource
	private SysConfigReadMapper configReadMapper;
	
	//话题
	@Resource
	private TopicReadMapper topicReadMapper;
	
	//用户
	@Resource
	private UsersReadMapper usersReadMapper;
	
	public List<Users> getUsers() {
		return usersReadMapper.getUsers();
	}
	public List<City> getCity(){
		return cityReadMapper.getCity();
	}
	
	public List<City> getAllCity(){
		return cityReadMapper.getAllCity();
	}
	public List<Keyword> getKeyWord(){
		return keywordReadMapper.getKeyWord();
	}
	public List<Brand> getBrand() {
		return brandReadMapper.getBrand();
	}
	public List<CarStyle> getCarStyle() {
		return carStyleReadMapper.getCarStyle();
	}

	public List<QuestionKind> getQuestionKind(){
		return kindReadMapper.selectAlls();
	}
	public List<Account> getAccountList(){
		return accountReadMapper.getAllAccount();
	}
	//获得全部车款  （show=0.type=0）
	public List<CarstyleDomain> getCarStyles(){
		return carReadMapper.getCarStyles();
	}
	public List<SysConfig> getPicProperty(){
		return configReadMapper.getCodeByKeyFromString("pic_pic_property_id");
	}
	public List<SysConfig> getPiCclass(){
		return configReadMapper.getCodeByKeyFromString("pic_pic_class_id");
	}
	public List<SysConfig> getChannelName(){
		return configReadMapper.getCodeByKeyFromString("rec_channel");
	}
	public List<CarstyleDomain> getstyleNameByCid() {
		return carstyleReadMapper.getCarShapes();
	}
	public Map<String,List<SysConfig>> getGift(String[] strings) {
		Map<String,List<SysConfig>> map=new ConcurrentHashMap<String,List<SysConfig>>();
		if(strings!=null && strings.length>0){
			for (int i = 0; i < strings.length; i++) {
			List<SysConfig> sys=configReadMapper.getCodeByKeyFromString(strings[i]);
			map.put(strings[i].trim(), sys);
			}
			return map;
	}
		return null;
}
	public List<Topic> topicAll() {
		return topicReadMapper.topicList();
	}
	
	}