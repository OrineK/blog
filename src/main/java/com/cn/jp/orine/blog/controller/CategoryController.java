package com.cn.jp.orine.blog.controller;

import com.alibaba.fastjson.JSON;
import com.cn.jp.orine.blog.service.CategoryService;
import com.cn.jp.orine.blog.utils.JsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    @ResponseBody
    public JSON categories() {
        return JsonUtil.newJson().addData("data", categoryService.list()).toJson();
    }
}
