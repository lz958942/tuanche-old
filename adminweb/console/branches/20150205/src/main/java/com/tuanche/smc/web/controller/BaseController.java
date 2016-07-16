package com.tuanche.smc.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.ui.Model;

import com.google.gson.Gson;
import com.tuanche.info.service.FriendLinkService;
import com.tuanche.smc.common.page.impl.Pagination;


public class BaseController {
	protected Logger log = Logger.getLogger(this.getClass());
	protected Gson gson = new Gson();
	private FriendLinkService friendLinkService=new FriendLinkService();
	
	public void  link(Model model,String url){
		model.addAttribute("friendLinks",friendLinkService.getByUrl(FriendLinkService.FL_TYPE_LINK,url));
		model.addAttribute("businesses",friendLinkService.getByUrl(FriendLinkService.FL_TYPE_BUSINESS,url));
	}
	
	protected void sentResponseInfo(HttpServletResponse response,String info){
		response.setContentType("text/plain; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		Writer out = null;
		try {
			out = response.getWriter();
			out.write(info);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void sentResponseData(HttpServletResponse response,String data){
		response.setContentType("text/html; charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
	}
	
	protected String getRequestData(HttpServletRequest request) {
		StringBuilder result = new StringBuilder();
		String line = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			in.close();
		} catch(Exception e) {
			log.error("获取request请求的数据失败。");
		}
		return result.toString();
	}
	
	/**
	 * 设置pageNo值.
	 * @param pagination
	 * @param request
	 */
	protected void setPageNo(Pagination pagination,HttpServletRequest request){
		if (pagination.getPageNo() == 0) {
			pagination.setPageNo(1);
		} else {
			Integer pageNo = (Integer) request.getSession().getAttribute("pageNo");
			if (pagination.getPageNo() == 1) {
				pagination.setPageNo(pageNo != null ? pageNo.intValue() : 1);
			}
		}
	}
	public String getEncoder(String str){
		if(str!=null&&str.length()>0){
			try {
				return URLEncoder.encode(str, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				return "";
			}
		}else{
			return "";
		}
		
	}
	
	
	public static void outJson(HttpServletResponse response,Object object){
		response.setContentType("text/html");
		response.setHeader("Content-Type","text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			 out = response.getWriter();
			 JSONObject fromObject = JSONObject.fromObject(object);
			 out.print(fromObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void outJsonArray(HttpServletResponse response,Object object){
		response.setContentType("text/html");
		response.setHeader("Content-Type","text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			 out = response.getWriter();
			 JSONArray fromObject = JSONArray.fromObject(object);
			 out.print(fromObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
