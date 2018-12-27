package com.company.project.model.param.basic;

public class BasicFarmPictureParam {
	private String farmId;
	private String[] fileList;
	private String farmAreaId;
	private String title;
	private String sortNum;

	public String getFarmId() {
		return farmId;
	}

	public void setFarmId(String farmId) {
		this.farmId = farmId;
	}

	public String[] getFileList() {
		return fileList;
	}

	public void setFileList(String[] fileList) {
		this.fileList = fileList;
	}

	public String getFarmAreaId() {
		return farmAreaId;
	}

	public void setFarmAreaId(String farmAreaId) {
		this.farmAreaId = farmAreaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSortNum() {
		return sortNum;
	}

	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}

}
