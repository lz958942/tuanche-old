package com.tuanche.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.cms.adminread.ContentReadMapper;
import com.tuanche.cms.adminread.TemplateReadMapper;
import com.tuanche.cms.bean.Content;
import com.tuanche.cms.bean.Plate;
import com.tuanche.cms.bean.Template;
import com.tuanche.cms.bean.Tlcity;
import com.tuanche.cms.cheread.CityReadMapper;
import com.tuanche.cms.util.FreemarkerUtil;
import com.tuanche.cms.util.Resources;

@Service
public class FreeServiceYaohao {
	@Autowired
	private FreeService freeService;
	@Autowired 
	private FreeServiceCity freeService_city;
	@Autowired
	private TemplateReadMapper templateRead;
	@Autowired
	private CityReadMapper cityReadMapper;
	@Autowired
	private ContentReadMapper contentReadMapper;
	
	public String makePaeeByTlcityId(Tlcity city,HttpServletRequest request,boolean uploadFTP){
		String htmlName="";
		Map<String ,Object> map = new HashMap<String, Object>();
		//城市
		Integer cityId = city.getCityId();
		com.tuanche.cms.bean.City cityVl = cityReadMapper.getCityById(cityId==-1?10:cityId);
		map.put("city", cityVl);
		//获取城市模板
		Template cityTemplate = templateRead.selectByPrimaryKey(city.getTid());
		//所有板块
		List<Plate> plateByCityId = freeService.getPlateByCityId(city,3);
		for (Plate p:plateByCityId) {
			map.put(p.getLabel(), p);//通过板块，得到板块标题  和板块的模板地址 等
			List<Content> contentList = freeService.getContentByPlateId(p,cityId);//可能p 是全国的板块，所以要传 城市id
			if(contentList!=null && contentList.size() >0){
				map.put(p.getLabel()+"_contentList", contentList);
			}
		}
		//摇号查询加  
		String yh_weight = Resources.getString("yh_weight");
		if(yh_weight.indexOf(cityId+",") != -1){
			map.put("yh_weight", true);
		}
		//头尾公用数据
		map = freeService.initData(map, cityId,3);
		//根据城市名生成静态文件
		htmlName =  cityVl.getPy()+"_yh.html";
		if(cityId==-1){//全国的
			htmlName = "www_yh.html";
		}
		try {
			FreemarkerUtil.makeHtml(map, cityTemplate.getTemplatePath(), htmlName,request,city.getTlcitytype());
			if(uploadFTP){
				freeService.uploadFtp(request, htmlName ,city.getTlcitytype());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return htmlName;
	}
}
