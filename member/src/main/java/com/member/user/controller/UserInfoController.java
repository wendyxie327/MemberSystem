package com.member.user.controller;

import com.member.common.dto.ControllerResult;
import com.member.common.dto.JTreeDto;
import com.member.common.util.BeanUtil;
import com.member.common.util.ConfigUtil;
import com.member.common.util.DateUtil;
import com.member.common.util.WordBookUtil;
import com.member.user.dto.RefereedUserDto;
import com.member.user.service.UserInfoService;
import com.member.user.vo.UserInfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户操作
 */
@Controller
@RequestMapping("/restful/userInfo")
public class UserInfoController {

    private static final String JSP_PATH_PREFIX = "views/user";

    @Autowired
    UserInfoService userInfoService;


    /**
     * 查询登陆用户的详细信息
     * @return  信息
     */
    @RequestMapping(value = "/queryLoginUserInfoDetail" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ControllerResult<?> queryLoginUserInfoDetail(HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if (userInfo == null) {
            return new ControllerResult<>(false, null , "未登录", WordBookUtil.NOT_LOGIN);
        }

        // 查询用户基本信息
        UserInfo loginUserInfo = userInfoService.queryUserInfoById(userInfo.getUserId());

        return new ControllerResult<>(loginUserInfo);
    }

    /**
     * 会员审核页面跳转
     *
     * @return 会员审核页面
     */
    @RequiresRoles("1")
    @RequestMapping(value = "/bossVerifyUserView")
    public String bossVerifyUserView(HttpSession httpSession, ServletRequest request, ServletResponse response, Model model){
        // 1.获取登陆用户基本信息
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("userInfo");
        if (userInfo == null) {
            return "login";
        }

        // 2.校验登陆用户权限： 只有老板才能审核
        if (!ConfigUtil.USER_RODE_ID_BOSS.equals(userInfo.getRodeId())){
            return "404";
        }

        // 3.查询需要审核的用户- 用户状态verify_status：1等待成为普通会员，3等待成为区域经理
        List<UserInfo> userInfos = userInfoService.queryNeedVerifyUserList();

        // 4.将查询结果传递给前台
        model.addAttribute("userInfos", userInfos);

        return JSP_PATH_PREFIX + "/boss_verify_user";
    }


    /**
     * 通过用户审核
     * 1. 更新此用户信息
     * 2. 在此用户推荐人的推荐人数上+1， 此处需要循环查询所有上级
     * @param userId    用户ID
     * @return  信息
     */
    @RequestMapping(value = "/agreeVerifyUser" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ControllerResult<?> agreeVerifyUser(@RequestParam("userId") String userId){
        if (userId == null) return new ControllerResult<>(false, "未选择审核的会员");

        UserInfo userInfo = userInfoService.queryUserInfoById(userId);
        if (userInfo == null) return new ControllerResult<>(false, "未找到该用户，请刷新表格");

        userInfoService.updateUserVerifyStatus(userId);

        return new ControllerResult<>("审核通过");
    }


    /**
     * 将推荐人编号默认填入注册页面
     * @param refereeUserCode   推荐人编号
     * @param model
     * @return  注册页面
     */
    @RequestMapping(value = "/registerViewContent", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String registerViewContent(@RequestParam(value = "refereeUserCode", required = false) String refereeUserCode, Model model) {
        if (refereeUserCode != null){
            // 查询推荐人基本信息
            UserInfo userInfo = userInfoService.queryUserInfoByCode(refereeUserCode);
            if (userInfo != null){
                model.addAttribute("refereeUserCode", refereeUserCode);
                model.addAttribute("registerBtn", "确认注册");
            }
        }

        return "register_content";
    }

    /**
     * 查询登陆用户直推列表
     * @return  直推列表页面
     */
    @RequestMapping(value = "/queryRefereeUserList", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public String queryRefereeUserList(HttpSession httpSession, Model model){
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("userInfo");
        if (userInfo == null) {
            return "login";
        }

        List<UserInfo> userInfos = userInfoService.queryRefereeUserList(userInfo.getUserId());
        model.addAttribute("userInfos", userInfos);
        return JSP_PATH_PREFIX +  "/referee_user_list";
    }

    @RequestMapping(value = "/queryRefereeAllUserTreeList" , method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public ControllerResult<?> queryRefereeAllUserTreeList(HttpSession httpSession, Model model){
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("userInfo");
        if (userInfo == null) {
            return new ControllerResult<>(false, null , "未登录", WordBookUtil.NOT_LOGIN);
        }

        List<RefereedUserDto> refereedUserDtos = userInfoService.queryRefereeAllUserList(userInfo.getUserId());

        // 对结果进行组装，封装成前台需要的格式
        List<JTreeDto<RefereedUserDto>> childrenTreeDto = refereeUserDto2JTree(refereedUserDtos);

        // 将该用户封装到最顶层
        List<JTreeDto<RefereedUserDto>> topTree = new ArrayList<>();
        JTreeDto<RefereedUserDto> jTreeDto = new JTreeDto<>();
        jTreeDto.setChildren(childrenTreeDto);
        jTreeDto.setText(userInfo.getUserCode());
        topTree.add(jTreeDto);

        return new ControllerResult<>(topTree);
    }


    /**
     * 将结构解析为树状图结构
     * @param refereedUserDtos
     * @return
     */
    private List<JTreeDto<RefereedUserDto>> refereeUserDto2JTree(List<RefereedUserDto> refereedUserDtos ){
        List<JTreeDto<RefereedUserDto>> treeDtos = new ArrayList<>();
        if (BeanUtil.isBlank(refereedUserDtos)){
            return treeDtos;
        }

        // 循环解析内部结构
        for (RefereedUserDto refereedUserDto : refereedUserDtos) {
            JTreeDto<RefereedUserDto> jTreeDto = new JTreeDto<>();
            jTreeDto.setData(refereedUserDto);
            jTreeDto.setText(refereedUserDto.getRefereeUserInfo().getUserCode());

            // 回调获取内部结构children
            List<JTreeDto<RefereedUserDto>> treeDtosDeep = refereeUserDto2JTree(refereedUserDto.getRefereedUserDtos());
            jTreeDto.setChildren(treeDtosDeep);

            treeDtos.add(jTreeDto);
        }
        return treeDtos;
    }


    /**
     * 更新登陆用户基本信息
     * @param userInfo  包含需要更新的内容
     * @return
     */
    @RequestMapping(value = "/updateLoginUserInfo")
    @ResponseBody
    public ControllerResult<?> updateLoginUserInfo(@RequestBody UserInfo userInfo, HttpSession httpSession){
        UserInfo sessionUserInfo = (UserInfo) httpSession.getAttribute("userInfo");
        if (sessionUserInfo == null) {
            return new ControllerResult<>(false, null , "未登录", WordBookUtil.NOT_LOGIN);
        }

        if (userInfo == null) return new ControllerResult<>(false,"传入参数错误");

        try {
            userInfoService.updateUserInfoBasic(sessionUserInfo.getUserId(), userInfo);
            return new ControllerResult<>("更新成功");
        }catch (Exception e){
            e.printStackTrace();
            return new ControllerResult<>(false, "更新失败："+ e.getMessage());
        }
    }
}
