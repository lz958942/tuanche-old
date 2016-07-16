package com.tuanche.cms.util;

import java.net.URLEncoder;
import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;
/**
 * 模板标签   URLEncoder
 */
public class FreeURLEncoder  implements  TemplateMethodModel{

	
	public Object exec(List par) throws TemplateModelException {
		if(par != null && par.size()==1){
			String string = par.get(0).toString();
			String encode = URLEncoder.encode(string);
			return encode;
		}
		return null;
	}

}
