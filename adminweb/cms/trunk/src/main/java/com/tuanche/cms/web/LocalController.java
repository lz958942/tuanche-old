package com.tuanche.cms.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuanche.cms.bean.Brand;
import com.tuanche.cms.bean.CityDist;
import com.tuanche.cms.bean.Common;
import com.tuanche.cms.bean.Style;
import com.tuanche.cms.bean.TreeNode;
import com.tuanche.cms.cheread.CarStyleMapper;
import com.tuanche.cms.cheread.CityMapper;
import com.tuanche.commons.util.StringUtils;




@Controller
@RequestMapping(value = "/local")
public class LocalController extends BaseController{
	
	 @Resource
	    private CityMapper cityMapper;
	 
	 @Autowired
	    private CarStyleMapper carStyleMapper;
	/**
	 * @param model
	 * @param pid地标的父亲id
	 * @return 地标的信息集合
	 */
	@RequestMapping(value = "/ajax/zone")
	public String getCityDist(Model model,HttpServletResponse response) {
		System.out.println("----------------------");
		if(Common.dists == null){
			handle();
		}
		sentResponseData(response, gson.toJson(Common.dists));
		return null;
	}
	
	/** 
	 *
	 * @Title: getClass 
	 * @Description: 获取所有车型类别
	 * @param @param response     
	 * @return void    
	 * @throws 
	 */
	@RequestMapping(value = "/ajax/carstyles")
	public void getCarstyles(HttpServletResponse response) {
		if(Common.carStyles == null){
			handleCarstyles();
		}
	    sentResponseData(response, gson.toJson(Common.carStyles));
	}
	
	private void handleCarstyles(){
		  List<Brand> brands = carStyleMapper.getBrand();
	        List<Style> carStyles = carStyleMapper.getCarStyle();
	        ArrayList<TreeNode> treeNodes = new ArrayList<TreeNode>();
	        HashMap<String, Brand> brandMap = new HashMap<String, Brand>();
	        HashMap<String, Style> styleMap = new HashMap<String, Style>();
	        
	        HashMap<String, Integer> key = new HashMap<String, Integer>();
	        Integer start =10000000;
	        TreeNode p = new TreeNode();
	        p.setName("无关联品牌");
	        p.setId(-1);
	        p.setOpen(true);
	        treeNodes.add(p);
	        for (int i=(int)'a';i<=(int)'z';i++) {
	            String pyPre = (char)i+"";
	            key.put(pyPre, ++start);
	            TreeNode treeNode = new TreeNode();
	            treeNode.setId(start);
	            treeNode.setpId(-1);
	            treeNode.setName(pyPre);
	            treeNode.setLevel(1);
	            treeNodes.add(treeNode);
	        }
	        
	        
	        for(Brand brand : brands ){
	            String pinyin = brand.getPinyin();
	            if(StringUtils.isEmpty(pinyin)){
	                continue;
	            }
	            char prePin = pinyin.charAt(0);
	            Integer integer = key.get(prePin+"");
	            TreeNode treeNode = new TreeNode();
	            treeNode.setId(brand.getId());
	            treeNode.setpId(integer);
	            treeNode.setName(brand.getName());
	            treeNodes.add(treeNode);
	            brandMap.put(brand.getId()+"", brand);
	        }
	        
	        for(Style style :carStyles){
	            TreeNode treeNode = new TreeNode();
	            treeNode.setId(style.getId()+Common.styleIdInc);
	            treeNode.setpId(style.getPid());
	            treeNode.setName(style.getName());
	            treeNodes.add(treeNode);
	            styleMap.put(style.getId()+"", style);
	        }
	        Common.brandMap = brandMap;
	        Common.styleMap = styleMap;
	        Common.carStyles = treeNodes;
	}
	
	private  void handle(){
		 List<CityDist> cityDist = cityMapper.getCityDist(0);
	        Common.cityDist = cityDist;
	        ArrayList<TreeNode> cityTreeNodes = new ArrayList<TreeNode>();
	        HashMap<String, Integer> key = new HashMap<String, Integer>();
	        Integer start =10000;
	        TreeNode p = new TreeNode();
	        p.setName("全国");
	        p.setId(-1);
	        p.setOpen(true);
	        cityTreeNodes.add(p);
	        for (int i=(int)'a';i<=(int)'z';i++) {
	            String pyPre = (char)i+"";
	            key.put(pyPre, ++start);
	            TreeNode treeNode = new TreeNode();
	            treeNode.setId(start);
	            treeNode.setpId(-1);
	            treeNode.setName(pyPre);
	            treeNode.setLevel(1);
	            cityTreeNodes.add(treeNode);
	        }
	        HashMap<String, CityDist> cityDistMap = new HashMap<String,CityDist>();
	        
	        for (CityDist dist : cityDist) {
	            String pyPre = dist.getPyPre();
	            TreeNode treeNode = new TreeNode();
	            treeNode.setId(dist.getLocalid());
	            treeNode.setpId(key.get(pyPre));
	            treeNode.setName(dist.getLocalname());
	            cityTreeNodes.add(treeNode);
	            cityDistMap.put(dist.getLocalid()+"", dist);
	        }
	        Common.cityDistMap = cityDistMap;
	        
	        Common.dists = cityTreeNodes;
	        
	        
	    }
}

