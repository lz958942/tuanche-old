package com.tuanche.baseapi.web.abs;

import com.tuanche.baseapi.web.header.Header;
import com.tuanche.baseapi.web.interceptor.HeadersInterceptor;
import com.tuanche.baseapi.web.interceptor.ParamCollector;
import com.tuanche.framework.base.util.Check;
import com.tuanche.framework.base.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>抽象的 Controller</p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi  on n 2016/1/23.13:53
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractController {

    /**
     * 将接收的参数转换为指定对象.
     * @param request
     * @param tClass
     * @return
     */
    protected final <T> T getEntity(final HttpServletRequest request, final Class<T> tClass) {
        final String params = (String) request.getAttribute(ParamCollector.PARAMS);
        if (Check.NuNStrStrict(params)) {
            return null;
        }

        return JsonUtil.json2Entity(params, tClass);
    }

    /**
     * 获取请求头信息.
     * @param request
     * @return
     */
    protected final Header getHeader(final HttpServletRequest request) {
        return (Header) request.getAttribute(HeadersInterceptor.HEADER);
    }

    /**
     * 从请求头中读取Des, 用于后续请求响应加解密操作.
     * @param request
     * @return
     */
    protected boolean getDesHeader(final HttpServletRequest request) {
        final Header header = getHeader(request);
        return header.getDes();
    }

    /**
     * 获取用户ID.
     * @param request
     * @return
     */
    protected Long getUserIdByRequest(final HttpServletRequest request) {
        final Object userId = request.getAttribute("userid");
        if (Check.NuNObj(userId)) {
            return 0L;
        }
        return (Long) userId;
    }
}
