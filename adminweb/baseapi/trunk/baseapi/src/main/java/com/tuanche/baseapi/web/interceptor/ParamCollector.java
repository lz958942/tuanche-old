package com.tuanche.baseapi.web.interceptor;

import com.tuanche.framework.base.util.IOHelper;
import com.tuanche.baseapi.web.interceptor.collector.Collector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 参数收集器.
 * <p>
 * 如果请求方法为GET，则只需手机 QueryString 后追加的参数，并生成 JSON 数据格式的参数.
 * </p>
 * <p>
 * 如果请求方法为POST，则需要收集包体流，并根据是否加密进行转换，最终生成 JSON 数据格式的参数.
 * </p>
 * 
 * @author YRJ
 * 
 */
@Component(value = "parInterceptor")
public class ParamCollector extends AbstractHandlerInterceptorAdapter {

	private final static Logger logger = LoggerFactory.getLogger(ParamCollector.class);

	public final static String PARAMS = ParamCollector.class.getName() + ".Params";

	@Resource(name = "commonCollector")
	private Collector<HttpServletRequest, Void, String> commonCollector;

	@Resource(name = "streamCollector")
	private Collector<OutputStream, Boolean, String> streamCollector;

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
		String path = request.getServletPath();
		if (path.equals("/pushInfo/recErp") || path.equals("/pushInfo/recErpSms") || path.contains("ferrari")) {
			return true;
		}

		final boolean des = getDesHeader(request);
		response.setHeader("des", String.valueOf(des));

		final boolean stream = checkStream(request);
		final String method = request.getMethod();

		String params = null;
		flag: {
			if (!stream || method.equalsIgnoreCase("GET")) {
				params = commonCollector.collector(request, null);
				break flag;
			}
		}
		request.setAttribute(PARAMS, params);
		return true;
	}

	/**
	 * 拷贝流.
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unused")
	private OutputStream copyStream(final HttpServletRequest request) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			final InputStream ips = request.getInputStream();
			IOHelper.copyStream(ips, baos);
		} catch (final IOException ignore) {
			logger.error("copy reqest inputstream failture.", ignore);
		}
		return baos;
	}

}
