package com.tuanche.cms.web;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tuanche.cms.bean.Employee;
import com.tuanche.cms.util.GlobalConstants;



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
								break;
							}
							for (String urlStr : list) {
								if (urlStr!=null&&url.contains(urlStr)) {
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

}
