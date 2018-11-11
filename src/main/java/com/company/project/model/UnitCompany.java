package com.company.project.model;

import javax.persistence.*;

@Table(name = "unit_company")
public class UnitCompany {
	/**
	 * 自增id数据id
	 */
	@Id
	private Integer id;

	/**
	 * 企业名称
	 */
	@Column(name = "companyName")
	private String companyname;

	/**
	 * 企业简称
	 */
	@Column(name = "shortName")
	private String shortname;

	/**
	 * 数据唯一标识
	 */
	private String uuid;

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
	 * 0 正常 1删除
	 */
	private Integer state;

	/**
	 * 企业logo图片文件名
	 */
	private String logo;

	/**
	 * 上级单位UUID
	 */
	private String superior;

	/**
	 * 1 建设2施工3设计4监理5 勘察6劳务分包商7公司集团
	 */
	private Integer type;

	/**
	 * 和type对应的单位类型
	 */
	@Column(name = "typeName")
	private String typename;

	/**
	 * 企业描述
	 */
	private String description;

	/**
	 * 官网地址
	 */
	@Column(name = "officialWebsite")
	private String officialwebsite;

	/**
	 * 获取自增id数据id
	 *
	 * @return id - 自增id数据id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置自增id数据id
	 *
	 * @param id
	 *            自增id数据id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取企业名称
	 *
	 * @return companyName - 企业名称
	 */
	public String getCompanyname() {
		return companyname;
	}

	/**
	 * 设置企业名称
	 *
	 * @param companyname
	 *            企业名称
	 */
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	/**
	 * 获取数据唯一标识
	 *
	 * @return uuid - 数据唯一标识
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * 设置数据唯一标识
	 *
	 * @param uuid
	 *            数据唯一标识
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	 * 获取0 正常 1删除
	 *
	 * @return state - 0 正常 1删除
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 设置0 正常 1删除
	 *
	 * @param state
	 *            0 正常 1删除
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 获取企业logo图片文件名
	 *
	 * @return logo - 企业logo图片文件名
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * 设置企业logo图片文件名
	 *
	 * @param logo
	 *            企业logo图片文件名
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * 获取上级单位UUID
	 *
	 * @return superior - 上级单位UUID
	 */
	public String getSuperior() {
		return superior;
	}

	/**
	 * 设置上级单位UUID
	 *
	 * @param superior
	 *            上级单位UUID
	 */
	public void setSuperior(String superior) {
		this.superior = superior;
	}

	/**
	 * 获取1 建设2施工3设计4监理5 勘察6劳务分包商7公司集团
	 *
	 * @return type - 1 建设2施工3设计4监理5 勘察6劳务分包商7公司集团
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置1 建设2施工3设计4监理5 勘察6劳务分包商7公司集团
	 *
	 * @param type
	 *            1 建设2施工3设计4监理5 勘察6劳务分包商7公司集团
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取和type对应的单位类型
	 *
	 * @return typeName - 和type对应的单位类型
	 */
	public String getTypename() {
		return typename;
	}

	/**
	 * 设置和type对应的单位类型
	 *
	 * @param typename
	 *            和type对应的单位类型
	 */
	public void setTypename(String typename) {
		this.typename = typename;
	}

	/**
	 * 获取企业描述
	 *
	 * @return description - 企业描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置企业描述
	 *
	 * @param description
	 *            企业描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取官网地址
	 *
	 * @return officialWebsite - 官网地址
	 */
	public String getOfficialwebsite() {
		return officialwebsite;
	}

	/**
	 * 设置官网地址
	 *
	 * @param officialwebsite
	 *            官网地址
	 */
	public void setOfficialwebsite(String officialwebsite) {
		this.officialwebsite = officialwebsite;
	}
}