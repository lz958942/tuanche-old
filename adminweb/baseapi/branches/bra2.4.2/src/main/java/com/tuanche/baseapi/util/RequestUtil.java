package com.tuanche.baseapi.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuanche.framework.base.util.Check;
import com.tuanche.framework.base.util.IOHelper;

/**
 * <p>
 * 获取请求的参数
 * </p>
 * 
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 * 
 * @author afi on 2015/6/2 0025.
 * @version 1.0
 * @since 1.0
 */
public class RequestUtil {

	@SuppressWarnings("unused")
	private final static Logger logger = LoggerFactory.getLogger(RequestUtil.class);

	/**
	 * 获取request的参数信息
	 * 
	 * @param request
	 * @return
	 */
	public static String getBodyStr(final HttpServletRequest request) throws Exception {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			final InputStream ips = request.getInputStream();
			IOHelper.copyStream(ips, baos);

		} catch (final IOException e) {
			throw e;
		}
		final String body = new String(baos.toByteArray(), "utf-8");
		if (Check.NuNStr(body)) {
			return "";
		} else {
			return body;
		}
	}
}
