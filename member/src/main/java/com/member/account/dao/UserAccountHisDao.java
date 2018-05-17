package com.member.account.dao;

import com.member.account.vo.UserAccountHis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAccountHisDao {

    void insertUserAccountHis(UserAccountHis userAccount);

    List<UserAccountHis> queryHisById(@Param(value = "userId") String userId);

    List<UserAccountHis> queryHisByType(@Param(value = "userId") String userId, @Param(value = "changeType") String changeType);

}
