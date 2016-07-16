package com.tuanche.console.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tuanche.bean.che.Brand;
import com.tuanche.bean.che.City;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.bean.SysAuthEmp;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.smc.common.ServletRequestHolder;



public class AuthUtil {
	private static Logger logger = Logger.getLogger(AuthUtil.class);
	private static Pattern pattern = Pattern.compile(GlobalConstants.EXCLUDE_ONLY_LOGIN);

	/*
	 * 权限检查
	 */
	public static boolean checkAuth(HttpServletRequest request, String url) {
		Employee emp = (Employee) request.getSession(true).getAttribute(GlobalConstants.SESSION_EMP);
		if(emp==null||emp.getRoleIds()==null){
			return Boolean.FALSE;
		}else{
			return checkAuth(request, url, emp);
		}
	}
	/** 
	* @author yangzs
	* @Title: checkAuth 
	* @Description: 重构检查权限
	* @param @param url
	* @param @return     
	* @return boolean    
	* @throws 
	*/
	public static boolean checkAuth( String url) {
	    HttpServletRequest request = ServletRequestHolder.getRequest();
        Employee emp = (Employee) request .getSession(true).getAttribute(GlobalConstants.SESSION_EMP);
	    if(emp==null||emp.getRoleIds()==null){
	        return Boolean.FALSE;
	    }else{
	        return checkAuth(request, url, emp);
	    }
	}
	
	
	public static boolean checkAuth(HttpServletRequest request, String url, Employee emp) {
		if(emp==null||emp.getRoleIds()==null){
			return Boolean.FALSE;
		}else{
			if (emp.getRoleIds().indexOf(GlobalConstants.ROLE_SUPPER_ID) >= 0) {
				return Boolean.TRUE;
			} else {
				Matcher matcher = pattern.matcher(url); //是否仅需要登录即可
				boolean checkauth = false;
				if (matcher.find()){
					checkauth=Boolean.TRUE;
				}else{
				Map<Integer, List<String>> roleMap = (Map) request.getSession(true).getServletContext().getAttribute(GlobalConstants.ROLE_URL_MAP);
					for (String roleId : emp.getRoleIds().split(",")) {
						if (roleId != null && roleId.length() > 0) {
							List<String> list = (List<String>) roleMap.get(Integer.valueOf(roleId));
							if (list == null || list.isEmpty()) {
								continue;
							}
							for (String urlStr : list) {
							    if(urlStr!=null&&urlStr.endsWith(".do")){
							        String substring = urlStr.substring(0, urlStr.lastIndexOf(".do"));
							        if(url.startsWith(substring)){
							            return true;
							        }
							    }
							    System.out.println(urlStr+"=====000=========="+url);   
								if (urlStr!=null&&urlStr.equalsIgnoreCase(url)) {
									checkauth = true;
									return Boolean.TRUE;
								}
							}
		
						}
					}
				}
				return checkauth;
			}
		}
	}
	
	public static Map<String,City> checkedCityMap(HttpServletRequest request) {
		 Map<String,City> newcityCodeMap=new LinkedHashMap<String, City>();
		 for(String s:GlobalConstants.districtMap.keySet()){
			 newcityCodeMap.put(s,GlobalConstants.districtMap.get(s));
		 }
		 return newcityCodeMap;
	}

	public static Map<Integer,Brand> checkedBrandMap(HttpServletRequest request) {
		Map<Integer,Brand> newBrandMap=new LinkedHashMap<Integer,Brand>();
		for(Integer s:GlobalConstants.brandMap.keySet()){
			newBrandMap.put(s,GlobalConstants.brandMap.get(s));
		}
		return newBrandMap;
	}
}
