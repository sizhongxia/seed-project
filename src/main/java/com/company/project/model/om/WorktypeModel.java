package com.company.project.model.om;

import org.hibernate.validator.constraints.NotBlank;

public class WorktypeModel {
	private String id;
	@NotBlank(message = "请传入工种名称")
	private String name;
	@NotBlank(message = "请传入PID")
	private String pid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
