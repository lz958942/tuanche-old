package com.tuanche.web.recordStat;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tuanche.bean.fcharts.FusionCharts;
import com.tuanche.bean.tongji.FlowStat;
import com.tuanche.bean.tongji.RecordStat;
import com.tuanche.service.tongji.RecordStatService;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.web.controller.BaseController;

@Controller
@RequestMapping("/recordStatManage")
public class RecordStatManageController extends BaseController {
	private Logger logger = Logger.getLogger(RecordStatManageController.class);
	
	@Autowired
	private RecordStatService recordStatService;
	
	@RequestMapping("/toAdd")
	public String toAdd(Model model) {
		return "recordStat/addRecord";
	}
	
	@RequestMapping(value="/addRecordStat", method = RequestMethod.POST)
	public String addRecordStat(@ModelAttribute RecordStat recordStat, HttpServletRequest request, Model model) {
		logger.debug("索引量: " + recordStat.getIndexNumber() + 
					", 搜索引擎: " + recordStat.getQueryEngine() + 
					", 网站site收录: " + recordStat.getSiteRecord());
		recordStatService.addRecordStat(recordStat);
		return "redirect:/recordStatManage/recordStatView";
	}

	@RequestMapping(value="/updateRecordStat", method = RequestMethod.POST)
	public String updateRecordStat(@ModelAttribute RecordStat recordStat, HttpServletRequest request, Model model) {
		recordStatService.updateRecordStat(recordStat);
		return "redirect:/recordStatManage/recordStatView";
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(@RequestParam int id, Model model) {
		RecordStat recordStat = recordStatService.queryRecordStatById(id);
		model.addAttribute("record", recordStat);
		return "recordStat/recordDetail";
	}
	
	@RequestMapping("/recordStatView")
	public String recordStatView(@ModelAttribute RecordStat recordStat, Model model) {
		logger.debug("startDate: " + recordStat.getStartDate() + ", endDate: " + recordStat.getEndDate());
		if(null != recordStat) {
			Pagination.threadLocal.set(recordStat.getPage());
		}
		List<RecordStat> list = recordStatService.queryRecordStatListByPage(recordStat);
		model.addAttribute("recordStatList", list);
		model.addAttribute("startDate", recordStat.getStartDate());
		model.addAttribute("endDate", recordStat.getEndDate());
		model.addAttribute("page", Pagination.threadLocal.get());
		
		return "recordStat/recordStatView";
	}

	@RequestMapping(value = "/deleteRecordStat")
	public /*@ResponseBody Model*/ void deleteRecordStat(@RequestParam int id, HttpServletResponse response)
	{
		logger.debug("deleteRecordStat id: " + id);
		boolean resp = false;
		try {
			recordStatService.deleteRecordStat(id);
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
	
	@RequestMapping(value = "/flowJsonData")
	public void flowJsonData(@RequestParam String startDate, 
							 @RequestParam String endDate, 
							 HttpServletResponse response) {
		try {
			FusionCharts chart = recordStatService.getChartDataByTypes(startDate, endDate);
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
		RecordStat recordStat = new RecordStat();
		String id = request.getParameter("id");
		if(id != null && !(id.equals(""))) {
			recordStat.setId(Integer.parseInt(id));
		}
		recordStat.setDate(request.getParameter("date"));
		recordStat.setSiteRecord(Integer.parseInt(request.getParameter("siteRecord")));
		recordStat.setIndexNumber(Integer.parseInt(request.getParameter("indexNumber")));
		recordStat.setQueryEngine(request.getParameter("queryEngine"));
		logger.debug("id: " + id + ",date: " + request.getParameter("date") + ",siteRecord: " + request.getParameter("siteRecord") + ",indexNumber: " + request.getParameter("indexNumber") + ",queryEngine: " + request.getParameter("queryEngine"));

		int nums = recordStatService.isExist(recordStat);
		try {
			response.getWriter().print(nums);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
