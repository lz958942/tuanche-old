package com.tuanche.baseapi.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuanche.baseapi.web.abs.AbstractController;
import com.tuanche.basedata.api.BaseDataApi;
import com.tuanche.basedata.api.EcarBaseDataApi;
import com.tuanche.basedata.entity.BaseModel;
import com.tuanche.basedata.entity.EcarBrand;
import com.tuanche.basedata.entity.EcarModelColor;
import com.tuanche.basedata.entity.EcarStyle;
import com.tuanche.framework.base.entity.DataTransferObject;

@Controller
@RequestMapping(value = "/ecar/basedata",method = { RequestMethod.POST, RequestMethod.GET })
public class EcarBaseDataController extends AbstractController{

	@Resource(name="ecar.ecarBaseDataServiceProxy")
	private EcarBaseDataApi ecarBaseDataApi;
	

	@Resource(name="baseData.baseDataServiceProxy")
	private BaseDataApi baseDataApi;
	
	@RequestMapping(value = "/brand")
	@ResponseBody
	public Object getBrandList(){
		DataTransferObject<List<EcarBrand>> dto = ecarBaseDataApi.getBrandList();
		return dto.toString();
	}
	
	@RequestMapping(value = "/style")
	@ResponseBody
	public Object getStyleList(@RequestParam(value="brandId")Integer brandId){
		DataTransferObject<List<EcarStyle>> dto = ecarBaseDataApi.getCarstyleByBrandId(brandId);
		return dto.toString();
	}
	
	@RequestMapping(value = "/model")
	@ResponseBody
	public Object getModelList(@RequestParam(value="styleId")Integer styleId){
		//DataTransferObject<List<EcarModel>> dto = ecarBaseDataApi.getCarModelByStyleId(styleId);
		DataTransferObject<List<BaseModel>> dto = baseDataApi.getCarModelByStyleId(styleId);
		return dto.toString();
	}
	
	@RequestMapping(value = "/color")
	@ResponseBody
	public Object getModelColor(@RequestParam(value="modelId")Integer modelId){
		
		DataTransferObject<EcarModelColor> dto = ecarBaseDataApi.getCarModelColorByModelId(modelId);
		return dto.toString();
	}
	
	
}
