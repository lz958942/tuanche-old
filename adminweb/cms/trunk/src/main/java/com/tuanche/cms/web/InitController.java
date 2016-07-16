package com.tuanche.cms.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuanche.cms.bean.City;
import com.tuanche.cms.dao.InfoDao;
import com.tuanche.cms.util.GlobalConstants;
import com.tuanche.commons.util.StringUtils;

@Controller
public class InitController extends BaseController{
	@Autowired
	private InfoDao infoDao;
	@RequestMapping("/init/city")
	public String initCity(){
		List<City> cityList=infoDao.getCity();
		if(cityList!=null){
			for(City tmp:cityList){
				GlobalConstants.cityMap.put(tmp.getId(), tmp.getDname());
				GlobalConstants.cityAllMap.put(tmp.getId(), tmp);
				GlobalConstants.cityMapPY.put(tmp.getId(),  tmp.getOrderName());
				if(StringUtils.isNotEmpty(tmp.getCityCode())){
                   GlobalConstants.cityCodeToIdMap.put(tmp.getCityCode(), tmp.getId());
				}
			}
		}
		return "";
	}
	
	@RequestMapping("/init/test")
	public String test(){
		File f = new File("E:/Project/cms/src/main/webapp/pic_tmp/adv/20141011/111.jpg");
//		File f = new File("/tmp/1111.jpg");
//		File f1 = new File("/opt/web/cms/pic_tmp/adv/20141011/1111.jpg");
//		File f2 = new File("/tmp/2222.jpg");
		
		if(!f.exists()){
			try {
				f.createNewFile();
//				f1.createNewFile();
//				f2.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
}
