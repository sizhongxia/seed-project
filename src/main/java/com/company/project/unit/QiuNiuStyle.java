package com.company.project.unit;

public enum QiuNiuStyle {
	_140x200("-140x200"), _200x200("-200x200"), _290x180("-290x180"), _360x220("-360x220"), _745x520("-745x520");

	public String code;

	private QiuNiuStyle(String name) {
		this.code = name;
	}

}
