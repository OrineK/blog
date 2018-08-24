package com.cn.jp.orine.blog;

import com.cn.jp.orine.blog.dao.ArticleDao;
import com.cn.jp.orine.blog.dao.CategoryDao;
import com.cn.jp.orine.blog.dao.TagDao;
import com.cn.jp.orine.blog.model.Article;
import com.cn.jp.orine.blog.model.Category;
import com.cn.jp.orine.blog.model.Tag;
import com.cn.jp.orine.blog.service.ArticleService;
import com.cn.jp.orine.blog.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitTagAndCate {

    @Resource
    private CategoryService categoryService;

    @Resource
    private TagDao tagDao;

    @Resource
    private ArticleDao articleDao;

    @Test
    public void addCate() {
        Category category = new Category();
        category.setName("程序人生");
        categoryService.addCategory(category);
    }

    @Test
    public void addTag() {
        Tag tag = new Tag();
        tag.setName("Java");
        tagDao.save(tag);
    }

}

