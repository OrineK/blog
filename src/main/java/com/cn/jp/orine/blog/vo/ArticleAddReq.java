package com.cn.jp.orine.blog.vo;

import com.cn.jp.orine.blog.model.Article;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
public class ArticleAddReq {

    private Long id;

    private String title;

    private String content;

    private String coverImg; //封面图片

    private String summary;

    private String Author;  //作者

    private Long categoryId;  //分类id

    private List<String> tagNames;  //标签

    private Article.Type type;

}
