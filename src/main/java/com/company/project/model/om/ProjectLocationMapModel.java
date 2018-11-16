package com.company.project.model.om;

import org.hibernate.validator.constraints.NotBlank;

public class ProjectLocationMapModel {
	@NotBlank(message = "请传入工地标识")
	private String uuid;
	private String width;
	private String length;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

}
