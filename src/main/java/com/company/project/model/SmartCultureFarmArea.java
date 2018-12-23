package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "smart_culture_farm_area")
public class SmartCultureFarmArea {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 区域ID
     */
    @Column(name = "area_id")
    private String areaId;

    /**
     * 所属农场
     */
    @Column(name = "farm_id")
    private String farmId;

    /**
     * 区域名称
     */
    @Column(name = "area_name")
    private String areaName;

    /**
     * 厂区描述
     */
    @Column(name = "area_describe")
    private String areaDescribe;

    /**
     * 厂区位置
     */
    @Column(name = "area_position")
    private String areaPosition;

    /**
     * 区域面积
     */
    private String acreage;

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
     * 获取区域ID
     *
     * @return area_id - 区域ID
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * 设置区域ID
     *
     * @param areaId 区域ID
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
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
     * 获取区域名称
     *
     * @return area_name - 区域名称
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 设置区域名称
     *
     * @param areaName 区域名称
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * 获取厂区描述
     *
     * @return area_describe - 厂区描述
     */
    public String getAreaDescribe() {
        return areaDescribe;
    }

    /**
     * 设置厂区描述
     *
     * @param areaDescribe 厂区描述
     */
    public void setAreaDescribe(String areaDescribe) {
        this.areaDescribe = areaDescribe;
    }

    /**
     * 获取厂区位置
     *
     * @return area_position - 厂区位置
     */
    public String getAreaPosition() {
        return areaPosition;
    }

    /**
     * 设置厂区位置
     *
     * @param areaPosition 厂区位置
     */
    public void setAreaPosition(String areaPosition) {
        this.areaPosition = areaPosition;
    }

    /**
     * 获取区域面积
     *
     * @return acreage - 区域面积
     */
    public String getAcreage() {
        return acreage;
    }

    /**
     * 设置区域面积
     *
     * @param acreage 区域面积
     */
    public void setAcreage(String acreage) {
        this.acreage = acreage;
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