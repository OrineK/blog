package com.cn.jp.orine.blog.controller;

import com.alibaba.fastjson.JSON;
import com.cn.jp.orine.blog.Exception.BusinessException;
import com.cn.jp.orine.blog.constant.ResultMsg;
import com.cn.jp.orine.blog.constant.SysContant;
import com.cn.jp.orine.blog.model.User;
import com.cn.jp.orine.blog.service.UserService;
import com.cn.jp.orine.blog.utils.IpAddrUtil;
import com.cn.jp.orine.blog.utils.JsonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/auth")
public class LoginController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSON submitLogin(String username, String password, HttpSession session,
                            HttpServletRequest request) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            String ipAddress = IpAddrUtil.getIpAddress(request);
            User user = (User) subject.getPrincipal();
            if (!subject.hasRole("admin")) {
                throw new BusinessException(ResultMsg.INSUFFICIENT_PERMISSIONS);
            }
            session.setAttribute(SysContant.USER_SESSION, user);
            user.setLoginIpAddress(ipAddress);
            userService.login(user);
        } catch (DisabledAccountException e) {
            throw new BusinessException(ResultMsg.USER_IS_BANNED);
        } catch (AuthenticationException e) {
            throw new BusinessException(ResultMsg.USERNAME_PASSWORD_ERROR);
        }
        // 执行到这里说明用户已登录成功
        return JsonUtil.newJson().addMessage(ResultMsg.USER_LOGIN_SUCCESS).toJson();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    //被踢出后跳转的页面
    @RequestMapping(value = "/kickout", method = RequestMethod.GET)
    public String kickOut() {
        return "kickout";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String html403() { return "403"; }

}
