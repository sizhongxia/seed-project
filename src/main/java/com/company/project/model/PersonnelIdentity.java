package com.company.project.model;

import javax.persistence.*;

@Table(name = "personnel_identity")
public class PersonnelIdentity {
    /**
     * 自增id
     */
    @Id
    private Integer id;

    /**
     * 数据id 也就是人员身份证号
     */
    @Column(name = "empNo")
    private String empno;

    /**
     * 人员类型  sys_dictionry 表  personnelType字段值 1 管理 2  劳务
     */
    private Integer type;

    /**
     * 人员所属企业uuid   unit_company 表的uuid
     */
    @Column(name = "companyUuid")
    private String companyuuid;

    /**
     * 人员所属工地uuid   unit_projectUuid   表的uuid
     */
    @Column(name = "proUuid")
    private String prouuid;

    /**
     * 劳务人员所属班组  unit_group 表的uuid
     */
    @Column(name = "groupUuid")
    private String groupuuid;

    /**
     * 是否是班组长
     */
    @Column(name = "isGroupLeader")
    private Integer isgroupleader;

    /**
     * 劳务人员工种   unit_workType 表的id
     */
    @Column(name = "workType")
    private Integer worktype;

    /**
     * 管理人员所属部门   unit_departMent  表的 uuid
     */
    @Column(name = "deptUuid")
    private String deptuuid;

    /**
     * 管理人员岗位   unit_post 表的uuid
     */
    private String post;

    /**
     * 是否删除0正常   1 数据删除
     */
    private Integer state;

    /**
     * 更新时间时间戳
     */
    @Column(name = "updateTime")
    private Long updatetime;

    /**
     * 添加时间时间戳
     */
    @Column(name = "addTime")
    private Long addtime;

    /**
     * 获取自增id
     *
     * @return id - 自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增id
     *
     * @param id 自增id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取数据id 也就是人员身份证号
     *
     * @return empNo - 数据id 也就是人员身份证号
     */
    public String getEmpno() {
        return empno;
    }

    /**
     * 设置数据id 也就是人员身份证号
     *
     * @param empno 数据id 也就是人员身份证号
     */
    public void setEmpno(String empno) {
        this.empno = empno;
    }

    /**
     * 获取人员类型  sys_dictionry 表  personnelType字段值 1 管理 2  劳务
     *
     * @return type - 人员类型  sys_dictionry 表  personnelType字段值 1 管理 2  劳务
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置人员类型  sys_dictionry 表  personnelType字段值 1 管理 2  劳务
     *
     * @param type 人员类型  sys_dictionry 表  personnelType字段值 1 管理 2  劳务
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取人员所属企业uuid   unit_company 表的uuid
     *
     * @return companyUuid - 人员所属企业uuid   unit_company 表的uuid
     */
    public String getCompanyuuid() {
        return companyuuid;
    }

    /**
     * 设置人员所属企业uuid   unit_company 表的uuid
     *
     * @param companyuuid 人员所属企业uuid   unit_company 表的uuid
     */
    public void setCompanyuuid(String companyuuid) {
        this.companyuuid = companyuuid;
    }

    /**
     * 获取人员所属工地uuid   unit_projectUuid   表的uuid
     *
     * @return proUuid - 人员所属工地uuid   unit_projectUuid   表的uuid
     */
    public String getProuuid() {
        return prouuid;
    }

    /**
     * 设置人员所属工地uuid   unit_projectUuid   表的uuid
     *
     * @param prouuid 人员所属工地uuid   unit_projectUuid   表的uuid
     */
    public void setProuuid(String prouuid) {
        this.prouuid = prouuid;
    }

    /**
     * 获取劳务人员所属班组  unit_group 表的uuid
     *
     * @return groupUuid - 劳务人员所属班组  unit_group 表的uuid
     */
    public String getGroupuuid() {
        return groupuuid;
    }

    /**
     * 设置劳务人员所属班组  unit_group 表的uuid
     *
     * @param groupuuid 劳务人员所属班组  unit_group 表的uuid
     */
    public void setGroupuuid(String groupuuid) {
        this.groupuuid = groupuuid;
    }

    /**
     * 获取是否是班组长
     *
     * @return isGroupLeader - 是否是班组长
     */
    public Integer getIsgroupleader() {
        return isgroupleader;
    }

    /**
     * 设置是否是班组长
     *
     * @param isgroupleader 是否是班组长
     */
    public void setIsgroupleader(Integer isgroupleader) {
        this.isgroupleader = isgroupleader;
    }

    /**
     * 获取劳务人员工种   unit_workType 表的id
     *
     * @return workType - 劳务人员工种   unit_workType 表的id
     */
    public Integer getWorktype() {
        return worktype;
    }

    /**
     * 设置劳务人员工种   unit_workType 表的id
     *
     * @param worktype 劳务人员工种   unit_workType 表的id
     */
    public void setWorktype(Integer worktype) {
        this.worktype = worktype;
    }

    /**
     * 获取管理人员所属部门   unit_departMent  表的 uuid
     *
     * @return deptUuid - 管理人员所属部门   unit_departMent  表的 uuid
     */
    public String getDeptuuid() {
        return deptuuid;
    }

    /**
     * 设置管理人员所属部门   unit_departMent  表的 uuid
     *
     * @param deptuuid 管理人员所属部门   unit_departMent  表的 uuid
     */
    public void setDeptuuid(String deptuuid) {
        this.deptuuid = deptuuid;
    }

    /**
     * 获取管理人员岗位   unit_post 表的uuid
     *
     * @return post - 管理人员岗位   unit_post 表的uuid
     */
    public String getPost() {
        return post;
    }

    /**
     * 设置管理人员岗位   unit_post 表的uuid
     *
     * @param post 管理人员岗位   unit_post 表的uuid
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * 获取是否删除0正常   1 数据删除
     *
     * @return state - 是否删除0正常   1 数据删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置是否删除0正常   1 数据删除
     *
     * @param state 是否删除0正常   1 数据删除
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取更新时间时间戳
     *
     * @return updateTime - 更新时间时间戳
     */
    public Long getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置更新时间时间戳
     *
     * @param updatetime 更新时间时间戳
     */
    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取添加时间时间戳
     *
     * @return addTime - 添加时间时间戳
     */
    public Long getAddtime() {
        return addtime;
    }

    /**
     * 设置添加时间时间戳
     *
     * @param addtime 添加时间时间戳
     */
    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }
}