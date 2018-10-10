package com.cn.jp.orine.blog.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer pid; //父级id

    private Long articleId; //文章id

    @Column(length = 50)
    private String username;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
}
