package com.tuanche.cms.common.page;   
  
/**   
 * 
 * @version 1.0 
 * @author andyhome 李振民  
 * @Email: andyhome108@126.com
 * @comment 为了梦想而奋斗，坚持到底
 * @create time 创建时间: Mar 28, 2011 11:08:24 PM
 */
/**
 * 分布接口
 */
public interface IPage {
	/**
	 * 总记录数
	 * 
	 * @return
	 */
	public int getTotalCount();

	/**
	 * 总页数
	 * 
	 * @return
	 */
	public int getTotalPage();

	/**
	 * 每页记录数
	 * 
	 * @return
	 */
	public int getPageSize();

	/**
	 * 当前页号
	 * 
	 * @return
	 */
	public int getPageNo();

	/**
	 * 是否第一页
	 * 
	 * @return
	 */
	public boolean isFirstPage();

	/**
	 * 是否最后一页
	 * 
	 * @return
	 */
	public boolean isLastPage();

	/**
	 * 返回下页的页号
	 */
	public int getNextPage();

	/**
	 * 返回上页的页号
	 */
	public int getPrePage();
}
