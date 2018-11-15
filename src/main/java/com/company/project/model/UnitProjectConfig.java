package com.company.project.model;

import javax.persistence.*;

@Table(name = "unit_project_config")
public class UnitProjectConfig {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 工地id
     */
    @Column(name = "proUuid")
    private String prouuid;

    /**
     * 是否以人员定位数据作为，考勤数据定位 1 人员定位，其他不是
     */
    @Column(name = "isPersonnelPositioning")
    private Integer ispersonnelpositioning;

    /**
     * 工地是否在政务版显示，1 不显示其他显示 
     */
    @Column(name = "isShowGov")
    private Integer isshowgov;

    /**
     * 项目bim地址
     */
    @Column(name = "bim_url")
    private String bimUrl;

    /**
     * 项目bim放大比例
     */
    @Column(name = "bim_proportion")
    private String bimProportion;

    /**
     * 获取自增主键
     *
     * @return id - 自增主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增主键
     *
     * @param id 自增主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取工地id
     *
     * @return proUuid - 工地id
     */
    public String getProuuid() {
        return prouuid;
    }

    /**
     * 设置工地id
     *
     * @param prouuid 工地id
     */
    public void setProuuid(String prouuid) {
        this.prouuid = prouuid;
    }

    /**
     * 获取是否以人员定位数据作为，考勤数据定位 1 人员定位，其他不是
     *
     * @return isPersonnelPositioning - 是否以人员定位数据作为，考勤数据定位 1 人员定位，其他不是
     */
    public Integer getIspersonnelpositioning() {
        return ispersonnelpositioning;
    }

    /**
     * 设置是否以人员定位数据作为，考勤数据定位 1 人员定位，其他不是
     *
     * @param ispersonnelpositioning 是否以人员定位数据作为，考勤数据定位 1 人员定位，其他不是
     */
    public void setIspersonnelpositioning(Integer ispersonnelpositioning) {
        this.ispersonnelpositioning = ispersonnelpositioning;
    }

    /**
     * 获取工地是否在政务版显示，1 不显示其他显示 
     *
     * @return isShowGov - 工地是否在政务版显示，1 不显示其他显示 
     */
    public Integer getIsshowgov() {
        return isshowgov;
    }

    /**
     * 设置工地是否在政务版显示，1 不显示其他显示 
     *
     * @param isshowgov 工地是否在政务版显示，1 不显示其他显示 
     */
    public void setIsshowgov(Integer isshowgov) {
        this.isshowgov = isshowgov;
    }

    /**
     * 获取项目bim地址
     *
     * @return bim_url - 项目bim地址
     */
    public String getBimUrl() {
        return bimUrl;
    }

    /**
     * 设置项目bim地址
     *
     * @param bimUrl 项目bim地址
     */
    public void setBimUrl(String bimUrl) {
        this.bimUrl = bimUrl;
    }

    /**
     * 获取项目bim放大比例
     *
     * @return bim_proportion - 项目bim放大比例
     */
    public String getBimProportion() {
        return bimProportion;
    }

    /**
     * 设置项目bim放大比例
     *
     * @param bimProportion 项目bim放大比例
     */
    public void setBimProportion(String bimProportion) {
        this.bimProportion = bimProportion;
    }
}