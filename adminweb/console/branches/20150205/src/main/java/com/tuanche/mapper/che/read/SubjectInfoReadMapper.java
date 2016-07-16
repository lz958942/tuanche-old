package com.tuanche.mapper.che.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.SubjectInfo;

public interface SubjectInfoReadMapper {

	List<SubjectInfo> queryByPage(@Param("condition")List<String> condition);
	
	

}
