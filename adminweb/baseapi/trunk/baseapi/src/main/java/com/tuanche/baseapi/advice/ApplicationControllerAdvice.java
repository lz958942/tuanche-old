package com.tuanche.baseapi.advice;

import com.tuanche.framework.base.constant.CommonCode;
import com.tuanche.framework.base.entity.DataTransferObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>异常切面</p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi  on n 2016/1/23.13:37
 * @version 1.0
 * @since 1.0
 */
@ControllerAdvice
public class ApplicationControllerAdvice {

	private static Logger logger = LoggerFactory.getLogger(ApplicationControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object handleException(final Exception e, final HttpServletRequest request, final HttpServletResponse response) {
		logger.error("", e);
		final DataTransferObject<Void> dto = new DataTransferObject<Void>();
		if (e instanceof TypeMismatchException || e instanceof MissingServletRequestParameterException) {
			dto.setErrCode(CommonCode.PAR_EXCEPTION.getCode());
			dto.setMsg(CommonCode.PAR_EXCEPTION.getMsg());
		} else {
			dto.setErrCode(CommonCode.EXCEPTION.getCode());
			dto.setMsg(CommonCode.EXCEPTION.getMsg());
		}
		return dto;

	}

}
