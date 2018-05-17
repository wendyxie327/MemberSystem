package com.member.user.dto;

import com.member.user.vo.UserInfo;

import java.util.List;

/**
 * 被推荐的用户基本信息
 * 内涵回调的对象
 */
public class RefereedUserDto {

    private UserInfo refereeUserInfo; // 被推荐用户基本信息
    private List<RefereedUserDto> refereedUserDtos;   // 该用户下，推荐用户基本信息

    public UserInfo getRefereeUserInfo() {
        return refereeUserInfo;
    }

    public void setRefereeUserInfo(UserInfo refereeUserInfo) {
        this.refereeUserInfo = refereeUserInfo;
    }

    public List<RefereedUserDto> getRefereedUserDtos() {
        return refereedUserDtos;
    }

    public void setRefereedUserDtos(List<RefereedUserDto> refereedUserDtos) {
        this.refereedUserDtos = refereedUserDtos;
    }
}
