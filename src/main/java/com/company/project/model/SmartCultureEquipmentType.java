package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "smart_culture_equipment_type")
public class SmartCultureEquipmentType {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 类型
     */
    @Column(name = "type_id")
    private String typeId;

    /**
     * 设备类型名称
     */
    @Column(name = "type_name")
    private String typeName;

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
     * 获取类型
     *
     * @return type_id - 类型
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * 设置类型
     *
     * @param typeId 类型
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取设备类型名称
     *
     * @return type_name - 设备类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置设备类型名称
     *
     * @param typeName 设备类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
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