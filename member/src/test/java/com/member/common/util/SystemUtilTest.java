package com.member.common.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class SystemUtilTest {

    @Test
    public void isEnoughRefereeUserCount() {
    }

    @Test
    public void getUserRewardIncome() {
        int num = SystemUtil.getUserRewardIncome(7000);
        System.out.println(num);
    }
}