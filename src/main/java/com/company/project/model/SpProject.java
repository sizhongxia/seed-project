package com.company.project.model;

import javax.persistence.*;

@Table(name = "sp_project")
public class SpProject {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目类别
     */
    private String type;

    /**
     * 省
     */
    private String provincial;

    /**
     * 市
     */
    private String city;

    /**
     * 区县
     */
    private String country;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取项目名称
     *
     * @return name - 项目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置项目名称
     *
     * @param name 项目名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取项目类别
     *
     * @return type - 项目类别
     */
    public String getType() {
        return type;
    }

    /**
     * 设置项目类别
     *
     * @param type 项目类别
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取省
     *
     * @return provincial - 省
     */
    public String getProvincial() {
        return provincial;
    }

    /**
     * 设置省
     *
     * @param provincial 省
     */
    public void setProvincial(String provincial) {
        this.provincial = provincial;
    }

    /**
     * 获取市
     *
     * @return city - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区县
     *
     * @return country - 区县
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置区县
     *
     * @param country 区县
     */
    public void setCountry(String country) {
        this.country = country;
    }
}