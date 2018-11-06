package com.company.project.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Table(name = "sys_user")
public class User {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 登陆名、账号
	 */
	@NotBlank(message = "{user.loginName.notBlank}")
	@Pattern(regexp = "[a-zA-Z0-9_]{3,20}", message = "{user.loginName.formatError}")
	@Column(name = "login_name")
	private String loginName;

	/**
	 * 登陆密码
	 */
	@NotBlank(message = "{user.loginPwd.notBlank}")
	@Pattern(regexp = "[a-zA-Z0-9_]{6,20}", message = "{user.loginPwd.formatError}")
	@Column(name = "login_pwd")
	private String loginPwd;

	/**
	 * 备注
	 */
	private String remarks;

	/**
	 * 创建时间
	 */
	@Column(name = "create_at")
	private Date createAt;

	/**
	 * 修改时间
	 */
	@Column(name = "update_at")
	private Date updateAt;

	/**
	 * 获取主键
	 *
	 * @return id - 主键
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置主键
	 *
	 * @param id
	 *            主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取登陆名、账号
	 *
	 * @return login_name - 登陆名、账号
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 设置登陆名、账号
	 *
	 * @param loginName
	 *            登陆名、账号
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * 获取登陆密码
	 *
	 * @return login_pwd - 登陆密码
	 */
	public String getLoginPwd() {
		return loginPwd;
	}

	/**
	 * 设置登陆密码
	 *
	 * @param loginPwd
	 *            登陆密码
	 */
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	/**
	 * 获取备注
	 *
	 * @return remarks - 备注
	 */
	public String getRemarks() {
		return remarks == null ? "" : remarks.trim();
	}

	/**
	 * 设置备注
	 *
	 * @param remarks
	 *            备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 获取创建时间
	 *
	 * @return create_at - 创建时间
	 */
	public Date getCreateAt() {
		return createAt;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createAt
	 *            创建时间
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	/**
	 * 获取修改时间
	 *
	 * @return update_at - 修改时间
	 */
	public Date getUpdateAt() {
		return updateAt;
	}

	/**
	 * 设置修改时间
	 *
	 * @param updateAt
	 *            修改时间
	 */
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
}