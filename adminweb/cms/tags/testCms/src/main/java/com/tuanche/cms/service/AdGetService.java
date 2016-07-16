package com.tuanche.cms.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.cms.bean.AdGet;
import com.tuanche.cms.dao.AdPositionTimeDao;
import com.tuanche.cms.util.Resources;

@Service
public class AdGetService {
	@Autowired
	AdPositionTimeDao adPositionTimeDao;

	public List<AdGet> adGet(int channel,int cityId,int brandId,int styleId) throws Exception{
		AdGet adGet=new AdGet();                                                                          
		adGet.setChannel(channel);
		adGet.setCityId(cityId);
		adGet.setBrandId(brandId);
		adGet.setStyleId(styleId);
		adGet.setIsDefault(0);
		List<AdGet> adGetList= adPositionTimeDao.adGet(adGet);
		adGet.setIsDefault(1);
		List<AdGet> adGetList1= adPositionTimeDao.adGet(adGet);
		List<AdGet> returnAdGetList= new LinkedList<AdGet>();
		if(adGetList==null){
			adGetList = adGetList1;
		}else{
			if(adGetList1!=null){
				adGetList.addAll(adGetList1);
			}
		}
		if(adGetList==null){
			adGet.setCityId(0);
			adGetList= adPositionTimeDao.adGet(adGet);
		}
		if(adGetList!=null){
			for(AdGet ag:adGetList){
				ag.setPicName(Resources.getString("picPath")+ag.getPicName());
				returnAdGetList.add(ag);
			}
		}
		return returnAdGetList;
	}
}
