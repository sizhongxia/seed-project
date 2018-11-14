package com.company.project.model.om;

import org.hibernate.validator.constraints.NotBlank;

public class UnitPostModel {
	private String uuid;

	@NotBlank(message = "请输入岗位名称")
	private String name;
	@NotBlank(message = "请选择一个部门")
	private String deptuuid;
	private String prouuid;

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

	public String getDeptuuid() {
		return deptuuid;
	}

	public void setDeptuuid(String deptuuid) {
		this.deptuuid = deptuuid;
	}

	public String getProuuid() {
		return prouuid;
	}

	public void setProuuid(String prouuid) {
		this.prouuid = prouuid;
	}

	@Override
	public String toString() {
		return "UnitPostModel [uuid=" + uuid + ", name=" + name + ", deptuuid=" + deptuuid + ", prouuid=" + prouuid
				+ "]";
	}

}
