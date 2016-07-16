package com.tuanche.baseapi.web.interceptor.collector;

import com.tuanche.framework.base.util.DesUtils;
import org.springframework.stereotype.Service;

import java.io.OutputStream;

/**
 * 流参数收集器.
 * @author YRJ
 *
 */
@Service(value = "streamCollector")
public class StreamCollector implements Collector<OutputStream, Boolean, String> {

	public String collector(final OutputStream t, final Boolean v) {
		if (t == null) {
			return null;
		}

		String body = t.toString();
		if (v) {
			body = DesUtils.decrypt(body);
		}

		return body;
	}

}
