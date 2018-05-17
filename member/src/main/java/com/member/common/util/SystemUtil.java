package com.member.common.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 本系统使用的基本工具
 */
public class SystemUtil {

    /**
     * 对密码进行处理，得到数据库中存储的密码
     * @param pwd
     * @return
     */
    public static String dealPwd2DB(String pwd){
        return getMD5Code(pwd);
    }

    /**
     * 返回MD5码
     * @param parStr 要编码的字符串
     * @return 返回值
     */
    public static String getMD5Code(String parStr) {
        return DigestUtils.md5Hex(parStr);
    }

    /**
     * 生成用户ID
     * @return  时间戳+6位随机数
     */
    public static String createUserId(){
        return DateUtil.getCurrentFullDateTimeString() + UniqId.getRandom(6);
    }


    /**
     * 检查人数是否符合推荐人阶段满员的条件
     * @param count 人员数量
     * @return  true满足，false不满足
     */
    public static boolean isEnoughRefereeUserCount(int count){
        ResPropertiesUtil resPropertiesUtil = new ResPropertiesUtil();
        String userEnoughNumsStr = resPropertiesUtil.getProperty("USER_ENOUGH_NUM");
        String[] userEnoughNums = userEnoughNumsStr.split("\\|");
        try {
            for (String numStr : userEnoughNums) {
                if (numStr != null && count == Integer.valueOf(numStr)){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据人数，获取用户奖励金额
     *  先查询奖励人数的下标，再根据下标查到对应的金额
     * @param count 人数
     * @return  奖励金额，单位元;
     */
    public static int getUserRewardIncome(int count){
        ResPropertiesUtil resPropertiesUtil = new ResPropertiesUtil();
        String userEnoughNumsStr = resPropertiesUtil.getProperty("USER_ENOUGH_NUM");
        String userEnoughIncomesStr = resPropertiesUtil.getProperty("USER_ENOUGH_INCOME");
        String[] userEnoughNums = userEnoughNumsStr.split("\\|");
        String[] userEnoughIncomes = userEnoughIncomesStr.split("\\|");
        // 从大到小比较，查询用户当前奖励金额是多少
        try {
            for (int i = 0; i < userEnoughNums.length; i++) {
                if (userEnoughNums[i] != null && count == Integer.valueOf(userEnoughNums[i])){
                    if (userEnoughIncomes.length > i){
                        return Integer.valueOf(userEnoughIncomes[i]);   // 返回对应金额
                    }
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
