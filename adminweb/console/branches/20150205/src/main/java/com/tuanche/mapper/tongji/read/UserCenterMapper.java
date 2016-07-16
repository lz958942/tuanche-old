package com.tuanche.mapper.tongji.read;

import java.util.List;

import com.tuanche.bean.tongji.UcenterStatistics;
import com.tuanche.bean.tongji.UcenterStatisticsDto;

public interface UserCenterMapper {

    public List<UcenterStatistics> selectUcData(UcenterStatisticsDto ucDto);
}
