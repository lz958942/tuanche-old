package com.tuanche.smc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuanche.commons.util.StringUtils;
import com.tuanche.smc.common.Common;
import com.tuanche.smc.common.TreeNode;
import com.tuanche.smc.domain.base.Brand;
import com.tuanche.smc.domain.base.Style;
import com.tuanche.smc.persistence.read.che100.CarStyleMapper;

@Repository
public class CarStyleDao {
    @Autowired
    private CarStyleMapper carStyleMapper;
    public void init(){
        
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
            treeNode.setId(style.getBrandId()+"-"+style.getId());
            treeNode.setpId(style.getBrandId());
            treeNode.setName(style.getName());
            treeNodes.add(treeNode);
            styleMap.put(style.getId()+"", style);
        }
        Common.brandMap = brandMap;
        Common.styleMap = styleMap;
        Common.carStyles = treeNodes;
    }
    
}
