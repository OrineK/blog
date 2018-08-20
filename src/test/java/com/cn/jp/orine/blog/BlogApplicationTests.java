package com.cn.jp.orine.blog;

import com.cn.jp.orine.blog.dao.PermissionDao;
import com.cn.jp.orine.blog.dao.RoleDao;
import com.cn.jp.orine.blog.dao.UserDao;
import com.cn.jp.orine.blog.model.Permission;
import com.cn.jp.orine.blog.model.Role;
import com.cn.jp.orine.blog.model.User;
import com.cn.jp.orine.blog.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
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

    @Test
    public void contextLoads() {

    }

}
