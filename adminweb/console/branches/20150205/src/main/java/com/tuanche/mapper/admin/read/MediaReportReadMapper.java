package com.tuanche.mapper.admin.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.admin.MediaReport;

public interface MediaReportReadMapper {

	List<MediaReport> selectAllByPage(@Param("condition")List<String> condition);

	MediaReport getMediaReportById(@Param("id")Integer id);

	List<MediaReport> titleRepetition(@Param("title")String title);

}
