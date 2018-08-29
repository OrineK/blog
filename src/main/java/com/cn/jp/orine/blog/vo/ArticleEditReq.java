package com.cn.jp.orine.blog.vo;

import com.cn.jp.orine.blog.model.Article;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ArticleEditReq extends Article {

    @Setter
    @Getter
    private String tagNames;  //标签

}
