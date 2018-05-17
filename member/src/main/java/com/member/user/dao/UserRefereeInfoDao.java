package com.member.user.dao;

import com.member.user.vo.UserRefereeInfo;
import org.apache.ibatis.annotations.Param;

public interface UserRefereeInfoDao {

    /**
     * 保存用户信息
     * @param userInfo 用户信息
     */
    void insertUserReferee(UserRefereeInfo userInfo);

    /**
     * 更新用户基本信息，
     *  ** 依据userId 进行更新
     *
     * @param userInfo  用户基本信息
     */
    void updateUserRefereeInfo(UserRefereeInfo userInfo);

    /**
     * 查询用户推荐数基本信息
     * @param userId
     * @return
     */
    UserRefereeInfo queryUserRefereeById(@Param(value = "userId") String userId);


}
