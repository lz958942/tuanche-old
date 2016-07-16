package com.tuanche.dao.che;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.che.GiftBean;
import com.tuanche.mapper.che.read.GiftReadMapper;
import com.tuanche.mapper.che.write.GiftWriteMapper;

@Repository
public class GiftDao {
	@Resource
private GiftReadMapper readMapper;
	@Resource
private  GiftWriteMapper writeMapper;
	public List<GiftBean> home(GiftBean bean) {
		return readMapper.homeByPage(bean);
	}
	public GiftBean findByid(Integer id) {
		return readMapper.findByid(id);
	}
	public void savegift(GiftBean bean) {
		writeMapper.savegift(bean);
	}
	public void editGift(GiftBean bean) {
		writeMapper.editGift(bean);
	}
	public void updateStatus(Integer id, Integer type, int uid) {
		writeMapper.updateStatus(id,type,uid);
	}
	public void updateStatusFromIds(Integer type, Integer uid, String ids) {
		writeMapper.updateStatusFromIds(type,uid,ids.split(","));
	}
}
