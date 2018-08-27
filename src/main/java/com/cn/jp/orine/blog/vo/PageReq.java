package com.cn.jp.orine.blog.vo;

/**
 * 请求分页
 * @author RDuser
 */
public class PageReq {
	
	/**
	 * 每页条数，默认每页10条
	 */
	private int pageSize = 10;

	/**
	 * 当前页，默认第一页
	 */
    private int currentPage = 0;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize >= 1 ? pageSize : 10;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		currentPage --;
		this.currentPage = currentPage > -1 ? currentPage : 0;
	}
}