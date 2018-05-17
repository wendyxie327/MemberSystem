package com.member.account.dao;

import com.member.account.vo.UserAccount;
import org.apache.ibatis.annotations.Param;

public interface UserAccountDao {

    void insertUserAccount(UserAccount userAccount);

    UserAccount queryUserAccountById(@Param(value = "userId") String userId);

}
