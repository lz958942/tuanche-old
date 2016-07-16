package com.tuanche.mapper.admin.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.admin.Black;

public interface BlackReadMapper {

	List<Black> searchByPage(@Param("search")List<String> search);

	Black selectById(@Param("id")Integer id);

	List<Black> verifyWord(@Param("word")String word,@Param("type") Integer type);

}
