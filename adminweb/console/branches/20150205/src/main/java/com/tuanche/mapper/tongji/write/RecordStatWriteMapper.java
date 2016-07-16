package com.tuanche.mapper.tongji.write;

import com.tuanche.bean.tongji.RecordStat;

public interface RecordStatWriteMapper {
	void addRecordStat(RecordStat recordStat);
	void updateRecordStat(RecordStat recordStat);
	void deleteRecordStat(int id);
}
