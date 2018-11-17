package com.company.project.model;

import javax.persistence.*;

@Table(name = "equipment_dustnoise_parameter")
public class EquipmentDustnoiseParameter {
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
     * 设备供应商，举例：中科正奇，威海精讯
     */
    @Column(name = "companyUuid")
    private String companyuuid;

    /**
     * PM2.5报警临界值
     */
    @Column(name = "pm2_5_Alarm")
    private Double pm25Alarm;

    /**
     * PM10报警临界值
     */
    @Column(name = "pm10_Alarm")
    private Double pm10Alarm;

    /**
     * 噪音报警临界值
     */
    @Column(name = "noise_Alarm")
    private Double noiseAlarm;

    /**
     * 0自动模式,1手动模式
     */
    @Column(name = "noise_pattern")
    private Integer noisePattern;

    /**
     * 上传周期（10-60）s
     */
    @Column(name = "noise_cycle")
    private Integer noiseCycle;

    /**
     * 继电器开合时间，默认1分钟
     */
    @Column(name = "noise_oc")
    private Integer noiseOc;

    /**
     * 0待下发，1被服务器抓取，2下发成功，3下发失败
     */
    @Column(name = "noise_status")
    private Integer noiseStatus;

    /**
     * 创建时间戳
     */
    @Column(name = "addTime")
    private Long addtime;

    /**
     * 更新时间戳
     */
    @Column(name = "updateTime")
    private Long updatetime;

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
     * 获取设备供应商，举例：中科正奇，威海精讯
     *
     * @return companyUuid - 设备供应商，举例：中科正奇，威海精讯
     */
    public String getCompanyuuid() {
        return companyuuid;
    }

    /**
     * 设置设备供应商，举例：中科正奇，威海精讯
     *
     * @param companyuuid 设备供应商，举例：中科正奇，威海精讯
     */
    public void setCompanyuuid(String companyuuid) {
        this.companyuuid = companyuuid;
    }

    /**
     * 获取PM2.5报警临界值
     *
     * @return pm2_5_Alarm - PM2.5报警临界值
     */
    public Double getPm25Alarm() {
        return pm25Alarm;
    }

    /**
     * 设置PM2.5报警临界值
     *
     * @param pm25Alarm PM2.5报警临界值
     */
    public void setPm25Alarm(Double pm25Alarm) {
        this.pm25Alarm = pm25Alarm;
    }

    /**
     * 获取PM10报警临界值
     *
     * @return pm10_Alarm - PM10报警临界值
     */
    public Double getPm10Alarm() {
        return pm10Alarm;
    }

    /**
     * 设置PM10报警临界值
     *
     * @param pm10Alarm PM10报警临界值
     */
    public void setPm10Alarm(Double pm10Alarm) {
        this.pm10Alarm = pm10Alarm;
    }

    /**
     * 获取噪音报警临界值
     *
     * @return noise_Alarm - 噪音报警临界值
     */
    public Double getNoiseAlarm() {
        return noiseAlarm;
    }

    /**
     * 设置噪音报警临界值
     *
     * @param noiseAlarm 噪音报警临界值
     */
    public void setNoiseAlarm(Double noiseAlarm) {
        this.noiseAlarm = noiseAlarm;
    }

    /**
     * 获取0自动模式,1手动模式
     *
     * @return noise_pattern - 0自动模式,1手动模式
     */
    public Integer getNoisePattern() {
        return noisePattern;
    }

    /**
     * 设置0自动模式,1手动模式
     *
     * @param noisePattern 0自动模式,1手动模式
     */
    public void setNoisePattern(Integer noisePattern) {
        this.noisePattern = noisePattern;
    }

    /**
     * 获取上传周期（10-60）s
     *
     * @return noise_cycle - 上传周期（10-60）s
     */
    public Integer getNoiseCycle() {
        return noiseCycle;
    }

    /**
     * 设置上传周期（10-60）s
     *
     * @param noiseCycle 上传周期（10-60）s
     */
    public void setNoiseCycle(Integer noiseCycle) {
        this.noiseCycle = noiseCycle;
    }

    /**
     * 获取继电器开合时间，默认1分钟
     *
     * @return noise_oc - 继电器开合时间，默认1分钟
     */
    public Integer getNoiseOc() {
        return noiseOc;
    }

    /**
     * 设置继电器开合时间，默认1分钟
     *
     * @param noiseOc 继电器开合时间，默认1分钟
     */
    public void setNoiseOc(Integer noiseOc) {
        this.noiseOc = noiseOc;
    }

    /**
     * 获取0待下发，1被服务器抓取，2下发成功，3下发失败
     *
     * @return noise_status - 0待下发，1被服务器抓取，2下发成功，3下发失败
     */
    public Integer getNoiseStatus() {
        return noiseStatus;
    }

    /**
     * 设置0待下发，1被服务器抓取，2下发成功，3下发失败
     *
     * @param noiseStatus 0待下发，1被服务器抓取，2下发成功，3下发失败
     */
    public void setNoiseStatus(Integer noiseStatus) {
        this.noiseStatus = noiseStatus;
    }

    /**
     * 获取创建时间戳
     *
     * @return addTime - 创建时间戳
     */
    public Long getAddtime() {
        return addtime;
    }

    /**
     * 设置创建时间戳
     *
     * @param addtime 创建时间戳
     */
    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取更新时间戳
     *
     * @return updateTime - 更新时间戳
     */
    public Long getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置更新时间戳
     *
     * @param updatetime 更新时间戳
     */
    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }
}