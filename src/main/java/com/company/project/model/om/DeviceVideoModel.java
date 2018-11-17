package com.company.project.model.om;

import org.hibernate.validator.constraints.NotBlank;

public class DeviceVideoModel {
	private String uuid;

	private String companyuuid;
	private String prouuid;
	@NotBlank(message = "请输入设备名称")
	private String name;
	private String equipmentno;
	private String positionx;
	private String positiony;
	private String suppliercompanyuuid;
	private String agentcompanyuuid;
	private String ipaddressport;
	private String ipportissuestatus;
	private String worktype;
	private String lng;
	private String lat;

	private String flowaddress;
	private String streamurl;
	private String playurl;
	private String deploytime;
	private String brandname;
	private String type;
	private String voperation;
	private String[] voperations;
	private String istowereye;
	private String pushflowmode;
	private String ip;
	private String port;
	private String username;
	private String password;
	private String state;
	private String lasttime;
	private String onvifstatus;
	private String iseyeclient;
	private String equipmentuuid;
	private String remotetime;
	private String remoteconnectionstring;

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

	public String getIpportissuestatus() {
		return ipportissuestatus;
	}

	public void setIpportissuestatus(String ipportissuestatus) {
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

	public String getFlowaddress() {
		return flowaddress;
	}

	public void setFlowaddress(String flowaddress) {
		this.flowaddress = flowaddress;
	}

	public String getStreamurl() {
		return streamurl;
	}

	public void setStreamurl(String streamurl) {
		this.streamurl = streamurl;
	}

	public String getPlayurl() {
		return playurl;
	}

	public void setPlayurl(String playurl) {
		this.playurl = playurl;
	}

	public String getDeploytime() {
		return deploytime;
	}

	public void setDeploytime(String deploytime) {
		this.deploytime = deploytime;
	}

	public String getBrandname() {
		return brandname;
	}

	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVoperation() {
		return voperation;
	}

	public void setVoperation(String voperation) {
		this.voperation = voperation;
	}

	public String getIstowereye() {
		return istowereye;
	}

	public void setIstowereye(String istowereye) {
		this.istowereye = istowereye;
	}

	public String getPushflowmode() {
		return pushflowmode;
	}

	public void setPushflowmode(String pushflowmode) {
		this.pushflowmode = pushflowmode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

	public String getOnvifstatus() {
		return onvifstatus;
	}

	public void setOnvifstatus(String onvifstatus) {
		this.onvifstatus = onvifstatus;
	}

	public String[] getVoperations() {
		return voperations;
	}

	public void setVoperations(String[] voperations) {
		this.voperations = voperations;
	}

	public String getIseyeclient() {
		return iseyeclient;
	}

	public void setIseyeclient(String iseyeclient) {
		this.iseyeclient = iseyeclient;
	}

	public String getEquipmentuuid() {
		return equipmentuuid;
	}

	public void setEquipmentuuid(String equipmentuuid) {
		this.equipmentuuid = equipmentuuid;
	}

	public String getRemotetime() {
		return remotetime;
	}

	public void setRemotetime(String remotetime) {
		this.remotetime = remotetime;
	}

	public String getRemoteconnectionstring() {
		return remoteconnectionstring;
	}

	public void setRemoteconnectionstring(String remoteconnectionstring) {
		this.remoteconnectionstring = remoteconnectionstring;
	}

	@Override
	public String toString() {
		return "DeviceVideoModel [uuid=" + uuid + ", companyuuid=" + companyuuid + ", prouuid=" + prouuid + ", name="
				+ name + ", equipmentno=" + equipmentno + ", positionx=" + positionx + ", positiony=" + positiony
				+ ", suppliercompanyuuid=" + suppliercompanyuuid + ", agentcompanyuuid=" + agentcompanyuuid
				+ ", ipaddressport=" + ipaddressport + ", ipportissuestatus=" + ipportissuestatus + ", worktype="
				+ worktype + ", lng=" + lng + ", lat=" + lat + ", flowaddress=" + flowaddress + ", streamurl="
				+ streamurl + ", playurl=" + playurl + ", deploytime=" + deploytime + ", brandname=" + brandname
				+ ", type=" + type + ", voperation=" + voperation + ", istowereye=" + istowereye + ", pushflowmode="
				+ pushflowmode + ", ip=" + ip + ", port=" + port + ", username=" + username + ", password=" + password
				+ ", state=" + state + ", lasttime=" + lasttime + ", onvifstatus=" + onvifstatus + ", iseyeclient="
				+ iseyeclient + ", equipmentuuid=" + equipmentuuid + ", remotetime=" + remotetime
				+ ", remoteconnectionstring=" + remoteconnectionstring + "]";
	}

}
