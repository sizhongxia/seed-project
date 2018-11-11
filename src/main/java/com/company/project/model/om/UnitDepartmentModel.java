package com.company.project.model.om;

import org.hibernate.validator.constraints.NotBlank;

public class UnitDepartmentModel {
	private String uuid;

	@NotBlank(message = "请输入部门名称")
	private String deptname;
	@NotBlank(message = "请请选择一个公司/工地")
	private String deptuuid;
	private String parentuuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDeptuuid() {
		return deptuuid;
	}

	public void setDeptuuid(String deptuuid) {
		this.deptuuid = deptuuid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getParentuuid() {
		return parentuuid;
	}

	public void setParentuuid(String parentuuid) {
		this.parentuuid = parentuuid;
	}

}
