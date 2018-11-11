package com.company.project.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user_token_flag")
public class UserTokenFlag {
	/**
	 * 用户ID
	 */
	@Id
	@Column(name = "userUuid")
	private String useruuid;

	/**
	 * token标识
	 */
	@Column(name = "flag_key")
	private String flagKey;
	/**
	 * token标识
	 */
	@Column(name = "token")
	private String token;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Long createTime;

	/**
	 * 到期时间
	 */
	@Column(name = "expire_time")
	private Long expireTime;

	/**
	 * 获取用户ID
	 *
	 * @return userUuid - 用户ID
	 */
	public String getUseruuid() {
		return useruuid;
	}

	/**
	 * 设置用户ID
	 *
	 * @param useruuid
	 *            用户ID
	 */
	public void setUseruuid(String useruuid) {
		this.useruuid = useruuid;
	}

	/**
	 * 获取token标识
	 *
	 * @return flag_key - token标识
	 */
	public String getFlagKey() {
		return flagKey;
	}

	/**
	 * 设置token标识
	 *
	 * @param flagKey
	 *            token标识
	 */
	public void setFlagKey(String flagKey) {
		this.flagKey = flagKey;
	}

	/**
	 * 获取创建时间
	 *
	 * @return create_time - 创建时间
	 */
	public Long getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取到期时间
	 *
	 * @return expire_time - 到期时间
	 */
	public Long getExpireTime() {
		return expireTime;
	}

	/**
	 * 设置到期时间
	 *
	 * @param expireTime
	 *            到期时间
	 */
	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}