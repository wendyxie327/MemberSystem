package com.member.user.service.impl;

import com.member.common.util.DateUtil;
import com.member.common.util.ResPropertiesUtil;
import com.member.common.util.SystemUtil;
import com.member.user.dao.UserRefereeInfoDao;
import com.member.user.service.UserRefereeInfoService;
import com.member.user.vo.UserRefereeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRefereeInfoServiceImpl implements UserRefereeInfoService {

    @Autowired
    UserRefereeInfoDao userRefereeInfoDao;

    @Override
    public void addRefereeCount(String userId, String areaName) {
        UserRefereeInfo userRefereeInfo = userRefereeInfoDao.queryUserRefereeById(userId);
        if (userRefereeInfo == null){
            // 生成该用户记录
            userRefereeInfo = new UserRefereeInfo();
            userRefereeInfo.setUserId(userId);
            userRefereeInfo.setNumUpdateTime(DateUtil.getNowDate());
            userRefereeInfo.setEnoughIncome(0);
            userRefereeInfo.setEnoughNum(0);

            // 在区域上加一
            if ("A".equals(areaName)){
                userRefereeInfo.setAreaA(1);
                userRefereeInfo.setAreaB(0);
            }else {
                userRefereeInfo.setAreaB(1);
                userRefereeInfo.setAreaA(0);
            }
            // 生成记录
            userRefereeInfoDao.insertUserReferee(userRefereeInfo);

        }else {
            userRefereeInfo.setNumUpdateTime(DateUtil.getNowDate());

            // 在区域上加一
            // +1的区域如果为6的倍数，并且，将两个区域进行比较，只有另一个区域大于等于+1区域时，才有可能两个同时满足清理空间的要求
            if ("A".equals(areaName)){
                userRefereeInfo.setAreaA( userRefereeInfo.getAreaA() + 1);
                // 检查该用户是否满员
                if ( SystemUtil.isEnoughRefereeUserCount(userRefereeInfo.getAreaA())
                        && userRefereeInfo.getAreaA() <= userRefereeInfo.getAreaB()){
                    // 当处于6，为整数时，需要清空时间
                    userRefereeInfo.setNumEnoughTime(DateUtil.getNowDate());
                    // 获取金额
                    userRefereeInfo.setEnoughNum(userRefereeInfo.getAreaA());
                    userRefereeInfo.setEnoughIncome(SystemUtil.getUserRewardIncome(userRefereeInfo.getAreaA()));
                }
            }else {
                userRefereeInfo.setAreaB( userRefereeInfo.getAreaB() + 1);
                if (SystemUtil.isEnoughRefereeUserCount(userRefereeInfo.getAreaB())
                        && userRefereeInfo.getAreaB() <= userRefereeInfo.getAreaA() ){
                    // 当处于6，为整数时，需要清空时间
                    userRefereeInfo.setNumEnoughTime(DateUtil.getNowDate());
                    // 获取金额
                    userRefereeInfo.setEnoughNum(userRefereeInfo.getAreaB());
                    userRefereeInfo.setEnoughIncome(SystemUtil.getUserRewardIncome(userRefereeInfo.getAreaB()));
                }
            }
            // 更新数据库
            userRefereeInfoDao.updateUserRefereeInfo(userRefereeInfo);
        }
    }
}
