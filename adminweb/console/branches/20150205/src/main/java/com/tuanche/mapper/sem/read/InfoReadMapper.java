package com.tuanche.mapper.sem.read;

import java.util.List;
import java.util.Map;
import com.tuanche.bean.sem.ResultBean;

public interface InfoReadMapper {
	List<ResultBean> getInfoList(Map<String,String> map);
	int count(Map<String,String> map);
}
