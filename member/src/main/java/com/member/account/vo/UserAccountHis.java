package com.member.account.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户账号变更记录
 *  包含：奖金发放记录
 */
public class UserAccountHis {
    private String userId;     // varchar(32) NOT NULL COMMENT '用户ID
    private String userCode;     // varchar(32) NOT NULL COMMENT '用户编号
    private String changeType;     // varchar(2) NOT NULL COMMENT '账户金额更改类型：1奖金发放、2提现（提现成功后，生成此纪录）
    private BigDecimal currentConsume;     // decimal(12,2) NOT NULL COMMENT '充值前金额，单位元
    private BigDecimal changingConsume;     // decimal(12,2) NOT NULL COMMENT '充值金额 ，单位元(充值，正数；消费，负数)
    private BigDecimal changedConsume;     // decimal(12,2) NOT NULL COMMENT '充值后金额，单位元
    private String thirdSerialNo;     // varchar(20) NOT NULL COMMENT '此记录对应的交易流水号，2提现时使用
    private Date createTime;     // datetime NOT NULL COMMENT '创建时间

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public BigDecimal getCurrentConsume() {
        return currentConsume;
    }

    public void setCurrentConsume(BigDecimal currentConsume) {
        this.currentConsume = currentConsume;
    }

    public BigDecimal getChangingConsume() {
        return changingConsume;
    }

    public void setChangingConsume(BigDecimal changingConsume) {
        this.changingConsume = changingConsume;
    }

    public BigDecimal getChangedConsume() {
        return changedConsume;
    }

    public void setChangedConsume(BigDecimal changedConsume) {
        this.changedConsume = changedConsume;
    }

    public String getThirdSerialNo() {
        return thirdSerialNo;
    }

    public void setThirdSerialNo(String thirdSerialNo) {
        this.thirdSerialNo = thirdSerialNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
