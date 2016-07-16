package com.tuanche.dao.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.admin.GmsAddress;
import com.tuanche.mapper.admin.read.GmsAddressReadMapper;
import com.tuanche.mapper.admin.write.GmsAddressWriteMapper;

@Repository
public class GcmAddressDao {
	@Autowired
	private GmsAddressReadMapper addressRead;
	
	@Autowired
	private GmsAddressWriteMapper addressWrite;
	
	public int insert(GmsAddress aGmsAddress){
		return addressWrite.insert(aGmsAddress);
	}
	
	public GmsAddress getGcmAddrByGcmId(Integer gcmId){
		return addressRead.selectByGcmId(gcmId);
	}
	
	public int updateById(GmsAddress aGmsAddress){
		return addressWrite.updateByPrimaryKeySelective(aGmsAddress);
	}
}
