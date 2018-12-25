package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "smart_culture_weather_city")
public class SmartCultureWeatherCity {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 城市编码
     */
    @Column(name = "city_code")
    private String cityCode;

    /**
     * 城市名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 城市拼音
     */
    @Column(name = "city_pinyin")
    private String cityPinyin;

    /**
     * 省名称
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 省拼音
     */
    @Column(name = "province_pinyin")
    private String provincePinyin;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

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
     * @return city_code - 城市编码
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 设置城市编码
     *
     * @param cityCode 城市编码
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 获取城市名称
     *
     * @return city_name - 城市名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置城市名称
     *
     * @param cityName 城市名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 获取城市拼音
     *
     * @return city_pinyin - 城市拼音
     */
    public String getCityPinyin() {
        return cityPinyin;
    }

    /**
     * 设置城市拼音
     *
     * @param cityPinyin 城市拼音
     */
    public void setCityPinyin(String cityPinyin) {
        this.cityPinyin = cityPinyin;
    }

    /**
     * 获取省名称
     *
     * @return province_name - 省名称
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置省名称
     *
     * @param provinceName 省名称
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * 获取省拼音
     *
     * @return province_pinyin - 省拼音
     */
    public String getProvincePinyin() {
        return provincePinyin;
    }

    /**
     * 设置省拼音
     *
     * @param provincePinyin 省拼音
     */
    public void setProvincePinyin(String provincePinyin) {
        this.provincePinyin = provincePinyin;
    }

    /**
     * 获取创建时间
     *
     * @return create_at - 创建时间
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * 设置创建时间
     *
     * @param createAt 创建时间
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}