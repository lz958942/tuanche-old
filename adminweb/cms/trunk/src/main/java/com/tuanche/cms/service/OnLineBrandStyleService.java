package com.tuanche.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tuanche.cms.bean.GroupBuy;
import com.tuanche.cms.dao.GroupBuyDao;
import com.tuanche.cms.util.GlobalConstants;
import com.tuanche.cms.util.Resources;
import com.tuanche.commons.cache.Cache;
import com.tuanche.commons.cache.PerpetualCacheImpl;
import com.tuanche.commons.exception.NetworkException;
import com.tuanche.commons.http.SearchClient;
import com.tuanche.info.bean.Brand;
import com.tuanche.info.bean.CarStyle;
@Repository
public class OnLineBrandStyleService {
	@Resource
	private GroupBuyDao groupBuyDao;
	
	
	public static Cache citySeriesToBrand=new PerpetualCacheImpl(86400);//1天
	//public static Cache cityLetterToBrand=new PerpetualCacheImpl(86400);//1天
	public static Cache cityHotBrand=new PerpetualCacheImpl(86400);//1天
	public static Cache cityHotStyle=new PerpetualCacheImpl(86400);//1天
	
	public static Cache cityCarLevelToStyleNum=new PerpetualCacheImpl(3600);//1天
	public static Cache cityBrandToStyle=new PerpetualCacheImpl(86400);//1天
	/**
	 * 
	 * @param cityId
	 * @param isReady  是否开团中 0 筹备中  1正在团购
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public LinkedHashMap<Integer, List<Brand>> getCitySeriesToBrand(Integer cityId,int  isOpen) {
		LinkedHashMap<Integer, List<Brand>>  brands=(LinkedHashMap<Integer, List<Brand>>) citySeriesToBrand.get(isOpen+"-"+cityId);
		List<GroupBuy> buyList=groupBuyDao.getOnLineBuyBrand(cityId,0);
		Map<Integer,String>  haveBrand=new HashMap<Integer,String>();
		if(buyList!=null){
			for(GroupBuy buy:buyList){
				System.out.println("starttime===="+buy.getStartTime());
				if(isOpen==1&&buy.getStartTime()==null){
					continue;
				}
				
				haveBrand.put(buy.getBrandId(), null);
			}
		}
		
		List<GroupBuy> manNumList=groupBuyDao.getBrandManNum(cityId);
		if(manNumList!=null){
			brands=new LinkedHashMap<Integer,List<Brand>>();
			Brand brand=null;
			int series=0;
			for(GroupBuy buy:manNumList){
				if(!haveBrand.containsKey(buy.getBrandId())){
					continue;
				}
				brand=new Brand();
				brand.setId(buy.getBrandId());
				brand.setName((String)GlobalConstants.brandMap.get(buy.getBrandId()));
				int brandId = buy.getBrandId();
				Integer integer = GlobalConstants.brandSeriesById.get(brandId);
				if(integer==null){
					continue;
				}
				series=(Integer)integer;
				if(series==5){
					series=2;
				}
				if(!brands.containsKey(series)){
					brands.put(series, new ArrayList<Brand>());
				}
				brands.get(series).add(brand);
			}
			citySeriesToBrand.put(isOpen+"-"+cityId, brands);;
		}
		
		return brands;
	}

	@SuppressWarnings("unchecked")
	public List<Brand> getCityHotBrand(Integer cityId,int limit) {
		List<Brand>  brands=(List<Brand>) cityHotBrand.get(cityId+"-"+limit);
		if(brands!=null&&brands.size()>0){
			return brands;
		}
		List<GroupBuy> buyList=groupBuyDao.getBrandManNum(cityId,limit);
		if(buyList!=null){
			Brand brand=null;
			brands=new ArrayList<Brand>();
			for(GroupBuy buy:buyList){
				brand=new Brand();
				brand.setId(buy.getBrandId());
				brand.setName((String)GlobalConstants.brandMap.get(buy.getBrandId()));
				brands.add(brand);
			}
			cityHotBrand.put(cityId+"-"+limit, brands);;
		}
		return brands;
	}
	@SuppressWarnings("unchecked")
	public List<Brand> getCityHotBrand(Integer cityId) {
		 return this.getCityHotBrand(cityId,12);
	}
	@SuppressWarnings("unchecked")
	public List<CarStyle> getCityHotStyle(Integer cityId) {
		List<CarStyle>  styles=(List<CarStyle>) cityHotStyle.get(cityId);
		if(styles!=null){
			return styles;
		}
		List<GroupBuy> buyList=groupBuyDao.getHotStyle(cityId,14);
		if(buyList!=null){
			CarStyle style=null;
			styles=new ArrayList<CarStyle>();
			for(GroupBuy buy:buyList){
				style=new CarStyle();
				style.setId(buy.getStyleId());
				style.setName(GlobalConstants.styleMap.get(buy.getStyleId()));
				styles.add(style);
			}
			cityHotStyle.put(cityId, styles);
		}
		return styles;
	}

	/**
	 * 
	 * @param cityId
	 * @param isOpen 正在团购中
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public LinkedHashMap<Integer, List<CarStyle>> getCityBrandToStyle(Integer cityId,int isOpen) {
		LinkedHashMap<Integer, List<CarStyle>>  styles=(LinkedHashMap<Integer, List<CarStyle>>) cityBrandToStyle.get(isOpen+"-"+cityId);
		List<GroupBuy> buyList=groupBuyDao.getOnLineBuyStyle(cityId);
		if(buyList!=null){
			CarStyle style=null;
			styles=new LinkedHashMap<Integer, List<CarStyle>>();
			for(GroupBuy buy:buyList){
				if(isOpen==1&&buy.getStartTime()==null){
					continue;
				}
				style=new CarStyle();
				style.setId(buy.getStyleId());
				style.setName(buy.getStyleName());
				style.setCityId(cityId);
				if(!styles.containsKey(buy.getBrandId())){
					styles.put(buy.getBrandId(), new ArrayList<CarStyle>());
				}
				styles.get(buy.getBrandId()).add(style);
			}
			cityBrandToStyle.put(isOpen+"-"+cityId, styles);
			
		}
		return styles;
	}
	public List<CarStyle> getOnLineStyle(Integer cityId,int brandId,int isOpen) {
		LinkedHashMap<Integer, List<CarStyle>> map=this.getCityBrandToStyle(cityId, isOpen);
		if(map!=null&&map.size()>0){
			return map.get(brandId);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String,Integer> getCityCarLevelToStyleNum(Integer cityId) {
		HashMap<String,Integer>  styles=(HashMap<String,Integer>) cityCarLevelToStyleNum.get(cityId);
		if(styles!=null){
			return styles;
		}
		List<GroupBuy> buyList=groupBuyDao.getOnLineBuyStyle(cityId);
		if(buyList!=null){
			styles= new HashMap<String,Integer>();;
			for(GroupBuy buy:buyList){
				if(buy.getCarLevel()>0){
					if(!styles.containsKey(buy.getCarLevel()+"")){
						styles.put(buy.getCarLevel()+"", 1);
					}else{
						styles.put(buy.getCarLevel()+"", styles.get(buy.getCarLevel()+"")+1);
					}
				}
			}
			cityCarLevelToStyleNum.put(cityId, styles);
			
		}
		return styles;
	}
	/**
	 * 
	 * @param cityId  城市id
	 * @param bos       车型大小
	 * @param execludeCids   排除的车型id
	 * @param limit     返回的条数
	 * @return
	 * @throws NetworkException 
	 */
	public List<GroupBuy>  getGroupBuyByBos(int cityId,int bos,String execludeCids,int limit) {
		List<GroupBuy> onLineBuyByCarLevel = groupBuyDao.getOnLineBuyByCarLevel(cityId, bos, limit);
		//评星  和评价人数
		return getXC(onLineBuyByCarLevel);
	}
	
	public static void main(String[] args) {
		List<GroupBuy> list = null;
		for (GroupBuy buy :list) {
			System.out.println();
		}
	}
	
	public static List<GroupBuy> getXC(List<GroupBuy> buys){
		if(buys == null || buys.size() ==0){
			return new ArrayList<GroupBuy>();
		}
		//评星  和评价人数
		Gson gson = new Gson();
		String searchUrl = Resources.getString("ucUrl");
		SearchClient searchClient = new SearchClient(searchUrl);
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		String ids="";
		for (GroupBuy buy :buys) {
			if(buy !=null &&  buy.getStyleId()>0){
				ids+= buy.getStyleId()+",";
			}
		}
		BasicNameValuePair p1 = new BasicNameValuePair("carStyleIds",ids);
		param.add(p1);
		String rs = null;
		try {
			rs = searchClient.getHttpProcessor().post(searchUrl+"/external/evaluate/averagelist", param);
			List<GroupBuy> list = gson.fromJson(rs, new TypeToken<List<GroupBuy>>() { }.getType());
//			List<GroupBuy> list = gson.fromJson(rs, List.class);
			if(list == null){
				return buys;
			}
			Map<Integer,Double>  commentTotalMap = new HashMap<Integer, Double>();
			Map<Integer,Integer>  evaluaterTotalMap = new HashMap<Integer, Integer>();
			for (GroupBuy g:list) {
				commentTotalMap.put(g.getStyleId(), g.getCommentTotal());
				evaluaterTotalMap.put(g.getStyleId(), g.getEvaluaterTotal());
			}
			for (GroupBuy buy :buys) {
				if(buy!=null){
					Double double1 = commentTotalMap.get(buy.getStyleId());
					Integer integer = evaluaterTotalMap.get(buy.getStyleId());
					buy.setCommentTotal(double1==null?0:double1);
					buy.setEvaluaterTotal(integer==null?0:integer);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buys;
	}
}
