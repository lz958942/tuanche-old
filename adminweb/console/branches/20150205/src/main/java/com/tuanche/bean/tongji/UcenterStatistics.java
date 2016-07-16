package com.tuanche.bean.tongji;


/** 
* @ClassName: UcenterStatistics 
* @Description: 用户中心统计数据的实体
*  
*/
public class UcenterStatistics {
    
    /**
     * 主键id
     */
    private Integer id; 
    /**
     * 日期
     */
    private String day ;
    /**
     * 用户中心登录总数
     */
    private Integer login_total_num ;
    /**
     * 用户中心PC端注册总数
     */
    private Integer pc_reg_num ;
    /**
     * 用户中心PC端主动注册总数
     */
    private Integer pc_actreg_num ;
    /**
     * 用户中心WAP端注册总数
     */
    private Integer wap_reg_num ;
    /**
     * 用户中心Android端注册总数
     */
    private Integer android_reg_num;
    /**
     * 用户中心IOS端注册总数
     */
    private Integer ios_reg_num;
    public UcenterStatistics(){
        login_total_num = 0;
        pc_reg_num = 0;
        pc_actreg_num = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getLogin_total_num() {
        return login_total_num;
    }
    public void setLogin_total_num(Integer login_total_num) {
        this.login_total_num = login_total_num;
    }
    public Integer getPc_reg_num() {
        return pc_reg_num;
    }
    public void setPc_reg_num(Integer pc_reg_num) {
        this.pc_reg_num = pc_reg_num;
    }
    public Integer getPc_actreg_num() {
        return pc_actreg_num;
    }
    public void setPc_actreg_num(Integer pc_actreg_num) {
        this.pc_actreg_num = pc_actreg_num;
    }

    public Integer getWap_reg_num() {
        return wap_reg_num;
    }

    public void setWap_reg_num(Integer wap_reg_num) {
        this.wap_reg_num = wap_reg_num;
    }

    public Integer getAndroid_reg_num() {
        return android_reg_num;
    }

    public void setAndroid_reg_num(Integer android_reg_num) {
        this.android_reg_num = android_reg_num;
    }

    public Integer getIos_reg_num() {
        return ios_reg_num;
    }

    public void setIos_reg_num(Integer ios_reg_num) {
        this.ios_reg_num = ios_reg_num;
    }
    
}
