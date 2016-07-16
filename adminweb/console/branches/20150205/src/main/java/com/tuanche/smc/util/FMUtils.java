package com.tuanche.smc.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.tuanche.commons.util.Resources;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FMUtils {
	
	public void outputFile(String htmlName,String ftlName,String ftlpath,String htmlPath,Map<String,Object> map)throws Exception{
		
		
		//创建fm的配置
		Configuration configuration=new Configuration();
		FileTemplateLoader templateLoader=new FileTemplateLoader(new File(ftlpath));
		//指定默认编码格式utf-6
		configuration.setDefaultEncoding("utf-8");
		//设置模板的包路径
		configuration.setTemplateLoader(templateLoader);
		//configuration.setClassForTemplateLoading(this.getClass(), ftlpath);
		//获得包的模板
		Template template=configuration.getTemplate(ftlName);
		
		//定义输出流，注意必须指定编码
		Writer writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(htmlPath+"/"+htmlName))));
		//生成模板
		template.process(map, writer);
		
	}
}
