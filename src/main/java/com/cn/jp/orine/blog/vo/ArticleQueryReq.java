package com.cn.jp.orine.blog.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Jpan on 2018/8/25/025.
 */
@Data
public class ArticleQueryReq extends PageReq  {

    @Setter
    @Getter
    private String title;

}
