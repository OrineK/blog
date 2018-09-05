package com.cn.jp.orine.blog.controller;

import com.alibaba.fastjson.JSON;
import com.cn.jp.orine.blog.service.ArticleService;
import com.cn.jp.orine.blog.utils.JsonUtil;
import com.cn.jp.orine.blog.vo.ArticleQueryReq;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public JSON findArticleList(ArticleQueryReq req) {
        return JsonUtil.newJson().addData("data", articleService.findList(req)).toJson();
    }
}
