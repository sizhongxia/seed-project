package com.company.project.model;

import javax.persistence.*;

@Table(name = "unit_laborsubcontractor")
public class UnitLaborsubcontractor {
    /**
     * 自增id
     */
    @Id
    private Integer id;

    /**
     * 工地uuid
     */
    @Column(name = "proUuid")
    private String prouuid;

    /**
     * 劳务分包商uuid
     */
    @Column(name = "companyUuid")
    private String companyuuid;

    /**
     * 添加时间毫秒值
     */
    @Column(name = "addTime")
    private Long addtime;

    /**
     * 状态0 正常1 删除
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
     * 获取劳务分包商uuid
     *
     * @return companyUuid - 劳务分包商uuid
     */
    public String getCompanyuuid() {
        return companyuuid;
    }

    /**
     * 设置劳务分包商uuid
     *
     * @param companyuuid 劳务分包商uuid
     */
    public void setCompanyuuid(String companyuuid) {
        this.companyuuid = companyuuid;
    }

    /**
     * 获取添加时间毫秒值
     *
     * @return addTime - 添加时间毫秒值
     */
    public Long getAddtime() {
        return addtime;
    }

    /**
     * 设置添加时间毫秒值
     *
     * @param addtime 添加时间毫秒值
     */
    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取状态0 正常1 删除
     *
     * @return state - 状态0 正常1 删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态0 正常1 删除
     *
     * @param state 状态0 正常1 删除
     */
    public void setState(Integer state) {
        this.state = state;
    }
}