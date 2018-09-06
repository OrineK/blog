package com.cn.jp.orine.blog.controller;

import com.cn.jp.orine.blog.model.Article;
import com.cn.jp.orine.blog.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Resource
    private ArticleService articleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "view/index";
    }

}
