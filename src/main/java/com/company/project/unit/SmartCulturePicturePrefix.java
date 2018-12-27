package com.company.project.unit;

public enum SmartCulturePicturePrefix {
	EMPTY(""), FARM("smart/culture/farm/pics/");

	private String prefix;

	private SmartCulturePicturePrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getPrefix() {
		return prefix;
	}
}
