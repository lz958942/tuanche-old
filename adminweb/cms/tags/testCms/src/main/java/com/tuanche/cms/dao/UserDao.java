package com.tuanche.cms.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.cms.bean.Brand;
import com.tuanche.cms.bean.User;
import com.tuanche.cms.cheread.CarStyleMapper;
import com.tuanche.cms.cheread.UserReadMapper;
import com.tuanche.cms.read.EmployeeReadMapper;
import com.tuanche.commons.util.StringUtils;
@Repository
public class UserDao {
	@Autowired
	private UserReadMapper userReadMapper;
	
	@Autowired
	private EmployeeReadMapper employeeReadMapper;
	
	@Autowired
	private CarStyleMapper carStyleMapper;
	
	public User getUserById(int id){
		User user= userReadMapper.getUserById(id);
		if(user!=null){
			List<Brand>  brands=userReadMapper.getBrandsByUid(user.getId());
			if(brands!=null){
				user.setBrands(brands);
			}
		}
		return user;
	}
	public List<User>  getUserById(String  ids){
		if(StringUtils.isNotEmpty(ids)){
			if(ids.endsWith(",")){
				ids=ids.substring(0,ids.length()-1);
			}
			List<User>  list= userReadMapper.getUserByIds(ids);
			if(list!=null){
				List<Brand>  brands=null;
				for(User tmp:list){
					brands=userReadMapper.getBrandsByUid(tmp.getId());
					if(brands!=null){
						tmp.setBrands(brands);
					}	 
					
				}
			}
			return list;
		}
		return null;
	}
}
