package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "smart_culture_user_identity")
public class SmartCultureUserIdentity {
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
     * 用户身份（super_admin, admin, user）
     */
    private String identity;

    /**
     * 使用状态
     */
    @Column(name = "use_state")
    private Integer useState;

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
     * 获取用户身份（super_admin, admin, user）
     *
     * @return identity - 用户身份（super_admin, admin, user）
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * 设置用户身份（super_admin, admin, user）
     *
     * @param identity 用户身份（super_admin, admin, user）
     */
    public void setIdentity(String identity) {
        this.identity = identity;
    }

    /**
     * 获取使用状态
     *
     * @return use_state - 使用状态
     */
    public Integer getUseState() {
        return useState;
    }

    /**
     * 设置使用状态
     *
     * @param useState 使用状态
     */
    public void setUseState(Integer useState) {
        this.useState = useState;
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