package com.tuanche.baseapi.constant;

/**
 * <p>报名接口的异常code定义</p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi  on n 2016/1/23.13:37
 * @version 1.0
 * @since 1.0
 */
public enum WeekApiConstant {


    PAR_EXCEPTION(7000000,"参数异常"),
    WEEK_ERROR(7000001,"报名异常")
            ;


    private int code;

    private String msg;

    private WeekApiConstant(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
