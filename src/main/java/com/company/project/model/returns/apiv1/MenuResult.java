package com.company.project.model.returns.apiv1;

import java.util.List;

public class MenuResult {
	private String name;
	private String icon;
	private String path;
	private String url;
	private List<MenuResult> subMenus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<MenuResult> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<MenuResult> subMenus) {
		this.subMenus = subMenus;
	}

}
