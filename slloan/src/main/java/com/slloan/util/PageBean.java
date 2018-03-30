package com.slloan.util;

import java.util.List;

/**
 * 分页
 * 
 * @author Administrator
 *
 */
public class PageBean<T> {
	private int currentPage;// 当前页
	private int pageSize = 3;// 每页记录数
	private int beginIndex;// 开始位置
	private int endIndex;// 结束位置
	private int pageCount;// 共多少页
	private int userCount;// 共多少条记录

	private List<T> userList;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public List<T> getUserList() {
		return userList;
	}

	public void setUserList(List<T> userList) {
		this.userList = userList;
	}
}
