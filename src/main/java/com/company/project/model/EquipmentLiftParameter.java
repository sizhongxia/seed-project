package com.company.project.model;

import javax.persistence.*;

@Table(name = "equipment_lift_parameter")
public class EquipmentLiftParameter {
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
     * 功能配置
     */
    @Column(name = "function_configuration")
    private String functionConfiguration;

    /**
     * 0RFID刷卡（刷一次）1RFID刷卡（持续）2指纹3指纹加卡 4虹膜5虹膜加卡6人脸识别
     */
    @Column(name = "Identification_method")
    private String identificationMethod;

    /**
     * 安装时间  设备只传递了年月日
     */
    @Column(name = "installation_time")
    private Long installationTime;

    /**
     * 空钩采样值1
     */
    @Column(name = "empty_hook_sampling_value1")
    private Double emptyHookSamplingValue1;

    /**
     * 空钩采样值2
     */
    @Column(name = "empty_hook_sampling_value2")
    private Double emptyHookSamplingValue2;

    /**
     * 空钩采样值
     */
    @Column(name = "empty_hook_sampling_value")
    private Double emptyHookSamplingValue;

    /**
     * 标准重量采样值1
     */
    @Column(name = "standard_weight_sampling_value1")
    private Double standardWeightSamplingValue1;

    /**
     * 标准重量采样值2
     */
    @Column(name = "standard_weight_sampling_value2")
    private Double standardWeightSamplingValue2;

    /**
     * 标准重物采样值
     */
    @Column(name = "standard_weight_sampling_value")
    private Double standardWeightSamplingValue;

    /**
     * 标准重物重量
     */
    @Column(name = "standard_weight_weight")
    private Double standardWeightWeight;

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
     * 分时限载起始时间1  小时
     */
    @Column(name = "time_limited_start_time1")
    private Byte timeLimitedStartTime1;

    /**
     * 分时限载结束时间1 小时
     */
    @Column(name = "time_limited_end_time1")
    private Byte timeLimitedEndTime1;

    /**
     * 分时限载起始时间2  小时
     */
    @Column(name = "time_limited_start_time2")
    private Byte timeLimitedStartTime2;

    /**
     * 分时限载结束时间2 小时
     */
    @Column(name = "time_limited_end_time2")
    private Byte timeLimitedEndTime2;

    /**
     * 分时限载起始时间3  小时
     */
    @Column(name = "time_limited_start_time3")
    private Byte timeLimitedStartTime3;

    /**
     * 分时限载结束时间3 小时
     */
    @Column(name = "time_limited_end_time3")
    private Byte timeLimitedEndTime3;

    /**
     * 分时限载起始时间4  小时
     */
    @Column(name = "time_limited_start_time4")
    private Byte timeLimitedStartTime4;

    /**
     * 分时限载结束时间4 小时
     */
    @Column(name = "time_limited_end_time4")
    private Byte timeLimitedEndTime4;

    /**
     * 分时限载额定载荷1     kg
     */
    @Column(name = "time_limited_safe_weight1")
    private Double timeLimitedSafeWeight1;

    /**
     * 分时限载额定载荷2     kg
     */
    @Column(name = "time_limited_safe_weight2")
    private Double timeLimitedSafeWeight2;

    /**
     * 分时限载额定载荷3     kg
     */
    @Column(name = "time_limited_safe_weight3")
    private Double timeLimitedSafeWeight3;

    /**
     * 分时限载额定载荷4     kg
     */
    @Column(name = "time_limited_safe_weight4")
    private Double timeLimitedSafeWeight4;

    /**
     * 1楼楼层采样值
     */
    @Column(name = "floor_sampling_one")
    private Double floorSamplingOne;

    /**
     * 最高楼层采样值
     */
    @Column(name = "floor_sampling_max")
    private Double floorSamplingMax;

    /**
     * 层高1   分米
     */
    @Column(name = "storey_height1")
    private Double storeyHeight1;

    /**
     * 层高2   分米
     */
    @Column(name = "storey_height2")
    private Double storeyHeight2;

    /**
     * 层高3   分米
     */
    @Column(name = "storey_height3")
    private Double storeyHeight3;

    /**
     * 层高4   分米
     */
    @Column(name = "storey_height4")
    private Double storeyHeight4;

    /**
     * 楼层1
     */
    private Double floor1;

    /**
     * 楼层2
     */
    private Double floor2;

    /**
     * 楼层3
     */
    private Double floor3;

    /**
     * 楼层4
     */
    private Double floor4;

    /**
     * 最大楼层
     */
    @Column(name = "floor_max")
    private Double floorMax;

    /**
     * 最大高度
     */
    @Column(name = "height_max")
    private Double heightMax;

    /**
     * 司机身份对比周期
     */
    @Column(name = "driver_identity_cycle")
    private Double driverIdentityCycle;

    /**
     * 监理身份对比周期
     */
    @Column(name = "supervision_identity_cycle")
    private Double supervisionIdentityCycle;

    /**
     * 限载人数
     */
    @Column(name = "limited_person")
    private Byte limitedPerson;

    /**
     * 纬度值
     */
    private Double latitude;

    /**
     * 北纬或南纬
     */
    @Column(name = "latitude_type")
    private Byte latitudeType;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 东经或西经
     */
    @Column(name = "longitude_type")
    private Byte longitudeType;

    /**
     * 风速预警
     */
    @Column(name = "wind_early_warning")
    private Byte windEarlyWarning;

    /**
     * 风速报警
     */
    @Column(name = "wind_give_alarm")
    private Byte windGiveAlarm;

    /**
     * boot loader 版本号
     */
    @Column(name = "boot_loader_version")
    private String bootLoaderVersion;

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
     * 获取功能配置
     *
     * @return function_configuration - 功能配置
     */
    public String getFunctionConfiguration() {
        return functionConfiguration;
    }

    /**
     * 设置功能配置
     *
     * @param functionConfiguration 功能配置
     */
    public void setFunctionConfiguration(String functionConfiguration) {
        this.functionConfiguration = functionConfiguration;
    }

    /**
     * 获取0RFID刷卡（刷一次）1RFID刷卡（持续）2指纹3指纹加卡 4虹膜5虹膜加卡6人脸识别
     *
     * @return Identification_method - 0RFID刷卡（刷一次）1RFID刷卡（持续）2指纹3指纹加卡 4虹膜5虹膜加卡6人脸识别
     */
    public String getIdentificationMethod() {
        return identificationMethod;
    }

    /**
     * 设置0RFID刷卡（刷一次）1RFID刷卡（持续）2指纹3指纹加卡 4虹膜5虹膜加卡6人脸识别
     *
     * @param identificationMethod 0RFID刷卡（刷一次）1RFID刷卡（持续）2指纹3指纹加卡 4虹膜5虹膜加卡6人脸识别
     */
    public void setIdentificationMethod(String identificationMethod) {
        this.identificationMethod = identificationMethod;
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
     * 获取空钩采样值1
     *
     * @return empty_hook_sampling_value1 - 空钩采样值1
     */
    public Double getEmptyHookSamplingValue1() {
        return emptyHookSamplingValue1;
    }

    /**
     * 设置空钩采样值1
     *
     * @param emptyHookSamplingValue1 空钩采样值1
     */
    public void setEmptyHookSamplingValue1(Double emptyHookSamplingValue1) {
        this.emptyHookSamplingValue1 = emptyHookSamplingValue1;
    }

    /**
     * 获取空钩采样值2
     *
     * @return empty_hook_sampling_value2 - 空钩采样值2
     */
    public Double getEmptyHookSamplingValue2() {
        return emptyHookSamplingValue2;
    }

    /**
     * 设置空钩采样值2
     *
     * @param emptyHookSamplingValue2 空钩采样值2
     */
    public void setEmptyHookSamplingValue2(Double emptyHookSamplingValue2) {
        this.emptyHookSamplingValue2 = emptyHookSamplingValue2;
    }

    /**
     * 获取空钩采样值
     *
     * @return empty_hook_sampling_value - 空钩采样值
     */
    public Double getEmptyHookSamplingValue() {
        return emptyHookSamplingValue;
    }

    /**
     * 设置空钩采样值
     *
     * @param emptyHookSamplingValue 空钩采样值
     */
    public void setEmptyHookSamplingValue(Double emptyHookSamplingValue) {
        this.emptyHookSamplingValue = emptyHookSamplingValue;
    }

    /**
     * 获取标准重量采样值1
     *
     * @return standard_weight_sampling_value1 - 标准重量采样值1
     */
    public Double getStandardWeightSamplingValue1() {
        return standardWeightSamplingValue1;
    }

    /**
     * 设置标准重量采样值1
     *
     * @param standardWeightSamplingValue1 标准重量采样值1
     */
    public void setStandardWeightSamplingValue1(Double standardWeightSamplingValue1) {
        this.standardWeightSamplingValue1 = standardWeightSamplingValue1;
    }

    /**
     * 获取标准重量采样值2
     *
     * @return standard_weight_sampling_value2 - 标准重量采样值2
     */
    public Double getStandardWeightSamplingValue2() {
        return standardWeightSamplingValue2;
    }

    /**
     * 设置标准重量采样值2
     *
     * @param standardWeightSamplingValue2 标准重量采样值2
     */
    public void setStandardWeightSamplingValue2(Double standardWeightSamplingValue2) {
        this.standardWeightSamplingValue2 = standardWeightSamplingValue2;
    }

    /**
     * 获取标准重物采样值
     *
     * @return standard_weight_sampling_value - 标准重物采样值
     */
    public Double getStandardWeightSamplingValue() {
        return standardWeightSamplingValue;
    }

    /**
     * 设置标准重物采样值
     *
     * @param standardWeightSamplingValue 标准重物采样值
     */
    public void setStandardWeightSamplingValue(Double standardWeightSamplingValue) {
        this.standardWeightSamplingValue = standardWeightSamplingValue;
    }

    /**
     * 获取标准重物重量
     *
     * @return standard_weight_weight - 标准重物重量
     */
    public Double getStandardWeightWeight() {
        return standardWeightWeight;
    }

    /**
     * 设置标准重物重量
     *
     * @param standardWeightWeight 标准重物重量
     */
    public void setStandardWeightWeight(Double standardWeightWeight) {
        this.standardWeightWeight = standardWeightWeight;
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
     * 获取分时限载起始时间1  小时
     *
     * @return time_limited_start_time1 - 分时限载起始时间1  小时
     */
    public Byte getTimeLimitedStartTime1() {
        return timeLimitedStartTime1;
    }

    /**
     * 设置分时限载起始时间1  小时
     *
     * @param timeLimitedStartTime1 分时限载起始时间1  小时
     */
    public void setTimeLimitedStartTime1(Byte timeLimitedStartTime1) {
        this.timeLimitedStartTime1 = timeLimitedStartTime1;
    }

    /**
     * 获取分时限载结束时间1 小时
     *
     * @return time_limited_end_time1 - 分时限载结束时间1 小时
     */
    public Byte getTimeLimitedEndTime1() {
        return timeLimitedEndTime1;
    }

    /**
     * 设置分时限载结束时间1 小时
     *
     * @param timeLimitedEndTime1 分时限载结束时间1 小时
     */
    public void setTimeLimitedEndTime1(Byte timeLimitedEndTime1) {
        this.timeLimitedEndTime1 = timeLimitedEndTime1;
    }

    /**
     * 获取分时限载起始时间2  小时
     *
     * @return time_limited_start_time2 - 分时限载起始时间2  小时
     */
    public Byte getTimeLimitedStartTime2() {
        return timeLimitedStartTime2;
    }

    /**
     * 设置分时限载起始时间2  小时
     *
     * @param timeLimitedStartTime2 分时限载起始时间2  小时
     */
    public void setTimeLimitedStartTime2(Byte timeLimitedStartTime2) {
        this.timeLimitedStartTime2 = timeLimitedStartTime2;
    }

    /**
     * 获取分时限载结束时间2 小时
     *
     * @return time_limited_end_time2 - 分时限载结束时间2 小时
     */
    public Byte getTimeLimitedEndTime2() {
        return timeLimitedEndTime2;
    }

    /**
     * 设置分时限载结束时间2 小时
     *
     * @param timeLimitedEndTime2 分时限载结束时间2 小时
     */
    public void setTimeLimitedEndTime2(Byte timeLimitedEndTime2) {
        this.timeLimitedEndTime2 = timeLimitedEndTime2;
    }

    /**
     * 获取分时限载起始时间3  小时
     *
     * @return time_limited_start_time3 - 分时限载起始时间3  小时
     */
    public Byte getTimeLimitedStartTime3() {
        return timeLimitedStartTime3;
    }

    /**
     * 设置分时限载起始时间3  小时
     *
     * @param timeLimitedStartTime3 分时限载起始时间3  小时
     */
    public void setTimeLimitedStartTime3(Byte timeLimitedStartTime3) {
        this.timeLimitedStartTime3 = timeLimitedStartTime3;
    }

    /**
     * 获取分时限载结束时间3 小时
     *
     * @return time_limited_end_time3 - 分时限载结束时间3 小时
     */
    public Byte getTimeLimitedEndTime3() {
        return timeLimitedEndTime3;
    }

    /**
     * 设置分时限载结束时间3 小时
     *
     * @param timeLimitedEndTime3 分时限载结束时间3 小时
     */
    public void setTimeLimitedEndTime3(Byte timeLimitedEndTime3) {
        this.timeLimitedEndTime3 = timeLimitedEndTime3;
    }

    /**
     * 获取分时限载起始时间4  小时
     *
     * @return time_limited_start_time4 - 分时限载起始时间4  小时
     */
    public Byte getTimeLimitedStartTime4() {
        return timeLimitedStartTime4;
    }

    /**
     * 设置分时限载起始时间4  小时
     *
     * @param timeLimitedStartTime4 分时限载起始时间4  小时
     */
    public void setTimeLimitedStartTime4(Byte timeLimitedStartTime4) {
        this.timeLimitedStartTime4 = timeLimitedStartTime4;
    }

    /**
     * 获取分时限载结束时间4 小时
     *
     * @return time_limited_end_time4 - 分时限载结束时间4 小时
     */
    public Byte getTimeLimitedEndTime4() {
        return timeLimitedEndTime4;
    }

    /**
     * 设置分时限载结束时间4 小时
     *
     * @param timeLimitedEndTime4 分时限载结束时间4 小时
     */
    public void setTimeLimitedEndTime4(Byte timeLimitedEndTime4) {
        this.timeLimitedEndTime4 = timeLimitedEndTime4;
    }

    /**
     * 获取分时限载额定载荷1     kg
     *
     * @return time_limited_safe_weight1 - 分时限载额定载荷1     kg
     */
    public Double getTimeLimitedSafeWeight1() {
        return timeLimitedSafeWeight1;
    }

    /**
     * 设置分时限载额定载荷1     kg
     *
     * @param timeLimitedSafeWeight1 分时限载额定载荷1     kg
     */
    public void setTimeLimitedSafeWeight1(Double timeLimitedSafeWeight1) {
        this.timeLimitedSafeWeight1 = timeLimitedSafeWeight1;
    }

    /**
     * 获取分时限载额定载荷2     kg
     *
     * @return time_limited_safe_weight2 - 分时限载额定载荷2     kg
     */
    public Double getTimeLimitedSafeWeight2() {
        return timeLimitedSafeWeight2;
    }

    /**
     * 设置分时限载额定载荷2     kg
     *
     * @param timeLimitedSafeWeight2 分时限载额定载荷2     kg
     */
    public void setTimeLimitedSafeWeight2(Double timeLimitedSafeWeight2) {
        this.timeLimitedSafeWeight2 = timeLimitedSafeWeight2;
    }

    /**
     * 获取分时限载额定载荷3     kg
     *
     * @return time_limited_safe_weight3 - 分时限载额定载荷3     kg
     */
    public Double getTimeLimitedSafeWeight3() {
        return timeLimitedSafeWeight3;
    }

    /**
     * 设置分时限载额定载荷3     kg
     *
     * @param timeLimitedSafeWeight3 分时限载额定载荷3     kg
     */
    public void setTimeLimitedSafeWeight3(Double timeLimitedSafeWeight3) {
        this.timeLimitedSafeWeight3 = timeLimitedSafeWeight3;
    }

    /**
     * 获取分时限载额定载荷4     kg
     *
     * @return time_limited_safe_weight4 - 分时限载额定载荷4     kg
     */
    public Double getTimeLimitedSafeWeight4() {
        return timeLimitedSafeWeight4;
    }

    /**
     * 设置分时限载额定载荷4     kg
     *
     * @param timeLimitedSafeWeight4 分时限载额定载荷4     kg
     */
    public void setTimeLimitedSafeWeight4(Double timeLimitedSafeWeight4) {
        this.timeLimitedSafeWeight4 = timeLimitedSafeWeight4;
    }

    /**
     * 获取1楼楼层采样值
     *
     * @return floor_sampling_one - 1楼楼层采样值
     */
    public Double getFloorSamplingOne() {
        return floorSamplingOne;
    }

    /**
     * 设置1楼楼层采样值
     *
     * @param floorSamplingOne 1楼楼层采样值
     */
    public void setFloorSamplingOne(Double floorSamplingOne) {
        this.floorSamplingOne = floorSamplingOne;
    }

    /**
     * 获取最高楼层采样值
     *
     * @return floor_sampling_max - 最高楼层采样值
     */
    public Double getFloorSamplingMax() {
        return floorSamplingMax;
    }

    /**
     * 设置最高楼层采样值
     *
     * @param floorSamplingMax 最高楼层采样值
     */
    public void setFloorSamplingMax(Double floorSamplingMax) {
        this.floorSamplingMax = floorSamplingMax;
    }

    /**
     * 获取层高1   分米
     *
     * @return storey_height1 - 层高1   分米
     */
    public Double getStoreyHeight1() {
        return storeyHeight1;
    }

    /**
     * 设置层高1   分米
     *
     * @param storeyHeight1 层高1   分米
     */
    public void setStoreyHeight1(Double storeyHeight1) {
        this.storeyHeight1 = storeyHeight1;
    }

    /**
     * 获取层高2   分米
     *
     * @return storey_height2 - 层高2   分米
     */
    public Double getStoreyHeight2() {
        return storeyHeight2;
    }

    /**
     * 设置层高2   分米
     *
     * @param storeyHeight2 层高2   分米
     */
    public void setStoreyHeight2(Double storeyHeight2) {
        this.storeyHeight2 = storeyHeight2;
    }

    /**
     * 获取层高3   分米
     *
     * @return storey_height3 - 层高3   分米
     */
    public Double getStoreyHeight3() {
        return storeyHeight3;
    }

    /**
     * 设置层高3   分米
     *
     * @param storeyHeight3 层高3   分米
     */
    public void setStoreyHeight3(Double storeyHeight3) {
        this.storeyHeight3 = storeyHeight3;
    }

    /**
     * 获取层高4   分米
     *
     * @return storey_height4 - 层高4   分米
     */
    public Double getStoreyHeight4() {
        return storeyHeight4;
    }

    /**
     * 设置层高4   分米
     *
     * @param storeyHeight4 层高4   分米
     */
    public void setStoreyHeight4(Double storeyHeight4) {
        this.storeyHeight4 = storeyHeight4;
    }

    /**
     * 获取楼层1
     *
     * @return floor1 - 楼层1
     */
    public Double getFloor1() {
        return floor1;
    }

    /**
     * 设置楼层1
     *
     * @param floor1 楼层1
     */
    public void setFloor1(Double floor1) {
        this.floor1 = floor1;
    }

    /**
     * 获取楼层2
     *
     * @return floor2 - 楼层2
     */
    public Double getFloor2() {
        return floor2;
    }

    /**
     * 设置楼层2
     *
     * @param floor2 楼层2
     */
    public void setFloor2(Double floor2) {
        this.floor2 = floor2;
    }

    /**
     * 获取楼层3
     *
     * @return floor3 - 楼层3
     */
    public Double getFloor3() {
        return floor3;
    }

    /**
     * 设置楼层3
     *
     * @param floor3 楼层3
     */
    public void setFloor3(Double floor3) {
        this.floor3 = floor3;
    }

    /**
     * 获取楼层4
     *
     * @return floor4 - 楼层4
     */
    public Double getFloor4() {
        return floor4;
    }

    /**
     * 设置楼层4
     *
     * @param floor4 楼层4
     */
    public void setFloor4(Double floor4) {
        this.floor4 = floor4;
    }

    /**
     * 获取最大楼层
     *
     * @return floor_max - 最大楼层
     */
    public Double getFloorMax() {
        return floorMax;
    }

    /**
     * 设置最大楼层
     *
     * @param floorMax 最大楼层
     */
    public void setFloorMax(Double floorMax) {
        this.floorMax = floorMax;
    }

    /**
     * 获取最大高度
     *
     * @return height_max - 最大高度
     */
    public Double getHeightMax() {
        return heightMax;
    }

    /**
     * 设置最大高度
     *
     * @param heightMax 最大高度
     */
    public void setHeightMax(Double heightMax) {
        this.heightMax = heightMax;
    }

    /**
     * 获取司机身份对比周期
     *
     * @return driver_identity_cycle - 司机身份对比周期
     */
    public Double getDriverIdentityCycle() {
        return driverIdentityCycle;
    }

    /**
     * 设置司机身份对比周期
     *
     * @param driverIdentityCycle 司机身份对比周期
     */
    public void setDriverIdentityCycle(Double driverIdentityCycle) {
        this.driverIdentityCycle = driverIdentityCycle;
    }

    /**
     * 获取监理身份对比周期
     *
     * @return supervision_identity_cycle - 监理身份对比周期
     */
    public Double getSupervisionIdentityCycle() {
        return supervisionIdentityCycle;
    }

    /**
     * 设置监理身份对比周期
     *
     * @param supervisionIdentityCycle 监理身份对比周期
     */
    public void setSupervisionIdentityCycle(Double supervisionIdentityCycle) {
        this.supervisionIdentityCycle = supervisionIdentityCycle;
    }

    /**
     * 获取限载人数
     *
     * @return limited_person - 限载人数
     */
    public Byte getLimitedPerson() {
        return limitedPerson;
    }

    /**
     * 设置限载人数
     *
     * @param limitedPerson 限载人数
     */
    public void setLimitedPerson(Byte limitedPerson) {
        this.limitedPerson = limitedPerson;
    }

    /**
     * 获取纬度值
     *
     * @return latitude - 纬度值
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度值
     *
     * @param latitude 纬度值
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取北纬或南纬
     *
     * @return latitude_type - 北纬或南纬
     */
    public Byte getLatitudeType() {
        return latitudeType;
    }

    /**
     * 设置北纬或南纬
     *
     * @param latitudeType 北纬或南纬
     */
    public void setLatitudeType(Byte latitudeType) {
        this.latitudeType = latitudeType;
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取东经或西经
     *
     * @return longitude_type - 东经或西经
     */
    public Byte getLongitudeType() {
        return longitudeType;
    }

    /**
     * 设置东经或西经
     *
     * @param longitudeType 东经或西经
     */
    public void setLongitudeType(Byte longitudeType) {
        this.longitudeType = longitudeType;
    }

    /**
     * 获取风速预警
     *
     * @return wind_early_warning - 风速预警
     */
    public Byte getWindEarlyWarning() {
        return windEarlyWarning;
    }

    /**
     * 设置风速预警
     *
     * @param windEarlyWarning 风速预警
     */
    public void setWindEarlyWarning(Byte windEarlyWarning) {
        this.windEarlyWarning = windEarlyWarning;
    }

    /**
     * 获取风速报警
     *
     * @return wind_give_alarm - 风速报警
     */
    public Byte getWindGiveAlarm() {
        return windGiveAlarm;
    }

    /**
     * 设置风速报警
     *
     * @param windGiveAlarm 风速报警
     */
    public void setWindGiveAlarm(Byte windGiveAlarm) {
        this.windGiveAlarm = windGiveAlarm;
    }

    /**
     * 获取boot loader 版本号
     *
     * @return boot_loader_version - boot loader 版本号
     */
    public String getBootLoaderVersion() {
        return bootLoaderVersion;
    }

    /**
     * 设置boot loader 版本号
     *
     * @param bootLoaderVersion boot loader 版本号
     */
    public void setBootLoaderVersion(String bootLoaderVersion) {
        this.bootLoaderVersion = bootLoaderVersion;
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