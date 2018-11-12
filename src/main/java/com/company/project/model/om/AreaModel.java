package com.company.project.model.om;

import org.hibernate.validator.constraints.NotBlank;

public class AreaModel {

	private String uuid;

	private String[] ids;

	@NotBlank(message = "请输入区域名称")
	private String name;
	@NotBlank(message = "请输入区域名称拼音")
	private String pinyin;
	@NotBlank(message = "请输入区域名称简拼")
	private String jianpin;
	private String pcode;
	@NotBlank(message = "请输入区域编码")
	private String code;
	private String lng;
	private String lat;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getJianpin() {
		return jianpin;
	}

	public void setJianpin(String jianpin) {
		this.jianpin = jianpin;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

}
