package com.company.project.model.om;

import org.hibernate.validator.constraints.NotBlank;

public class MemorabiliaModel {

	private String uuid;

	@NotBlank(message = "为传入项目工地ID")
	private String prouuid;
	@NotBlank(message = "请输入大事件标题")
	private String title;
	@NotBlank(message = "请输入大事件内容")
	private String content;
	@NotBlank(message = "请输入大事件发生时间")
	private String happentime;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getProuuid() {
		return prouuid;
	}

	public void setProuuid(String prouuid) {
		this.prouuid = prouuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getHappentime() {
		return happentime;
	}

	public void setHappentime(String happentime) {
		this.happentime = happentime;
	}

}
