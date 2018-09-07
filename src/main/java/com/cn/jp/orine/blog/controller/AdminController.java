package com.cn.jp.orine.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.jp.orine.blog.Exception.BusinessException;
import com.cn.jp.orine.blog.constant.SysContant;
import com.cn.jp.orine.blog.model.Article;
import com.cn.jp.orine.blog.model.Category;
import com.cn.jp.orine.blog.model.Tag;
import com.cn.jp.orine.blog.model.User;
import com.cn.jp.orine.blog.service.ArticleService;
import com.cn.jp.orine.blog.service.CategoryService;
import com.cn.jp.orine.blog.utils.JsonUtil;
import com.cn.jp.orine.blog.vo.ArticleAddReq;
import com.cn.jp.orine.blog.vo.ArticleEditReq;
import com.cn.jp.orine.blog.vo.ArticleQueryReq;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.hibernate.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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
        System.out.println(req.toString());
        articleService.addArticle(req);
        return JsonUtil.newJson().toJson();
    }

    @RequestMapping(value = "/editArticle", method = RequestMethod.POST)
    @ResponseBody
    public JSON editArticle(ArticleAddReq req) {
        articleService.updateArticle(req);
        return JsonUtil.newJson().toJson();
    }

    @RequestMapping(value = "/delArticle/{id}", method = RequestMethod.POST)
    @ResponseBody
    public JSON delArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return JsonUtil.newJson().toJson();
    }

    @RequestMapping(value = "/editArticle/{id}", method = RequestMethod.GET)
    public ModelAndView articleInfo(@PathVariable Long id, HttpSession session) {
        ArticleEditReq article = articleService.getArticleEditInfoById(id);
        ModelAndView mav = new ModelAndView();
        User user = (User) session.getAttribute(SysContant.USER_SESSION);
        List<Category> categories = categoryService.list();
        mav.addObject("categories", categories);
        mav.addObject(user);
        mav.addObject("article", article);
        mav.addObject("sidebar", "article_list");
        mav.setViewName("admin/article_edit");
        return mav;
    }

    @RequestMapping(value = "/articleList", method = RequestMethod.GET)
    public ModelAndView findArticleList(ArticleQueryReq req, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        User user = (User) session.getAttribute(SysContant.USER_SESSION);
        mav.addObject(user);
        mav.addObject("sidebar", "article_list");
        mav.setViewName("admin/article_list");
        return mav;
    }

    @RequestMapping(value = "/articleListJson", method = RequestMethod.GET)
    @ResponseBody
    public JSON findArtList(ArticleQueryReq req, HttpSession session) {
        Page<Article> articlePage = articleService.findList(req);
        return JsonUtil.newJson().addData("count", articlePage.getTotalElements())
                .addData("data", articlePage.getContent()).toJson();
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView categoryList(HttpSession session) {
        ModelAndView mav = new ModelAndView();
        User user = (User) session.getAttribute(SysContant.USER_SESSION);
        mav.addObject(user);
        mav.addObject("sidebar", "category");
        mav.setViewName("admin/category_list");
        return mav;
    }

    @RequestMapping(value = "/delCategory/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JSON delCategory(@PathVariable("id") Long id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            logger.info("category not fund");
            throw new BusinessException("分类不存在，参数错误");
        }
        categoryService.delete(category);
        return JsonUtil.newJson().toJson();
    }

    @RequestMapping(value = "/editCategory/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JSON editCategory(@PathVariable("id") Long id, @RequestParam("name") String name) {
        Category category = categoryService.findById(id);
        if (category == null) {
            logger.info("category not fund");
            throw new BusinessException("分类不存在，参数错误");
        }
        category.setName(name);
        categoryService.editCategory(category);
        return JsonUtil.newJson().toJson();
    }

}
