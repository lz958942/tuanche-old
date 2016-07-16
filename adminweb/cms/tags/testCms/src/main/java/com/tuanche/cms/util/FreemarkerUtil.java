package com.tuanche.cms.util;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FreemarkerUtil {
	public static  Configuration configuration = null; 
	
	private static final String ftlpath = "/ftl/zixun/";
	public static final String zixun_staticfile = "/ftl/static/zixun/";
	public static final String city_staticfile = "/ftl/static/city/";
	public static final String yaohao_staticfile = "/ftl/static/yaohao/";
	/**
	 * 获取template
	 * Administrator：zhaojl
	 * @param path
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Template getTemplate(String path,HttpServletRequest request) throws Exception{
		if(configuration == null){
			configuration = new Configuration();
			
			String ftlPath = getFtlPath(request);//ftl存放目录
			File file=new File(ftlPath);   
			configuration.setDirectoryForTemplateLoading(file);   
			configuration.setObjectWrapper(new DefaultObjectWrapper()); 
			configuration.setDefaultEncoding("utf-8");
		}
		Template template = null;
		try {
			template= configuration.getTemplate(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return template;
	}
	
	
	/**
	 * 获取ftl的目录
	 * Administrator：zhaojl
	 * @return
	 */
	public static String getFtlPath(HttpServletRequest request){
		String servletPath = request.getContextPath();
        String realPath = request.getSession().getServletContext().getRealPath(servletPath);
        return realPath + ftlpath;
	}
	
	/**
	 * 生成页面
	 * Administrator：zhaojl
	 * @param root
	 * @param templateName
	 * @param htmlToPath
	 * @param htmltype 页面类型  1资讯   2 城市首页
	 * @throws Exception
	 */
	public static void makeHtml(Map<String,Object> root,String templateName,String htmlname,HttpServletRequest request,int htmltype) throws Exception{
		String des = "";
		if(htmltype==1){//资讯页
			des =zixun_staticfile;
		}else if(htmltype == 2){//城市首页
			des =city_staticfile;
		}else if(htmltype == 3){//摇号
			des =yaohao_staticfile;
		}
		//生成文件的目录
		String servletPath = request.getContextPath();
        String realPath = request.getSession().getServletContext().getRealPath(servletPath);
        File file = new File(realPath + des + htmlname);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file),"utf-8"));
		Template template = getTemplate(templateName,request);
		template.process(root, out);   
		out.flush();   
		out.close();
	}
	
	/**
	 * 生成模板
	 * Administrator：zhaojl
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public static String makeFtl(HttpServletRequest request,HttpServletResponse response,com.tuanche.cms.bean.Template template) throws Exception{
		//模板名字 = 模板的描述 + 年月日
		String teplateName = template.getPic() + ".ftl";//标签名
		
     	String servletPath = request.getContextPath();
        String realPath = request.getSession().getServletContext().getRealPath(servletPath);
        
        // 放到项目根目录下
        String fullPath = new File(realPath) + "/ftl/zixun/"+ teplateName ;
        File dir = new File(fullPath).getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
		FileOutputStream stream = new FileOutputStream(new File(fullPath));
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(stream,"utf-8");
		outputStreamWriter.write(template.getContent());
		outputStreamWriter.close();
		stream.close();
		return teplateName;
	}
	
}
