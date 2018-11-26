package com.company.project.model.param.apiv1;

import org.hibernate.validator.constraints.NotBlank;

public class GroupParam {
	@NotBlank(message = "请传入UID")
	private String uid;
	@NotBlank(message = "请传入PID")
	private String pid;

	private String id;
	private String name;
	private String companyId;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

}
