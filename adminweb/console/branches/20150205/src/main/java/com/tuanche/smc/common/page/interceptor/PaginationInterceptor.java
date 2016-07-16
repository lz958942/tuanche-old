package com.tuanche.smc.common.page.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.tuanche.smc.common.page.impl.Pagination;

/**
 * 
 * @author Administrator 分页拦截器
 */
public class PaginationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String pageSize = request.getParameter("page.pageSize");
		String pageNo = request.getParameter("page.pageNo");
		Pagination pagination = new Pagination();
		//author :ningtao 请不要修改
		if("0".equals(pageNo)){
			pagination.setPageNo(0);
		}
		if (pageSize != null) {
			pagination.setPageSize(Integer.parseInt(pageSize));
			pagination.setPageNo(Integer.parseInt(pageNo));
		}
		Pagination.threadLocal.set(pagination);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
		Pagination pagination = Pagination.threadLocal.get();
		if (pagination != null) {
			request.setAttribute("page", pagination);
			// Sevlet容器有可能使用线程池，所以必须手动清空线程变量。
			Pagination.threadLocal.remove();
		}
	}

}