package com.member.user.dao;

import com.member.user.vo.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserInfoDao {

    /**
     * 保存用户信息
     * @param userInfo 用户信息
     */
    public void insertUser(UserInfo userInfo);

    /**
     * 根据用户编号，查询用户信息
     * @param userCode  用户编号
     * @return  用户信息
     */
    public UserInfo queryUserInfoByCode(@Param("userCode") String userCode);


    /**
     * 根据用户编号，查询用户信息
     * @param userId  用户Id
     * @return  用户信息
     */
    public UserInfo queryUserInfoById(@Param("userId") String userId);

    /**
     * 查询需要审核的用户列表
     * @return  需要被审核
     */
    public List<UserInfo> queryNeedVerifyUserList();

    /**
     * 更新用户基本信息，
     *  ** 依据userId 进行更新
     *
     * @param userInfo  用户基本信息
     */
    void updateUserInfo(UserInfo userInfo);

    /**
     * 查询用户直接推荐的人员列表
     * @param refereeUserId 用户Id
     * @return  直接推荐的人员列表
     */
    List<UserInfo> queryRefereeUserList(@Param("refereeUserId") String refereeUserId);
}
