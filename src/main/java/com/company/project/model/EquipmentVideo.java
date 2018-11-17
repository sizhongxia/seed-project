package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "equipment_video")
public class EquipmentVideo {
    @Id
    private Integer id;

    /**
     * 数据id
     */
    private String uuid;

    /**
     * 摄像头名称
     */
    private String name;

    /**
     * 所属工地  或者单位
     */
    @Column(name = "deptUuid")
    private String deptuuid;

    /**
     * 视频拉流地址
     */
    @Column(name = "flowAddress")
    private String flowaddress;

    /**
     * 推流地址
     */
    @Column(name = "streamUrl")
    private String streamurl;

    /**
     * 播放地址
     */
    @Column(name = "playUrl")
    private String playurl;

    /**
     * 部署安装时间
     */
    @Column(name = "deployTime")
    private Long deploytime;

    /**
     * 品牌名称
     */
    @Column(name = "brandName")
    private String brandname;

    /**
     * 1：球机  2：枪机
     */
    private String type;

    /**
     * 变焦 1  旋转 2   3焦点  4 光圈
     */
    private String voperation;

    /**
     * 是否一体机推流方式 (1是 2否)
     */
    @Column(name = "isTowerEye")
    private Integer istowereye;

    /**
     * 推流方式（呼叫0，长期1）
     */
    @Column(name = "pushFlowMode")
    private String pushflowmode;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 端口号
     */
    private String port;

    /**
     * 用户名
     */
    @Column(name = "userName")
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 0正常   1 删除
     */
    private Integer state;

    /**
     * 最后一次刷新的时间 可以判断在线与否
     */
    private Date lasttime;

    /**
     * no 不在线 yes 判断lasttime 不能超过2分钟
     */
    private String onvifstatus;

    /**
     * 是否是塔吊眼 (1是 2否)
     */
    @Column(name = "isEyeClient")
    private Integer iseyeclient;

    /**
     * 设备数据uuid 比如塔吊
     */
    @Column(name = "equipmentUuid")
    private String equipmentuuid;

    /**
     * 连接字符串被更新时的时间
     */
    @Column(name = "remoteTime")
    private Date remotetime;

    /**
     * 客户端连接字符串
     */
    @Column(name = "remoteConnectionString")
    private String remoteconnectionstring;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取数据id
     *
     * @return uuid - 数据id
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置数据id
     *
     * @param uuid 数据id
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取摄像头名称
     *
     * @return name - 摄像头名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置摄像头名称
     *
     * @param name 摄像头名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取所属工地  或者单位
     *
     * @return deptUuid - 所属工地  或者单位
     */
    public String getDeptuuid() {
        return deptuuid;
    }

    /**
     * 设置所属工地  或者单位
     *
     * @param deptuuid 所属工地  或者单位
     */
    public void setDeptuuid(String deptuuid) {
        this.deptuuid = deptuuid;
    }

    /**
     * 获取视频拉流地址
     *
     * @return flowAddress - 视频拉流地址
     */
    public String getFlowaddress() {
        return flowaddress;
    }

    /**
     * 设置视频拉流地址
     *
     * @param flowaddress 视频拉流地址
     */
    public void setFlowaddress(String flowaddress) {
        this.flowaddress = flowaddress;
    }

    /**
     * 获取推流地址
     *
     * @return streamUrl - 推流地址
     */
    public String getStreamurl() {
        return streamurl;
    }

    /**
     * 设置推流地址
     *
     * @param streamurl 推流地址
     */
    public void setStreamurl(String streamurl) {
        this.streamurl = streamurl;
    }

    /**
     * 获取播放地址
     *
     * @return playUrl - 播放地址
     */
    public String getPlayurl() {
        return playurl;
    }

    /**
     * 设置播放地址
     *
     * @param playurl 播放地址
     */
    public void setPlayurl(String playurl) {
        this.playurl = playurl;
    }

    /**
     * 获取部署安装时间
     *
     * @return deployTime - 部署安装时间
     */
    public Long getDeploytime() {
        return deploytime;
    }

    /**
     * 设置部署安装时间
     *
     * @param deploytime 部署安装时间
     */
    public void setDeploytime(Long deploytime) {
        this.deploytime = deploytime;
    }

    /**
     * 获取品牌名称
     *
     * @return brandName - 品牌名称
     */
    public String getBrandname() {
        return brandname;
    }

    /**
     * 设置品牌名称
     *
     * @param brandname 品牌名称
     */
    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    /**
     * 获取1：球机  2：枪机
     *
     * @return type - 1：球机  2：枪机
     */
    public String getType() {
        return type;
    }

    /**
     * 设置1：球机  2：枪机
     *
     * @param type 1：球机  2：枪机
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取变焦 1  旋转 2   3焦点  4 光圈
     *
     * @return voperation - 变焦 1  旋转 2   3焦点  4 光圈
     */
    public String getVoperation() {
        return voperation;
    }

    /**
     * 设置变焦 1  旋转 2   3焦点  4 光圈
     *
     * @param voperation 变焦 1  旋转 2   3焦点  4 光圈
     */
    public void setVoperation(String voperation) {
        this.voperation = voperation;
    }

    /**
     * 获取是否一体机推流方式 (1是 2否)
     *
     * @return isTowerEye - 是否一体机推流方式 (1是 2否)
     */
    public Integer getIstowereye() {
        return istowereye;
    }

    /**
     * 设置是否一体机推流方式 (1是 2否)
     *
     * @param istowereye 是否一体机推流方式 (1是 2否)
     */
    public void setIstowereye(Integer istowereye) {
        this.istowereye = istowereye;
    }

    /**
     * 获取推流方式（呼叫0，长期1）
     *
     * @return pushFlowMode - 推流方式（呼叫0，长期1）
     */
    public String getPushflowmode() {
        return pushflowmode;
    }

    /**
     * 设置推流方式（呼叫0，长期1）
     *
     * @param pushflowmode 推流方式（呼叫0，长期1）
     */
    public void setPushflowmode(String pushflowmode) {
        this.pushflowmode = pushflowmode;
    }

    /**
     * 获取ip地址
     *
     * @return ip - ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置ip地址
     *
     * @param ip ip地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取端口号
     *
     * @return port - 端口号
     */
    public String getPort() {
        return port;
    }

    /**
     * 设置端口号
     *
     * @param port 端口号
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * 获取用户名
     *
     * @return userName - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取0正常   1 删除
     *
     * @return state - 0正常   1 删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0正常   1 删除
     *
     * @param state 0正常   1 删除
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取最后一次刷新的时间 可以判断在线与否
     *
     * @return lasttime - 最后一次刷新的时间 可以判断在线与否
     */
    public Date getLasttime() {
        return lasttime;
    }

    /**
     * 设置最后一次刷新的时间 可以判断在线与否
     *
     * @param lasttime 最后一次刷新的时间 可以判断在线与否
     */
    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    /**
     * 获取no 不在线 yes 判断lasttime 不能超过2分钟
     *
     * @return onvifstatus - no 不在线 yes 判断lasttime 不能超过2分钟
     */
    public String getOnvifstatus() {
        return onvifstatus;
    }

    /**
     * 设置no 不在线 yes 判断lasttime 不能超过2分钟
     *
     * @param onvifstatus no 不在线 yes 判断lasttime 不能超过2分钟
     */
    public void setOnvifstatus(String onvifstatus) {
        this.onvifstatus = onvifstatus;
    }

    /**
     * 获取是否是塔吊眼 (1是 2否)
     *
     * @return isEyeClient - 是否是塔吊眼 (1是 2否)
     */
    public Integer getIseyeclient() {
        return iseyeclient;
    }

    /**
     * 设置是否是塔吊眼 (1是 2否)
     *
     * @param iseyeclient 是否是塔吊眼 (1是 2否)
     */
    public void setIseyeclient(Integer iseyeclient) {
        this.iseyeclient = iseyeclient;
    }

    /**
     * 获取设备数据uuid 比如塔吊
     *
     * @return equipmentUuid - 设备数据uuid 比如塔吊
     */
    public String getEquipmentuuid() {
        return equipmentuuid;
    }

    /**
     * 设置设备数据uuid 比如塔吊
     *
     * @param equipmentuuid 设备数据uuid 比如塔吊
     */
    public void setEquipmentuuid(String equipmentuuid) {
        this.equipmentuuid = equipmentuuid;
    }

    /**
     * 获取连接字符串被更新时的时间
     *
     * @return remoteTime - 连接字符串被更新时的时间
     */
    public Date getRemotetime() {
        return remotetime;
    }

    /**
     * 设置连接字符串被更新时的时间
     *
     * @param remotetime 连接字符串被更新时的时间
     */
    public void setRemotetime(Date remotetime) {
        this.remotetime = remotetime;
    }

    /**
     * 获取客户端连接字符串
     *
     * @return remoteConnectionString - 客户端连接字符串
     */
    public String getRemoteconnectionstring() {
        return remoteconnectionstring;
    }

    /**
     * 设置客户端连接字符串
     *
     * @param remoteconnectionstring 客户端连接字符串
     */
    public void setRemoteconnectionstring(String remoteconnectionstring) {
        this.remoteconnectionstring = remoteconnectionstring;
    }
}