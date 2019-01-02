package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "smart_culture_farm")
public class SmartCultureFarm {
    /**
     * 主键ID
     */
    @Id
    private Long id;

    /**
     * 农场ID
     */
    @Column(name = "farm_id")
    private String farmId;

    /**
     * 农场名称
     */
    @Column(name = "farm_name")
    private String farmName;

    /**
     * 农场编号
     */
    @Column(name = "farm_code")
    private String farmCode;

    /**
     * 二维码地址（生成规则：https://zhyz.yeetong.cn/farm/:code）
     */
    @Column(name = "qr_code_url")
    private String qrCodeUrl;

    /**
     * 所属组织
     */
    @Column(name = "organize_id")
    private String organizeId;

    /**
     * 农场所属用户
     */
    @Column(name = "owner_user_id")
    private String ownerUserId;

    /**
     * 所在省编码
     */
    @Column(name = "province_code")
    private String provinceCode;

    /**
     * 所在省名称
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 所在市/区编码
     */
    @Column(name = "city_code")
    private String cityCode;

    /**
     * 所在市/区名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 所在乡/镇编码
     */
    @Column(name = "county_code")
    private String countyCode;

    /**
     * 所在乡/镇名称
     */
    @Column(name = "county_name")
    private String countyName;

    /**
     * 天气城市编码
     */
    @Column(name = "weather_city_code")
    private String weatherCityCode;

    /**
     * 天气城市名称
     */
    @Column(name = "weather_city_name")
    private String weatherCityName;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 农场地址
     */
    private String address;

    /**
     * 农场面积
     */
    private String acreage;

    private String logo;

    /**
     * 网站链接
     */
    private String website;

    /**
     * 备注说明
     */
    private String remark;

    /**
     * 版本
     */
    private Long version;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 修改时间
     */
    @Column(name = "update_at")
    private Date updateAt;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取农场ID
     *
     * @return farm_id - 农场ID
     */
    public String getFarmId() {
        return farmId;
    }

    /**
     * 设置农场ID
     *
     * @param farmId 农场ID
     */
    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    /**
     * 获取农场名称
     *
     * @return farm_name - 农场名称
     */
    public String getFarmName() {
        return farmName;
    }

    /**
     * 设置农场名称
     *
     * @param farmName 农场名称
     */
    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    /**
     * 获取农场编号
     *
     * @return farm_code - 农场编号
     */
    public String getFarmCode() {
        return farmCode;
    }

    /**
     * 设置农场编号
     *
     * @param farmCode 农场编号
     */
    public void setFarmCode(String farmCode) {
        this.farmCode = farmCode;
    }

    /**
     * 获取二维码地址（生成规则：https://zhyz.yeetong.cn/farm/:code）
     *
     * @return qr_code_url - 二维码地址（生成规则：https://zhyz.yeetong.cn/farm/:code）
     */
    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    /**
     * 设置二维码地址（生成规则：https://zhyz.yeetong.cn/farm/:code）
     *
     * @param qrCodeUrl 二维码地址（生成规则：https://zhyz.yeetong.cn/farm/:code）
     */
    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    /**
     * 获取所属组织
     *
     * @return organize_id - 所属组织
     */
    public String getOrganizeId() {
        return organizeId;
    }

    /**
     * 设置所属组织
     *
     * @param organizeId 所属组织
     */
    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    /**
     * 获取农场所属用户
     *
     * @return owner_user_id - 农场所属用户
     */
    public String getOwnerUserId() {
        return ownerUserId;
    }

    /**
     * 设置农场所属用户
     *
     * @param ownerUserId 农场所属用户
     */
    public void setOwnerUserId(String ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    /**
     * 获取所在省编码
     *
     * @return province_code - 所在省编码
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * 设置所在省编码
     *
     * @param provinceCode 所在省编码
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * 获取所在省名称
     *
     * @return province_name - 所在省名称
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置所在省名称
     *
     * @param provinceName 所在省名称
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * 获取所在市/区编码
     *
     * @return city_code - 所在市/区编码
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 设置所在市/区编码
     *
     * @param cityCode 所在市/区编码
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 获取所在市/区名称
     *
     * @return city_name - 所在市/区名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置所在市/区名称
     *
     * @param cityName 所在市/区名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 获取所在乡/镇编码
     *
     * @return county_code - 所在乡/镇编码
     */
    public String getCountyCode() {
        return countyCode;
    }

    /**
     * 设置所在乡/镇编码
     *
     * @param countyCode 所在乡/镇编码
     */
    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    /**
     * 获取所在乡/镇名称
     *
     * @return county_name - 所在乡/镇名称
     */
    public String getCountyName() {
        return countyName;
    }

    /**
     * 设置所在乡/镇名称
     *
     * @param countyName 所在乡/镇名称
     */
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    /**
     * 获取天气城市编码
     *
     * @return weather_city_code - 天气城市编码
     */
    public String getWeatherCityCode() {
        return weatherCityCode;
    }

    /**
     * 设置天气城市编码
     *
     * @param weatherCityCode 天气城市编码
     */
    public void setWeatherCityCode(String weatherCityCode) {
        this.weatherCityCode = weatherCityCode;
    }

    /**
     * 获取天气城市名称
     *
     * @return weather_city_name - 天气城市名称
     */
    public String getWeatherCityName() {
        return weatherCityName;
    }

    /**
     * 设置天气城市名称
     *
     * @param weatherCityName 天气城市名称
     */
    public void setWeatherCityName(String weatherCityName) {
        this.weatherCityName = weatherCityName;
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取纬度
     *
     * @return latitude - 纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取农场地址
     *
     * @return address - 农场地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置农场地址
     *
     * @param address 农场地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取农场面积
     *
     * @return acreage - 农场面积
     */
    public String getAcreage() {
        return acreage;
    }

    /**
     * 设置农场面积
     *
     * @param acreage 农场面积
     */
    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

    /**
     * @return logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * @param logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * 获取网站链接
     *
     * @return website - 网站链接
     */
    public String getWebsite() {
        return website;
    }

    /**
     * 设置网站链接
     *
     * @param website 网站链接
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * 获取备注说明
     *
     * @return remark - 备注说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注说明
     *
     * @param remark 备注说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取版本
     *
     * @return version - 版本
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 设置版本
     *
     * @param version 版本
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 获取创建时间
     *
     * @return create_at - 创建时间
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * 设置创建时间
     *
     * @param createAt 创建时间
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取修改时间
     *
     * @return update_at - 修改时间
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * 设置修改时间
     *
     * @param updateAt 修改时间
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}