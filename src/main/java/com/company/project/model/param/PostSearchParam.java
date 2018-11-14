package com.company.project.model.param;

public class PostSearchParam {
	private String keyword;
	private String deptUuid;

	private Integer page;
	private Integer size;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDeptUuid() {
		return deptUuid;
	}

	public void setDeptUuid(String deptUuid) {
		this.deptUuid = deptUuid;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "PostSearchParam [keyword=" + keyword + ", deptUuid=" + deptUuid + ", page=" + page + ", size=" + size
				+ "]";
	}

}
