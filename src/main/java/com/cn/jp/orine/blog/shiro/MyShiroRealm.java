package com.cn.jp.orine.blog.shiro;

import com.cn.jp.orine.blog.model.User;
import com.cn.jp.orine.blog.service.RoleService;
import com.cn.jp.orine.blog.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (principal instanceof User) {
            User userLogin = (User) principal;
            Set<String> roles = roleService.findRoleNameByUserId(userLogin.getId());
            authorizationInfo.addRoles(roles);
            System.out.println(userLogin.getUsername());
            Set<String> permissions = userService.findPermissionsByUserId(userLogin.getId());
            authorizationInfo.addStringPermissions(permissions);
        }
        logger.info("---- 获取到以下权限 ----");
        logger.info(authorizationInfo.getStringPermissions().toString());
        logger.info("---------------- Shiro 权限获取成功 ----------------------");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        logger.info("---------------- 执行 Shiro 凭证认证 ----------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        // 从数据库获取对应用户名密码的用户
        User user = userService.findByUsername(name);
        if (user != null) {
            logger.info("---------------- Shiro 凭证认证成功 ----------------------");
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    user, //用户
                    password, //密码
                    ByteSource.Util.bytes(user.getSalt()),
                    getName()  //realm name
            );
            return authenticationInfo;
        }
        throw new UnknownAccountException();
    }
}
