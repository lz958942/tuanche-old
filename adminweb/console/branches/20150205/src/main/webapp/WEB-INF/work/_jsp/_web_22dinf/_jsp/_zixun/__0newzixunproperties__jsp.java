/*
 * JSP generated by Resin-4.0.23 (built Fri, 30 Sep 2011 09:31:50 PDT)
 */

package _jsp._web_22dinf._jsp._zixun;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;

public class __0newzixunproperties__jsp extends com.caucho.jsp.JavaPage
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

    TagState _jsp_state = null;

    try {
      _jspService(request, response, pageContext, _jsp_application, session, _jsp_state);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
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

    out.write(_jsp_string0, 0, _jsp_string0.length);
    pageContext.pageSetOrRemove("currentPage", "newZixunProperties");
    out.write(_jsp_string1, 0, _jsp_string1.length);
    if (_caucho_expr_0.evalBoolean(_jsp_env)) {
      out.write(_jsp_string2, 0, _jsp_string2.length);
    }
    out.write(' ');
    if (_caucho_expr_1.evalBoolean(_jsp_env)) {
      out.write(_jsp_string3, 0, _jsp_string3.length);
    }
    out.write(_jsp_string4, 0, _jsp_string4.length);
    if (_caucho_expr_2.evalBoolean(_jsp_env)) {
      out.write(_jsp_string2, 0, _jsp_string2.length);
    }
    out.write(' ');
    if (_caucho_expr_3.evalBoolean(_jsp_env)) {
      out.write(_jsp_string5, 0, _jsp_string5.length);
    }
    out.write(_jsp_string6, 0, _jsp_string6.length);
    pageContext.defaultSetOrRemove("hasDeploy", _caucho_expr_4.evalObject(_jsp_env));
    out.write(_jsp_string7, 0, _jsp_string7.length);
    _caucho_expr_5.print(out, _jsp_env, false);
    out.write(_jsp_string8, 0, _jsp_string8.length);
    _caucho_expr_6.print(out, _jsp_env, false);
    out.write(_jsp_string9, 0, _jsp_string9.length);
    _caucho_expr_7.print(out, _jsp_env, false);
    out.write(_jsp_string10, 0, _jsp_string10.length);
    _caucho_expr_8.print(out, _jsp_env, false);
    out.write(_jsp_string11, 0, _jsp_string11.length);
    _caucho_expr_9.print(out, _jsp_env, false);
    out.write(_jsp_string12, 0, _jsp_string12.length);
    if (_caucho_expr_10.evalBoolean(_jsp_env)) {
      out.write(_jsp_string13, 0, _jsp_string13.length);
      _caucho_expr_11.print(out, _jsp_env, false);
      out.write('-');
      _caucho_expr_12.print(out, _jsp_env, false);
      out.write(_jsp_string14, 0, _jsp_string14.length);
      pageContext.requestSetOrRemove("valueStr", _caucho_expr_13.evalObject(_jsp_env));
      out.write(_jsp_string15, 0, _jsp_string15.length);
    }
    out.write(_jsp_string15, 0, _jsp_string15.length);
    if (_caucho_expr_14.evalBoolean(_jsp_env)) {
      out.write(_jsp_string16, 0, _jsp_string16.length);
      _caucho_expr_12.print(out, _jsp_env, false);
      out.write(_jsp_string17, 0, _jsp_string17.length);
      pageContext.requestSetOrRemove("valueStr", _caucho_expr_11.evalObject(_jsp_env));
      out.write(_jsp_string18, 0, _jsp_string18.length);
    }
    out.write(_jsp_string19, 0, _jsp_string19.length);
    _caucho_expr_15.print(out, _jsp_env, false);
    out.write(_jsp_string20, 0, _jsp_string20.length);
    _caucho_expr_11.print(out, _jsp_env, false);
    out.write(_jsp_string21, 0, _jsp_string21.length);
    _caucho_expr_16.print(out, _jsp_env, false);
    out.write(_jsp_string22, 0, _jsp_string22.length);
    _caucho_expr_17.print(out, _jsp_env, false);
    out.write(_jsp_string23, 0, _jsp_string23.length);
    _caucho_expr_18.print(out, _jsp_env, false);
    out.write(_jsp_string24, 0, _jsp_string24.length);
    _caucho_expr_19.print(out, _jsp_env, false);
    out.write(_jsp_string25, 0, _jsp_string25.length);
    _caucho_expr_20.print(out, _jsp_env, false);
    out.write(_jsp_string26, 0, _jsp_string26.length);
    _caucho_expr_21.print(out, _jsp_env, false);
    out.write(_jsp_string27, 0, _jsp_string27.length);
    _caucho_expr_22.print(out, _jsp_env, false);
    out.write(_jsp_string28, 0, _jsp_string28.length);
    _caucho_expr_23.print(out, _jsp_env, false);
    out.write(_jsp_string29, 0, _jsp_string29.length);
    if (_caucho_expr_24.evalBoolean(_jsp_env)) {
      out.write(_jsp_string30, 0, _jsp_string30.length);
      if (_caucho_expr_25.evalBoolean(_jsp_env)) {
        out.write(_jsp_string31, 0, _jsp_string31.length);
      }
      out.write(_jsp_string32, 0, _jsp_string32.length);
    }
    out.write(_jsp_string33, 0, _jsp_string33.length);
    if (_caucho_expr_26.evalBoolean(_jsp_env)) {
      out.write(_jsp_string31, 0, _jsp_string31.length);
    }
    out.write(_jsp_string34, 0, _jsp_string34.length);
    if (_caucho_expr_27.evalBoolean(_jsp_env)) {
      out.write(_jsp_string35, 0, _jsp_string35.length);
    }
    else {
      out.write(_jsp_string36, 0, _jsp_string36.length);
    }
    out.write(_jsp_string37, 0, _jsp_string37.length);
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
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/jsp/zixun/_newZixunProperties.jsp"), -270432271580129608L, false);
    _caucho_depends.add(depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/jsp/zixun/newZixunProperties.jsp"), -3272287567259554397L, false);
    _caucho_depends.add(depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/jsp/zixun/zixunMenuHeader.jsp"), 6640496937735886858L, false);
    _caucho_depends.add(depend);
  }

  final static class TagState {

    void release()
    {
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
      manager.addTaglibFunctions(_jsp_functionMap, "func", "/WEB-INF/func.tld");
      com.caucho.jsp.PageContextImpl pageContext = new com.caucho.jsp.InitPageContextImpl(webApp, this);
      _caucho_expr_0 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${currentPage == 'zixunManage'}");
      _caucho_expr_1 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${currentPage != 'zixunManage'}");
      _caucho_expr_2 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${currentPage == 'newZixunProperties'}");
      _caucho_expr_3 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${currentPage != 'newZixunProperties'}");
      _caucho_expr_4 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${func:checkAuth('/zixun/deployZixun')}");
      _caucho_expr_5 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun.id}");
      _caucho_expr_6 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${func:getallCity(zixun.cityId==null?-1:zixun.cityId)}");
      _caucho_expr_7 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun.cityId==null?-1:zixun.cityId}");
      _caucho_expr_8 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun==null?'\u7efc\u5408':func:getZXClass(zixun.classId)}");
      _caucho_expr_9 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun==null?36:zixun.classId}");
      _caucho_expr_10 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun.styleId!=null&&zixun.styleId!=''}");
      _caucho_expr_11 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun.brandId}");
      _caucho_expr_12 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun.styleId}");
      _caucho_expr_13 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun.brandId}-${zixun.styleId}");
      _caucho_expr_14 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun.styleId==null||zixun.styleId==''}");
      _caucho_expr_15 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${func:getStyleInfo(valueStr)}");
      _caucho_expr_16 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun.publishDateStr}");
      _caucho_expr_17 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun.title}");
      _caucho_expr_18 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun.description}");
      _caucho_expr_19 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun!=null?zixun.listPicFull:\"/images/selectPiuc.jpg\"}");
      _caucho_expr_20 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun.listPic}");
      _caucho_expr_21 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun.keyword}");
      _caucho_expr_22 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun.labelWord}");
      _caucho_expr_23 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun.content}");
      _caucho_expr_24 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${hasDeploy}");
      _caucho_expr_25 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${zixun.status == 1}");
      _caucho_expr_26 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${newZixun || zixun.status == 0 ||!hasDeploy}");
      _caucho_expr_27 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${newZixun}");
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
  private static com.caucho.el.Expr _caucho_expr_11;
  private static com.caucho.el.Expr _caucho_expr_12;
  private static com.caucho.el.Expr _caucho_expr_13;
  private static com.caucho.el.Expr _caucho_expr_14;
  private static com.caucho.el.Expr _caucho_expr_15;
  private static com.caucho.el.Expr _caucho_expr_16;
  private static com.caucho.el.Expr _caucho_expr_17;
  private static com.caucho.el.Expr _caucho_expr_18;
  private static com.caucho.el.Expr _caucho_expr_19;
  private static com.caucho.el.Expr _caucho_expr_20;
  private static com.caucho.el.Expr _caucho_expr_21;
  private static com.caucho.el.Expr _caucho_expr_22;
  private static com.caucho.el.Expr _caucho_expr_23;
  private static com.caucho.el.Expr _caucho_expr_24;
  private static com.caucho.el.Expr _caucho_expr_25;
  private static com.caucho.el.Expr _caucho_expr_26;
  private static com.caucho.el.Expr _caucho_expr_27;

  private final static char []_jsp_string0;
  private final static char []_jsp_string15;
  private final static char []_jsp_string37;
  private final static char []_jsp_string36;
  private final static char []_jsp_string34;
  private final static char []_jsp_string31;
  private final static char []_jsp_string25;
  private final static char []_jsp_string27;
  private final static char []_jsp_string9;
  private final static char []_jsp_string18;
  private final static char []_jsp_string13;
  private final static char []_jsp_string17;
  private final static char []_jsp_string24;
  private final static char []_jsp_string16;
  private final static char []_jsp_string1;
  private final static char []_jsp_string35;
  private final static char []_jsp_string19;
  private final static char []_jsp_string26;
  private final static char []_jsp_string5;
  private final static char []_jsp_string8;
  private final static char []_jsp_string10;
  private final static char []_jsp_string21;
  private final static char []_jsp_string29;
  private final static char []_jsp_string23;
  private final static char []_jsp_string12;
  private final static char []_jsp_string11;
  private final static char []_jsp_string2;
  private final static char []_jsp_string32;
  private final static char []_jsp_string20;
  private final static char []_jsp_string22;
  private final static char []_jsp_string3;
  private final static char []_jsp_string6;
  private final static char []_jsp_string14;
  private final static char []_jsp_string33;
  private final static char []_jsp_string28;
  private final static char []_jsp_string4;
  private final static char []_jsp_string30;
  private final static char []_jsp_string7;
  static {
    _jsp_string0 = "\r\n<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n<html>\r\n<head>\r\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n<title>\u56e2\u8f66\u7f51\u8d44\u8baf\u7ba1\u7406</title>\r\n<script type=\"text/javascript\" src=\"/js/common/jquery.js\"></script>\r\n<script type=\"text/javascript\" src=\"/js/common/jquery-ui.js\"></script>\r\n<script type=\"text/javascript\" src=\"/js/common/jquery.blockUI.js\"></script>\r\n<script type=\"text/javascript\" src=\"/js/common/jquery.tipsy.js\"></script>\r\n<script type=\"text/javascript\" src=\"/js/common/validation.js\"></script>\r\n<script type=\"text/javascript\" src=\"/js/common/common.js\"></script>\r\n\r\n <!-- ztree ue-->\r\n <script type=\"text/javascript\" charset=\"utf-8\" src=\"/ue/ueditor.config.js\"></script>\r\n <script type=\"text/javascript\" charset=\"utf-8\" src=\"/ue/ueditor.all.min.js\"> </script>\r\n  <script type=\"text/javascript\" charset=\"utf-8\" src=\"/js/ztree/ztree.3.5.js\"> </script>\r\n  <script type=\"text/javascript\" charset=\"utf-8\" src=\"/js/ztree/jquery.ztree.exhide-3.5.min.js\"> </script>\r\n  <script type=\"text/javascript\" charset=\"utf-8\" src=\"/js/ajaxfileupload.js\"> </script>\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/demo.ztree.css\" />\r\n<link type=\"text/css\" rel=\"stylesheet\" href=\"/css/zTreeStyle.css\"/>\r\n\r\n\r\n \r\n <script type=\"text/javascript\" src=\"/js/zixun/zixun.js\"></script>\r\n<link type=\"text/css\" rel=\"stylesheet\" href=\"/css/common/smoothness/jquery-ui-1.8.14.custom.css\"/>\r\n<link type=\"text/css\" rel=\"stylesheet\" href=\"/css/common/tipsy.css\"/>\r\n<link type=\"text/css\" rel=\"stylesheet\" href=\"/css/layout.css\"/>\r\n</head>\r\n<body>\r\n<div >\r\n\r\n<table border=\"0\" cellpadding=\"2\" cellspacing=\"2\" align=\"center\" width=\"100%\">\r\n\r\n	<tr>\r\n		<td style=\"vertical-align: top; overflow-x: scoll\">\r\n			\r\n\r\n\r\n<script type=\"text/javascript\" src=\"/js/common/jsdate.js\"></script>\r\n<script type=\"text/javascript\" src=\"/js/My97DatePicker/WdatePicker.js\"></script>\r\n".toCharArray();
    _jsp_string15 = "\r\n					".toCharArray();
    _jsp_string37 = "\r\n	</div>\r\n</form>\r\n\r\n<div id=\"menuContent\" style=\"display:none; position: absolute;\" >\r\n	<ul id=\"treeCity\" class=\"ztree\"  ></ul>\r\n</div>\r\n\r\n<div id=\"menuContentClass\" style=\"display:none; position: absolute;\" >\r\n	<ul id=\"treeClass\" class=\"ztree\"  ></ul>\r\n</div>\r\n<div id=\"menuContentselectCarStyle\" style=\"display:none; position: absolute;\" >\r\n	<ul id=\"treeselectCarStyle\" class=\"ztree\"  ></ul>\r\n</div>\r\n\r\n\r\n		</td>\r\n	</tr>\r\n	\r\n</table>\r\n\r\n</div>\r\n</body>\r\n</html>\r\n".toCharArray();
    _jsp_string36 = "\r\n				<input id=\"newZixunBtn\" type=\"button\" value=\"\u4fdd\u5b58\" onClick=\"saveZixunProperties();\"/>\r\n			".toCharArray();
    _jsp_string34 = " />\u5f85\u53d1\u5e03\r\n				</td>\r\n            </tr>\r\n            <tr>\r\n            	<td>&nbsp;</td>\r\n            	<td>\r\n            		<span id=\"errMsg\" style=\"color:red;\"></span>\r\n            	</td>\r\n            </tr>\r\n        </table>\r\n	</div>\r\n	<div class=\"xtnext\">\r\n		".toCharArray();
    _jsp_string31 = " checked=\"checked\" ".toCharArray();
    _jsp_string25 = "'  width=\"60\" height=\"40\" onclick=\"$('#listPicFile').trigger('click')\"/>\r\n          	   	\u5217\u8868 \u56fe\u7247\u5c3a\u5bf8\u4e3a\uff1a600*400\r\n          	    <input type=\"hidden\" id=\"listPic\" name=\"listPic\"  value=\"".toCharArray();
    _jsp_string27 = "\" style=\"width:675px;\" />\r\n                 	<p class=\"font\">\u539f\u521b\u7f16\u8f91\u5fc5\u586b</p>\r\n                 </td>\r\n            </tr>\r\n            <tr>\r\n            	<td style=\"vertical-align:top\">\u8d44\u8baf\u6807\u7b7e\u8bcd\uff1a</td>\r\n                <td>\r\n                	<input maxlength=\"100\" type=\"text\"  id=\"labelWord\" name=\"labelWord\" value=\"".toCharArray();
    _jsp_string9 = "\"/>\r\n				<input  id=\"cityId\" name=\"cityId\" type=\"hidden\" value=\"".toCharArray();
    _jsp_string18 = "\r\n					    \r\n					".toCharArray();
    _jsp_string13 = "\r\n					    	<input id=\"styleId\" name=\"styleId\" type=\"hidden\" value=\"".toCharArray();
    _jsp_string17 = "\"/>\r\n					    	 ".toCharArray();
    _jsp_string24 = "</textarea>\r\n                 <p class=\"font\">\u7528\u4e8e\u63cf\u8ff0\u8be5\u4e13\u9898\u9875\u9762\uff0c\u4f1a\u4ee5 meta \u6807\u7b7e\u7684\u5f62\u5f0f\u5185\u5d4c\u5728\u7f51\u9875\u4e2d\uff0c\u5c3d\u91cf\u5728\u63cf\u8ff0\u4e2d\u52a0\u5165\u4f60\u4e3b\u8981\u7684\u5173\u952e\u5b57\uff0c\u5728\u6e90\u4ee3\u7801\u7684description\u4e2d\u663e\u793a\uff0c\u914d\u5408\u641c\u7d22\u5f15\u64ce\u6293\u53d6\uff0c\u63d0\u9ad8\u8d44\u8baf\u88ab\u641c\u7d22\u5f15\u64ce\u6293\u53d6\u7684\u51e0\u7387\uff08\u8bf7\u63a7\u5236250\u5b57\u4ee5\u5185\uff09</p></td>\r\n            </tr>\r\n            <tr>\r\n            	<td>\r\n          	    	\u9009\u62e9\u5217\u8868\u56fe\uff1a\r\n          	    </td>\r\n          	    <td>\r\n          	    <img id=\"listImage\" src='".toCharArray();
    _jsp_string16 = "\r\n					\r\n					    	<input id=\"styleId\" name=\"styleId\" type=\"hidden\" value=\"".toCharArray();
    _jsp_string1 = "\r\n\r\n<div id=\"tabs\" class=\"tabs\">  \r\n	<ul>\r\n		<li ".toCharArray();
    _jsp_string35 = "\r\n				<input id=\"newZixunBtn\" type=\"button\" value=\"\u4fdd\u5b58\" onClick=\"saveZixunProperties();\"/>\r\n				<input id=\"newZixunBtn\" type=\"button\" value=\"\u4e0b\u4e00\u6b65\" onClick=\"checkNewZixunProperties();\"/>\r\n			".toCharArray();
    _jsp_string19 = "\r\n					<input readonly=\"readonly\" id=\"selectCarStyle\" onclick=\"selectCarStyleshow()\" value=\" ".toCharArray();
    _jsp_string26 = "\" />\r\n          	    </td>\r\n            </tr>\r\n            <tr>\r\n            	<td style=\"vertical-align:top\">\u8d44\u8baf\u5173\u952e\u8bcd\uff1a</td>\r\n                <td>\r\n                	<input maxlength=\"50\" type=\"text\"  id=\"keyword\" name=\"keyword\" value=\"".toCharArray();
    _jsp_string5 = " style=\"background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%\"".toCharArray();
    _jsp_string8 = "\" />\r\n	<div class=\"borderDiv\">\r\n		<table>\r\n			<tr>\r\n				<td><span style=\"width:100px;padding-right:16px; vertical-align:top\">\u9009\u62e9\u57ce\u5e02\uff1a</span>\r\n				<input readonly=\"readonly\" id=\"city\" onclick=\"showMenu(); return false;\" id=\"city\"  style=\"width:93px\" value=\"".toCharArray();
    _jsp_string10 = "\"/>\r\n				</td>\r\n				<td>\r\n					<span style=\"width:100px;padding-right:16px;padding-left:16px;vertical-align:top\">\u9009\u62e9\u5206\u7c7b\uff1a<span style=\"color:red;\">*</span></span>\r\n					<input readonly=\"readonly\" id=\"classId\" onclick=\"classshowMenu(); return false;\" value=\"".toCharArray();
    _jsp_string21 = ",\"/>\r\n					\r\n				</td>\r\n				<td>\r\n					<span style=\"width:100px;padding-right:16px;padding-left:16px;vertical-align:top\"><!--\u9009\u62e9\u5217\u8868\u56fe\u7247\uff1a--></span>\r\n					<div style=\"display: none\">\r\n					<input id=\"listPicFile\" name=\"listPicFile\" type=\"file\" onchange=\"upload()\"/>\r\n					</div>\r\n				</td>\r\n			</tr>\r\n		</table>\r\n		\r\n	</div>\r\n	<div class=\"borderDiv\">\r\n		<table>\r\n			<tr>\r\n            	<td>\u53d1\u5e03\u65f6\u95f4:</td>\r\n                <td>\r\n                <input value=\"".toCharArray();
    _jsp_string29 = "</script>\r\n	            		<script type=\"text/javascript\">\r\n						     editor = new baidu.editor.ui.Editor({\r\n						        textarea:'content'\r\n						    });\r\n						    editor.render(\"myEditor\");\r\n						</script>\r\n					</div>\r\n            	</td>\r\n            </tr>\r\n            <tr>\r\n            	<td>\u5ba1\u6838\u72b6\u6001:</td>\r\n                <td>\r\n                	".toCharArray();
    _jsp_string23 = "\" style=\"width:675px;\" />\r\n                	<p class=\"font\">\u8d44\u8baf\u6807\u9898\u662f\u8d44\u8baf\u7684\u6807\u9898\uff0c\u5728\u6b63\u6587\u7684\u5f00\u5934\u51fa\u73b0\u3002\u5e76\u4e14\u5728\u8d44\u8baf\u9875\u9762\u4ee3\u7801\u7684title\u4e2d\u663e\u793a\uff08\u9650\u5b9a44\u4e2a\u5b57\uff09</p>\r\n                </td>\r\n            </tr>\r\n            <tr>\r\n            	<td style=\"vertical-align:top\">\u8d44\u8baf\u63cf\u8ff0:</td>\r\n                <td><textarea maxlength=\"250\" cols=\"107\"  rows=\"5\" id=\"description\" name=\"description\" style=\"width:675px\">".toCharArray();
    _jsp_string12 = "\"/>\r\n				</td>\r\n				<td>\r\n					<span style=\"width:100px;padding-right:16px;padding-left:16px;vertical-align:top\">\u9009\u62e9\u5173\u8054\u8f66\u578b\uff1a</span>\r\n					".toCharArray();
    _jsp_string11 = "\" />\r\n					<input id=\"classIdh\" name=\"classId\" type=\"hidden\" value=\"".toCharArray();
    _jsp_string2 = "class=\"tabs_active\"".toCharArray();
    _jsp_string32 = " />\u53d1\u5e03\r\n                		".toCharArray();
    _jsp_string20 = "\"/>\r\n					<input id=\"brindId\" name=\"brandId\" type=\"hidden\" value=\"".toCharArray();
    _jsp_string22 = "\"  onfocus=\" WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'}) \"  name=\"publishDateStr\" style=\"width:300px;\" class=\"dateCss\" type=\"text\"  readonly=\"readonly\">\r\n                </td>\r\n            </tr>\r\n            <tr>\r\n            	<td style=\"vertical-align:top\">\u8d44\u8baf\u6807\u9898\uff1a<span style=\"color:red;\">*</span></td>\r\n                <td><input maxlength=\"44\" type=\"text\"  id=\"zixunTitle\" name=\"title\" value=\"".toCharArray();
    _jsp_string3 = "  style=\"background:url(/images/ui-bg_glass_75_e6e6e6_1x400.png) #e6e6e6 repeat-x 50% 50%\" ".toCharArray();
    _jsp_string6 = " ><a href=\"/zixun/newZixunProperties\">\u7f16\u8f91\u8d44\u8baf</a></li>\r\n	</ul>\r\n</div>\r\n\r\n<div class=\"ztsx\">\r\n	<label><strong>\u7f16\u8f91\u8d44\u8baf\u5185\u5bb9</strong></label>\r\n</div>\r\n	<!-- \u68c0\u67e5\u6743\u9650 -->\r\n	".toCharArray();
    _jsp_string14 = ",\"/>\r\n					        ".toCharArray();
    _jsp_string33 = "\r\n                	<input type=\"radio\" name=\"status\" value=\"0\" ".toCharArray();
    _jsp_string28 = "\" style=\"width:675px;\" />\r\n                 	<p class=\"font\">\u4f18\u5316\u7f16\u8f91\u5fc5\u586b</p>\r\n                 </td>\r\n            </tr>\r\n            <tr>\r\n            	<td style=\"vertical-align:top\">\u8d44\u8baf\u5185\u5bb9\uff1a<span style=\"color:red;\">*</span></td>\r\n            	<td>\r\n            		<div style=\"width:900px;\">\r\n	            		<script type=\"text/plain\" id=\"myEditor\">".toCharArray();
    _jsp_string4 = " ><a href=\"/zixun/home\">\u7ba1\u7406\u8d44\u8baf</a></li>\r\n		<li ".toCharArray();
    _jsp_string30 = "\r\n							<input type=\"radio\" name=\"status\" value=\"1\" ".toCharArray();
    _jsp_string7 = "\r\n<form action=\"/zixun/nextZixunProperties\" method=\"post\" style=\"padding:0 10px 0 10px; margin-top:0px\" name=\"zixun\" id=\"newZixunPropertiesForm\">\r\n		<input id=\"zixunId\" type=\"hidden\" name=\"id\" value=\"".toCharArray();
  }
}
