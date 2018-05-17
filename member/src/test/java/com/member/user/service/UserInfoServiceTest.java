package com.member.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"classpath:config/spring/spring-dao.xml","classpath:config/spring/spring-service.xml"}
)
public class UserInfoServiceTest {

    @Autowired
    UserInfoService userInfoService;

    @Test
    public void queryRefereeUserList() {
        userInfoService.queryRefereeAllUserList("201805081957027571788");
    }

    @Test
    public void queryRefereeAllUserList() {
    }
}