package com.cn.jp.orine.blog.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date(); //创建时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime = new Date(); //修改时间

    private String Author;  //作者

    private Integer clickNum = 0;  //点击数

    private Integer startNum = 0;  //点赞数

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {

        ORIGINAL("原创"),

        REPRINT("转载");

        private String text;

        private Type(String text) {
            this.text = text;
        }

        @JsonValue
        public String getText() {
            return text;
        }
    }
}
