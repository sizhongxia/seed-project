package com.company.project.model;

import javax.persistence.*;

@Table(name = "sys_area")
public class SysArea {
    /**
     * 自增id
     */
    @Id
    private Integer id;

    private String name;

    private String pinyin;

    private String jianpin;

    private Integer pcode;

    private Integer code;

    /**
     * 维度
     */
    private String lng;

    /**
     * 经度
     */
    private String lat;

    /**
     * 获取自增id
     *
     * @return id - 自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增id
     *
     * @param id 自增id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return pinyin
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * @param pinyin
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    /**
     * @return jianpin
     */
    public String getJianpin() {
        return jianpin;
    }

    /**
     * @param jianpin
     */
    public void setJianpin(String jianpin) {
        this.jianpin = jianpin;
    }

    /**
     * @return pcode
     */
    public Integer getPcode() {
        return pcode;
    }

    /**
     * @param pcode
     */
    public void setPcode(Integer pcode) {
        this.pcode = pcode;
    }

    /**
     * @return code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 获取维度
     *
     * @return lng - 维度
     */
    public String getLng() {
        return lng;
    }

    /**
     * 设置维度
     *
     * @param lng 维度
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

    /**
     * 获取经度
     *
     * @return lat - 经度
     */
    public String getLat() {
        return lat;
    }

    /**
     * 设置经度
     *
     * @param lat 经度
     */
    public void setLat(String lat) {
        this.lat = lat;
    }
}