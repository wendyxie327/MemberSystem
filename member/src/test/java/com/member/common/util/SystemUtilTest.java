package com.member.common.util;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

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


    @Test
    public void test(){
        double d = 150515032.83;
        double s = 1.5051503283E8;
        System.out.println(d);
        System.out.println(s);

        BigDecimal dNumber = BigDecimal.valueOf(d);
        System.out.println("dNumber.toPlainString()= " + dNumber.toPlainString());
        System.out.println("dNumber.toString()= " + dNumber.toString());

        BigDecimal sNumber = BigDecimal.valueOf(s);
        System.out.println("sNumber.toPlainString()= " + sNumber.toPlainString());
        System.out.println("sNumber.toString()= " + sNumber.toString());

        BigDecimal dNumber1 = new BigDecimal(d );
        System.out.println("dNumber1.toPlainString()= " + dNumber1.toPlainString());
        System.out.println("dNumber1.toString()= " + dNumber1.toString());

    }
}