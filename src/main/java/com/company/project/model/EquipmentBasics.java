package com.company.project.model;

import javax.persistence.*;

@Table(name = "equipment_basics")
public class EquipmentBasics {
    /**
     * 自增id排序用
     */
    @Id
    private Integer id;

    /**
     * 数据id
     */
    private String uuid;

    /**
     * 设备所属单位uuid  代理商等，直属工地，则为空
     */
    @Column(name = "companyUuid")
    private String companyuuid;

    /**
     * 设备所属工地， 
     */
    @Column(name = "proUuid")
    private String prouuid;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备编号，全表唯一不重复（至少某种设备不重复）
     */
    @Column(name = "equipmentNo")
    private String equipmentno;

    /**
     * 设备区位图相对坐标x
     */
    private String positionx;

    /**
     * 设备区位图相对坐标y
     */
    private String positiony;

    /**
     * 设备类型  相见字典表sys_dictionart  equipmentType 类型
     */
    private Integer type;

    /**
     * 数据状态0正常  其他删除
     */
    private Integer state;

    /**
     * 设备添加时间
     */
    @Column(name = "addTime")
    private Long addtime;

    /**
     * 设备编辑时间
     */
    @Column(name = "updateTime")
    private Long updatetime;

    /**
     * 设备最后在线时间
     */
    @Column(name = "lastOnLineTime")
    private Long lastonlinetime;

    /**
     * 设备供应商   uuid
     */
    @Column(name = "supplierCompanyUuid")
    private String suppliercompanyuuid;

    /**
     * 设备代理商   uuid
     */
    @Column(name = "agentCompanyUuid")
    private String agentcompanyuuid;

    /**
     * ip及端口
     */
    @Column(name = "ipaddressPort")
    private String ipaddressport;

    /**
     * 0待下发，1服务抓取，2下发成功，3下发失败
     */
    @Column(name = "ipportIssueStatus")
    private Integer ipportissuestatus;

    /**
     * 电表设备工作类型，人员定位设备1进2出
     */
    @Column(name = "workType")
    private String worktype;

    /**
     * 高德地图设备经度
     */
    private String lng;

    /**
     * 高德地图设备维度
     */
    private String lat;

    /**
     * 获取自增id排序用
     *
     * @return id - 自增id排序用
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增id排序用
     *
     * @param id 自增id排序用
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
     * 获取设备所属单位uuid  代理商等，直属工地，则为空
     *
     * @return companyUuid - 设备所属单位uuid  代理商等，直属工地，则为空
     */
    public String getCompanyuuid() {
        return companyuuid;
    }

    /**
     * 设置设备所属单位uuid  代理商等，直属工地，则为空
     *
     * @param companyuuid 设备所属单位uuid  代理商等，直属工地，则为空
     */
    public void setCompanyuuid(String companyuuid) {
        this.companyuuid = companyuuid;
    }

    /**
     * 获取设备所属工地， 
     *
     * @return proUuid - 设备所属工地， 
     */
    public String getProuuid() {
        return prouuid;
    }

    /**
     * 设置设备所属工地， 
     *
     * @param prouuid 设备所属工地， 
     */
    public void setProuuid(String prouuid) {
        this.prouuid = prouuid;
    }

    /**
     * 获取设备名称
     *
     * @return name - 设备名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置设备名称
     *
     * @param name 设备名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取设备编号，全表唯一不重复（至少某种设备不重复）
     *
     * @return equipmentNo - 设备编号，全表唯一不重复（至少某种设备不重复）
     */
    public String getEquipmentno() {
        return equipmentno;
    }

    /**
     * 设置设备编号，全表唯一不重复（至少某种设备不重复）
     *
     * @param equipmentno 设备编号，全表唯一不重复（至少某种设备不重复）
     */
    public void setEquipmentno(String equipmentno) {
        this.equipmentno = equipmentno;
    }

    /**
     * 获取设备区位图相对坐标x
     *
     * @return positionx - 设备区位图相对坐标x
     */
    public String getPositionx() {
        return positionx;
    }

    /**
     * 设置设备区位图相对坐标x
     *
     * @param positionx 设备区位图相对坐标x
     */
    public void setPositionx(String positionx) {
        this.positionx = positionx;
    }

    /**
     * 获取设备区位图相对坐标y
     *
     * @return positiony - 设备区位图相对坐标y
     */
    public String getPositiony() {
        return positiony;
    }

    /**
     * 设置设备区位图相对坐标y
     *
     * @param positiony 设备区位图相对坐标y
     */
    public void setPositiony(String positiony) {
        this.positiony = positiony;
    }

    /**
     * 获取设备类型  相见字典表sys_dictionart  equipmentType 类型
     *
     * @return type - 设备类型  相见字典表sys_dictionart  equipmentType 类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置设备类型  相见字典表sys_dictionart  equipmentType 类型
     *
     * @param type 设备类型  相见字典表sys_dictionart  equipmentType 类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取数据状态0正常  其他删除
     *
     * @return state - 数据状态0正常  其他删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置数据状态0正常  其他删除
     *
     * @param state 数据状态0正常  其他删除
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取设备添加时间
     *
     * @return addTime - 设备添加时间
     */
    public Long getAddtime() {
        return addtime;
    }

    /**
     * 设置设备添加时间
     *
     * @param addtime 设备添加时间
     */
    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取设备编辑时间
     *
     * @return updateTime - 设备编辑时间
     */
    public Long getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置设备编辑时间
     *
     * @param updatetime 设备编辑时间
     */
    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取设备最后在线时间
     *
     * @return lastOnLineTime - 设备最后在线时间
     */
    public Long getLastonlinetime() {
        return lastonlinetime;
    }

    /**
     * 设置设备最后在线时间
     *
     * @param lastonlinetime 设备最后在线时间
     */
    public void setLastonlinetime(Long lastonlinetime) {
        this.lastonlinetime = lastonlinetime;
    }

    /**
     * 获取设备供应商   uuid
     *
     * @return supplierCompanyUuid - 设备供应商   uuid
     */
    public String getSuppliercompanyuuid() {
        return suppliercompanyuuid;
    }

    /**
     * 设置设备供应商   uuid
     *
     * @param suppliercompanyuuid 设备供应商   uuid
     */
    public void setSuppliercompanyuuid(String suppliercompanyuuid) {
        this.suppliercompanyuuid = suppliercompanyuuid;
    }

    /**
     * 获取设备代理商   uuid
     *
     * @return agentCompanyUuid - 设备代理商   uuid
     */
    public String getAgentcompanyuuid() {
        return agentcompanyuuid;
    }

    /**
     * 设置设备代理商   uuid
     *
     * @param agentcompanyuuid 设备代理商   uuid
     */
    public void setAgentcompanyuuid(String agentcompanyuuid) {
        this.agentcompanyuuid = agentcompanyuuid;
    }

    /**
     * 获取ip及端口
     *
     * @return ipaddressPort - ip及端口
     */
    public String getIpaddressport() {
        return ipaddressport;
    }

    /**
     * 设置ip及端口
     *
     * @param ipaddressport ip及端口
     */
    public void setIpaddressport(String ipaddressport) {
        this.ipaddressport = ipaddressport;
    }

    /**
     * 获取0待下发，1服务抓取，2下发成功，3下发失败
     *
     * @return ipportIssueStatus - 0待下发，1服务抓取，2下发成功，3下发失败
     */
    public Integer getIpportissuestatus() {
        return ipportissuestatus;
    }

    /**
     * 设置0待下发，1服务抓取，2下发成功，3下发失败
     *
     * @param ipportissuestatus 0待下发，1服务抓取，2下发成功，3下发失败
     */
    public void setIpportissuestatus(Integer ipportissuestatus) {
        this.ipportissuestatus = ipportissuestatus;
    }

    /**
     * 获取电表设备工作类型，人员定位设备1进2出
     *
     * @return workType - 电表设备工作类型，人员定位设备1进2出
     */
    public String getWorktype() {
        return worktype;
    }

    /**
     * 设置电表设备工作类型，人员定位设备1进2出
     *
     * @param worktype 电表设备工作类型，人员定位设备1进2出
     */
    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    /**
     * 获取高德地图设备经度
     *
     * @return lng - 高德地图设备经度
     */
    public String getLng() {
        return lng;
    }

    /**
     * 设置高德地图设备经度
     *
     * @param lng 高德地图设备经度
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

    /**
     * 获取高德地图设备维度
     *
     * @return lat - 高德地图设备维度
     */
    public String getLat() {
        return lat;
    }

    /**
     * 设置高德地图设备维度
     *
     * @param lat 高德地图设备维度
     */
    public void setLat(String lat) {
        this.lat = lat;
    }
}