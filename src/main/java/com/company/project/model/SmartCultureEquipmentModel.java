package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "smart_culture_equipment_model")
public class SmartCultureEquipmentModel {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 规格型号
     */
    @Column(name = "model_id")
    private String modelId;

    /**
     * 规格型号名称
     */
    @Column(name = "model_name")
    private String modelName;

    /**
     * 类型
     */
    @Column(name = "equipment_type_id")
    private String equipmentTypeId;

    /**
     * 设备类型名称
     */
    @Column(name = "equipment_type_name")
    private String equipmentTypeName;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 备注
     */
    private String remark;

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
     * 获取规格型号
     *
     * @return model_id - 规格型号
     */
    public String getModelId() {
        return modelId;
    }

    /**
     * 设置规格型号
     *
     * @param modelId 规格型号
     */
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    /**
     * 获取规格型号名称
     *
     * @return model_name - 规格型号名称
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * 设置规格型号名称
     *
     * @param modelName 规格型号名称
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * 获取类型
     *
     * @return equipment_type_id - 类型
     */
    public String getEquipmentTypeId() {
        return equipmentTypeId;
    }

    /**
     * 设置类型
     *
     * @param equipmentTypeId 类型
     */
    public void setEquipmentTypeId(String equipmentTypeId) {
        this.equipmentTypeId = equipmentTypeId;
    }

    /**
     * 获取设备类型名称
     *
     * @return equipment_type_name - 设备类型名称
     */
    public String getEquipmentTypeName() {
        return equipmentTypeName;
    }

    /**
     * 设置设备类型名称
     *
     * @param equipmentTypeName 设备类型名称
     */
    public void setEquipmentTypeName(String equipmentTypeName) {
        this.equipmentTypeName = equipmentTypeName;
    }

    /**
     * 获取供应商
     *
     * @return supplier - 供应商
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * 设置供应商
     *
     * @param supplier 供应商
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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