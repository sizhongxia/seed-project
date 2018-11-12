package com.company.project.model;

import javax.persistence.*;

@Table(name = "sys_dictionary")
public class SysDictionary {
	/**
	 * 字典排序值 用来排序
	 */
	@Id
	private Integer id;

	/**
	 * 字典名
	 */
	private String name;

	/**
	 * 字典值
	 */
	private Integer value;

	/**
	 * 字典分类，同一个分类的type一样
	 */
	private String type;

	/**
	 * 字典描述
	 */
	private String remarks;

	/**
	 * 获取字典排序值 用来排序
	 *
	 * @return id - 字典排序值 用来排序
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置字典排序值 用来排序
	 *
	 * @param id
	 *            字典排序值 用来排序
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取字典名
	 *
	 * @return name - 字典名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置字典名
	 *
	 * @param name
	 *            字典名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取字典值
	 *
	 * @return value - 字典值
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * 设置字典值
	 *
	 * @param value
	 *            字典值
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * 获取字典分类，同一个分类的type一样
	 *
	 * @return type - 字典分类，同一个分类的type一样
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置字典分类，同一个分类的type一样
	 *
	 * @param type
	 *            字典分类，同一个分类的type一样
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取字典描述
	 *
	 * @return remarks - 字典描述
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 设置字典描述
	 *
	 * @param remarks
	 *            字典描述
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}