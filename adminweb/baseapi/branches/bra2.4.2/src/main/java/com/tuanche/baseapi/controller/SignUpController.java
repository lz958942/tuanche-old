/**
 * Copyright (c) 2016, Stupid Bird and/or its affiliates. All rights reserved.
 * STUPID BIRD PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 * @Project : frontAPI Maven Webapp
 * @Title : SignUp.java
 * @Package : com.tuanche.frontapi.controller.order
 * @author <a href="http://www.lizhaoweb.net">李召(Jhon.Lee)</a>
 * @Date : 2016年1月14日
 * @Time : 上午11:44:12
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package com.tuanche.baseapi.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuanche.baseapi.web.abs.AbstractController;
import com.tuanche.commons.util.Resources;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.framework.base.entity.DataTransferObject;
import com.tuanche.framework.base.util.DateUtil;
import com.tuanche.week.api.SignUp;
import com.tuanche.week.constant.WeekCode;
import com.tuanche.week.dto.Enroll;
import com.tuanche.week.dto.EnrollReplace;
import com.tuanche.week.dto.ParameterTransmitter;
import com.tuanche.week.util.Constant.Charset;
import com.tuanche.week.util.EnrollLoggerUtil;
import com.tuanche.week.util.LoggerUtil;

/**
 * 
 * @author <a href="http://www.lizhaoweb.cn">李召(Jhon.Lee)</a>
 * @version
 * @notes Created on 2016年1月14日<br>
 *        Revision of last commit:$Revision$<br>
 *        Author of last commit:$Author$<br>
 *        Date of last commit:$Date$<br>
 *        <p />
 * 
 */
@Controller
@RequestMapping(value = "/signup")
public class SignUpController extends AbstractController {

	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.sss";

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "week.signUp")
	private SignUp signUp;

	@RequestMapping(value = "/apply", method = { RequestMethod.POST })
	@ResponseBody
	public Object apply(HttpServletRequest request, @ModelAttribute("parameters") ParameterTransmitter parameters, @ModelAttribute("enroll") Enroll enroll, @ModelAttribute("enrollReplace") EnrollReplace enrollReplace) throws Exception {
		System.out.println(enroll);
		LoggerUtil.fatal(logger, "/" + org.apache.commons.lang.StringUtils.center(" [BASE-API] 用户报名 %s ", 100, "=") + "\\", DateUtil.date2Str(new Date(), DATE_TIME_FORMAT));
		EnrollLoggerUtil.info(logger, enroll, parameters, this.getClass().getName() + ".apply(HttpServletRequest request, ParameterTransmitter parameters, Enroll enroll, EnrollReplace enrollReplace) throws Exception", "UMAPI入参--新车报名的参数对象");
		EnrollLoggerUtil.info(logger, enrollReplace, parameters, this.getClass().getName() + ".apply(HttpServletRequest request, ParameterTransmitter parameters, Enroll enroll, EnrollReplace enrollReplace) throws Exception", "UMAPI入参--二手车报名的参数对象");

		long startDateTime = System.currentTimeMillis();
		this.extraObjectParameterConvert(request, parameters, enroll, enrollReplace);

		DataTransferObject<Object> dataTransferObject = new DataTransferObject<Object>();
		try {
			String signTime = request.getHeader("Sign-Time");
			String accessSign = request.getHeader("Access-Sign");
			if (signTime == null || accessSign == null) {
				dataTransferObject = new DataTransferObject<Object>(WeekCode.SIGN_NULL.getCode(), WeekCode.SIGN_NULL.getMsg());
			} else {
				String accessKey = Resources.getString("com.tuanche.api.access.key");
				String validSecondString = Resources.getString("com.tuanche.api.access.valid.second");

				long signTimeLong = Long.valueOf(signTime);
				long validSecond = -1;
				if (validSecondString.matches("^\\d+$")) {
					validSecond = Long.valueOf(validSecondString) * 1000;
				} else {
					validSecond = 0;
				}

				String plaintext = String.format("%s%s%s", accessKey, "/apply", signTime);
				String sign = DigestUtils.md5Hex(plaintext);
				if (!accessSign.equals(sign)) {// 认证签名
					dataTransferObject = new DataTransferObject<Object>(WeekCode.UNAUTHORIZED_ACCESS.getCode(), WeekCode.UNAUTHORIZED_ACCESS.getMsg());
				} else if (System.currentTimeMillis() - validSecond > signTimeLong) {// 签名过期
					dataTransferObject = new DataTransferObject<Object>(WeekCode.EXPIRE_SIGN.getCode(), WeekCode.EXPIRE_SIGN.getMsg());
				} else if (enrollReplace != null && StringUtils.isNotEmpty(parameters.getToken())) {
					this.dataConvertEnrollReplace(parameters, enrollReplace);
					EnrollLoggerUtil.info(logger, enrollReplace, parameters, this.getClass().getName() + ".apply(HttpServletRequest request, ParameterTransmitter parameters, Enroll enroll, EnrollReplace enrollReplace) throws Exception", "二手车报名的参数对象");
					dataTransferObject = signUp.secondApply(enroll, enrollReplace, parameters);
				} else {
					this.dataConvertEnroll(parameters, enroll);
					EnrollLoggerUtil.info(logger, enrollReplace, parameters, this.getClass().getName() + ".apply(HttpServletRequest request, ParameterTransmitter parameters, Enroll enroll, EnrollReplace enrollReplace) throws Exception", "新车报名的参数对象");
					dataTransferObject = signUp.apply(enroll, parameters);
				}
			}
		} catch (Exception e) {
			EnrollLoggerUtil.error(logger, e, enroll, parameters, this.getClass().getName() + ".apply(Enroll enroll, ParameterTransmitter switchx)", null);
			dataTransferObject = new DataTransferObject<Object>(WeekCode.ERROR_SYS.getCode(), WeekCode.ERROR_SYS.getMsg());
		}
		long endDateTime = System.currentTimeMillis();
		LoggerUtil.fatal(logger, "\\" + org.apache.commons.lang.StringUtils.center(" [BASE-API] 报名耗时 %d 毫秒 %s ", 100, "=") + "/", endDateTime - startDateTime, DateUtil.date2Str(new Date(), DATE_TIME_FORMAT));
		return dataTransferObject;
	}

	// 额外的对象参数转换
	private void extraObjectParameterConvert(HttpServletRequest request, ParameterTransmitter parameters, Enroll enroll, EnrollReplace enrollReplace) {
		// // SESSION
		// HttpSession session = request.getSession();
		// parameters.setSessionId(session.getId());
		//
		// // 请求头
		// String referer = request.getHeader("referer");
		// if (!StringUtils.isEmpty(referer)) {
		// if (referer.length() > 200) {
		// referer = referer.substring(0, 200);
		// }
		// enroll.setUrl(referer);
		// }
		// String userAgent = request.getHeader("User-Agent");
		// parameters.setUserAgent(userAgent);
		//
		// // 请求体
		// String isblack = request.getParameter("isblack");
		// parameters.setBlack(StringUtils.isEmpty(isblack));
		//
		// String isJson = request.getParameter("isQd");// 微信签到报名，返回报名iD
		// parameters.setResponseDataType(StringUtils.isEmpty(isJson) ? 0 : 1);
		//
		// String serialNO = request.getParameter("SN");// 流水号用于日志查询
		// parameters.setSerialNO(serialNO);
		//
		// String sourceString = request.getParameter("source");
		// Integer source = StringUtils.strToInt(sourceString);
		// parameters.setSource(source);

		String requestURL = new StringBuffer(request.getScheme()).append("://").append(request.getServerName()).append(":").append(request.getServerPort()).append(request.getRequestURI()).append("?").append(request.getQueryString()).toString();
		parameters.setRequestURL(requestURL);

		parameters.setCityCode(null);
	}

	// 数据转换
	private void dataConvertEnroll(ParameterTransmitter parameters, Enroll enroll) {
		// 报名对象自身转换
		if (enroll.getState() == 0) {
			enroll.setState(10);
		}
		if (enroll.getSourceId() == 0) {
			enroll.setSourceId(1);
		}
		if (enroll.getKnowPrice() == null) {
			enroll.setKnowPrice("");
		}
		if (enroll.getUrl() == null) {
			enroll.setUrl("");
		}
		if (enroll.getBizCode() == null) {
			enroll.setBizCode("");
		}
		if (enroll.getAccountCode() == null) {
			enroll.setAccountCode("");
		}
		if (enroll.getUrl() == null) {
			enroll.setUrl("");
		} else {
			String url = enroll.getUrl();
			try {
				url = URLDecoder.decode(url, Charset.UTF8);
			} catch (UnsupportedEncodingException e) {
				url = "";
			}
			enroll.setUrl(url);
		}
		if (enroll.getSeoAccountCode() == null || enroll.getSeoAccountCode().length() > 25) {
			enroll.setSeoAccountCode("");
		}

		String sourceSiteurl = enroll.getSourceSiteurl();
		if (sourceSiteurl != null) {
			sourceSiteurl = StringUtils.decodeUTF8(sourceSiteurl);
			int indexNum = sourceSiteurl.indexOf("?");
			if (indexNum >= 0) {
				sourceSiteurl = sourceSiteurl.substring(0, indexNum);
			}
			if (sourceSiteurl.length() > 150) {
				sourceSiteurl = sourceSiteurl.substring(0, 150);
			}
			enroll.setSourceSiteurl(sourceSiteurl);
		}
		/**
		 * sourceType seo类型1-999 直接访问1-99 外部链接100-199 搜索引擎200-299 sem 1000-1999'
		 * 商务合作 2000-2999
		 */
		if (StringUtils.isNotEmpty(enroll.getUtmSource())) {
			enroll.setSourceType(2000);
		}
		if (StringUtils.isNotEmpty(enroll.getAccountCode())) {
			enroll.setSourceType(1000);
		} else {
			if (StringUtils.isNotEmpty(sourceSiteurl)) {
				if (sourceSiteurl.matches(".+(baidu.com|so.com|sogou.com|youdao.com|bing.com|yahoo.com|google.cn|google.com|soso.com).+")) {
					enroll.setSourceType(400);
				} else {
					enroll.setSourceType(100);
				}
			} else {
				if ("".equals(sourceSiteurl)) {
					enroll.setSourceType(1);
				}
			}
		}

		// 参数传递对象自身
		if (parameters.getUpkey() == null) {
			parameters.setUpkey("");
		}
		if (parameters.getReplaceId() == null) {
			parameters.setReplaceId(0);
		}

		// 混合处理
		if (parameters.getNoSure() == -1) {
			enroll.setKnowPrice("-1");
		}
	}

	// 数据转换
	private void dataConvertEnrollReplace(ParameterTransmitter parameters, EnrollReplace enrollReplace) {

		// 置换对象自身

		if (parameters.getToken() == null) {
			parameters.setToken("");
		}

		if (enrollReplace.getSpTime() == null) {
			enrollReplace.setSpTime("");
		}
		if (enrollReplace.getYyTime() == null) {
			enrollReplace.setYyTime("");
		}

		// 参数传递对象自身
		if (parameters.getSource() == 0) {
			parameters.setSource(2);
		}
	}
}
