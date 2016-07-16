package com.tuanche.service.che;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.che.GiftBean;
import com.tuanche.dao.che.GiftDao;
import com.tuanche.smc.util.ZiXunDateUtil;

@Service
public class GiftService {
	@Autowired
private GiftDao dao;

	public List<GiftBean> home(GiftBean bean) {
		return dao.home(bean);
	}

	public GiftBean findByid(Integer id) {
		return dao.findByid(id);
	}

	/**
	 * 
	 * */
	public void savegift(GiftBean bean,int uid) {
		if(bean!=null){
			giftInit(bean,uid);
			if(bean.getId()==null){
				dao.savegift(bean);
			}else{
			dao.editGift(bean);
			}
		}
	}

	private void giftInit(GiftBean bean,int uid) {
		if(bean!=null){
			if(bean.getId()==null){
				bean.setCreateTime(ZiXunDateUtil.getDateToString());
				bean.setCreateUid(uid);
			}else{
				bean.setUpdateTime(ZiXunDateUtil.getDateToString());
				bean.setUpdateUid(uid);
			}
		}
	}

	public void updateStatus(Integer id, Integer type,int uid) {
		dao.updateStatus(id,type,uid);
	}

	public void updateStatusFromIds(Integer type, Integer uid,String ids) {
		dao.updateStatusFromIds(type,uid,ids);
	}
}
