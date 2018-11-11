package com.company.project.model;

import javax.persistence.*;

@Table(name = "user_identity")
public class UserIdentity {
	/**
	 * 自增列，不作为唯一主键
	 */
	@Id
	private Integer id;

	/**
	 * 数据id 唯一表示
	 */
	private String uuid;

	/**
	 * 用户UUID
	 */
	@Column(name = "userUuid")
	private String useruuid;

	/**
	 * 身份类型 1工地 2 企业 3政务4劳务人员
	 */
	private Integer type;

	/**
	 * 是否默认身份 默认身份每个类型只能有一个 1 默认 其他不是
	 */
	@Column(name = "isDefault")
	private Integer isdefault;

	/**
	 * 所属 工地
	 */
	@Column(name = "deptUuid")
	private String deptuuid;

	/**
	 * 用户角色id
	 */
	@Column(name = "roleUuid")
	private String roleuuid;

	/**
	 * 当前帐号是否可以登陆工地版0可以1不可以 默认超管 不是系统管理员
	 */
	@Column(name = "isLogIng")
	private Integer isloging;

	/**
	 * 是否单点登陆 0 否 1 是
	 */
	@Column(name = "singleSignon")
	private Integer singlesignon;

	/**
	 * 添加时间
	 */
	@Column(name = "addTime")
	private Long addtime;

	/**
	 * 更新时间
	 */
	@Column(name = "updateTime")
	private Long updatetime;

	/**
	 * 审核通过时间
	 */
	@Column(name = "passTime")
	private Long passtime;

	/**
	 * 用户身份状态 0正常 1删除 2待审核 3审核失败 4停用、5解除绑定
	 */
	private Integer state;

	/**
	 * 所属部门
	 */
	private String department;

	/**
	 * 所属岗位
	 */
	private String post;

	/**
	 * 所属班组
	 */
	@Column(name = "groupUuid")
	private String groupuuid;

	/**
	 * 当前工种
	 */
	private String worktype;

	/**
	 * 是否是系统创建时候的默认系统超管 1是 0不是
	 */
	@Column(name = "isSuper")
	private Integer issuper;

	/**
	 * 用户所属企业
	 */
	@Column(name = "companyUuid")
	private String companyuuid;

	/**
	 * 获取自增列，不作为唯一主键
	 *
	 * @return id - 自增列，不作为唯一主键
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置自增列，不作为唯一主键
	 *
	 * @param order
	 *            自增列，不作为唯一主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取数据id 唯一表示
	 *
	 * @return uuid - 数据id 唯一表示
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * 设置数据id 唯一表示
	 *
	 * @param uuid
	 *            数据id 唯一表示
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * 获取用户UUID
	 *
	 * @return userUuid - 用户UUID
	 */
	public String getUseruuid() {
		return useruuid;
	}

	/**
	 * 设置用户UUID
	 *
	 * @param useruuid
	 *            用户UUID
	 */
	public void setUseruuid(String useruuid) {
		this.useruuid = useruuid;
	}

	/**
	 * 获取身份类型 1工地 2 企业 3政务4劳务人员
	 *
	 * @return type - 身份类型 1工地 2 企业 3政务4劳务人员
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置身份类型 1工地 2 企业 3政务4劳务人员
	 *
	 * @param type
	 *            身份类型 1工地 2 企业 3政务4劳务人员
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取是否默认身份 默认身份每个类型只能有一个 1 默认 其他不是
	 *
	 * @return isDefault - 是否默认身份 默认身份每个类型只能有一个 1 默认 其他不是
	 */
	public Integer getIsdefault() {
		return isdefault;
	}

	/**
	 * 设置是否默认身份 默认身份每个类型只能有一个 1 默认 其他不是
	 *
	 * @param isdefault
	 *            是否默认身份 默认身份每个类型只能有一个 1 默认 其他不是
	 */
	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}

	/**
	 * 获取所属 工地
	 *
	 * @return deptUuid - 所属 工地
	 */
	public String getDeptuuid() {
		return deptuuid;
	}

	/**
	 * 设置所属 工地
	 *
	 * @param deptuuid
	 *            所属 工地
	 */
	public void setDeptuuid(String deptuuid) {
		this.deptuuid = deptuuid;
	}

	/**
	 * 获取用户角色id
	 *
	 * @return roleUuid - 用户角色id
	 */
	public String getRoleuuid() {
		return roleuuid;
	}

	/**
	 * 设置用户角色id
	 *
	 * @param roleuuid
	 *            用户角色id
	 */
	public void setRoleuuid(String roleuuid) {
		this.roleuuid = roleuuid;
	}

	/**
	 * 获取当前帐号是否可以登陆工地版0可以1不可以 默认超管 不是系统管理员
	 *
	 * @return isLogIng - 当前帐号是否可以登陆工地版0可以1不可以 默认超管 不是系统管理员
	 */
	public Integer getIsloging() {
		return isloging;
	}

	/**
	 * 设置当前帐号是否可以登陆工地版0可以1不可以 默认超管 不是系统管理员
	 *
	 * @param isloging
	 *            当前帐号是否可以登陆工地版0可以1不可以 默认超管 不是系统管理员
	 */
	public void setIsloging(Integer isloging) {
		this.isloging = isloging;
	}

	/**
	 * 获取是否单点登陆 0 否 1 是
	 *
	 * @return singleSignon - 是否单点登陆 0 否 1 是
	 */
	public Integer getSinglesignon() {
		return singlesignon;
	}

	/**
	 * 设置是否单点登陆 0 否 1 是
	 *
	 * @param singlesignon
	 *            是否单点登陆 0 否 1 是
	 */
	public void setSinglesignon(Integer singlesignon) {
		this.singlesignon = singlesignon;
	}

	/**
	 * 获取添加时间
	 *
	 * @return addTime - 添加时间
	 */
	public Long getAddtime() {
		return addtime;
	}

	/**
	 * 设置添加时间
	 *
	 * @param addtime
	 *            添加时间
	 */
	public void setAddtime(Long addtime) {
		this.addtime = addtime;
	}

	/**
	 * 获取更新时间
	 *
	 * @return updateTime - 更新时间
	 */
	public Long getUpdatetime() {
		return updatetime;
	}

	/**
	 * 设置更新时间
	 *
	 * @param updatetime
	 *            更新时间
	 */
	public void setUpdatetime(Long updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * 获取审核通过时间
	 *
	 * @return passTime - 审核通过时间
	 */
	public Long getPasstime() {
		return passtime;
	}

	/**
	 * 设置审核通过时间
	 *
	 * @param passtime
	 *            审核通过时间
	 */
	public void setPasstime(Long passtime) {
		this.passtime = passtime;
	}

	/**
	 * 获取用户身份状态 0正常 1删除 2待审核 3审核失败 4停用、5解除绑定
	 *
	 * @return state - 用户身份状态 0正常 1删除 2待审核 3审核失败 4停用、5解除绑定
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 设置用户身份状态 0正常 1删除 2待审核 3审核失败 4停用、5解除绑定
	 *
	 * @param state
	 *            用户身份状态 0正常 1删除 2待审核 3审核失败 4停用、5解除绑定
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 获取所属部门
	 *
	 * @return department - 所属部门
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * 设置所属部门
	 *
	 * @param department
	 *            所属部门
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * 获取所属岗位
	 *
	 * @return post - 所属岗位
	 */
	public String getPost() {
		return post;
	}

	/**
	 * 设置所属岗位
	 *
	 * @param post
	 *            所属岗位
	 */
	public void setPost(String post) {
		this.post = post;
	}

	/**
	 * 获取所属班组
	 *
	 * @return groupUuid - 所属班组
	 */
	public String getGroupuuid() {
		return groupuuid;
	}

	/**
	 * 设置所属班组
	 *
	 * @param groupuuid
	 *            所属班组
	 */
	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
	}

	/**
	 * 获取当前工种
	 *
	 * @return worktype - 当前工种
	 */
	public String getWorktype() {
		return worktype;
	}

	/**
	 * 设置当前工种
	 *
	 * @param worktype
	 *            当前工种
	 */
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

	/**
	 * 获取是否是系统创建时候的默认系统超管 1是 0不是
	 *
	 * @return isSuper - 是否是系统创建时候的默认系统超管 1是 0不是
	 */
	public Integer getIssuper() {
		return issuper;
	}

	/**
	 * 设置是否是系统创建时候的默认系统超管 1是 0不是
	 *
	 * @param issuper
	 *            是否是系统创建时候的默认系统超管 1是 0不是
	 */
	public void setIssuper(Integer issuper) {
		this.issuper = issuper;
	}

	/**
	 * 获取用户所属企业
	 *
	 * @return companyUuid - 用户所属企业
	 */
	public String getCompanyuuid() {
		return companyuuid;
	}

	/**
	 * 设置用户所属企业
	 *
	 * @param companyuuid
	 *            用户所属企业
	 */
	public void setCompanyuuid(String companyuuid) {
		this.companyuuid = companyuuid;
	}
}