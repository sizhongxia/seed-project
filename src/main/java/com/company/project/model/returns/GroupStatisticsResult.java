package com.company.project.model.returns;

public class GroupStatisticsResult {
	private String name;
	private Integer value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public GroupStatisticsResult() {
		super();
	}
	public GroupStatisticsResult(String name, Integer value) {
		super();
		this.name = name;
		this.value = value;
	}
	@Override
	public String toString() {
		return "GroupStatisticsResult [name=" + name + ", value=" + value + "]";
	}


}
