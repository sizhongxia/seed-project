package com.company.project.model;

import javax.persistence.*;

@Table(name = "unit_post")
public class UnitPost {
    /**
     * 自增id
     */
    @Id
    private Integer id;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 工地uuid
     */
    @Column(name = "proUuid")
    private String prouuid;

    /**
     * 部门uuid
     */
    @Column(name = "deptUuid")
    private String deptuuid;

    /**
     * 0正常 1删除
     */
    private Integer state;

    /**
     * 获取自增id
     *
     * @return id - 自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增id
     *
     * @param id 自增id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取uuid
     *
     * @return uuid - uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置uuid
     *
     * @param uuid uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取岗位名称
     *
     * @return name - 岗位名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置岗位名称
     *
     * @param name 岗位名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取工地uuid
     *
     * @return proUuid - 工地uuid
     */
    public String getProuuid() {
        return prouuid;
    }

    /**
     * 设置工地uuid
     *
     * @param prouuid 工地uuid
     */
    public void setProuuid(String prouuid) {
        this.prouuid = prouuid;
    }

    /**
     * 获取部门uuid
     *
     * @return deptUuid - 部门uuid
     */
    public String getDeptuuid() {
        return deptuuid;
    }

    /**
     * 设置部门uuid
     *
     * @param deptuuid 部门uuid
     */
    public void setDeptuuid(String deptuuid) {
        this.deptuuid = deptuuid;
    }

    /**
     * 获取0正常 1删除
     *
     * @return state - 0正常 1删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0正常 1删除
     *
     * @param state 0正常 1删除
     */
    public void setState(Integer state) {
        this.state = state;
    }
}