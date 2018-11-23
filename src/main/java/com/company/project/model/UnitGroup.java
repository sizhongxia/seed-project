package com.company.project.model;

import javax.persistence.*;

@Table(name = "unit_group")
public class UnitGroup {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 班组id数据id唯一主键
     */
    private String uuid;

    /**
     * 班组名称
     */
    private String name;

    /**
     * 工地uuid
     */
    @Column(name = "proUuid")
    private String prouuid;

    /**
     * 劳务分包商企业uuid
     */
    @Column(name = "companyUuid")
    private String companyuuid;

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
     * 0正常 1删除
     */
    private Integer state;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取班组id数据id唯一主键
     *
     * @return uuid - 班组id数据id唯一主键
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置班组id数据id唯一主键
     *
     * @param uuid 班组id数据id唯一主键
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取班组名称
     *
     * @return name - 班组名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置班组名称
     *
     * @param name 班组名称
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
     * 获取劳务分包商企业uuid
     *
     * @return companyUuid - 劳务分包商企业uuid
     */
    public String getCompanyuuid() {
        return companyuuid;
    }

    /**
     * 设置劳务分包商企业uuid
     *
     * @param companyuuid 劳务分包商企业uuid
     */
    public void setCompanyuuid(String companyuuid) {
        this.companyuuid = companyuuid;
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