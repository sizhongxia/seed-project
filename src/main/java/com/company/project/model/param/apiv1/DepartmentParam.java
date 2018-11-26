package com.company.project.model.param.apiv1;

import org.hibernate.validator.constraints.NotBlank;

public class DepartmentParam {
	@NotBlank(message = "请传入UID")
	private String uid;
	@NotBlank(message = "请传入PID")
	private String pid;

	private String id;
	private String name;
	private String parentUuid;

	private String[] posts;

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

	public String getParentUuid() {
		return parentUuid;
	}

	public void setParentUuid(String parentUuid) {
		this.parentUuid = parentUuid;
	}

	public String[] getPosts() {
		return posts;
	}

	public void setPosts(String[] posts) {
		this.posts = posts;
	}

}
