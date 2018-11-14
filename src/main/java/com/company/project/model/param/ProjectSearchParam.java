package com.company.project.model.param;

public class ProjectSearchParam {
	private String name;
	private String proName;
	private String proCode;
	private String[] addTime;

	private String province;
	private String city;
	private String county;

	private Integer page;
	private Integer size;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String[] getAddTime() {
		return addTime;
	}

	public void setAddTime(String[] addTime) {
		this.addTime = addTime;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
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
		return "ProjectSearchParam [name=" + name + ", proName=" + proName + ", proCode=" + proCode + ", addTime="
				+ addTime + ", province=" + province + ", city=" + city + ", county=" + county + ", page=" + page
				+ ", size=" + size + "]";
	}

}
