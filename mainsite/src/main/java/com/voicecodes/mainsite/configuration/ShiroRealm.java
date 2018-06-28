package com.voicecodes.mainsite.configuration;

import com.voicecodes.domain.model.User;
import com.voicecodes.mainsite.service.IUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Author: YafengLiang
 * @Description:
 * @Date: Created in  15:00 2018/6/20
 */
public class ShiroRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroRealm.class);

    @Autowired
    private IUser userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();

        return authorizationInfo;
    }

    //校验
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        LOGGER.info("username::"+token.getUsername()+"  password::"+String.valueOf(token.getPassword()));
        User user = userService.getUserByLoginId(token.getUsername(), String.valueOf(token.getPassword()));

        if (user == null) {
            LOGGER.info("用户名或密码错误");
            throw new AccountException("用户名或密码错误");
        } else if (user.getState().equals("0")) {
            LOGGER.info("账号未生效,请联系系统管理员");
            throw new DisabledAccountException("账号未生效,请联系系统管理员");
        }
        return new SimpleAuthenticationInfo(user, String.valueOf(token.getPassword()), getName());
    }
}
