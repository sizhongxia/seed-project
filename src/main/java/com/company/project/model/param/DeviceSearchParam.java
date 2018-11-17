package com.company.project.model.param;

public class DeviceSearchParam {
	private String name;
	private String equipmentNo;
	private String type;

	private String[] lastOnLineTime;

	private Integer page;
	private Integer size;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEquipmentNo() {
		return equipmentNo;
	}

	public void setEquipmentNo(String equipmentNo) {
		this.equipmentNo = equipmentNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getLastOnLineTime() {
		return lastOnLineTime;
	}

	public void setLastOnLineTime(String[] lastOnLineTime) {
		this.lastOnLineTime = lastOnLineTime;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
