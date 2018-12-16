package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yeetong_banner")
public class YeeTongBanner {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 唯一主键
     */
    @Column(name = "unique_id")
    private String uniqueId;

    /**
     * 新闻标题
     */
    @Column(name = "banner_title")
    private String bannerTitle;

    /**
     * 类别
     */
    @Column(name = "banner_type")
    private String bannerType;

    /**
     * 图片URL（PC:1920x900）
     */
    @Column(name = "pic_url")
    private String picUrl;

    /**
     * 跳转链接
     */
    @Column(name = "jump_link")
    private String jumpLink;

    /**
     * 排序权重
     */
    @Column(name = "weight_num")
    private Integer weightNum;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取唯一主键
     *
     * @return unique_id - 唯一主键
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * 设置唯一主键
     *
     * @param uniqueId 唯一主键
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * 获取新闻标题
     *
     * @return banner_title - 新闻标题
     */
    public String getBannerTitle() {
        return bannerTitle;
    }

    /**
     * 设置新闻标题
     *
     * @param bannerTitle 新闻标题
     */
    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    /**
     * 获取类别
     *
     * @return banner_type - 类别
     */
    public String getBannerType() {
        return bannerType;
    }

    /**
     * 设置类别
     *
     * @param bannerType 类别
     */
    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    /**
     * 获取图片URL（PC:1920x900）
     *
     * @return pic_url - 图片URL（PC:1920x900）
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设置图片URL（PC:1920x900）
     *
     * @param picUrl 图片URL（PC:1920x900）
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取跳转链接
     *
     * @return jump_link - 跳转链接
     */
    public String getJumpLink() {
        return jumpLink;
    }

    /**
     * 设置跳转链接
     *
     * @param jumpLink 跳转链接
     */
    public void setJumpLink(String jumpLink) {
        this.jumpLink = jumpLink;
    }

    /**
     * 获取排序权重
     *
     * @return weight_num - 排序权重
     */
    public Integer getWeightNum() {
        return weightNum;
    }

    /**
     * 设置排序权重
     *
     * @param weightNum 排序权重
     */
    public void setWeightNum(Integer weightNum) {
        this.weightNum = weightNum;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}