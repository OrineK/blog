package com.cn.jp.orine.blog;

import com.cn.jp.orine.blog.dao.*;
import com.cn.jp.orine.blog.model.*;
import com.cn.jp.orine.blog.service.ArticleService;
import com.cn.jp.orine.blog.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private PermissionDao permissionDao;

    @Resource
    private TagDao tagDao;

    @Resource
    private ArticleDao articleDao;

    @Resource
    private ArticleService articleService;

    @Test
    public void contextLoads() {

    }

//    @Test
//    public void tagList() {
//        List<Tag> tags = tagDao.findAll();
//        for (Tag tag : tags) {
//            System.out.println(tag.getName() + ":" +tag.getArticles().size());
//        }
//    }
//
//    @Test
//    @Transactional
//    public void articleInfo() {
//        Article article = articleDao.getOne(5L);
//        System.out.println(article.toString());
//    }
}
