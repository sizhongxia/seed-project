package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yeetong_news_chosen")
public class YeeTongNewsChosen {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 精选新闻ID
     */
    @Column(name = "news_id")
    private String newsId;

    /**
     * 新闻封面图（620x413）
     */
    @Column(name = "news_cover_pic")
    private String newsCoverPic;

    /**
     * 启用状态
     */
    @Column(name = "use_state")
    private String useState;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取精选新闻ID
     *
     * @return news_id - 精选新闻ID
     */
    public String getNewsId() {
        return newsId;
    }

    /**
     * 设置精选新闻ID
     *
     * @param newsId 精选新闻ID
     */
    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    /**
     * 获取新闻封面图（620x413）
     *
     * @return news_cover_pic - 新闻封面图（620x413）
     */
    public String getNewsCoverPic() {
        return newsCoverPic;
    }

    /**
     * 设置新闻封面图（620x413）
     *
     * @param newsCoverPic 新闻封面图（620x413）
     */
    public void setNewsCoverPic(String newsCoverPic) {
        this.newsCoverPic = newsCoverPic;
    }

    /**
     * 获取启用状态
     *
     * @return use_state - 启用状态
     */
    public String getUseState() {
        return useState;
    }

    /**
     * 设置启用状态
     *
     * @param useState 启用状态
     */
    public void setUseState(String useState) {
        this.useState = useState;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}