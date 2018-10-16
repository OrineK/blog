package com.cn.jp.orine.blog.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Orine
 * @Date: 2018/10/16
 * @Time: 15:52
 * 反馈信息
 */
@Data
@Entity
public class FeedBack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private String username;

    private int tourist;  //是否是游客 1是 0否

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
}
