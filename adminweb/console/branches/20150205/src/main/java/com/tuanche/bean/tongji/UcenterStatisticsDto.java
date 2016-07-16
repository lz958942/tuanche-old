package com.tuanche.bean.tongji;

import com.tuanche.smc.common.page.impl.Pagination;


/** 
* @ClassName: UcenterStatisticsDto 
* @Description: 用户中心统计数据的页面实体
*  
*/
public class UcenterStatisticsDto extends UcenterStatistics {
    
    private String starttime ;
    private String endtime ;
    public String getStarttime() {
        return starttime;
    }
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
    public String getEndtime() {
        return endtime;
    }
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
    
    
    
    
}
