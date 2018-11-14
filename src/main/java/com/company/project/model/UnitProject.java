package com.company.project.model;

import javax.persistence.*;

@Table(name = "unit_project")
public class UnitProject {
    /**
     * 工地自增id
     */
    @Id
    private Integer id;

    /**
     * 工地id唯一标识
     */
    private String uuid;

    /**
     * 项目简称
     */
    private String name;

    /**
     * 工地名称全称呼
     */
    @Column(name = "proName")
    private String proname;

    /**
     * 平台序列号，项目编码
     */
    @Column(name = "proCode")
    private String procode;

    /**
     * 工地经度
     */
    private String longitude;

    /**
     * 工地纬度
     */
    private String latitude;

    /**
     * 工地所属单位
     */
    @Column(name = "companyUuid")
    private String companyuuid;

    /**
     * 建设单位UUID
     */
    private String building;

    /**
     * 监理单位UUID
     */
    private String supervision;

    /**
     * 勘察单位UUD
     */
    private String survey;

    /**
     * 施工单位UUID
     */
    private String construction;

    /**
     * 设计单位UUID
     */
    private String design;

    /**
     * 工地宽度,区位图宽度
     */
    private Integer width;

    /**
     * 工地长度,区位图长度
     */
    private Integer length;

    /**
     * 区位图文件名称
     */
    @Column(name = "locationMap")
    private String locationmap;

    /**
     * 添加时间
     */
    @Column(name = "addTime")
    private Long addtime;

    /**
     * 更新时间
     */
    @Column(name = "updateTime")
    private Long updatetime;

    /**
     * 现场联系人负责人
     */
    @Column(name = "personName")
    private String personname;

    /**
     * 现场联系人电话
     */
    private String phone;

    /**
     * 销售人员姓名
     */
    @Column(name = "salesName")
    private String salesname;

    /**
     * 销售联系电话
     */
    @Column(name = "salesPhone")
    private String salesphone;

    /**
     * 0正常1 删除
     */
    private Integer state;

    /**
     * 工地二维码地址
     */
    private String code;

    /**
     * 工地logo文件名
     */
    private String logo;

    /**
     * 建筑面积   平方米
     */
    private Double measure;

    /**
     * 投资额  万元
     */
    private Double investment;

    /**
     * 建筑层数
     */
    @Column(name = "numberOfLayers")
    private String numberoflayers;

    /**
     * 工程类型    字典表  proType
     */
    private String type;

    /**
     * 建设性质    字典表  proConstructionNature
     */
    @Column(name = "constructionNature")
    private String constructionnature;

    /**
     * 主要结构类型   字典表   proMainStructureType
     */
    @Column(name = "mainStructureType")
    private String mainstructuretype;

    /**
     * 项目功能（字典表  proFunctrion）、工程用途，坑爹不是字典的了
     */
    private String function;

    /**
     * 计划开工时间
     */
    @Column(name = "planStartTime")
    private Long planstarttime;

    /**
     * 计划竣工时间
     */
    @Column(name = "planEndTime")
    private Long planendtime;

    /**
     * 工地所在省
     */
    private String province;

    /**
     * 工地所在市
     */
    private String city;

    /**
     * 工地所在区/县
     */
    private String county;

    /**
     * 获取工地自增id
     *
     * @return id - 工地自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置工地自增id
     *
     * @param id 工地自增id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取工地id唯一标识
     *
     * @return uuid - 工地id唯一标识
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置工地id唯一标识
     *
     * @param uuid 工地id唯一标识
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取项目简称
     *
     * @return name - 项目简称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置项目简称
     *
     * @param name 项目简称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取工地名称全称呼
     *
     * @return proName - 工地名称全称呼
     */
    public String getProname() {
        return proname;
    }

    /**
     * 设置工地名称全称呼
     *
     * @param proname 工地名称全称呼
     */
    public void setProname(String proname) {
        this.proname = proname;
    }

    /**
     * 获取平台序列号，项目编码
     *
     * @return proCode - 平台序列号，项目编码
     */
    public String getProcode() {
        return procode;
    }

    /**
     * 设置平台序列号，项目编码
     *
     * @param procode 平台序列号，项目编码
     */
    public void setProcode(String procode) {
        this.procode = procode;
    }

    /**
     * 获取工地经度
     *
     * @return longitude - 工地经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置工地经度
     *
     * @param longitude 工地经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取工地纬度
     *
     * @return latitude - 工地纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置工地纬度
     *
     * @param latitude 工地纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取工地所属单位
     *
     * @return companyUuid - 工地所属单位
     */
    public String getCompanyuuid() {
        return companyuuid;
    }

    /**
     * 设置工地所属单位
     *
     * @param companyuuid 工地所属单位
     */
    public void setCompanyuuid(String companyuuid) {
        this.companyuuid = companyuuid;
    }

    /**
     * 获取建设单位UUID
     *
     * @return building - 建设单位UUID
     */
    public String getBuilding() {
        return building;
    }

    /**
     * 设置建设单位UUID
     *
     * @param building 建设单位UUID
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * 获取监理单位UUID
     *
     * @return supervision - 监理单位UUID
     */
    public String getSupervision() {
        return supervision;
    }

    /**
     * 设置监理单位UUID
     *
     * @param supervision 监理单位UUID
     */
    public void setSupervision(String supervision) {
        this.supervision = supervision;
    }

    /**
     * 获取勘察单位UUD
     *
     * @return survey - 勘察单位UUD
     */
    public String getSurvey() {
        return survey;
    }

    /**
     * 设置勘察单位UUD
     *
     * @param survey 勘察单位UUD
     */
    public void setSurvey(String survey) {
        this.survey = survey;
    }

    /**
     * 获取施工单位UUID
     *
     * @return construction - 施工单位UUID
     */
    public String getConstruction() {
        return construction;
    }

    /**
     * 设置施工单位UUID
     *
     * @param construction 施工单位UUID
     */
    public void setConstruction(String construction) {
        this.construction = construction;
    }

    /**
     * 获取设计单位UUID
     *
     * @return design - 设计单位UUID
     */
    public String getDesign() {
        return design;
    }

    /**
     * 设置设计单位UUID
     *
     * @param design 设计单位UUID
     */
    public void setDesign(String design) {
        this.design = design;
    }

    /**
     * 获取工地宽度,区位图宽度
     *
     * @return width - 工地宽度,区位图宽度
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * 设置工地宽度,区位图宽度
     *
     * @param width 工地宽度,区位图宽度
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 获取工地长度,区位图长度
     *
     * @return length - 工地长度,区位图长度
     */
    public Integer getLength() {
        return length;
    }

    /**
     * 设置工地长度,区位图长度
     *
     * @param length 工地长度,区位图长度
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * 获取区位图文件名称
     *
     * @return locationMap - 区位图文件名称
     */
    public String getLocationmap() {
        return locationmap;
    }

    /**
     * 设置区位图文件名称
     *
     * @param locationmap 区位图文件名称
     */
    public void setLocationmap(String locationmap) {
        this.locationmap = locationmap;
    }

    /**
     * 获取添加时间
     *
     * @return addTime - 添加时间
     */
    public Long getAddtime() {
        return addtime;
    }

    /**
     * 设置添加时间
     *
     * @param addtime 添加时间
     */
    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取更新时间
     *
     * @return updateTime - 更新时间
     */
    public Long getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置更新时间
     *
     * @param updatetime 更新时间
     */
    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取现场联系人负责人
     *
     * @return personName - 现场联系人负责人
     */
    public String getPersonname() {
        return personname;
    }

    /**
     * 设置现场联系人负责人
     *
     * @param personname 现场联系人负责人
     */
    public void setPersonname(String personname) {
        this.personname = personname;
    }

    /**
     * 获取现场联系人电话
     *
     * @return phone - 现场联系人电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置现场联系人电话
     *
     * @param phone 现场联系人电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取销售人员姓名
     *
     * @return salesName - 销售人员姓名
     */
    public String getSalesname() {
        return salesname;
    }

    /**
     * 设置销售人员姓名
     *
     * @param salesname 销售人员姓名
     */
    public void setSalesname(String salesname) {
        this.salesname = salesname;
    }

    /**
     * 获取销售联系电话
     *
     * @return salesPhone - 销售联系电话
     */
    public String getSalesphone() {
        return salesphone;
    }

    /**
     * 设置销售联系电话
     *
     * @param salesphone 销售联系电话
     */
    public void setSalesphone(String salesphone) {
        this.salesphone = salesphone;
    }

    /**
     * 获取0正常1 删除
     *
     * @return state - 0正常1 删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0正常1 删除
     *
     * @param state 0正常1 删除
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取工地二维码地址
     *
     * @return code - 工地二维码地址
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置工地二维码地址
     *
     * @param code 工地二维码地址
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取工地logo文件名
     *
     * @return logo - 工地logo文件名
     */
    public String getLogo() {
        return logo;
    }

    /**
     * 设置工地logo文件名
     *
     * @param logo 工地logo文件名
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * 获取建筑面积   平方米
     *
     * @return measure - 建筑面积   平方米
     */
    public Double getMeasure() {
        return measure;
    }

    /**
     * 设置建筑面积   平方米
     *
     * @param measure 建筑面积   平方米
     */
    public void setMeasure(Double measure) {
        this.measure = measure;
    }

    /**
     * 获取投资额  万元
     *
     * @return investment - 投资额  万元
     */
    public Double getInvestment() {
        return investment;
    }

    /**
     * 设置投资额  万元
     *
     * @param investment 投资额  万元
     */
    public void setInvestment(Double investment) {
        this.investment = investment;
    }

    /**
     * 获取建筑层数
     *
     * @return numberOfLayers - 建筑层数
     */
    public String getNumberoflayers() {
        return numberoflayers;
    }

    /**
     * 设置建筑层数
     *
     * @param numberoflayers 建筑层数
     */
    public void setNumberoflayers(String numberoflayers) {
        this.numberoflayers = numberoflayers;
    }

    /**
     * 获取工程类型    字典表  proType
     *
     * @return type - 工程类型    字典表  proType
     */
    public String getType() {
        return type;
    }

    /**
     * 设置工程类型    字典表  proType
     *
     * @param type 工程类型    字典表  proType
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取建设性质    字典表  proConstructionNature
     *
     * @return constructionNature - 建设性质    字典表  proConstructionNature
     */
    public String getConstructionnature() {
        return constructionnature;
    }

    /**
     * 设置建设性质    字典表  proConstructionNature
     *
     * @param constructionnature 建设性质    字典表  proConstructionNature
     */
    public void setConstructionnature(String constructionnature) {
        this.constructionnature = constructionnature;
    }

    /**
     * 获取主要结构类型   字典表   proMainStructureType
     *
     * @return mainStructureType - 主要结构类型   字典表   proMainStructureType
     */
    public String getMainstructuretype() {
        return mainstructuretype;
    }

    /**
     * 设置主要结构类型   字典表   proMainStructureType
     *
     * @param mainstructuretype 主要结构类型   字典表   proMainStructureType
     */
    public void setMainstructuretype(String mainstructuretype) {
        this.mainstructuretype = mainstructuretype;
    }

    /**
     * 获取项目功能（字典表  proFunctrion）、工程用途，坑爹不是字典的了
     *
     * @return function - 项目功能（字典表  proFunctrion）、工程用途，坑爹不是字典的了
     */
    public String getFunction() {
        return function;
    }

    /**
     * 设置项目功能（字典表  proFunctrion）、工程用途，坑爹不是字典的了
     *
     * @param function 项目功能（字典表  proFunctrion）、工程用途，坑爹不是字典的了
     */
    public void setFunction(String function) {
        this.function = function;
    }

    /**
     * 获取计划开工时间
     *
     * @return planStartTime - 计划开工时间
     */
    public Long getPlanstarttime() {
        return planstarttime;
    }

    /**
     * 设置计划开工时间
     *
     * @param planstarttime 计划开工时间
     */
    public void setPlanstarttime(Long planstarttime) {
        this.planstarttime = planstarttime;
    }

    /**
     * 获取计划竣工时间
     *
     * @return planEndTime - 计划竣工时间
     */
    public Long getPlanendtime() {
        return planendtime;
    }

    /**
     * 设置计划竣工时间
     *
     * @param planendtime 计划竣工时间
     */
    public void setPlanendtime(Long planendtime) {
        this.planendtime = planendtime;
    }

    /**
     * 获取工地所在省
     *
     * @return province - 工地所在省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置工地所在省
     *
     * @param province 工地所在省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取工地所在市
     *
     * @return city - 工地所在市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置工地所在市
     *
     * @param city 工地所在市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取工地所在区/县
     *
     * @return county - 工地所在区/县
     */
    public String getCounty() {
        return county;
    }

    /**
     * 设置工地所在区/县
     *
     * @param county 工地所在区/县
     */
    public void setCounty(String county) {
        this.county = county;
    }
}