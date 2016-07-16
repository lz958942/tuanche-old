    <%@ page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"%>
    <%@ page import="com.tuanche.smc.util.Uploader" %>
	    <%@page import="com.tuanche.commons.Resources"%>
    <%
    request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	System.out.println("start");
	String param = request.getParameter("action");
    Uploader up = new Uploader(request);
    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
    up.setAllowFiles(fileType);
    up.setMaxSize(10000); //单位KB
   	String dir = Resources.getString( "filePath" );
    String url =  Resources.getString( "fileUrl" );
    up.setUrl(url);
    up.setSavePath( dir );
    if(param!=null && param.equals("tmpImg")){
    	System.out.println("上传");
    	up.upload();
    	out.print("<script>parent.ue_callback('" + up.getUrl() + "','" + up.getState() + "')</script>");
    }else{
        System.out.println("base64上传");
    	up.uploadBase64("content");
    	response.getWriter().print("{'url':'" + up.getUrl()+"',state:'"+up.getState()+"'}");
    }
    
    %>
