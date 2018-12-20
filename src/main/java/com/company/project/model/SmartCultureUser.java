package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "smart_culture_user")
public class SmartCultureUser {
    /**
     * 主键ID
     */
    @Id
    private Long id;

    /**
     * 唯一关联主键
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 登陆密码
     */
    private String password;

    /**
     * 头像
     */
    @Column(name = "user_avator")
    private String userAvator;

    /**
     * 手机号
     */
    @Column(name = "phone_no")
    private String phoneNo;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 账号状态（0:正常，1禁用）
     */
    @Column(name = "account_state")
    private Integer accountState;

    /**
     * 版本
     */
    private Long version;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 修改时间
     */
    @Column(name = "update_at")
    private Date updateAt;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取唯一关联主键
     *
     * @return user_id - 唯一关联主键
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置唯一关联主键
     *
     * @param userId 唯一关联主键
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取登陆密码
     *
     * @return password - 登陆密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登陆密码
     *
     * @param password 登陆密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取头像
     *
     * @return user_avator - 头像
     */
    public String getUserAvator() {
        return userAvator;
    }

    /**
     * 设置头像
     *
     * @param userAvator 头像
     */
    public void setUserAvator(String userAvator) {
        this.userAvator = userAvator;
    }

    /**
     * 获取手机号
     *
     * @return phone_no - 手机号
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * 设置手机号
     *
     * @param phoneNo 手机号
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取账号状态（0:正常，1禁用）
     *
     * @return account_state - 账号状态（0:正常，1禁用）
     */
    public Integer getAccountState() {
        return accountState;
    }

    /**
     * 设置账号状态（0:正常，1禁用）
     *
     * @param accountState 账号状态（0:正常，1禁用）
     */
    public void setAccountState(Integer accountState) {
        this.accountState = accountState;
    }

    /**
     * 获取版本
     *
     * @return version - 版本
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 设置版本
     *
     * @param version 版本
     */
    public void setVersion(Long version) {
        this.version = version;
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
     * 获取修改时间
     *
     * @return update_at - 修改时间
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * 设置修改时间
     *
     * @param updateAt 修改时间
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}