package com.company.project.model.om;

import java.util.List;

public class AreaLevelModel {
	private String label;
	private String value;
	private boolean isLeaf;
	private List<AreaLevelModel> children;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public List<AreaLevelModel> getChildren() {
		return children;
	}

	public void setChildren(List<AreaLevelModel> children) {
		this.children = children;
	}

}