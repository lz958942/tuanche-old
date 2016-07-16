package com.tuanche.dao.tongji;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.tuanche.bean.tongji.UcenterStatistics;
import com.tuanche.bean.tongji.UcenterStatisticsDto;
import com.tuanche.mapper.tongji.read.UserCenterMapper;

@Repository
public class UserCenterDao {
    @Resource
    private UserCenterMapper ucMapper;
    
    public List<UcenterStatistics> selectUcData(UcenterStatisticsDto ucDto){
        return ucMapper.selectUcData(ucDto);
    }
}
