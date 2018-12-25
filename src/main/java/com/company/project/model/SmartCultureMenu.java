package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "smart_culture_menu")
public class SmartCultureMenu {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 父级ID
     */
    private Long pid;

    /**
     * 菜单名称
     */
    @Column(name = "menu_name")
    private String menuName;

    /**
     * 菜单访问Key
     */
    @Column(name = "menu_access_key")
    private String menuAccessKey;

    /**
     * 排序值
     */
    @Column(name = "sort_num")
    private Integer sortNum;

    /**
     * 菜单备注
     */
    private String remark;

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
     * 获取父级ID
     *
     * @return pid - 父级ID
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置父级ID
     *
     * @param pid 父级ID
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取菜单名称
     *
     * @return menu_name - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取菜单访问Key
     *
     * @return menu_access_key - 菜单访问Key
     */
    public String getMenuAccessKey() {
        return menuAccessKey;
    }

    /**
     * 设置菜单访问Key
     *
     * @param menuAccessKey 菜单访问Key
     */
    public void setMenuAccessKey(String menuAccessKey) {
        this.menuAccessKey = menuAccessKey;
    }

    /**
     * 获取排序值
     *
     * @return sort_num - 排序值
     */
    public Integer getSortNum() {
        return sortNum;
    }

    /**
     * 设置排序值
     *
     * @param sortNum 排序值
     */
    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    /**
     * 获取菜单备注
     *
     * @return remark - 菜单备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置菜单备注
     *
     * @param remark 菜单备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
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