package com.tuanche.web.tj;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tuanche.bean.che.City;
import com.tuanche.bean.che.Search;
import com.tuanche.bean.sem.Account;
import com.tuanche.bean.sem.DayStatistics;
import com.tuanche.bean.sem.Keyword;
import com.tuanche.bean.sem.KeywordClassifyCost;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.console.util.DateEditor;
import com.tuanche.console.util.ExcelBrandCostImport;
import com.tuanche.console.util.ExportExcel;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.console.util.Pager;
import com.tuanche.console.util.RequestUtils;
import com.tuanche.console.web.AuthUtil;
import com.tuanche.mapper.sem.read.AccountReadMapper;
import com.tuanche.mapper.sem.read.CompanyReadMapper;
import com.tuanche.mapper.sem.read.DayStatisticsReadMapper;
import com.tuanche.mapper.sem.read.KeywordClassifyCostMapper;
import com.tuanche.mapper.sem.read.KeywordCostReadMapper;
import com.tuanche.service.admin.PromotionService;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.tj.service.CommonService;

@Controller
public class CostController {
	@Autowired
	private CommonService commonService;
	@Autowired
	private PromotionService promotionService;
	@Autowired
	private CompanyReadMapper companyReadMapper;
	@Autowired
	private AccountReadMapper accountReadMapper;
	@Autowired
	private DayStatisticsReadMapper dayStatisticsReadMapper;
	@Autowired
	private KeywordCostReadMapper keywordCostReadMapper;
	@Autowired
	private KeywordClassifyCostMapper classifyCostMapper;

	@RequestMapping(value = "/tongji/cost")
	public String searchCost(HttpServletRequest request, ModelMap model,
			@ModelAttribute(value = "search") Search search) {
		changeSearch(search, request);
		int cpage = RequestUtils.getInt(request, "cpage", 0);
		int totalRows = RequestUtils.getInt(request, "totalRows", 0);
		if (totalRows <= 0) {
			totalRows = dayStatisticsReadMapper.count(search);
		}
		Pager page = new Pager(cpage, totalRows, GlobalConstants.PAGE_SIZE);
		page.setLinkUrl("/tongji/cost?totalRows=" + totalRows);
		addCondition(page, request);
		search.setStart(page.getStartRow());
		search.setPageSize(GlobalConstants.PAGE_SIZE);
		search.setOrderStr("company_code asc,city_code asc");
		List<DayStatistics> statisticsList = dayStatisticsReadMapper
				.select(search);
		int asi = totalRows % 10 == 0 ? totalRows / 10 : (totalRows / 10) + 1;
		if (cpage >= asi || totalRows <= 10) {
			model.addAttribute("objsum",
					dayStatisticsReadMapper.selectwhere(search));
			Search search1 = search;
			if (search.getBrandId() <= 0) {
				search1.setBrandId(-1);
				model.addAttribute("objother",
						dayStatisticsReadMapper.selectwhere(search1));
			}
		}
		model.addAttribute("data", statisticsList);
		model.addAttribute("nameMap", GlobalConstants.businessType);
		model.addAttribute("businessTypeMap", commonService.getBusinessInfo());
		model.addAttribute("companyMap", commonService.getCompanyMap());
		model.addAttribute("accountMap", commonService.getAccountMap());
		model.addAttribute("brandMap", AuthUtil.checkedBrandMap(request));
		model.addAttribute("cityMap", AuthUtil.checkedCityMap(request));
		model.addAttribute("search", search);
		request.setAttribute("pb", page);

		return "/tongji/cost";
	}

	@RequestMapping(value = "/tongji/kcost")
	public String newKcost(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute(value = "classifyCost")KeywordClassifyCost classifyCost,ModelMap model){
		if(classifyCost!=null){
			Pagination.threadLocal.set(classifyCost.getPage());
		}else{
			classifyCost = new KeywordClassifyCost();
		}
		List<KeywordClassifyCost> list = classifyCostMapper.selectKeyWordCostByPage(classifyCost);
		model.addAttribute("page", Pagination.threadLocal.get());
		model.addAttribute("data", list);
		model.addAttribute("obj", classifyCost);
		model.addAttribute("companyMap", commonService.getCompanyMap());
		model.addAttribute("cityMap", GlobalConstants.cityMaps);
		model.addAttribute("brandMap", AuthUtil.checkedBrandMap(request));
		model.addAttribute("accountMap", commonService.getAccountIdMap());
		model.addAttribute("accountList", accountReadMapper.getGroupAccount("bd"));
		return "/tongji/new_kcost";
	}
	

	@RequestMapping(value = "/common/downkcost")
	public void newDwonkcost(HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute(value = "classifyCost")KeywordClassifyCost classifyCost,ModelMap model) {
		List<KeywordClassifyCost> list = classifyCostMapper.selectAll(classifyCost);
		Map<String, String> titleValueMap = new LinkedHashMap<String, String>();
		titleValueMap.put("Accountid", "账户");
		titleValueMap.put("CityName", "城市");
		titleValueMap.put("BrandId", "品牌");
		titleValueMap.put("UesKeywordid", "关键词ID");
		titleValueMap.put("Title", "关键词");
		titleValueMap.put("ApplyNum", "报名");
		titleValueMap.put("DayCost", "花费");
		titleValueMap.put("Click", "点击次数");
		titleValueMap.put("Device", "分类");
		titleValueMap.put("Strdate", "日期");
		request.setAttribute("accountMap", commonService.getAccountIdMap());
		ExportExcel.newExportExcel("关键词花费.xls", titleValueMap, list, request,response);
	}
	
	
//	@RequestMapping(value = "/tongji/kcost")
//	public String kcost(HttpServletRequest request, ModelMap model,
//			@ModelAttribute(value = "search") Search search) {
//		changeSearch(search, request);
//		int cpage = RequestUtils.getInt(request, "cpage", 0);
//		int totalRows = RequestUtils.getInt(request, "totalRows", 0);
//		if (totalRows <= 0) {
//			totalRows = keywordCostReadMapper.count(search);
//		}
//		Pager page = new Pager(cpage, totalRows, GlobalConstants.PAGE_SIZE);
//		page.setLinkUrl("/tongji/kcost?totalRows=" + totalRows);
//		addCondition(page, request);
//		search.setStart(page.getStartRow());
//		search.setPageSize(GlobalConstants.PAGE_SIZE);
//		List<Keyword> keywordList = keywordCostReadMapper.select(search);
//		model.addAttribute("data", keywordList);
//		model.addAttribute("companyMap", commonService.getCompanyMap());
//		model.addAttribute("accountMap", commonService.getAccountIdMap());
//		model.addAttribute("brandMap", AuthUtil.checkedBrandMap(request));
//		model.addAttribute("cityMap", AuthUtil.checkedCityMap(request));
//		model.addAttribute("search", search);
//		request.setAttribute("pb", page);
//		return "/tongji/kcost";
//	}

//	
//	@RequestMapping(value = "/common/downkcost")
//	public void dwonkcost(HttpServletRequest request,
//			@ModelAttribute(value = "search") Search search,
//			HttpServletResponse response) {
//		int accountId = StringUtils.strToInt(request.getParameter("accountId"));
//		changeSearch(search, request);
//		List<Keyword> keywordList = keywordCostReadMapper.select(search);
//		Map<String, String> titleValueMap = new LinkedHashMap<String, String>();
//		titleValueMap.put("AccountName", "账户");
//		titleValueMap.put("CityName", "城市");
//		titleValueMap.put("BrandName", "品牌");
//		titleValueMap.put("Title", "关键词");
//		titleValueMap.put("ApplyNum", "报名");
//		titleValueMap.put("CostMoney", "报名");
//		titleValueMap.put("Average", "平均");
//		titleValueMap.put("Click", "点击次数");
//		titleValueMap.put("Time", "日期");
//		ExportExcel.exportExcel((accountId > 0 ? request.getParameter("name")
//				: "") + "关键词花费.xls", titleValueMap, keywordList, request,
//				response);
//	}

	@RequestMapping(value = "/common/getAccountInfo")
	@ResponseBody
	public List<Account> getAccountInfo(
			@RequestParam(value = "code") String code) {
		return accountReadMapper.getGroupAccount(code);
	}

	@RequestMapping(value = "/cost/uploadExcel", method = RequestMethod.POST)
	public String excleImport(Model model, HttpServletRequest request,
			HttpSession session, @ModelAttribute(value = "search") Search search)
			throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile fileExcel = multipartRequest.getFile("file");
		String filerelName = fileExcel.getOriginalFilename();
		Map<String, DayStatistics> dsMap = null;
		// if(filerelName.contains(".xlsx")){
		// dsMap =
		// ExcelBrandCostImport.uploadExcel07(fileExcel.getInputStream(),
		// search);
		// }else{
		dsMap = ExcelBrandCostImport.uploadExcel03(fileExcel.getInputStream(),
				search);
		// }
		List<DayStatistics> selectApplySumWhere = commonService
				.selectApplySumWhere(search, dsMap);
		commonService.inserListDayStatiscs(selectApplySumWhere);
		model.addAttribute("search", search);
		return "redirect:/tongji/cost";
	}

	@RequestMapping(value = "/tongji/insertDayCityBrandCost")
	public void insertDayCityBrandCost(Model model, HttpServletRequest request,
			HttpSession session, @ModelAttribute(value = "search") Search search,HttpServletResponse response){
		List<DayStatistics> selectListGroup = dayStatisticsReadMapper.selectListGroupCityAndBrand(search);
		Integer addPromotionDay = promotionService.addPromotionDay(selectListGroup);
		model.addAttribute("count", addPromotionDay);
	}
	
	@RequestMapping(value = "/tongji/deleteDayData")
	public String deleteDayData(Model model, HttpServletRequest request,
			HttpSession session, @ModelAttribute(value = "search") Search search){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date starttime = null;
		try {
			starttime = sdf.parse(search.getStarttime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date endtime = new Date(starttime.getTime()+24*3600*1000);
		search.setEndtime(sdf.format(endtime));
		commonService.deleteDay(search);
		model.addAttribute("search", search);
		return "redirect:/tongji/cost";
	}
	
	@RequestMapping(value = "/tongji/downLoadExcel")
	public void downLoadExcel(Model model, HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value = "search") Search search){
		City city = GlobalConstants.districtMap.get(search.getCityCode());
		search.setDistrictId(city!=null?city.getId():0);
		List<DayStatistics> list = dayStatisticsReadMapper.selectListGroupCity(search);
		Map<String,DayStatistics> map = new HashMap<String, DayStatistics>();
		Map<String,String> accountMap = new HashMap<String,String>();//账户总
		Map<Integer,String> brandMap = new HashMap<Integer,String>();//品牌总
		String stringZZ = "0-0";
		for (DayStatistics dayStatistics :list) {
			String[] splitZZ = stringZZ.split("-");
			int applyNumZZ = Integer.parseInt(splitZZ[0]) + dayStatistics.getApplyNum();
			BigDecimal addZZ = dayStatistics.getCostMoney().add(new BigDecimal(splitZZ[1]).setScale(2, BigDecimal.ROUND_HALF_UP));
			stringZZ = applyNumZZ +"-" + addZZ;
			if(map.containsKey(dayStatistics.getAccountId()+"$"+dayStatistics.getBrandId())){
				DayStatistics ds = map.get(dayStatistics.getAccountCode()+"$"+dayStatistics.getBrandId());
				ds.setApplyNum(ds.getApplyNum()+dayStatistics.getApplyNum());
				ds.setCostMoney(ds.getCostMoney().add(dayStatistics.getCostMoney()));
			}else{
				map.put(dayStatistics.getAccountCode()+"$"+dayStatistics.getBrandId(), dayStatistics);
			}
			if(accountMap.containsKey(dayStatistics.getAccountCode())){
				String string = accountMap.get(dayStatistics.getAccountCode());
				String[] split = string.split("-");
				int applyNum = Integer.parseInt(split[0]) + dayStatistics.getApplyNum();
				BigDecimal add = dayStatistics.getCostMoney().add(new BigDecimal(split[1]).setScale(2, BigDecimal.ROUND_HALF_UP));
				accountMap.put(dayStatistics.getAccountCode(), applyNum+"-"+add);
			}else{
				accountMap.put(dayStatistics.getAccountCode(), dayStatistics.getApplyNum()+"-"+dayStatistics.getCostMoney());
			}
			if(city!=null){
				if(brandMap.containsKey(dayStatistics.getBrandId())){
					String string = brandMap.get(dayStatistics.getBrandId());
					String[] split = string.split("-");
					int applyNum = Integer.parseInt(split[0]) + dayStatistics.getApplyNum();
					BigDecimal add = dayStatistics.getCostMoney().add(new BigDecimal(split[1]).setScale(2, BigDecimal.ROUND_HALF_UP));
					brandMap.put(dayStatistics.getBrandId(), applyNum+"-"+add);
				}else{
					brandMap.put(dayStatistics.getBrandId(),  dayStatistics.getApplyNum()+"-"+dayStatistics.getCostMoney());
				}
			}
		}
		ExcelBrandCostImport.downLoadExcel03(map,accountMap,brandMap, request, response, search.getCityCode(),stringZZ);
	}
	
	private void changeSearch(Search search, HttpServletRequest request) {
		Map<String, String> resultMap = getIntersectMap(request);
		if (resultMap != null && resultMap.size() > 0) {
			if (StringUtils.isEmpty(search.getAccountCode())) {
				search.setAccountCodes(resultMap.keySet());
			}
		}
		if (StringUtils.isEmpty(search.getStarttime())) {
			search.setStarttime(new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date(new Date().getTime() - 24 * 3600 * 1000)));
		}
		if (StringUtils.isEmpty(search.getEndtime())) {
			search.setEndtime(new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date()));
		}
	}

	private Map<String, Map<String, String>> getBizCodeMap() {
		Map<String, Map<String, String>> bizMap = new HashMap<String, Map<String, String>>();
		List<Account> accoutList = accountReadMapper
				.select(new HashMap<String, Object>());
		Map<String, String> tempMap = null;
		for (Account account : accoutList) {
			for (String s : account.getBizCode().split("")) {
				if (s.equals(""))
					continue;
				if (bizMap.containsKey(s)) {
					if (!bizMap.get(s).containsKey(account.getAccountCode())) {
						bizMap.get(s).put(account.getAccountCode(),
								account.getCompanyCode());
					}
					continue;
				}
				tempMap = new HashMap<String, String>();
				tempMap.put(account.getAccountCode(), account.getCompanyCode());
				bizMap.put(s, tempMap);
			}
		}
		return bizMap;
	}

	private Map<String, String> getIntersectMap(HttpServletRequest request) {
		Map<String, Map<String, String>> bizMap = getBizCodeMap();
		Map<String, String> tempMap = new HashMap<String, String>();
		Map<String, String> resultMap = null;
		for (String s : GlobalConstants.businessType.keySet()) {
			if (StringUtils.isNotEmpty(request.getParameter(s))) {
				if (!bizMap.containsKey(request.getParameter(s))) {
					tempMap.clear();
					continue;
				}
				if (tempMap.size() == 0) {
					tempMap.putAll(bizMap.get(request.getParameter(s)));
				} else {
					resultMap = new HashMap<String, String>();
					for (String s2 : bizMap.get(request.getParameter(s))
							.keySet()) {
						if (tempMap.containsKey(s2)) {
							resultMap.put(s2, tempMap.get(s2));
						}
					}
					tempMap.clear();
					tempMap.putAll(resultMap);
				}
			}
		}
		return tempMap;
	}

	@SuppressWarnings("unchecked")
	private void addCondition(Pager pager, HttpServletRequest request) {
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String s = names.nextElement();
			if (StringUtils.isNotEmpty(request.getParameter(s))) {
				pager.addCondition(s, request.getParameter(s));
			}
		}
	}

	@InitBinder
	public void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	@RequestMapping(value = "/common/seeKeyword")
	@ResponseBody
	public List<Keyword> seeKeyword(HttpServletRequest request) {
		int brandId = RequestUtils.getInt(request, "b", 0);
		int carstyleId = RequestUtils.getInt(request, "c", 0);
		int districtId = RequestUtils.getInt(request, "d", 0);
		int accountId = RequestUtils.getInt(request, "a", 0);
		String time = request.getParameter("t");
		return keywordCostReadMapper.selectKeyword(brandId, carstyleId,
				districtId, accountId, time);
	}

	
}
