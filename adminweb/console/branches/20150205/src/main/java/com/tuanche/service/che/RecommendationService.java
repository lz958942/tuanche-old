package com.tuanche.service.che;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.che.BrandGroupbuy;
import com.tuanche.bean.che.CarstyleDomain;
import com.tuanche.bean.che.CarstyleGroupbuy;
import com.tuanche.bean.che.Recommendation;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.dao.che.RecommendationDao;
import com.tuanche.mapper.che.read.BrandGroupbuyReadMapper;
import com.tuanche.mapper.che.read.CarstyleGroupbuyReadMapper;
import com.tuanche.smc.common.Common;
import com.tuanche.smc.util.ZiXunDateUtil;
import com.tuanche.util.KevinUtil;

@Service
public class RecommendationService {
	@Autowired
	private RecommendationDao dao;
	
	public List<Recommendation> getR12NHome(Recommendation r12n) {
		return dao.getR12NHome(r12n);
	}

	public Recommendation getR12NByid(Integer id) {
		return dao.getR12NByid(id);
	}

/*	public BrandGroupbuy getGroupBrandAll() {
		return dao.getGroupBrandAll();
	}

	public CarstyleGroupbuy getGroupCarStyleAll() {
		return dao.getGroupCarStyleAll();
	}*/
	public List<Recommendation> getGroupBrandAllBycity(Integer city) {
		 List<BrandGroupbuy> list=dao.getGroupBrandAllBycity(city);
		 List<Recommendation> r12ns=new ArrayList<Recommendation>();
		 Recommendation r12n=new Recommendation();
		 Recommendation tmpR12n=null;
		 for (BrandGroupbuy brandGroupbuy : list) {
			if(brandGroupbuy!=null ){
				tmpR12n=(Recommendation)r12n.clone();
				tmpR12n.setBrandName(GlobalConstants.brandMap.containsKey(brandGroupbuy.getBrandId())==true?GlobalConstants.brandMap.get(brandGroupbuy.getBrandId()).getName():"无");
				tmpR12n.setBrandId(brandGroupbuy.getBrandId());
				tmpR12n.setCityId(brandGroupbuy.getCityId());
				 r12ns.add(tmpR12n);
			}
		}
		 tmpR12n=null;
		 r12n=null; 
		 list=null;
		return r12ns;
	}
	public List<Recommendation> getGroupCarStyleAllByCityAndBrand(Integer city,Integer brand) {
		List<CarstyleGroupbuy>  list=dao.getGroupCarStyleAllByCityAndBrand(city,brand);
		List<Recommendation> r12ns=new ArrayList<Recommendation>();
		Recommendation r12n=new Recommendation();
		 Recommendation tmpR12n=null;
		for (CarstyleGroupbuy carstyleGroupbuy : list) {
			if(carstyleGroupbuy!=null){
				tmpR12n=(Recommendation)r12n.clone();
				tmpR12n.setBrandId(carstyleGroupbuy.getBrandId());
				tmpR12n.setCarstyleId(carstyleGroupbuy.getCarstyleId());
				tmpR12n.setCarStyleName(Common.styleMap.containsKey(carstyleGroupbuy.getCarstyleId()+"")==true?Common.styleMap.get(carstyleGroupbuy.getCarstyleId()+"").getName():"无");
				r12ns.add(tmpR12n);
			}
		}
		 tmpR12n=null;
		 r12n=null; 
		 list=null;
		return r12ns ;
	}

	public void saveR12N(Recommendation r12n) {
			if(r12n!=null){
				if(r12n.getId()==null){
					if(r12n.getCarStyleNameR()!=null){
						//要添加几条推荐
						r12n.setCreateTime(ZiXunDateUtil.getDateToStringMM());
						ArrayList<Recommendation> r12ns=initR12N(r12n);
						dao.saveR12n(r12ns);
					}
				}/*else{
					dao.updateR12n(r12n);
				}*/
			}
	}
	private ArrayList<Recommendation> initR12N(Recommendation r12n) {
		String values=null;
		Recommendation tmpR12N=null;
		int size= KevinUtil.getFrequency(r12n.getCarStyleNameR(),",")==0?200:KevinUtil.getFrequency(r12n.getCarStyleNameR(),",")+1;
		int listSize=KevinUtil.getFrequency(r12n.getCarStyleName(),",")==0?200:KevinUtil.getFrequency(r12n.getCarStyleName(),",")+1;
		
		ArrayList<Recommendation> r12ns=new ArrayList<Recommendation>(size*listSize);
		String[] stringTokenizer=r12n.getCarStyleNameR().split(",|，");
		String[] qCars=r12n.getCarStyleName().split(",|，");
		String values1=null;
		for (String string1 : stringTokenizer) {
			values1 =string1;
			if(values1!=null && KevinUtil.getFrequency(values1,"-")==1){
				//页面品牌
				if(values1!=null &&values1.length()>0){
				for (String string : qCars) {
					//查新品牌
					tmpR12N=(Recommendation) r12n.clone();
					values =string;
					tmpR12N.setrBrandId(Integer.valueOf(values1.substring(0,values1.indexOf("-"))));
					tmpR12N.setrStyleId(Integer.valueOf(values1.substring(values1.indexOf("-")+1,values1.length())));
					if(values!=null && KevinUtil.getFrequency(values,"-")==1){
					tmpR12N.setBrandId(Integer.valueOf(values.substring(0,values.indexOf("-"))));
					tmpR12N.setCarstyleId(Integer.valueOf(values.substring(values.indexOf("-")+1,values.length())));
					r12ns.add(tmpR12N);
					}
				}
			}
			}
		}
		return r12ns;
	}

	public void UpdateStatusOrbatch(Integer id, String ids, Integer type,
			Integer uid) {
		if(id==null){
			//批量
			dao.updateStatusBatch(ids,type,uid);
		}else{
			//单个
			dao.updateStatus(id,type,uid);
		}
	}

	public List<CarstyleDomain> getCarStyle(Integer brandId) {
		return dao.getCarStyle(brandId);
	}
}
