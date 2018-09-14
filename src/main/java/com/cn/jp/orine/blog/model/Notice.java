package com.cn.jp.orine.blog.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Notice implements Serializable {

    private static final long serialVersionUID = 6389006501322746414L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;

    private Date createTime;

    private int showOrders;

    private Date updateTime;
}
