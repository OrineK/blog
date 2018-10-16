package com.cn.jp.orine.blog.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @Author: Orine
 * @Date: 2018/10/16
 * @Time: 15:26
 */
@Component
public class ErrorPageConfig implements ErrorPageRegistrar {

    private static final Logger logger = LoggerFactory.getLogger(ErrorPageConfig.class);

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages=new ErrorPage[2];
        errorPages[0]=new ErrorPage(HttpStatus.NOT_FOUND,"/error404");
        errorPages[1]=new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error500");
        registry.addErrorPages(errorPages);
    }
}
