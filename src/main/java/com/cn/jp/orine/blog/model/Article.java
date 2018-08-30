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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;  //标题

    private String coverImg; //封面图片

    private String summary; //摘要

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content; //内容

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ArticleTag", joinColumns = {@JoinColumn(name = "articleId")},
            inverseJoinColumns = {@JoinColumn(name = "tagId")})
    private List<Tag> tags; //标签

    private Date createTime = DateUtil.getNow(); //创建时间

    private Date updateTime = DateUtil.getNow(); //修改时间

    private String Author;  //作者

    private Integer clickNum = 0;  //点击数

    private Integer startNum = 0;  //点赞数

}
