package com.cn.jp.orine.blog.dao;

import com.cn.jp.orine.blog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagDao extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {

    Tag findTagByName(String name);
}
