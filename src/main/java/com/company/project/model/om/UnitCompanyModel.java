package com.company.project.model.om;

import org.hibernate.validator.constraints.NotBlank;

public class UnitCompanyModel {

	private String uuid;

	@NotBlank(message = "请输入企业全称")
	private String companyname;
	@NotBlank(message = "请输入企业简称")
	private String shortname;
	@NotBlank(message = "请选择一个企业类别")
	private String type;
	private String description;
	private String officialwebsite;
	private String superior;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOfficialwebsite() {
		return officialwebsite;
	}

	public void setOfficialwebsite(String officialwebsite) {
		this.officialwebsite = officialwebsite;
	}

	public String getSuperior() {
		return superior;
	}

	public void setSuperior(String superior) {
		this.superior = superior;
	}

}
