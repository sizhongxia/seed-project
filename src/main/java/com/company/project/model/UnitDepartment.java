package com.company.project.model;

import javax.persistence.*;

@Table(name = "unit_department")
public class UnitDepartment {
    /**
     * 自增列，排序用
     */
    @Id
    private Integer id;

    /**
     * 数据唯一标识
     */
    private String uuid;

    /**
     * 添加时间
     */
    @Column(name = "addTime")
    private Long addtime;

    /**
     * 编辑时间
     */
    @Column(name = "updateTime")
    private Long updatetime;

    /**
     * 父节点uuid
     */
    @Column(name = "parentUUID")
    private String parentuuid;

    /**
     * 部门名称
     */
    @Column(name = "deptName")
    private String deptname;

    /**
     * 所属工地，公司，政务单位唯一标识
     */
    @Column(name = "deptUUID")
    private String deptuuid;

    /**
     * 0正常  1删除
     */
    private Integer state;

    /**
     * 获取自增列，排序用
     *
     * @return id - 自增列，排序用
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增列，排序用
     *
     * @param id 自增列，排序用
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取数据唯一标识
     *
     * @return uuid - 数据唯一标识
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置数据唯一标识
     *
     * @param uuid 数据唯一标识
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
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
     * 获取编辑时间
     *
     * @return updateTime - 编辑时间
     */
    public Long getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置编辑时间
     *
     * @param updatetime 编辑时间
     */
    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取父节点uuid
     *
     * @return parentUUID - 父节点uuid
     */
    public String getParentuuid() {
        return parentuuid;
    }

    /**
     * 设置父节点uuid
     *
     * @param parentuuid 父节点uuid
     */
    public void setParentuuid(String parentuuid) {
        this.parentuuid = parentuuid;
    }

    /**
     * 获取部门名称
     *
     * @return deptName - 部门名称
     */
    public String getDeptname() {
        return deptname;
    }

    /**
     * 设置部门名称
     *
     * @param deptname 部门名称
     */
    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    /**
     * 获取所属工地，公司，政务单位唯一标识
     *
     * @return deptUUID - 所属工地，公司，政务单位唯一标识
     */
    public String getDeptuuid() {
        return deptuuid;
    }

    /**
     * 设置所属工地，公司，政务单位唯一标识
     *
     * @param deptuuid 所属工地，公司，政务单位唯一标识
     */
    public void setDeptuuid(String deptuuid) {
        this.deptuuid = deptuuid;
    }

    /**
     * 获取0正常  1删除
     *
     * @return state - 0正常  1删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0正常  1删除
     *
     * @param state 0正常  1删除
     */
    public void setState(Integer state) {
        this.state = state;
    }
}