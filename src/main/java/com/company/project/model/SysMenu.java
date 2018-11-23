package com.company.project.model;

import javax.persistence.*;

@Table(name = "sys_menu")
public class SysMenu {
    /**
     * 排序id
     */
    @Id
    private Integer id;

    /**
     * 数据id
     */
    private String uuid;

    /**
     * 父模块id
     */
    @Column(name = "parentId")
    private String parentid;

    /**
     * 模块名称
     */
    private String name;

    /**
     * 模块路径
     */
    private String url;

    /**
     * 模块路径
     */
    private String path;

    /**
     * 图标
     */
    private String icon;

    /**
     * 1工地模块菜单
     */
    private Integer type;

    /**
     * 排序值
     */
    @Column(name = "sortNum")
    private Integer sortnum;

    /**
     * 0正常 1 删除
     */
    private Integer state;

    /**
     * 获取排序id
     *
     * @return id - 排序id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置排序id
     *
     * @param id 排序id
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
     * 获取父模块id
     *
     * @return parentId - 父模块id
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * 设置父模块id
     *
     * @param parentid 父模块id
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    /**
     * 获取模块名称
     *
     * @return name - 模块名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置模块名称
     *
     * @param name 模块名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取模块路径
     *
     * @return url - 模块路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置模块路径
     *
     * @param url 模块路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取模块路径
     *
     * @return path - 模块路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置模块路径
     *
     * @param path 模块路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取图标
     *
     * @return icon - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取1工地模块菜单
     *
     * @return type - 1工地模块菜单
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1工地模块菜单
     *
     * @param type 1工地模块菜单
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取排序值
     *
     * @return sortNum - 排序值
     */
    public Integer getSortnum() {
        return sortnum;
    }

    /**
     * 设置排序值
     *
     * @param sortnum 排序值
     */
    public void setSortnum(Integer sortnum) {
        this.sortnum = sortnum;
    }

    /**
     * 获取0正常 1 删除
     *
     * @return state - 0正常 1 删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0正常 1 删除
     *
     * @param state 0正常 1 删除
     */
    public void setState(Integer state) {
        this.state = state;
    }
}