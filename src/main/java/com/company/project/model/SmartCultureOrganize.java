package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "smart_culture_organize")
public class SmartCultureOrganize {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 组织ID
     */
    @Column(name = "organize_id")
    private String organizeId;

    /**
     * 组织名称
     */
    @Column(name = "organize_name")
    private String organizeName;

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
     * 获取组织ID
     *
     * @return organize_id - 组织ID
     */
    public String getOrganizeId() {
        return organizeId;
    }

    /**
     * 设置组织ID
     *
     * @param organizeId 组织ID
     */
    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    /**
     * 获取组织名称
     *
     * @return organize_name - 组织名称
     */
    public String getOrganizeName() {
        return organizeName;
    }

    /**
     * 设置组织名称
     *
     * @param organizeName 组织名称
     */
    public void setOrganizeName(String organizeName) {
        this.organizeName = organizeName;
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