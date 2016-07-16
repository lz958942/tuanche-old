package com.tuanche.service.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.admin.GmsAddress;
import com.tuanche.bean.admin.GmsBrand;
import com.tuanche.bean.admin.GmsCity;
import com.tuanche.bean.admin.GmsContent;
import com.tuanche.bean.erp.TCrmCustomer;
import com.tuanche.dao.admin.GcmBrandDao;
import com.tuanche.dao.admin.GcmCityDao;
import com.tuanche.dao.admin.GcmContentDao;
import com.tuanche.dao.erp.TCrmCustomerDao;
import com.tuanche.util.GcmUtil;


@Service
public class GcmCityService {
	@Autowired
	private GcmCityDao cityDao;
	@Autowired
	private GcmBrandDao brandDao;
	@Autowired
	private GcmContentDao contentDao;
	@Autowired
	private TCrmCustomerDao tcrmDao;
	
	
	public List<GmsCity> getByPage(GmsCity gmsCity){
		return cityDao.getByPage(gmsCity);
	}
	
	public int add(GmsCity gmsCity,HttpServletRequest request){
		String uploadImg = GcmUtil.uploadImg(gmsCity.getImage(),request);
		gmsCity.setImage(uploadImg);
		
		int line = cityDao.insert(gmsCity);
		Integer id = gmsCity.getId();
		if(line==0 || id == null){
			return 0;
		}
		brandDao.addGmsBrandList(gmsCity.getHotStyle(), id, GmsBrand.TYPE_RM);//热门车型
		brandDao.addGmsBrandList( gmsCity.getHighStyle(), id, GmsBrand.TYPE_GD);//高端车型
		brandDao.addGmsBrandList(gmsCity.getHomemadeStyle(), id, GmsBrand.TYPE_GC);//国产
		brandDao.addGmsBrandList(gmsCity.getJointStyle(), id, GmsBrand.TYPE_HZ);//合资
		contentDao.addContentList(gmsCity.getCarNews(), id, GmsContent.TYPE_NEWS, request);	//资讯
		contentDao.addContentList(gmsCity.getCarReview(), id, GmsContent.TYPE_HG, request);//车展回顾
		contentDao.addContentList(gmsCity.getOldCarShow(), id, GmsContent.TYPE_OLDNEWS, request);//往期车展
		contentDao.addContentList(gmsCity.getCountyGoShow(), id, GmsContent.TYPE_XW_XSXZ, request);//县市巡展
		contentDao.addContentList(gmsCity.getShowShops(), id, GmsContent.TYPE_DIAN, request);//4s店
		return id;
	}
	
	public GmsCity getGcmById(Integer id){
		GmsCity gcmById = cityDao.getGcmById(id);
		//品牌
		List<GmsBrand> gmsBrandsByGmsId = brandDao.getGmsBrandsByGmsId(id, GmsBrand.TYPE_RM);
		gcmById.setHotStyle(gmsBrandsByGmsId);
		List<GmsBrand> gmsBrandsByGmsId2 = brandDao.getGmsBrandsByGmsId(id, GmsBrand.TYPE_GD);
		gcmById.setHighStyle(gmsBrandsByGmsId2);
		List<GmsBrand> gmsBrandsByGmsId3 = brandDao.getGmsBrandsByGmsId(id, GmsBrand.TYPE_GC);
		gcmById.setHomemadeStyle(gmsBrandsByGmsId3);
		List<GmsBrand> gmsBrandsByGmsId4 = brandDao.getGmsBrandsByGmsId(id, GmsBrand.TYPE_HZ);
		gcmById.setJointStyle(gmsBrandsByGmsId4);
		//资讯
		List<GmsContent> contentListByGcmId = contentDao.getContentListByGcmId(id, GmsContent.TYPE_NEWS);
		gcmById.setCarNews(contentListByGcmId);
		//车展回顾
		List<GmsContent> contentListByGcmId2 = contentDao.getContentListByGcmId(id, GmsContent.TYPE_HG);
		gcmById.setCarReview(contentListByGcmId2);
		//往期车展
		List<GmsContent> contentListByGcmId3 = contentDao.getContentListByGcmId(id, GmsContent.TYPE_OLDNEWS);
		gcmById.setOldCarShow(contentListByGcmId3);
		//县市巡展
		List<GmsContent> contentListByGcmId4 = contentDao.getContentListByGcmId(id, GmsContent.TYPE_XW_XSXZ);
		gcmById.setCountyGoShow(contentListByGcmId4);
		//4s店
		List<GmsContent> contentListByGcmId5 = contentDao.getContentListByGcmId(id, GmsContent.TYPE_DIAN);
		//获取4s店名
		getDianName(contentListByGcmId5);
		gcmById.setShowShops(contentListByGcmId5);
		return gcmById;
	}
	
	public void updateGcm(GmsCity gmsCity,HttpServletRequest request){
		String uploadImg = GcmUtil.uploadImg(gmsCity.getImage(),request);
		gmsCity.setImage(uploadImg);
		//车型品牌
		brandDao.updateGmsBrandList(gmsCity.getHotStyle(),gmsCity.getId(),GmsBrand.TYPE_RM);
		brandDao.updateGmsBrandList(gmsCity.getHighStyle(),gmsCity.getId(),GmsBrand.TYPE_GD);
		brandDao.updateGmsBrandList(gmsCity.getHomemadeStyle(),gmsCity.getId(),GmsBrand.TYPE_GC);
		brandDao.updateGmsBrandList(gmsCity.getJointStyle(),gmsCity.getId(),GmsBrand.TYPE_HZ);
		//资讯
		contentDao.updateConList(gmsCity.getCarNews(), gmsCity.getId(), GmsContent.TYPE_NEWS, request);
		//车展回顾
		contentDao.updateConList(gmsCity.getCarReview(),  gmsCity.getId(), GmsContent.TYPE_HG, request);
		//往期车展
		contentDao.updateConList(gmsCity.getOldCarShow(),  gmsCity.getId(), GmsContent.TYPE_OLDNEWS, request);
		//县市巡展
		contentDao.updateConList(gmsCity.getCountyGoShow(), gmsCity.getId(), GmsContent.TYPE_XW_XSXZ, request);
		//4s店
		contentDao.updateConList(gmsCity.getShowShops(), gmsCity.getId(), GmsContent.TYPE_DIAN, request);
		cityDao.updateGcm(gmsCity);
	}
	
	/** 上线团车会
	 * Administrator：zhaojl
	 * @param gcmId
	 * @param isOnline  
	 */
	public int deleteGcm(Integer gcmId,Integer isOnline){
		if(gcmId == null){
			return 0;
		}
		GmsCity gmsCity = new GmsCity();
		gmsCity.setId(gcmId);
		gmsCity.setOnline(isOnline);
		return cityDao.updateGcm(gmsCity);
	}
	
	/** 删除团车会车型品牌
	 * Administrator：zhaojl
	 * @param gcmId
	 */
	public int deleteGcmBrand(Integer gcmBrandId){
		if(gcmBrandId == null){
			return 0;
		}
		GmsBrand brand = new GmsBrand();
		brand.setId(gcmBrandId);
		brand.setIsDelete(GmsBrand.IS_DELETE);
		return brandDao.updateGmsBrand(brand);
	}
	
	/** 删除团车会内容
	 * Administrator：zhaojl
	 * @param gcmId
	 */
	public int deleteGcmContent(Integer gcmContentId){
		if(gcmContentId == null){
			return 0;
		}
		GmsContent content = new GmsContent();
		content.setId(gcmContentId);
		content.setIsDelete(GmsContent.IS_DELETE);
		return contentDao.updateGmsContent(content);
	}
	
	public TCrmCustomer getById(int id){
		return tcrmDao.getById(id);
	}
	public List<TCrmCustomer> getByName(String name){
		return tcrmDao.getListByName(name);
	}
	
	/**
	 * 获取 4s店名
	 * Administrator：zhaojl
	 * @param contents
	 */
	private void getDianName(List<GmsContent> contents){
		if(contents == null){
			return;
		}
		for (GmsContent  content:contents) {
			if(content!=null && content.getDianId()!=null){
				TCrmCustomer byId = tcrmDao.getById(content.getDianId());
				if(byId!=null){
					content.setDianName(byId.getCustomer());
				}
			}
		}
	}
}
