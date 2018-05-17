package com.member.common.util;

public class ConfigUtil {

    /** 用户角色编号， 1老板，2区域经理，3普通会员 */
    public static final String USER_RODE_ID_BOSS = "1";
    public static final String USER_RODE_ID_AREA = "2";
    public static final String USER_RODE_ID_COMMON = "3";

    /** 认证具体状态：0老板，1等待成为普通会员，2成为普通会员，3等待成为区域经理，4成为区域经理 */
    public static final String USER_VERIFY_STATUS_BOSS = "0";
    public static final String USER_VERIFY_STATUS_WAIT_COMMON = "1";
    public static final String USER_VERIFY_STATUS_COMMON = "2";
    public static final String USER_VERIFY_STATUS_WAIT_AREA = "3";
    public static final String USER_VERIFY_STATUS_AREA = "4";

}
