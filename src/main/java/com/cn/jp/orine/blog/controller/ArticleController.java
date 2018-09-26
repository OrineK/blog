package com.cn.jp.orine.blog.controller;

import com.alibaba.fastjson.JSON;
import com.cn.jp.orine.blog.model.Article;
import com.cn.jp.orine.blog.model.Category;
import com.cn.jp.orine.blog.service.ArticleService;
import com.cn.jp.orine.blog.service.CategoryService;
import com.cn.jp.orine.blog.utils.JsonUtil;
import com.cn.jp.orine.blog.vo.ArticleQueryReq;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private CategoryService categoryService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("view/article");
        return mav;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JSON findArticleList(ArticleQueryReq req) {
        return JsonUtil.newJson().addData("data", articleService.findList(req)).toJson();
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView articleDetail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Article article = articleService.getAticleById(id);
        List<Category> categories = categoryService.list();
        modelAndView.addObject("article", article);
        modelAndView.addObject("categories", categories);
        modelAndView.setViewName("view/detail");
        return modelAndView;
    }

}
