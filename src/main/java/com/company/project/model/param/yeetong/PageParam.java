package com.company.project.model.param.yeetong;

import org.apache.commons.lang3.math.NumberUtils;

import cn.hutool.core.util.NumberUtil;

public class PageParam {
	private String page;
	private String size;

	private String type;

	public String getPage() {
		if (NumberUtils.isParsable(page)) {
			if (page.length() > 8) {
				page = "1";
			} else {
				int npage = NumberUtil.parseInt(page);
				if (npage < 1) {
					page = "1";
				}
			}
		} else {
			page = "1";
		}
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getSize() {
		if (NumberUtils.isParsable(size)) {
			if (size.length() > 8) {
				size = "10";
			} else {
				int nsize = NumberUtil.parseInt(size);
				if (nsize < 1) {
					size = "10";
				}
			}
		} else {
			size = "10";
		}
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
