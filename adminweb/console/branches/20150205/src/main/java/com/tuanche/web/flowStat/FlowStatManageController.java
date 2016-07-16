package com.tuanche.web.flowStat;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tuanche.bean.che.Search;
import com.tuanche.bean.fcharts.FusionCharts;
import com.tuanche.bean.sem.DayStatistics;
import com.tuanche.bean.tongji.FlowStat;
import com.tuanche.bean.tongji.RateDomain;
import com.tuanche.bean.tongji.RecordStat;
import com.tuanche.console.util.ExcelBrandCostImport;
import com.tuanche.console.util.ExportExcel;
import com.tuanche.console.util.RequestUtils;
import com.tuanche.service.tongji.FlowStatService;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.web.controller.BaseController;

@Controller
@RequestMapping("/flowStatManage")
public class FlowStatManageController extends BaseController {
	private Logger logger = Logger.getLogger(FlowStatManageController.class);
	
	@Autowired
	private FlowStatService flowStatService;

	private List<String> types;
	@PostConstruct
	public void init() {
		types = new ArrayList<String>();
		types.add("seo");
		types.add("sem");
	}
	
	@RequestMapping("/toAdd")
	public String toAdd(Model model) {
		return "flowStat/addFlow";
	}
	
	@RequestMapping(value="/addFlowStat", method = RequestMethod.POST)
	public String addFlowStat(@ModelAttribute FlowStat flowStat, HttpServletRequest request, Model model) {
		flowStatService.addFlowStat(flowStat);
		return "redirect:/flowStatManage/flowStatView";
	}

	@RequestMapping(value="/updateFlowStat", method = RequestMethod.POST)
	public String updateFlowStat(@ModelAttribute FlowStat flowStat, HttpServletRequest request, Model model) {
		flowStatService.updateFlowStat(flowStat);
		return "redirect:/flowStatManage/flowStatView";
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(@RequestParam int id, Model model) {
		FlowStat flowStat = flowStatService.queryFlowStatById(id);
		model.addAttribute("flow", flowStat);
		return "flowStat/flowDetail";
	}
	
	@RequestMapping("/flowStatView")
	public String flowStatView(/*@ModelAttribute FlowStat flowStat, */
								Model model, @Param(value = "flowParam") FlowStat flowParam,HttpServletRequest request) {
		logger.debug("startDate: " + flowParam.getStartDate() + ", endDate: " + flowParam.getEndDate() + ", domainCondition: " + flowParam.getDomain() + ", dataTypeSelected: " + flowParam.getDataType());
		Pagination page=flowParam.getPage();
		if(null==page){page= new Pagination();}
		Pagination.threadLocal.set(page);
		if(null != flowParam) {
			
		}
		//默认设置的开始时间为最近七天
		if(null==flowParam.getStartDate()&&null==flowParam.getEndDate()){
			flowParam.setStartDate(getCurrentTime(8));
			flowParam.setEndDate(getCurrentTime(1));
		}
		List<FlowStat> lists=new ArrayList<FlowStat>();
		List<FlowStat> flowStatList=new ArrayList<FlowStat>();
		String from="";
		if(request.getParameter("from")!=null){
			from=request.getParameter("from");
		}
		FlowStat flow=new FlowStat();
		int pv=0;
		int uv=0;
		int tpv=0;
		int tuv=0;
		int mpv=0;
		int muv=0;
		if(from.equals("百度统计")){
			lists=flowStatService.queryFlowStatList(flowParam);
			flowStatList=flowStatService.queryFlowStatListByPage(flowParam);
			//设置  seo    sem    的  pv  uv  数据
			for(FlowStat f:lists){
				pv+=f.getPv();
				uv+=f.getUv();
				
				if(f.getDataType().equals("seo")){
					f.setTpv(f.getPv());
					f.setTuv(f.getUv());
				}
				if(f.getDataType().equals("sem")){
					f.setMpv(f.getPv());
					f.setMuv(f.getUv());
				}
				if(f.getDataType().length()>1){
					f.setFrom("百度统计");
				}else if(f.getDataType().length()==1){
					f.setFrom("网页日志");
				}
				tpv+=f.getTpv();
				tuv+=f.getTuv();
				mpv+=f.getMpv();
				muv+=f.getMuv();
			}
			for(FlowStat f:flowStatList){
				
				if(f.getDataType().equals("seo")){
					f.setTpv(f.getPv());
					f.setTuv(f.getUv());
				}
				if(f.getDataType().equals("sem")){
					f.setMpv(f.getPv());
					f.setMuv(f.getUv());
				}
				if(f.getDataType().length()>1){
					f.setFrom("百度统计");
				}else if(f.getDataType().length()==1){
					f.setFrom("网页日志");
				}
			}
			if(lists.size()>0){
			flow.setPv(pv/lists.size());
			flow.setUv(uv/lists.size());
			flow.setTpv(tpv/lists.size());
			flow.setTuv(tuv/lists.size());
			flow.setMpv(mpv/lists.size());
			flow.setMuv(muv/lists.size());
			}
			Pagination.threadLocal.get().setTotalCount(flowStatService.queryFlowStatList(flowParam).size());
		}else if(from.equals("网页日志")){
			
			lists=flowStatService.queryPvtotalList(flowParam);
			String tp=flowParam.getDataType();
			List<FlowStat> listseo=new ArrayList<FlowStat>();
			List<FlowStat> listsem=new ArrayList<FlowStat>();
			flowParam.setDataType("seo");
			listseo=flowStatService.queryPvtotalList(flowParam);
			flowParam.setDataType("sem");
			listsem=flowStatService.queryPvtotalList(flowParam);
			flowParam.setDataType(tp);
			for(FlowStat f:lists){
				pv+=f.getPv();
				uv+=f.getUv();
				
				if("1".equals(f.getDataType())){
					f.setTpv(f.getPv());
					f.setTuv(f.getUv());
				}
				if("2".equals(f.getDataType())){
					f.setMpv(f.getPv());
					f.setMuv(f.getUv());
				}
				if(f.getDataType().length()>1){
					f.setFrom("百度统计");
				}else if(f.getDataType().length()==1){
					f.setFrom("网页日志");
				}

			}
			//设置  seo    sem    的  pv  uv  数据
			flowStatList=flowStatService.queryPvtotalListByPage(flowParam);
			for(FlowStat ff:listseo){
				tpv+=ff.getPv();
				tuv+=ff.getUv();
				
			}
			
			for(FlowStat ff:listsem){
				mpv+=ff.getPv();
				muv+=ff.getUv();
				}
			for(FlowStat f:flowStatList){
				
				if("1".equals(f.getDataType())){
					f.setTpv(f.getPv());
					f.setTuv(f.getUv());
				}
				if("2".equals(f.getDataType())){
					f.setMpv(f.getPv());
					f.setMuv(f.getUv());
				}
				if(f.getDataType().length()>1){
					f.setFrom("百度统计");
				}else if(f.getDataType().length()==1){
					f.setFrom("网页日志");
				}
				for(FlowStat ff:listseo){
					if(f.getDate().equals(ff.getDate())&&ff.getDataType().equals("1")&&!(f.getFrom().equals("百度统计"))){
						f.setTpv(ff.getPv());
						f.setTuv(ff.getUv());
					}
				}
				for(FlowStat ff:listsem){
					if(f.getDate().equals(ff.getDate())&&ff.getDataType().equals("2")&&!(f.getFrom().equals("百度统计"))){
						f.setMpv(ff.getPv());
						f.setMuv(ff.getUv());
					}
				
				}
			}
			Pagination.threadLocal.get().setTotalCount(flowStatService.queryPvtotalList(flowParam).size());
			if(lists.size()>0){
				flow.setPv(pv/lists.size());
				flow.setUv(uv/lists.size());
				flow.setTpv(tpv/lists.size());
				flow.setTuv(tuv/lists.size());
				flow.setMpv(mpv/lists.size());
				flow.setMuv(muv/lists.size());
				}
		}else{
			lists=flowStatService.seleAllList(flowParam);
			String tp=flowParam.getDataType();
			List<FlowStat> listseo=new ArrayList<FlowStat>();
			List<FlowStat> listsem=new ArrayList<FlowStat>();
			flowParam.setDataType("seo");
			listseo=flowStatService.queryPvtotalList(flowParam);
			flowParam.setDataType("sem");
			listsem=flowStatService.queryPvtotalList(flowParam);
			flowParam.setDataType(tp);
			//设置seo  sem 的   pv  uv  数据
			Integer pageNo=page.getPageNo();
			Integer pageSize=page.getPageSize();
			flowParam.setStar(pageSize*(pageNo-1));
			flowParam.setEn(pageSize);
			flowStatList=flowStatService.selectAllList(flowParam);
			for(FlowStat f:lists){
				pv+=f.getPv();
				uv+=f.getUv();
				
				if("1".equals(f.getDataType())||"seo".equals(f.getDataType())){
					f.setTpv(f.getPv());
					f.setTuv(f.getUv());
				}
				if("2".equals(f.getDataType())||"sem".equals(f.getDataType())){
					f.setMpv(f.getPv());
					f.setMuv(f.getUv());
				}
				if(f.getDataType().length()>1){
					f.setFrom("百度统计");
				}else if(f.getDataType().length()==1){
					f.setFrom("网页日志");
				}
				tpv+=f.getTpv();
				tuv+=f.getTuv();
				mpv+=f.getMpv();
				muv+=f.getMuv();
			}
			for(FlowStat f:flowStatList){

				if("1".equals(f.getDataType())||"seo".equals(f.getDataType())){
					f.setTpv(f.getPv());
					f.setTuv(f.getUv());
				}
				if("2".equals(f.getDataType())||"sem".equals(f.getDataType())){
					f.setMpv(f.getPv());
					f.setMuv(f.getUv());
				}
				if(f.getDataType().length()>1){
					f.setFrom("百度统计");
				}else if(f.getDataType().length()==1){
					f.setFrom("网页日志");
				}
				for(FlowStat ff:listseo){
					if(f.getDate().equals(ff.getDate())&&ff.getDataType().equals("1")&&!(f.getFrom().equals("百度统计"))){
						f.setTpv(ff.getPv());
						f.setTuv(ff.getUv());
					}
				}
				for(FlowStat ff:listsem){
					if(f.getDate().equals(ff.getDate())&&ff.getDataType().equals("2")&&!(f.getFrom().equals("百度统计"))){
						f.setMpv(ff.getPv());
						f.setMuv(ff.getUv());
					}
				}
			}
			for(FlowStat ff:listseo){
				tpv+=ff.getPv();
				tuv+=ff.getUv();
				
			}
			
			for(FlowStat ff:listsem){
				mpv+=ff.getPv();
				muv+=ff.getUv();
				}
			page.setList(flowStatList);
			page.setTotalCount(lists.size());
			Pagination.threadLocal.set(page);
			if(lists.size()>0){
				flow.setPv(pv/lists.size());
				flow.setUv(uv/lists.size());
				flow.setTpv(tpv/lists.size());
				flow.setTuv(tuv/lists.size());
				flow.setMpv(mpv/lists.size());
				flow.setMuv(muv/lists.size());
				}
		}				
		model.addAttribute("flowStatList", flowStatList);
		model.addAttribute("flowParam", flowParam);
		model.addAttribute("domains", flowStatService.selectDomain());
		/*
		model.addAttribute("startDate", flowStat.getStartDate());
		model.addAttribute("endDate", flowStat.getEndDate());
		model.addAttribute("domainCondition", flowStat.getDomain());
		model.addAttribute("dataTypeSelected", flowStat.getDataType());
		*/
		model.addAttribute("flow",flow);
		model.addAttribute("page",Pagination.threadLocal.get());
		
		return "flowStat/flowStatView";
	}

	@RequestMapping(value = "/deleteFlowStat")
	public /*@ResponseBody Model*/ void deleteFlowStat(@RequestParam int id, HttpServletResponse response)
	{
		logger.debug("deleteFlowStat id: " + id);
		boolean resp = false;
		try {
			flowStatService.deleteFlowStat(id);
			resp = true;
		} catch(Exception e) {
			logger.warn(e.getMessage(), e.fillInStackTrace());
		}

		logger.debug("resp: " + resp);

		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(resp ? "删除成功" : "删除失败");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		model.addAttribute("resp", resp ? "删除成功" : "删除失败");
//		return model;
	}

	@RequestMapping(value = "/uploadExcel", method = RequestMethod.POST)
	public String excleImport(HttpServletRequest request, @RequestParam String startDateExcel, @RequestParam String endDateExcel) throws Exception {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile fileExcel = multipartRequest.getFile("file");
		logger.debug("excel filename: " + fileExcel.getOriginalFilename() + ",startDate: " + startDateExcel + ",endDate: " + endDateExcel);
		if((startDateExcel != null && !startDateExcel.equals("")) || (endDateExcel != null && !endDateExcel.equals(""))) {
			flowStatService.deleteFlowStatByDate(startDateExcel, endDateExcel);
		}
		flowStatService.uploadExcel(fileExcel.getInputStream());
		return "redirect:/flowStatManage/flowStatView";
	}
	
	@RequestMapping(value = "/flowJsonData")
	public void flowJsonData(@RequestParam String startDate, 
							 @RequestParam String endDate, 
							 @RequestParam String domain, 
							 @RequestParam String dataType,
							 @RequestParam String from,
							 @RequestParam String type,
							 HttpServletResponse response) {
		try {
			FusionCharts chart = flowStatService.getChartDataByTypes(startDate, endDate, domain, dataType,from,type, types);
			response.setCharacterEncoding("utf-8"); 
			response.setContentType("text/html; charset=UTF-8"); 
			JSONObject jsonObj = JSONObject.fromObject(chart);
			response.getWriter().print(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/isExist")
	public String isExist(HttpServletRequest request, HttpServletResponse response) {
		FlowStat flowStat = new FlowStat();
		String id = request.getParameter("id");
		if(id != null && !(id.equals(""))) {
			flowStat.setId(Integer.parseInt(id));
		}
//		flowStat.setDate(request.getParameter("date"));
		flowStat.setPv(Integer.parseInt(request.getParameter("pv")));
		flowStat.setUv(Integer.parseInt(request.getParameter("uv")));
		flowStat.setDomain(request.getParameter("domain"));
		flowStat.setDate(request.getParameter("date"));

		int nums = flowStatService.isExist(flowStat);
		logger.debug("exist nums: " + nums);
		try {
			response.getWriter().print(nums);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 下载流量统计
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value="/download")
	public void ratedownload(HttpServletRequest request,HttpServletResponse response,ModelMap model,@Param("flowStat")FlowStat flowStat){
		Map<String,String> titleValueMap=new LinkedHashMap<String,String>();
		
		List<FlowStat> list=new ArrayList<FlowStat>();
		String from="";
		if(null!=flowStat.getFrom()){
			from=flowStat.getFrom();
		}
		FlowStat flow=new FlowStat();
		int pv=0;
		int uv=0;
		int tpv=0;
		int tuv=0;
		int mpv=0;
		int muv=0;
		if("百度统计".equals(from)){
			flow.setFrom("百度统计");
			list=flowStatService.queryFlowStatList(flowStat);
			for(FlowStat f:list){
				pv+=f.getPv();
				uv+=f.getUv();
				if(f.getDataType().equals("seo")){
					f.setTpv(f.getPv());
					f.setTuv(f.getUv());
				}
				if(f.getDataType().equals("sem")){
					f.setMpv(f.getPv());
					f.setMuv(f.getUv());
				}
				if(f.getDataType().length()>1){
					f.setFrom("百度统计");
				}else if(f.getDataType().length()==1){
					f.setFrom("网页日志");
				}
				tpv+=f.getTpv();
				tuv+=f.getTuv();
				mpv+=f.getMpv();
				muv+=f.getMuv();
			}
			if(list.size()>0){
				flow.setPv(pv/list.size());
				flow.setUv(uv/list.size());
				flow.setTpv(tpv/list.size());
				flow.setTuv(tuv/list.size());
				flow.setMpv(mpv/list.size());
				flow.setMuv(muv/list.size());
				}
		}else if("网页日志".equals(from)){
			flow.setFrom("网页日志");
			list=flowStatService.queryPvtotalList(flowStat);
			String tp=flowStat.getDataType();
			List<FlowStat> listseo=new ArrayList<FlowStat>();
			List<FlowStat> listsem=new ArrayList<FlowStat>();
			flowStat.setDataType("seo");
			listseo=flowStatService.queryPvtotalList(flowStat);
			flowStat.setDataType("sem");
			listsem=flowStatService.queryPvtotalList(flowStat);
			flowStat.setDataType(tp);
			for(FlowStat f:list){
				pv+=f.getPv();
				uv+=f.getUv();
				if("1".equals(f.getDataType())){
					f.setTpv(f.getPv());
					f.setTuv(f.getUv());
				}
				if("2".equals(f.getDataType())){
					f.setMpv(f.getPv());
					f.setMuv(f.getUv());
				}
				if(f.getDataType().length()>1){
					f.setFrom("百度统计");
				}else if(f.getDataType().length()==1){
					f.setFrom("网页日志");
				}
				
				for(FlowStat ff:listseo){
					if(f.getDate().equals(ff.getDate())&&ff.getDataType().equals("1")&&!(f.getFrom().equals("百度统计"))){
						f.setTpv(ff.getPv());
						f.setTuv(ff.getUv());
					}
				}
				for(FlowStat ff:listsem){
					if(f.getDate().equals(ff.getDate())&&ff.getDataType().equals("2")&&!(f.getFrom().equals("百度统计"))){
						f.setMpv(ff.getPv());
						f.setMuv(ff.getUv());
					}
				}
				tpv+=f.getTpv();
				tuv+=f.getTuv();
				mpv+=f.getMpv();
				muv+=f.getMuv();
				}
			if(list.size()>0){
				flow.setPv(pv/list.size());
				flow.setUv(uv/list.size());
				flow.setTpv(tpv/list.size());
				flow.setTuv(tuv/list.size());
				flow.setMpv(mpv/list.size());
				flow.setMuv(muv/list.size());
				}
		}else{
			flow.setFrom("全部数据");
			list=flowStatService.seleAllList(flowStat);
			String tp=flowStat.getDataType();
			List<FlowStat> listseo=new ArrayList<FlowStat>();
			List<FlowStat> listsem=new ArrayList<FlowStat>();
			flowStat.setDataType("seo");
			listseo=flowStatService.queryPvtotalList(flowStat);
			flowStat.setDataType("sem");
			listsem=flowStatService.queryPvtotalList(flowStat);
			flowStat.setDataType(tp);
			//
			for(FlowStat f:list){
				pv+=f.getPv();
				uv+=f.getUv();
				if("1".equals(f.getDataType())||"seo".equals(f.getDataType())){
					f.setTpv(f.getPv());
					f.setTuv(f.getUv());
				}
				if("2".equals(f.getDataType())||"sem".equals(f.getDataType())){
					f.setMpv(f.getPv());
					f.setMuv(f.getUv());
				}
				if(f.getDataType().length()>1){
					f.setFrom("百度统计");
				}else if(f.getDataType().length()==1){
					f.setFrom("网页日志");
				}	

				for(FlowStat ff:listseo){
					if(f.getDate().equals(ff.getDate())&&ff.getDataType().equals("1")&&!(f.getFrom().equals("百度统计"))){
						f.setTpv(ff.getPv());
						f.setTuv(ff.getUv());
					}
				}
				for(FlowStat ff:listsem){
					if(f.getDate().equals(ff.getDate())&&ff.getDataType().equals("2")&&!(f.getFrom().equals("百度统计"))){
						f.setMpv(ff.getPv());
						f.setMuv(ff.getUv());
					}
				}
				tpv+=f.getTpv();
				tuv+=f.getTuv();
				mpv+=f.getMpv();
				muv+=f.getMuv();
				}
			if(list.size()>0){
				flow.setPv(pv/list.size());
				flow.setUv(uv/list.size());
				flow.setTpv(tpv/list.size());
				flow.setTuv(tuv/list.size());
				flow.setMpv(mpv/list.size());
				flow.setMuv(muv/list.size());
				}
		}
		flow.setDate("日均");
		list.add(flow);
		titleValueMap.put("Date","时间");
		if(null==flowStat.getDataType()){
			if("pv".equals(flowStat.getType())||null==flowStat.getType()){
			titleValueMap.put("Pv","总Pv");
			}
			if("uv".equals(flowStat.getType())||null==flowStat.getType()){
			titleValueMap.put("Uv","总Uv");	
			}
		}
		if("seo".equals(flowStat.getDataType())||null==flowStat.getDataType()){
			if("pv".equals(flowStat.getType())||null==flowStat.getType()){
		titleValueMap.put("Tpv","SEOPV");
			}
			if("uv".equals(flowStat.getType())||null==flowStat.getType()){
		titleValueMap.put("Tuv","SEOUV");
			}
		}
		if("sem".equals(flowStat.getDataType())||null==flowStat.getDataType()){
			if("pv".equals(flowStat.getType())||null==flowStat.getType()){
		titleValueMap.put("Mpv","SEMPV");
			}
			if("uv".equals(flowStat.getType())||null==flowStat.getType()){
		titleValueMap.put("Muv","SEMUV");
			}
		}
		titleValueMap.put("From","数据来源");
		ExportExcel.exportExcel(new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xls",titleValueMap,list,request,response);
	}
	/**
	 * 获得系统当前时间
	 * @return
	 */
	  public static String getCurrentTime(Integer i) {  
	        String returnStr = null;  
	        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");  
	        Date date = new Date(System.currentTimeMillis()-24*60*60*1000*i);  
	        returnStr = f.format(date);  
	        return returnStr;  
	    } 
}
