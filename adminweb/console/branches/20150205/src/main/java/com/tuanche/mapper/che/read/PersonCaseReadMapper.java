package com.tuanche.mapper.che.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.PersonCase;

public interface PersonCaseReadMapper {

	List<PersonCase> PEListByConditionByPage(@Param("condition")List<String> condition);

	PersonCase updateTo(@Param("id")Integer id);

}
