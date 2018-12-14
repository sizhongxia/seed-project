package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yeetong_news")
public class YeeTongNews {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 唯一主键
     */
    @Column(name = "unique_id")
    private String uniqueId;

    /**
     * 新闻标题
     */
    @Column(name = "news_title")
    private String newsTitle;

    /**
     * 发布状态，已发布Y, 未发布N
     */
    @Column(name = "release_state")
    private String releaseState;

    /**
     * 发布时间
     */
    @Column(name = "release_time")
    private Date releaseTime;

    /**
     * 新闻来源
     */
    @Column(name = "news_origin")
    private String newsOrigin;

    /**
     * 新闻封面图（430x250）
     */
    @Column(name = "news_cover_pic")
    private String newsCoverPic;

    /**
     * 新闻摘要
     */
    @Column(name = "news_abstract")
    private String newsAbstract;

    /**
     * 新闻关键词
     */
    @Column(name = "news_keywords")
    private String newsKeywords;

    /**
     * 前一篇ID
     */
    @Column(name = "before_id")
    private String beforeId;

    /**
     * 后一篇ID
     */
    @Column(name = "after_id")
    private String afterId;

    /**
     * 访问量
     */
    @Column(name = "page_view_num")
    private Long pageViewNum;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 新闻内容
     */
    @Column(name = "news_content")
    private String newsContent;

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
     * 获取唯一主键
     *
     * @return unique_id - 唯一主键
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * 设置唯一主键
     *
     * @param uniqueId 唯一主键
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * 获取新闻标题
     *
     * @return news_title - 新闻标题
     */
    public String getNewsTitle() {
        return newsTitle;
    }

    /**
     * 设置新闻标题
     *
     * @param newsTitle 新闻标题
     */
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    /**
     * 获取发布状态，已发布Y, 未发布N
     *
     * @return release_state - 发布状态，已发布Y, 未发布N
     */
    public String getReleaseState() {
        return releaseState;
    }

    /**
     * 设置发布状态，已发布Y, 未发布N
     *
     * @param releaseState 发布状态，已发布Y, 未发布N
     */
    public void setReleaseState(String releaseState) {
        this.releaseState = releaseState;
    }

    /**
     * 获取发布时间
     *
     * @return release_time - 发布时间
     */
    public Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * 设置发布时间
     *
     * @param releaseTime 发布时间
     */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * 获取新闻来源
     *
     * @return news_origin - 新闻来源
     */
    public String getNewsOrigin() {
        return newsOrigin;
    }

    /**
     * 设置新闻来源
     *
     * @param newsOrigin 新闻来源
     */
    public void setNewsOrigin(String newsOrigin) {
        this.newsOrigin = newsOrigin;
    }

    /**
     * 获取新闻封面图（430x250）
     *
     * @return news_cover_pic - 新闻封面图（430x250）
     */
    public String getNewsCoverPic() {
        return newsCoverPic;
    }

    /**
     * 设置新闻封面图（430x250）
     *
     * @param newsCoverPic 新闻封面图（430x250）
     */
    public void setNewsCoverPic(String newsCoverPic) {
        this.newsCoverPic = newsCoverPic;
    }

    /**
     * 获取新闻摘要
     *
     * @return news_abstract - 新闻摘要
     */
    public String getNewsAbstract() {
        return newsAbstract;
    }

    /**
     * 设置新闻摘要
     *
     * @param newsAbstract 新闻摘要
     */
    public void setNewsAbstract(String newsAbstract) {
        this.newsAbstract = newsAbstract;
    }

    /**
     * 获取新闻关键词
     *
     * @return news_keywords - 新闻关键词
     */
    public String getNewsKeywords() {
        return newsKeywords;
    }

    /**
     * 设置新闻关键词
     *
     * @param newsKeywords 新闻关键词
     */
    public void setNewsKeywords(String newsKeywords) {
        this.newsKeywords = newsKeywords;
    }

    /**
     * 获取前一篇ID
     *
     * @return before_id - 前一篇ID
     */
    public String getBeforeId() {
        return beforeId;
    }

    /**
     * 设置前一篇ID
     *
     * @param beforeId 前一篇ID
     */
    public void setBeforeId(String beforeId) {
        this.beforeId = beforeId;
    }

    /**
     * 获取后一篇ID
     *
     * @return after_id - 后一篇ID
     */
    public String getAfterId() {
        return afterId;
    }

    /**
     * 设置后一篇ID
     *
     * @param afterId 后一篇ID
     */
    public void setAfterId(String afterId) {
        this.afterId = afterId;
    }

    /**
     * 获取访问量
     *
     * @return page_view_num - 访问量
     */
    public Long getPageViewNum() {
        return pageViewNum;
    }

    /**
     * 设置访问量
     *
     * @param pageViewNum 访问量
     */
    public void setPageViewNum(Long pageViewNum) {
        this.pageViewNum = pageViewNum;
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
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取新闻内容
     *
     * @return news_content - 新闻内容
     */
    public String getNewsContent() {
        return newsContent;
    }

    /**
     * 设置新闻内容
     *
     * @param newsContent 新闻内容
     */
    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
}