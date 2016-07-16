package com.tuanche.mapper.admin.write;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.admin.MediaReport;

public interface MediaReportWriteMapper {

	void save(MediaReport bean);

	void update(MediaReport bean);

	void updateStatus(@Param("id")Integer id, @Param("type")Integer type, @Param("eid")Integer eid);

	void updateStatusBatch(@Param("list")List<String> list, @Param("type")Integer type, @Param("eid")Integer eid);

}
