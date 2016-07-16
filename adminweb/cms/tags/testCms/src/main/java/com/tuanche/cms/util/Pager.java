package com.tuanche.cms.util;

import java.util.HashMap;
import java.util.Map;

public class Pager {
	private int totalRows=0; // 总行数

	private int pageSize= 10; // 每页显示的行数

	private int currentPage=1; // 当前页号

	private int totalPages=1; // 总页数

	private int startRow=0; // 当前页在数据库中的起始行

	private String linkUrl; // 要跳转的URL
	
	private Map<String,String>  searchCondtions;

	public Pager() {
	}
 
	public Pager(int _currentPage,int _totalRows, int _pageSize)
	{
		
		totalRows = _totalRows;
		pageSize = _pageSize;
		totalPages = totalRows / pageSize;
		
		int mod = totalRows % pageSize;
		if (mod > 0) {
			totalPages++;
		}
		this.currentPage=_currentPage;
		if(this.currentPage==0){
			this.currentPage=1;
		}
			this.setStart(_currentPage);
	}
	public void setStart(int currentPage) {
		if(currentPage==0||currentPage==1){
			this.startRow=0;
		}else{
			this.startRow = (currentPage - 1) * pageSize;
		}
}
	public  int getStartRow(int currentPage){
		if(currentPage==0||currentPage==1){
			return 0;
		}else{
			return (currentPage - 1) * this.getPageSize();
		}
	}
	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public void addCondition(String name,String value) {
		if(this.searchCondtions==null){
			this.searchCondtions=new HashMap<String,String>();
		}
		this.searchCondtions.put(name, value);
	}

	public Map<String, String> getSearchCondtions() {
		return searchCondtions;
	}

	public void setSearchCondtions(Map<String, String> searchCondtions) {
		this.searchCondtions = searchCondtions;
	}
}