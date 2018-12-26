package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "smart_culture_weather_history")
public class SmartCultureWeatherHistory {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 城市编码
     */
    @Column(name = "basic_cid")
    private String basicCid;

    /**
     * 更新当地时间
     */
    @Column(name = "update_loc")
    private String updateLoc;

    /**
     * UTC时间
     */
    @Column(name = "update_utc")
    private String updateUtc;

    /**
     * 云量
     */
    @Column(name = "now_cloud")
    private String nowCloud;

    /**
     * 实况天气状况代码
     */
    @Column(name = "now_cond_code")
    private String nowCondCode;

    /**
     * 实况天气状况描述
     */
    @Column(name = "now_cond_txt")
    private String nowCondTxt;

    /**
     * 体感温度，默认单位：摄氏度
     */
    @Column(name = "now_fl")
    private String nowFl;

    /**
     * 温度，默认单位：摄氏度
     */
    @Column(name = "now_tmp")
    private String nowTmp;

    /**
     * 风向360角度
     */
    @Column(name = "now_wind_deg")
    private String nowWindDeg;

    /**
     * 风向
     */
    @Column(name = "now_wind_dir")
    private String nowWindDir;

    /**
     * 风速，公里/小时
     */
    @Column(name = "now_wind_spd")
    private String nowWindSpd;

    /**
     * 风力
     */
    @Column(name = "now_wind_sc")
    private String nowWindSc;

    /**
     * 相对湿度
     */
    @Column(name = "now_hum")
    private String nowHum;

    /**
     * 降水量
     */
    @Column(name = "now_pcpn")
    private String nowPcpn;

    /**
     * 大气压强
     */
    @Column(name = "now_pres")
    private String nowPres;

    /**
     * 能见度，默认单位：公里
     */
    @Column(name = "now_vis")
    private String nowVis;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取城市编码
     *
     * @return basic_cid - 城市编码
     */
    public String getBasicCid() {
        return basicCid;
    }

    /**
     * 设置城市编码
     *
     * @param basicCid 城市编码
     */
    public void setBasicCid(String basicCid) {
        this.basicCid = basicCid;
    }

    /**
     * 获取更新当地时间
     *
     * @return update_loc - 更新当地时间
     */
    public String getUpdateLoc() {
        return updateLoc;
    }

    /**
     * 设置更新当地时间
     *
     * @param updateLoc 更新当地时间
     */
    public void setUpdateLoc(String updateLoc) {
        this.updateLoc = updateLoc;
    }

    /**
     * 获取UTC时间
     *
     * @return update_utc - UTC时间
     */
    public String getUpdateUtc() {
        return updateUtc;
    }

    /**
     * 设置UTC时间
     *
     * @param updateUtc UTC时间
     */
    public void setUpdateUtc(String updateUtc) {
        this.updateUtc = updateUtc;
    }

    /**
     * 获取云量
     *
     * @return now_cloud - 云量
     */
    public String getNowCloud() {
        return nowCloud;
    }

    /**
     * 设置云量
     *
     * @param nowCloud 云量
     */
    public void setNowCloud(String nowCloud) {
        this.nowCloud = nowCloud;
    }

    /**
     * 获取实况天气状况代码
     *
     * @return now_cond_code - 实况天气状况代码
     */
    public String getNowCondCode() {
        return nowCondCode;
    }

    /**
     * 设置实况天气状况代码
     *
     * @param nowCondCode 实况天气状况代码
     */
    public void setNowCondCode(String nowCondCode) {
        this.nowCondCode = nowCondCode;
    }

    /**
     * 获取实况天气状况描述
     *
     * @return now_cond_txt - 实况天气状况描述
     */
    public String getNowCondTxt() {
        return nowCondTxt;
    }

    /**
     * 设置实况天气状况描述
     *
     * @param nowCondTxt 实况天气状况描述
     */
    public void setNowCondTxt(String nowCondTxt) {
        this.nowCondTxt = nowCondTxt;
    }

    /**
     * 获取体感温度，默认单位：摄氏度
     *
     * @return now_fl - 体感温度，默认单位：摄氏度
     */
    public String getNowFl() {
        return nowFl;
    }

    /**
     * 设置体感温度，默认单位：摄氏度
     *
     * @param nowFl 体感温度，默认单位：摄氏度
     */
    public void setNowFl(String nowFl) {
        this.nowFl = nowFl;
    }

    /**
     * 获取温度，默认单位：摄氏度
     *
     * @return now_tmp - 温度，默认单位：摄氏度
     */
    public String getNowTmp() {
        return nowTmp;
    }

    /**
     * 设置温度，默认单位：摄氏度
     *
     * @param nowTmp 温度，默认单位：摄氏度
     */
    public void setNowTmp(String nowTmp) {
        this.nowTmp = nowTmp;
    }

    /**
     * 获取风向360角度
     *
     * @return now_wind_deg - 风向360角度
     */
    public String getNowWindDeg() {
        return nowWindDeg;
    }

    /**
     * 设置风向360角度
     *
     * @param nowWindDeg 风向360角度
     */
    public void setNowWindDeg(String nowWindDeg) {
        this.nowWindDeg = nowWindDeg;
    }

    /**
     * 获取风向
     *
     * @return now_wind_dir - 风向
     */
    public String getNowWindDir() {
        return nowWindDir;
    }

    /**
     * 设置风向
     *
     * @param nowWindDir 风向
     */
    public void setNowWindDir(String nowWindDir) {
        this.nowWindDir = nowWindDir;
    }

    /**
     * 获取风速，公里/小时
     *
     * @return now_wind_spd - 风速，公里/小时
     */
    public String getNowWindSpd() {
        return nowWindSpd;
    }

    /**
     * 设置风速，公里/小时
     *
     * @param nowWindSpd 风速，公里/小时
     */
    public void setNowWindSpd(String nowWindSpd) {
        this.nowWindSpd = nowWindSpd;
    }

    /**
     * 获取风力
     *
     * @return now_wind_sc - 风力
     */
    public String getNowWindSc() {
        return nowWindSc;
    }

    /**
     * 设置风力
     *
     * @param nowWindSc 风力
     */
    public void setNowWindSc(String nowWindSc) {
        this.nowWindSc = nowWindSc;
    }

    /**
     * 获取相对湿度
     *
     * @return now_hum - 相对湿度
     */
    public String getNowHum() {
        return nowHum;
    }

    /**
     * 设置相对湿度
     *
     * @param nowHum 相对湿度
     */
    public void setNowHum(String nowHum) {
        this.nowHum = nowHum;
    }

    /**
     * 获取降水量
     *
     * @return now_pcpn - 降水量
     */
    public String getNowPcpn() {
        return nowPcpn;
    }

    /**
     * 设置降水量
     *
     * @param nowPcpn 降水量
     */
    public void setNowPcpn(String nowPcpn) {
        this.nowPcpn = nowPcpn;
    }

    /**
     * 获取大气压强
     *
     * @return now_pres - 大气压强
     */
    public String getNowPres() {
        return nowPres;
    }

    /**
     * 设置大气压强
     *
     * @param nowPres 大气压强
     */
    public void setNowPres(String nowPres) {
        this.nowPres = nowPres;
    }

    /**
     * 获取能见度，默认单位：公里
     *
     * @return now_vis - 能见度，默认单位：公里
     */
    public String getNowVis() {
        return nowVis;
    }

    /**
     * 设置能见度，默认单位：公里
     *
     * @param nowVis 能见度，默认单位：公里
     */
    public void setNowVis(String nowVis) {
        this.nowVis = nowVis;
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
}