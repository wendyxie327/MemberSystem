package com.member.shiro;

import com.member.user.service.UserInfoService;
import com.member.user.vo.UserInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomRealm extends AuthorizingRealm{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.debug("--------授权--------");
        UserInfo userInfo = (UserInfo) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        // 设置角色
        info.addRole(userInfo.getRodeId());
        logger.debug("rodeId = " + info.getRoles().toString());

        //TODO 设置相应权限
        // info.addStringPermissions();
        return info;
    }


    /**
     * 认证 - 登陆
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.debug("--------认证--------");
        String userName = (String) authenticationToken.getPrincipal();
        if (userName == null || "".equals(userName)) {
            return  null;
        }
        UserInfo userInfo = userInfoService.queryUserInfoByCode(userName);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userInfo,userInfo.getLoginPwd(),this.getName());
        return info;
    }
}
