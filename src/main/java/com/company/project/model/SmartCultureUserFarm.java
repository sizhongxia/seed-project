package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "smart_culture_user_farm")
public class SmartCultureUserFarm {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 记录ID
     */
    @Column(name = "res_id")
    private String resId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    @Column(name = "farm_id")
    private String farmId;

    /**
     * 用户身份（admin, manager, visitor）
     */
    private String identity;

    /**
     * 申请时间
     */
    @Column(name = "apply_at")
    private Date applyAt;

    /**
     * 申请备注
     */
    @Column(name = "apply_remark")
    private String applyRemark;

    /**
     * 申请状态
     */
    @Column(name = "apply_state")
    private String applyState;

    /**
     * 修改时间
     */
    @Column(name = "handle_user_id")
    private String handleUserId;

    /**
     * 处理时间
     */
    @Column(name = "handle_at")
    private Date handleAt;

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
     * 获取记录ID
     *
     * @return res_id - 记录ID
     */
    public String getResId() {
        return resId;
    }

    /**
     * 设置记录ID
     *
     * @param resId 记录ID
     */
    public void setResId(String resId) {
        this.resId = resId;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return farm_id
     */
    public String getFarmId() {
        return farmId;
    }

    /**
     * @param farmId
     */
    public void setFarmId(String farmId) {
        this.farmId = farmId;
    }

    /**
     * 获取用户身份（admin, manager, visitor）
     *
     * @return identity - 用户身份（admin, manager, visitor）
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * 设置用户身份（admin, manager, visitor）
     *
     * @param identity 用户身份（admin, manager, visitor）
     */
    public void setIdentity(String identity) {
        this.identity = identity;
    }

    /**
     * 获取申请时间
     *
     * @return apply_at - 申请时间
     */
    public Date getApplyAt() {
        return applyAt;
    }

    /**
     * 设置申请时间
     *
     * @param applyAt 申请时间
     */
    public void setApplyAt(Date applyAt) {
        this.applyAt = applyAt;
    }

    /**
     * 获取申请备注
     *
     * @return apply_remark - 申请备注
     */
    public String getApplyRemark() {
        return applyRemark;
    }

    /**
     * 设置申请备注
     *
     * @param applyRemark 申请备注
     */
    public void setApplyRemark(String applyRemark) {
        this.applyRemark = applyRemark;
    }

    /**
     * 获取申请状态
     *
     * @return apply_state - 申请状态
     */
    public String getApplyState() {
        return applyState;
    }

    /**
     * 设置申请状态
     *
     * @param applyState 申请状态
     */
    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }

    /**
     * 获取修改时间
     *
     * @return handle_user_id - 修改时间
     */
    public String getHandleUserId() {
        return handleUserId;
    }

    /**
     * 设置修改时间
     *
     * @param handleUserId 修改时间
     */
    public void setHandleUserId(String handleUserId) {
        this.handleUserId = handleUserId;
    }

    /**
     * 获取处理时间
     *
     * @return handle_at - 处理时间
     */
    public Date getHandleAt() {
        return handleAt;
    }

    /**
     * 设置处理时间
     *
     * @param handleAt 处理时间
     */
    public void setHandleAt(Date handleAt) {
        this.handleAt = handleAt;
    }
}