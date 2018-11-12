package com.company.project.model.om;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class DictionaryModel {

	private String uuid;
	@NotBlank(message = "请输入字典值名称")
	private String name;
	@Pattern(regexp = "^[0-9]{1,5}$")
	@NotBlank(message = "请输入有效的字典值")
	private String value;
	@Pattern(regexp = "^[a-zA-Z]{2,60}$")
	@NotBlank(message = "请输入有效的类型代码")
	private String type;
	@NotBlank(message = "请输入字典类型描述")
	private String remarks;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
