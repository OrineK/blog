package com.cn.jp.orine.blog;

import com.cn.jp.orine.blog.dao.PermissionDao;
import com.cn.jp.orine.blog.dao.RoleDao;
import com.cn.jp.orine.blog.dao.UserDao;
import com.cn.jp.orine.blog.model.Permission;
import com.cn.jp.orine.blog.model.Role;
import com.cn.jp.orine.blog.model.User;
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
public class InitAdmin {//初始化admin的一些数据

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private PermissionDao permissionDao;

    @Test
    public void addPermission() {
        Permission permission = new Permission();
        permission.setName("admin:add");
        permissionDao.save(permission);
    }

    @Test
    public void addRole() {
        Role role = new Role();
        role.setRole("admin");
        role.setDescription("管理员");
        roleDao.save(role);
    }

    @Test
    public void addRoleAndP() {
        Role role = roleDao.findRoleById(1L);
        List<Permission> permissions = permissionDao.findAll();
        role.setPermissions(permissions);
        roleDao.save(role);
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("admin");
        user.setSalt("123");
        String hashAlgorithmName = "MD5";
        Object crdentials = "123456";//密码原值
        Object salt = user.getSalt();//盐值
        int hashIterations = 1024;//加密1024次
        Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
        user.setPassword(result.toString());
        userDao.save(user);
    }

    @Test
    public void addUAR() {
        User user = userDao.findUserById(1L);
        List<Role> roles = roleDao.findAll();
        user.setRoles(roles);
        user.setRoles(roles);
        userDao.save(user);
    }
}
