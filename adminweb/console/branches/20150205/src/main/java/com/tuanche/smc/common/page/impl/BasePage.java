package com.tuanche.smc.common.page.impl;

import com.tuanche.smc.common.page.IPage;

/**
 *<b>www.taofang.com</b>
 * <b>function:</b>基本分页类（抽象）
 * @author lizhenmin
 * @createDate 2011 Jul 21, 2011 10:44:48 AM
 * @email lizm@taofang.com
 * @version 1.0
 */
public abstract class BasePage implements java.io.Serializable, IPage {
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * @author andyhome
	 * @Create time Mar 29, 201112:19:21 AM
	 */
	private static final long serialVersionUID = 2286258500479017476L;

	/** 每页数量的默认值 **/
	public static final int DEF_COUNT = 20;

	/** 每页的数量 */
	private int pageSize = 20;
	/** 当前页第一条位置*/
	private int currentResult;


	public BasePage(int pageNo, int pageSize, int totalCountt) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCountt;
	}

	/** 总页数 */
	private int totalPage;

	/** 当前页 */
	private int pageNo = 1;;

	/** 总记录数 */
	private int totalCount;

	/**
	 * @return totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            要设置的 totalCount
	 */
	public void setTotalCount(int totalCount) {
		if (totalCount < 0) {
			this.totalCount = 0;
		} else {
			this.totalCount = totalCount;
		}
	}

	/** The default constructor */
	public BasePage() {

	}

	/**
	 * 第一条数据位置
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return (pageNo - 1) * pageSize;
	}

	/**
	 * @param pageSize
	 *            The pageSize to set.
	 */
	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			this.pageSize = DEF_COUNT;
		} else {
			this.pageSize = pageSize;
		}
	}

	/**
	 * @return Returns the totalPage.
	 * 
	 */
	public int getTotalPage() {
		if (this.totalPage <= 0) {
			this.totalPage = totalCount / pageSize;
			if (totalPage == 0 || totalCount % pageSize != 0) {
				this.totalPage++;
			}
		}
		return this.totalPage;
	}

	@Override
	public int getPageSize() {
		// TODO Auto-generated method stub
		return this.pageSize;
	}

	@Override
	public int getPageNo() {
		// TODO Auto-generated method stub
		return this.pageNo;
	}

	/**
	 * 是否有上一页
	 */
	@Override
	public boolean isFirstPage() {
		return pageNo <= 1;
	}

	/**
	 * 是否有下一页
	 */
	@Override
	public boolean isLastPage() {
		// TODO Auto-generated method stub
		return pageNo >= getTotalPage();
	}

	@Override
	public int getNextPage() {
		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}
	/**
	 * 当前页是第一条记录的位置
	 */
	public int getCurrentResult(){
		currentResult = (getPageNo() - 1) * getPageSize();
		if (currentResult < 0)
			currentResult = 0;
		return currentResult;
	}
	@Override
	public int getPrePage() {
		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}
}
