package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "business_memorabilia")
public class BusinessMemorabilia {
    /**
     * 排序值自增id
     */
    @Id
    private Integer id;

    /**
     * 数据id
     */
    private String uuid;

    /**
     * 工地UUid
     */
    @Column(name = "proUUid")
    private String prouuid;

    /**
     * 大事记标题
     */
    private String title;

    /**
     * 大记事内容
     */
    private String content;

    /**
     * 事件发生的事件
     */
    @Column(name = "happenTime")
    private Date happentime;

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
     * 获取排序值自增id
     *
     * @return id - 排序值自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置排序值自增id
     *
     * @param id 排序值自增id
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
     * 获取工地UUid
     *
     * @return proUUid - 工地UUid
     */
    public String getProuuid() {
        return prouuid;
    }

    /**
     * 设置工地UUid
     *
     * @param prouuid 工地UUid
     */
    public void setProuuid(String prouuid) {
        this.prouuid = prouuid;
    }

    /**
     * 获取大事记标题
     *
     * @return title - 大事记标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置大事记标题
     *
     * @param title 大事记标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取大记事内容
     *
     * @return content - 大记事内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置大记事内容
     *
     * @param content 大记事内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取事件发生的事件
     *
     * @return happenTime - 事件发生的事件
     */
    public Date getHappentime() {
        return happentime;
    }

    /**
     * 设置事件发生的事件
     *
     * @param happentime 事件发生的事件
     */
    public void setHappentime(Date happentime) {
        this.happentime = happentime;
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