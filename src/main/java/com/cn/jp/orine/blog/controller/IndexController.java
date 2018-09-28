package com.cn.jp.orine.blog.controller;

import com.alibaba.fastjson.JSON;
import com.cn.jp.orine.blog.constant.ResultMsg;
import com.cn.jp.orine.blog.model.User;
import com.cn.jp.orine.blog.service.UserService;
import com.cn.jp.orine.blog.utils.JsonUtil;
import com.cn.jp.orine.blog.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
public class IndexController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        // 处理一个系统首装的时候的问题，管理员数据注册
        if (userService.userCount() <= 0) {
            return "view/initAdmin";
        }
        return "view/index";
    }

    @RequestMapping(value = "/initAdmin", method = RequestMethod.POST)
    @ResponseBody
    public JSON initAdmin(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (StringUtil.isBlank(username)) {
            return JsonUtil.newJson().addMessage("用户名必须填写").toJson();
        }
        if (StringUtil.isBlank(password)) {
            return JsonUtil.newJson().addMessage("密码必须填写").toJson();
        }
        User user = userService.saveInitAdmin(username, password);
        if (user != null) {
            return JsonUtil.newJson().toJson();
        }
        return JsonUtil.newJson().addMessage(ResultMsg.INIT_ADMIN_ERROR).toJson();
    }

}
