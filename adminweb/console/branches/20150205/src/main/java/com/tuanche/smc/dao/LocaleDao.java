package com.tuanche.smc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tuanche.smc.common.Common;
import com.tuanche.smc.common.TreeNode;
import com.tuanche.smc.domain.base.CityDist;
import com.tuanche.smc.persistence.read.che100.CityMapper;

@Repository
public class LocaleDao {

    @Resource
    private CityMapper cityMapper;




    public List<CityDist> getCityDist(int pid) {
        return cityMapper.getCityDist(pid);
    }

    public void init() {
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
