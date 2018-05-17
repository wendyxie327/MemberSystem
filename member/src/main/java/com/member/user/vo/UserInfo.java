package com.member.user.vo;

import java.util.Date;

/**
 * 用户基本信息表
 */
public class UserInfo {

    private String userId;     // varchar(32) NOT NULL COMMENT '用户ID，系统自动生成
    private String userCode;     // varchar(32) NOT NULL COMMENT '用户编号，此编号唯一，并且可由用户来填写
    private String rodeId;     // varchar(2) NOT NULL COMMENT '用户角色编号， 1老板，2区域经理，3普通会员
    private String refereeUserId;     // varchar(32) NOT NULL COMMENT '推荐人ID
    private String higherUserId;     // varchar(32) DEFAULT NULL COMMENT '上一级的用户ID
    private String higherUserCode;     // varchar(32) DEFAULT NULL COMMENT '上一级人员用户编号，用于显示
    private String higherRodeId;     // varchar(2) DEFAULT NULL COMMENT '上一级人员角色ID
    private String areaName; // 所属区域A、B
    private String loginPwd;     // varchar(128) NOT NULL COMMENT '登录密码，md5加密
    private String verifyStatus;     // VARCHAR(2) NOT NULL DEFAULT '0' COMMENT '认证具体状态：0老板，1等待成为普通会员，2成为普通会员，3等待成为区域经理，4成为区域经理，99拒绝
    private Date createTime;     // datetime DEFAULT NULL COMMENT '注册时间
    private Date verifyTime;     // datetime DEFAULT NULL COMMENT '提出认证时间：只有当认证状态为1、3时，此值有效
    private Date updateTime;     // datetime DEFAULT NULL COMMENT '最近更新时间
    private String realUserName;     // varchar(20) DEFAULT NULL COMMENT '用户真实姓名
    private String identityId;     // varchar(20) DEFAULT NULL COMMENT '身份证号
    private String addressProvince;     // varchar(20) DEFAULT NULL COMMENT '省份
    private String addressCity;     // varchar(20) DEFAULT NULL COMMENT '市
    private String addressArea;     // varchar(20) DEFAULT NULL COMMENT '区
    private String addressDetail;     // varchar(100) DEFAULT NULL COMMENT '详细地址
    private String mobileNum;     // varchar(20) DEFAULT NULL COMMENT '联系电话
    private String qq;     // varchar(20) DEFAULT NULL,
    private String wx;
    private String bankName;     // varchar(50) DEFAULT NULL COMMENT '开户行账号
    private String bankCode;     // varchar(50) DEFAULT NULL COMMENT '开户行账号
    private String bankUserName;     // varchar(50) DEFAULT NULL COMMENT '开户行用户姓名
    private String bankAddress;     // varchar(50) DEFAULT NULL COMMENT '分行名称

    private String refereeUserCode; // 推荐人编码

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }

    public String getRefereeUserCode() {
        return refereeUserCode;
    }

    public void setRefereeUserCode(String refereeUserCode) {
        this.refereeUserCode = refereeUserCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

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

    public String getRodeId() {
        return rodeId;
    }

    public void setRodeId(String rodeId) {
        this.rodeId = rodeId;
    }

    public String getRefereeUserId() {
        return refereeUserId;
    }

    public void setRefereeUserId(String refereeUserId) {
        this.refereeUserId = refereeUserId;
    }

    public String getHigherUserId() {
        return higherUserId;
    }

    public void setHigherUserId(String higherUserId) {
        this.higherUserId = higherUserId;
    }

    public String getHigherUserCode() {
        return higherUserCode;
    }

    public void setHigherUserCode(String higherUserCode) {
        this.higherUserCode = higherUserCode;
    }

    public String getHigherRodeId() {
        return higherRodeId;
    }

    public void setHigherRodeId(String higherRodeId) {
        this.higherRodeId = higherRodeId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRealUserName() {
        return realUserName;
    }

    public void setRealUserName(String realUserName) {
        this.realUserName = realUserName;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }
}
