package com.member.user.service;


public interface UserRefereeInfoService {

    /**
     * 向区域中添加一条记录
     * @param userId
     * @param areaName
     */
    void addRefereeCount(String userId,  String areaName);
}
