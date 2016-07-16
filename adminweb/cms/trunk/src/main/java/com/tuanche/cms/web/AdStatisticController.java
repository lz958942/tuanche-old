package com.tuanche.cms.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuanche.cms.bean.AdStatistic;
import com.tuanche.cms.dao.AdStatisticDao;
import com.tuanche.cms.util.GlobalConstants;
import com.tuanche.cms.util.Pager;
import com.tuanche.cms.util.RequestUtils;

@Controller
@RequestMapping(value="/adStatistic")

public class AdStatisticController {
	@Autowired
	AdStatisticDao adStatisticDao;
	@RequestMapping(value="/adStatisticList")
	public String adStatisticList(HttpServletRequest request,Model model){
		
		AdStatistic adStatistic=cerateStatistic(request);
		model.addAttribute("channelMap", GlobalConstants.channelMap);
		model.addAttribute("adTypeMap", GlobalConstants.adTypeMap);
		model.addAttribute("cityMap", GlobalConstants.cityMap);
		int cpage = RequestUtils.getInt(request, "cpage",0);
		int totalRows = RequestUtils.getInt(request, "totalRows",0);
		totalRows=adStatisticDao.count(adStatistic);
		int start=(cpage>0?cpage-1:cpage);
		adStatistic.setStart(start*10);
		adStatistic.setLimit(10);
		model.addAttribute("pb",new Pager(cpage, totalRows,10));
		List<AdStatistic> adStatisticList=adStatisticDao.queryAdStatistic(adStatistic);
		model.addAttribute("adStatistic", adStatistic);
		model.addAttribute("list", adStatisticList);
		return "/advertising/ad_statistic";
	}
	AdStatistic cerateStatistic(HttpServletRequest request){
		AdStatistic adStatistic=new AdStatistic();
		adStatistic.setCityId(RequestUtils.getInt(request, "city", -1));
		adStatistic.setChannel(RequestUtils.getInt(request, "channel", -1));
		adStatistic.setAdType(RequestUtils.getInt(request, "adType", -1));
		adStatistic.setDate(RequestUtils.getString(request, "date"));
		return adStatistic;
	}
}
