package com.company.project.model.param;

public class ProjectDeviceParam {
	private String proUuid;
	private String deviceType;

	public String getProUuid() {
		return proUuid;
	}

	public void setProUuid(String proUuid) {
		this.proUuid = proUuid;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	@Override
	public String toString() {
		return "ProjectDeviceParam [proUuid=" + proUuid + ", deviceType=" + deviceType + "]";
	}

}
