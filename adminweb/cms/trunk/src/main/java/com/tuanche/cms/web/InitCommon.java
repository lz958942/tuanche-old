package com.tuanche.cms.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tuanche.cms.util.ApplicationUtil;
import com.tuanche.commons.util.Resources;

public class InitCommon extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init() throws ServletException {
		Map parameMap = new HashMap(); 
		parameMap.put("cpage", 0);
		parameMap.put("pageSize", Integer.MAX_VALUE);
		parameMap.put("orderStr", "id");
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ApplicationUtil.getInstance().runALL(ctx, this.getServletContext(), parameMap);
		
		this.getServletContext().setAttribute("picUrl", Resources.getString("pic.url"));
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String key=request.getParameter("key");
		String method=request.getParameter("method");
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		if(key!=null&&key.length()>0&&method!=null){
			if("update".equalsIgnoreCase(method)){
			String obj=request.getParameter("obj");
			ApplicationUtil.getInstance().update(this.getServletContext(), key, ctx,obj);
			}else if("reload".equalsIgnoreCase(method)){
				ApplicationUtil.getInstance().reload(this.getServletContext(), key, ctx);
			}
			
		}
	}
}
