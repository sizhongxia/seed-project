package com.company.project.model.returns.basic;

import java.util.List;

public class AreaResult {
	private String label;
	private String value;
	private boolean isLeaf;
	private List<AreaResult> children;

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

	public List<AreaResult> getChildren() {
		return children;
	}

	public void setChildren(List<AreaResult> children) {
		this.children = children;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

}