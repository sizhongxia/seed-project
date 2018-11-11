package com.company.project.model;

import javax.persistence.*;

@Table(name = "user_role")
public class UserRole {
    /**
     * 自增id  排序
     */
    @Id
    private Integer id;

    /**
     * 角色id
     */
    private String uuid;

    /**
     * 角色名称
     */
    @Column(name = "roleName")
    private String rolename;

    /**
     * 角色名称
     */
    @Column(name = "roleCode")
    private String rolecode;

    /**
     * 添加时间
     */
    @Column(name = "addTime")
    private Long addtime;

    /**
     * 修改时间
     */
    @Column(name = "updateTime")
    private Long updatetime;

    /**
     * 角色所属工地、企业、政务
     */
    @Column(name = "deptId")
    private String deptid;

    /**
     * 0 正常  1 删除
     */
    private Integer state;

    /**
     * 获取自增id  排序
     *
     * @return id - 自增id  排序
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增id  排序
     *
     * @param id 自增id  排序
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色id
     *
     * @return uuid - 角色id
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置角色id
     *
     * @param uuid 角色id
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取角色名称
     *
     * @return roleName - 角色名称
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * 设置角色名称
     *
     * @param rolename 角色名称
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * 获取角色名称
     *
     * @return roleCode - 角色名称
     */
    public String getRolecode() {
        return rolecode;
    }

    /**
     * 设置角色名称
     *
     * @param rolecode 角色名称
     */
    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
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
     * 获取修改时间
     *
     * @return updateTime - 修改时间
     */
    public Long getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置修改时间
     *
     * @param updatetime 修改时间
     */
    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取角色所属工地、企业、政务
     *
     * @return deptId - 角色所属工地、企业、政务
     */
    public String getDeptid() {
        return deptid;
    }

    /**
     * 设置角色所属工地、企业、政务
     *
     * @param deptid 角色所属工地、企业、政务
     */
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    /**
     * 获取0 正常  1 删除
     *
     * @return state - 0 正常  1 删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0 正常  1 删除
     *
     * @param state 0 正常  1 删除
     */
    public void setState(Integer state) {
        this.state = state;
    }
}