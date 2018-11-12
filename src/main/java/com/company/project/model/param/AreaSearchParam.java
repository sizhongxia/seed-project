package com.company.project.model.param;

public class AreaSearchParam {

	private String name;
	private String pinyin;
	private String jianpin;
	private String pcode;
	private String code;

	private Integer page;
	private Integer size;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getJianpin() {
		return jianpin;
	}

	public void setJianpin(String jianpin) {
		this.jianpin = jianpin;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
		return "AreaSearchParam [name=" + name + ", pinyin=" + pinyin + ", jianpin=" + jianpin + ", pcode=" + pcode
				+ ", code=" + code + ", page=" + page + ", size=" + size + "]";
	}

}
