package com.tuanche.mapper.tongji.read;

import java.util.List;

import com.tuanche.bean.tongji.RecordStat;

public interface RecordStatReadMapper {
	List<RecordStat> queryRecordStatListByPage(RecordStat recordStat);
	List<RecordStat> queryRecordStatList(RecordStat recordStat);
	RecordStat queryRecordStatById(int id);
	int isExist(RecordStat recordStat);
}
