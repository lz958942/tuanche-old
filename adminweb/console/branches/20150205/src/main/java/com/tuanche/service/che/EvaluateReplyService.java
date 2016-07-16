package com.tuanche.service.che;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.che.EvaluateReplyBean;
import com.tuanche.dao.che.EvaluateReplyDao;
import com.tuanche.smc.util.ZiXunDateUtil;

@Service
public class EvaluateReplyService {
	@Autowired
private EvaluateReplyDao dao;
	public void saveComments(String comments, Integer evaluateId,Integer eid) {
		EvaluateReplyBean bean=new EvaluateReplyBean();
		if(comments!=null && comments.length()>0){
			bean.setContent(comments);
		}else{
			
			bean.setContent("");
		}
		if(evaluateId!=null && evaluateId>0){
			bean.setEvaluateId(evaluateId);
		}else{
			bean.setEvaluateId(0);
		}
		if(eid!=null && eid>0){
			bean.setReplyUid(eid);
		}else{
			bean.setReplyUid(0);
		}
		bean.setReplyTime(ZiXunDateUtil.getDateToStringMM());
		dao.saveComments(bean);
	}

	public List<EvaluateReplyBean> getReply(Integer id) {
		return dao.getReply(id);
	}

	public List<EvaluateReplyBean> home(EvaluateReplyBean bean) {
		List<String> search=new ArrayList<String>();
		if(bean!=null){
			
		}
		return null;
	}

	
}
