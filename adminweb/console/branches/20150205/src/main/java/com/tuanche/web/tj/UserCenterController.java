package com.tuanche.web.tj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tuanche.bean.tongji.UcenterStatistics;
import com.tuanche.bean.tongji.UcenterStatisticsDto;
import com.tuanche.commons.util.StringUtils;
import com.tuanche.console.util.ExportExcel;
import com.tuanche.service.tongji.UserCenterService;

@Controller
public class UserCenterController {
    @Autowired
    private UserCenterService ucService;
    /**
     * 查询用户中心统计数据
     * 
     */
    @RequestMapping(value="/tongji/uc")
    public ModelAndView toList(Model model,HttpServletRequest request ,@Param("ucDto")UcenterStatisticsDto ucDto){
        List<UcenterStatistics>  ucenterStatistics = ucService.queryUcData(ucDto);
        model.addAttribute("list", ucenterStatistics);
        SimpleDateFormat sc = new SimpleDateFormat("yyyy-MM-dd");
        if(StringUtils.isNotEmpty(ucDto.getStarttime())){
        try {
            model.addAttribute("starttime", sc.parse(ucDto.getStarttime()));
            } catch (ParseException e) {
            e.printStackTrace();
           }
      } 
        if(StringUtils.isNotEmpty(ucDto.getEndtime())){
          try {
              model.addAttribute("endtime", sc.parse(ucDto.getEndtime()));
          } catch (ParseException e) {
              e.printStackTrace();
          }
        }
        return new ModelAndView("/tongji/ucsearch");
    }
    /**
     * 导出excel
     * 
     * 
     */
    @RequestMapping(value="/uc/download")
    public void ucdownload(HttpServletRequest request,HttpServletResponse response,ModelMap model,@Param("ucDto")UcenterStatisticsDto ucDto){
        List<UcenterStatistics>  ucenterStatistics = ucService.queryUcData(ucDto);
        Map<String,String> titleValueMap=new LinkedHashMap<String,String>();
        titleValueMap.put("Day","日期");
        titleValueMap.put("Login_total_num","登录总数");
        titleValueMap.put("Pc_reg_num","PC端注册总数");
        titleValueMap.put("Pc_actreg_num","PC端主动注册数");
        titleValueMap.put("Android_reg_num","Android端注册数");
        titleValueMap.put("Ios_reg_num","IOS端注册数");
        titleValueMap.put("Wap_reg_num","WAP端注册数");
        ExportExcel.exportExcel(new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".xls",titleValueMap,ucenterStatistics,request,response);
    }
}
