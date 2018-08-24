package com.cn.jp.orine.blog.model;

import com.cn.jp.orine.blog.utils.DateUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(nullable = false, length = 100)
    private String title;  //标题

    @Setter
    @Getter
    private String coverImg; //封面图片

    @Setter
    @Getter
    private String summary; //摘要

    @Lob
    @Setter
    @Getter
    @Column(columnDefinition = "TEXT")
    private String content; //内容

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;

    @Setter
    @Getter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ArticleTag", joinColumns = {@JoinColumn(name = "articleId")},
            inverseJoinColumns = {@JoinColumn(name = "tagId")})
    private List<Tag> tags; //标签

    @Setter
    @Getter
    private Date createTime = DateUtil.getNow(); //创建时间

    @Setter
    @Getter
    private Date updateTime = DateUtil.getNow(); //修改时间

    @Setter
    @Getter
    private String Author;  //作者

    @Setter
    @Getter
    private Integer clickNum = 0;  //点击数

    @Setter
    @Getter
    private Integer startNum = 0;  //点赞数



}
