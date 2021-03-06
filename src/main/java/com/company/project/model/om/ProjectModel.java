package com.company.project.model.om;

import java.util.Arrays;

import org.hibernate.validator.constraints.NotBlank;

public class ProjectModel {

	private String uuid;

	@NotBlank(message = "请输入工地简称")
	private String name;
	@NotBlank(message = "请输入工地全称")
	private String proname;
	private String procode;
	private String width;
	private String length;
	private String locationmap;
	private String code;
	private String logo;
	private String province;
	private String city;
	private String county;

	private String personnelpositioning;

	private String[] areainfo;
	private String building;
	private String companyuuid;
	private String construction;
	private String constructionnature;
	private String design;
	private String[] function;
	private String investment;
	private String latitude;
	private String longitude;
	private String[] mainstructuretype;
	private String measure;
	private String numberoflayers;
	private String personname;
	private String phone;
	private String planendtime;
	private String planstarttime;
	private String salesname;
	private String salesphone;
	private String[] subcontractorcompany;
	private String supervision;
	private String survey;
	private String type;

	// 超管账号
	@NotBlank(message = "请输入超管账号")
	private String username;
	// 超管密码
	@NotBlank(message = "请输入超管密码")
	private String password;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getProcode() {
		return procode;
	}

	public void setProcode(String procode) {
		this.procode = procode;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getCompanyuuid() {
		return companyuuid;
	}

	public void setCompanyuuid(String companyuuid) {
		this.companyuuid = companyuuid;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getSupervision() {
		return supervision;
	}

	public void setSupervision(String supervision) {
		this.supervision = supervision;
	}

	public String getSurvey() {
		return survey;
	}

	public void setSurvey(String survey) {
		this.survey = survey;
	}

	public String getConstruction() {
		return construction;
	}

	public void setConstruction(String construction) {
		this.construction = construction;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getLocationmap() {
		return locationmap;
	}

	public void setLocationmap(String locationmap) {
		this.locationmap = locationmap;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSalesname() {
		return salesname;
	}

	public void setSalesname(String salesname) {
		this.salesname = salesname;
	}

	public String getSalesphone() {
		return salesphone;
	}

	public void setSalesphone(String salesphone) {
		this.salesphone = salesphone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getInvestment() {
		return investment;
	}

	public void setInvestment(String investment) {
		this.investment = investment;
	}

	public String getNumberoflayers() {
		return numberoflayers;
	}

	public void setNumberoflayers(String numberoflayers) {
		this.numberoflayers = numberoflayers;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getConstructionnature() {
		return constructionnature;
	}

	public void setConstructionnature(String constructionnature) {
		this.constructionnature = constructionnature;
	}

	public String getPlanstarttime() {
		return planstarttime;
	}

	public void setPlanstarttime(String planstarttime) {
		this.planstarttime = planstarttime;
	}

	public String getPlanendtime() {
		return planendtime;
	}

	public void setPlanendtime(String planendtime) {
		this.planendtime = planendtime;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String[] getAreainfo() {
		return areainfo;
	}

	public void setAreainfo(String[] areainfo) {
		this.areainfo = areainfo;
	}

	public String[] getFunction() {
		return function;
	}

	public void setFunction(String[] function) {
		this.function = function;
	}

	public String[] getMainstructuretype() {
		return mainstructuretype;
	}

	public void setMainstructuretype(String[] mainstructuretype) {
		this.mainstructuretype = mainstructuretype;
	}

	public String[] getSubcontractorcompany() {
		return subcontractorcompany;
	}

	public void setSubcontractorcompany(String[] subcontractorcompany) {
		this.subcontractorcompany = subcontractorcompany;
	}

	public String getPersonnelpositioning() {
		return personnelpositioning;
	}

	public void setPersonnelpositioning(String personnelpositioning) {
		this.personnelpositioning = personnelpositioning;
	}

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

	@Override
	public String toString() {
		return "ProjectModel [uuid=" + uuid + ", name=" + name + ", proname=" + proname + ", procode=" + procode
				+ ", width=" + width + ", length=" + length + ", locationmap=" + locationmap + ", code=" + code
				+ ", logo=" + logo + ", province=" + province + ", city=" + city + ", county=" + county
				+ ", personnelpositioning=" + personnelpositioning + ", areainfo=" + Arrays.toString(areainfo)
				+ ", building=" + building + ", companyuuid=" + companyuuid + ", construction=" + construction
				+ ", constructionnature=" + constructionnature + ", design=" + design + ", function="
				+ Arrays.toString(function) + ", investment=" + investment + ", latitude=" + latitude + ", longitude="
				+ longitude + ", mainstructuretype=" + Arrays.toString(mainstructuretype) + ", measure=" + measure
				+ ", numberoflayers=" + numberoflayers + ", personname=" + personname + ", phone=" + phone
				+ ", planendtime=" + planendtime + ", planstarttime=" + planstarttime + ", salesname=" + salesname
				+ ", salesphone=" + salesphone + ", subcontractorcompany=" + Arrays.toString(subcontractorcompany)
				+ ", supervision=" + supervision + ", survey=" + survey + ", type=" + type + ", username=" + username
				+ ", password=" + password + "]";
	}

}
