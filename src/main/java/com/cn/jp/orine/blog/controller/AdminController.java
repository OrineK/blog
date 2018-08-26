package com.cn.jp.orine.blog.controller;

import com.alibaba.fastjson.JSON;
import com.cn.jp.orine.blog.constant.SysContant;
import com.cn.jp.orine.blog.model.Article;
import com.cn.jp.orine.blog.model.Category;
import com.cn.jp.orine.blog.model.User;
import com.cn.jp.orine.blog.service.ArticleService;
import com.cn.jp.orine.blog.service.CategoryService;
import com.cn.jp.orine.blog.utils.JsonUtil;
import com.cn.jp.orine.blog.vo.ArticleAddReq;
import com.cn.jp.orine.blog.vo.ArticleEditReq;
import com.cn.jp.orine.blog.vo.ArticleQueryReq;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiresRoles("admin")
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private ArticleService articleService;

    @Resource
    private CategoryService categoryService;

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(HttpSession session) {
        User user = (User) session.getAttribute(SysContant.USER_SESSION);
        ModelAndView mav = new ModelAndView();
        mav.addObject(user);
        mav.addObject("sidebar", "index");
        mav.setViewName("admin/index");
        return mav;
    }

    @RequestMapping(value = "/addArticle", method = RequestMethod.GET)
    public ModelAndView addArticle(HttpSession session) {
        User user = (User) session.getAttribute(SysContant.USER_SESSION);
        ModelAndView mav = new ModelAndView();
        mav.addObject(user);
        List<Category> categories = categoryService.list();
        mav.addObject("categories", categories);
        mav.addObject("sidebar", "article_add");
        mav.setViewName("admin/article_add");
        return mav;
    }

    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    @ResponseBody
    public JSON addArticle(ArticleAddReq req) {
        articleService.addArticle(req);
        return JsonUtil.newJson().toJson();
    }

    @RequestMapping(value = "/editArticle", method = RequestMethod.POST)
    @ResponseBody
    public JSON editArticle(ArticleEditReq req) {
        articleService.updateArticle(req);
        return JsonUtil.newJson().toJson();
    }

    @RequestMapping(value = "/delArticle/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JSON delArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return JsonUtil.newJson().toJson();
    }

    @RequestMapping(value = "/articleInfo/{id}", method = RequestMethod.GET)
    public ModelAndView articleInfo(@PathVariable Long id, HttpSession session) {
        Article article = articleService.getOneById(id);
        ModelAndView mav = new ModelAndView();
        User user = (User) session.getAttribute(SysContant.USER_SESSION);
        mav.addObject(user);
        mav.addObject(article);
        mav.addObject("sidebar", "article_list");
        mav.setViewName("admin/article_info");
        return mav;
    }

    @RequestMapping(value = "/articleList", method = RequestMethod.GET)
    public ModelAndView findArticleList(ArticleQueryReq req, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        User user = (User) session.getAttribute(SysContant.USER_SESSION);
        mav.addObject(user);
        mav.addObject("sidebar", "article_list");
        mav.addObject(articleService.findList(req));
        mav.setViewName("admin/article_list");
        return mav;
    }

}
