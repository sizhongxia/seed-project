package com.company.project.model.om;

import java.util.Arrays;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class LoginAccountModel {
	private String uuid;

	private String[] ids;

	private String idnumber;
	@NotBlank(message = "请输入用户登陆名")
	private String username;
	@NotBlank(message = "请输入用户手机号")
	private String phone;
	private String name;
	private String password;
	private String photo;
	@Pattern(regexp = "[01]", message = "请选择有效的性别")
	private String sex;
	@Pattern(regexp = "[012]", message = "请选择有效的状态")
	private String state;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	@Override
	public String toString() {
		return "LoginAccountModel [uuid=" + uuid + ", ids=" + Arrays.toString(ids) + ", idnumber=" + idnumber
				+ ", username=" + username + ", phone=" + phone + ", name=" + name + ", password=" + password
				+ ", photo=" + photo + ", sex=" + sex + ", state=" + state + "]";
	}

}
