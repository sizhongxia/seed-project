package com.company.project.model;

import javax.persistence.*;

@Table(name = "business_memorabilia_img")
public class BusinessMemorabiliaImg {
    /**
     * 主键ID
     */
    @Id
    private Integer id;

    /**
     * business_memorabilia表数据id，可能会有多个
     */
    private String uuid;

    /**
     * 排序
     */
    @Column(name = "sortNum")
    private Integer sortnum;

    /**
     * 图片地址
     */
    private String src;

    private String title;

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
     * 0正常，1 删除
     */
    private Integer state;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取business_memorabilia表数据id，可能会有多个
     *
     * @return uuid - business_memorabilia表数据id，可能会有多个
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置business_memorabilia表数据id，可能会有多个
     *
     * @param uuid business_memorabilia表数据id，可能会有多个
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取排序
     *
     * @return sortNum - 排序
     */
    public Integer getSortnum() {
        return sortnum;
    }

    /**
     * 设置排序
     *
     * @param sortnum 排序
     */
    public void setSortnum(Integer sortnum) {
        this.sortnum = sortnum;
    }

    /**
     * 获取图片地址
     *
     * @return src - 图片地址
     */
    public String getSrc() {
        return src;
    }

    /**
     * 设置图片地址
     *
     * @param src 图片地址
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
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
     * 获取0正常，1 删除
     *
     * @return state - 0正常，1 删除
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0正常，1 删除
     *
     * @param state 0正常，1 删除
     */
    public void setState(Integer state) {
        this.state = state;
    }
}