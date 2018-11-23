package com.company.project.model.param.apiv1;

import org.hibernate.validator.constraints.NotBlank;

public class PersonnelParam {
	@NotBlank(message = "请传入UID")
	private String uid;
	@NotBlank(message = "请传入PID")
	private String pid;

	private String code;
	private String empNo;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

}
