package com.tuanche.baseapi.service;

/**
 * <p></p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi  on n 2016/1/23.13:30
 * @version 1.0
 * @since 1.0
 */
public interface HttpsService {

    /**
     *
     * 调用远程地址.
     *
     * @param api_url
     * @param object
     * @return
     */
    String sendPost(String api_url, Object object);


    /**
     *
     * 调用远程地址.
     *
     * @param api_url
     * @param postArgs
     * @return
     */
    String sendPost(String api_url, String postArgs);

    /**
     *
     * 调用远程地址.
     *
     * @param api_url
     * @param postArgs
     * @return
     */
    String sendPostDes(String api_url, String postArgs);

    /**
     *
     * 调用远程地址.
     *
     * @param api_url
     * @return
     */
    String sendPost(String api_url);

}

