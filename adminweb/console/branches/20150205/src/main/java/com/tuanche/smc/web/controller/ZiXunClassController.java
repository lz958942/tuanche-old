package com.tuanche.smc.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuanche.commons.util.StringUtils;
import com.tuanche.smc.common.Common;
import com.tuanche.smc.common.page.impl.Pagination;
import com.tuanche.smc.dao.NewsClassfityDao;
import com.tuanche.smc.domain.base.NewsClassify;

@Controller
@RequestMapping(value = "/zixunclass")
public class ZiXunClassController {
    @Qualifier("com.tuanche.smc.dao.NewsClassfityDao")
    @Autowired
    private NewsClassfityDao classdao;

    /** 
    * @author yangzs
    * @Title: classHome 
    * @Description: home页
    * @param  model
    * @param  classifySearch
    * @param  request
    * @param      
    * @return String    
    * @throws 
    */
    @RequestMapping(value = "/home")
    public String classHome(Model model, NewsClassify classifySearch, HttpServletRequest request) {
        Integer pageno =  (Integer) request.getAttribute("page.pageNo");
        Pagination page = classifySearch.getPage();
        if(pageno!=null&&pageno>0){
            if(page==null){
                page = new Pagination();
            }
            page.setPageNo( pageno);
        }
        Pagination.threadLocal.set(page);
        List<NewsClassify> newsClassfity = classdao.getNewsClassfityByPage();
        model.addAttribute("classes", newsClassfity);
        model.addAttribute("action", "/zixunclass/home");
        model.addAttribute("msg", request.getAttribute("msg"));
        model.addAttribute("page", Pagination.threadLocal.get());
        return "zixun/list";
    }

    /** 
    * @author yangzs
    * @Title: classSearch 
    * @Description: 搜索资讯
    * @param  model
    * @param  classifySearch
    * @param      
    * @return String    
    * @throws 
    */
    @RequestMapping(value = "/search")
    public String classSearch(Model model, NewsClassify classifySearch) {
        
        Pagination page = classifySearch.getPage();
        Pagination.threadLocal.set(page);
        ArrayList<String> cons = new ArrayList<String>();
        Integer id = classifySearch.getId();
        boolean byId = id != null && id > 0;
        List<NewsClassify> newsClassfity = null;
        //搜索条件为串联
        if(!byId) {
            if (StringUtils.isNotEmpty(classifySearch.getName())) {
                cons.add(" name like '%" + classifySearch.getName() + "%'");
            }
            if (classifySearch.getStatus() > -1) {
                cons.add("status = " + classifySearch.getStatus());
            }
            if (classifySearch.getCityAttr() > -1) {
                cons.add("city_attr = " + classifySearch.getCityAttr());
            }
            if (classifySearch.getpId() != null && classifySearch.getpId() > 0) {
                cons.add("pid = " + classifySearch.getpId());
            }
            
            if (StringUtils.isNotEmpty(classifySearch.getUrl())) {
                cons.add("url like '%" + classifySearch.getUrl() + "%'");
            }
            
            if(StringUtils.isNotEmpty(classifySearch.getKeyword())){
                cons.add("keyword like '%" + classifySearch.getKeyword() + "%'");
            }
            if(StringUtils.isNotEmpty(classifySearch.getTitle())){
                cons.add("title like '%" + classifySearch.getTitle() + "%'");
            }
            
            if (cons.size() == 0) {
                newsClassfity = classdao.getNewsClassfityByPage();
            } else {
                newsClassfity = classdao.selectClassfityByPage(cons);
            }
            model.addAttribute("class", classifySearch);
            
        }else{
            newsClassfity = classdao.getNewsClassfityByIdByPage(id);
            model.addAttribute("class",newsClassfity.get(0));
        }
        
        model.addAttribute("classes", newsClassfity);
        model.addAttribute("action", "/zixunclass/search");
        model.addAttribute("page", Pagination.threadLocal.get());
        return "zixun/list";
    }

    /** 
    * @author yangzs
    * @Title: updateStatus 
    * @Description: 更新分类 
    * @param newsClassify
    * @param  p
    * @param  request
    * @param     
    * @return String    
    * @throws 
    */
    @RequestMapping(value = "/updateStatus")
    public String updateStatus(NewsClassify newsClassify, int p,HttpServletRequest request) {
        // 有子节点和已经上线的节点不可以删除
        Integer newsClassfityChild = classdao.getNewsClassfityChild(newsClassify.getId());
        Integer newsClassfityonlineChild = classdao.getNewsClassfityOnlineChild(newsClassify.getId());
        
        if (newsClassfityChild != null && newsClassify.getStatus() == -1) {
            String string = "该分类存在子节点,不可删除! ";
            string = decodeUrl(string);
            initParam(p, request, string);
            return "forward:/zixunclass/home" ;
        }
        
        if (newsClassfityonlineChild != null && newsClassify.getStatus() == 0) {
            String string = " 该分类存在已上线子节点,不可下线! ";
            string = decodeUrl(string);
            initParam(p, request, string);
            return "forward:/zixunclass/home" ;
        }
        
        if(newsClassify.getStatus()==1 ){
            NewsClassify newsClassifyParent = Common.newsClassifyMap.get(Common.newsClassifyMap.get(newsClassify.getId()+"").getpId()+"");
            if(newsClassifyParent.getId()>0&&newsClassifyParent.getStatus()<1){
                String string = " 该分类父节点未上线! 不可直接上线!";
                string = decodeUrl(string);
                initParam(p, request, string);
                return "forward:/zixunclass/home" ;
            }
        }
        classdao.updateZixunClassStatus(newsClassify);
        classdao.init();
        return "redirect:/zixunclass/home?page.pageNo="+p ;
    }

    private void initParam(int p, HttpServletRequest request, String string) {
        request.setAttribute("page.pageNo",  p);
        request.setAttribute("msg",  string);
    }

    private String decodeUrl(String string) {
        try {
            string = URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string;
    }

    /** 
    * @author yangzs
    * @Title: save 
    * @Description: 保存资讯
    * @param @param classify
    * @param @param request
    * @param @return     
    * @return String    
    * @throws 
    */
    @RequestMapping(value = "/save")
    public String save(NewsClassify classify,HttpServletRequest request) {
        Pagination page = classify.getPage();
        int pageNo = 1;
        if (page != null) {
            pageNo = page.getPageNo();
        }
        String url = "forward:/zixunclass/home" ;
        request.setAttribute("page.pageNo", pageNo);
        //level 赋值
        NewsClassify newsParentClassify = Common.newsClassifyMap.get(classify.getpId() + "");
        classify.setLevel(newsParentClassify.getLevel() + 1);
        
        String classUrl = classdao.getClassUrl(classify);
        if (classUrl != null) {
            String string = "url 已经存在! ";
            string = decodeUrl(string);
            request.setAttribute("msg", string);
            return url;
        }

        
        if (classify.getId() != null ) {
            
            Integer newsClassfityonlineChild = classdao.getNewsClassfityOnlineChild(classify.getId());
            if (newsClassfityonlineChild != null && newsParentClassify.getStatus() == 0) {
                String string = " 该分类存在已上线子节点,不可下线! ";
                string = decodeUrl(string);
                initParam(pageNo, request, string);
                return url ;
            }
            
            
            if(classify.getStatus()==1 ){
                
                String parentId = classify.getpId()+"";
                NewsClassify newsClassifyParent = Common.newsClassifyMap.get(parentId);
                if(  newsClassifyParent.getId()>0&&newsClassifyParent.getStatus()<1){
                    String string = " 该分类父节点未上线!不可直接上线! ";
                    string = decodeUrl(string);
                    initParam(pageNo, request, string);
                    return url ;
                }
            }
            
            classdao.updateZixunClass(classify);
            classdao.init();
        } else {
            classdao.save(classify);
            classdao.init();
        }
        return url;
    }

}
