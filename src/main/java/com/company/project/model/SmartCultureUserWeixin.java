package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "smart_culture_user_weixin")
public class SmartCultureUserWeixin {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 微信小程序id
     */
    @Column(name = "wx_app_id")
    private String wxAppId;

    @Column(name = "session_key")
    private String sessionKey;

    /**
     * OpenId
     */
    @Column(name = "open_id")
    private String openId;

    @Column(name = "union_id")
    private String unionId;

    /**
     * 微信昵称
     */
    @Column(name = "nicke_name")
    private String nickeName;

    /**
     * 头像
     */
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 更新时间
     */
    @Column(name = "update_at")
    private Date updateAt;

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
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取微信小程序id
     *
     * @return wx_app_id - 微信小程序id
     */
    public String getWxAppId() {
        return wxAppId;
    }

    /**
     * 设置微信小程序id
     *
     * @param wxAppId 微信小程序id
     */
    public void setWxAppId(String wxAppId) {
        this.wxAppId = wxAppId;
    }

    /**
     * @return session_key
     */
    public String getSessionKey() {
        return sessionKey;
    }

    /**
     * @param sessionKey
     */
    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    /**
     * 获取OpenId
     *
     * @return open_id - OpenId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置OpenId
     *
     * @param openId OpenId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * @return union_id
     */
    public String getUnionId() {
        return unionId;
    }

    /**
     * @param unionId
     */
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    /**
     * 获取微信昵称
     *
     * @return nicke_name - 微信昵称
     */
    public String getNickeName() {
        return nickeName;
    }

    /**
     * 设置微信昵称
     *
     * @param nickeName 微信昵称
     */
    public void setNickeName(String nickeName) {
        this.nickeName = nickeName;
    }

    /**
     * 获取头像
     *
     * @return avatar_url - 头像
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 设置头像
     *
     * @param avatarUrl 头像
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取省份
     *
     * @return province - 省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省份
     *
     * @param province 省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取性别
     *
     * @return gender - 性别
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(Integer gender) {
        this.gender = gender;
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

    /**
     * 获取更新时间
     *
     * @return update_at - 更新时间
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * 设置更新时间
     *
     * @param updateAt 更新时间
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}