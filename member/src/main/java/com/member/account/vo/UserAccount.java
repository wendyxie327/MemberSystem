package com.member.account.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户当前总金额
 */
public class UserAccount {

    private String userId;     // varchar(32) NOT NULL COMMENT '用户ID
    private BigDecimal totalConsume;     // decimal(12,2) DEFAULT NULL COMMENT '总收入（只加不减）
    private BigDecimal remainConsume;     // decimal(12,2) DEFAULT NULL COMMENT '用户剩余金额（= total_consume - withdrawing_consume - withdraw_consume = remain_consume上次 - withdrawing_consume  ）
    private BigDecimal withdrawConsume;     // decimal(12,2) DEFAULT NULL COMMENT '已提现金额（提款成功时更改该字段，只加不减）
    private BigDecimal withdrawingConsume;     // decimal(12,2) DEFAULT NULL COMMENT '正在提现的金额
    private Date createTime;     // datetime NOT NULL COMMENT '创建时间
    private Date updateTime;     // datetime NOT NULL COMMENT '更新时间

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalConsume() {
        return totalConsume;
    }

    public void setTotalConsume(BigDecimal totalConsume) {
        this.totalConsume = totalConsume;
    }

    public BigDecimal getRemainConsume() {
        return remainConsume;
    }

    public void setRemainConsume(BigDecimal remainConsume) {
        this.remainConsume = remainConsume;
    }

    public BigDecimal getWithdrawConsume() {
        return withdrawConsume;
    }

    public void setWithdrawConsume(BigDecimal withdrawConsume) {
        this.withdrawConsume = withdrawConsume;
    }

    public BigDecimal getWithdrawingConsume() {
        return withdrawingConsume;
    }

    public void setWithdrawingConsume(BigDecimal withdrawingConsume) {
        this.withdrawingConsume = withdrawingConsume;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
