package com.tuanche.web.evaluateReply;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tuanche.bean.che.EvaluateReplyBean;
import com.tuanche.bean.che.SysConfig;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.mapper.che.read.SysConfigReadMapper;
import com.tuanche.mapper.che.write.ReviewWriteMapper;
import com.tuanche.service.che.EvaluateReplyService;

@Controller
public class EvaluateReplyController {
	@Autowired
private EvaluateReplyService evaluateReplyService;
	@Autowired
private SysConfigReadMapper configReadMapper;
	@Autowired
private ReviewWriteMapper reviewWriteMapper;
	
	/*
	 * 后期在做
	@RequestMapping("/reply/shortcutHome")
	public void home(EvaluateReplyBean  bean) {
		if(bean!=null){
		List<EvaluateReplyBean>stlist=evaluateReplyService.home(bean);
		}
	}*/
	
	
	/********与评论模块处理****************/
	@RequestMapping("/json/ajaxGetShortcut")
	public  @ResponseBody List<SysConfig> ajaxGetShortcut() {
			List<SysConfig> list=configReadMapper.getCodeByKeyFromString("evaluate_reply_shortcut");
			System.out.println(list);
			return list;
	}
	@RequestMapping(value="/reply/comments",method=RequestMethod.POST)
	public  @ResponseBody String comments(HttpSession session,@RequestParam("idd") Integer idd,@RequestParam("content")String content ) {
		 Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
			if(sessionUser!=null&&sessionUser.getId()!=null){
				reviewWriteMapper.reply(idd, sessionUser.getId());
				//保存评论内容
				evaluateReplyService.saveComments(content,idd,sessionUser.getId());
			}
		return "1";
	}
}
