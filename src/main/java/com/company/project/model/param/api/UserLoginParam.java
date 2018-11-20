package com.company.project.model.param.api;

import org.hibernate.validator.constraints.NotBlank;

public class UserLoginParam {

	@NotBlank(message = "请输入登录账号")
	private String username;
	@NotBlank(message = "请输入登录密码")
	private String password;
	private String vcode;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

}
