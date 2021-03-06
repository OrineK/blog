package com.cn.jp.orine.blog.dao;

import com.cn.jp.orine.blog.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArticleDao extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    Article findFirstByIdBeforeOrderByIdDesc(Long id);

    Article findFirstByIdAfterOrderByIdAsc(Long id);
}
