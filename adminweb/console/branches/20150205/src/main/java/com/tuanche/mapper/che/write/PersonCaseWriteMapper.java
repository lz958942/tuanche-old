package com.tuanche.mapper.che.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.PersonCase;

public interface PersonCaseWriteMapper {

	void savePE(PersonCase bean);

	void updateStatus(@Param("id")Integer id, @Param("type")Integer type);

	void update(PersonCase bean);

	void beanchStatus(@Param("list")List<String> list,@Param("type") Integer type);

}
