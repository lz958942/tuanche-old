package com.tuanche.cms.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.cms.bean.Employee;
import com.tuanche.cms.bean.SysAuthEmp;
import com.tuanche.cms.service.EmployeeService;
import com.tuanche.cms.service.SysAuthEmpService;
import com.tuanche.cms.util.Encriptor;
import com.tuanche.cms.util.GlobalConstants;

@Controller
public class LoginController {
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private SysAuthEmpService sysAuthEmpService;
	
	@RequestMapping(value="/main",method=RequestMethod.GET)
	public String main(){
		return "/sys/main";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("empLogin") String empLogin, @RequestParam("empPwd") String empPwd, HttpServletRequest request) {
		Employee employee = employeeService.selectEmployeeByEmpLogin(empLogin.trim());
		Map returnMap = new HashMap();
		boolean check = false;
		SysAuthEmp sysAuthEmp = null;
		String checkStr = "error.login";
		if (employee != null && Encriptor.getMD5(empPwd).equalsIgnoreCase(employee.getEmpPwd().trim())) {
			//sysAuthEmp = sysAuthEmpService.selectSysAuthEmpByEmpId(employee.getId());
			request.getSession().setAttribute("employeeId", employee.getId());
			//if (sysAuthEmp == null) {
				//checkStr = "error.nopower.data";
			//} else {
			//sysAuthEmp = sysAuthEmpService.selectSysAuthEmpByEmpId(employee.getId());
			//if (sysAuthEmp == null) {
				//checkStr = "error.nopower.data";
			//} else {
				check = Boolean.TRUE;
			//}
		}
		if (check) {
			request.getSession(true).setAttribute(GlobalConstants.SESSION_EMP, employee);
			request.getSession(true).setAttribute(GlobalConstants.SESSION_AUTH_DATA, sysAuthEmp);
			returnMap.put(GlobalConstants.SESSION_EMP, employee);
			returnMap.put(GlobalConstants.SESSION_AUTH_DATA, sysAuthEmp);
			return new ModelAndView("redirect:" + GlobalConstants.MAIN_PAGE, returnMap);
		} else {
			returnMap.put(GlobalConstants.ERROR_KEY, checkStr);
			return new ModelAndView("redirect:"+GlobalConstants.USER_HOME, returnMap);
		}

	}
}
