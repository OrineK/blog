package com.cn.jp.orine.blog.controller;

import com.alibaba.fastjson.JSON;
import com.cn.jp.orine.blog.constant.SysContant;
import com.cn.jp.orine.blog.model.Notice;
import com.cn.jp.orine.blog.model.User;
import com.cn.jp.orine.blog.service.NoticeService;
import com.cn.jp.orine.blog.utils.JsonUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NoticeController {

    private final Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Resource
    private NoticeService noticeService;

    @RequiresRoles("admin")
    @RequestMapping(value = "/admin/notice", method = RequestMethod.GET)
    public ModelAndView index(HttpSession session) {
        User user = (User) session.getAttribute(SysContant.USER_SESSION);
        ModelAndView mav = new ModelAndView();
        mav.addObject(user);
        mav.addObject("sidebar", "notice");
        mav.setViewName("admin/notice");
        return mav;
    }

    @RequestMapping(value = "/notice/list", method = RequestMethod.GET)
    @ResponseBody
    public JSON noticeIndex() {
        List<Notice> notices = noticeService.list();
        return JsonUtil.newJson().addData("data", notices).toJson();
    }

    @ResponseBody
    @RequiresRoles("admin")
    @RequestMapping(value = "/admin/notice/add", method = RequestMethod.POST)
    public JSON saveNotice(HttpSession session, Notice notice) {
        noticeService.save(notice);
        return JsonUtil.newJson().toJson();
    }
}
