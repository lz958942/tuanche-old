/*
 * JSP generated by Resin-4.0.23 (built Fri, 30 Sep 2011 09:31:50 PDT)
 */

package _jsp._web_22dinf._jsp._groupbuy;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import com.tuanche.console.web.AuthUtil;

public class _list__jsp extends com.caucho.jsp.JavaPage
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
    com.caucho.jsp.IteratorLoopSupportTag _jsp_loop_1 = null;
    org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag _jsp_FormatDateTag_2 = null;
    com.tuanche.console.util.PageTager _jsp_PageTager_3 = null;

    out.write(_jsp_string0, 0, _jsp_string0.length);
    pageContext.include("/inc/header.jsp", false);out.write(_jsp_string1, 0, _jsp_string1.length);
    
	boolean isBdel=AuthUtil.checkAuth(request,"/groupbuy/bdel");
	boolean isCdel=AuthUtil.checkAuth(request,"/groupbuy/cdel");
	boolean isBadd=AuthUtil.checkAuth(request,"/groupbuy/badd");

    out.write(_jsp_string2, 0, _jsp_string2.length);
    _caucho_expr_0.print(out, _jsp_env, false);
    out.write(_jsp_string3, 0, _jsp_string3.length);
    _jsp_loop_1 = _jsp_state.get_jsp_loop_1(pageContext, _jsp_parent_tag);
    java.lang.Object _jsp_items_4 = _caucho_expr_1.evalObject(_jsp_env);
    java.util.Iterator _jsp_iter_4 = com.caucho.jstl.rt.CoreForEachTag.getIterator(_jsp_items_4);
    _jsp_loop_1.init(0, Integer.MAX_VALUE, 1, false, false, false);
    while (_jsp_iter_4.hasNext()) {
      Object _jsp_i_4 = _jsp_iter_4.next();
      _jsp_loop_1.setCurrent(_jsp_i_4, _jsp_iter_4.hasNext());
      pageContext.setAttribute("city", _jsp_i_4);
      out.write(_jsp_string4, 0, _jsp_string4.length);
      _caucho_expr_2.print(out, _jsp_env, false);
      out.write(_jsp_string5, 0, _jsp_string5.length);
      _caucho_expr_3.print(out, _jsp_env, false);
      out.write('>');
      _caucho_expr_4.print(out, _jsp_env, false);
      out.write(_jsp_string6, 0, _jsp_string6.length);
    }
    pageContext.pageSetOrRemove("city", null);
    out.write(_jsp_string7, 0, _jsp_string7.length);
    _jsp_loop_1 = _jsp_state.get_jsp_loop_1(pageContext, _jsp_parent_tag);
    java.lang.Object _jsp_items_5 = _caucho_expr_5.evalObject(_jsp_env);
    java.util.Iterator _jsp_iter_5 = com.caucho.jstl.rt.CoreForEachTag.getIterator(_jsp_items_5);
    _jsp_loop_1.init(0, Integer.MAX_VALUE, 1, false, false, false);
    while (_jsp_iter_5.hasNext()) {
      Object _jsp_i_5 = _jsp_iter_5.next();
      _jsp_loop_1.setCurrent(_jsp_i_5, _jsp_iter_5.hasNext());
      pageContext.setAttribute("brand", _jsp_i_5);
      out.write(_jsp_string4, 0, _jsp_string4.length);
      _caucho_expr_6.print(out, _jsp_env, false);
      out.write(_jsp_string5, 0, _jsp_string5.length);
      _caucho_expr_7.print(out, _jsp_env, false);
      out.write('>');
      _caucho_expr_8.print(out, _jsp_env, false);
      out.write(_jsp_string6, 0, _jsp_string6.length);
    }
    pageContext.pageSetOrRemove("brand", null);
    out.write(_jsp_string8, 0, _jsp_string8.length);
    if (_caucho_expr_9.evalBoolean(_jsp_env)) {
      out.write(_jsp_string9, 0, _jsp_string9.length);
    }
    out.write(_jsp_string10, 0, _jsp_string10.length);
    if (_caucho_expr_10.evalBoolean(_jsp_env)) {
      out.write(_jsp_string9, 0, _jsp_string9.length);
    }
    out.write(_jsp_string11, 0, _jsp_string11.length);
     if(isBadd) { 
    out.write(_jsp_string12, 0, _jsp_string12.length);
     } 
    out.write(_jsp_string13, 0, _jsp_string13.length);
    _jsp_loop_1 = _jsp_state.get_jsp_loop_1(pageContext, _jsp_parent_tag);
    java.lang.Object _jsp_items_6 = _caucho_expr_11.evalObject(_jsp_env);
    java.util.Iterator _jsp_iter_6 = com.caucho.jstl.rt.CoreForEachTag.getIterator(_jsp_items_6);
    _jsp_loop_1.init(0, Integer.MAX_VALUE, 1, false, false, false);
    while (_jsp_iter_6.hasNext()) {
      Object _jsp_i_6 = _jsp_iter_6.next();
      _jsp_loop_1.setCurrent(_jsp_i_6, _jsp_iter_6.hasNext());
      pageContext.setAttribute("data", _jsp_i_6);
      out.write(_jsp_string14, 0, _jsp_string14.length);
      _caucho_expr_12.print(out, _jsp_env, false);
      out.write(_jsp_string15, 0, _jsp_string15.length);
      if (_caucho_expr_13.evalBoolean(_jsp_env)) {
        out.write(_jsp_string16, 0, _jsp_string16.length);
        _caucho_expr_14.print(out, _jsp_env, false);
        out.write('-');
        _caucho_expr_15.print(out, _jsp_env, false);
        out.write(_jsp_string17, 0, _jsp_string17.length);
      }
      out.write(_jsp_string18, 0, _jsp_string18.length);
      _caucho_expr_16.print(out, _jsp_env, false);
      out.write(_jsp_string19, 0, _jsp_string19.length);
      _caucho_expr_17.print(out, _jsp_env, false);
      out.write(_jsp_string20, 0, _jsp_string20.length);
      _caucho_expr_18.print(out, _jsp_env, false);
      out.write(_jsp_string20, 0, _jsp_string20.length);
      _caucho_expr_19.print(out, _jsp_env, false);
      out.write(_jsp_string20, 0, _jsp_string20.length);
      _jsp_FormatDateTag_2 = _jsp_state.get_jsp_FormatDateTag_2(pageContext, _jsp_parent_tag);
      _jsp_FormatDateTag_2.setValue((java.util.Date) _caucho_expr_20.evalObject(_jsp_env));
      _jsp_FormatDateTag_2.doEndTag();
      out.write(_jsp_string20, 0, _jsp_string20.length);
      _caucho_expr_21.print(out, _jsp_env, false);
      out.write(_jsp_string20, 0, _jsp_string20.length);
      _caucho_expr_22.print(out, _jsp_env, false);
      out.write(_jsp_string21, 0, _jsp_string21.length);
       if(isBadd){ 
      out.write(_jsp_string22, 0, _jsp_string22.length);
      _caucho_expr_23.print(out, _jsp_env, false);
      out.write(_jsp_string23, 0, _jsp_string23.length);
      _caucho_expr_12.print(out, _jsp_env, false);
      out.write(_jsp_string24, 0, _jsp_string24.length);
      _caucho_expr_24.print(out, _jsp_env, false);
      out.write(_jsp_string25, 0, _jsp_string25.length);
       } 
      out.write(_jsp_string26, 0, _jsp_string26.length);
       if(isBdel){ 
      out.write(_jsp_string27, 0, _jsp_string27.length);
      _caucho_expr_25.print(out, _jsp_env, false);
      out.write(_jsp_string28, 0, _jsp_string28.length);
      _caucho_expr_14.print(out, _jsp_env, false);
      out.write(_jsp_string29, 0, _jsp_string29.length);
      _caucho_expr_15.print(out, _jsp_env, false);
      out.write(_jsp_string30, 0, _jsp_string30.length);
      _caucho_expr_12.print(out, _jsp_env, false);
      out.write(_jsp_string31, 0, _jsp_string31.length);
      _caucho_expr_26.print(out, _jsp_env, false);
      out.write(_jsp_string24, 0, _jsp_string24.length);
      _caucho_expr_24.print(out, _jsp_env, false);
      out.write(_jsp_string32, 0, _jsp_string32.length);
      _caucho_expr_27.print(out, _jsp_env, false);
      out.write(_jsp_string33, 0, _jsp_string33.length);
       } 
      out.write(_jsp_string34, 0, _jsp_string34.length);
    }
    pageContext.pageSetOrRemove("data", null);
    out.write(_jsp_string35, 0, _jsp_string35.length);
    _jsp_PageTager_3 = _jsp_state.get_jsp_PageTager_3(pageContext, _jsp_parent_tag);
    _jsp_PageTager_3.setPager((com.tuanche.console.util.Pager) _caucho_expr_28.evalObject(_jsp_env));
    _jsp_PageTager_3.doStartTag();
    out.write(_jsp_string36, 0, _jsp_string36.length);
    _jsp_loop_1 = _jsp_state.get_jsp_loop_1(pageContext, _jsp_parent_tag);
    java.lang.Object _jsp_items_15 = _caucho_expr_1.evalObject(_jsp_env);
    java.util.Iterator _jsp_iter_15 = com.caucho.jstl.rt.CoreForEachTag.getIterator(_jsp_items_15);
    _jsp_loop_1.init(0, Integer.MAX_VALUE, 1, false, false, false);
    while (_jsp_iter_15.hasNext()) {
      Object _jsp_i_15 = _jsp_iter_15.next();
      _jsp_loop_1.setCurrent(_jsp_i_15, _jsp_iter_15.hasNext());
      pageContext.setAttribute("city", _jsp_i_15);
      out.write(_jsp_string4, 0, _jsp_string4.length);
      _caucho_expr_2.print(out, _jsp_env, false);
      out.write(_jsp_string32, 0, _jsp_string32.length);
      _caucho_expr_4.print(out, _jsp_env, false);
      out.write(_jsp_string6, 0, _jsp_string6.length);
    }
    pageContext.pageSetOrRemove("city", null);
    out.write(_jsp_string37, 0, _jsp_string37.length);
    _caucho_expr_29.print(out, _jsp_env, false);
    out.write(_jsp_string38, 0, _jsp_string38.length);
    _caucho_expr_29.print(out, _jsp_env, false);
    out.write(_jsp_string39, 0, _jsp_string39.length);
    _caucho_expr_29.print(out, _jsp_env, false);
    out.write(_jsp_string40, 0, _jsp_string40.length);
    _caucho_expr_30.print(out, _jsp_env, false);
    out.write(_jsp_string41, 0, _jsp_string41.length);
     if(isBadd){ 
    out.write(_jsp_string42, 0, _jsp_string42.length);
     } 
     if(isCdel){ 
    out.write(_jsp_string43, 0, _jsp_string43.length);
     } 
    out.write(_jsp_string44, 0, _jsp_string44.length);
    _caucho_expr_29.print(out, _jsp_env, false);
    out.write(_jsp_string45, 0, _jsp_string45.length);
    _caucho_expr_29.print(out, _jsp_env, false);
    out.write(_jsp_string46, 0, _jsp_string46.length);
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
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/jsp/groupbuy/list.jsp"), 5299307551185233785L, false);
    _caucho_depends.add(depend);
    depend = new com.caucho.vfs.Depend(mergePath.lookup("jar:file:/F:/workspace/Console/src/main/webapp/WEB-INF/lib/jstl-1.2.jar!/META-INF/fmt.tld"), 7849922606946648492L, false);
    _caucho_depends.add(depend);
    _caucho_depends.add(new com.caucho.make.ClassDependency("org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag", 7844905413159918656L));
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/pagetag.tld"), -3417914569418740693L, false);
    _caucho_depends.add(depend);
    _caucho_depends.add(new com.caucho.make.ClassDependency("com.tuanche.console.util.PageTager", 5740209874162940004L));
  }

  static {
    try {
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  final static class TagState {
    private com.caucho.jsp.IteratorLoopSupportTag _jsp_loop_1;

    final com.caucho.jsp.IteratorLoopSupportTag get_jsp_loop_1(PageContext pageContext, javax.servlet.jsp.tagext.JspTag _jsp_parent_tag) throws Throwable
    {
      if (_jsp_loop_1 == null) {
        _jsp_loop_1 = new com.caucho.jsp.IteratorLoopSupportTag();
        _jsp_loop_1.setParent((javax.servlet.jsp.tagext.Tag) null);
      }

      return _jsp_loop_1;
    }
    private org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag _jsp_FormatDateTag_2;

    final org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag get_jsp_FormatDateTag_2(PageContext pageContext, javax.servlet.jsp.tagext.JspTag _jsp_parent_tag) throws Throwable
    {
      if (_jsp_FormatDateTag_2 == null) {
        _jsp_FormatDateTag_2 = new org.apache.taglibs.standard.tag.rt.fmt.FormatDateTag();
        _jsp_FormatDateTag_2.setPageContext(pageContext);
        _jsp_FormatDateTag_2.setParent((javax.servlet.jsp.tagext.Tag) _jsp_loop_1);
        _jsp_FormatDateTag_2.setPattern("yyyy-MM-dd");
      }

      return _jsp_FormatDateTag_2;
    }
    private com.tuanche.console.util.PageTager _jsp_PageTager_3;

    final com.tuanche.console.util.PageTager get_jsp_PageTager_3(PageContext pageContext, javax.servlet.jsp.tagext.JspTag _jsp_parent_tag) throws Throwable
    {
      if (_jsp_PageTager_3 == null) {
        _jsp_PageTager_3 = new com.tuanche.console.util.PageTager();
        _jsp_PageTager_3.setPageContext(pageContext);
        if (_jsp_parent_tag instanceof javax.servlet.jsp.tagext.Tag)
          _jsp_PageTager_3.setParent((javax.servlet.jsp.tagext.Tag) _jsp_parent_tag);
        else if (_jsp_parent_tag instanceof javax.servlet.jsp.tagext.SimpleTag)
          _jsp_PageTager_3.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jsp_parent_tag));
        else
          _jsp_PageTager_3.setParent((javax.servlet.jsp.tagext.Tag) null);
      }

      return _jsp_PageTager_3;
    }

    void release()
    {
      if (_jsp_FormatDateTag_2 != null)
        _jsp_FormatDateTag_2.release();
      if (_jsp_PageTager_3 != null)
        _jsp_PageTager_3.release();
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
      manager.addTaglibFunctions(_jsp_functionMap, "npage", "/WEB-INF/pagetag.tld");
      com.caucho.jsp.PageContextImpl pageContext = new com.caucho.jsp.InitPageContextImpl(webApp, this);
      _caucho_expr_0 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${search.carstyleId }");
      _caucho_expr_1 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${citys}");
      _caucho_expr_2 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${city.value.id}");
      _caucho_expr_3 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${search.cityId==city.value.id?\"selected\":\"\" }");
      _caucho_expr_4 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${city.value.orderName}");
      _caucho_expr_5 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${brands}");
      _caucho_expr_6 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${brand.key}");
      _caucho_expr_7 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${search.brandId==brand.key?\"selected\":\"\"}");
      _caucho_expr_8 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${brand.value.orderName}");
      _caucho_expr_9 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${search.stateName!=null && search.stateName==1 }");
      _caucho_expr_10 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${search.stateName!=null && search.stateName==0 }");
      _caucho_expr_11 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${groupbuyList}");
      _caucho_expr_12 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.id}");
      _caucho_expr_13 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${search.carstyleId==null}");
      _caucho_expr_14 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.cityId}");
      _caucho_expr_15 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.brandId}");
      _caucho_expr_16 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.title}");
      _caucho_expr_17 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${func:getDistrictName(data.cityId)}");
      _caucho_expr_18 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${func:getBrandName(data.brandId)}");
      _caucho_expr_19 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.salerName}");
      _caucho_expr_20 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.groupbuyDate}");
      _caucho_expr_21 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.stateName}");
      _caucho_expr_22 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.delName}");
      _caucho_expr_23 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${addAction}");
      _caucho_expr_24 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.upToken}");
      _caucho_expr_25 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${delAction}");
      _caucho_expr_26 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.isdel==0?-1:0}");
      _caucho_expr_27 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${data.isdel==0?\"\u5220\u9664\":\"\u6062\u590d\"}");
      _caucho_expr_28 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${pb}");
      _caucho_expr_29 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${search.carstyleId}");
      _caucho_expr_30 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${search.brandId}");
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
  private static com.caucho.el.Expr _caucho_expr_28;
  private static com.caucho.el.Expr _caucho_expr_29;
  private static com.caucho.el.Expr _caucho_expr_30;

  private final static char []_jsp_string4;
  private final static char []_jsp_string38;
  private final static char []_jsp_string34;
  private final static char []_jsp_string44;
  private final static char []_jsp_string5;
  private final static char []_jsp_string36;
  private final static char []_jsp_string3;
  private final static char []_jsp_string32;
  private final static char []_jsp_string15;
  private final static char []_jsp_string10;
  private final static char []_jsp_string16;
  private final static char []_jsp_string39;
  private final static char []_jsp_string7;
  private final static char []_jsp_string30;
  private final static char []_jsp_string13;
  private final static char []_jsp_string18;
  private final static char []_jsp_string1;
  private final static char []_jsp_string12;
  private final static char []_jsp_string45;
  private final static char []_jsp_string41;
  private final static char []_jsp_string27;
  private final static char []_jsp_string25;
  private final static char []_jsp_string9;
  private final static char []_jsp_string24;
  private final static char []_jsp_string21;
  private final static char []_jsp_string37;
  private final static char []_jsp_string22;
  private final static char []_jsp_string26;
  private final static char []_jsp_string17;
  private final static char []_jsp_string8;
  private final static char []_jsp_string19;
  private final static char []_jsp_string31;
  private final static char []_jsp_string42;
  private final static char []_jsp_string6;
  private final static char []_jsp_string2;
  private final static char []_jsp_string43;
  private final static char []_jsp_string14;
  private final static char []_jsp_string29;
  private final static char []_jsp_string0;
  private final static char []_jsp_string20;
  private final static char []_jsp_string35;
  private final static char []_jsp_string40;
  private final static char []_jsp_string23;
  private final static char []_jsp_string46;
  private final static char []_jsp_string33;
  private final static char []_jsp_string28;
  private final static char []_jsp_string11;
  static {
    _jsp_string4 = "\n							<option value=\"".toCharArray();
    _jsp_string38 = "'==data[i].id?\"selected\":\"\")+'>'+data[i].style+'</option>');\n				}\n				/* $.each(data,function(m,n){\n					$('select[name=carstyleId]').append('<option value=\"'+m+'\" '+('".toCharArray();
    _jsp_string34 = "\n	          				</td>\n	          				\n	          			</tr>\n	          		".toCharArray();
    _jsp_string44 = "</td></tr>';\n			});\n			$(that).closest('tr').after(str);\n		},'json');\n	});\n	$('.del').live('click',function(e){\n		e= e || window.event;\n		e.preventDefault();\n		if(window.confirm('\u4f60\u786e\u5b9a\u8981\u5220\u9664\u8be5\u8bb0\u5f55\uff01')){\n		$.ajax({async:false,type:'get',url:$(this).attr('href'),success:function(data){\n			$('#pageForm').submit();\n		}});\n		}\n	});\n	\n	$('#caddbach').live('click',function(e){\n		e= e || window.event;\n		e.preventDefault();\n		var token;\n		if(!$('select[name=cityId]').val()){\n			alert('\u8bf7\u9009\u62e9\u57ce\u5e02!');\n			$('select[name=cityId]').focus();\n			return false;\n		}\n		if(!$('select[name=stateName]').val()){\n			alert('\u8bf7\u9009\u56e2\u8d2d\u72b6\u6001!');\n			$('select[name=stateName]').focus();\n			return false;\n		}\n		if($('select[name=carstyleId]').val() || $('select[name=brandId]').val() ){\n			alert(\"\u6279\u91cf\u4fee\u6539\u4e0d\u80fd\u9009\u62e9\u54c1\u724c\u4ee5\u53ca\u8f66\u578b\uff01\");\n			return false;\n		}\n		var $kevincity=$('select[name=cityId]').val();\n		var $kevinStateName=$('select[name=stateName]').val();\n		$('select[name=cityIdd] option').each(function(){\n			if($(this).val()==$kevincity){\n				$(this).attr(\"selected\",\"selected\");\n			}\n		});\n		$('select[name=stateNamee] option').each(function(){\n			if($(this).val()==$kevinStateName){\n				$(this).attr(\"selected\",\"selected\");\n			}\n		});\n		kevinAjax();\n		$(\"#kevinDiv1\").show();\n	});\n	function clossKevinDic1(){\n		$(\"#kevinDiv1\").hide();\n	}\n	function reply(){\n		  var $content= $.trim($(\"#replyContent\").val());\n		  if(!$('select[name=cityIdd]').val()){\n				alert('\u8bf7\u9009\u62e9\u57ce\u5e02!');\n				$('select[name=cityIdd]').focus();\n				return false;\n			}\n		  if(!$('select[name=stateNamee]').val()){\n				alert('\u8bf7\u9009\u62e9\u56e2\u8d2d\u72b6\u6001!');\n				$('select[name=stateNamee]').focus();\n				return false;\n			}\n			  if($content==null || \"\"==$content){\n				  alert(\"\u8bf7\u586b\u5199\u4eae\u70b9\u5185\u5bb9\uff01\");\n				  return;\n			  }\n			  $(\"#kevinButton\").attr('disabled','disabled');\n			  $(\"#kevinButtonClose\").attr('disabled','disabled');\n			  $.ajax({\n				 	type: \"POST\",\n				    url: \"/groupbuy/list.do\",\n				    data:{cityIdd:$('select[name=cityIdd]').val(),stateName:$('select[name=stateNamee]').val(),content:$content},\n				    success: function(data){\n				    	 $(\"#kevinButton\").removeAttr('disabled');\n						 $(\"#kevinButtonClose\").removeAttr('disabled');\n				    	alert(\"\u6279\u91cf\u4fee\u6539\u6210\u529f\uff0c\u8bf7\u5173\u95ed!\");\n				    	$(\"#form4\").submit();\n				    	$(\"#kevinDiv1\").hide();\n				   }\n				 });\n		  }\n	function kevinAjax(){\n		if(!$('select[name=cityIdd]').val() || !$('select[name=stateNamee]').val()){\n			alert(\"\u8bf7\u9009\u62e9\u57ce\u5e02\u6216\u8005\u5f00\u56e2\u72b6\u6001\uff01\");\n			return false;\n		}\n	$.post(\"/common/getContentByCityId\",{cityId:$('select[name=cityIdd]').val(),stateName:$('select[name=stateNamee]').val()},function(result){\n			$(\"#replyContent\").text(result);\n		},'json');\n	}\n	function brandUpdate(type,sta){\n		var cityId=$(\"#cityId\").val(); \n		var bid=$(\"#bid\").val(); \n		var carstyleId=$(\"#carstyleId\").val();\n		var stateName=$(\"#stateName\").val();\n		var tmeUrl=$(type).attr(\"tmpHref\");\n		var url=\"\";\n		var tmpCpage=$(\".current\").text();\n		\n		if(sta==1){\n			//\u54c1\u724c\n		url=\"/groupbuy/\"+tmeUrl;\n		}else{\n			url=tmeUrl;\n		}\n		if(cityId){\n			url+=\"&tmcCityId=\"+cityId;	\n			}\n		if(bid){\n			url+=\"&tmcBrandId=\"+bid;	\n			}\n		if(carstyleId){\n			url+=\"&tmcCarstyleId=\"+carstyleId;	\n			}\n		if(stateName){\n			url+=\"&tmcStateName=\"+stateName;	\n			}\n		if(tmpCpage){\n			url+=\"&tmpCpage=\"+tmpCpage;\n		}\n		window.location.href=url;\n	}\n	$(function(){\n		var carStyleSta=$(\"#carStyleSta\").val();\n		var bid=$(\"#bid\").val();\n		if(carStyleSta && bid){\n			$.post('/common/getCarstyle',{\"brandId\":bid},function(data){\n				for(var i=0;i< data.length;i++){\n					/* if(carStyleSta=data[i].id){\n						$('select[name=carstyleId]').append('<option selected=selected value=\"'+data[i].id+'\" '+('".toCharArray();
    _jsp_string5 = "\" ".toCharArray();
    _jsp_string36 = "\n            			</div>\n          			</div>\n      			</div>\n	</div>\n</div>\n	<div  id=\"kevinDiv1\"  style=\"position:fixed;display:none;margin-left: auto;margin-right: auto;z-index:9;height:;width:500px;border:1px solid #ddd;left:210px;top:110px;border-bottom: none !important;background-color:#8F8FBD;\">\n				<input type=\"hidden\" id=\"replyID\">\n				<center><font>\u6279\u91cf\u4fee\u6539\u4eae\u70b9</font></center><br>\n				<font>\u57ce\u5e02\uff1a</font>\n				<select   name=\"cityIdd\" onchange=\"kevinAjax()\">\n						<option   value=\"\">\u8bf7\u9009\u62e9</option>\n						".toCharArray();
    _jsp_string3 = "\">\n          <form method='post' action='/groupbuy/list' id=\"form4\" >\n          	<div class=\"pd5\">\n          		<label class=\"pr15\">\u9009\u62e9\u57ce\u5e02:\n          			<select id=\"cityId\" name=\"cityId\">\n						<option value=\"\">\u8bf7\u9009\u62e9</option>\n						".toCharArray();
    _jsp_string32 = "\">".toCharArray();
    _jsp_string15 = "</td>\n	          				<td>\n	          					".toCharArray();
    _jsp_string10 = " value=\"1\">\u5f00\u56e2</option>\n						<option  ".toCharArray();
    _jsp_string16 = "\n	          						<img src=\"/images/11.gif\" id=\"show_list\" data-value=\"".toCharArray();
    _jsp_string39 = "'==m.id?\"selected\":\"\")+'>'+n.style+'</option>');\n				}); */\n			},'json');\n		}\n		\n	});\n	if('".toCharArray();
    _jsp_string7 = "\n					</select>\n          		</label>\n          		<label class=\"pr15\">\u9009\u62e9\u54c1\u724c:\n          			<select id=\"bid\" name=\"brandId\">\n						<option value=\"\">\u8bf7\u9009\u62e9</option>\n						".toCharArray();
    _jsp_string30 = "&id=".toCharArray();
    _jsp_string13 = "\n          	</div>\n          </form>\n         </div>\n	</div>\n	<div class=\"rb-con\">\n		<div class=\"over-auto\">\n			<table class=\"table table-bordered chargeTable\">\n				<thead>\n					<th>\u7f16\u53f7</th>\n					<th>\u6807\u9898</th>\n					<th>\u57ce\u5e02</th>\n					<th>\u54c1\u724c</th>\n					<th>\u56e2\u957f</th>\n					<th>\u56e2\u8d2d\u65f6\u95f4</th>\n					<th>\u56e2\u8d2d\u72b6\u6001</th>\n					<th>\u72b6\u6001</th>\n					<th>\u64cd\u4f5c</th>\n	          	</thead>\n	          	<tbody>\n	          		".toCharArray();
    _jsp_string18 = "\n	          					".toCharArray();
    _jsp_string1 = "\n<title>\u56e2\u8d2d\u5217\u8868</title>\n</head>\n\n\n<body>\n".toCharArray();
    _jsp_string12 = "\n          		<a id=\"badd\" href=\"/groupbuy/badd\" class=\"btn btn-info\">\u6dfb\u52a0\u54c1\u724c\u56e2\u8d2d</a>&nbsp;\n          		<a id=\"cadd\" href=\"/groupbuy/cadd\" class=\"btn btn-info\">\u6dfb\u52a0\u8f66\u578b\u56e2\u8d2d</a>\n          		<a id=\"caddbach\" href=\"#\" class=\"btn btn-info\">\u6279\u91cf\u4fee\u6539\u4eae\u70b9</a>\n          		".toCharArray();
    _jsp_string45 = "'==data[i].id?\"selected\":\"\")+'>'+data[i].style+'</option>');	\n					}else{\n						\n					} */\n					$('select[name=carstyleId]').append('<option value=\"'+data[i].id+'\" '+('".toCharArray();
    _jsp_string41 = "'){\n		$('select[name=brandId]').change();\n	}\n	$('#badd').live('click',function(e){\n		e= e || window.event;\n		e.preventDefault();\n		var token;\n		if(!$('select[name=cityId]').val()){\n			alert('\u8bf7\u9009\u62e9\u57ce\u5e02!');\n			$('select[name=cityId]').focus();\n			return false;\n		}\n		if(!$('select[name=brandId]').val()){\n			alert('\u8bf7\u9009\u62e9\u54c1\u724c!');\n			$('select[name=brandId]').focus();\n			return false;\n		}\n		$.ajax({async:false,type:'post',url:'/common/checkgroupbuy',dataType:'json',data:{cityId:$('select[name=cityId]').val(),brandId:$('select[name=brandId]').val(),carstyleId:0},success:function(data){\n			token=data;\n		}});\n		if(!token){\n			alert('\u8be5\u57ce\u5e02\u4e0b\u8be5\u54c1\u724c\u56e2\u8d2d\u5df2\u7ecf\u5b58\u5728');\n			return false;\n		}\n		window.location.href=$(this).attr('href')+'?cityId='+$('select[name=cityId]').val()+'&brandId='+$('select[name=brandId]').val()+'&token='+token;\n		\n	});\n	\n	\n	$('#cadd').live('click',function(e){\n		e= e || window.event;\n		e.preventDefault();\n		var token;\n		if(!$('select[name=cityId]').val()){\n			alert('\u8bf7\u9009\u62e9\u57ce\u5e02!');\n			$('select[name=cityId]').focus();\n			return false;\n		}\n		if(!$('select[name=brandId]').val()){\n			alert('\u8bf7\u9009\u62e9\u54c1\u724c!');\n			$('select[name=brandId]').focus();\n			return false;\n		}\n		if(!$('select[name=carstyleId]').val()){\n			alert('\u8bf7\u9009\u62e9\u8f66\u578b!');\n			$('select[name=carstyleId]').focus();\n			return false;\n		}\n		$.ajax({async:false,type:'post',url:'/common/checkgroupbuy',dataType:'json',data:{cityId:$('select[name=cityId]').val(),carstyleId:$('select[name=carstyleId]').val(),brandId:$('select[name=brandId]').val()},success:function(data){\n			token=data;\n		}});\n		if(!token){\n			alert('\u8be5\u57ce\u5e02\u4e0b\u8be5\u8f66\u578b\u56e2\u8d2d\u5df2\u7ecf\u5b58\u5728\u6216\u8005\u54c1\u724c\u56e2\u8d2d\u4e0d\u5b58\u5728');\n			return false;\n		}\n		window.location.href=$(this).attr('href')+'?cityId='+$('select[name=cityId]').val()+'&carstyleId='+$('select[name=carstyleId]').val()+'&token='+token+\"&brandId=\"+$('select[name=brandId]').val();\n	});\n	$(\"[name='vv']\").click(function(){\n		alert(1);\n	});\n	$('#show_list').live('click',function(){\n		$(this).attr(\"src\",$(this).attr(\"src\")==\"/images/11.gif\"?\"/images/12.gif\":\"/images/11.gif\");\n		var cla=$('.'+$(this).attr(\"data-value\"));\n		if(cla.size()>0){\n			cla.css('display')==\"none\"?cla.show():cla.hide();\n			return false;\n		}\n		var that=this;\n		$.post(\"/common/getCarstyleGroupbuy\",{data:$(this).attr(\"data-value\")},function(result){\n			var str='';\n			$.each(result,function(m,n){\n				str+='<tr  class=\"'+$(that).attr(\"data-value\")+'\"><td>'+result[m].id+'</td><td><em><img src=\"/images/tree.gif\" /></em>&nbsp;'+result[m].title+'</td><td>'+result[m].cityName+'</td><td>'+result[m].carstyleName+'</td><td>'+result[m].salerName+'</td><td>'+result[m].groupbuyDateStr+'</td><td>'+result[m].stateName+'</td><td>'+result[m].delName+'</td><td>".toCharArray();
    _jsp_string27 = "\n	          				&nbsp;<a class=\"del\" href=\"".toCharArray();
    _jsp_string25 = "\" onclick=\"brandUpdate(this,1)\">\u4fee\u6539</a>\n	          				".toCharArray();
    _jsp_string9 = " selected='selected'".toCharArray();
    _jsp_string24 = "&token=".toCharArray();
    _jsp_string21 = "</td>\n	          				<td>\n	          				".toCharArray();
    _jsp_string37 = "\n					</select>\n					<font>\u56e2\u8d2d\u72b6\u6001\uff1a</font>\n					<select onchange=\"kevinAjax()\" name=\"stateNamee\">\n						<option  value=\"\">\u8bf7\u9009\u62e9</option>\n						<option  value=\"1\">\u5f00\u56e2</option>\n						<option  value=\"0\">\u672a\u5f00\u56e2</option>\n					</select>\n					<center><textarea id=\"replyContent\" name=\"content\" style=\"width: 450px;height: 150px\" rows=\"3\" cols=\"31\" maxlength=\"250\"></textarea><br> <input id=\"kevinButton\" type=\"button\" value=\"\u6279\u91cf\u4fee\u6539\" onclick=\"reply()\"> <input id=\"kevinButtonClose\" onclick=\"clossKevinDic1()\" type=\"button\" value=\"\u5173\u95ed\"></center>\n			</div>\n<script type=\"text/javascript\" charset=\"utf-8\">\n	$('select[name=brandId]').live('change',function(){\n		$('select[name=carstyleId] option:gt(0)').remove();\n		if($(this).val()){\n			$.post('/common/getCarstyleOk',{\"brandId\":$(this).val()},function(data){\n				for(var i=0;i< data.length;i++){\n					$('select[name=carstyleId]').append('<option value=\"'+data[i].id+'\" '+('".toCharArray();
    _jsp_string22 = "\n	          				<a href=\"#\"  tmpHref=\"".toCharArray();
    _jsp_string26 = "\n	          				".toCharArray();
    _jsp_string17 = "\" />&nbsp;&nbsp;\n	          					".toCharArray();
    _jsp_string8 = "\n					</select>\n          		</label>\n          		<label class=\"pr15\">\u9009\u62e9\u8f66\u578b:\n          			<select  id=\"carstyleId\" name=\"carstyleId\">\n						<option value=\"\">\u8bf7\u9009\u62e9</option>\n					</select>\n          		</label>\n          		<label class=\"pr15\">\u56e2\u8d2d\u72b6\u6001:\n          			<select name=\"stateName\" id=\"stateName\">\n						<option  value=\"\">\u8bf7\u9009\u62e9</option>\n						<option ".toCharArray();
    _jsp_string19 = "\n	          				</td>\n	          				<td>".toCharArray();
    _jsp_string31 = "&state=".toCharArray();
    _jsp_string42 = "<a  onclick= brandUpdate(this,2) tmpHref=\"/groupbuy/cadd?id='+result[m].id+'&token='+result[m].upToken+'\">\u4fee\u6539</a>".toCharArray();
    _jsp_string6 = "</option>\n						".toCharArray();
    _jsp_string2 = "\n<div id=\"man_zone\">\n	<div>\n		<div class=\"b-con a-form\">\n		<input type=\"hidden\" id=\"carStyleSta\" value=\"".toCharArray();
    _jsp_string43 = "&nbsp;<a  class=\"del\" href=\"cdel?cityId='+result[m].cityId+'&brandId='+result[m].brandId+'&id='+result[m].id+'&state='+(result[m].isdel==-1?0:-1)+'&token='+result[m].upToken+'\">'+(result[m].isdel==-1?\"\u6062\u590d\":\"\u5220\u9664\")+'</a>".toCharArray();
    _jsp_string14 = "\n	          			<tr>\n	          				<td>".toCharArray();
    _jsp_string29 = "&brandId=".toCharArray();
    _jsp_string0 = "\n\n\n\n\n\n".toCharArray();
    _jsp_string20 = "</td>\n	          				<td>".toCharArray();
    _jsp_string35 = "\n	          	</tbody>\n	        </table>\n	    </div>\n	    <div class=\"table-page\">\n          			<div class=\"page tcenter\">\n            			<div class=\"pageWrap\">\n              				".toCharArray();
    _jsp_string40 = "' || '".toCharArray();
    _jsp_string23 = "?id=".toCharArray();
    _jsp_string46 = "'==data[i].id?\"selected\":\"\")+'>'+data[i].style+'</option>');	\n				}\n			},'json');	\n		}\n	});\n</script>\n</body>\n</html>".toCharArray();
    _jsp_string33 = "</a>\n	          				".toCharArray();
    _jsp_string28 = "?cityId=".toCharArray();
    _jsp_string11 = " value=\"0\">\u672a\u5f00\u56e2</option>\n					</select>\n          		</label>\n          		<input type=\"submit\" value=\"\u67e5\u8be2\" class=\"btn btn-info\"/>&nbsp;\n          		".toCharArray();
  }
}
