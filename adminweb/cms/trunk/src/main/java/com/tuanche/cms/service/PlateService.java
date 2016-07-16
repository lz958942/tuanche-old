package com.tuanche.cms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.cms.adminread.PlateReadMapper;
import com.tuanche.cms.adminread.ZiXunClassReadMapper;
import com.tuanche.cms.adminwrite.PlateWriteMapper;
import com.tuanche.cms.bean.City;
import com.tuanche.cms.bean.Employee;
import com.tuanche.cms.bean.Plate;
import com.tuanche.cms.bean.ZiXunClass;
import com.tuanche.cms.cheread.CityReadMapper;
import com.tuanche.cms.util.ApplicationUtil;
import com.tuanche.commons.util.Resources;
import com.tuanche.commons.util.StringUtils;

@Service
public class PlateService {
	
	@Autowired
	private PlateReadMapper readMapper;
	
	@Autowired
	private PlateWriteMapper writeMapper;
	
	@Autowired
	private CityReadMapper cityReadMapper;
	
	@Autowired
	private  ZiXunClassReadMapper classReadMapper;
	
	public int addPlate(Plate plate){
		handleAdd(plate);
		return writeMapper.insert(plate);
	}

	public int updatePlate(Plate plate){
		handleUpdate(plate);
		return writeMapper.updateByPrimaryKeySelective(plate);
	}
	
	public List<Plate> getPlateByPage(Plate plate){
		return readMapper.getPlateByPage(plate);
	}
	
	public Plate getPlateById(Integer id){
		return readMapper.selectByPrimaryKey(id);
	}
	
	public List<ZiXunClass> getAllZiXunClass(){
		return classReadMapper.selectAll();
	}
	
	private void handleAdd(Plate plate){
		handlehyperlink(plate);
		if(plate.getType()==Plate.type_teshu){
			plate.setDataType(null);
			plate.setDataTypeClass(null);
		}
		Employee employee = ApplicationUtil.getEmployee();
		plate.setCreateUserId(employee.getId());
		plate.setCreateUserName(employee.getEmpName());
	}
	
	private void handleUpdate(Plate plate){
		handlehyperlink(plate);
		Employee employee = ApplicationUtil.getEmployee();
		plate.setUpdateUserId(employee.getId());
		plate.setUpdateUserName(employee.getEmpName());
	}
	/***
	 * 
	 * Administrator：zhaojl
	 * @param plate
	 */
	private void handlehyperlink(Plate plate){
		String hyperlink = plate.getHyperlink();
		hyperlink = hyperlink == null ?"":hyperlink;
		if(!hyperlink.contains("http://") && Objects.equals(1, plate.getDataType())){//资讯有分类
			if(plate.getDataTypeClass()!=null){
				ZiXunClass zixunClass = classReadMapper.getZixunClassById(plate.getDataTypeClass());
				String url = zixunClass.getUrl();
				if(url!=null && !"".equals(url)&& "-".equals(url.indexOf(0))){
					url = "/zx"+url+"/";
				}else{
					url = url.replace("-", "/");
					url = "/zx-"+url+"/";
				}
				plate.setHyperlink(url);
			}
		}
	}
	/**
	 * 根据城市  拼接板块链接
	 * Administrator：zhaojl
	 * @param plate
	 * @param cityid
	 * @return
	 */
	public List<Plate> getAllPlatehyperlinkByCityId(List<Plate> plateList,Integer cityId){
		List<Plate> plateList111 = new ArrayList<Plate>();
		String cityStr = "";//城市拼音
		if(cityId!=-1){
			City cityVl = cityReadMapper.getCityById(cityId);
			cityStr=cityVl.getPy()+".";
		}else{
			cityStr = "bj.";
		}
		if(plateList!=null){
			for (int i = 0; i <plateList.size(); i++) {
				String cityAtt = cityStr;
				Plate plate = plateList.get(i);
				String hyperlink = plate.getHyperlink();
				if(!StringUtils.isEmpty(hyperlink)&&!hyperlink.contains("http://")&& plate.getDataType()!=null && plate.getDataType()==1&& plate.getDataTypeClass()!=null){//资讯有分类
					ZiXunClass zixunClass = classReadMapper.getZixunClassById(plate.getDataTypeClass());
					if(zixunClass != null && zixunClass.getCityAttr()==0){
						cityAtt = "";
					}
					String allhyperlink = "http://"+cityAtt+Resources.getString("tuanches.url")+hyperlink;
					System.out.println(allhyperlink);
					plate.setHyperlink(allhyperlink);
				}
				plateList111.add(plate);
			}
		}
		return plateList111;
	}
	
}
