package com.tuanche.baseapi.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuanche.basedata.api.CarInfoApi;
import com.tuanche.basedata.entity.CarInfo;
import com.tuanche.basedata.entity.CarPic;

@Controller
@RequestMapping(value = "/carinfo", method = { RequestMethod.POST, RequestMethod.GET })
public class CarInfoServiceController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name = "baseData.CarInfoServiceProxy")
	CarInfoApi carInfoApi;
	
	
	@RequestMapping(value = "/getCarInfoByCid" , method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<CarInfo> getCarInfoByCid(HttpServletRequest request) {
		
		String cid = request.getParameter("cid");
		
		return carInfoApi.getCarInfoByCid(cid);
		
	}

	
	@RequestMapping(value = "/getCarinfoByBid" , method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<CarInfo> getCarinfoByBid(HttpServletRequest request) {
		
		String bid = request.getParameter("bid");
		
		return carInfoApi.getCarInfoByBid(bid);
		
	}
	
	
	@RequestMapping(value = "/getCarPicListByCid" , method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<CarPic> getCarPicListByCid(HttpServletRequest request) {
		String cid = request.getParameter("cid");
		return carInfoApi.getCarPicListByCid(cid);
	}
	
	
}
