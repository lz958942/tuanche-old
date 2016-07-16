package com.tuanche.baseapi.web.interceptor;

import com.tuanche.framework.base.util.Check;
import com.tuanche.baseapi.web.header.Header;
import org.apache.http.HeaderIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;

/**
 * 请求头拦截器.
 * <p> 负责从请求头中获取必要的参数列表, 并将参数列表统一放入 request 范围. 便于程序使用.</p>
 * @author YRJ
 *
 */
public class HeadersInterceptor extends HandlerInterceptorAdapter {

	private final static Logger logger = LoggerFactory.getLogger(HeadersInterceptor.class);

	/** 请求头属性名 */
	public final static String HEADER = HeaderIterator.class.getName() + ".Header";

	/** Token值属性名. 用于标识用户是否登陆. */
	public final static String TOKEN = HeadersInterceptor.class.getName() + ".Token";

	/** 是否输出请求头信息. */
	private boolean isPrint;

	public void setPrint(final boolean isPrint) {
		this.isPrint = isPrint;
	}

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {

		final String encoding = request.getHeader("Accept-Encoding");
		final String uAgent = request.getHeader("User-Agent");
		final String des = request.getHeader("des");

		final String traceinfo = request.getHeader("traceinfo");

		final Header header = matchTraceInfoToHeader(encoding, uAgent, des, traceinfo);

		final String token = request.getParameter("token");
		request.setAttribute(HEADER, header);
		request.setAttribute(TOKEN, token);

		if (isPrint) {
			logger.info("header: {}, token: {}.", header, token);
		}

		return true;
	}

	/**
	 * 解析请求头, 构建Header信息.
	 * @param traceinfo
	 * @return
	 */
	private Header matchTraceInfoToHeader(final String encoding, final String uAgent, final String des, final String traceinfo) {
		final Header header = new Header(encoding, uAgent, des);
		if (Check.NuNStrStrict(traceinfo)) {
			return header;
		}

		final String[] traceInfos = traceinfo.split(";");
		for (final String info : traceInfos) {
			if (Check.NuNStr(info)) {
				continue;
			}

			final int indexOf = info.indexOf('=');
			if (indexOf == -1) {
				continue;
			}

			setParameters(header, info, indexOf);
		}

		return header;
	}

	/**
	 * 为 Header 赋值, 通过 Field 反射实现.
	 * @param header
	 * @param info
	 * @param indexOf
	 */
	private void setParameters(final Header header, final String info, final int indexOf) {
		final String paramName = info.substring(0, indexOf);
		final String paramValue = info.substring(indexOf + 1);
		if (Check.NuNObjs(paramName, paramValue)) {
			return;
		}
		try {
			final Field field = header.getClass().getDeclaredField(paramName);
			field.setAccessible(true);

			field.set(header, paramValue);
		} catch (final Exception ignore) {//Ignore all exception.
		}
	}
}
