package com.company.project.model.returns;

public class GroupStatisticsResult {
	private String item;
	private Integer num;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "GroupStatisticsResult [item=" + item + ", num=" + num + "]";
	}

}
