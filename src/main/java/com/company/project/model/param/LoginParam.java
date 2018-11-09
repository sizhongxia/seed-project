package com.company.project.model.param;

public class LoginParam {
	private String loginName;
	private String loginPwd;
	private String type;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "LoginParam [loginName=" + loginName + ", loginPwd=" + loginPwd + ", type=" + type + "]";
	}

}
