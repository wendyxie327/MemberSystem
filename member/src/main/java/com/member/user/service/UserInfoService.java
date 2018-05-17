package com.member.user.service;

import com.member.user.dto.RefereedUserDto;
import com.member.user.vo.UserInfo;

import java.util.List;

public interface UserInfoService {

    /**
     * 注册用户
     * @param userInfo  用户基本信息
     */
    public void saveUserInfo(UserInfo userInfo);

    /**
     * 根据用户编号查询用户信息
     * @param userCode  用户编号
     * @return  用户列表
     */
    public UserInfo queryUserInfoByCode(String userCode);

    /**
     * 根据用户Id查询用户信息
     * @param userId  用户编号
     * @return  用户列表
     */
    public UserInfo queryUserInfoById(String userId);

    /**
     * 查询需要审核的用户列表
     * @return  需要被审核
     */
    public List<UserInfo> queryNeedVerifyUserList();

    /**
     * 更新用户认证状态
     * @param userId    用户ID
     */
    void updateUserVerifyStatus(String userId);

    /**
     * 查询用户直接推荐的人员列表
     * @param userId 用户Id
     * @return  直接推荐的人员列表
     */
    List<UserInfo> queryRefereeUserList(String userId);

    /**
     * 分级查询用户名下所有的推荐人列表
     *  需要一层层都查询出来
     * @param userId    用户ID
     * @return  全部推荐会员列表
     */
    List<RefereedUserDto> queryRefereeAllUserList(String userId);

    /**
     * 更新用户基本信息
     * @param userId    需要更新的用户ID
     * @param userInfo  用户信息
     */
    void updateUserInfoBasic(String userId, UserInfo userInfo);

}
