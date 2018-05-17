package com.member.user.vo;

import java.util.Date;

/**
 * 用户推荐人数表
 */
public class UserRefereeInfo {

    private String userId;     // varchar(32) NOT NULL COMMENT '用户ID
    private Integer areaA;     // int(11) NOT NULL COMMENT 'A区总人数（包含间接、直接）
    private Integer areaB;     // int(11) NOT NULL COMMENT 'B区总人数（包含间接、直接）
    private Date numUpdateTime;     // datetime NOT NULL COMMENT '人员总数最近更新时间（会员审核通过，进行更新）
    private Date numEnoughTime;     // datetime NOT NULL COMMENT '最近人员满6的倍数的时间（会员审核通过，进行更新）
    private Date accountUpdateTime;     // datetime NOT NULL COMMENT '奖金最近发放时间(每天汇总)
    private Integer enoughNum;  // 符合奖金水平-人数
    private Integer enoughIncome;   //符合奖金水平-奖金

    public Integer getEnoughNum() {
        return enoughNum;
    }

    public void setEnoughNum(Integer enoughNum) {
        this.enoughNum = enoughNum;
    }

    public Integer getEnoughIncome() {
        return enoughIncome;
    }

    public void setEnoughIncome(Integer enoughIncome) {
        this.enoughIncome = enoughIncome;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAreaA() {
        return areaA;
    }

    public void setAreaA(Integer areaA) {
        this.areaA = areaA;
    }

    public Integer getAreaB() {
        return areaB;
    }

    public void setAreaB(Integer areaB) {
        this.areaB = areaB;
    }

    public Date getNumUpdateTime() {
        return numUpdateTime;
    }

    public void setNumUpdateTime(Date numUpdateTime) {
        this.numUpdateTime = numUpdateTime;
    }

    public Date getNumEnoughTime() {
        return numEnoughTime;
    }

    public void setNumEnoughTime(Date numEnoughTime) {
        this.numEnoughTime = numEnoughTime;
    }

    public Date getAccountUpdateTime() {
        return accountUpdateTime;
    }

    public void setAccountUpdateTime(Date accountUpdateTime) {
        this.accountUpdateTime = accountUpdateTime;
    }
}
