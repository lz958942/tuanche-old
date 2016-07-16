package com.tuanche.smc.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tuanche.bean.che.UsedCarstyle;
import com.tuanche.console.bean.Employee;
import com.tuanche.smc.domain.base.Brand;
import com.tuanche.smc.domain.base.CityDist;
import com.tuanche.smc.domain.base.NewsClassify;
import com.tuanche.smc.domain.base.Style;

public class Common {
    
    //区分brandid 和styleid 把styleid+ 1百万
    public static Integer styleIdInc = 1000000 ;
    
    public static List<CityDist> cityDist ;
    
    public static ArrayList<TreeNode> dists;
    
    public static ArrayList<TreeNode> carStyles;
    
    public static List<NewsClassify> newsClassifies;
    
    public static HashMap<String, Brand> brandMap;
    
    public static HashMap<String, Style> styleMap;
    
    public static HashMap<String, CityDist> cityDistMap;
    
    public static HashMap<String, NewsClassify> newsClassifyMap;
    
    private static List<Employee> editers;
    
    public  static HashMap<String,Employee> editersMap;
    
    public static List<UsedCarstyle> usedCstyleList;
    
    public static Map<Integer,UsedCarstyle> usedCstyleMap;
    
    //test 60秒更新一次
    private static Integer initTime = 24*60*60*1000;
    
    public static long init;
    
    public static List<Employee> getEditers(){
        if(System.currentTimeMillis()-init>initTime){
            //更新缓存
            ApplicationContext  ac = WebApplicationContextUtils.getWebApplicationContext(ServletRequestHolder.getRequest().getSession().getServletContext());
            try {
                ac.getBean("com.tuanche.smc.dao.EditerDao",com.tuanche.smc.dao.EditerDao.class).init();;
                ac.getBean("com.tuanche.smc.dao.LocaleDao",com.tuanche.smc.dao.LocaleDao.class).init();
                ac.getBean("com.tuanche.smc.dao.NewsClassfityDao",com.tuanche.smc.dao.NewsClassfityDao.class).init();;
                ac.getBean("com.tuanche.smc.dao.CarStyleDao",com.tuanche.smc.dao.CarStyleDao.class).init();
                ac.getBean("com.tuanche.dao.che.UsedCarstyleDao",com.tuanche.dao.che.UsedCarstyleDao.class).init();
                System.out.println("all fush!");
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
        return editers;
    }

    public static void setEditers(List<Employee> editers) {
        Common.editers = editers;
    }
    
    
}
