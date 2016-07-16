package com.tuanche.baseapi.web.interceptor.collector;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service(value = "commonCollector")
public class CommonCollector implements Collector<HttpServletRequest, Void, String> {

	private final static Logger logger = LoggerFactory.getLogger(CommonCollector.class);

	public String collector(final HttpServletRequest request, final Void v) {
		final Map<String, Object> params = new HashMap<String, Object>();

		final Enumeration<String> paramNames = request.getParameterNames();
		for (; paramNames.hasMoreElements();) {
			final String name = paramNames.nextElement();
			final String value = request.getParameter(name);

			params.put(name, value);
		}

		if (params.isEmpty()) {
			return null;
		}

		final ObjectMapper mapper = new ObjectMapper();
		String value = null;
		try {
			value = mapper.writeValueAsString(params);
		} catch (final IOException ignore) {
			logger.error("request params transfer to json failture.", ignore);
		}
		return value;
	}

}
