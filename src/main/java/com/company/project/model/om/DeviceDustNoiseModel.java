package com.company.project.model.om;

import org.hibernate.validator.constraints.NotBlank;

public class DeviceDustNoiseModel {

	private String uuid;

	private String companyuuid;
	private String prouuid;
	@NotBlank(message = "请输入设备名称")
	private String name;
	@NotBlank(message = "请输入设备编码")
	private String equipmentno;
	private String positionx;
	private String positiony;
	private String suppliercompanyuuid;
	private String agentcompanyuuid;
	private String ipaddressport;
	private Integer ipportissuestatus;
	private String worktype;
	private String lng;
	private String lat;

	private String pm25Alarm;
	private String pm10Alarm;
	private String noiseAlarm;
	private String noisePattern;
	private String noiseCycle;
	private String noiseOc;

	private String personincharge;
	private String personinchargephone;
	private String installperson;
	private String installpersonphone;
	private String receipt;
	private String installtime;
	private String host;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCompanyuuid() {
		return companyuuid;
	}

	public void setCompanyuuid(String companyuuid) {
		this.companyuuid = companyuuid;
	}

	public String getProuuid() {
		return prouuid;
	}

	public void setProuuid(String prouuid) {
		this.prouuid = prouuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEquipmentno() {
		return equipmentno;
	}

	public void setEquipmentno(String equipmentno) {
		this.equipmentno = equipmentno;
	}

	public String getPositionx() {
		return positionx;
	}

	public void setPositionx(String positionx) {
		this.positionx = positionx;
	}

	public String getPositiony() {
		return positiony;
	}

	public void setPositiony(String positiony) {
		this.positiony = positiony;
	}

	public String getSuppliercompanyuuid() {
		return suppliercompanyuuid;
	}

	public void setSuppliercompanyuuid(String suppliercompanyuuid) {
		this.suppliercompanyuuid = suppliercompanyuuid;
	}

	public String getAgentcompanyuuid() {
		return agentcompanyuuid;
	}

	public void setAgentcompanyuuid(String agentcompanyuuid) {
		this.agentcompanyuuid = agentcompanyuuid;
	}

	public String getIpaddressport() {
		return ipaddressport;
	}

	public void setIpaddressport(String ipaddressport) {
		this.ipaddressport = ipaddressport;
	}

	public Integer getIpportissuestatus() {
		return ipportissuestatus;
	}

	public void setIpportissuestatus(Integer ipportissuestatus) {
		this.ipportissuestatus = ipportissuestatus;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
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

	public String getPm25Alarm() {
		return pm25Alarm;
	}

	public void setPm25Alarm(String pm25Alarm) {
		this.pm25Alarm = pm25Alarm;
	}

	public String getPm10Alarm() {
		return pm10Alarm;
	}

	public void setPm10Alarm(String pm10Alarm) {
		this.pm10Alarm = pm10Alarm;
	}

	public String getNoiseAlarm() {
		return noiseAlarm;
	}

	public void setNoiseAlarm(String noiseAlarm) {
		this.noiseAlarm = noiseAlarm;
	}

	public String getNoisePattern() {
		return noisePattern;
	}

	public void setNoisePattern(String noisePattern) {
		this.noisePattern = noisePattern;
	}

	public String getNoiseCycle() {
		return noiseCycle;
	}

	public void setNoiseCycle(String noiseCycle) {
		this.noiseCycle = noiseCycle;
	}

	public String getNoiseOc() {
		return noiseOc;
	}

	public void setNoiseOc(String noiseOc) {
		this.noiseOc = noiseOc;
	}

	public String getPersonincharge() {
		return personincharge;
	}

	public void setPersonincharge(String personincharge) {
		this.personincharge = personincharge;
	}

	public String getPersoninchargephone() {
		return personinchargephone;
	}

	public void setPersoninchargephone(String personinchargephone) {
		this.personinchargephone = personinchargephone;
	}

	public String getInstallperson() {
		return installperson;
	}

	public void setInstallperson(String installperson) {
		this.installperson = installperson;
	}

	public String getInstallpersonphone() {
		return installpersonphone;
	}

	public void setInstallpersonphone(String installpersonphone) {
		this.installpersonphone = installpersonphone;
	}

	public String getReceipt() {
		return receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getInstalltime() {
		return installtime;
	}

	public void setInstalltime(String installtime) {
		this.installtime = installtime;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return "DeviceDustNoiseModel [uuid=" + uuid + ", companyuuid=" + companyuuid + ", prouuid=" + prouuid
				+ ", name=" + name + ", equipmentno=" + equipmentno + ", positionx=" + positionx + ", positiony="
				+ positiony + ", suppliercompanyuuid=" + suppliercompanyuuid + ", agentcompanyuuid=" + agentcompanyuuid
				+ ", ipaddressport=" + ipaddressport + ", ipportissuestatus=" + ipportissuestatus + ", worktype="
				+ worktype + ", lng=" + lng + ", lat=" + lat + ", pm25Alarm=" + pm25Alarm + ", pm10Alarm=" + pm10Alarm
				+ ", noiseAlarm=" + noiseAlarm + ", noisePattern=" + noisePattern + ", noiseCycle=" + noiseCycle
				+ ", noiseOc=" + noiseOc + ", personincharge=" + personincharge + ", personinchargephone="
				+ personinchargephone + ", installperson=" + installperson + ", installpersonphone="
				+ installpersonphone + ", receipt=" + receipt + ", installtime=" + installtime + ", host=" + host + "]";
	}

}
