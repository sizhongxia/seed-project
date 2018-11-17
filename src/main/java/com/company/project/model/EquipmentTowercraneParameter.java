package com.company.project.model;

import javax.persistence.*;

@Table(name = "equipment_towercrane_parameter")
public class EquipmentTowercraneParameter {
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
     * 倍率取值2或4
     */
    private Byte rate;

    /**
     * 最小高度时传感器采样值
     */
    @Column(name = "min_height_sensor_value")
    private Double minHeightSensorValue;

    /**
     * 最大高度时传感器采样值
     */
    @Column(name = "max_height_sensor_value")
    private Double maxHeightSensorValue;

    /**
     * 标准尺长度
     */
    @Column(name = "standard_length")
    private Double standardLength;

    /**
     * 最小幅度
     */
    @Column(name = "min_range")
    private Double minRange;

    /**
     * 最小幅度时传感器采样值
     */
    @Column(name = "min_range_sensor_value")
    private Double minRangeSensorValue;

    /**
     * 最大幅度
     */
    @Column(name = "max_range")
    private Double maxRange;

    /**
     * 最大幅度时传感器采样值
     */
    @Column(name = "max_range_sensor_value")
    private Double maxRangeSensorValue;

    /**
     * 空钩时AD采样值
     */
    @Column(name = "empty_hook_AD_value")
    private Double emptyHookAdValue;

    /**
     * 吊载砝码时传感器采样值
     */
    @Column(name = "weight_sensor_value")
    private Double weightSensorValue;

    /**
     * 砝码重量
     */
    @Column(name = "weight_weight")
    private Double weightWeight;

    /**
     * 1电位器回转2绝对值回转
     */
    @Column(name = "rotation_type")
    private String rotationType;

    /**
     * 1东2南3西4北
     */
    @Column(name = "absolute_rotation_direction")
    private String absoluteRotationDirection;

    /**
     * 绝对值回转值
     */
    @Column(name = "absolute_rotation")
    private Double absoluteRotation;

    /**
     * 绝对值回转点确认后的回转值
     */
    @Column(name = "absolute_rotationpoint_confirm")
    private Double absoluteRotationpointConfirm;

    /**
     * 电位器回转左限位传感器值
     */
    @Column(name = "potentiometer_rotation_leftlimit_sensor_value")
    private Double potentiometerRotationLeftlimitSensorValue;

    /**
     * 电位器回转右限位传感器值
     */
    @Column(name = "potentiometer_rotation_rightlimit_sensor_value")
    private Double potentiometerRotationRightlimitSensorValue;

    /**
     * 电位器回转左右限位角度和
     */
    @Column(name = "potentiometer_rotation_limit_anglesum")
    private Double potentiometerRotationLimitAnglesum;

    /**
     * 4倍率时最大起重量
     */
    @Column(name = "four_rate_max_weight")
    private Double fourRateMaxWeight;

    /**
     * 4倍率时最大起重量幅度
     */
    @Column(name = "four_rate_max_weight_range")
    private Double fourRateMaxWeightRange;

    /**
     * 4倍率时最大幅度
     */
    @Column(name = "four_rate_max_range")
    private Double fourRateMaxRange;

    /**
     * 4倍率时最大幅度的起重量
     */
    @Column(name = "four_rate_max_range_weight")
    private Double fourRateMaxRangeWeight;

    /**
     * 2倍率时最大起重量
     */
    @Column(name = "two_rate_max_weight")
    private Double twoRateMaxWeight;

    /**
     * 2倍率时最大起重量幅度
     */
    @Column(name = "two_rate_max_weight_range")
    private Double twoRateMaxWeightRange;

    /**
     * 2倍率时最大幅度
     */
    @Column(name = "two_rate_max_range")
    private Double twoRateMaxRange;

    /**
     * 2倍率时最大幅度的起重量
     */
    @Column(name = "two_rate_max_range_weight")
    private Double twoRateMaxRangeWeight;

    /**
     * zigbee 本机编号
     */
    @Column(name = "zigbee_local_No")
    private Byte zigbeeLocalNo;

    /**
     * zigbee本机频道号
     */
    @Column(name = "zigbee_channel_No")
    private Byte zigbeeChannelNo;

    /**
     * zigbee本机组号
     */
    @Column(name = "zigbee_group_No")
    private Byte zigbeeGroupNo;

    /**
     * 防碰撞信息本机X
     */
    @Column(name = "anti_collision_local_X")
    private Double antiCollisionLocalX;

    /**
     * 防碰撞信息本机Y
     */
    @Column(name = "anti_collision_local_Y")
    private Double antiCollisionLocalY;

    /**
     * 起重臂长
     */
    @Column(name = "lifting_arm_length")
    private Double liftingArmLength;

    /**
     * 平衡臂长
     */
    @Column(name = "balance_arm_length")
    private Double balanceArmLength;

    /**
     * 塔身高
     */
    @Column(name = "tower_body_height")
    private Double towerBodyHeight;

    /**
     * 塔帽高
     */
    @Column(name = "tower_hat_height")
    private Double towerHatHeight;

    /**
     * 幅度减速值  单位 米
     */
    @Column(name = "range_decelerate")
    private Double rangeDecelerate;

    /**
     * 幅度限速值   单位  米
     */
    @Column(name = "range_speedlimit")
    private Double rangeSpeedlimit;

    /**
     * 高度减速值
     */
    @Column(name = "height_decelerate")
    private Double heightDecelerate;

    /**
     * 高度限速值
     */
    @Column(name = "height_speedlimit")
    private Double heightSpeedlimit;

    /**
     * 回转减速值
     */
    @Column(name = "rotation_decelerate")
    private Double rotationDecelerate;

    /**
     * 回转限速值
     */
    @Column(name = "rotation_speedlimit")
    private Double rotationSpeedlimit;

    /**
     * 区域保护减速值
     */
    @Column(name = "locality_protection_decelerate")
    private Double localityProtectionDecelerate;

    /**
     * 区域保护限速值
     */
    @Column(name = "locality_protection_speedlimit")
    private Double localityProtectionSpeedlimit;

    /**
     * 防碰撞减速值
     */
    @Column(name = "anti_collision_decelerate")
    private Double antiCollisionDecelerate;

    /**
     * 防碰撞限速值
     */
    @Column(name = "anti_collision_speedlimit")
    private Double antiCollisionSpeedlimit;

    /**
     * 换速力矩
     */
    @Column(name = "speed_exchange_torque")
    private Double speedExchangeTorque;

    /**
     * 切断力矩
     */
    @Column(name = "cutting_torque")
    private Double cuttingTorque;

    /**
     * 换速重量
     */
    @Column(name = "speed_exchange_weight")
    private Double speedExchangeWeight;

    /**
     * 切断重量
     */
    @Column(name = "cutting_weight")
    private Double cuttingWeight;

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
     * 获取倍率取值2或4
     *
     * @return rate - 倍率取值2或4
     */
    public Byte getRate() {
        return rate;
    }

    /**
     * 设置倍率取值2或4
     *
     * @param rate 倍率取值2或4
     */
    public void setRate(Byte rate) {
        this.rate = rate;
    }

    /**
     * 获取最小高度时传感器采样值
     *
     * @return min_height_sensor_value - 最小高度时传感器采样值
     */
    public Double getMinHeightSensorValue() {
        return minHeightSensorValue;
    }

    /**
     * 设置最小高度时传感器采样值
     *
     * @param minHeightSensorValue 最小高度时传感器采样值
     */
    public void setMinHeightSensorValue(Double minHeightSensorValue) {
        this.minHeightSensorValue = minHeightSensorValue;
    }

    /**
     * 获取最大高度时传感器采样值
     *
     * @return max_height_sensor_value - 最大高度时传感器采样值
     */
    public Double getMaxHeightSensorValue() {
        return maxHeightSensorValue;
    }

    /**
     * 设置最大高度时传感器采样值
     *
     * @param maxHeightSensorValue 最大高度时传感器采样值
     */
    public void setMaxHeightSensorValue(Double maxHeightSensorValue) {
        this.maxHeightSensorValue = maxHeightSensorValue;
    }

    /**
     * 获取标准尺长度
     *
     * @return standard_length - 标准尺长度
     */
    public Double getStandardLength() {
        return standardLength;
    }

    /**
     * 设置标准尺长度
     *
     * @param standardLength 标准尺长度
     */
    public void setStandardLength(Double standardLength) {
        this.standardLength = standardLength;
    }

    /**
     * 获取最小幅度
     *
     * @return min_range - 最小幅度
     */
    public Double getMinRange() {
        return minRange;
    }

    /**
     * 设置最小幅度
     *
     * @param minRange 最小幅度
     */
    public void setMinRange(Double minRange) {
        this.minRange = minRange;
    }

    /**
     * 获取最小幅度时传感器采样值
     *
     * @return min_range_sensor_value - 最小幅度时传感器采样值
     */
    public Double getMinRangeSensorValue() {
        return minRangeSensorValue;
    }

    /**
     * 设置最小幅度时传感器采样值
     *
     * @param minRangeSensorValue 最小幅度时传感器采样值
     */
    public void setMinRangeSensorValue(Double minRangeSensorValue) {
        this.minRangeSensorValue = minRangeSensorValue;
    }

    /**
     * 获取最大幅度
     *
     * @return max_range - 最大幅度
     */
    public Double getMaxRange() {
        return maxRange;
    }

    /**
     * 设置最大幅度
     *
     * @param maxRange 最大幅度
     */
    public void setMaxRange(Double maxRange) {
        this.maxRange = maxRange;
    }

    /**
     * 获取最大幅度时传感器采样值
     *
     * @return max_range_sensor_value - 最大幅度时传感器采样值
     */
    public Double getMaxRangeSensorValue() {
        return maxRangeSensorValue;
    }

    /**
     * 设置最大幅度时传感器采样值
     *
     * @param maxRangeSensorValue 最大幅度时传感器采样值
     */
    public void setMaxRangeSensorValue(Double maxRangeSensorValue) {
        this.maxRangeSensorValue = maxRangeSensorValue;
    }

    /**
     * 获取空钩时AD采样值
     *
     * @return empty_hook_AD_value - 空钩时AD采样值
     */
    public Double getEmptyHookAdValue() {
        return emptyHookAdValue;
    }

    /**
     * 设置空钩时AD采样值
     *
     * @param emptyHookAdValue 空钩时AD采样值
     */
    public void setEmptyHookAdValue(Double emptyHookAdValue) {
        this.emptyHookAdValue = emptyHookAdValue;
    }

    /**
     * 获取吊载砝码时传感器采样值
     *
     * @return weight_sensor_value - 吊载砝码时传感器采样值
     */
    public Double getWeightSensorValue() {
        return weightSensorValue;
    }

    /**
     * 设置吊载砝码时传感器采样值
     *
     * @param weightSensorValue 吊载砝码时传感器采样值
     */
    public void setWeightSensorValue(Double weightSensorValue) {
        this.weightSensorValue = weightSensorValue;
    }

    /**
     * 获取砝码重量
     *
     * @return weight_weight - 砝码重量
     */
    public Double getWeightWeight() {
        return weightWeight;
    }

    /**
     * 设置砝码重量
     *
     * @param weightWeight 砝码重量
     */
    public void setWeightWeight(Double weightWeight) {
        this.weightWeight = weightWeight;
    }

    /**
     * 获取1电位器回转2绝对值回转
     *
     * @return rotation_type - 1电位器回转2绝对值回转
     */
    public String getRotationType() {
        return rotationType;
    }

    /**
     * 设置1电位器回转2绝对值回转
     *
     * @param rotationType 1电位器回转2绝对值回转
     */
    public void setRotationType(String rotationType) {
        this.rotationType = rotationType;
    }

    /**
     * 获取1东2南3西4北
     *
     * @return absolute_rotation_direction - 1东2南3西4北
     */
    public String getAbsoluteRotationDirection() {
        return absoluteRotationDirection;
    }

    /**
     * 设置1东2南3西4北
     *
     * @param absoluteRotationDirection 1东2南3西4北
     */
    public void setAbsoluteRotationDirection(String absoluteRotationDirection) {
        this.absoluteRotationDirection = absoluteRotationDirection;
    }

    /**
     * 获取绝对值回转值
     *
     * @return absolute_rotation - 绝对值回转值
     */
    public Double getAbsoluteRotation() {
        return absoluteRotation;
    }

    /**
     * 设置绝对值回转值
     *
     * @param absoluteRotation 绝对值回转值
     */
    public void setAbsoluteRotation(Double absoluteRotation) {
        this.absoluteRotation = absoluteRotation;
    }

    /**
     * 获取绝对值回转点确认后的回转值
     *
     * @return absolute_rotationpoint_confirm - 绝对值回转点确认后的回转值
     */
    public Double getAbsoluteRotationpointConfirm() {
        return absoluteRotationpointConfirm;
    }

    /**
     * 设置绝对值回转点确认后的回转值
     *
     * @param absoluteRotationpointConfirm 绝对值回转点确认后的回转值
     */
    public void setAbsoluteRotationpointConfirm(Double absoluteRotationpointConfirm) {
        this.absoluteRotationpointConfirm = absoluteRotationpointConfirm;
    }

    /**
     * 获取电位器回转左限位传感器值
     *
     * @return potentiometer_rotation_leftlimit_sensor_value - 电位器回转左限位传感器值
     */
    public Double getPotentiometerRotationLeftlimitSensorValue() {
        return potentiometerRotationLeftlimitSensorValue;
    }

    /**
     * 设置电位器回转左限位传感器值
     *
     * @param potentiometerRotationLeftlimitSensorValue 电位器回转左限位传感器值
     */
    public void setPotentiometerRotationLeftlimitSensorValue(Double potentiometerRotationLeftlimitSensorValue) {
        this.potentiometerRotationLeftlimitSensorValue = potentiometerRotationLeftlimitSensorValue;
    }

    /**
     * 获取电位器回转右限位传感器值
     *
     * @return potentiometer_rotation_rightlimit_sensor_value - 电位器回转右限位传感器值
     */
    public Double getPotentiometerRotationRightlimitSensorValue() {
        return potentiometerRotationRightlimitSensorValue;
    }

    /**
     * 设置电位器回转右限位传感器值
     *
     * @param potentiometerRotationRightlimitSensorValue 电位器回转右限位传感器值
     */
    public void setPotentiometerRotationRightlimitSensorValue(Double potentiometerRotationRightlimitSensorValue) {
        this.potentiometerRotationRightlimitSensorValue = potentiometerRotationRightlimitSensorValue;
    }

    /**
     * 获取电位器回转左右限位角度和
     *
     * @return potentiometer_rotation_limit_anglesum - 电位器回转左右限位角度和
     */
    public Double getPotentiometerRotationLimitAnglesum() {
        return potentiometerRotationLimitAnglesum;
    }

    /**
     * 设置电位器回转左右限位角度和
     *
     * @param potentiometerRotationLimitAnglesum 电位器回转左右限位角度和
     */
    public void setPotentiometerRotationLimitAnglesum(Double potentiometerRotationLimitAnglesum) {
        this.potentiometerRotationLimitAnglesum = potentiometerRotationLimitAnglesum;
    }

    /**
     * 获取4倍率时最大起重量
     *
     * @return four_rate_max_weight - 4倍率时最大起重量
     */
    public Double getFourRateMaxWeight() {
        return fourRateMaxWeight;
    }

    /**
     * 设置4倍率时最大起重量
     *
     * @param fourRateMaxWeight 4倍率时最大起重量
     */
    public void setFourRateMaxWeight(Double fourRateMaxWeight) {
        this.fourRateMaxWeight = fourRateMaxWeight;
    }

    /**
     * 获取4倍率时最大起重量幅度
     *
     * @return four_rate_max_weight_range - 4倍率时最大起重量幅度
     */
    public Double getFourRateMaxWeightRange() {
        return fourRateMaxWeightRange;
    }

    /**
     * 设置4倍率时最大起重量幅度
     *
     * @param fourRateMaxWeightRange 4倍率时最大起重量幅度
     */
    public void setFourRateMaxWeightRange(Double fourRateMaxWeightRange) {
        this.fourRateMaxWeightRange = fourRateMaxWeightRange;
    }

    /**
     * 获取4倍率时最大幅度
     *
     * @return four_rate_max_range - 4倍率时最大幅度
     */
    public Double getFourRateMaxRange() {
        return fourRateMaxRange;
    }

    /**
     * 设置4倍率时最大幅度
     *
     * @param fourRateMaxRange 4倍率时最大幅度
     */
    public void setFourRateMaxRange(Double fourRateMaxRange) {
        this.fourRateMaxRange = fourRateMaxRange;
    }

    /**
     * 获取4倍率时最大幅度的起重量
     *
     * @return four_rate_max_range_weight - 4倍率时最大幅度的起重量
     */
    public Double getFourRateMaxRangeWeight() {
        return fourRateMaxRangeWeight;
    }

    /**
     * 设置4倍率时最大幅度的起重量
     *
     * @param fourRateMaxRangeWeight 4倍率时最大幅度的起重量
     */
    public void setFourRateMaxRangeWeight(Double fourRateMaxRangeWeight) {
        this.fourRateMaxRangeWeight = fourRateMaxRangeWeight;
    }

    /**
     * 获取2倍率时最大起重量
     *
     * @return two_rate_max_weight - 2倍率时最大起重量
     */
    public Double getTwoRateMaxWeight() {
        return twoRateMaxWeight;
    }

    /**
     * 设置2倍率时最大起重量
     *
     * @param twoRateMaxWeight 2倍率时最大起重量
     */
    public void setTwoRateMaxWeight(Double twoRateMaxWeight) {
        this.twoRateMaxWeight = twoRateMaxWeight;
    }

    /**
     * 获取2倍率时最大起重量幅度
     *
     * @return two_rate_max_weight_range - 2倍率时最大起重量幅度
     */
    public Double getTwoRateMaxWeightRange() {
        return twoRateMaxWeightRange;
    }

    /**
     * 设置2倍率时最大起重量幅度
     *
     * @param twoRateMaxWeightRange 2倍率时最大起重量幅度
     */
    public void setTwoRateMaxWeightRange(Double twoRateMaxWeightRange) {
        this.twoRateMaxWeightRange = twoRateMaxWeightRange;
    }

    /**
     * 获取2倍率时最大幅度
     *
     * @return two_rate_max_range - 2倍率时最大幅度
     */
    public Double getTwoRateMaxRange() {
        return twoRateMaxRange;
    }

    /**
     * 设置2倍率时最大幅度
     *
     * @param twoRateMaxRange 2倍率时最大幅度
     */
    public void setTwoRateMaxRange(Double twoRateMaxRange) {
        this.twoRateMaxRange = twoRateMaxRange;
    }

    /**
     * 获取2倍率时最大幅度的起重量
     *
     * @return two_rate_max_range_weight - 2倍率时最大幅度的起重量
     */
    public Double getTwoRateMaxRangeWeight() {
        return twoRateMaxRangeWeight;
    }

    /**
     * 设置2倍率时最大幅度的起重量
     *
     * @param twoRateMaxRangeWeight 2倍率时最大幅度的起重量
     */
    public void setTwoRateMaxRangeWeight(Double twoRateMaxRangeWeight) {
        this.twoRateMaxRangeWeight = twoRateMaxRangeWeight;
    }

    /**
     * 获取zigbee 本机编号
     *
     * @return zigbee_local_No - zigbee 本机编号
     */
    public Byte getZigbeeLocalNo() {
        return zigbeeLocalNo;
    }

    /**
     * 设置zigbee 本机编号
     *
     * @param zigbeeLocalNo zigbee 本机编号
     */
    public void setZigbeeLocalNo(Byte zigbeeLocalNo) {
        this.zigbeeLocalNo = zigbeeLocalNo;
    }

    /**
     * 获取zigbee本机频道号
     *
     * @return zigbee_channel_No - zigbee本机频道号
     */
    public Byte getZigbeeChannelNo() {
        return zigbeeChannelNo;
    }

    /**
     * 设置zigbee本机频道号
     *
     * @param zigbeeChannelNo zigbee本机频道号
     */
    public void setZigbeeChannelNo(Byte zigbeeChannelNo) {
        this.zigbeeChannelNo = zigbeeChannelNo;
    }

    /**
     * 获取zigbee本机组号
     *
     * @return zigbee_group_No - zigbee本机组号
     */
    public Byte getZigbeeGroupNo() {
        return zigbeeGroupNo;
    }

    /**
     * 设置zigbee本机组号
     *
     * @param zigbeeGroupNo zigbee本机组号
     */
    public void setZigbeeGroupNo(Byte zigbeeGroupNo) {
        this.zigbeeGroupNo = zigbeeGroupNo;
    }

    /**
     * 获取防碰撞信息本机X
     *
     * @return anti_collision_local_X - 防碰撞信息本机X
     */
    public Double getAntiCollisionLocalX() {
        return antiCollisionLocalX;
    }

    /**
     * 设置防碰撞信息本机X
     *
     * @param antiCollisionLocalX 防碰撞信息本机X
     */
    public void setAntiCollisionLocalX(Double antiCollisionLocalX) {
        this.antiCollisionLocalX = antiCollisionLocalX;
    }

    /**
     * 获取防碰撞信息本机Y
     *
     * @return anti_collision_local_Y - 防碰撞信息本机Y
     */
    public Double getAntiCollisionLocalY() {
        return antiCollisionLocalY;
    }

    /**
     * 设置防碰撞信息本机Y
     *
     * @param antiCollisionLocalY 防碰撞信息本机Y
     */
    public void setAntiCollisionLocalY(Double antiCollisionLocalY) {
        this.antiCollisionLocalY = antiCollisionLocalY;
    }

    /**
     * 获取起重臂长
     *
     * @return lifting_arm_length - 起重臂长
     */
    public Double getLiftingArmLength() {
        return liftingArmLength;
    }

    /**
     * 设置起重臂长
     *
     * @param liftingArmLength 起重臂长
     */
    public void setLiftingArmLength(Double liftingArmLength) {
        this.liftingArmLength = liftingArmLength;
    }

    /**
     * 获取平衡臂长
     *
     * @return balance_arm_length - 平衡臂长
     */
    public Double getBalanceArmLength() {
        return balanceArmLength;
    }

    /**
     * 设置平衡臂长
     *
     * @param balanceArmLength 平衡臂长
     */
    public void setBalanceArmLength(Double balanceArmLength) {
        this.balanceArmLength = balanceArmLength;
    }

    /**
     * 获取塔身高
     *
     * @return tower_body_height - 塔身高
     */
    public Double getTowerBodyHeight() {
        return towerBodyHeight;
    }

    /**
     * 设置塔身高
     *
     * @param towerBodyHeight 塔身高
     */
    public void setTowerBodyHeight(Double towerBodyHeight) {
        this.towerBodyHeight = towerBodyHeight;
    }

    /**
     * 获取塔帽高
     *
     * @return tower_hat_height - 塔帽高
     */
    public Double getTowerHatHeight() {
        return towerHatHeight;
    }

    /**
     * 设置塔帽高
     *
     * @param towerHatHeight 塔帽高
     */
    public void setTowerHatHeight(Double towerHatHeight) {
        this.towerHatHeight = towerHatHeight;
    }

    /**
     * 获取幅度减速值  单位 米
     *
     * @return range_decelerate - 幅度减速值  单位 米
     */
    public Double getRangeDecelerate() {
        return rangeDecelerate;
    }

    /**
     * 设置幅度减速值  单位 米
     *
     * @param rangeDecelerate 幅度减速值  单位 米
     */
    public void setRangeDecelerate(Double rangeDecelerate) {
        this.rangeDecelerate = rangeDecelerate;
    }

    /**
     * 获取幅度限速值   单位  米
     *
     * @return range_speedlimit - 幅度限速值   单位  米
     */
    public Double getRangeSpeedlimit() {
        return rangeSpeedlimit;
    }

    /**
     * 设置幅度限速值   单位  米
     *
     * @param rangeSpeedlimit 幅度限速值   单位  米
     */
    public void setRangeSpeedlimit(Double rangeSpeedlimit) {
        this.rangeSpeedlimit = rangeSpeedlimit;
    }

    /**
     * 获取高度减速值
     *
     * @return height_decelerate - 高度减速值
     */
    public Double getHeightDecelerate() {
        return heightDecelerate;
    }

    /**
     * 设置高度减速值
     *
     * @param heightDecelerate 高度减速值
     */
    public void setHeightDecelerate(Double heightDecelerate) {
        this.heightDecelerate = heightDecelerate;
    }

    /**
     * 获取高度限速值
     *
     * @return height_speedlimit - 高度限速值
     */
    public Double getHeightSpeedlimit() {
        return heightSpeedlimit;
    }

    /**
     * 设置高度限速值
     *
     * @param heightSpeedlimit 高度限速值
     */
    public void setHeightSpeedlimit(Double heightSpeedlimit) {
        this.heightSpeedlimit = heightSpeedlimit;
    }

    /**
     * 获取回转减速值
     *
     * @return rotation_decelerate - 回转减速值
     */
    public Double getRotationDecelerate() {
        return rotationDecelerate;
    }

    /**
     * 设置回转减速值
     *
     * @param rotationDecelerate 回转减速值
     */
    public void setRotationDecelerate(Double rotationDecelerate) {
        this.rotationDecelerate = rotationDecelerate;
    }

    /**
     * 获取回转限速值
     *
     * @return rotation_speedlimit - 回转限速值
     */
    public Double getRotationSpeedlimit() {
        return rotationSpeedlimit;
    }

    /**
     * 设置回转限速值
     *
     * @param rotationSpeedlimit 回转限速值
     */
    public void setRotationSpeedlimit(Double rotationSpeedlimit) {
        this.rotationSpeedlimit = rotationSpeedlimit;
    }

    /**
     * 获取区域保护减速值
     *
     * @return locality_protection_decelerate - 区域保护减速值
     */
    public Double getLocalityProtectionDecelerate() {
        return localityProtectionDecelerate;
    }

    /**
     * 设置区域保护减速值
     *
     * @param localityProtectionDecelerate 区域保护减速值
     */
    public void setLocalityProtectionDecelerate(Double localityProtectionDecelerate) {
        this.localityProtectionDecelerate = localityProtectionDecelerate;
    }

    /**
     * 获取区域保护限速值
     *
     * @return locality_protection_speedlimit - 区域保护限速值
     */
    public Double getLocalityProtectionSpeedlimit() {
        return localityProtectionSpeedlimit;
    }

    /**
     * 设置区域保护限速值
     *
     * @param localityProtectionSpeedlimit 区域保护限速值
     */
    public void setLocalityProtectionSpeedlimit(Double localityProtectionSpeedlimit) {
        this.localityProtectionSpeedlimit = localityProtectionSpeedlimit;
    }

    /**
     * 获取防碰撞减速值
     *
     * @return anti_collision_decelerate - 防碰撞减速值
     */
    public Double getAntiCollisionDecelerate() {
        return antiCollisionDecelerate;
    }

    /**
     * 设置防碰撞减速值
     *
     * @param antiCollisionDecelerate 防碰撞减速值
     */
    public void setAntiCollisionDecelerate(Double antiCollisionDecelerate) {
        this.antiCollisionDecelerate = antiCollisionDecelerate;
    }

    /**
     * 获取防碰撞限速值
     *
     * @return anti_collision_speedlimit - 防碰撞限速值
     */
    public Double getAntiCollisionSpeedlimit() {
        return antiCollisionSpeedlimit;
    }

    /**
     * 设置防碰撞限速值
     *
     * @param antiCollisionSpeedlimit 防碰撞限速值
     */
    public void setAntiCollisionSpeedlimit(Double antiCollisionSpeedlimit) {
        this.antiCollisionSpeedlimit = antiCollisionSpeedlimit;
    }

    /**
     * 获取换速力矩
     *
     * @return speed_exchange_torque - 换速力矩
     */
    public Double getSpeedExchangeTorque() {
        return speedExchangeTorque;
    }

    /**
     * 设置换速力矩
     *
     * @param speedExchangeTorque 换速力矩
     */
    public void setSpeedExchangeTorque(Double speedExchangeTorque) {
        this.speedExchangeTorque = speedExchangeTorque;
    }

    /**
     * 获取切断力矩
     *
     * @return cutting_torque - 切断力矩
     */
    public Double getCuttingTorque() {
        return cuttingTorque;
    }

    /**
     * 设置切断力矩
     *
     * @param cuttingTorque 切断力矩
     */
    public void setCuttingTorque(Double cuttingTorque) {
        this.cuttingTorque = cuttingTorque;
    }

    /**
     * 获取换速重量
     *
     * @return speed_exchange_weight - 换速重量
     */
    public Double getSpeedExchangeWeight() {
        return speedExchangeWeight;
    }

    /**
     * 设置换速重量
     *
     * @param speedExchangeWeight 换速重量
     */
    public void setSpeedExchangeWeight(Double speedExchangeWeight) {
        this.speedExchangeWeight = speedExchangeWeight;
    }

    /**
     * 获取切断重量
     *
     * @return cutting_weight - 切断重量
     */
    public Double getCuttingWeight() {
        return cuttingWeight;
    }

    /**
     * 设置切断重量
     *
     * @param cuttingWeight 切断重量
     */
    public void setCuttingWeight(Double cuttingWeight) {
        this.cuttingWeight = cuttingWeight;
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