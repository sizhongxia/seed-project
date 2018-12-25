package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "smart_culture_user_menu")
public class SmartCultureUserMenu {
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
     * 授权菜单名称
     */
    @Column(name = "menu_access_key")
    private String menuAccessKey;

    /**
     * 授权有效状态（1:有效，0：无效）
     */
    @Column(name = "valid_state")
    private Integer validState;

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
     * 获取授权菜单名称
     *
     * @return menu_access_key - 授权菜单名称
     */
    public String getMenuAccessKey() {
        return menuAccessKey;
    }

    /**
     * 设置授权菜单名称
     *
     * @param menuAccessKey 授权菜单名称
     */
    public void setMenuAccessKey(String menuAccessKey) {
        this.menuAccessKey = menuAccessKey;
    }

    /**
     * 获取授权有效状态（1:有效，0：无效）
     *
     * @return valid_state - 授权有效状态（1:有效，0：无效）
     */
    public Integer getValidState() {
        return validState;
    }

    /**
     * 设置授权有效状态（1:有效，0：无效）
     *
     * @param validState 授权有效状态（1:有效，0：无效）
     */
    public void setValidState(Integer validState) {
        this.validState = validState;
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