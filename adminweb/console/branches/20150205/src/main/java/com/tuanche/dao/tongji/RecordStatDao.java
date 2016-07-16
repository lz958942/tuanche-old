package com.tuanche.dao.tongji;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.tongji.RecordStat;
import com.tuanche.mapper.tongji.read.RecordStatReadMapper;
import com.tuanche.mapper.tongji.write.RecordStatWriteMapper;

@Repository
public class RecordStatDao {
	
	private Logger logger = Logger.getLogger(RecordStatDao.class);
	
	@Autowired
	private RecordStatWriteMapper recordStatWriteMapper;
	
	@Autowired
	private RecordStatReadMapper recordStatReadMapper;
	
	//@Transactional(rollbackFor = Exception.class)
	public void addRecordStat(RecordStat recordStat) {
		recordStatWriteMapper.addRecordStat(recordStat);
	}
	
	public void updateRecordStat(RecordStat recordStat) {
		recordStatWriteMapper.updateRecordStat(recordStat);
	}

	public List<RecordStat> queryRecordStatListByPage(RecordStat recordStat) {
		return recordStatReadMapper.queryRecordStatListByPage(recordStat);
	}
	
	public List<RecordStat> queryRecordStatList(RecordStat recordStat) {
		return recordStatReadMapper.queryRecordStatList(recordStat);
	}
	
	public RecordStat queryRecordStatById(int id) {
		return recordStatReadMapper.queryRecordStatById(id);
	}
	
	public void deleteRecordStat(int id) {
		recordStatWriteMapper.deleteRecordStat(id);
	}
	
	public int isExist(RecordStat recordStat) {
		return recordStatReadMapper.isExist(recordStat);
	}
}
