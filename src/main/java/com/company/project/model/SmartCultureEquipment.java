package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "smart_culture_equipment")
public class SmartCultureEquipment {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 设备ID
     */
    @Column(name = "equipment_id")
    private String equipmentId;

    /**
     * 设备名称
     */
    @Column(name = "equipment_name")
    private String equipmentName;

    /**
     * 设备编码
     */
    @Column(name = "equipment_code")
    private String equipmentCode;

    /**
     * 设备序列号
     */
    @Column(name = "equipment_sn")
    private String equipmentSn;

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
     * 规格型号
     */
    @Column(name = "equipment_model_id")
    private String equipmentModelId;

    /**
     * 规格型号名称
     */
    @Column(name = "equipment_model_name")
    private String equipmentModelName;

    /**
     * 所属农场
     */
    @Column(name = "farm_id")
    private String farmId;

    /**
     * 所在厂区
     */
    @Column(name = "farm_area_id")
    private String farmAreaId;

    /**
     * 操作人
     */
    private String operator;

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
     * 获取设备ID
     *
     * @return equipment_id - 设备ID
     */
    public String getEquipmentId() {
        return equipmentId;
    }

    /**
     * 设置设备ID
     *
     * @param equipmentId 设备ID
     */
    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    /**
     * 获取设备名称
     *
     * @return equipment_name - 设备名称
     */
    public String getEquipmentName() {
        return equipmentName;
    }

    /**
     * 设置设备名称
     *
     * @param equipmentName 设备名称
     */
    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    /**
     * 获取设备编码
     *
     * @return equipment_code - 设备编码
     */
    public String getEquipmentCode() {
        return equipmentCode;
    }

    /**
     * 设置设备编码
     *
     * @param equipmentCode 设备编码
     */
    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    /**
     * 获取设备序列号
     *
     * @return equipment_sn - 设备序列号
     */
    public String getEquipmentSn() {
        return equipmentSn;
    }

    /**
     * 设置设备序列号
     *
     * @param equipmentSn 设备序列号
     */
    public void setEquipmentSn(String equipmentSn) {
        this.equipmentSn = equipmentSn;
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
     * 获取规格型号
     *
     * @return equipment_model_id - 规格型号
     */
    public String getEquipmentModelId() {
        return equipmentModelId;
    }

    /**
     * 设置规格型号
     *
     * @param equipmentModelId 规格型号
     */
    public void setEquipmentModelId(String equipmentModelId) {
        this.equipmentModelId = equipmentModelId;
    }

    /**
     * 获取规格型号名称
     *
     * @return equipment_model_name - 规格型号名称
     */
    public String getEquipmentModelName() {
        return equipmentModelName;
    }

    /**
     * 设置规格型号名称
     *
     * @param equipmentModelName 规格型号名称
     */
    public void setEquipmentModelName(String equipmentModelName) {
        this.equipmentModelName = equipmentModelName;
    }

    /**
     * 获取所属农场
     *
     * @return farm_id - 所属农场
     */
    public String getFarmId() {
        return farmId;
    }

    /**
     * 设置所属农场
     *
     * @param farmId 所属农场
     */
    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    /**
     * 获取所在厂区
     *
     * @return farm_area_id - 所在厂区
     */
    public String getFarmAreaId() {
        return farmAreaId;
    }

    /**
     * 设置所在厂区
     *
     * @param farmAreaId 所在厂区
     */
    public void setFarmAreaId(String farmAreaId) {
        this.farmAreaId = farmAreaId;
    }

    /**
     * 获取操作人
     *
     * @return operator - 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人
     *
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
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