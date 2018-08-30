package com.cn.jp.orine.blog.service;

import com.cn.jp.orine.blog.dao.TagDao;
import com.cn.jp.orine.blog.model.Tag;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TagService {

    @Resource
    private TagDao tagDao;

    public List<Tag> tagList() {
        return tagDao.findAll();
    }

}
