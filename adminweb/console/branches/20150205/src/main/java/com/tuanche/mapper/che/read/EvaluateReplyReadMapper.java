package com.tuanche.mapper.che.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuanche.bean.che.EvaluateReplyBean;

public interface EvaluateReplyReadMapper {

	List<EvaluateReplyBean> getReply(@Param("eid")Integer eid);


}
