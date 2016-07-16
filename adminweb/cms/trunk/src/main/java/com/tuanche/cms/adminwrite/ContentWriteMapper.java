package com.tuanche.cms.adminwrite;

import com.tuanche.cms.bean.Content;


public interface ContentWriteMapper {

    int insert(Content content);

    int updateByPrimaryKeySelective(Content content);

}