package com.tuanche.mapper.che.write;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.EvaluateReplyBean;

public interface EvaluateReplyWriteMapper {

	void saveComments(EvaluateReplyBean bean);

}
