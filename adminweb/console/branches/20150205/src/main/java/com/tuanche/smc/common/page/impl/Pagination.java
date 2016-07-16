package com.tuanche.smc.common.page.impl;

import java.util.List;

/**
 *<b>www.taofang.com</b>
 * <b>function:</b>分页封装类
 * @author lizhenmin
 * @createDate 2011 Jul 21, 2011 10:44:48 AM
 * @email lizm@taofang.com
 * @version 1.0
 */
public class Pagination extends BasePage {

	private static final long serialVersionUID = 5729482538591310351L;
	public static ThreadLocal<Pagination> threadLocal = new ThreadLocal<Pagination>();

	public Pagination() {
	}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 */
	public Pagination(int pageNo, int pageSize, int totalCount) {
		super(pageNo, pageSize, totalCount);
	}

	/**
	 * 构造器
	 * 
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            每页几条数据
	 * @param totalCount
	 *            总共几条数据
	 * @param list
	 *            分页内容
	 */
	public Pagination(int pageNo, int pageSize, int totalCount, List<?> list) {
		super(pageNo, pageSize, totalCount);
		this.list = list;
	}

	/**
	 * 当前页的数据
	 */
	private List<?> list;

	/**
	 * 获得分页内容
	 * 
	 * @return
	 */
	public List<?> getList() {
		return list;
	}

	/**
	 * 设置分页内容
	 * 
	 * @param list
	 */
	public void setList(List<?> list) {
		this.list = list;
	}

}
