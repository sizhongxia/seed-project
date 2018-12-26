package com.company.project.model;

import javax.persistence.*;

@Table(name = "smart_culture_basic_city")
public class SmartCultureBasicCity {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 城市编码
     */
    private String code;

    /**
     * 父级编码
     */
    private String pcode;

    /**
     * 城市名称
     */
    private String name;

    /**
     * 级别
     */
    @Column(name = "level_no")
    private Integer levelNo;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 简拼
     */
    private String jianpin;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取城市编码
     *
     * @return code - 城市编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置城市编码
     *
     * @param code 城市编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取父级编码
     *
     * @return pcode - 父级编码
     */
    public String getPcode() {
        return pcode;
    }

    /**
     * 设置父级编码
     *
     * @param pcode 父级编码
     */
    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    /**
     * 获取城市名称
     *
     * @return name - 城市名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置城市名称
     *
     * @param name 城市名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取级别
     *
     * @return level_no - 级别
     */
    public Integer getLevelNo() {
        return levelNo;
    }

    /**
     * 设置级别
     *
     * @param levelNo 级别
     */
    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    /**
     * 获取拼音
     *
     * @return pinyin - 拼音
     */
    public String getPinyin() {
        return pinyin;
    }

    /**
     * 设置拼音
     *
     * @param pinyin 拼音
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    /**
     * 获取简拼
     *
     * @return jianpin - 简拼
     */
    public String getJianpin() {
        return jianpin;
    }

    /**
     * 设置简拼
     *
     * @param jianpin 简拼
     */
    public void setJianpin(String jianpin) {
        this.jianpin = jianpin;
    }
}