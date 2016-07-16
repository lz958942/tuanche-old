package com.tuanche.web.tj;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuanche.bean.sem.Account;
import com.tuanche.bean.sem.Company;
import com.tuanche.bean.sem.DownKeyword;
import com.tuanche.commons.util.Encriptor;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.ExportExcel;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.console.util.Pager;
import com.tuanche.console.util.RequestUtils;
import com.tuanche.console.util.Resources;
import com.tuanche.mapper.sem.read.AccountReadMapper;
import com.tuanche.mapper.sem.read.CompanyReadMapper;
import com.tuanche.mapper.sem.read.InfoReadMapper;
import com.tuanche.mapper.sem.write.AccountWriteMapper;
import com.tuanche.mapper.sem.write.CompanyWriteMapper;
import com.tuanche.tj.service.CommonService;

@Controller
public class CompanyController {
		@Autowired
		private CompanyWriteMapper companyWriteMapper;
		@Autowired
		private CompanyReadMapper companyReadMapper;
		@Autowired
		private AccountWriteMapper accountWriteMapper;
		@Autowired
		private AccountReadMapper accountReadMapper;
		@Autowired
		private InfoReadMapper infoReadMapper;
		@Autowired
		private CommonService commonService;
		
		
		@RequestMapping(value="/company/campaign")
		public String companykey(HttpServletRequest request,ModelMap model){
			model.addAttribute("companyList",companyReadMapper.select(null));
			if(request.getMethod().equalsIgnoreCase("post") 
					|| StringUtils.isNotEmpty(request.getParameter("type")) 
					|| StringUtils.isNotEmpty(request.getParameter("totalRows"))
					){
				String type=request.getParameter("type");
				if(StringUtils.isEmpty(type)){
					type="campaign";
				}
				Map<String,String> map=new ConcurrentHashMap<String, String>();
				searchMap(map, request);
				map.put("type",type);
				Map<String,String> infoMap=new HashMap<String, String>();
				infoMap.putAll(GlobalConstants.accountPageMap.get(type));
				infoMap.putAll(map);
				infoMap.put("tableName",type);
				int cpage = RequestUtils.getInt(request, "cpage",0);
				int totalRows = RequestUtils.getInt(request, "totalRows",0);
				if (totalRows <= 0) {
					totalRows=infoReadMapper.count(infoMap);
				}
				Pager page = new Pager(cpage, totalRows,GlobalConstants.PAGE_SIZE);
				page.setSearchCondtions(map);
				page.setLinkUrl("/company/campaign?totalRows="+ totalRows);
				infoMap.put("start",page.getStartRow()+"");
				infoMap.put("pageSize",GlobalConstants.PAGE_SIZE+"");
				model.addAttribute("search",infoMap);
				model.addAttribute("pb",page);
				model.addAttribute("campaignList",infoReadMapper.getInfoList(infoMap));
			}
			return "/company/keyword";
		}
		
		
		@RequestMapping(value="/company/list")
		public String companyindex(HttpServletRequest request,ModelMap model){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("orderStr","id desc");
			model.addAttribute("companyList",companyReadMapper.select(map));
			return "/company/list";
		}
		
		@RequestMapping(value="/company/add")
		public String companyAdd(HttpServletRequest request,ModelMap model,@ModelAttribute(value="company")Company company){
			String method=request.getMethod();
			if(method.equalsIgnoreCase("post")){
				if(company.getId()>0){
					if(request.getParameter("token").equals(getSafeString(company.getId())))
						companyWriteMapper.update(company);
					return "redirect:/company/list";
				}
				Employee employee=(Employee)request.getSession(true).getAttribute(GlobalConstants.SESSION_EMP);
				company.setAddUserId(employee.getId());
				companyWriteMapper.insert(company);
				return "redirect:/company/list";
			}
			if(company.getId()>0){
				model.addAttribute("company",companyReadMapper.find(company.getId()));
				model.addAttribute("token",getSafeString(company.getId()));
			}
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("parentCode","");
			model.addAttribute("companyList",companyReadMapper.select(map));
			return "/company/add";
		}
		
		@RequestMapping(value="/company/accountlist")
		public String accountlist(HttpServletRequest request,ModelMap model){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("orderStr","id desc");
			model.addAttribute("accountList",accountReadMapper.select(map));
			model.addAttribute("businessMap",commonService.getBusinessMap());
			return "/company/accountlist";
		}
		
		@RequestMapping(value="/company/accountadd")
		public String accountAdd(HttpServletRequest request,ModelMap model,
				@ModelAttribute(value="account")Account account){
			String method=request.getMethod();
			if(method.equalsIgnoreCase("post")){
				account.setBizCode(getBizCode(request));
				account.setCompanyCode(companyReadMapper.find(account.getCompanyId()).getCode());
				account.setAccountCode(StringUtils.isEmpty(account.getAccountCode())?
						getAccountCode(account.getCompanyId(),account.getCompanyCode()):account.getAccountCode());
				if(account.getId()>0){
					if(request.getParameter("token").equals(getSafeString(account.getId())))
						accountWriteMapper.update(account);
					return "redirect:/company/accountlist";
				}
				Employee employee=(Employee)request.getSession(true).getAttribute(GlobalConstants.SESSION_EMP);
				account.setAddUserId(employee.getId());
				accountWriteMapper.insert(account);
				return "redirect:/company/accountlist";
			}
			if(account.getId()>0){
				model.addAttribute("account",accountReadMapper.find(account.getId()));
				model.addAttribute("token",getSafeString(account.getId()));
			}
			model.addAttribute("companyList",companyReadMapper.select(new HashMap<String, Object>()));
			model.addAttribute("nameMap",GlobalConstants.businessType);
			model.addAttribute("businessTypeMap",commonService.getBusinessInfo());
			return "/company/accountadd";
		}
		
		private String getBizCode(HttpServletRequest request){
			StringBuffer sb=new StringBuffer();
			for(String s:GlobalConstants.businessType.keySet()){
				sb.append(StringUtils.isEmpty(request.getParameter(s))?0:request.getParameter(s));
			}
			return sb.toString();
		}
		
		private String getAccountCode(int companyId,String code){
			int num=accountReadMapper.getAccountNum(companyId);
			return code+(num==0?"01":(num<9?("0"+(num+1)):num+1));
		}
		
		private String getSafeString(int id){
			return Encriptor.getMD5(id+""+Resources.getWebMessage("update.auth"));
		}
		
		@RequestMapping(value="/common/accountjson")
		@ResponseBody
		public List<Account> accountjson(@RequestParam(value="id")int id){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("companyId",id);
			return accountReadMapper.select(map);
		}
		
		@SuppressWarnings("unchecked")
		private void searchMap(Map<String,String> map,HttpServletRequest request){
			Enumeration<String> names=request.getParameterNames();
			while(names.hasMoreElements()){
				String s=names.nextElement();
				if(StringUtils.isNotEmpty(request.getParameter(s))){
					map.put(s,request.getParameter(s));
				}
			}
		}
		
		@RequestMapping(value="/common/downkeyword")
		public void downkeyword(HttpServletRequest request,HttpServletResponse response){
			List<DownKeyword> keywordList=commonService.getDownKeywordList(StringUtils.strToInt(request.getParameter("accountId")));
			Map<String,String> titleValueMap=new LinkedHashMap<String,String>();
			titleValueMap.put("KeywordId","关键词Id");
			titleValueMap.put("CampaignName","推广计划");
			titleValueMap.put("GroupName","推广单元");
			titleValueMap.put("Title","关键词");
			titleValueMap.put("Url","链接");
			ExportExcel.exportExcel(request.getParameter("name")+"关键词.xls",titleValueMap,keywordList,request,response);
		}
}
