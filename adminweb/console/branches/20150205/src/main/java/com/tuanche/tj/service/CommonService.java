package com.tuanche.tj.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.che.Apply;
import com.tuanche.bean.che.Brand;
import com.tuanche.bean.che.City;
import com.tuanche.bean.che.Search;
import com.tuanche.bean.sem.Account;
import com.tuanche.bean.sem.ApplyNum;
import com.tuanche.bean.sem.Business;
import com.tuanche.bean.sem.Company;
import com.tuanche.bean.sem.Creative;
import com.tuanche.bean.sem.DayStatistics;
import com.tuanche.bean.sem.DownKeyword;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.mapper.sem.read.AccountReadMapper;
import com.tuanche.mapper.sem.read.BusinessReadMapper;
import com.tuanche.mapper.sem.read.CompanyReadMapper;
import com.tuanche.mapper.sem.read.CreativeReadMapper;
import com.tuanche.mapper.sem.read.DkeywordReadMapper;
import com.tuanche.mapper.sem.write.DayStatisticsWriteMapper;
import com.tuanche.smc.domain.base.Style;
import com.tuanche.smc.persistence.read.che100.ApplyReadMapper;
import com.tuanche.smc.persistence.read.che100.CarStyleMapper;
import com.tuanche.smc.persistence.read.che100.CityMapper;

@Service
public class CommonService {
	@Autowired
	private CityMapper cityMapper;
	@Autowired
	private CarStyleMapper carStyleMapper;
	@Autowired
	private ApplyReadMapper applyReadMapper;
	@Autowired
	private BusinessReadMapper businessReadMapper;
	@Autowired
	private AccountReadMapper accountReadMapper;
	@Autowired
	private CompanyReadMapper companyReadMapper;
	@Autowired
	private DkeywordReadMapper dkeywordReadMapper;
	@Autowired
	private CreativeReadMapper creativeReadMapper;
	@Autowired
	private DayStatisticsWriteMapper dayStatisticsWriteMapper;
	
	public List<DownKeyword> getDownKeywordList(int accountId){
		List<DownKeyword> list=dkeywordReadMapper.getDownKeywordList(accountId);
		Map<Long,Creative> creativeMap=getCreativeMap(accountId);
		Creative creative=null;
		for(DownKeyword downKeyword:list){
			creative=creativeMap.get(downKeyword.getGroupId());
			if(creative==null) continue;
			downKeyword.setCampaignName(creative.getCampaignName());
			downKeyword.setGroupName(creative.getGroupName());
			downKeyword.setUrl(creative.getUrl()+"&k="+downKeyword.getKeywordId());
		}
		return list;
	}
	
	public Map<Long,Creative> getCreativeMap(int accountId){
		Map<Long,Creative> map=new ConcurrentHashMap<Long, Creative>();
		List<Creative> list=creativeReadMapper.getCreativeList(accountId);
		for(Creative c:list){
			map.put(c.getGroupId(),c);
		}
		return map;
	}
	
	public List<Brand> getBrands(){
		return carStyleMapper.getBrands();
	}
	
	public List<City> getCitys(){
		return cityMapper.getCitys();
	}
	
	public List<Style> getCarstyles(){
		return carStyleMapper.getCarStyle();
	}
	
	public List<Style> getCarstylesOk(){
		return carStyleMapper.getCarStyleOk();
	}
	
	public Map<String,List<Business>> getBusinessInfo(){
		List<Business> businessList=businessReadMapper.select();
		Map<String,List<Business>> map=new HashMap<String, List<Business>>();
		List<Business> list=null;
		for(Business b:businessList){
			if(b.getParentCode().equals("")){
				list=new ArrayList<Business>();
				map.put(b.getBizCode(),list);
			}else{
				if(map.containsKey(b.getParentCode())){
					map.get(b.getParentCode()).add(b);
				}
			}
		}
		return map;
	}
	
	public Map<String,Company> getCompanyMap(){
		Map<String,Company> map=new HashMap<String, Company>();
		for(Company company:companyReadMapper.select(new HashMap<String, Object>())){
			if(company!=null){
				map.put(company.getCode(),company);
			}
		}
		return map;
	}
	
	public Map<String,Account> getAccountMap(){
		Map<String,Account> map=new HashMap<String, Account>();
		for(Account account:accountReadMapper.select(new HashMap<String, Object>())){
			map.put(account.getAccountCode(),account);
		}
		return map;
	}
	public Map<Integer,Account> getAccountIdMap(){
		Map<Integer,Account> map=new HashMap<Integer, Account>();
		for(Account account:accountReadMapper.select(null)){
			map.put(account.getId(),account);
		}
		return map;
	}
	public Map<String,String> getBusinessMap(){
		List<Business> businessList=businessReadMapper.select();
		Map<String,String> map=new HashMap<String,String>();
		for(Business b:businessList){
			if(!b.getParentCode().equals("")){
				map.put(b.getBizCode(),b.getBizName());
			}
		}
		return map;
	}
	
	public List<Apply> getApplyList(Map<String,Object> map){
		return applyReadMapper.selectApplyList(map);
	}
	
	public Map<Integer,Map<String,Integer>> selectDayApply(Map<String,Object> map){
		Map<Integer,Map<String,Integer>> dayMap=new LinkedHashMap<Integer, Map<String,Integer>>();
		Map<String,Integer> timeMap=new LinkedHashMap<String,Integer>();
		List<ApplyNum> applyNumList=applyReadMapper.selectDayApply(map);
		Map<String,Integer> timeNumMap=null;
		for(ApplyNum applyNum:applyNumList){
			timeMap.put(applyNum.getBmtime(),timeMap.containsKey(applyNum.getBmtime())?timeMap.get(applyNum.getBmtime())+applyNum.getNum():applyNum.getNum());
			if(dayMap.containsKey(applyNum.getDistrictId())){
				dayMap.get(applyNum.getDistrictId()).put(applyNum.getBmtime(),applyNum.getNum());
				continue;
			}
			timeNumMap=new HashMap<String, Integer>();
			timeNumMap.put(applyNum.getBmtime(),applyNum.getNum());
			dayMap.put(applyNum.getDistrictId(),timeNumMap);
		}
		dayMap.put(0,timeMap);
		return dayMap;
	}
	
	public Map<Integer,Map<String,Integer>> selectDayBrandApply(Map<String,Object> map){
		Map<Integer,Map<String,Integer>> dayMap=new LinkedHashMap<Integer, Map<String,Integer>>();
		Map<String,Integer> timeMap=new LinkedHashMap<String,Integer>();
		List<ApplyNum> applyNumList=applyReadMapper.selectDayBrandApply(map);
		Map<String,Integer> timeNumMap=null;
		for(ApplyNum applyNum:applyNumList){
			timeMap.put(applyNum.getBmtime(),timeMap.containsKey(applyNum.getBmtime())?timeMap.get(applyNum.getBmtime())+applyNum.getNum():applyNum.getNum());
			if(dayMap.containsKey(applyNum.getBrandId())){
				dayMap.get(applyNum.getBrandId()).put(applyNum.getBmtime(),
						dayMap.get(applyNum.getBrandId()).containsKey(applyNum.getBmtime())?dayMap.get(applyNum.getBrandId()).get(applyNum.getMMddtime())+applyNum.getNum():
							applyNum.getNum());
				continue;
			}
			timeNumMap=new HashMap<String, Integer>();
			timeNumMap.put(applyNum.getBmtime(),applyNum.getNum());
			dayMap.put(applyNum.getBrandId(),timeNumMap);
		}
		dayMap.put(0,timeMap);
		return dayMap;
	}
	public Map<Integer,Map<String,Integer>> selectDayBrandApplyDownload(Map<String,Object> map){
		Map<Integer,Map<String,Integer>> dayMap=new LinkedHashMap<Integer, Map<String,Integer>>();
		Map<Integer,Map<String,Integer>> returnMap=new LinkedHashMap<Integer, Map<String,Integer>>();
		Map<String,Integer> timeMap=new LinkedHashMap<String,Integer>();
		List<ApplyNum> applyNumList=applyReadMapper.selectDayBrandApply(map);
		Map<Integer,Integer> numMap=new HashMap<Integer, Integer>();
		Map<String,Integer> timeNumMap=null;
		for(ApplyNum applyNum:applyNumList){
			timeMap.put(applyNum.getBmtime(),timeMap.containsKey(applyNum.getBmtime())?timeMap.get(applyNum.getBmtime())+applyNum.getNum():applyNum.getNum());
			timeMap.put("0",timeMap.containsKey("0")?timeMap.get("0")+applyNum.getNum():applyNum.getNum());
			if(dayMap.containsKey(applyNum.getBrandId())){
				dayMap.get(applyNum.getBrandId()).put("0",dayMap.get(applyNum.getBrandId()).get("0")+applyNum.getNum());
				dayMap.get(applyNum.getBrandId()).put(applyNum.getBmtime(),
						dayMap.get(applyNum.getBrandId()).containsKey(applyNum.getBmtime())?dayMap.get(applyNum.getBrandId()).get(applyNum.getBmtime())+applyNum.getNum():
							applyNum.getNum());
				continue;
			}
			timeNumMap=new HashMap<String, Integer>();
			timeNumMap.put(applyNum.getBmtime(),applyNum.getNum());
			timeNumMap.put("0",applyNum.getNum());
			dayMap.put(applyNum.getBrandId(),timeNumMap);
		}
		for(Integer id:dayMap.keySet()){
			numMap.put(id,dayMap.get(id).get("0"));
		}
		List<Map.Entry<Integer,Integer>> sortList=new ArrayList<Map.Entry<Integer,Integer>>(numMap.entrySet());
		Collections.sort(sortList,new Comparator<Map.Entry<Integer, Integer>>() {    
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {    
                return (o2.getValue() - o1.getValue());    
            }    
        });  
		for(int i=0,len=sortList.size();i<len;i++){
			int id=sortList.get(i).getKey();
			returnMap.put(id,dayMap.get(id));
		}
		returnMap.put(0,timeMap);
		return returnMap;
	}
	public Map<String,Map<String,Integer>> selectDayCarstyleApply(Map<String,Object> map){
		Map<String,Map<String,Integer>> dayMap=new LinkedHashMap<String, Map<String,Integer>>();
		List<ApplyNum> applyNumList=applyReadMapper.selectDayBrandApply(map);
		Map<String,Integer> timeNumMap=null;
		for(ApplyNum applyNum:applyNumList){
			if(dayMap.containsKey(applyNum.getCarstyleName())){
				dayMap.get(applyNum.getCarstyleName()).put(applyNum.getMMddtime(),
							applyNum.getNum());
				continue;
			}
			timeNumMap=new LinkedHashMap<String, Integer>();
			timeNumMap.put(applyNum.getMMddtime(),applyNum.getNum());
			dayMap.put(applyNum.getCarstyleName(),timeNumMap);
		}
		return dayMap;
	}
	
	public Map<String,Map<String,Integer>> selectTimeApply(Map<String,Object> map){
		Map<String,Map<String,Integer>> resultMap=new LinkedHashMap<String, Map<String,Integer>>();
		Map<String,Integer> sidMap=new LinkedHashMap<String,Integer>();
		List<ApplyNum> applyNumList=applyReadMapper.selectTimeApply(map);
		Map<String,Integer> tempMap=null;
		for(ApplyNum applyNum:applyNumList){
			sidMap.put(applyNum.getSid()+"",sidMap.containsKey(applyNum.getSid()+"")?sidMap.get(applyNum.getSid()+"")+applyNum.getNum():applyNum.getNum());
			if(resultMap.containsKey(applyNum.getBmtime())){
				resultMap.get(applyNum.getBmtime()).put(applyNum.getSid()+"",applyNum.getNum());
				continue;
			}
			tempMap=new LinkedHashMap<String, Integer>();
			tempMap.put(applyNum.getSid()+"",applyNum.getNum());
			resultMap.put(applyNum.getBmtime(),tempMap);
		}
		resultMap.put("0",sidMap);
		return resultMap;
	}
	
	
	public Map<String,Map<String,Integer>> selectAccoutApply(Map<String,Object> map){
		Map<String,Map<String,Integer>> resultMap=new LinkedHashMap<String, Map<String,Integer>>();
		Map<String,Integer> accountMap=new LinkedHashMap<String,Integer>();
		List<ApplyNum> applyNumList=applyReadMapper.selectAccountApply(map);
		Map<String,Integer> tempMap=null;
		for(ApplyNum applyNum:applyNumList){
			String type=((String)map.get("type")).equalsIgnoreCase("newsid")?applyNum.getNewsid()+"":applyNum.getAccountCode();
			accountMap.put(type, accountMap.containsKey(type)?accountMap.get(type)+applyNum.getNum():applyNum.getNum());
			if(resultMap.containsKey(applyNum.getBmtime())){
				resultMap.get(applyNum.getBmtime()).put(type,applyNum.getNum());
				continue;
			}
			tempMap=new LinkedHashMap<String, Integer>();
			tempMap.put(type,applyNum.getNum());
			resultMap.put(applyNum.getBmtime(),tempMap);
		}
		resultMap.put("0",accountMap);
		return resultMap;
	}
	
	
	
	public Map<String,Map<Integer,Integer>> selectCityDayPosition(Map<String,Object> map){
		Map<String,Map<Integer,Integer>> resultMap=new LinkedHashMap<String, Map<Integer,Integer>>();
		Map<Integer,Integer> bmtypeMap=new LinkedHashMap<Integer, Integer>();
		Map<Integer,Integer> tempMap=null;
		List<ApplyNum> list=applyReadMapper.selectCityDayPosition(map);
		for(ApplyNum applyNum:list){
			bmtypeMap.put(applyNum.getBmtype(),bmtypeMap.containsKey(applyNum.getBmtype())?
					(applyNum.getNum()+bmtypeMap.get(applyNum.getBmtype())):applyNum.getNum());
			if(resultMap.containsKey(applyNum.getBmtime())){
				resultMap.get(applyNum.getBmtime()).put(applyNum.getBmtype(),applyNum.getNum());
				continue;
			}
			tempMap=new LinkedHashMap<Integer, Integer>();
			tempMap.put(applyNum.getBmtype(),applyNum.getNum());
			resultMap.put(applyNum.getBmtime(),tempMap);
		}
		resultMap.put("0",bmtypeMap);
		return resultMap;
	}
	
	public Map<String,Map<Integer,Integer>> selectPosition(Map<String,Object> map){
		List<ApplyNum> list=applyReadMapper.selectPosition(map);
		if(list==null || list.size()==0) return null;
		Map<String,Map<Integer,Integer>> resultMap=new LinkedHashMap<String, Map<Integer,Integer>>();
		Map<Integer,Integer> tempMap=null;
		for(ApplyNum applyNum:list){
			if(resultMap.containsKey(GlobalConstants.positionTypeMap.get(applyNum.getPosition()))){
				resultMap.get(GlobalConstants.positionTypeMap.get(applyNum.getPosition())).put(applyNum.getBmtype(),applyNum.getNum());
				continue;
			}
			tempMap=new LinkedHashMap<Integer, Integer>();
			tempMap.put(applyNum.getBmtype(),applyNum.getNum());
			resultMap.put(GlobalConstants.positionTypeMap.get(applyNum.getPosition()), tempMap);
		}
		return resultMap;
	}
	
	public Map<String,Map<Integer,Integer>> selectAllCity(Map<String,Object> map){
		List<ApplyNum> list=applyReadMapper.selectAllCity(map);
		if(list==null || list.size()==0) return null;
		Map<String,Map<Integer,Integer>> resultMap=new LinkedHashMap<String, Map<Integer,Integer>>();
		Map<Integer,Integer> tempMap=null;
		for(ApplyNum applyNum:list){
			if(!GlobalConstants.cityMap.containsKey(applyNum.getDistrictId())) continue;
			String name=GlobalConstants.cityMap.get(applyNum.getDistrictId())+"-"+applyNum.getDistrictId();
			if(resultMap.containsKey(name)){
				resultMap.get(name).put(applyNum.getBmtype(),applyNum.getNum());
				continue;
			}
			tempMap=new LinkedHashMap<Integer, Integer>();
			tempMap.put(applyNum.getBmtype(),applyNum.getNum());
			resultMap.put(name, tempMap);
		}
		return resultMap;
	}
	
	public Map<Integer,Integer> selectApplySum(Map<String,Object> map){
		Map<Integer,Integer> sum = new HashMap<Integer, Integer>();
		List<ApplyNum> selectApplyDisSum = applyReadMapper.selectApplyDisSum(map);
		for (ApplyNum applynum : selectApplyDisSum) {
			if("districtId".equals(map.get("group"))){
				sum.put(applynum.getDistrictId(), applynum.getSum());
			}else{
				sum.put(applynum.getBrandId(), applynum.getSum());
			}
		}
		return sum;
	}
	
	public Map<String,Integer> selectTimeApplySum(Map<String,Object> map){
		Map<String,Integer> sum = new HashMap<String, Integer>();
		List<ApplyNum> selectApplyDisSum = applyReadMapper.selectTimeApplyDisSum(map);
		for (ApplyNum applynum : selectApplyDisSum) {
			sum.put(applynum.getBmtime(), applynum.getNum());
		}
		return sum;
	}
	
	public List<DayStatistics> selectApplySumWhere(Search search,Map <String,DayStatistics> dsMap) throws ParseException{
		List<DayStatistics> dsList = new ArrayList<DayStatistics>();
		Map<String,Object>  map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date starttime = sdf.parse(search.getStarttime());
		Date endtime = new Date(starttime.getTime()+24*3600*1000);
		map.put("starttime",starttime);
		map.put("endtime",endtime);
		map.put("accountCode", search.getAccountCode());
		String accountCode_MENG = GlobalConstants.accountCodeMap_MENG.get(search.getAccountCode());
		Object[] array = dsMap.keySet().toArray();
		for (int i = 0; i < dsMap.size(); i++) {
			DayStatistics dayStatistics = dsMap.get(array[i]);
			if(dayStatistics.getDistrictId()==-1 || dayStatistics.getBrandId() == -1){
				dsList.add(dayStatistics);
				continue;
			}
			map.put("districtId", dayStatistics.getDistrictId());
			map.put("brandId",dayStatistics.getBrandId());
			map.put("accountCode", search.getAccountCode());
			handleString(map);
			List<ApplyNum> selectwhere = applyReadMapper.selectwhere(map);
			if(selectwhere != null && selectwhere.size() >0){
				ApplyNum applyNum = selectwhere.get(0);
				dayStatistics.setApplyNum(applyNum.getSum());
			}
			if(accountCode_MENG!=null){
				String[] split = accountCode_MENG.split("-");
				for (int sp = 0; sp <split.length;sp++) {
					String accountcodeL = split[sp];
					map.put("accountCode", accountcodeL);
					handleString(map);
					List<ApplyNum> selectwhere1 = applyReadMapper.selectwhere(map);
					if(selectwhere1 != null && selectwhere1.size() >0){
						ApplyNum applyNum = selectwhere1.get(0);
						dayStatistics.setApplyNum(dayStatistics.getApplyNum()+applyNum.getSum());
					}
				}
			}
			dsList.add(dayStatistics);
		}
		return dsList;
	}
	
	public void inserListDayStatiscs(List<DayStatistics> list){
		if(list != null && list.size() > 0){
			dayStatisticsWriteMapper.insertList(list);
		}
		
	}
	
	public int deleteDay(Search search){
		return dayStatisticsWriteMapper.delete(search);
	}
	
	public void handleString(Map<String,Object>  map ){
		String object = (String) map.get("accountCode");
		String accountval = GlobalConstants.accountTmpMap.get(object);
		if(accountval!=null){
			if(accountval.indexOf("&")!=-1){
				String[] split = accountval.split("&");
				map.put("sid", Integer.parseInt(split[0]));
				map.put("newsid", Integer.parseInt(split[1]));
			}else{
				map.put("sid", accountval);
			}
			map.remove("accountCode");
		}else{
			map.put("qib", 1);
			map.remove("sid");
			map.remove("newsid");
		}
	}
	
	
}
