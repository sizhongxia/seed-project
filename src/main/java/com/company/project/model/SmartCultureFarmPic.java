package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "smart_culture_farm_pic")
public class SmartCultureFarmPic {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 图片ID
     */
    @Column(name = "pic_id")
    private String picId;

    /**
     * 农场ID
     */
    @Column(name = "farm_id")
    private String farmId;

    /**
     * 农场厂区ID
     */
    @Column(name = "farm_area_id")
    private String farmAreaId;

    /**
     * 图片地址
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 图片标题
     */
    @Column(name = "pic_title")
    private String picTitle;

    /**
     * 排序编号
     */
    @Column(name = "sort_num")
    private Integer sortNum;

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
     * 获取图片ID
     *
     * @return pic_id - 图片ID
     */
    public String getPicId() {
        return picId;
    }

    /**
     * 设置图片ID
     *
     * @param picId 图片ID
     */
    public void setPicId(String picId) {
        this.picId = picId;
    }

    /**
     * 获取农场ID
     *
     * @return farm_id - 农场ID
     */
    public String getFarmId() {
        return farmId;
    }

    /**
     * 设置农场ID
     *
     * @param farmId 农场ID
     */
    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    /**
     * 获取农场厂区ID
     *
     * @return farm_area_id - 农场厂区ID
     */
    public String getFarmAreaId() {
        return farmAreaId;
    }

    /**
     * 设置农场厂区ID
     *
     * @param farmAreaId 农场厂区ID
     */
    public void setFarmAreaId(String farmAreaId) {
        this.farmAreaId = farmAreaId;
    }

    /**
     * 获取图片地址
     *
     * @return pic_url - 图片地址
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设置图片地址
     *
     * @param picUrl 图片地址
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取图片标题
     *
     * @return pic_title - 图片标题
     */
    public String getPicTitle() {
        return picTitle;
    }

    /**
     * 设置图片标题
     *
     * @param picTitle 图片标题
     */
    public void setPicTitle(String picTitle) {
        this.picTitle = picTitle;
    }

    /**
     * 获取排序编号
     *
     * @return sort_num - 排序编号
     */
    public Integer getSortNum() {
        return sortNum;
    }

    /**
     * 设置排序编号
     *
     * @param sortNum 排序编号
     */
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
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