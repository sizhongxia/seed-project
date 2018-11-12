package com.company.project.model;

import javax.persistence.*;

@Table(name = "unit_worktype")
public class UnitWorkType {
    /**
     * 工种id
     */
    @Id
    private Integer id;

    /**
     * 工种名称
     */
    private String name;

    /**
     * 添加时间
     */
    @Column(name = "addTime")
    private Long addtime;

    /**
     * 更新时间
     */
    @Column(name = "updateTime")
    private Long updatetime;

    /**
     * 数据状态 0 正常1 删除
     */
    private Integer state;

    /**
     * 0系统配置只能用不能改   1 各工地自己添加的自己随便动
     */
    private Integer type;

    /**
     * 工地的uuid
     */
    @Column(name = "proUuid")
    private String prouuid;

    /**
     * 获取工种id
     *
     * @return id - 工种id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置工种id
     *
     * @param id 工种id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取工种名称
     *
     * @return name - 工种名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置工种名称
     *
     * @param name 工种名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取添加时间
     *
     * @return addTime - 添加时间
     */
    public Long getAddtime() {
        return addtime;
    }

    /**
     * 设置添加时间
     *
     * @param addtime 添加时间
     */
    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取更新时间
     *
     * @return updateTime - 更新时间
     */
    public Long getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置更新时间
     *
     * @param updatetime 更新时间
     */
    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取数据状态 0 正常1 删除
     *
     * @return state - 数据状态 0 正常1 删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置数据状态 0 正常1 删除
     *
     * @param state 数据状态 0 正常1 删除
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取0系统配置只能用不能改   1 各工地自己添加的自己随便动
     *
     * @return type - 0系统配置只能用不能改   1 各工地自己添加的自己随便动
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置0系统配置只能用不能改   1 各工地自己添加的自己随便动
     *
     * @param type 0系统配置只能用不能改   1 各工地自己添加的自己随便动
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取工地的uuid
     *
     * @return proUuid - 工地的uuid
     */
    public String getProuuid() {
        return prouuid;
    }

    /**
     * 设置工地的uuid
     *
     * @param prouuid 工地的uuid
     */
    public void setProuuid(String prouuid) {
        this.prouuid = prouuid;
    }
}