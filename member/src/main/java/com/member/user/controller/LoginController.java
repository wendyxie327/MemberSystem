package com.member.user.controller;

import com.member.common.dto.ControllerResult;
import com.member.common.util.BeanUtil;
import com.member.common.util.ConfigUtil;
import com.member.common.util.StringUtil;
import com.member.common.util.SystemUtil;
import com.member.user.service.UserInfoService;
import com.member.user.vo.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 不需要登陆用户登陆、退出、注册等功能
 */
@Controller
@RequestMapping("/restful/login")
public class LoginController {
    /**
     * 输出日志
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 退出登陆
     *
     * @param session   HttpSession
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public void logout(HttpSession session, ServletRequest request, ServletResponse response) {
        logger.debug("正在执行退出系统操作..");
//        // 清空 管理用户和控制台用户session
//        session.removeAttribute("userInfo");
//        // 销毁session
//        session.invalidate();

        Subject subject = SecurityUtils.getSubject();
        subject.getSession().removeAttribute("userInfo");
        subject.logout();

//        try {
//            request.getRequestDispatcher("/login").forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    /**
     * 登陆功能
     * @param userCode  用户编号
     * @param pwd   登陆密码
     * @return  json
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ControllerResult<?> login(@RequestParam("loginUsername") String userCode, @RequestParam("loginPassword") String pwd) {
        try {
            //1 检查信息是否完整
            if (userCode == null || userCode.length() == 0) {
                return new ControllerResult<>(false, "请填写用户编号");
            }
            if (pwd == null || pwd.length() == 0) {
                return new ControllerResult<>(false, "请填写登陆密码");
            }

            //2 将页面上传来的密码进行md5加密
            pwd = SystemUtil.dealPwd2DB(pwd);

            //创建subject实例
            Subject subject = SecurityUtils.getSubject();

            //判断当前用户是否登录
            if(!subject.isAuthenticated()){
                //将用户名及密码封装交个UsernamePasswordToken
                UsernamePasswordToken token = new UsernamePasswordToken(userCode,pwd );
                try {
                    subject.login(token);   // 进入CustomRealm中，进行登陆认证

                    UserInfo userInfo = (UserInfo)subject.getPrincipal();
                    logger.debug(userInfo.toString() );
                    // 4.检查用户是否已经通过审核
                    if (ConfigUtil.USER_VERIFY_STATUS_WAIT_COMMON.equals(userInfo.getVerifyStatus())){
                        subject.logout(); // 对于未审核的，需要退出
                        return new ControllerResult<>(false, "您的账户还在审核中...");
                    }
                    subject.getSession().setAttribute("userInfo", userInfo);

                } catch (IncorrectCredentialsException e) {
                    e.printStackTrace();
                    return new ControllerResult<>(false, "用户名或密码错误！");
                } catch (AuthenticationException e) {
                    e.printStackTrace();
                    return new ControllerResult<>(false, "验证不通过，无法登录！");
                }
            }
            return new ControllerResult<>(true, "登录成功！");
 //           //3 根据手机号和密码查询该用户是否存在
//            UserInfo userInfo = userInfoService.queryUserInfoByCode(userCode);
//            if (userInfo != null
//                    && userInfo.getLoginPwd() != null
//                    && userInfo.getLoginPwd().equals(pwd) ){
//
//                // 4.检查用户是否已经通过审核
//                if (ConfigUtil.USER_VERIFY_STATUS_WAIT_COMMON.equals(userInfo.getVerifyStatus())){
//                    return new ControllerResult<>(false, "您的账户还在审核中...");
//                }
//
//                //5. 将用户信息存入session
//                session.setAttribute("userInfo", userInfo);
//                return new ControllerResult<>(true, "登录成功！");
//            }else {
//                return new ControllerResult<>(false, "用户名或密码错误！");
//            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ControllerResult<>(false, "发生未知错误");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ControllerResult<?> register(@RequestBody UserInfo userInfo) {
        // 基本信息校验
        if (BeanUtil.isBlank(userInfo)) return new ControllerResult<>(false, "传入参数有误");


        // 检查推荐人编号是否存在
        if (!StringUtil.isBlank()){
            UserInfo refereeUserInfo = userInfoService.queryUserInfoByCode(userInfo.getRefereeUserCode());
            userInfo.setRefereeUserId(refereeUserInfo.getUserId());
            if (BeanUtil.isBlank(refereeUserInfo)){
                return new ControllerResult<>(false, "未找到该推荐人！请检查");
            }
        }

        // 检查区域经理是否存在
        //TODO 区域经理暂时指定为老板
//        String higherUserCode = userInfo.getHigherUserCode();
        String higherUserCode = "boss";
        if (!StringUtil.isBlank()){
            UserInfo higherUserInfo = userInfoService.queryUserInfoByCode(higherUserCode);
            if (higherUserInfo.getRodeId() == null || ConfigUtil.USER_RODE_ID_COMMON.equals(higherUserInfo.getRodeId())){
                return new ControllerResult<>(false, "这个人是普通会员，不能作为你的区域经理");
            }
            userInfo.setHigherUserId(higherUserInfo.getUserId());
            userInfo.setHigherRodeId(higherUserInfo.getRodeId());
            if (BeanUtil.isBlank(higherUserInfo)){
                return new ControllerResult<>(false, "未找到此区域经理！请检查");
            }
        }


        // 检查会员编号是否可以使用
        UserInfo checkUserInfo = userInfoService.queryUserInfoByCode(userInfo.getUserCode());
        if (!BeanUtil.isBlank(checkUserInfo)) {
            return new ControllerResult<>(false, "会员编号已被使用，请进行更换");
        }

        try {
            // 保存用户信息
            userInfoService.saveUserInfo(userInfo);

            return new ControllerResult<>(userInfo);
        }catch (Exception e){
            e.printStackTrace();
            return new ControllerResult<>(false, "未知错误：" + e.getMessage());
        }
    }


    /**
     * 跳转到注册页面，用于二维码地址
     * 将推荐人编号默认填入注册页面
     * @param refereeUserCode   推荐人编号
     * @param model
     * @return  注册页面
     */
    @RequestMapping(value = "/registerView", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String registerView(@RequestParam(value = "refereeUserCode", required = false) String refereeUserCode, Model model) {
        if (refereeUserCode != null){
            // 查询推荐人基本信息
            UserInfo userInfo = userInfoService.queryUserInfoByCode(refereeUserCode);
            if (userInfo != null){
                model.addAttribute("refereeUserCode", refereeUserCode);
            }
        }

        return "register";
    }


    /**
     * 检查推荐人编号是否存在
     * 要求推荐人存在
     * @param refereeUserCode
     * @return  true推荐人存在
     */
    @RequestMapping(value = "/checkRefereeUserCode", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ControllerResult<?> checkRefereeUserCode(@RequestParam("refereeUserCode") String refereeUserCode) {
        // 基本信息校验
        if (StringUtil.isBlank(refereeUserCode))
            return new ControllerResult<>(false, "请输入推荐人编号");

        UserInfo userInfo = userInfoService.queryUserInfoByCode(refereeUserCode);
        if (BeanUtil.isBlank(userInfo)) {
            return new ControllerResult<>(false, "未找到此推荐人");
        }else {
            return new ControllerResult<>("推荐人存在");
        }
    }


    @RequestMapping(value = "/checkHigherUserCode", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ControllerResult<?> checkHigherUserCode(@RequestParam("higherUserCode") String higherUserCode) {
        // 基本信息校验
        if (StringUtil.isBlank(higherUserCode))
            return new ControllerResult<>(false, "请输入区域经理编号");

        UserInfo userInfo = userInfoService.queryUserInfoByCode(higherUserCode);
        if (BeanUtil.isBlank(userInfo)) {
            return new ControllerResult<>(false, "未找到此人");
        }

        if (userInfo.getRodeId() == null || ConfigUtil.USER_RODE_ID_COMMON.equals(userInfo.getRodeId())){
            return new ControllerResult<>(false, "这个人是普通会员，不能作为你的区域经理");
        }
        return new ControllerResult<>("上级编号存在");
    }


    /**
     * 检查会员编号是否存在
     * 会员编号唯一，不能重复
     * @param userCode
     * @return  true可注册此会员编号
     */
    @RequestMapping(value = "/checkUserCode", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ControllerResult<?> checkUserCode(@RequestParam("userCode") String userCode) {
        // 基本信息校验
        if (StringUtil.isBlank(userCode))
            return new ControllerResult<>(false, "请输入会员编号");

        UserInfo userInfo = userInfoService.queryUserInfoByCode(userCode);
        if (!BeanUtil.isBlank(userInfo)) {
            return new ControllerResult<>(false, "会员编号已被使用，请进行更换");
        }else {
            return new ControllerResult<>("可注册");
        }
    }

    /**
     * 用于注册成功后页面的跳转
     * @param userCode  userCode
     * @param model
     * @return  注册确认页面
     */
    @RequestMapping(value = "/registerConfirm", method = RequestMethod.GET)
    public String registerConfirmView(@RequestParam(value = "userCode" ) String userCode, Model model){
        // 查询用户基本信息
        UserInfo userInfo = userInfoService.queryUserInfoByCode(userCode);
        // 推荐人编号
        UserInfo registerUserInfo = userInfoService.queryUserInfoById(userInfo.getRefereeUserId());
        if (registerUserInfo != null){
            userInfo.setRefereeUserCode(registerUserInfo.getUserCode());
        }

        model.addAttribute("userInfo", userInfo);
        return "register_confirm";
    }
}
