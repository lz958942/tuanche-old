package com.tuanche.console.util;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import com.tuanche.bean.che.Brand;
import com.tuanche.bean.che.City;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.bean.MapBean;
import com.tuanche.console.bean.SysFunc;
import com.tuanche.console.bean.SysRole;
import com.tuanche.console.service.EmployeeService;
import com.tuanche.console.service.SysFuncService;
import com.tuanche.console.service.SysRoleFuncService;
import com.tuanche.console.service.SysRoleService;
import com.tuanche.console.util.http.NetClient;
import com.tuanche.smc.domain.base.Style;
import com.tuanche.smc.persistence.read.che100.CarStyleMapper;
import com.tuanche.smc.persistence.read.che100.CityMapper;
import com.tuanche.tj.service.CommonService;



public class ApplicationUtil {
	private static ApplicationUtil application = null;
	public static final ThreadLocal<ApplicationUtil> session = new ThreadLocal<ApplicationUtil>();

	public static ApplicationUtil getInstance() {
		application = session.get();
		if (application == null) {
			application = new ApplicationUtil();
			session.set(application);
		}
		return application;
	}

	/*
	 * 璋冪敤鏂规硶
	 */
	public void update(	ServletContext servletContext , String key, ApplicationContext ctx,String obj) {
		String urls = Resources.getString("host.ip");
		Map parameMap = new HashMap();
		parameMap.put("cpage", 0);
		parameMap.put("pageSize", Integer.MAX_VALUE);
		parameMap.put("orderStr", "id");
		if (urls != null && urls.indexOf(",") > 0) {
			for (String url : urls.split(",")) {
				try {
					NetClient.getHttpResponse(url + "/sys/InitCommon?key=" + key+"&method=update&obj="+obj);
					NetClient.shutdown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		this.runService(ctx, key, servletContext, parameMap,obj);
	}
	
	/*
	 * 璋冪敤鏂规硶
	 */
	public void reload(ServletContext servletContext , String key, ApplicationContext ctx) {
		String urls = Resources.getString("host.ip");
		Map parameMap = new HashMap();
		parameMap.put("cpage", 0);
		parameMap.put("pageSize", Integer.MAX_VALUE);
		parameMap.put("orderStr", "id");
		if (urls != null && urls.indexOf(",") > 0) {
			for (String url : urls.split(",")) {
				try {
					NetClient.getHttpResponse(url + "/sys/InitCommon?key=" + key+"&method=reload");
					NetClient.shutdown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
		}
		this.runService(ctx, key, servletContext, parameMap,null);

	}
	public Object getAppKey(HttpServletRequest request,String key) {
		return request.getSession(true).getServletContext().getAttribute(key);
	}
	/*
	 * 璋冪敤鍏ュ彛
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
		case GlobalConstants.BRAND_MAP:
			this.runBrandMap(ctx, servletContext);
			break;
		case GlobalConstants.DISTRICT_MAP:
			this.runCityMap(ctx, servletContext);
			break;
		case GlobalConstants.CARSTYLE_MAP:
			this.runCarstyleMap(ctx, servletContext);
			break;
		default:
			break;
		}
	}

	public void runALL(ApplicationContext ctx, ServletContext servletContext, Map parameMap) {
		this.runAuth(ctx, servletContext);
		this.runFuncMap(ctx, servletContext, parameMap);
		this.makeAuth(ctx, servletContext, parameMap);
		this.runRoleMap(ctx, servletContext, parameMap);
		this.runBrandMap(ctx, servletContext);
		this.runCityMap(ctx, servletContext);
		this.runCarstyleMap(ctx, servletContext);
		this.runEmployeeMap(ctx, servletContext, parameMap,null);
	}
	
	private void runBrandMap(ApplicationContext ctx,ServletContext servletContext) {
		CommonService commonService = (CommonService) ctx.getBean("commonService");
		List<Brand> listBrand = commonService.getBrands();
		GlobalConstants.brandMap.clear();
		for (Brand b : listBrand) {
			GlobalConstants.brandMap.put(b.getId(),b);
		}
	}
	
	
	private void runCarstyleMap(ApplicationContext ctx,ServletContext servletContext) {
		CommonService commonService = (CommonService) ctx.getBean("commonService");
		List<Style> listCarstyle = commonService.getCarstyles();
		GlobalConstants.carstyleMap.clear();
		Map<Integer,String> map=null;
		for (Style b : listCarstyle) {
			
			if(GlobalConstants.brandMap.get(b.getPid())==null) continue;

			if(GlobalConstants.carstyleMap.containsKey(b.getPid()))
			{
				GlobalConstants.carstyleMap.get(b.getPid()).put(b.getId(),b.getName());
						
				continue;
			}
			map=new ConcurrentHashMap<Integer, String>();
			map.put(b.getId(),b.getName());
			GlobalConstants.carstyleMap.put(b.getPid(),map);
		}
		
		
		
		 listCarstyle = commonService.getCarstylesOk();
		GlobalConstants.carstyleOKMap.clear();
		map=null;
		for (Style b : listCarstyle) {
			
			if(GlobalConstants.brandMap.get(b.getPid())==null) continue;

			if(GlobalConstants.carstyleOKMap.containsKey(b.getPid()))
			{
				GlobalConstants.carstyleOKMap.get(b.getPid()).put(b.getId(),b.getName());
						
				continue;
			}
			map=new ConcurrentHashMap<Integer, String>();
			map.put(b.getId(),b.getName());
			GlobalConstants.carstyleOKMap.put(b.getPid(),map);
		}
		
		
		
		
		
		
		
	}
	private void runCityMap(ApplicationContext ctx, ServletContext servletContext) {
		CommonService commonService = (CommonService) ctx.getBean("commonService");
		List<City> listCity =commonService.getCitys();
		GlobalConstants.districtMap.clear();
		for (City city: listCity){
			GlobalConstants.districtIdCitycodeMap.put(city.getId(),city.getCityCode());
			GlobalConstants.districtMap.put(city.getCityCode(),city);
		}
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
			String keywordRoleIds=Resources.getString("keyword.roleids");
			String[] keywordRoleIdsStr=keywordRoleIds.split(";");
			for (MapBean bean : listEmployee) {
				if(bean.getIsdel()==0){
					GlobalConstants.employeeMap.put(bean.getId(), bean.getName());
					if(keywordRoleIdsStr!=null&&bean.getRoleIds()!=null){
						for(String tmp:keywordRoleIdsStr){
							if(StringUtils.isNotEmptyString(tmp)){
								if((bean.getRoleIds()).indexOf(tmp)>=0){
									GlobalConstants.editEmployeeMap.put(bean.getId(), bean.getName());
								    break;
								}
							}
						}
					}
					
					//System.out.println(bean.getRoleIds());
				}else{
					//System.out.println("============================="+GlobalConstants.delEmployeeMap);
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
			List<SysFunc> funList1 = sysFuncService.selectSysFuncList(parameMap);// 绯荤粺鑺傜偣
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


	@SuppressWarnings("unchecked")
    private void makeAuth(ApplicationContext ctx, ServletContext servletContext, Map parameMap) {
		SysRoleService sysRoleService = (SysRoleService) ctx.getBean("sysRoleService");
		SysFuncService sysFuncService = (SysFuncService) ctx.getBean("sysFuncService");
		List<SysRole> list = sysRoleService.selectSysRoleList(parameMap);
		parameMap.put("parentId", StringUtils.strToInt(Resources.getString("func.pid")));
		parameMap.put("funcStatus", 1);
		//查找需要显示的父级目录
		List<SysFunc> funList1 = sysFuncService.selectSysFuncList(parameMap);// 绯荤粺鑺傜偣
		MakeAuth authJson = new MakeAuth();
		for (SysRole role : list) {
		    //System.out.println("生成角色权限==>> "+role.getId());
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
}
