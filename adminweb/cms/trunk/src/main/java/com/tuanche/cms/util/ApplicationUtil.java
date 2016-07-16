package com.tuanche.cms.util;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tuanche.cms.bean.Employee;
import com.tuanche.cms.bean.MapBean;
import com.tuanche.cms.bean.SysFunc;
import com.tuanche.cms.bean.SysRole;
import com.tuanche.cms.service.EmployeeService;
import com.tuanche.cms.service.SysFuncService;
import com.tuanche.cms.service.SysRoleFuncService;
import com.tuanche.cms.service.SysRoleService;



public class ApplicationUtil {
	private static ApplicationUtil application = null;
	public static final ThreadLocal<ApplicationUtil> session = new ThreadLocal<ApplicationUtil>();
	public static final String SESSION_EMP = "emp_session";
	
	public static ApplicationUtil getInstance() {
		application = session.get();
		if (application == null) {
			application = new ApplicationUtil();
			session.set(application);
		}
		return application;
	}

	/*
	 * 调用方法
	 */
	public void update(	ServletContext servletContext , String key, ApplicationContext ctx,String obj) {
		String urls = Resources.getString("host.ip");
		Map parameMap = new HashMap();
		parameMap.put("cpage", 0);
		parameMap.put("pageSize", Integer.MAX_VALUE);
		parameMap.put("orderStr", "id");
		
		this.runService(ctx, key, servletContext, parameMap,obj);
	}
	
	/*
	 * 调用方法
	 */
	public void reload(ServletContext servletContext , String key, ApplicationContext ctx) {
		String urls = Resources.getString("host.ip");
		Map parameMap = new HashMap();
		parameMap.put("cpage", 0);
		parameMap.put("pageSize", Integer.MAX_VALUE);
		parameMap.put("orderStr", "id");
		
		this.runService(ctx, key, servletContext, parameMap,null);

	}
	public Object getAppKey(HttpServletRequest request,String key) {
		return request.getSession(true).getServletContext().getAttribute(key);
	}
	/*
	 * 调用入口
	 */
	public void runService(ApplicationContext ctx, String serviceName, ServletContext servletContext, Map parameMap,String obj) {
		switch (serviceName) {
		case GlobalConstants.ROLE_MAP:
			this.runRoleMap(ctx, servletContext, parameMap);
			break;
		case GlobalConstants.FUNC_MAP:
			this.runFuncMap(ctx, servletContext, parameMap);
			break;
		case GlobalConstants.EMPLOYEE_MAP:
			this.runEmployeeMap(ctx, servletContext, parameMap,obj);
			break;
		case GlobalConstants.MAKE_AUTH:
			this.makeAuth(ctx, servletContext, parameMap);
			break;
		case GlobalConstants.ROLE_FUN_ID:
			this.runRoleFuncID(ctx, servletContext,parameMap, obj);
			break;
		default:
			break;
		}
		/*if(GlobalConstants.ROLE_MAP.equals(serviceName)){
			this.runRoleMap(ctx, servletContext, parameMap);
		}else if(GlobalConstants.FUNC_MAP.equals(serviceName)){
			this.runFuncMap(ctx, servletContext, parameMap);
		}else if(GlobalConstants.EMPLOYEE_MAP.equals(serviceName)){
			this.runEmployeeMap(ctx, servletContext, parameMap,obj);
		}else if(GlobalConstants.MAKE_AUTH.equals(serviceName)){
			this.makeAuth(ctx, servletContext, parameMap);
		}else if(GlobalConstants.ROLE_FUN_ID.equals(serviceName)){
			this.runRoleFuncID(ctx, servletContext,parameMap, obj);
		}*/
		
	}

	public void runALL(ApplicationContext ctx, ServletContext servletContext, Map parameMap) {
		this.runAuth(ctx, servletContext);
		this.runFuncMap(ctx, servletContext, parameMap);
		this.makeAuth(ctx, servletContext, parameMap);
		this.runRoleMap(ctx, servletContext, parameMap);
		this.runEmployeeMap(ctx, servletContext, parameMap,null);
	}

	private void runEmployeeMap(ApplicationContext ctx, ServletContext servletContext, Map parameMap,String obj) {
		EmployeeService employService = (EmployeeService) ctx.getBean("employeeService");
		if(obj!=null&&obj.length()>0){
			Employee employee=employService.selectEmployeeById(Integer.parseInt(obj));
			GlobalConstants.employeeMap.remove(employee.getId());
			GlobalConstants.employeeMap.put(employee.getId(), employee.getEmpName());
		}else{
			Map map=new  HashMap();
			map.put("isdel",0);
			List<MapBean> listEmployee = employService.selectEmployeeInit(map);
			GlobalConstants.employeeMap.clear();
			for (MapBean bean : listEmployee) {
				if(bean.getIsdel()==0){
					GlobalConstants.employeeMap.put(bean.getId(), bean.getName());
				}else{
					GlobalConstants.delEmployeeMap.put(bean.getId(), bean.getName());
				}
			}
		}
	}


	private void runRoleMap(ApplicationContext ctx, ServletContext servletContext, Map parameMap) {
		ConcurrentMap<Integer, String> roleMap = new ConcurrentHashMap();
		SysRoleService sysRoleService = (SysRoleService) ctx.getBean("sysRoleService");
		List<SysRole> list = sysRoleService.selectSysRoleList(parameMap);
		for (SysRole role : list) {
			roleMap.put(role.getId(), role.getRoleName());
		}
		servletContext.removeAttribute(GlobalConstants.ROLE_MAP);
		servletContext.setAttribute(GlobalConstants.ROLE_MAP, roleMap);
	}

	private void runFuncMap(ApplicationContext ctx, ServletContext servletContext, Map parameMap) {
		ConcurrentMap<Integer, String> funcMap = new ConcurrentHashMap();
		SysFuncService sysFuncService = (SysFuncService) ctx.getBean("sysFuncService");
		List<SysFunc> funcList = sysFuncService.selectSysFuncList(parameMap);
		for (SysFunc func : funcList) {
			funcMap.put(func.getId(), func.getFuncName());
		}
		servletContext.removeAttribute(GlobalConstants.FUNC_MAP);
		servletContext.setAttribute(GlobalConstants.FUNC_MAP, funcMap);
	}




	private void runAuth(ApplicationContext ctx, ServletContext servletContext) {
		SysRoleFuncService sysRoleFuncService = (SysRoleFuncService) ctx.getBean("sysRoleFuncService");
		ConcurrentMap<Integer, List<String>> authMap = null;
		authMap = sysRoleFuncService.selectRoleAuth();
		servletContext.removeAttribute(GlobalConstants.ROLE_URL_MAP);
		servletContext.setAttribute(GlobalConstants.ROLE_URL_MAP, authMap);
	}
	private void runRoleFuncID(ApplicationContext ctx, ServletContext servletContext,Map parameMap,String obj) {
		if(obj!=null&&obj.length()>0){
			int roleId=Integer.parseInt(obj);
			SysRoleFuncService sysRoleFuncService = (SysRoleFuncService) ctx.getBean("sysRoleFuncService");
			SysFuncService sysFuncService = (SysFuncService) ctx.getBean("sysFuncService");
			ConcurrentMap<Integer, List<String>> authMap=(ConcurrentMap<Integer, List<String>>) servletContext.getAttribute(GlobalConstants.ROLE_URL_MAP);
			authMap.remove(Integer.parseInt(obj));
			authMap.put(roleId, sysRoleFuncService.selectSysRoleFuncURL(roleId));
			servletContext.removeAttribute(GlobalConstants.ROLE_URL_MAP);
			servletContext.setAttribute(GlobalConstants.ROLE_URL_MAP, authMap);
			parameMap.put("parentId", 0);
			parameMap.put("funcStatus", 1);
			parameMap.put("isMenu", 1);
			parameMap.put("bizCode", "cms");
			List<SysFunc> funList1 = sysFuncService.selectSysFuncList(parameMap);// 系统节点
			MakeAuth authJson = new MakeAuth();
			String path = servletContext.getRealPath("") + "/inc/auth/" +roleId + ".js";
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(path);
				Writer out = new OutputStreamWriter(fos, "UTF-8");
				String temp=null;
				if (1 == roleId) {
					temp=authJson.makeSupper(sysFuncService, funList1);
					out.write(temp);
				} else {
					temp=authJson.listAuthByRoleNo(roleId, sysFuncService, funList1);
					out.write(temp);
				}
				out.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}


	private void makeAuth(ApplicationContext ctx, ServletContext servletContext, Map parameMap) {
		SysRoleService sysRoleService = (SysRoleService) ctx.getBean("sysRoleService");
		SysFuncService sysFuncService = (SysFuncService) ctx.getBean("sysFuncService");
		List<SysRole> list = sysRoleService.selectSysRoleList(parameMap);
		parameMap.put("parentId", 0);
		parameMap.put("funcStatus", 1);
		parameMap.put("bizCode", "cms");
		List<SysFunc> funList1 = sysFuncService.selectSysFuncList(parameMap);// 系统节点
		MakeAuth authJson = new MakeAuth();
		for (SysRole role : list) {
			String path = servletContext.getRealPath("") + "inc/auth/" + role.getId() + ".js";
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(path);
				Writer out = new OutputStreamWriter(fos, "UTF-8");
				if (1 == role.getId()) {
					out.write(authJson.makeSupper(sysFuncService, funList1));
				} else {
					out.write(authJson.listAuthByRoleNo(role.getId(), sysFuncService, funList1));
				}
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	public static Employee getEmployee( ){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
		return  (Employee) request.getSession(true).getAttribute(SESSION_EMP);
	}
}
