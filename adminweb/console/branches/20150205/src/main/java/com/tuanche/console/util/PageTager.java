package com.tuanche.console.util;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class PageTager extends TagSupport {
	private static final long serialVersionUID = 1L;

	private Pager pager;

	public int doStartTag() {

		try {
			JspWriter out = pageContext.getOut();
			out.print("<form  id='pageForm' action='" + pager.getLinkUrl() + "'method='post' >");
			Map<String,String> conditions=pager.getSearchCondtions();
			if(conditions!=null&&conditions.size()>0){
				Set<Entry<String,String>>  entrys=conditions.entrySet();
				for(Entry<String,String> entry: entrys) {
					if(entry.getValue()!=null&&!entry.getValue().equalsIgnoreCase("null")&&entry.getValue().trim().length()>0){
					 out.print("<input type=\"hidden\"  name=\""+entry.getKey()+"\" value=\""+entry.getValue()+"\"/>");
					}
				}
				
			}
			
			out.print("共" + pager.getTotalRows() + "条");
			out.print("第 " + pager.getCurrentPage() + " 页/共 " + (pager.getTotalPages()) + " 页");
			String connector=(pager.getLinkUrl().contains("?")?"&":"?");
			if(pager.getLinkUrl().contains("?")){
				
			}
			if (pager.getCurrentPage() > 1) {
				out.print("[<a href=\"javaScript:pager('" + pager.getLinkUrl() +connector+ "cpage=1');\">首页</a>]");
			}
			if(pager.getCurrentPage() <= pager.getTotalPages()&&pager.getCurrentPage()>1){
				out.print("[<a href=\"javaScript:pager('" + pager.getLinkUrl()+connector + "cpage=" + (pager.getCurrentPage() - 1) + "');\")>上一页</a>]");
			}
			for (int i = pager.getCurrentPage() - 3; i <= pager.getCurrentPage() + 3; i++) {
				if (i <= 0 || i > pager.getTotalPages()) {
					continue;
				}
				if (i == pager.getCurrentPage()) {
					out.print("[<span class=\"current\" style=\"color:#FF0000; border: 1px solid #cccccc; font-weight:bold; width:10px;text-align: center;\"> " + i + " </span>]");
				} else {
					out.print("[<a href=\"javaScript:pager('" + pager.getLinkUrl() +connector + "cpage=" + i + "');\">" + i + "</a>]");
				}
			}

			if (pager.getCurrentPage() < pager.getTotalPages() && pager.getTotalPages() != 0) {
				out.print("[<a href=\"javaScript:pager('" + pager.getLinkUrl()+connector  + "cpage=" + (pager.getCurrentPage() + 1) + "');\")>下一页</a>]");
				out.print("[<a href=\"javaScript:pager('" + pager.getLinkUrl() +connector + "cpage=" + (pager.getTotalPages()) + "');\")>末页</a>]");
			}
	
			out.print("</form>");
            out.print("<script type=\"text/javascript\"> function pager(url){document.getElementById(\"pageForm\").action=url;"
            		+ "document.getElementById(\"pageForm\").submit();} </script>");
			out.flush();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return super.SKIP_BODY;
	}

	public int doEndTag() {
		return EVAL_PAGE;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Pager getPager() {
		return pager;
	}

	public void release() {
		super.release();
	}

}
