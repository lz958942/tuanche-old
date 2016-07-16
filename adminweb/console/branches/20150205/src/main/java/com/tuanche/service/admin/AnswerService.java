package com.tuanche.service.admin;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.admin.Answer;
import com.tuanche.console.bean.Employee;
import com.tuanche.console.util.GlobalConstants;
import com.tuanche.console.util.StringUtils;
import com.tuanche.mapper.admin.read.AnswerReadMapper;
import com.tuanche.mapper.admin.read.QuestionReadMapper;
import com.tuanche.mapper.admin.write.AnswerWriteMapper;
import com.tuanche.mapper.admin.write.QuestionWriteMapper;



@Service
public class AnswerService {
	@Autowired
	AnswerReadMapper readMapper;
	@Autowired
	AnswerWriteMapper writeMapper;
	@Autowired
	QuestionWriteMapper qWriteMapper;
	@Autowired
	QuestionReadMapper questionReadMapper;
	@Autowired
	QuestionWriteMapper questionWriteMapper;
    //推荐/已解决/采纳
    private static final Byte RECOM = 1;
    //推荐/已解决/采纳
    private static final Byte NORECOM = 0;
	/**
	 * 查询问题的回复数
	 * @param qid
	 * @return
	 */
	public Integer selectCount(Integer qid){
		return readMapper.selectCount(qid);
	}
	/**
	 * 删除问题下的答案
	 * @param id
	 */
	public void deleteAnswersWithQ(Byte answerStatus,Integer buildEmp,Integer id){
		writeMapper.deleteAnswersWithQ(answerStatus, buildEmp, id);
	}
	/**
	 * 批量添加答案
	 * @param list
	 */
	public void addAnswers(List<Answer> list){
		writeMapper.addAnswers(list);
	}
	/**
	 * 根据问题找回答
	 * 
	 */
	public List<Answer> getAnswersByQuestion(Integer id){
		return readMapper.getAnswersByQuestionByPage(id);
	}
	public void updateState(Integer id, Integer type,HttpSession session,Integer pid) {
		if(pid==null && id!=null){
			pid=readMapper.getPidByid(id);
		}
		Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
		if(sessionUser!=null &&sessionUser.getId()!=null ){
			if(type==1 && pid!=null){
				List<Answer> alist=readMapper.getAnswersByQuestionByPage(pid);
				if(alist!=null && alist.size()>=1){
					for (Answer answer : alist) {
						if(answer!=null && answer.getAnswerAdopt()!=null&& answer.getAnswerAdopt()==1 && alist.size()>=1 ){
							writeMapper.updateState(answer.getId(),2,sessionUser.getId());
						}
					}
				}
				//问题改为已解决
				
			}
			writeMapper.updateState(id,type,sessionUser.getId());
			if(type==1){
				qWriteMapper.resolve(RECOM,sessionUser.getId(),pid);
			}else if(type==2){
				qWriteMapper.resolve(NORECOM,sessionUser.getId(),pid);
			}
		}questionWriteMapper.updateReply(readMapper.selectCount(pid),pid);
	}
	/***保存答案 kevin**********/
	public void saveAnswer(Answer answer,HttpSession session) {
		if(answer!=null){
			initAnswer(answer,session);
			writeMapper.saveAnswer(answer);
			questionWriteMapper.updateReply(readMapper.selectCount(answer.getQuestionId()), answer.getQuestionId());
		}
	}
	private void initAnswer(Answer answer,HttpSession session) {
		if(answer!=null){
			Employee sessionUser = (Employee) session.getAttribute(GlobalConstants.SESSION_EMP);
			if(sessionUser!=null &&sessionUser.getId()!=null ){
				answer.setAnswerStatus((byte) 4);
				answer.setBuildEmp(sessionUser.getId());
				answer.setAnswerAdopt((byte) 0);
			}
			
		}
	}
	
	/*****
	 * 搜索与首页一体
	 * **********/
	public List<Answer> answerList(Answer answer) {
		List<String> search=new ArrayList<String>();
		if(answer!=null){
			 initAnswer(answer,search);
			 System.out.println();
		}
		search.add("a.answer_status =2");
		List<Answer> answerList=readMapper.answerListByPage(search);
		if(answerList!=null && answerList.size()>0){
			return answerList;
		}
		return null;
	}
	private void initAnswer(Answer answer,List<String> search) {
		if(answer!=null){
			if(answer.getAnswerAdopt()!=null){
				search.add("a.answer_adopt ="+answer.getAnswerAdopt());
			}
			if(answer.getQuestionAnswer()!=null && answer.getQuestionAnswer().length()>0){
				search.add("q.title LIKE '%"+answer.getQuestionAnswer()+"%'");
			}
			/*if(answer.getAnswerStatus()!=null){
				search.add("a.answer_status ="+answer.getAnswerStatus());
			}*/
		}
	}
	/**
	 * 批量修改答案状态（审核通过或删除）
	 * @param answerStatus
	 * @param buildEmp
	 * @param list
	 */
	public void updateAnswers(Byte answerStatus,Integer buildEmp,List<String> list) {
		writeMapper.updateAnswers(answerStatus, buildEmp, list);
		
	}
}
