package com.tuanche.baseapi.web.interceptor;

import com.tuanche.framework.base.util.Check;
import com.tuanche.framework.base.util.DesUtils;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 抽象拦截器.
 * @author YRJ
 *
 */
public abstract class AbstractHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

	/**
	 * 检测是否以流的方式传输.
	 * @param request
	 * @return
	 */
	protected boolean checkStream(final HttpServletRequest request) {
		return MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType());
	}

	/**
	 * 从请求头中读取Des，用于后续请求响应加解密操作.
	 * @param request
	 * @return
	 */
	protected boolean getDesHeader(final HttpServletRequest request) {
		return Boolean.parseBoolean(request.getHeader("des"));
	}

	/**
	 * 向客户端写入异常信息.
	 * @param request
	 * @param response
	 * @param msg
	 */
	protected void responseErrorMsg(final HttpServletRequest request, final ServletResponse response, final Object msg) {
		if (Check.NuNObj(msg)) {
			return;
		}

		String message = msg.toString();
		try {
			if (getDesHeader(request)) {
				message = DesUtils.encrypt(message);
			}
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(message);
			response.flushBuffer();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
