package com.tuanche.dao.che;

import java.util.List;

import javax.annotation.Resource;




import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.tuanche.bean.che.EvaluateReplyBean;
import com.tuanche.mapper.che.read.EvaluateReplyReadMapper;
import com.tuanche.mapper.che.write.EvaluateReplyWriteMapper;

@Repository
public class EvaluateReplyDao {
	@Resource
	private EvaluateReplyReadMapper evaluateReplyReadMapper;
	@Resource
	private EvaluateReplyWriteMapper evaluateReplyWriteMapper;
	public void saveComments(EvaluateReplyBean bean) {
		evaluateReplyWriteMapper.saveComments(bean);		
	}
	public List<EvaluateReplyBean> getReply(Integer id) {
		return evaluateReplyReadMapper.getReply(id);
	}
}
