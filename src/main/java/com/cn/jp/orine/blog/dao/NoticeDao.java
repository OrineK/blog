package com.cn.jp.orine.blog.dao;

import com.cn.jp.orine.blog.model.Notice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeDao extends JpaRepository<Notice, Integer>, JpaSpecificationExecutor<Notice> {

    @Query("select n from Notice n")
    List<Notice> findAllBySort(Sort sort);
}
