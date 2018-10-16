package com.cn.jp.orine.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Orine
 * @Date: 2018/10/16
 * @Time: 15:30
 */
@Controller
public class ErrorController {

    @RequestMapping("error404")
    public String error404(){
        return "404";
    }
    @RequestMapping("error500")
    public String error500(){
        return "500";
    }
}
