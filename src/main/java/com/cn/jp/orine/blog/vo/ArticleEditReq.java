package com.cn.jp.orine.blog.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class ArticleEditReq {

    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String title;

    @Setter
    @Getter
    private String content;

    @Setter
    @Getter
    private String coverImg; //封面图片

    @Setter
    @Getter
    private String summary;

    @Setter
    @Getter
    private String Author;  //作者

    @Setter
    @Getter
    private Long categoryId;  //分类id

    @Setter
    @Getter
    private List<Long> tagIds;  //标签ids

}
