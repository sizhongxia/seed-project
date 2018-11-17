package com.company.project.model;

import javax.persistence.*;

@Table(name = "equipment_discharge_parameter")
public class EquipmentDischargeParameter {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 设备表里面的UUid一致
     */
    private String uuid;

    /**
     * 设备编号
     */
    @Column(name = "equipmentNo")
    private String equipmentno;

    /**
     * 数据时间，也是就是数据包里带的时间
     */
    @Column(name = "data_time")
    private Long dataTime;

    /**
     * 安装时间  设备只传递了年月日
     */
    @Column(name = "installation_time")
    private Long installationTime;

    /**
     * 额定载荷
     */
    @Column(name = "safe_weight")
    private Double safeWeight;

    /**
     * 预警系数
     */
    @Column(name = "early_warning_ratio")
    private Byte earlyWarningRatio;

    /**
     * 报警系数
     */
    @Column(name = "give_alarm_ratio")
    private Byte giveAlarmRatio;

    /**
     * 空载AD1
     */
    @Column(name = "empty_load_ad1")
    private Double emptyLoadAd1;

    /**
     * 空载AD2
     */
    @Column(name = "empty_load_ad2")
    private Double emptyLoadAd2;

    /**
     * 空载AD3
     */
    @Column(name = "empty_load_ad3")
    private Double emptyLoadAd3;

    /**
     * 空载AD4
     */
    @Column(name = "empty_load_ad4")
    private Double emptyLoadAd4;

    /**
     * 空载AD
     */
    @Column(name = "empty_load_ad")
    private Double emptyLoadAd;

    /**
     * 标准重物AD1
     */
    @Column(name = "standard_weight_ad1")
    private Double standardWeightAd1;

    /**
     * 标准重物AD2
     */
    @Column(name = "standard_weight_ad2")
    private Double standardWeightAd2;

    /**
     * 标准重物AD3
     */
    @Column(name = "standard_weight_ad3")
    private Double standardWeightAd3;

    /**
     * 标准重物AD4
     */
    @Column(name = "standard_weight_ad4")
    private Double standardWeightAd4;

    /**
     * 标准重物AD
     */
    @Column(name = "standard_weight_ad")
    private Double standardWeightAd;

    /**
     * 标准重物
     */
    @Column(name = "standard_weight")
    private Double standardWeight;

    /**
     * 倾角预警值
     */
    @Column(name = "dip_angle_early_warning")
    private Double dipAngleEarlyWarning;

    /**
     * 倾角报警值
     */
    @Column(name = "dip_angle_give_alarm")
    private Double dipAngleGiveAlarm;

    /**
     * 软件版本号
     */
    @Column(name = "soft_version")
    private String softVersion;

    /**
     * 创建时间
     */
    @Column(name = "creat_time")
    private Long creatTime;

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
     * 获取设备表里面的UUid一致
     *
     * @return uuid - 设备表里面的UUid一致
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置设备表里面的UUid一致
     *
     * @param uuid 设备表里面的UUid一致
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取设备编号
     *
     * @return equipmentNo - 设备编号
     */
    public String getEquipmentno() {
        return equipmentno;
    }

    /**
     * 设置设备编号
     *
     * @param equipmentno 设备编号
     */
    public void setEquipmentno(String equipmentno) {
        this.equipmentno = equipmentno;
    }

    /**
     * 获取数据时间，也是就是数据包里带的时间
     *
     * @return data_time - 数据时间，也是就是数据包里带的时间
     */
    public Long getDataTime() {
        return dataTime;
    }

    /**
     * 设置数据时间，也是就是数据包里带的时间
     *
     * @param dataTime 数据时间，也是就是数据包里带的时间
     */
    public void setDataTime(Long dataTime) {
        this.dataTime = dataTime;
    }

    /**
     * 获取安装时间  设备只传递了年月日
     *
     * @return installation_time - 安装时间  设备只传递了年月日
     */
    public Long getInstallationTime() {
        return installationTime;
    }

    /**
     * 设置安装时间  设备只传递了年月日
     *
     * @param installationTime 安装时间  设备只传递了年月日
     */
    public void setInstallationTime(Long installationTime) {
        this.installationTime = installationTime;
    }

    /**
     * 获取额定载荷
     *
     * @return safe_weight - 额定载荷
     */
    public Double getSafeWeight() {
        return safeWeight;
    }

    /**
     * 设置额定载荷
     *
     * @param safeWeight 额定载荷
     */
    public void setSafeWeight(Double safeWeight) {
        this.safeWeight = safeWeight;
    }

    /**
     * 获取预警系数
     *
     * @return early_warning_ratio - 预警系数
     */
    public Byte getEarlyWarningRatio() {
        return earlyWarningRatio;
    }

    /**
     * 设置预警系数
     *
     * @param earlyWarningRatio 预警系数
     */
    public void setEarlyWarningRatio(Byte earlyWarningRatio) {
        this.earlyWarningRatio = earlyWarningRatio;
    }

    /**
     * 获取报警系数
     *
     * @return give_alarm_ratio - 报警系数
     */
    public Byte getGiveAlarmRatio() {
        return giveAlarmRatio;
    }

    /**
     * 设置报警系数
     *
     * @param giveAlarmRatio 报警系数
     */
    public void setGiveAlarmRatio(Byte giveAlarmRatio) {
        this.giveAlarmRatio = giveAlarmRatio;
    }

    /**
     * 获取空载AD1
     *
     * @return empty_load_ad1 - 空载AD1
     */
    public Double getEmptyLoadAd1() {
        return emptyLoadAd1;
    }

    /**
     * 设置空载AD1
     *
     * @param emptyLoadAd1 空载AD1
     */
    public void setEmptyLoadAd1(Double emptyLoadAd1) {
        this.emptyLoadAd1 = emptyLoadAd1;
    }

    /**
     * 获取空载AD2
     *
     * @return empty_load_ad2 - 空载AD2
     */
    public Double getEmptyLoadAd2() {
        return emptyLoadAd2;
    }

    /**
     * 设置空载AD2
     *
     * @param emptyLoadAd2 空载AD2
     */
    public void setEmptyLoadAd2(Double emptyLoadAd2) {
        this.emptyLoadAd2 = emptyLoadAd2;
    }

    /**
     * 获取空载AD3
     *
     * @return empty_load_ad3 - 空载AD3
     */
    public Double getEmptyLoadAd3() {
        return emptyLoadAd3;
    }

    /**
     * 设置空载AD3
     *
     * @param emptyLoadAd3 空载AD3
     */
    public void setEmptyLoadAd3(Double emptyLoadAd3) {
        this.emptyLoadAd3 = emptyLoadAd3;
    }

    /**
     * 获取空载AD4
     *
     * @return empty_load_ad4 - 空载AD4
     */
    public Double getEmptyLoadAd4() {
        return emptyLoadAd4;
    }

    /**
     * 设置空载AD4
     *
     * @param emptyLoadAd4 空载AD4
     */
    public void setEmptyLoadAd4(Double emptyLoadAd4) {
        this.emptyLoadAd4 = emptyLoadAd4;
    }

    /**
     * 获取空载AD
     *
     * @return empty_load_ad - 空载AD
     */
    public Double getEmptyLoadAd() {
        return emptyLoadAd;
    }

    /**
     * 设置空载AD
     *
     * @param emptyLoadAd 空载AD
     */
    public void setEmptyLoadAd(Double emptyLoadAd) {
        this.emptyLoadAd = emptyLoadAd;
    }

    /**
     * 获取标准重物AD1
     *
     * @return standard_weight_ad1 - 标准重物AD1
     */
    public Double getStandardWeightAd1() {
        return standardWeightAd1;
    }

    /**
     * 设置标准重物AD1
     *
     * @param standardWeightAd1 标准重物AD1
     */
    public void setStandardWeightAd1(Double standardWeightAd1) {
        this.standardWeightAd1 = standardWeightAd1;
    }

    /**
     * 获取标准重物AD2
     *
     * @return standard_weight_ad2 - 标准重物AD2
     */
    public Double getStandardWeightAd2() {
        return standardWeightAd2;
    }

    /**
     * 设置标准重物AD2
     *
     * @param standardWeightAd2 标准重物AD2
     */
    public void setStandardWeightAd2(Double standardWeightAd2) {
        this.standardWeightAd2 = standardWeightAd2;
    }

    /**
     * 获取标准重物AD3
     *
     * @return standard_weight_ad3 - 标准重物AD3
     */
    public Double getStandardWeightAd3() {
        return standardWeightAd3;
    }

    /**
     * 设置标准重物AD3
     *
     * @param standardWeightAd3 标准重物AD3
     */
    public void setStandardWeightAd3(Double standardWeightAd3) {
        this.standardWeightAd3 = standardWeightAd3;
    }

    /**
     * 获取标准重物AD4
     *
     * @return standard_weight_ad4 - 标准重物AD4
     */
    public Double getStandardWeightAd4() {
        return standardWeightAd4;
    }

    /**
     * 设置标准重物AD4
     *
     * @param standardWeightAd4 标准重物AD4
     */
    public void setStandardWeightAd4(Double standardWeightAd4) {
        this.standardWeightAd4 = standardWeightAd4;
    }

    /**
     * 获取标准重物AD
     *
     * @return standard_weight_ad - 标准重物AD
     */
    public Double getStandardWeightAd() {
        return standardWeightAd;
    }

    /**
     * 设置标准重物AD
     *
     * @param standardWeightAd 标准重物AD
     */
    public void setStandardWeightAd(Double standardWeightAd) {
        this.standardWeightAd = standardWeightAd;
    }

    /**
     * 获取标准重物
     *
     * @return standard_weight - 标准重物
     */
    public Double getStandardWeight() {
        return standardWeight;
    }

    /**
     * 设置标准重物
     *
     * @param standardWeight 标准重物
     */
    public void setStandardWeight(Double standardWeight) {
        this.standardWeight = standardWeight;
    }

    /**
     * 获取倾角预警值
     *
     * @return dip_angle_early_warning - 倾角预警值
     */
    public Double getDipAngleEarlyWarning() {
        return dipAngleEarlyWarning;
    }

    /**
     * 设置倾角预警值
     *
     * @param dipAngleEarlyWarning 倾角预警值
     */
    public void setDipAngleEarlyWarning(Double dipAngleEarlyWarning) {
        this.dipAngleEarlyWarning = dipAngleEarlyWarning;
    }

    /**
     * 获取倾角报警值
     *
     * @return dip_angle_give_alarm - 倾角报警值
     */
    public Double getDipAngleGiveAlarm() {
        return dipAngleGiveAlarm;
    }

    /**
     * 设置倾角报警值
     *
     * @param dipAngleGiveAlarm 倾角报警值
     */
    public void setDipAngleGiveAlarm(Double dipAngleGiveAlarm) {
        this.dipAngleGiveAlarm = dipAngleGiveAlarm;
    }

    /**
     * 获取软件版本号
     *
     * @return soft_version - 软件版本号
     */
    public String getSoftVersion() {
        return softVersion;
    }

    /**
     * 设置软件版本号
     *
     * @param softVersion 软件版本号
     */
    public void setSoftVersion(String softVersion) {
        this.softVersion = softVersion;
    }

    /**
     * 获取创建时间
     *
     * @return creat_time - 创建时间
     */
    public Long getCreatTime() {
        return creatTime;
    }

    /**
     * 设置创建时间
     *
     * @param creatTime 创建时间
     */
    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }
}