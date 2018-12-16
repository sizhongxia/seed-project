package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "yeetong_product")
public class YeeTongProduct {
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
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 产品简称
     */
    @Column(name = "product_short_name")
    private String productShortName;

    /**
     * 产品封面图
     */
    @Column(name = "product_cover_pic")
    private String productCoverPic;

    /**
     * 发布状态，已发布Y, 未发布N
     */
    @Column(name = "release_state")
    private String releaseState;

    /**
     * 排序权重
     */
    @Column(name = "weight_num")
    private Integer weightNum;

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
     * 产品介绍
     */
    @Column(name = "product_introduce")
    private String productIntroduce;

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
     * 获取产品名称
     *
     * @return product_name - 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取产品简称
     *
     * @return product_short_name - 产品简称
     */
    public String getProductShortName() {
        return productShortName;
    }

    /**
     * 设置产品简称
     *
     * @param productShortName 产品简称
     */
    public void setProductShortName(String productShortName) {
        this.productShortName = productShortName;
    }

    /**
     * 获取产品封面图
     *
     * @return product_cover_pic - 产品封面图
     */
    public String getProductCoverPic() {
        return productCoverPic;
    }

    /**
     * 设置产品封面图
     *
     * @param productCoverPic 产品封面图
     */
    public void setProductCoverPic(String productCoverPic) {
        this.productCoverPic = productCoverPic;
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
     * 获取排序权重
     *
     * @return weight_num - 排序权重
     */
    public Integer getWeightNum() {
        return weightNum;
    }

    /**
     * 设置排序权重
     *
     * @param weightNum 排序权重
     */
    public void setWeightNum(Integer weightNum) {
        this.weightNum = weightNum;
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
     * 获取产品介绍
     *
     * @return product_introduce - 产品介绍
     */
    public String getProductIntroduce() {
        return productIntroduce;
    }

    /**
     * 设置产品介绍
     *
     * @param productIntroduce 产品介绍
     */
    public void setProductIntroduce(String productIntroduce) {
        this.productIntroduce = productIntroduce;
    }
}