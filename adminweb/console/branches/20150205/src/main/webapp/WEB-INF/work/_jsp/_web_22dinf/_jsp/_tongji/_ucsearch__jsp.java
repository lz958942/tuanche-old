/*
 * JSP generated by Resin-4.0.23 (built Fri, 30 Sep 2011 09:31:50 PDT)
 */

package _jsp._web_22dinf._jsp._tongji;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import com.tuanche.console.web.AuthUtil;

public class _ucsearch__jsp extends com.caucho.jsp.JavaPage
{
  private static final java.util.HashMap<String,java.lang.reflect.Method> _jsp_functionMap = new java.util.HashMap<String,java.lang.reflect.Method>();
  private boolean _caucho_isDead;
  private boolean _caucho_isNotModified;
  private com.caucho.jsp.PageManager _jsp_pageManager;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    javax.servlet.http.HttpSession session = request.getSession(true);
    com.caucho.server.webapp.WebApp _jsp_application = _caucho_getApplication();
    com.caucho.jsp.PageContextImpl pageContext = _jsp_pageManager.allocatePageContext(this, _jsp_application, request, response, null, session, 8192, true, false);

    TagState _jsp_state = new TagState();

    try {
      _jspService(request, response, pageContext, _jsp_application, session, _jsp_state);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      _jsp_state.release();
      _jsp_pageManager.freePageContext(pageContext);
    }
  }
  
  private void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response,
              com.caucho.jsp.PageContextImpl pageContext,
              javax.servlet.ServletContext application,
              javax.servlet.http.HttpSession session,
              TagState _jsp_state)
    throws Throwable
  {
    javax.servlet.jsp.JspWriter out = pageContext.getOut();
    final javax.el.ELContext _jsp_env = pageContext.getELContext();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    javax.servlet.jsp.tagext.JspTag _jsp_parent_tag = null;
    com.caucho.jsp.PageContextImpl _jsp_parentContext = pageContext;
    response.setContentType("text/html; charset=UTF-8");
    org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag _jsp_FormatDateTag_0 = null;
    com.caucho.jsp.IteratorLoopSupportTag _jsp_loop_2 = null;

    out.write(_jsp_string0, 0, _jsp_string0.length);
    pageContext.include("/inc/header.jsp", false);out.write(_jsp_string1, 0, _jsp_string1.length);
    
	boolean isDown=AuthUtil.checkAuth(request,"/uc/download");

    out.write(_jsp_string2, 0, _jsp_string2.length);
    _jsp_FormatDateTag_0 = _jsp_state.get_jsp_FormatDateTag_0(pageContext, _jsp_parent_tag);
    _jsp_FormatDateTag_0.setValue((java.util.Date) _caucho_expr_0.evalObject(_jsp_env));
    _jsp_FormatDateTag_0.doEndTag();
    out.write(_jsp_string3, 0, _jsp_string3.length);
    _jsp_FormatDateTag_0 = _jsp_state.get_jsp_FormatDateTag_0(pageContext, _jsp_parent_tag);
    _jsp_FormatDateTag_0.setValue((java.util.Date) _caucho_expr_1.evalObject(_jsp_env));
    _jsp_FormatDateTag_0.doEndTag();
    out.write(_jsp_string4, 0, _jsp_string4.length);
     if(isDown){ 
    out.write(_jsp_string5, 0, _jsp_string5.length);
     } 
    out.write(_jsp_string6, 0, _jsp_string6.length);
    if (_caucho_expr_2.evalBoolean(_jsp_env)) {
      out.write(_jsp_string7, 0, _jsp_string7.length);
      _jsp_loop_2 = _jsp_state.get_jsp_loop_2(pageContext, _jsp_parent_tag);
      java.lang.Object _jsp_items_11 = _caucho_expr_3.evalObject(_jsp_env);
      java.util.Iterator _jsp_iter_11 = com.caucho.jstl.rt.CoreForEachTag.getIterator(_jsp_items_11);
      _jsp_loop_2.init(0, Integer.MAX_VALUE, 1, false, false, false);
      while (_jsp_iter_11.hasNext()) {
        Object _jsp_i_11 = _jsp_iter_11.next();
        _jsp_loop_2.setCurrent(_jsp_i_11, _jsp_iter_11.hasNext());
        pageContext.setAttribute("data", _jsp_i_11);
        out.write(_jsp_string8, 0, _jsp_string8.length);
        _caucho_expr_4.print(out, _jsp_env, false);
        out.write(_jsp_string9, 0, _jsp_string9.length);
        _caucho_expr_5.print(out, _jsp_env, false);
        out.write(_jsp_string9, 0, _jsp_string9.length);
        _caucho_expr_6.print(out, _jsp_env, false);
        out.write(_jsp_string9, 0, _jsp_string9.length);
        _caucho_expr_7.print(out, _jsp_env, false);
        out.write(_jsp_string9, 0, _jsp_string9.length);
        _caucho_expr_8.print(out, _jsp_env, false);
        out.write(_jsp_string9, 0, _jsp_string9.length);
        _caucho_expr_9.print(out, _jsp_env, false);
        out.write(_jsp_string9, 0, _jsp_string9.length);
        _caucho_expr_10.print(out, _jsp_env, false);
        out.write(_jsp_string10, 0, _jsp_string10.length);
      }
      pageContext.pageSetOrRemove("data", null);
      out.write(_jsp_string11, 0, _jsp_string11.length);
    }
    out.write(_jsp_string12, 0, _jsp_string12.length);
  }

  private com.caucho.make.DependencyContainer _caucho_depends
    = new com.caucho.make.DependencyContainer();

  public java.util.ArrayList<com.caucho.vfs.Dependency> _caucho_getDependList()
  {
    return _caucho_depends.getDependencies();
  }

  public void _caucho_addDepend(com.caucho.vfs.PersistentDependency depend)
  {
    super._caucho_addDepend(depend);
    _caucho_depends.add(depend);
  }

  protected void _caucho_setNeverModified(boolean isNotModified)
  {
    _caucho_isNotModified = true;
  }

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;

    if (_caucho_isNotModified)
      return false;

    if (com.caucho.server.util.CauchoSystem.getVersionId() != -1793038186146849453L)
      return true;

    return _caucho_depends.isModified();
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
    TagState tagState;
  }

  public void init(com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.server.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    com.caucho.loader.DynamicClassLoader loader;
    loader = (com.caucho.loader.DynamicClassLoader) getClass().getClassLoader();
    String resourcePath = loader.getResourcePathSpecificFirst();
    mergePath.addClassPath(resourcePath);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/jsp/tongji/ucsearch.jsp"), -4767210951871033319L, false);
    _caucho_depends.add(depend);
    depend = new com.caucho.vfs.Depend(mergePath.lookup("jar:file:/F:/workspace/Console/src/main/webapp/WEB-INF/lib/jstl-1.2.jar!/META-INF/fmt.tld"), 7849922606946648492L, false);
    _caucho_depends.add(depend);
    _caucho_depends.add(new com.caucho.make.ClassDependency("org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag", 7844905413159918656L));
  }

  static {
    try {
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  final static class TagState {
    private org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag _jsp_FormatDateTag_0;

    final org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag get_jsp_FormatDateTag_0(PageContext pageContext, javax.servlet.jsp.tagext.JspTag _jsp_parent_tag) throws Throwable
    {
      if (_jsp_FormatDateTag_0 == null) {
        _jsp_FormatDateTag_0 = new org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag();
        _jsp_FormatDateTag_0.setPageContext(pageContext);
        if (_jsp_parent_tag instanceof javax.servlet.jsp.tagext.Tag)
          _jsp_FormatDateTag_0.setParent((javax.servlet.jsp.tagext.Tag) _jsp_parent_tag);
        else if (_jsp_parent_tag instanceof javax.servlet.jsp.tagext.SimpleTag)
          _jsp_FormatDateTag_0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jsp_parent_tag));
        else
          _jsp_FormatDateTag_0.setParent((javax.servlet.jsp.tagext.Tag) null);
        _jsp_FormatDateTag_0.setPattern("yyyy-MM-dd");
      }

      return _jsp_FormatDateTag_0;
    }
    private com.caucho.jsp.IteratorLoopSupportTag _jsp_loop_2;

    final com.caucho.jsp.IteratorLoopSupportTag get_jsp_loop_2(PageContext pageContext, javax.servlet.jsp.tagext.JspTag _jsp_parent_tag) throws Throwable
    {
      if (_jsp_loop_2 == null) {
        _jsp_loop_2 = new com.caucho.jsp.IteratorLoopSupportTag();
        _jsp_loop_2.setParent((javax.servlet.jsp.tagext.Tag) null);
      }

      return _jsp_loop_2;
    }

    void release()
    {
      if (_jsp_FormatDateTag_0 != null)
        _jsp_FormatDateTag_0.release();
    }
  }

  public java.util.HashMap<String,java.lang.reflect.Method> _caucho_getFunctionMap()
  {
    return _jsp_functionMap;
  }

  public void caucho_init(ServletConfig config)
  {
    try {
      com.caucho.server.webapp.WebApp webApp
        = (com.caucho.server.webapp.WebApp) config.getServletContext();
      init(config);
      if (com.caucho.jsp.JspManager.getCheckInterval() >= 0)
        _caucho_depends.setCheckInterval(com.caucho.jsp.JspManager.getCheckInterval());
      _jsp_pageManager = webApp.getJspApplicationContext().getPageManager();
      com.caucho.jsp.TaglibManager manager = webApp.getJspApplicationContext().getTaglibManager();
      manager.addTaglibFunctions(_jsp_functionMap, "c", "http://java.sun.com/jsp/jstl/core");
      manager.addTaglibFunctions(_jsp_functionMap, "fmt", "http://java.sun.com/jsp/jstl/fmt");
      manager.addTaglibFunctions(_jsp_functionMap, "func", "/WEB-INF/func.tld");
      com.caucho.jsp.PageContextImpl pageContext = new com.caucho.jsp.InitPageContextImpl(webApp, this);
      _caucho_expr_0 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${starttime}");
      _caucho_expr_1 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${endtime}");
      _caucho_expr_2 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${list!=null && list.size() > 0}");
      _caucho_expr_3 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${list}");
      _caucho_expr_4 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.day}");
      _caucho_expr_5 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.login_total_num}");
      _caucho_expr_6 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.pc_reg_num}");
      _caucho_expr_7 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.pc_actreg_num}");
      _caucho_expr_8 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.android_reg_num}");
      _caucho_expr_9 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.ios_reg_num}");
      _caucho_expr_10 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.wap_reg_num}");
    } catch (Exception e) {
      throw com.caucho.config.ConfigException.create(e);
    }
  }
  private static com.caucho.el.Expr _caucho_expr_0;
  private static com.caucho.el.Expr _caucho_expr_1;
  private static com.caucho.el.Expr _caucho_expr_2;
  private static com.caucho.el.Expr _caucho_expr_3;
  private static com.caucho.el.Expr _caucho_expr_4;
  private static com.caucho.el.Expr _caucho_expr_5;
  private static com.caucho.el.Expr _caucho_expr_6;
  private static com.caucho.el.Expr _caucho_expr_7;
  private static com.caucho.el.Expr _caucho_expr_8;
  private static com.caucho.el.Expr _caucho_expr_9;
  private static com.caucho.el.Expr _caucho_expr_10;

  private final static char []_jsp_string8;
  private final static char []_jsp_string3;
  private final static char []_jsp_string2;
  private final static char []_jsp_string12;
  private final static char []_jsp_string10;
  private final static char []_jsp_string7;
  private final static char []_jsp_string4;
  private final static char []_jsp_string1;
  private final static char []_jsp_string5;
  private final static char []_jsp_string6;
  private final static char []_jsp_string11;
  private final static char []_jsp_string0;
  private final static char []_jsp_string9;
  static {
    _jsp_string8 = "\n	          		<tr>\n	          			<td>".toCharArray();
    _jsp_string3 = "\" readonly=\"readonly\" autocomplete=\"off\" />\n                      	</div>-\n                      	<div class=\"input-prepend\">\n                      		<span class=\"add-on\"><i class=\"icon-calendar\"></i></span>\n                      		<input type='text' name='endtime' class=\"querytime span2\" value=\"".toCharArray();
    _jsp_string2 = "\n<div id=\"man_zone\">\n	<div>\n		<div class=\"b-con a-form\">\n          <form method='post' action='/tongji/uc'>\n          	<div class=\"pd5\">\n          		<label class=\"pr15\">\n          			\u65e5\u671f:\n          			<div class=\"input-prepend\">\n                      		<span class=\"add-on\"><i class=\"icon-calendar\"></i></span>\n                      		<input type='text' name='starttime' class=\"querytime span2\" value=\"".toCharArray();
    _jsp_string12 = "\n</div>\n<script type=\"text/javascript\">\n$('.querytime').live(\"click\",function() {\n	WdatePicker({\n	isShowClear:false,\n	qsEnabled:false,\n	dateFmt:'yyyy-MM-dd'\n	});\n	});\n$('.download').bind('click',function(e){\n	e= e || window.event;\n	e.preventDefault();\n	var href=$(this).attr('href')+'?starttime='+$('input[name=starttime]').val()+'&endtime='+$('input[name=endtime]').val()+($('select[name=brandId]').val()?'&brandId='+$('select[name=brandId]').val():\"\")+($('select[name=districtId]').val()?'&districtId='+$('select[name=districtId]').val():\"\");\n	window.location.href=href;\n});\n(function(){\n	var trs=$('.table tbody tr');\n	$('.table tbody').html(\"\");\n	trs.each(function(){\n		var that=this;\n		var len=$('.table tbody tr').length;\n		if(len>0){\n			var i=0;\n			\n			$('.table tbody tr').each(function(){\n				i++;\n				$(this).removeClass('tr1').removeClass('tr2').addClass(i%2==1?'tr1':'tr2');\n				if(parseInt($(that).find('.num').html())>parseInt($(this).find('.num').html())){\n					$(this).before($(that));\n					return false;\n				}else if(i==len){\n					$(this).after($(that));\n					return false;\n				}\n			});\n		}else{\n			$('.table tbody').append($(this));\n		}\n	});\n})();\n\nfunction changeSort(str,obj,cla){\n	var trs=$(obj).closest('tr').nextAll(cla);\n	if(trs.length>0){\n		var i=0;\n		trs.each(function(){\n			i++;\n			if(parseInt($(this).find('.num').html())<parseInt($(str).find('.num').html())){\n				$(this).before(str);\n				return false;\n			}else if(i==trs.length){\n				$(this).after(str);\n				return false;\n			}\n		});\n	}else{\n		$(obj).closest('tr').after(str)\n	}\n	\n}\n</script>\n</body>\n</html>".toCharArray();
    _jsp_string10 = "</td>\n	          		</tr>\n	          	".toCharArray();
    _jsp_string7 = "\n	<div class=\"rb-con\">\n		<div class=\"over-auto\">\n			<table class=\"table table-bordered chargeTable\">\n				<thead>\n					<tr style=\"background:none repeat scroll 0 0 #E8F1FD\">\n						<th>\u65e5\u671f</th>\n						<th>\u767b\u5f55\u603b\u6570</th>\n						<th>PC\u7aef\u6ce8\u518c\u603b\u6570</th>	\n						<th>PC\u7aef\u4e3b\u52a8\u6ce8\u518c\u6570</th>\n						<th>Android\u7aef\u6ce8\u518c\u6570</th>\n						<th>IOS\u7aef\u6ce8\u518c\u6570</th>\n						<th>WAP\u7aef\u6ce8\u518c\u6570</th>\n						<!-- <th>\u603b\u8ba1</th> -->\n					</tr>\n	          	</thead>\n	          	<tbody>\n	          	".toCharArray();
    _jsp_string4 = "\" readonly=\"readonly\" autocomplete=\"off\" />\n                      	</div>\n          		</label>\n          		\n          		<input type=\"submit\" value=\"\u67e5\u8be2\" class=\"btn btn-info\"/>&nbsp;&nbsp;\n          		".toCharArray();
    _jsp_string1 = "\n<title>\u7528\u6237\u4e2d\u5fc3\u7edf\u8ba1\u67e5\u8be2</title>\n<style>\n	.tr1{background:none repeat scroll 0px 0px rgb(224, 255, 255)!important;}\n	.tr2{background:none repeat scroll 0px 0px rgb(255, 251, 236)!important;}\n</style>\n</head>\n<body>\n".toCharArray();
    _jsp_string5 = "\n          		<a href=\"/uc/download\" class=\"btn btn-info download\" id=\"download\">\u4e0b\u8f7d</a>\n          		".toCharArray();
    _jsp_string6 = "\n          	</div>\n          </form>\n         </div>\n	</div>\n	".toCharArray();
    _jsp_string11 = "\n	          	</tbody>\n	          </table>\n	     </div>\n	</div>\n	".toCharArray();
    _jsp_string0 = "\n\n\n\n\n".toCharArray();
    _jsp_string9 = "</td>\n	          			<td>".toCharArray();
  }
}
