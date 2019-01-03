package com.company.project.unit;

public enum SmartCulturePicturePrefix {
	EMPTY(""), FARM("smart/culture/farm/pics/"), FARM_QR("smart/culture/farm/qr/"), FARM_LOGO("smart/culture/farm/logo/");

	private String prefix;

	private SmartCulturePicturePrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getPrefix() {
		return prefix;
	}
}
