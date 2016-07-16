package com.tuanche.cms.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuanche.cms.adminread.TlcityReadMapper;
import com.tuanche.cms.bean.Tlcity;
import com.tuanche.cms.service.FreeService;
import com.tuanche.cms.service.FreeServiceCity;
import com.tuanche.cms.service.FreeServiceYaohao;
import com.tuanche.cms.service.PlateService;

@Controller
@RequestMapping(value = "/free")
public class FreeController extends BaseController{
	
	@Autowired
	private TlcityReadMapper tlcityReadMapper;
	
	@Autowired
	private FreeService freeService;
	@Autowired
	private FreeServiceCity freeService_city;
	@Autowired
	private FreeServiceYaohao serviceYaohao;
	
	
	@Autowired
	private PlateService plateService;
	
	/**
	 * 生成页面
	 * Administrator：zhaojl
	 * @param model
	 * @param tlCityId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/makePageByCityId")
	public void makePageByCityId (Model model,int tlCityId,
			HttpServletRequest request,HttpServletResponse response){
		Tlcity tlcity = tlcityReadMapper.selectByPrimaryKey(tlCityId);
		if(tlcity.getTlcitytype()==Tlcity.type_zixun){//资讯
			freeService.makePaeeByTlcityId(tlcity,request,true);
		}else if(tlcity.getTlcitytype()==Tlcity.type_city){//城市首页
			freeService_city.makePaeeByTlcityId(tlcity, request,true);
		}else if(tlcity.getTlcitytype()==Tlcity.type_yaohao){//摇号
			serviceYaohao.makePaeeByTlcityId(tlcity, request, true);
		}
		Map<String , Object> map = new HashMap<String , Object>();
		JSONArray fromObject = JSONArray.fromObject(map);
		sentResponseData(response,fromObject.toString());
	}
	/**
	 * 预览
	 * Administrator：zhaojl
	 * @param model
	 * @param tlCityId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/viewHtml")
	public void viewHtml (Model model,int tlCityId,
			HttpServletRequest request,HttpServletResponse response){
		Tlcity tlcity = tlcityReadMapper.selectByPrimaryKey(tlCityId);
		String htmlName= "";
		if(tlcity.getTlcitytype()==Tlcity.type_zixun){//资讯
			htmlName = "zixun/"+freeService.makePaeeByTlcityId(tlcity,request,false);
		}else if(tlcity.getTlcitytype()==Tlcity.type_city){//城市首页
			htmlName = "city/"+freeService_city.makePaeeByTlcityId(tlcity, request,false);
		}else if(tlcity.getTlcitytype()==Tlcity.type_yaohao){//摇号
			htmlName = "yaohao/"+	serviceYaohao.makePaeeByTlcityId(tlcity, request, false);
		}
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("Htmlname", htmlName);
		JSONArray fromObject = JSONArray.fromObject(map);
		sentResponseData(response,fromObject.toString());
	}
	
	
	
	/**
	 * 定时器入口
	 * Administrator：zhaojl
	 * @param request
	 * @param response
	 * @param secret
	 * @throws Exception
	 */
	@RequestMapping(value = "/timmingHandle")
	public void timmingHandle(HttpServletRequest request,HttpServletResponse response,int secret ) throws Exception{
		if(secret == 312){
			List<Tlcity> allTlcity = freeService.getAllTlcity(request);
			for (Tlcity tlcity :allTlcity) {
				if(tlcity.getTlcitytype()==Tlcity.type_zixun){
					freeService.makePaeeByTlcityId(tlcity,request,true);
				}else if(tlcity.getTlcitytype()==Tlcity.type_city){
					freeService_city.makePaeeByTlcityId(tlcity, request,true);
				}else if(tlcity.getTlcitytype()==Tlcity.type_yaohao){
					serviceYaohao.makePaeeByTlcityId(tlcity, request, true);
				}
			}
		}

		sentResponseData(response,"生成页面成功");
	}
	
}
