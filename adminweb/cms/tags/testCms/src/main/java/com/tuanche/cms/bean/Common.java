package com.tuanche.cms.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;



public class Common {
    
    //区分brandid 和styleid 把styleid+ 1百万
    public static Integer styleIdInc = 1000000 ;
    
    public static List<CityDist> cityDist ;
    
    public static ArrayList<TreeNode> dists;
    
    public static ArrayList<TreeNode> carStyles;
    
    
    public static HashMap<String, Brand> brandMap;
    
    public static HashMap<String, Style> styleMap;
    
    public static HashMap<String, CityDist> cityDistMap;
    
    
    private static List<Employee> editers;
    
    public  static HashMap<String,Employee> editersMap;
    
    //test 60秒更新一次
    private static Integer initTime = 24*60*60*1000;
    
    public static long init;
    
    

    public static void setEditers(List<Employee> editers) {
        Common.editers = editers;
    }
    
    
}
