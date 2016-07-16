package com.tuanche.service.tongji;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuanche.bean.tongji.UcenterStatistics;
import com.tuanche.bean.tongji.UcenterStatisticsDto;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.dao.tongji.UserCenterDao;

@Service
public class UserCenterService {
    @Autowired
    private UserCenterDao ucDao;
    
    public List<UcenterStatistics> queryUcData(UcenterStatisticsDto ucDto){
        if("".equals(ucDto.getStarttime())){
            ucDto.setStarttime(null);
        }
        if("".equals(ucDto.getEndtime())){
            ucDto.setEndtime(null);
        }
        return ucDao.selectUcData(ucDto);
    }
}
