package com.tuanche.console.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tuanche.console.util.ApplicationUtil;

@Controller
public class AuthController{
       @RequestMapping(value="/flushAll")
       public String  fulshAll(HttpServletRequest request)
    		   throws Exception {
    	   ServletContext servletContext = request.getSession().getServletContext();
    	   InitCommon initCommon=new InitCommon();
    	   Map parameMap = new HashMap();
           parameMap.put("cpage", 0);
           parameMap.put("pageSize", Integer.MAX_VALUE);
           parameMap.put("orderStr", "id");
           ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
           ApplicationUtil.getInstance().runALL(ctx, servletContext, parameMap);
    	   return "sys/main";
       }
}
