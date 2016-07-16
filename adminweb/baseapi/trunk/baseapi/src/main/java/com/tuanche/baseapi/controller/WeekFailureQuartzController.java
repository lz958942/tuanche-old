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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuanche.baseapi.web.abs.AbstractController;
import com.tuanche.week.api.WeekFailureQuartz;
import com.tuanche.week.dto.Enroll;
import com.tuanche.week.dto.EnrollReplace;
import com.tuanche.week.dto.ParameterTransmitter;

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
@RequestMapping(value = "/weekFailure", method = { RequestMethod.POST, RequestMethod.GET })
public class WeekFailureQuartzController extends AbstractController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "week.WeekFailureQuartzProxy")
	private WeekFailureQuartz weekFailureQuartz;

	@RequestMapping(value = "/quartz")
	@ResponseBody
	public void execute(HttpServletRequest request, ParameterTransmitter parameters, Enroll enroll, EnrollReplace enrollReplace) {
		System.out.println("WeekFailureQuartzController.execute");
		weekFailureQuartz.execute(null);
	}
}
