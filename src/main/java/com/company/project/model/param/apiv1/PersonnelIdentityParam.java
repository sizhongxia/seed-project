package com.company.project.model.param.apiv1;

import org.hibernate.validator.constraints.NotBlank;

public class PersonnelIdentityParam {
	@NotBlank(message = "请传入UID")
	private String uid;
	@NotBlank(message = "请传入PID")
	private String pid;

	private String id;

	public String getUid() {
		return uid == null ? null : uid.trim();
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPid() {
		return pid == null ? null : pid.trim();
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


}
