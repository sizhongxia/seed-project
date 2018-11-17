package com.company.project.model;

import javax.persistence.*;

@Table(name = "equipment_link")
public class EquipmentLink {
    /**
     * 自增Id
     */
    @Id
    private Integer id;

    /**
     * 扬尘设备表里的uuid 
     */
    private String yuuid;

    /**
     * 雾炮在设备表里的uuid
     */
    private String wuuid;

    /**
     * 是否联动
     */
    @Column(name = "linkage_is")
    private String linkageIs;

    /**
     * 联动：报警延时时间 单位秒
     */
    @Column(name = "linkage_overalarm_delay")
    private Integer linkageOveralarmDelay;

    /**
     * 联动：单次工作时长
     */
    @Column(name = "linkage_single_time")
    private Integer linkageSingleTime;

    /**
     * 联动：间隔时长
     */
    @Column(name = "linkage_interval_time")
    private Integer linkageIntervalTime;

    /**
     * 联动：报警参考方式 0手动 1天气预报
     */
    @Column(name = "linkage_alarm_reference_mode")
    private String linkageAlarmReferenceMode;

    /**
     * 获取自增Id
     *
     * @return id - 自增Id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增Id
     *
     * @param id 自增Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取扬尘设备表里的uuid 
     *
     * @return yuuid - 扬尘设备表里的uuid 
     */
    public String getYuuid() {
        return yuuid;
    }

    /**
     * 设置扬尘设备表里的uuid 
     *
     * @param yuuid 扬尘设备表里的uuid 
     */
    public void setYuuid(String yuuid) {
        this.yuuid = yuuid;
    }

    /**
     * 获取雾炮在设备表里的uuid
     *
     * @return wuuid - 雾炮在设备表里的uuid
     */
    public String getWuuid() {
        return wuuid;
    }

    /**
     * 设置雾炮在设备表里的uuid
     *
     * @param wuuid 雾炮在设备表里的uuid
     */
    public void setWuuid(String wuuid) {
        this.wuuid = wuuid;
    }

    /**
     * 获取是否联动
     *
     * @return linkage_is - 是否联动
     */
    public String getLinkageIs() {
        return linkageIs;
    }

    /**
     * 设置是否联动
     *
     * @param linkageIs 是否联动
     */
    public void setLinkageIs(String linkageIs) {
        this.linkageIs = linkageIs;
    }

    /**
     * 获取联动：报警延时时间 单位秒
     *
     * @return linkage_overalarm_delay - 联动：报警延时时间 单位秒
     */
    public Integer getLinkageOveralarmDelay() {
        return linkageOveralarmDelay;
    }

    /**
     * 设置联动：报警延时时间 单位秒
     *
     * @param linkageOveralarmDelay 联动：报警延时时间 单位秒
     */
    public void setLinkageOveralarmDelay(Integer linkageOveralarmDelay) {
        this.linkageOveralarmDelay = linkageOveralarmDelay;
    }

    /**
     * 获取联动：单次工作时长
     *
     * @return linkage_single_time - 联动：单次工作时长
     */
    public Integer getLinkageSingleTime() {
        return linkageSingleTime;
    }

    /**
     * 设置联动：单次工作时长
     *
     * @param linkageSingleTime 联动：单次工作时长
     */
    public void setLinkageSingleTime(Integer linkageSingleTime) {
        this.linkageSingleTime = linkageSingleTime;
    }

    /**
     * 获取联动：间隔时长
     *
     * @return linkage_interval_time - 联动：间隔时长
     */
    public Integer getLinkageIntervalTime() {
        return linkageIntervalTime;
    }

    /**
     * 设置联动：间隔时长
     *
     * @param linkageIntervalTime 联动：间隔时长
     */
    public void setLinkageIntervalTime(Integer linkageIntervalTime) {
        this.linkageIntervalTime = linkageIntervalTime;
    }

    /**
     * 获取联动：报警参考方式 0手动 1天气预报
     *
     * @return linkage_alarm_reference_mode - 联动：报警参考方式 0手动 1天气预报
     */
    public String getLinkageAlarmReferenceMode() {
        return linkageAlarmReferenceMode;
    }

    /**
     * 设置联动：报警参考方式 0手动 1天气预报
     *
     * @param linkageAlarmReferenceMode 联动：报警参考方式 0手动 1天气预报
     */
    public void setLinkageAlarmReferenceMode(String linkageAlarmReferenceMode) {
        this.linkageAlarmReferenceMode = linkageAlarmReferenceMode;
    }
}