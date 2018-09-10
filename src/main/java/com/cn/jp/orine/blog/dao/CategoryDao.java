package com.cn.jp.orine.blog.dao;

import com.cn.jp.orine.blog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryDao extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    Category findCategoryById(Long id);

    Category findByName(String name);
}
