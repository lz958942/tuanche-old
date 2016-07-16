package com.tuanche.console.util;



import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	private Pager pager;

	@Override
	public int doStartTag() {
		try {
			JspWriter out = pageContext.getOut();
			out.print("<input type=\"hidden\"  name=\"totalRows\" value=\""+pager.getTotalRows()+"\"/>");
			out.print("<input type=\"hidden\"  name=\"cpage\" id=\"cpage\" value=\"0\"/>");
			out.print("共" + pager.getTotalRows() + "条");
			out.print("第 " + pager.getCurrentPage() + " 页/共 " + (pager.getTotalPages()) + " 页");
			if (pager.getCurrentPage() > 1) {
				out.print("[<a href=\"javaScript:pager(0);\">首页</a>]");
			}
			if(pager.getCurrentPage() <= pager.getTotalPages()&&pager.getCurrentPage()>1){
				out.print("[<a href=\"javaScript:pager(" + (pager.getCurrentPage() - 1) + ");\")>上一页</a>]");
			}
			for (int i = pager.getCurrentPage() - 3; i <= pager.getCurrentPage() + 3; i++) {
				if (i <= 0 || i > pager.getTotalPages()) {
					continue;
				}
				if (i == pager.getCurrentPage()) {
					out.print("[<span style=\"color:#FF0000; border: 1px solid #cccccc; font-weight:bold; width:10px;text-align: center;\"> " + i + " </span>]");
				} else {
					out.print("[<a href=\"javaScript:pager(" +  i + ");\">" + i + "</a>]");
				}
			}

			if (pager.getCurrentPage() < pager.getTotalPages() && pager.getTotalPages() != 0) {
				out.print("[<a href=\"javaScript:pager('"  + (pager.getCurrentPage() + 1) + "');\")>下一页</a>]");
				out.print("[<a href=\"javaScript:pager('" + (pager.getTotalPages()) + "');\")>末页</a>]");
			}
            out.print("<script type=\"text/javascript\"> function pager(pageNum){document.getElementById(\"cpage\").value=pageNum;document.forms[0].submit();} </script>");
			out.flush();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return super.SKIP_BODY;
	}

	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Pager getPager() {
		return pager;
	}

	@Override
	public void release() {
		super.release();
	}

}