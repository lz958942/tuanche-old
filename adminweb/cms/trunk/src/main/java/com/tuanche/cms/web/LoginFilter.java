package com.tuanche.cms.web;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tuanche.cms.bean.Employee;
import com.tuanche.cms.util.GlobalConstants;
import com.tuanche.cms.util.Resources;

public class LoginFilter implements Filter {

	private Logger logger = Logger.getLogger(LoginFilter.class);
	public static String DESKEY = "";
	Pattern pattern = null;

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Employee emp = null;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String url = httpServletRequest.getRequestURI();
		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) {
			chain.doFilter(request, response);
		} else {
			if (url.lastIndexOf(".") > 0) {
				url = url.substring(0, url.lastIndexOf("."));
			}
				emp = (Employee) httpServletRequest.getSession(true).getAttribute(GlobalConstants.SESSION_EMP);
				System.out.println(emp);
				if (emp == null) {
					httpServletRequest.setAttribute(GlobalConstants.ERROR_KEY, "error.session");
					httpServletRequest.getRequestDispatcher(GlobalConstants.USER_HOME).forward(request, response);
					return;
				} else {
					String noF = null;
					try {
						//noF = PropertiesUtil.getApplicationProperty("exclude");
						noF=Resources.getString("exclude");
					} catch (Exception e) {
						e.printStackTrace();
					}
					boolean checkauth = AuthUtil.checkAuth(httpServletRequest, url, emp);
					if(noF!="" && noF.indexOf(url)!=-1){
						checkauth = true;
					}
					if (!checkauth) {
						request.setAttribute(GlobalConstants.ERROR_KEY, "error.nopower");
						request.getRequestDispatcher(GlobalConstants.ERROR_PAGE).forward(request, response);
						return;
					}
				}

			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		pattern = Pattern.compile(GlobalConstants.EXCLUDE_DIRECTORY_REGEX);

	}
}
