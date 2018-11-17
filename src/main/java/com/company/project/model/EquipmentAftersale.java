package com.company.project.model;

import javax.persistence.*;

@Table(name = "equipment_aftersale")
public class EquipmentAftersale {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 设备表数据id
     */
    private String uuid;

    /**
     * 设备负责人
     */
    @Column(name = "personInCharge")
    private String personincharge;

    /**
     * 设备负责人联系电话
     */
    @Column(name = "personInChargePhone")
    private String personinchargephone;

    /**
     * 设备安装人
     */
    @Column(name = "installPerson")
    private String installperson;

    /**
     * 设备安装人联系电话
     */
    @Column(name = "installPersonPhone")
    private String installpersonphone;

    /**
     * 设备回执单图片地址
     */
    private String receipt;

    /**
     * 设备安装时间
     */
    @Column(name = "installTime")
    private Long installtime;

    /**
     * 设备服务器地址
     */
    private String host;

    /**
     * 功能，区域，道路喷淋，对应sys_idctionary sprayType字段
     */
    @Column(name = "sprayType")
    private Integer spraytype;

    /**
     * 0正常  1 删除
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
     * 获取设备表数据id
     *
     * @return uuid - 设备表数据id
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置设备表数据id
     *
     * @param uuid 设备表数据id
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取设备负责人
     *
     * @return personInCharge - 设备负责人
     */
    public String getPersonincharge() {
        return personincharge;
    }

    /**
     * 设置设备负责人
     *
     * @param personincharge 设备负责人
     */
    public void setPersonincharge(String personincharge) {
        this.personincharge = personincharge;
    }

    /**
     * 获取设备负责人联系电话
     *
     * @return personInChargePhone - 设备负责人联系电话
     */
    public String getPersoninchargephone() {
        return personinchargephone;
    }

    /**
     * 设置设备负责人联系电话
     *
     * @param personinchargephone 设备负责人联系电话
     */
    public void setPersoninchargephone(String personinchargephone) {
        this.personinchargephone = personinchargephone;
    }

    /**
     * 获取设备安装人
     *
     * @return installPerson - 设备安装人
     */
    public String getInstallperson() {
        return installperson;
    }

    /**
     * 设置设备安装人
     *
     * @param installperson 设备安装人
     */
    public void setInstallperson(String installperson) {
        this.installperson = installperson;
    }

    /**
     * 获取设备安装人联系电话
     *
     * @return installPersonPhone - 设备安装人联系电话
     */
    public String getInstallpersonphone() {
        return installpersonphone;
    }

    /**
     * 设置设备安装人联系电话
     *
     * @param installpersonphone 设备安装人联系电话
     */
    public void setInstallpersonphone(String installpersonphone) {
        this.installpersonphone = installpersonphone;
    }

    /**
     * 获取设备回执单图片地址
     *
     * @return receipt - 设备回执单图片地址
     */
    public String getReceipt() {
        return receipt;
    }

    /**
     * 设置设备回执单图片地址
     *
     * @param receipt 设备回执单图片地址
     */
    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    /**
     * 获取设备安装时间
     *
     * @return installTime - 设备安装时间
     */
    public Long getInstalltime() {
        return installtime;
    }

    /**
     * 设置设备安装时间
     *
     * @param installtime 设备安装时间
     */
    public void setInstalltime(Long installtime) {
        this.installtime = installtime;
    }

    /**
     * 获取设备服务器地址
     *
     * @return host - 设备服务器地址
     */
    public String getHost() {
        return host;
    }

    /**
     * 设置设备服务器地址
     *
     * @param host 设备服务器地址
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 获取功能，区域，道路喷淋，对应sys_idctionary sprayType字段
     *
     * @return sprayType - 功能，区域，道路喷淋，对应sys_idctionary sprayType字段
     */
    public Integer getSpraytype() {
        return spraytype;
    }

    /**
     * 设置功能，区域，道路喷淋，对应sys_idctionary sprayType字段
     *
     * @param spraytype 功能，区域，道路喷淋，对应sys_idctionary sprayType字段
     */
    public void setSpraytype(Integer spraytype) {
        this.spraytype = spraytype;
    }

    /**
     * 获取0正常  1 删除
     *
     * @return state - 0正常  1 删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0正常  1 删除
     *
     * @param state 0正常  1 删除
     */
    public void setState(Integer state) {
        this.state = state;
    }
}