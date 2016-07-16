package com.tuanche.smc.common;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 *Title:SimpleRequestListener
 *Description:监听HttpServletRequest的创建及销毁
 *@Create_by:
 *@Create_date:
 *@Last_Edit_By:
 *@Edit_Description
 *@version:
 */
public class SimpleRequestListener implements ServletRequestListener {
    
	/* 
	 * 监听request被销毁的事件
	 */
	public void requestDestroyed(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		ServletRequestHolder.reset(request);
	}

	/* 
	 * 监听request线程被初始化的事件
	 */
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		ServletRequestHolder.setRequest(request);
	}

}
