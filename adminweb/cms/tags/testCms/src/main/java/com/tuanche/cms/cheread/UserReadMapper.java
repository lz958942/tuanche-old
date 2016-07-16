package com.tuanche.cms.cheread;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.cms.bean.Brand;
import com.tuanche.cms.bean.User;

public interface UserReadMapper {
	public User  getUserById(@Param(value="id")int id);
	public List<User>  getUserByIds(@Param(value="ids")String  ids);
	
	public List<Brand>  getBrandsByUid(@Param(value="uid")int uid);
}
