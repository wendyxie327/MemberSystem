package com.member.user.service.impl;

import com.member.common.dto.ControllerResult;
import com.member.common.util.BeanUtil;
import com.member.common.util.ConfigUtil;
import com.member.common.util.DateUtil;
import com.member.common.util.SystemUtil;
import com.member.user.dao.UserInfoDao;
import com.member.user.dto.RefereedUserDto;
import com.member.user.service.UserInfoService;
import com.member.user.service.UserRefereeInfoService;
import com.member.user.vo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserRefereeInfoService userRefereeInfoService;

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        userInfo.setUserId(SystemUtil.createUserId());
        userInfo.setRodeId(ConfigUtil.USER_RODE_ID_COMMON);
        userInfo.setVerifyStatus(ConfigUtil.USER_VERIFY_STATUS_WAIT_COMMON);
        userInfo.setCreateTime(DateUtil.getNowDate());
        userInfo.setUpdateTime(DateUtil.getNowDate());
        userInfo.setVerifyTime(DateUtil.getNowDate());
        userInfo.setLoginPwd(SystemUtil.dealPwd2DB(userInfo.getLoginPwd())); // 对密码进行加密

        userInfoDao.insertUser(userInfo);
    }

    @Override
    public UserInfo queryUserInfoByCode(String userCode) {
        return userInfoDao.queryUserInfoByCode(userCode);
    }

    @Override
    public UserInfo queryUserInfoById(String userId) {
        return userInfoDao.queryUserInfoById(userId);
    }

    @Override
    public List<UserInfo> queryNeedVerifyUserList() {
        return userInfoDao.queryNeedVerifyUserList();
    }

    @Override
    public void updateUserVerifyStatus(String userId) {
        // 更新内容： 认证状态，最近更新时间，认证时间（此时认证时间为认证成功的时间）
        UserInfo userInfo = userInfoDao.queryUserInfoById(userId);
        if (userInfo == null) return;

        // 1. 将审核状态改为已审核
        if (ConfigUtil.USER_VERIFY_STATUS_WAIT_COMMON.equals(userInfo.getVerifyStatus())) {
            userInfo.setVerifyStatus(ConfigUtil.USER_VERIFY_STATUS_COMMON); // 成为普通会员

            // 4. TODO 在此用户推荐人的推荐人数上+1， 此处需要循环查询所有上级
            List<String> AUserIdList = new ArrayList<>();
            List<String> BUserIdList = new ArrayList<>();
            // 查询出， 添加此用户后，全部需要在+1的推荐人
            queryTopUserRefereeList(userId, AUserIdList, BUserIdList);
            for (String A : AUserIdList) {
                userRefereeInfoService.addRefereeCount(A, "A");
            }
            for (String B : BUserIdList) {
                userRefereeInfoService.addRefereeCount(B, "B");
            }

        } else if (ConfigUtil.USER_VERIFY_STATUS_WAIT_AREA.equals(userInfo.getVerifyStatus())) {
            userInfo.setVerifyStatus(ConfigUtil.USER_VERIFY_STATUS_AREA); // 成为区域经理
        } else {
            return; // 用户处于审核状态
        }

        // 2. 设置更新基本信息
        userInfo.setVerifyTime(DateUtil.getNowDate());
        userInfo.setUpdateTime(DateUtil.getNowDate());

        // 3. 更新数据库
        userInfoDao.updateUserInfo(userInfo);

    }

    @Override
    public List<UserInfo> queryRefereeUserList(String userId) {
        return userInfoDao.queryRefereeUserList(userId);
    }

    @Override
    public List<RefereedUserDto> queryRefereeAllUserList(String userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        RefereedUserDto refereedUserDto = new RefereedUserDto();
        refereedUserDto.setRefereeUserInfo(userInfo);

        List<RefereedUserDto> refereedUserList = queryRefereeUsers(refereedUserDto);
        return refereedUserList;
    }

    @Override
    public void updateUserInfoBasic(String userId, UserInfo userInfo) {
        // 获取用户当前信息
        UserInfo dbUserInfo = userInfoDao.queryUserInfoById(userId);
        if (dbUserInfo == null) return;

        // 更新用户基本信息
        dbUserInfo.setUpdateTime(DateUtil.getNowDate());    // 更新时间
        dbUserInfo.setRealUserName(userInfo.getRealUserName());
        dbUserInfo.setIdentityId(userInfo.getIdentityId());
        dbUserInfo.setAddressProvince(userInfo.getAddressProvince());
        dbUserInfo.setAddressCity(userInfo.getAddressCity());
        dbUserInfo.setAddressArea(userInfo.getAddressArea());
        dbUserInfo.setAddressDetail(userInfo.getAddressDetail());
        dbUserInfo.setMobileNum(userInfo.getMobileNum());
        dbUserInfo.setWx(userInfo.getWx());
        dbUserInfo.setQq(userInfo.getQq());
        dbUserInfo.setBankAddress(userInfo.getBankAddress());
        dbUserInfo.setBankCode(userInfo.getBankCode());
        dbUserInfo.setBankName(userInfo.getBankName());
        dbUserInfo.setBankUserName(userInfo.getBankUserName());

        userInfoDao.updateUserInfo(dbUserInfo);

    }

    /**
     * 深层次查询推荐列表，包含直接推荐和间接推荐
     *
     * @param refereedUser 直推列表
     * @return 间接推荐列表
     */
    private List<RefereedUserDto> queryRefereeUsers(RefereedUserDto refereedUser) {
        if (BeanUtil.isBlank(refereedUser)) return null;

        // 单个用户下的间接推荐和直接推荐的用户列表
        List<RefereedUserDto> refereedUserDtos = new ArrayList<>();
        // 1. 查询用户下面的直推列表
        List<UserInfo> userInfos = userInfoDao.queryRefereeUserList(refereedUser.getRefereeUserInfo().getUserId());

        if (!BeanUtil.isBlank(userInfos)) {
            // 2. 循环查询直推列表会员下一层的直推会员列表
            for (UserInfo userInfo : userInfos) {
                RefereedUserDto refereedUserDto = new RefereedUserDto();
                refereedUserDto.setRefereeUserInfo(userInfo);
                // 根据单个查询出的推荐人列表，回调本函数，查询这些推荐人列表下的直推列表
                List<RefereedUserDto> refereedUserQueryDeep = queryRefereeUsers(refereedUserDto); // 多次查询后的人员列表

                refereedUserDto.setRefereedUserDtos(refereedUserQueryDeep);
                refereedUserDtos.add(refereedUserDto);
            }
        }

        return refereedUserDtos;
    }


    /**
     * 根据某个用户，查询推荐他的人的用户ID，一层层向上回调查询
     *
     * @param userId    用户
     */
    private void queryTopUserRefereeList(String userId, List<String> AUserIds, List<String> BUserIds){
        if (AUserIds == null || BUserIds == null || userId == null) return;

        // 查询该用户的推荐人
        UserInfo userInfo = userInfoDao.queryUserInfoById(userId);
        // 如果该用户没有推荐人，则结束
        if (userInfo == null || userInfo.getRefereeUserId() == null) return;
        // 如果此用户为A区，则放置到AList;反之放到BList
        if ("A".equals(userInfo.getAreaName())){
            AUserIds.add(userInfo.getRefereeUserId());
        }else if ("B".equals(userInfo.getAreaName())){
            BUserIds.add(userInfo.getRefereeUserId());
        }else {
            logger.error("未找到该用户分区：userId = "+ userInfo.getUserId() + ", 分区= "+ userInfo.getAreaName() );
        }
    }
}
