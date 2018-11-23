package com.company.project.model.param.apiv1;

import org.hibernate.validator.constraints.NotBlank;

public class CommonParam {

	@NotBlank(message = "请传入UID")
	private String uid;
	@NotBlank(message = "请传入PID")
	private String pid;

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

	@Override
	public String toString() {
		return "CommonParam [uid=" + uid + ", pid=" + pid + "]";
	}

}
