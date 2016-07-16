package com.tuanche.web.wenda;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.admin.Answer;
import com.tuanche.bean.admin.Question;
import com.tuanche.bean.admin.QuestionKind;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.service.admin.AnswerService;
import com.tuanche.service.admin.QuestionKindService;
import com.tuanche.service.admin.QuestionService;
import com.tuanche.smc.common.page.impl.Pagination;

@Controller
public class TkQuesttionController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private QuestionKindService kindService;
	@Autowired
	private AnswerService answerService;

	/**
	 * 问题查询答案
	 * 
	 * **/
	@RequestMapping(value = "/wd/replys")
	public ModelAndView replys(@Param("id") Integer id, Model model,
			Answer value) {
		Pagination pagination = value.getPage();
		if (pagination == null) {
			pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		model.addAttribute("page", Pagination.threadLocal.get());
		if (id != null && id > 0) {
			Question question = questionService.getQuestionByid(id);
			List<Answer> answers = answerService.getAnswersByQuestion(id);
			if (question != null) {
				model.addAttribute("question", question);
			}
			return new ModelAndView("/question/tkAnswer", "answers", answers);
		}
		return new ModelAndView("/question/tkAnswer");
	}

	@RequestMapping(value = "/wd/best")
	public String best(@Param("id") Integer id, @Param("pid") Integer pid,
			@Param("type") Integer type, HttpSession session) {
		if (type != null && id != null) {
			if (pid != null) {
				answerService.updateState(id, type, session, pid);
				return "redirect:/wd/replys?id=" + pid;
			} else {
				answerService.updateState(id, type, session, null);
				return "redirect:/wd/answerList";
			}
		}
		return "/question/tkAnswer";
	}

	@RequestMapping(value = "/wd/saveWd")
	public String saveAnswer(Answer answer, HttpSession session)
			throws Exception {

		String contentString = answer.getContent();

		answerService.saveAnswer(answer, session);

		if (answer != null && answer.getQuestionId() != null) {
			return "redirect:/wd/replys?id=" + answer.getQuestionId();
		}
		return "redirect:/questionAnswer/toQuestionList";
	}

	@RequestMapping("/json/getTwoByOne")
	@ResponseBody
	public List<QuestionKind> getTwoByOne(@Param("parentId") Integer parentId) {
		List<QuestionKind> questionKind = null;
		if (parentId != null) {
			questionKind = kindService.selectTwoKind(parentId);
		}
		if (questionKind != null && questionKind.size() > 0) {
			return questionKind;
		}
		return null;
	}

	@RequestMapping(value = "/wd/answerList")
	public ModelAndView answerList(Model model, Answer answer) {
		Pagination pagination = answer.getPage();
		if (pagination == null) {
			pagination = new Pagination();
		}
		Pagination.threadLocal.set(pagination);
		model.addAttribute("page", Pagination.threadLocal.get());
		// model.addAttribute("question", questionService.selectAlls(new
		// Question()));
		List<Answer> answerlist = answerService.answerList(answer);
		// model.addAttribute("answer",answer );
		// model.addAttribute("oneKinds",kindService.selectOneKind() );
		return new ModelAndView("/question/answerList", "answers", answerlist);
	}

	@RequestMapping(value = "/wd/updatestatus")
	@ResponseBody
	public String updatestatus(Model model, HttpServletRequest request,
			@Param("checkeds") String[] ids, HttpSession session,
			@Param("type") Byte type) {
		Employee employee = (Employee) session
				.getAttribute(GlobalConstants.SESSION_EMP);
		// questionService.updateQuestions(type,employee.getId(),Arrays.asList(ids));//换
		answerService.updateAnswers(type, employee.getId(), Arrays.asList(ids));
		// 修改问题下的回复数

		// update
		return "";
	}
}
